package frc.robot.commands.auto;

//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class RotateTest extends SequentialCommandGroup
{
   
	public RotateTest()
    {

        super(
           // new MoveRobotSense(1, 0.5, 0, 0.0, 0.5,()->false)
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(2, Math.PI/2, 0, 0, Math.PI)
            //new InstantCommand(() -> Globals.runFlag = false )
        );
        
    }
       

}