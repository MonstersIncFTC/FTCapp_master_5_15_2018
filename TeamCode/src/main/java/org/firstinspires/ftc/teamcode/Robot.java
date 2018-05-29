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

    DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor, leftGrabber, rightGrabber;
    Servo rightArm, leftArm, jewelRotor, jewelArm;
    ModernRoboticsI2cGyro gyro;
    ColorSensor color;

    final public double JEWEL_CENTER = 0.84;
    final public double JEWEL_OFFCENTER = 0.81;   // for trying a second read of jewel color
    final public double JEWEL_LEFT = 1.00;
    final public double JEWEL_RIGHT = 0.74;
    final public double JEWEL_UP = 0.00;
    final public double JEWEL_DOWN = 0.49;

    public static final double rightClose_Servo =  1.00 ;
    public static final double rightOpen_Servo =  0.55;
    public static final double rightEngage_Servo = 0.65;
    public static final double leftClose_Servo =  0.00;
    public static final double leftOpen_Servo =  0.45;
    public static final double leftEngage_Servo = 0.35;


    final public double LIFT_TICKS_PER_INCH = 1120 / (0.75*3.14159);
    final public int LIFT_UP = (int) (6 * LIFT_TICKS_PER_INCH);
    final public int LIFT_DOWN = 0;

    public Robot(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL, DcMotor lM, DcMotor lG, DcMotor rG, Servo rA, Servo lA, Servo jR, Servo jA, ModernRoboticsI2cGyro g, ColorSensor c) {
        frontRight = fR;
        frontLeft = fL;
        backRight = bR;
        backLeft = bL;
        liftMotor = lM;
        leftArm = lA;
        rightArm = rA;
        leftGrabber = lG;
        rightGrabber = rG;
        jewelRotor = jR;
        jewelArm = jA;
        gyro = g;
        color = c;

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        liftMotor.setDirection(DcMotor.Direction.REVERSE);

    }
}
