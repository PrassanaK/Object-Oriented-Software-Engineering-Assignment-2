//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package Observer;
import edu.curtin.comp2003.rover.*;
public class Below4kmObserver implements EnvironmentObserver
{
    //update when visibility falls below 4km
    @Override
    public void update(double temp, double visibilityLevel, double lightLevel, EarthComm earthComm)
    {
        if(visibilityLevel < 4.0)
        {
            earthComm.sendMessage("E" + " " + temp + " " + visibilityLevel + " " + lightLevel);
        }
    }
}
