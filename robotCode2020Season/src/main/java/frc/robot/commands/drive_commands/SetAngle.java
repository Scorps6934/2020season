/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive_commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.limelight.LimeLight;
import frc.robot.subsystems.S_Drive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetAngle extends PIDCommand {
  /**
   * Creates a new SetAngle.
   */
  private static double turnP = 0.023, turnI = 0, turnD =0.00115;
  private S_Drive sub;
  private LimeLight limelight;

  public SetAngle(S_Drive sub, LimeLight limelight) { // TODO: make continuously editable pid values

    super(
      // The controller that the command will use
      new PIDController(turnP, turnI, turnD),
      /*new PIDController( // for tuning
        SmartDashboard.getNumber("turnP", 0),
        SmartDashboard.getNumber("turnI", 0),
        SmartDashboard.getNumber("turnD", 0)
      ),*/
      // This should return the measurement
      () -> sub.gyro.getYaw(), //maybe Math.IEEtAngleEremainder(sub.gyro.getYaw(), 360) instead
      // This should return the setpoint (can also be a constant)
      limelight.getHorizontalOffset(),
      //() -> SmartDashboard.getNumber("turnSetpoint", 0), //for tuning
      // This uses the output
      output -> {
        // Use the output here
        sub.arcadeDrive(0,output);
        //System.out.println("yaw: " + sub.gyro.getYaw());
      });
    // Use addRequirements() here to declare subsystem dependencies.

    System.out.println("turnP: " + SmartDashboard.getNumber("turnP", 0));
    System.out.println("turnI: " + SmartDashboard.getNumber("turnI", 0));
    System.out.println("turnD: " + SmartDashboard.getNumber("turnD", 0));
    System.out.println("turnSetpoint: " + SmartDashboard.getNumber("turnSetpoint", 0));
    addRequirements(sub);
    this.sub = sub;
    this.limelight = limelight;
    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180); // maybe change depending on the navx
    getController().setTolerance(0, 0); //copied constants (5,10) (may need adjusting and put into constants.java)
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return getController().atSetpoint();
    return false;
  }

  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
    sub.diffDrive.setDeadband(0);
    sub.gyro.reset();
    getController().setSetpoint(limelight.getHorizontalOffset());
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted){
      sub.diffDrive.setDeadband(.02);
    }
  }



}