// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.units.measure.*;
import static edu.wpi.first.units.Units.*;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    
    // SwingArm
    public static final boolean SWING_MOTOR_INVERT = false;
    public static final String SWING_GEAR_RATIO = "12:9";
    public static final int SWING_MOTOR_CURRENT_LIMIT = 40;
    public static final double SWING_MOTOR_RAMP_RATE = 20;

    public static final Angle ARM_SOFT_MIN = Degrees.of(-45);
    public static final Angle ARM_SOFT_MAX = Degrees.of(45);
    public static final Angle ARM_HARD_MIN = Degrees.of(-90);
    public static final Angle ARM_HARD_MAX = Degrees.of(90);
    public static final Angle ARM_START_ANGLE = Degrees.of(0);
    public static final Distance ARM_LENGTH = Feet.of(1);
    public static final Mass ARM_MASS = Pounds.of(1);
    public static final Angle ARM_DEFAULT_ANGLE = Degrees.of(0);

    public static final Angle CNTRL_SET_POS = Degrees.of(30);
    public static final Angle CNTRL_SET_NEG = Degrees.of(-30);

    public static final double CNTRL_CYCLE_POS = .1;
    public static final double CNTRL_CYCLE_NEG = -.1;

    public static final Voltage SYSID_MAX_VOLTAGE = Volts.of(7);
    public static final Velocity<VoltageUnit> SYSID_VELOCITY = Volts.of(7).per(Second);
    public static final Time SYSID_DURATION = Seconds.of(4);

    // Motor Controller Mappings
    public static final int TALONFX_0 = 0;
    public static final int TALONFX_1 = 1;

    //CAN
    public static final CANBus CAN_BUS_MAIN = new CANBus();
  }
}
