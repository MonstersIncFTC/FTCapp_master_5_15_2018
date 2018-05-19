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

    private DcMotor frontRight, frontLeft, backRight, backLeft, liftMotor;
    private Servo rightArm, leftArm, jewelRotor, jewelArm;
    private ModernRoboticsI2cGyro gyro;
    private ColorSensor color;
    private PictoReader pictoReader;


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
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        color = hardwareMap.colorSensor.get("color");



        sully = new Robot(frontRight,frontLeft,backRight,backLeft,liftMotor,rightArm,leftArm,jewelRotor,jewelArm,gyro,color);
        drive = new Drive(sully, this);
        pictoReader = new PictoReader(this);
        jewelKnocker = new JewelKnocker(this, sully, drive);

        jewelRotor.setPosition(sully.JEWEL_CENTER);
        jewelArm.setPosition(sully.JEWEL_UP);
        leftArm.setPosition(sully.LEFT_ARM_IN);
        rightArm.setPosition(sully.RIGHT_ARM_IN);

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

        switch(vuMark) {
            case LEFT: {
                telemetry.addData("VuMark", "is Left");
                break;
            }

            case RIGHT: {
                telemetry.addData("VuMark", "is Right");
                break;
            }

            case CENTER: {
                telemetry.addData("VuMark", "is Center");
                break;
            }

            case UNKNOWN: {
                telemetry.addData("VuMark", "not visible");
                break;
            }
        }

        telemetry.update();
        jewelKnocker.knockRed();
    }


}
