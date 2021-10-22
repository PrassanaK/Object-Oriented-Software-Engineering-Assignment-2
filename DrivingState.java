//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package State;
public class DrivingState implements RobotState
{
    //does this while in driving state
    @Override
    public void doCommand(RobotContext context, String command)
    {
        //state set from driving to stopped
        context.setState(new StoppedState());
    }

    public String toString()
    {
        return "DRIVING";
    }
}
