// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.constants.Constants.OperatorConstants.*;
import static frc.robot.constants.TunerConstants.PIDConstants.*;

import com.ctre.phoenix6.hardware.TalonFX;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import yams.gearing.GearBox;
import yams.gearing.MechanismGearing;
import yams.mechanisms.config.ArmConfig;
import yams.mechanisms.positional.Arm;
import yams.motorcontrollers.SmartMotorController;
import yams.motorcontrollers.SmartMotorControllerConfig;
import yams.motorcontrollers.SmartMotorControllerConfig.ControlMode;
import yams.motorcontrollers.SmartMotorControllerConfig.MotorMode;
import yams.motorcontrollers.SmartMotorControllerConfig.TelemetryVerbosity;
import yams.motorcontrollers.remote.TalonFXWrapper;

public class SwingArm extends SubsystemBase {
  
  private SmartMotorControllerConfig swingArmSMCConfig = new SmartMotorControllerConfig(this)
  .withControlMode(ControlMode.CLOSED_LOOP)
  // PID constants
  .withClosedLoopController(SWING_KP, SWING_KI, SWING_KD, SWING_MAX_VEL, SWING_MAX_ACC)
  .withSimClosedLoopController(SWING_KP, SWING_KI, SWING_KD, SWING_MAX_VEL, SWING_MAX_ACC)
  // Feedforward Constants
  .withFeedforward(new ArmFeedforward(SWING_FF_KS, SWING_FF_KG, SWING_FF_KV))
  .withSimFeedforward(new ArmFeedforward(SWING_FF_KS, SWING_FF_KG, SWING_FF_KV))
  // Telemetry name & verbosity
  .withTelemetry("Swing Talon Controller", TelemetryVerbosity.HIGH)
  // Gearing rotor>shaft
  // Set to constants later
  .withGearing(new MechanismGearing(GearBox.fromStages(SWING_GEAR_RATIO)))
  // .withGearing(SmartMechanism.gearing(SmartMechanism.gearbox(3,4))) --DEPRECATED--
  // Overcurrent protection
  .withMotorInverted(SWING_MOTOR_INVERT)
  .withIdleMode(MotorMode.BRAKE)
  .withStatorCurrentLimit(Amps.of(SWING_MOTOR_CURRENT_LIMIT))
  .withClosedLoopRampRate(Seconds.of(SWING_MOTOR_RAMP_RATE))
  .withOpenLoopRampRate(Seconds.of(SWING_MOTOR_RAMP_RATE));

  private TalonFX swingTalonPrimary = new TalonFX(TALONFX_0, CAN_BUS_MAIN);
  //final TalonFX swingTalonSecondary = new TalonFX(TALONFX_1);

  private SmartMotorController talonControllerPrimary = new TalonFXWrapper(swingTalonPrimary, DCMotor.getKrakenX60(1), swingArmSMCConfig);

  private ArmConfig armCfg = new ArmConfig(talonControllerPrimary)
  .withSoftLimits(ARM_SOFT_MIN, ARM_SOFT_MAX)
  .withHardLimit(ARM_HARD_MIN, ARM_HARD_MAX)
  .withStartingPosition(ARM_START_ANGLE)
  .withLength(ARM_LENGTH)
  .withMass(ARM_MASS)
  .withTelemetry("Arm", TelemetryVerbosity.HIGH);

  private Arm arm = new Arm(armCfg);

  /**
   * Set the angle of the arm.
   * @param angle Angle to go to.
   */
  public Command setAngle(Angle angle) { return arm.setAngle(angle);}

  /**
   * Move the arm up and down.
   * @param dutycycle [-1, 1] speed to set the arm too.
   */
  public Command set(double dutycycle) { return arm.set(dutycycle);}

  /**
   * Run sysId on the {@link Arm}
   */
  public Command sysId() { return arm.sysId(SYSID_MAX_VOLTAGE, SYSID_VELOCITY, SYSID_DURATION);}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    arm.updateTelemetry();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    arm.simIterate();
  }
}
