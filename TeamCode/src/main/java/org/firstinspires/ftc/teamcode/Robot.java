package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by JRC on 5/15/2018.
 *
 * Robot contains all of the hardware objects for Sully.  This is passed to any methods in classes
 * that need hardware access
 */

public class Robot extends Object {

    DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor;
    Servo rightArm, leftArm, jewelRotor, jewelArm;
    ModernRoboticsI2cGyro gyro;
    ColorSensor color;

    final public double JEWEL_CENTER = 0.84;
    final public double JEWEL_OFFCENTER = 0.82;   // for trying a second read of jewel color
    final public double JEWEL_LEFT = 1.00;
    final public double JEWEL_RIGHT = 0.76;
    final public double JEWEL_UP = 0.00;
    final public double JEWEL_DOWN = 0.57;

    final public double RIGHT_ARM_IN = -0.50;
    final public double LEFT_ARM_IN = 1.00;
    final public double RIGHT_ARM_OUT = 0.00;
    final public double LEFT_ARM_OUT = 0.95;
    final public double RIGHT_ARM_ACTIVE = 0.05;
    final public double LEFT_ARM_ACTIVE = 0.96;

    final public int LIFT_UP = (int)(1170*6/(0.5*3.14159));
    final public int LIFT_DOWN = 0;

    public Robot(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL, DcMotor lM, Servo rA, Servo lA, Servo jR, Servo jA, ModernRoboticsI2cGyro g, ColorSensor c) {
        frontRight = fR;
        frontLeft = fL;
        backRight = bR;
        backLeft = bL;
        liftMotor = lM;
        leftArm = lA;
        rightArm = rA;
        jewelRotor = jR;
        jewelArm = jA;
        gyro = g;
        color = c;

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        liftMotor.setDirection(DcMotor.Direction.REVERSE);

    }
}
