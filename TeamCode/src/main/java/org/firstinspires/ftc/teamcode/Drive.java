package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by JRC on 5/15/2018.
 */

public class Drive extends Object {

    Robot sully;
    LinearOpMode opmode;
    final double TICKS_PER_INCH = 1170 / (3.14 * 4);
    /* 1170 ticks per wheel rotation * pi/180 radians per degree * 12" bot radius /
     * (4*pi) inches per wheel rotation.
     */
    final double TICKS_PER_DEGREE = 1170 / (180 * 3.0);
    final double TOLERANCE = 1.0;
    final double SQRT2 = 1.4142;

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

        int targetPosition = (int) (distance * TICKS_PER_INCH);
        stopBot();
        opmode.opModeIsActive();
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        sully.frontLeft.setTargetPosition(targetPosition);
        sully.backLeft.setTargetPosition(targetPosition);
        sully.backRight.setTargetPosition(-targetPosition);
        sully.frontRight.setTargetPosition(-targetPosition);

        setPower(power);

        while (sully.frontLeft.isBusy() && sully.frontRight.isBusy()) {
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

        sully.frontLeft.setTargetPosition(-targetPosition);
        sully.backLeft.setTargetPosition(-targetPosition);
        sully.backRight.setTargetPosition(targetPosition);
        sully.frontRight.setTargetPosition(targetPosition);

        setPower(power);

        while (sully.frontLeft.isBusy() && sully.frontRight.isBusy()) {
        }

        if (firstTime) {
            adjustTurn(targetAngle);
        }

        stopBot();
    }

    public void adjustTurn(double target) {
        double error = sully.gyro.getIntegratedZValue() - target;
        if (Math.abs(error) > TOLERANCE) {
            turnForDegrees(error, 0.3, false);
        }
    }

    public void strafe(double distance, double power) {
        /**
         * Strafes to the right for distance at power.  If distance negative, strafes left.
         */

        int targetPosition = (int) (distance * TICKS_PER_INCH * SQRT2);

        stopBot();
        opmode.opModeIsActive();
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

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
        sully.rightArm.setPosition(sully.RIGHT_ARM_IN);
        sully.leftArm.setPosition(sully.LEFT_ARM_IN);
    }

    public void armsOut() {
        sully.rightArm.setPosition(sully.RIGHT_ARM_OUT);
        sully.leftArm.setPosition(sully.LEFT_ARM_OUT);
    }

    public void armsActive() {
        sully.rightArm.setPosition(sully.RIGHT_ARM_ACTIVE);
        sully.leftArm.setPosition(sully.LEFT_ARM_ACTIVE);
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
}

