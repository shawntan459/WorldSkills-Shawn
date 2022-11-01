package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auto.MoveArm;
public class Place extends SequentialCommandGroup
{
    public Place(){
        super(
            new MoveArm(new Translation2d(0.33,0.24), 100),
            new MoveArm(new Translation2d(0.33,-0.07), 100),
           new MoveGripper("open",100),
            new MoveArm(new Translation2d(0.33,0.24), 100),
            new MoveGripper("close",100)

        );
    }
}
