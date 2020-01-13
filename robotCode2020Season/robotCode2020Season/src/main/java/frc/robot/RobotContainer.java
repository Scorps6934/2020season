/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DetectColor;
import frc.robot.commands.RunNeo;
import frc.robot.commands.SetAngle;
import frc.robot.subsystems.S_Neo;
import frc.robot.subsystems.S_Spinner;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final S_Neo sneo = new S_Neo();
  private final S_Spinner sspinner = new S_Spinner();


  private final DetectColor c_commandColor = new DetectColor(sspinner);
  private final RunNeo c_runNeo = new RunNeo(sneo);
  //private final SetAngle c_setAngle = new SetAngle();

  //controllers
  Joystick driveController = new Joystick(Constants.logitechDriveCont);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    sneo.setDefaultCommand(c_runNeo);
    sspinner.setDefaultCommand(c_commandColor);

    sneo.resetEncPosition();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(driveController, Constants.buttonA).whileHeld(new SetAngle(sneo));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SetAngle(sneo); //TODO: change this to an actual auto command
  }
}