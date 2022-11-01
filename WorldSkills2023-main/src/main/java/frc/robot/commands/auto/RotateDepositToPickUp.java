package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class RotateDepositToPickUp extends SequentialCommandGroup {
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static double maxSpeed1 = 0.2;
    private final static double maxSpeed2 = 0.5;

    public RotateDepositToPickUp() {
        super(
                new Place(),
                new MoveRobotSense(1, -10, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() > 15),
                new MoveRobot(2, -Math.PI, 0, 0, maxSpeed2),
                new MoveRobotSense(0, 5, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 50),
                new MoveRobotSense(1, 5, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 15),
                new MoveRobotSense(0, 10, 0, 0, maxSpeed1, () -> m_sensor.getCobraTotal() > 3500),
                new MoveRobotSense(1, 1, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 10)

        );
    }
}
