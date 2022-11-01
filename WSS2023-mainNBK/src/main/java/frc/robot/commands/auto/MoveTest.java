package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// import the commands


/**
 * MoveTest class
 * <p>
 * This class uses SelectCommand to select 1 of 3 commands to execute
 */
public class MoveTest extends SequentialCommandGroup
{
    
	public MoveTest()
    {
        super(
            new MoveRobot(1, 0.5, 0, 0, 0.4), 
            new SelectCmds()
        );
    }
}
