// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.constants.Constants.OperatorConstants;
import frc.robot.subsystems.SwingArm;

import static frc.robot.constants.Constants.OperatorConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SwingArm swingArm = new SwingArm();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    swingArm.setDefaultCommand(swingArm.setAngle(ARM_DEFAULT_ANGLE));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // Schedule `setAngle` when the Xbox controller's A/B button is pressed,
    // cancelling on release.
    m_driverController.a().whileTrue(swingArm.setAngle(CNTRL_SET_NEG));
    m_driverController.b().whileTrue(swingArm.setAngle(CNTRL_SET_POS));
    // Schedule `set` when the Xbox controller's X/Y button is pressed,
    // cancelling on release.
    //m_driverController.x().whileTrue(swingArm.set(CNTRL_CYCLE_NEG));
    //m_driverController.y().whileTrue(swingArm.set(CNTRL_CYCLE_POS));

    m_driverController.rightBumper().whileTrue(swingArm.sysId());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
   return Autos.exampleAuto(swingArm);
  }
}
