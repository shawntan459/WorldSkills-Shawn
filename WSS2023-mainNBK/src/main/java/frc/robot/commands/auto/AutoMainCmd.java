package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

// import the commands

/**
 * Auto mode main class
 * <p>
 * This class creates the auto command to drive the robot during autonomous mode
 */
public class AutoMainCmd extends SequentialCommandGroup
{   

	public AutoMainCmd()
    {
    //    super( 
    //         new LoopCmd(new MoveBack(), ()->RobotContainer.m_sensor.getIRDistance()<30) 
    //    );

        super( 
            // new MoveServo(300, 150) ,
            // new MoveServo(0, 150), 
            // new MoveServo(50, 150)
            new MoveArm(0.2, 0.0, 0.5) ,
            new MoveArm(0.2, 0.2, 0.5) ,
            new MoveArm(0.4, 0.2, 0.5),
            new MoveArm(0.4, 0.0, 0.5),
            new MoveArm(0.2, 0.0, 0.5),
            new MoveArm(0.2, 0.2, 0.5) ,
            new MoveArm(0.4, 0.2, 0.5),
            new MoveArm(0.4, 0.0, 0.5),
            new MoveArm(0.2, 0.0, 0.5)
  
        );


        // super(
        //     new MoveRobot(1, 0.5, 0, 0, 0.4),  
        //     new MoveRobot(0, 0.5, 0, 0, 0.4),  
        //     new MoveRobot(1, -0.5, 0, 0, 0.4),  
        //     new MoveRobot(0, -0.5, 0, 0, 0.4),  
        //     new MoveRobot(2, Math.PI*2, 0, 0, Math.PI),
        //     new MoveRobot(2, -Math.PI*2, 0, 0, Math.PI)
        //      );
    }
    /**
     * Code here will run once when the command is called for the first time
     */
    // @Override
    // public void initialize()
    // {
    //     //Must be initialised before super.initialise
    //     super.initialize();
    //     //RobotContainer.m_arm.initialize();
    // }
}
