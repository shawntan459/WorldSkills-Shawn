package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
//Subsystem imports


/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveServo2 extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private final static Arm m_arm = RobotContainer.m_arm;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal;
    private TrapezoidProfile.State m_setpoint;
    private int m_dir;
    private double start_pos;
    private double dist;

    private final double tgt_pos;
    /**
     * This command moves the robot a certain distance following a trapezoidal speed profile.
     * <p>
     * 
     * @param pos - target position
     * @param maxSpeed - max speed of robot
     */
    //This move the robot a certain distance following a trapezoidal speed profile.
    public MoveServo2(double pos, double maxSpeed)
    {
        m_constraints = new TrapezoidProfile.Constraints(maxSpeed, maxSpeed);
        tgt_pos = pos;

    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        start_pos = m_arm.getServoAngle0();
        dist = tgt_pos - start_pos;
 
        //Negative distance don't seem to work with the library function????
        //Easier to make distance positive and use m_dir to keep track of negative speed.
        m_dir = (dist>0)?1:-1;
        dist *= m_dir;          
        
        m_goal = new TrapezoidProfile.State(dist, 0);
        m_setpoint = new TrapezoidProfile.State(0, 0);
        m_endFlag = false;
    }
    /**
     * Condition to end speed profile
     */
    public boolean endCondition()
    {
        return false;
    }
    /**
     * Called continously until command is ended
     */
    @Override
    public void execute()
    {
        //Create a new profile to calculate the next setpoint(speed) for the profile
        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = profile.calculate(dT);
        m_arm.setServoAngle0( m_setpoint.position*m_dir + start_pos);

        if ((m_setpoint.position>=m_goal.position) ) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.
            m_arm.setServoAngle0( m_setpoint.position*m_dir + start_pos);
            m_endFlag = true;
        }
    }

    /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    public void end(boolean interrupted)
    {

    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        return m_endFlag;
    }

}