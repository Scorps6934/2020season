/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.commands.DetectColor;
import frc.robot.commands.drive_commands.SetAngle;
import frc.robot.commands.drive_commands.DistancePID;
import frc.robot.commands.drive_commands.KeepAngle;
import frc.robot.commands.drive_commands.ManualDrive;
import frc.robot.subsystems.S_Drive;
import frc.robot.subsystems.S_Spinner;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public final S_Drive sdrive = new S_Drive();
  private final S_Spinner sspinner = new S_Spinner();

  private final DetectColor c_commandColor = new DetectColor(sspinner);
  private final ManualDrive c_manualDrive = new ManualDrive(sdrive);
  private final DistancePID c_distancePID = new DistancePID(sdrive);
  private final SetAngle c_setAngle = new SetAngle(sdrive);

  // controllers
  public static Joystick driveController = new Joystick(Constants.logitechDriveCont);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    sdrive.setDefaultCommand(c_distancePID);
    sspinner.setDefaultCommand(c_commandColor);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(driveController, Constants.buttonA).whileHeld(new SetAngle(sdrive));
    // new JoystickButton(driveController, Constants.buttonB).toggleWhenPressed(new
    // DistancePID(sdrive));
    new JoystickButton(driveController, Constants.rightBumper).whileHeld(new KeepAngle(sdrive));
    new POVButton(driveController, Constants.dpadUp).whenPressed(() -> {
      sdrive.runMotor(.5, .5);
      Timer.delay(.1);
      sdrive.runMotor(0, 0);
    }, sdrive);
    new POVButton(driveController, Constants.dpadDown).whenPressed(() -> {
      sdrive.runMotor(-.5, -.5);
      Timer.delay(.1);
      sdrive.runMotor(0, 0);
    }, sdrive);
    new POVButton(driveController, Constants.dpadLeft).whenPressed(() -> {
      sdrive.runMotor(-.5, .5);
      Timer.delay(.05);
      sdrive.runMotor(0, 0);
    }, sdrive);
    new POVButton(driveController, Constants.dpadRight).whenPressed(() -> {
      sdrive.runMotor(.5, -.5);
      Timer.delay(.05);
      sdrive.runMotor(0, 0);
    }, sdrive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    TrajectoryConfig config = new TrajectoryConfig(Constants.maxTrajVelocity, Constants.maxTrajAcceleration);
    config.setKinematics(sdrive.getKinematics());
    // An example trajectory to follow. All units in meters.
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config);

    RamseteCommand command = new RamseteCommand(trajectory, sdrive::getPose, new RamseteController(2.0, 0.7),
        sdrive.getFeedforward(), sdrive.getKinematics(), sdrive::getSpeeds, sdrive.getLeftTrajPIDController(),
        sdrive.getRightTrajPIDController(), sdrive::setOutput, sdrive);

    return command;
  }
}