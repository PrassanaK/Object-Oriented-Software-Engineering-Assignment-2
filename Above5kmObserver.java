//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package Observer;
import edu.curtin.comp2003.rover.*;
public class Above5kmObserver implements EnvironmentObserver
{
    //update if visibility is greater than 5km update is invoked
    @Override
    public void update(double temp, double visibilityLevel, double lightLevel, EarthComm earthComm)
    {
        if(visibilityLevel > 5.0)
        {
            earthComm.sendMessage("E" + " " + temp + " " + visibilityLevel + " " + lightLevel);
        }
    }
}
