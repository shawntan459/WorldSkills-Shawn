package frc.robot.commands.auto;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.Globals;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveTest;
import frc.robot.commands.auto.functionX;
//import frc.robot.commands.auto.MoveIRSensor;
import frc.robot.commands.auto.RotateTest;
import frc.robot.commands.auto.MoveRobotSense.end_func;
import frc.robot.subsystems.Sensor;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup {

    private final static Sensor m_sensor = RobotContainer.m_sensor;

    public AutoMainCmd() {

        super(
                // new MoveRobot(0, 0, 0, 0, 0), // Move left
                // new MoveRobot(0, 0, 0, 0, 0), // Move Forward

                // new MoveRobot(0, 0, 0, 0, 0), // Move left
                // new MoveRobot(0, 0, 0, 0, 0) // Move Forward

                // new MoveRobot(1, 0.5, 0, 0, 5),
                // new MoveRobot(0, -1.1 + 0.3, 0, 0, 5),
                // new MoveRobotSense(1, 1, 0, 0, 5, () -> m_sensor.getIRDistance() < 10),
                // new Pick(),
                new MoveRobot(2, Math.PI, 0, 0, Math.PI),
                new MoveRobot(2, Math.PI, 0, 0, Math.PI),
                new MoveRobot(2, Math.PI, 0, 0, Math.PI),
                new MoveRobot(2, Math.PI, 0, 0, Math.PI)

        // new LoopCmd(new functionX(), () -> (++Globals.loopCount) > 8)

        // new MoveRobot(0, -2, 0, 0, 5),
        // new MoveRobot(1, -.3, 0, 0, 5),
        // new MoveRobot(2, (Math.PI - Math.PI / 7), 0, 0, Math.PI),
        // new MoveRobotSense(0, 10, 0, 0, 0.25, () -> m_sensor.getCobraTotal() > 3500),
        // new MoveRobotSense(1, 1, 0, 0, 5, () -> m_sensor.getIRDistance() < 10),
        // new Pick(),

        // new MoveRobot(1, -.3, 0, 0, 5),
        // new MoveRobot(2, (Math.PI - Math.PI / 7), 0, 0, Math.PI),

        // new MoveRobot(0, 2, 0, 0, 5),
        // new MoveRobotSense(0, 10, 0, 0, 0.25, () ->
        // RobotContainer.m_sensor.getCobraTotal() > 3500),
        // new MoveRobotSense(1, 1, 0, 0, 5, () ->
        // RobotContainer.m_sensor.getIRDistance() < 10),
        // new Pick()

        // new Pick()
        // new MoveArm(new Translation2d(0.2,0),25),
        // new MoveArm(new Translation2d(0.3,0),25),
        // new MoveArm(new Translation2d(0.3,0.3),25),
        // new MoveArm(new Translation2d(0.2,0.3),25),
        // new MoveArm(new Translation2d(0.2,0),25)
        );

    }

    @Override
    public void initialize() {
        // Initialize done before base initialization
        RobotContainer.m_arm.initialize();
        super.initialize();

    }

}
