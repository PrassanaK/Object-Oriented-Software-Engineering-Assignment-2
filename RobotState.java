//***********************************************
//Prassana Kamalakannan
//Date: 3/06/2021
//***********************************************
package State;
//interface for the state pattern
public interface RobotState
{
    public void doCommand(RobotContext context, String command);
}
