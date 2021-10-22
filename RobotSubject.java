//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package Observer;
import edu.curtin.comp2003.rover.*;
public class RobotSubject
{
    //This class is the Event source
    //Only one observer is needed.
    private EnvironmentObserver observer;

    //setObserver instead of add observer
    //creates observer depending on what the event is
    public void setObserver(EnvironmentObserver inObserver)
    {
        observer = inObserver;
    }

    public void notifyObserver(double temp, double visibility, double lightLevel, EarthComm earthComm)
    {
        observer.update(temp, visibility, lightLevel, earthComm);
    }
}
