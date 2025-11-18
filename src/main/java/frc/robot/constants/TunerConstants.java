// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;

import edu.wpi.first.units.measure.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class TunerConstants {
  public static class PIDConstants {
    
    // SwingArm
    public static final double SWING_KP = 55; //55
    public static final double SWING_KI = 0; //
    public static final double SWING_KD = 10; //10

    public static final double SWING_FF_KS = 0; //0
    public static final double SWING_FF_KG = 4; //.005
    public static final double SWING_FF_KV = 0; //0

    public static final AngularVelocity SWING_MAX_VEL = DegreesPerSecond.of(90);
    public static final AngularAcceleration SWING_MAX_ACC = DegreesPerSecondPerSecond.of(60);


  }
}
