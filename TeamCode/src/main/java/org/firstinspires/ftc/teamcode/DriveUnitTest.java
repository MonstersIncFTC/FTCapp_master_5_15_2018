package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 5/15/2018.
 */

public class DriveUnitTest extends LinearOpMode {

    Drive drive;
    Robot sully;

    DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor;
    Servo rightArm, leftArm, jewelRotor, jewelArm;
    ModernRoboticsI2cGyro gyro;
    ColorSensor color;

    final private double JEWEL_CENTER = 0.85;
    final private double JEWEL_LEFT = 0.70;
    final private double JEWEL_RIGHT = 1.00;
    final private double JEWEL_UP = 0.50;
    final private double JEWEL_DOWN = 0.00;

    final private double RIGHT_ARM_IN = 0.30;
    final private double LEFT_ARM_IN = 0.30;
    final private double RIGHT_ARM_OUT = 0.70;
    final private double LEFT_ARM_OUT = 0.70;

    final private int LIFT_UP = 1000;
    final private int LIFT_DOWN = 0;

    public void initialize() {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        rightArm = hardwareMap.servo.get("rightArm");
        leftArm = hardwareMap.servo.get("leftArm");
        jewelArm = hardwareMap.servo.get("jewelArm");
        jewelRotor = hardwareMap.servo.get("jewelRotor");
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        color = hardwareMap.colorSensor.get("color");

        jewelRotor.setPosition(JEWEL_CENTER);
        jewelArm.setPosition(JEWEL_UP);
        rightArm.setPosition(RIGHT_ARM_IN);
        leftArm.setPosition(LEFT_ARM_IN);

        gyro.calibrate();
        while (gyro.isCalibrating() && opModeIsActive()) {
            idle();
        }

    }
    @Override
    public void runOpMode() {
        initialize();



    }
}

