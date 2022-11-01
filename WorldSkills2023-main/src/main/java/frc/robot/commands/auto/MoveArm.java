package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
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
public class MoveArm extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private final static Arm m_arm = RobotContainer.m_arm;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal_0;
    private TrapezoidProfile.State m_setpoint_0;
    private TrapezoidProfile.State m_goal_1;
    private TrapezoidProfile.State m_setpoint_1;
    private TrapezoidProfile m_profile_0;
    private TrapezoidProfile m_profile_1;


    private final Translation2d tgt_pos;
    private double[] targetAngles;
    /**
     * This command moves the robot a certain distance following a trapezoidal speed profile.
     * <p>
     * 
     * @param pos - target position
     * @param maxSpeed - max speed of robot
     */

    public MoveArm(Translation2d pos, double maxSpeed)
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
        targetAngles = m_arm.getArmAngles(tgt_pos);
        double start_pos_0 = m_arm.getServoAngle0();
        double start_pos_1 = m_arm.getServoAngle1();
        m_goal_0 = new TrapezoidProfile.State(targetAngles[0], 0);
        m_goal_1 = new TrapezoidProfile.State(targetAngles[1], 0);
        m_setpoint_0 = new TrapezoidProfile.State(start_pos_0, 0);
        m_setpoint_1 = new TrapezoidProfile.State(start_pos_1, 0);
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
        m_profile_0 = new TrapezoidProfile(m_constraints, m_goal_0, m_setpoint_0);
        m_profile_1 = new TrapezoidProfile(m_constraints, m_goal_1, m_setpoint_1);
        m_setpoint_0 = m_profile_0.calculate(dT);
        m_setpoint_1 = m_profile_1.calculate(dT);
        m_arm.setServoAngle0(m_setpoint_0.position);
        m_arm.setServoAngle1(m_setpoint_1.position);
        if ((m_profile_0.isFinished(dT)) && (m_profile_1.isFinished(dT)) ) {
            //distance reached End the command
            //m_arm.setServoAngle0( m_goal.position);
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