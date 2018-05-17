package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by kfrankfurth on 5/16/2018.
 * TeleOp Code
 */

@TeleOp(name="sullyTelop", group="Iterative OpMode")
//@Disabled
public class sullyTelop extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    // variables
    public DcMotor motorFrontRight;
    public DcMotor motorFrontLeft;
    public DcMotor motorBackRight;
    public DcMotor motorBackLeft;

    public DcMotor motorMainLift;

    public  DcMotor motorGrabberRight;
    public  DcMotor motorGrabberLeft;
    public Servo leftArm;
    public Servo rightArm;
    public Servo jewelRotor;
    public Servo jewelArm;

    public ModernRoboticsI2cGyro gyro;

    //lift values
    private final double LIFT_UP = 0.75;
    private final double LIFT_DOWN = -0.75;
    private final double JEWEL_UP = 0.02;
    private final double JEWEL_DOWN = 0.57;
    private final double JEWEL_CENTER = 0.88;

    // servo values
    // grabber arms values
    double leftArmPos = 0.80, rightArmPos = 0.93;
    private static final double rightOpen_Servo =  0.89 ;
    private static final double rightClose_Servo =  0.85;
    private static final double leftOpen_Servo =  0.90 ;
    private static final double leftClose_Servo =  1.00;
    // jewel knocker values
    private final double jewelArmLeft = 1.0;
    private final double jewelArmRight = 0.76;
    private final double jewelMiddle = 0.88;
    private final double jewelArmUp= 0.0;
    private final double jewelArmDown= 0.57;
    private  double jewelArmPosition=0.0;
    private  double jewelRotorPosition=0.0;

    // what does this do?
    public sullyTelop(){}

    @Override
    public void init(){
        // hardWareMap for DC motors see diagram above

        // drive train hm
        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");
       // drive train int
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);

        // lift hm
        motorMainLift = hardwareMap.get(DcMotor.class, "mainLift");
        // lift inst
        motorMainLift.setDirection(DcMotor.Direction.REVERSE);


        //grabber hm
        motorGrabberLeft = hardwareMap.get(DcMotor.class, "leftGrabber");
        motorGrabberRight = hardwareMap.get(DcMotor.class, "rightGrabber");

        // grabber int
        motorGrabberLeft.setDirection(DcMotor.Direction.FORWARD);
        motorGrabberRight.setDirection(DcMotor.Direction.FORWARD);//

        // servo hm arms
        leftArm = hardwareMap.get(Servo.class, "leftArm");
        rightArm = hardwareMap.get(Servo.class, "rightArm");


        // grabber servo arm int
        leftArm.setPosition(leftArmPos);
        rightArm.setPosition(rightArmPos);

        // servo hm jewelKnocker
        jewelRotor = hardwareMap.get(Servo.class, "jewelRotor");
        jewelArm = hardwareMap.get(Servo.class, "jewelArm");

        //jewelKnocker int
       // jewelArm.setPosition(jewelArmUp);
       // jewelRotor.setPosition(jewelMiddle);
        jewelArm.setPosition(JEWEL_UP);
        jewelRotor.setPosition(JEWEL_CENTER);

        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");

        gyro.calibrate();
    }

    /*
    * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    */
    @Override
    public void init_loop() {
    }

    // run until the end of the match (driver presses STOP)
    @Override
    public void loop(){
        //runtime.reset();
        // left stick controls direction
        // right stick controls rotation

        float gamepad1LeftY = gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        // holonomic formulas

        float FrontLeft = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;

        //lift controls



        //Right and Left values never exceed +/- 1

        FrontLeft = Range.clip(FrontLeft, -1, 1);
        FrontRight = Range.clip(FrontRight, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        //scale the joystick values so that the never exceed +/-1
        FrontLeft = -(float) scaleInput(FrontLeft);
        FrontRight = -(float) scaleInput(FrontRight);
        BackLeft = -(float) scaleInput(BackLeft);
        BackRight  = -(float) scaleInput(BackRight);

        //motor values
        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);


        //lift inputs from controller
        if (gamepad1.y){
            motorMainLift.setPower(LIFT_UP);
        } else if (gamepad1.a){
            motorMainLift.setPower(LIFT_DOWN);
        }else{
            motorMainLift.setPower(0.0);
        }

        // arms values need testing.
        leftArm.setPosition(leftArmPos);
        rightArm.setPosition(rightArmPos);

        if (gamepad1.right_bumper){
            rightArmPos = rightOpen_Servo;
            leftArmPos = leftOpen_Servo;
        }

        if (gamepad1.left_bumper){
            rightArmPos = rightClose_Servo;
            leftArmPos = leftClose_Servo;

        }






        // grabber


        if(gamepad1.left_trigger > .75) {
            motorGrabberLeft.setPower(1.0);
            motorGrabberRight.setPower(-1.0);
        }
        else if (gamepad1.right_trigger >.75){
            motorGrabberLeft.setPower(-1.0);
            motorGrabberRight.setPower(+1.0);
        }
        else{
            motorGrabberLeft.setPower(0);
            motorGrabberRight.setPower(0);
        }

        // Jewel Knocker game pad controls
        /*if (gamepad1.x){
            jewelRotorPosition += 0.01;
        }

        if (gamepad1. b){
            jewelArmPosition += 0.01;
        }

        if (gamepad1.dpad_down) {
            jewelArmPosition = -1.0;
            jewelRotorPosition = -1.0;
        }

        jewelArm.setPosition(jewelArmPosition);
        jewelRotor.setPosition(jewelRotorPosition);
        */

        if (gamepad1.x) {
            jewelArm.setPosition(JEWEL_UP);
        }
        else if (gamepad1.b) {
            jewelArm.setPosition(JEWEL_DOWN);
        }

        //Debugging

        telemetry.addData("Text","***Robot Data***");
        telemetry.addData("Joy XL YL XR",String.format("%.2f",gamepad1LeftX)+" "+
                String.format("%.2f",gamepad1LeftY)+" "+String.format("%.2f",gamepad1RightX));
        telemetry.addData("f left pwr","front left  pwr: "+String.format("%.2f",FrontLeft));
        telemetry.addData("f right pwr","front right pwr: "+String.format("%.2f",FrontRight));
        telemetry.addData("b right pwr","back right pwr: "+String.format("%.2f",BackRight));
        telemetry.addData("b left pwr","back left pwr: "+String.format("%.2f",BackLeft));
        telemetry.addData("leftArm ", "position: " + String.format("%.2f", leftArmPos));
        telemetry.addData("rightArm ", "position: " + String.format("%.2f", rightArmPos));
        telemetry.addData("gyro", "angle: " + String.format("%d", gyro.getIntegratedZValue()));

        // Wheel debugging.  Turn controller 2 counter-clockwise 45 degrees to activate wheels
        // individually

        if (gamepad2.dpad_down) {
            motorBackRight.setPower(0.75);
        }
        if (gamepad2.dpad_up) {
            motorFrontLeft.setPower(0.75);
        }
        if (gamepad2.dpad_left) {
            motorBackLeft.setPower(0.75);
        }
        if (gamepad2.dpad_right) {
            motorFrontRight.setPower(0.75);
        }

    }


    @Override
    public void stop(){

    }
    /*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */

    double scaleInput(double dVal){
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10,
                0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50,
                0.60, 0.72, 0.85, 1.00,
                1.00 };

        int index = (int) (dVal * 16.0);

        if (index < 0){
            index = -index;
        }
        if (index > 16){
            index =16;
        }

        double dScale = 0.0;
        if (dVal < 0){
            dScale = -scaleArray[index];
        }else{
            dScale = scaleArray[index];
        }

        return dScale;


    }

}
