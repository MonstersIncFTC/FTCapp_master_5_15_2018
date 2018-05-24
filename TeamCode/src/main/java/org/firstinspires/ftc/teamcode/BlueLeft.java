package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;


/**
 * Created by CCA on 5/18/2018.
 */

@Autonomous(name="BlueLeft", group="Autonomous")

public class BlueLeft extends LinearOpMode {
    public static final String TAG = "Vuforia Vumark Sample";



    private Drive drive;
    private Robot sully;
    private JewelKnocker jewelKnocker;

    private DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor, leftGrabber, rightGrabber;
    private Servo rightArm, leftArm, jewelRotor, jewelArm;
    private ModernRoboticsI2cGyro gyro;
    private ColorSensor color;
    private PictoReader pictoReader;

    public final double DISTANCE_TO_CRYPTOBOX = 28.0;
    public final double LEFT_STRAFE = 3.0;
    public final double CENTER_STRAFE = 9.0;
    public final double RIGHT_STRAFE = 15.0;

    public void initialize() {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        liftMotor = hardwareMap.dcMotor.get("mainLift");
        rightArm = hardwareMap.servo.get("rightArm");
        leftArm = hardwareMap.servo.get("leftArm");
        jewelArm = hardwareMap.servo.get("jewelArm");
        jewelRotor = hardwareMap.servo.get("jewelRotor");
        leftGrabber = hardwareMap.dcMotor.get("leftGrabber");
        rightGrabber = hardwareMap.dcMotor.get("rightGrabber");
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        color = hardwareMap.colorSensor.get("color");



        sully = new Robot(frontRight,frontLeft,backRight,backLeft,liftMotor,leftGrabber, rightGrabber,
                rightArm, leftArm, jewelRotor, jewelArm, gyro, color);
        drive = new Drive(sully, this);
        pictoReader = new PictoReader(this);
        jewelKnocker = new JewelKnocker(this, sully, drive);

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
        RelicRecoveryVuMark vuMark = pictoReader.ReadPictograph();


        telemetry.update();
        jewelKnocker.knockRed();
        drive.raiseLift(7.0, 0.50);
        drive.armsOut();
        sleep(500);
        drive.raiseLift(-7.0, 0.50);
        drive.straightForDistance(2.0, 0.50);
        drive.armsActive();
        sleep(500);
        drive.spinIn(300);


        /*
        drive.straightForDistance(DISTANCE_TO_CRYPTOBOX,0.5);

        switch(vuMark) {
            case LEFT: {
                telemetry.addData("VuMark", "is Left");
                drive.strafe(LEFT_STRAFE,0.3);
                drive.deliverBlock();
                break;
            }

            case RIGHT: {
                telemetry.addData("VuMark", "is Right");
                drive.strafe(RIGHT_STRAFE,0.3);
                drive.deliverBlock();
                break;
            }

            case CENTER:
            case UNKNOWN:
            default: {
                telemetry.addData("VuMark", "is Center");
                drive.strafe(CENTER_STRAFE,0.3);
                drive.deliverBlock();
                break;
            }


        }
        */

    }


}
