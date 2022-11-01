package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class PickUpToBase extends SequentialCommandGroup{
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    
    public PickUpToBase(){
        super(
            new MoveRobot(1, -0.755, 0, 0, 2.5),
            new MoveRobot(0, 0.85, 0, 0, 2.5)    
        );
    }
}
