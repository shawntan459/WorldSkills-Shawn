/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.auto.AutoMainCmd;
import frc.robot.commands.tele.*;
import frc.robot.subsystems.*;
public class RobotContainer {

  //subsystems
  public final static OI m_oi = new OI();
  public final static OmniDrive m_omnidrive = new OmniDrive();
  public final static Sensor m_sensor = new Sensor(); 
  public final static Arm m_arm = new Arm();
  public final static CobraSensor m_cobra = new CobraSensor();  
  //user menu
  public final static Menu m_menu = new Menu(m_oi);
  //commands
  public final static TeleCmd m_teleCmd = new TeleCmd(m_omnidrive, m_oi,m_arm);
  public final static AutoMainCmd m_autoCmd = new AutoMainCmd();


  public RobotContainer()
  {
      //Create new instances

      //Set the default command for the hardware subsytem
      //m_omnidrive.setDefaultCommand(m_teleCmd);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCmd;
  }
  public Command getTeleopCommand() {
    // An ExampleCommand will run in autonomous
    return m_teleCmd;
  }
  
}
