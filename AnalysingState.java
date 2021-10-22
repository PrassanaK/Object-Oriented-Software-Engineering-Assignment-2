//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package State;
public class AnalysingState implements RobotState
{
    //Does this command while in analysing state
    @Override
    public void doCommand(RobotContext context, String command)
    {
        //sets state to stopped when it is invoked
        context.setState(new StoppedState());
    }

    public String toString()
    {
        return "ANALYSING";
    }
}
