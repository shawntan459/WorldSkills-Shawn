package frc.robot.subsystems;
import com.studica.frc.Cobra;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class CobraSensor extends SubsystemBase{
        // Sensors
        private Cobra cobra;
    
        // Good for debugging
        // Shuffleboard
        private final ShuffleboardTab tab = Shuffleboard.getTab("Cobra");
        private final NetworkTableEntry D_Cobra_0 = tab.add("Cobra Value (0): ", 0).getEntry();
        private final NetworkTableEntry D_Cobra_1 = tab.add("Cobra Value (1): ", 0).getEntry();
        private final NetworkTableEntry D_Cobra_2 = tab.add("Cobra Value (2): ", 0).getEntry();
        private final NetworkTableEntry D_Cobra_3 = tab.add("Cobra Value (3): ", 0).getEntry();
    
        public CobraSensor() {
            //Constuct a new instance
            cobra = new Cobra();
        }
        /**
         * Call for the raw ADC value
         * <p>
         * 
         * @param channel range 0 - 3 (matches what is on the adc)
         * @return value between 0 and 2047 (11-bit)
         */
        public int getCobraRawValue(final int channel) {
            return cobra.getRawValue(channel); 
        }
        
        /**
         * Code that runs once every robot loop
         */
        
        // Runs every 20ms
        @Override
        public void periodic()
        {
            D_Cobra_0.setNumber(getCobraRawValue(0));
            D_Cobra_1.setNumber(getCobraRawValue(1));
            D_Cobra_2.setNumber(getCobraRawValue(2));
            D_Cobra_3.setNumber(getCobraRawValue(3));
            
        }
}
