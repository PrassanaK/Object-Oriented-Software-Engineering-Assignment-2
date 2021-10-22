package edu.curtin.comp2003.rover;
public class EngineSystem
{
    private double count = 0.0;
    private boolean driving = false;

    /** 
     * Begins driving forward. The effect is *not* to drive a fixed distance, but 
     * to simply start driving. The rover will not stop until the stopDriving() 
     * method is subsequently called.
     *
     * If startDriving() is called while the rover is already driving, it will
     * throw an exception.
     */
    public void startDriving() 
    {
        if(driving)
        {
            throw new IllegalStateException();
        }
        driving = true;
        System.out.println("Robot is driving");
    }

    /**
     * Stops driving.
     *
     * If stopDriving() is called while the rover is already stopped, it will 
     * throw an exception.
     */
    public void stopDriving() 
    {
        if(!driving)
        {
            throw new IllegalStateException();
        }
        driving = false;
        System.out.println("Robot has stopped");
    }

    /**
     * Immediately turns the rover by the specified angle anticlockwise (negative
     * for clockwise).
     */
    public void turn(double angle) 
    {
        System.out.println("Robot has turned " + angle + " degrees");
    }

    /**
     * Returns the total distance that the rover has ever driven, since it first 
     * landed on Mars. This figure is never reset. It remains constant while the 
     * rover is stopped, and increases while the rover is driving. 
     */     
    public double getDistanceDriven() 
    {
        return count += 50.0;
    }
}
