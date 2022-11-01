package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class RotatePickUpToDeposit extends SequentialCommandGroup {
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static double maxSpeed1 = 0.2;

    public RotatePickUpToDeposit() {
        super(
                new Pick(),
                new MoveRobot(2, Math.PI, 0, 0, 0.5),
                new MoveRobotSense(0, 10, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 55),
                new MoveRobotSense(1, 10, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 20),
                new MoveRobotSense(0, 10, 0, 0, 0.25, () -> m_sensor.getCobraTotal() > 3500),
                new MoveRobotSense(1, 1, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 10)

        );
    }
}
