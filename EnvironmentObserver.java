package Observer;
import edu.curtin.comp2003.rover.*;
//interface for the observer pattern
public interface EnvironmentObserver
{
    public void update(double temp, double visibilityLevel, double lightLevel, EarthComm earthComm);
}
