package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class PickUpToDeposit extends SequentialCommandGroup {
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static double maxSpeed1 = 0.2;

    public PickUpToDeposit() {

        super(

                new Pick(),
                new MoveRobot(0, -0.7, 0, 5, 5),
                new MoveRobotSense(1, 10, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 60),
                new MoveRobotSense(0, -10, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 50),
                new MoveRobotSense(1, 10, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 20),
                new MoveRobotSense(0, -10, 0, 0, 0.25, () -> m_sensor.getCobraTotal() > 3500),
                new MoveRobotSense(1, 1, 0, 0, maxSpeed1, () -> m_sensor.getIRDistance() < 10)

        );

    }
}