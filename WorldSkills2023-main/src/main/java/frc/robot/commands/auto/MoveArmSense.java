package frc.robot.commands.auto;
import frc.robot.commands.auto.MoveArm;
import edu.wpi.first.wpilibj.geometry.Translation2d;


public class MoveArmSense extends MoveArm
{
    private final end_func f_ptr;
    interface end_func {
        public boolean endCondition();
    }
    
    /**
     * This command moves the robot a certain distance following a trapezoidal speed profile.
     * <p>
     * 
     * @param pos - target position
     * @param maxSpeed - max speed of robot
     * @param f - function that defines early end condition
     */
    public MoveArmSense(Translation2d pos, double maxSpeed, end_func f)
    {
        super(pos, maxSpeed);
        
        f_ptr = f;
    }

    @Override
    public boolean endCondition()
    {
        return f_ptr.endCondition();
    }
}