//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package State;
public class StoppedState implements RobotState
{
    @Override
    public void doCommand(RobotContext context, String command)
    {
        //there are two possible states it could change into from stopped state
        if(command.charAt(0)==('D'))
        {
            context.setState(new DrivingState());
        }
        if(command.equals("S"))
        {
            context.setState(new AnalysingState());
        }
    }

    public String toString()
    {
        return "STOPPED";
    }
}
