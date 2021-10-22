//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package Main;
import edu.curtin.comp2003.rover.*;
import Controller.*;
public class Main
{
    public static void main(String [] args)
    {

        EarthComm earthComm = new EarthComm();
        Sensors sensors = new Sensors();
        EngineSystem engine = new EngineSystem();
        SoilAnalyser soilAnalyser = new SoilAnalyser();

        //Robot object created and takes in EarthComm, Sensors, EngineSystem and SoilAnalyser object
        //Dependency Injection
        RobotController robot = new RobotController(earthComm, sensors, engine, soilAnalyser);

        //Thread is started
        robot.start();
    }
}
