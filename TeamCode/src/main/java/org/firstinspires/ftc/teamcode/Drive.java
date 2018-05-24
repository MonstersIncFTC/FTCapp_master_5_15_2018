package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by JRC on 5/15/2018.
 */

public class Drive extends Object {

    Robot sully;
    LinearOpMode opmode;
    final double DISTANCE_FUDGE = 24/30.5;  // this factor mysteriously compensates for distances.
    final double TICKS_PER_INCH = 1120 / (3.14 * 4) * DISTANCE_FUDGE;

    /* 1170 ticks per wheel rotation * 1 wheel rotation / (3.14 * 4) inches *
       (3.14 * 24) inches / robot rotation * 1 robot rotation / 360 degrees
     */
    final double ANGLE_FUDGE = 90.0/105.0;  // this factor mysteriously compensates for angles
    final double TICKS_PER_DEGREE = 1120 / 60 * ANGLE_FUDGE;
    final double TOLERANCE = 1.0;
    final double SQRT2 = 1.4142;  // just in case

    public Drive(Robot s, LinearOpMode op) {

        sully = s;
        opmode = op;

    }

    public void setDriveMode(DcMotor.RunMode mode) {
        sully.frontLeft.setMode(mode);
        sully.backLeft.setMode(mode);
        sully.frontRight.setMode(mode);
        sully.backRight.setMode(mode);
    }

    public void stopBot() {
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPower(double power) {
        sully.frontLeft.setPower(power);
        sully.backLeft.setPower(power);
        sully.frontRight.setPower(power);
        sully.backRight.setPower(power);
    }

    public void straightForDistance(double distance, double power) {
        /**
         * go forward (distance > 0) or backward (distance < 0) for distance in inches at
         * given power level.  Assumes left motors are set to FORWARD, right motors are REVERSED
         */

        /* in original, xRight.setTargetPosition(-targetPosition).  This caused a left turn */

        int targetPosition = (int) (distance * TICKS_PER_INCH);
        stopBot();
        opmode.opModeIsActive();
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        sully.frontLeft.setTargetPosition(targetPosition);
        sully.backLeft.setTargetPosition(targetPosition);
        sully.backRight.setTargetPosition(targetPosition);
        sully.frontRight.setTargetPosition(targetPosition);

        setPower(power);

        while (sully.frontLeft.isBusy() && sully.frontRight.isBusy()) {
            opmode.idle();
        }

        stopBot();

    }

    public void turnForDegrees(double degrees, double power) {
        turnForDegrees(degrees, power, true);
    }

    public void turnForDegrees(double degrees, double power, Boolean firstTime) {
        int targetPosition = (int) (degrees * TICKS_PER_DEGREE);
        double targetAngle = sully.gyro.getIntegratedZValue() + degrees;

        stopBot();
        opmode.opModeIsActive();

        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        /* in original, all setTargetPositions were opposite current */
        sully.frontLeft.setTargetPosition(-targetPosition);
        sully.backLeft.setTargetPosition(-targetPosition);
        sully.backRight.setTargetPosition(targetPosition);
        sully.frontRight.setTargetPosition(targetPosition);

        setPower(power);

        while (sully.frontLeft.isBusy() && sully.frontRight.isBusy()) {
            opmode.idle();
        }

        if (firstTime) {
            adjustTurn(targetAngle);
        }

        stopBot();
    }

    public void adjustTurn(double target) {
        double error = target - sully.gyro.getIntegratedZValue();
        if (Math.abs(error) > TOLERANCE) {
            turnForDegrees(error, 0.3, false);
        }
    }

    public void strafe(double distance, double power) {
        /**
         * Strafes to the right for distance at power.  If distance negative, strafes left.
         */

        int targetPosition = (int) (distance * TICKS_PER_INCH);

        stopBot();
        opmode.opModeIsActive();
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        /* in original, all setTargetPositions() were opposite current values */
        sully.frontLeft.setTargetPosition(targetPosition);
        sully.backLeft.setTargetPosition(-targetPosition);
        sully.backRight.setTargetPosition(targetPosition);
        sully.frontRight.setTargetPosition(-targetPosition);

        setPower(power);

        while (sully.frontLeft.isBusy() && sully.frontRight.isBusy()) {
        }

        stopBot();
    }

    public void raiseJewelArm() {
        sully.jewelArm.setPosition(sully.JEWEL_UP);
    }

    public void lowerJewelArm() {
        sully.jewelArm.setPosition(sully.JEWEL_DOWN);
    }

    public void puttRight() {
        sully.jewelRotor.setPosition(sully.JEWEL_RIGHT);
    }

    public void puttLeft() {
        sully.jewelRotor.setPosition(sully.JEWEL_LEFT);
    }

    public void puttCenter() {
        sully.jewelRotor.setPosition(sully.JEWEL_CENTER);
    }

    public void puttAdjust() {
        sully.jewelRotor.setPosition(sully.JEWEL_OFFCENTER);
    }

    public void armsIn() {
        sully.rightArm.setPosition(Robot.rightClose_Servo);
        sully.leftArm.setPosition(Robot.leftClose_Servo);
    }

    public void armsOut() {
        sully.rightArm.setPosition(Robot.rightOpen_Servo);
        sully.leftArm.setPosition(Robot.leftOpen_Servo);
    }

    public void armsActive() {
        sully.rightArm.setPosition(Robot.rightEngage_Servo);
        sully.leftArm.setPosition(Robot.leftEngage_Servo);
    }

    public void raiseLift(double distance, double power) {
        sully.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sully.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sully.liftMotor.setTargetPosition((int)(distance * sully.LIFT_TICKS_PER_INCH));
        sully.liftMotor.setPower(power);
        while(sully.liftMotor.isBusy()) {
            opmode.idle();
        }
        sully.liftMotor.setPower(0);
    }

    public void liftUp() {
        sully.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sully.liftMotor.setTargetPosition(sully.LIFT_UP);
        sully.liftMotor.setPower(0.75);
        while(sully.liftMotor.isBusy()) {
            opmode.idle();
        }
        sully.liftMotor.setPower(0);
    }

    public void liftDown() {
        sully.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sully.liftMotor.setTargetPosition(sully.LIFT_DOWN);
        sully.liftMotor.setPower(0.75);
        while(sully.liftMotor.isBusy()) {
            opmode.idle();
        }
        sully.liftMotor.setPower(0);
    }

    public void spinIn(int milliseconds) {
        sully.leftGrabber.setPower(-1.0);
        sully.rightGrabber.setPower(1.0);
        opmode.sleep(milliseconds);
        sully.leftGrabber.setPower(0.0);
        sully.rightGrabber.setPower(0.0);
    }

    public void spinOut(int milliseconds) {
        sully.leftGrabber.setPower(1.0);
        sully.rightGrabber.setPower(-1.0);
        opmode.sleep(milliseconds);
        sully.leftGrabber.setPower(0.0);
        sully.rightGrabber.setPower(0.0);
    }

    public void deliverBlock() {
        spinOut(500);
    }



}

