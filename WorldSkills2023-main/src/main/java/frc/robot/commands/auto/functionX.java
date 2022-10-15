package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;

public class functionX extends SequentialCommandGroup {
    public functionX() {
        super(
                new YellowToTop(),

                new TopToYellow(),

                new YellowToBottom(),

                new BottomToYellow());
    }
}
