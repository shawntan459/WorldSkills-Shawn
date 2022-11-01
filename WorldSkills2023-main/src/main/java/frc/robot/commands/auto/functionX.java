package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.Astar.Layout;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class functionX extends SequentialCommandGroup {
    public static int testPos0[] = { 210, 1210, 0 };
    public static int testPos1[] = { 900, 900, 0 };
    public static int depoPos1[] = { 600, 3400, 0 };
    public static int homeBase[] = { 300, 300, 0 };
    public static int depoPos2[] = { 1500, 3300, 0 };
    private final static Sensor m_sensor = RobotContainer.m_sensor;

    public functionX() {

        super(
                new MovetoB(Layout.Convert_mm_Pose2d(depoPos1)),
                new MoveRobot(2, -Math.PI, 0, 0, Math.PI),
                new alignmentLeft(),
                new WaitCommand(1),
                new MoveRobot(2, -Math.PI, 0, 0, Math.PI),
                new MovetoB(Layout.Convert_mm_Pose2d(testPos1)),
                new alignmentLeft(),
                new WaitCommand(1),
                new MovetoB(Layout.Convert_mm_Pose2d(depoPos2)),
                new alignmentLeft(),
                new WaitCommand(1));
    }
}
