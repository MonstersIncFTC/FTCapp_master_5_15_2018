package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 5/15/2018.
 *
 * Robot contains all of the hardware objects for Sully.  This is passed to any methods in classes
 * that need hardware access
 */

public class Robot extends Object {

    DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor;
    Servo rightArm, leftArm, jewelRotor, jewelArm;
    IntegratingGyroscope gyro;
    ColorSensor color;

    public Robot(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL, DcMotor lM, Servo rA, Servo lA, Servo jR, Servo jA, IntegratingGyroscope g, ColorSensor c) {
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

    }
}
