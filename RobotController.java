package Controller;
import java.util.*;
import java.util.Base64.Encoder;
import edu.curtin.comp2003.rover.*;
import State.*;
import Observer.*;
public class RobotController extends Thread
{
    private EarthComm earthComm;
    private Sensors sensors;
    private EngineSystem engineSystem;
    private SoilAnalyser soilAnalyser;
    private double totalDist;
    private double currAngle;
    private RobotContext context;
    private RobotSubject subject;

    //dependency injection
    public RobotController(EarthComm inComm, Sensors inSensors, EngineSystem inEngine, SoilAnalyser inAnalyser)
    {
        earthComm = inComm;
        sensors = inSensors;
        engineSystem = inEngine;
        soilAnalyser = inAnalyser;
        totalDist = 0.0;
        currAngle = 0.0;
        context = new RobotContext();
        context.setState(new StoppedState());
        subject = new RobotSubject();
    }

    public void run() 
    {
        String command;   
        byte[] results;
        
        //infinite wile loop
        while(true)
        {
            command = earthComm.pollCommand();//retrieves command
            if(command != null)//if command exists
            {
                String[] arr = command.split(" ");
                if(arr[0].equals("D")&&arr.length<2)//if the D command is not accompanied by distance value
                {
                    System.out.println("Invalid command. Please provide distance value as well");
                }
                else if(arr[0].equals("T") && arr.length<2)//if the T command is not accompanied byangle
                {
                    System.out.println("Invalid command. Please provide turn angle as well");
                }
                else if(arr[0].equals("D"))//if valid D command is invoked
                {
                    if((context.getState().toString().equals("ANALYSING")))//if robot is analysing, command is invalid
                    {
                        System.out.println("Invalid command");
                        earthComm.sendMessage("!" + " " + "D" + " " + arr[1]);
                    }
 
                    if((!context.getState().toString().equals("DRIVING")))//if robot is not driving command is valid
                    {
                        engineSystem.startDriving();
                    }
                 
                    double targetDist = Double.parseDouble(arr[1]);//target distance for robot is set

                    if(context.getState().toString().equals("DRIVING"))//if D command is invoked while robot is already driving, new distance is set regardless of previous D command
                    {
                        totalDist = targetDist + engineSystem.getDistanceDriven();
                    }
                    else
                    {
                        totalDist = totalDist + targetDist;
                        context.doCommand("D");
                    }
                }
                else if(arr[0].equals("T"))//if valid T command is invoked
                {
                    if(context.getState().toString().equals("ANALYSING"))//if robot is analysing, command invalid
                    {
                        System.out.println("invalid command");
                        earthComm.sendMessage("!" + " " + "T" + " " + arr[1]);
                    }
                    else
                    {
                        double angle = Double.parseDouble(arr[1]);
                        engineSystem.turn(angle);
                    }
                }
                else if(arr[0].equals("P"))//Command to take a photo
                {
                    Encoder encoder = Base64.getEncoder();
                    String encodedString = encoder.encodeToString(sensors.takePhoto());
                    earthComm.sendMessage("P" + " " + encodedString);
                } 
                else if(arr[0].equals("E"))//command to read environmental data
                {
                    double temp = sensors.readTemperature();
                    double visibility = sensors.readVisibility();
                    double lightLevel = sensors.readLightLevel();
                    earthComm.sendMessage("E" + " " + temp + " " + visibility + " " + lightLevel);
                }
                else if(arr[0].equals("S"))//command is invoke soil analysis
                {
                    if(context.getState().toString().equals("DRIVING"))//if robot is already driving, command is invalid
                    {
                        System.out.println("invalid command");
                        earthComm.sendMessage("!" + " " + "S");
                    }
                    else
                    {
                        soilAnalyser.startAnalysis();
                        context.doCommand("S");
                    }
                }
                else//if any other command is given, it is invalid
                {
                    System.out.println("invalid command");
                    earthComm.sendMessage("!");
                }

                double inTemp = sensors.readTemperature();
                double excessiveVisibility = sensors.readVisibility();
                double inLightLevel = sensors.readLightLevel();
                //Environmental data is scanned regardless of command to scan whether or not visibility falls below 4km or above 5km
                if(excessiveVisibility < 4.0)
                {
                    subject.setObserver(new Below4kmObserver());
                    subject.notifyObserver(inTemp, excessiveVisibility, inLightLevel, earthComm);
                    
                }
                else if(excessiveVisibility > 5.0)
                {
                    subject.setObserver(new Above5kmObserver());
                    subject.notifyObserver(inTemp, excessiveVisibility, inLightLevel, earthComm);
                }
                else
                {
                    System.out.println("Invalid command");
                }
                
                
            }
            
            //if robot is driving and the robot reaches it's destination, message is sent
            if((context.getState().toString().equals("DRIVING")) && totalDist <= (engineSystem.getDistanceDriven()))
            {
                context.setState(new StoppedState());
                engineSystem.stopDriving();
                earthComm.sendMessage("D");
            }

            //soil scanning
            if((context.getState().toString().equals("ANALYSING")))
            {
                results = soilAnalyser.pollAnalysis();
                if(results != null)
                {
                    context.setState(new StoppedState());
                    Encoder newEncoder = Base64.getEncoder();
                    String encodedSoil = newEncoder.encodeToString(results);
                    earthComm.sendMessage("S" + " " + encodedSoil);
                }
            }

            //sleep
            try
            { 
                Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }
}
