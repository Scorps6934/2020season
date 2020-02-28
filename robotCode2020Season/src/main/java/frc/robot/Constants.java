/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  //neo sparkmaxes -- the ids on the side of the pdp with lower numbers has their can id set to 1+pdpNumber
  //TODO: set for final robot
  public static final int NEOlf = 15;
  public static final int NEOlb = 16;
  public static final int NEOrf = 2;
  public static final int NEOrb = 1;

  public static final int spinnerMotoPort = 14;
  public static final int winchFalcon = 4; //TODO: finalize port number

  public static final double driveGearRatio = 10.71;

  //TODO: finalize module number
  public static final int compressorModule = 0; //TODO: make sure this works

  //Duotake falcon ports
  public static final int intakePort = 3;

  //Duotake talon ports 
  public static final int topConveyerPort = 7;
  public static final int bottomConveyerPort = 8;

  //Duotake solenoid TODO: finalize port numbers
  public static final int duotakeSolenoidPort = -1;
  public static final int duotakeSolenoidReverse = -1; 
  public static final int duotakeSolenoidForward = -1;

  //climb solenoids TODO: finalize port numbers
  public static final int climbLeftSolenoidPort = -1;
  public static final int climbLeftSolenoidReverse = -1; 
  public static final int climbLeftSolenoidForward = -1;

  public static final int climbRightSolenoidPort = -1;
  public static final int climbRightSolenoidReverse = -1; 
  public static final int climbRightSolenoidForward = -1;
  
  //color indices -- in counter clockwise order
  public static final int kBlue = 0;
  public static final int kYellow = 1;
  public static final int kRed = 2;
  public static final int kGreen = 3;
  public static final int kUnknown = 4;

  //conversion factors
  public static final double neoRevs2meters = 0.1524*Math.PI/driveGearRatio;
  public static final double meters2NeoRevs = 1/neoRevs2meters;

  //trajectory related value (in SI units) TODO: POPULATE VALUES
  public static final double trackWidth = 0;
  public static final double kS = 0;
  public static final double kV = 0;
  public static final double kA = 0;
  public static final double kPTraj = 0;
  public static final double maxTrajVelocity = 0;
  public static final double maxTrajAcceleration = 0;

  // limelight
  public static final int lemonPipeline = -1; //TODO: populate lemonPipeline id


  //old 


  //controllers
  public static final int logitechDriveCont = 0;
  public static final int logitechWeaponsCont = 1;

  //logitech button
  public static final int buttonA = 1;
  public static final int buttonB = 2;
  public static final int buttonX = 3;
  public static final int buttonY = 4;
  public static final int leftBumper = 5;
  public static final int rightBumper = 6;
  public static final int backButton = 7;
  public static final int startButton = 8;
  public static final int dpadUp =  0;
  public static final int dpadDown =  180;
  public static final int dpadLeft =  270;
  public static final int dpadRight =  90;

  //arcade/weapons controller
  public static final int spinClockwiseBtn = 8; // counter clockwise for motor
  public static final int spinClockBtn = 6; // clockwise for motor
  public static final int rotation1Btn = 9; // rotation
  public static final int rotation2Btn = 10; // color
  public static final int spinPneuToggleBtn = 11;
  public static final int extakeTogglePOV = 90;
  public static final int intakeOutBtn = 11; //left top
  public static final int intakeInPOV = 270;  //left bottom
  public static final int extakeOutPOV = 180; //right top
  public static final int extakeInPOV = 0; //right bottom
  public static final int unmap1 = 0;
  public static final int unmap2 = 1;
  public static final int unmap3 = 2;
  public static final int armExtendSwitch = 4;
  public static final int winchSwitch = 5;
  //public static final int weaponsAxisY = 1;
  //public static final int weaponsAxisX = 0;

  //joystick axes
  public static final int leftAxisX = 0;
  public static final int leftAxisY = 1;
  public static final int rightAxisX = 4;
  public static final int rightAxisY = 5;
  //triggers
  public static final int leftTrigger = 2;
  public static final int rightTrigger = 3;

  public static enum AutoID{
    HIGHTIDE, WIN, RIGHT, TEST
  }


  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
