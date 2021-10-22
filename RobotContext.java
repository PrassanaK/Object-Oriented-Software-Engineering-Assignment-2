//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package State;
//Context for the state pattern
public class RobotContext
{
    private RobotState state;

    public RobotContext()
    {
        state = null;
    }

    public void setState(RobotState inState)
    {
        state = inState;
    }

    public RobotState getState()
    {
        RobotState inState = null;
        try
        {
            inState = state;
        }
        catch(NullPointerException e)//This exception is caught in the case that state is null
        {
            System.out.println("Nullpointer exception thrown in RobotContext");
        }
        return inState;
    }

    public void doCommand(String command)
    {
        state.doCommand(this, command);
    }
}
