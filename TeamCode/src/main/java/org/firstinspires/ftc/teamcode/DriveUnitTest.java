package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by JRC on 5/15/2018.
 */

@Autonomous(name="DriveUnitTest", group="Autonomous")
public class DriveUnitTest extends LinearOpMode {

    private Drive drive;
    private Robot sully;

    private DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor, leftGrabber, rightGrabber;
    private Servo rightArm, leftArm, jewelRotor, jewelArm;
    private ModernRoboticsI2cGyro gyro;
    private ColorSensor color;




    public void initialize() {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        liftMotor = hardwareMap.dcMotor.get("mainLift");
        leftGrabber = hardwareMap.dcMotor.get("leftGrabber");
        rightGrabber = hardwareMap.dcMotor.get("rightGrabber");
        rightArm = hardwareMap.servo.get("rightArm");
        leftArm = hardwareMap.servo.get("leftArm");
        jewelArm = hardwareMap.servo.get("jewelArm");
        jewelRotor = hardwareMap.servo.get("jewelRotor");
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        color = hardwareMap.colorSensor.get("color");



        sully = new Robot(frontRight,frontLeft,backRight,backLeft,liftMotor,leftGrabber, rightGrabber, rightArm,leftArm,jewelRotor,jewelArm,gyro,color);
        drive = new Drive(sully, this);

        jewelRotor.setPosition(sully.JEWEL_CENTER);
        jewelArm.setPosition(sully.JEWEL_UP);
        leftArm.setPosition(Robot.leftClose_Servo);
        rightArm.setPosition(Robot.rightClose_Servo);

        sully.gyro.calibrate();
        while (sully.gyro.isCalibrating() && opModeIsActive()) {
            idle();
        }

    }
    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        /*drive.raiseJewelArm();
        sleep(500);
        drive.lowerJewelArm();
        sleep(500);

        drive.puttLeft();
        sleep(500);
        drive.puttCenter();
        sleep(500);
        drive.puttAdjust();
        sleep(500);
        drive.puttRight();
        sleep(500);

        */
        /*drive.armsActive();
        sleep(500);
        drive.armsOut();
        sleep(500);
        */

        /*drive.liftUp();
        sleep(2000);
        drive.liftDown();
        sleep(500);
        */

        /*
        drive.puttCenter();
        sleep(500);
        drive.raiseJewelArm();
        sleep(500);
        */


        drive.straightForDistance(24,0.25);
        sleep(500);
        drive.straightForDistance(-24,0.50);
        sleep(500);
        drive.strafe(24, 0.50);
        sleep(500);

        drive.strafe(-24,0.50);
        sleep(500);


        /*drive.turnForDegrees(90,0.50);
        sleep(500);
        drive.turnForDegrees(-90, 0.50);
        sleep(500);*/
        telemetry.addData("Angle: ", String.format("%d", sully.gyro.getIntegratedZValue()));
        telemetry.update();
        drive.turnForDegrees(90,0.50);
        sleep(500);
        telemetry.addData("Angle: ", String.format("%d", sully.gyro.getIntegratedZValue()));
        telemetry.update();
        sleep(5000);



    }
}

