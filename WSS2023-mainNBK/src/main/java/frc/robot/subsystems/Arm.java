package frc.robot.subsystems;

import java.util.Map;

import com.studica.frc.Servo;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private final Servo servo0;
    private final Servo servo1;
    private final Servo servo2;
    private final double a1 = 0.24; 
    private final double a2 = 0.32; 
    private final double default_x = 0.2;
    private final double default_y = -0.1;
    private double m_x, m_y;  //current arm tip position

    //The offset is required as motor cannot be mounted perfectly.
    //Also a preferred offset is required due to limited range of servo motor movement
    //For example, studica servo range is 0-300 deg.
    //After gearing 4:1, the range is reduced to 0-75deg.
    //The offset allows us to choose the most effect mounting position for servo.
    //Servo0 is mounted at 45deg so that the arm can move from 45 to 105 deg which is more useful.
    //Hence offset0 is 45*4 = 180 degree, theorectically.

    private double offset0 = -160;   //For making software adjustment to servo 
    private double offset1 = -24;

    //gearing of servo motor to joint
    private double ratio0 = 4.0;
    private double ratio1 = 2.0;

    // Good for debugging
    // Shuffleboard
    private final ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private final NetworkTableEntry D_servo0 = tab.add("servo0", 0).getEntry();
    private final NetworkTableEntry D_servo1 = tab.add("servo1", 0).getEntry();
    private final NetworkTableEntry D_servo2 = tab.add("servo2", 0).getEntry();
    private final NetworkTableEntry D_offset0 = tab.add("offset0", offset0).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -200, "max", +200)).getEntry();
    private final NetworkTableEntry D_offset1 = tab.add("offset1", offset1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -100, "max", +100)).getEntry();
    //private final NetworkTableEntry D_offset0 = tab.addPersistent("offset0", 0).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -500, "max", +500)).getEntry();
    //private final NetworkTableEntry D_offset1 = tab.addPersistent("offset1", 0).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -500, "max", +500)).getEntry();

    private final NetworkTableEntry D_posX = tab.add("posX", 0).getEntry();
    private final NetworkTableEntry D_posY = tab.add("posY", 0).getEntry();
    private final NetworkTableEntry D_debug1 = tab.add("debug1", 0).getEntry();
    private final NetworkTableEntry D_debug2 = tab.add("debug2", 0).getEntry();
    private final NetworkTableEntry D_debug3 = tab.add("debug3", 0).getEntry();
    private final NetworkTableEntry D_sliderX = tab.add("setX", default_x).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0.05, "max", 0.5)) .getEntry();
    private final NetworkTableEntry D_sliderY = tab.add("setY", default_y).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", -0.2, "max", 0.5)) .getEntry();
    
   
    public Arm () {
        servo0 = new Servo(Constants.SERVO_0);  //shoulder
        servo1 = new Servo(Constants.SERVO_1);  //elbow
        servo2 = new Servo(Constants.SERVO_2);  //gripper
        m_x = default_x;;
        m_y = default_y;;
        setArmPos(m_x, m_y);

    }
    
    public void initialize() {
        //setArmPos(m_x, m_y);
    }

    /**
     * Sets the servo0 angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public void setServoAngle0(final double degrees) {
        servo0.setAngle(degrees);
    }

    /**
     * Get slider-x value
     * <p>
     * 
     * @return return slider value
     */
    public double getSliderX( ) {
        return D_sliderX.getDouble(0.04);
    }

    /**
     * Get slider-y value
     * <p>
     * 
     * @return return slider value
     */
    public double getSliderY( ) {
        return D_sliderY.getDouble(0.0);
    }
    public double getSliderOffset0( ) {
        return D_offset0.getDouble(0.0);
    }
    public double getSliderOffset1( ) {
        return D_offset1.getDouble(0.0);
    }

    /**
     * Sets the servo0 angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public double getServoAngle0( ) {
        return servo0.getAngle();
    }
    /**
     * Sets the servo1 angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public void setServoAngle1(final double degrees) {
        servo1.setAngle(degrees);
    }

    /**
     * Get the arm tip x position
     * <p>
     * 
     * @param : none
     */
    public double getArmPosX( ) {
        return m_x;
    }

    public double getArmPosY( ) {
        return m_y;
    }
    /**
     * Sets the arm tip (x,y) position
     * <p>
     * 
     * @param pos (x,y) position of arm tip
     */
    public void setArmPos(double x, double y ) {

        //Refer to https://www.alanzucconi.com/2018/05/02/ik-2d-1/

        // arm tip  cannot be physically in the area around origin
        if ( x<0.15  ) {
            x = 0.15;
        }
        m_x = x;
        m_y = y;

        double a = a2;
        double c = a1;
        double b = Math.sqrt(x*x+y*y);
        double alpha = Math.acos( (b*b + c*c - a*a)/(2*b*c) );
        double beta = Math.acos( (a*a + c*c - b*b)/(2*a*c) );

        // A is servo0 angle wrt horizon
        // When A is zero, arm-c is horizontal.
        // B is servo1 angle wrt arm-c (BA)
        // When B is zero, arm-a is opened parallel  to arm-c
        double B = Math.PI - beta;    
        double A = alpha + Math.atan2(y,x);

        //servo0 and servo1 might be mounted clockwise or anti clockwise.
        //offset0 and offset1 are used to adjust the zero the arm position.
        //This makes it easier to mount and tune the arm.
        A = (Math.toDegrees(A) )*ratio0; 
        B = (Math.toDegrees(B) )*ratio1; 

        servo0.setAngle(A + offset0);    //servo0 offset is -15 degrees * ratio0
        servo1.setAngle(B + offset1);    //servo1 offset is also -15 degrees * ratio1

        //D_debug1.setDouble(A);
        //D_debug2.setDouble(B);
    }

    public void Debug(double x, double y, double z) {
        D_debug1.setDouble(x);
        D_debug2.setDouble(y);
        D_debug3.setDouble(z);
    }
    /**
     * Code that runs once every robot loop
     */
    @Override
    public void periodic()
    {
        //offset0 = D_offset0.getDouble(offset0);
        //offset1 = D_offset1.getDouble(offset1);

        //Unnecessary display should be removed during contest
        D_servo0.setDouble(servo0.getAngle());
        D_servo1.setDouble(servo1.getAngle());
        D_servo2.setDouble(servo2.getAngle());
        D_posX.setDouble(m_x);
        D_posY.setDouble(m_y);
        //D_posX.setDoubleArray( {m_pos.getX(), m_pos.getY()});
    }
}
