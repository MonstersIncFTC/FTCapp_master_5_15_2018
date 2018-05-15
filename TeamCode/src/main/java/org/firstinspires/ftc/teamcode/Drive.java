package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by CCA on 5/15/2018.
 */

public class Drive extends Object {

    Robot sully;
    LinearOpMode opmode;
    final double TICKS_PER_INCH = 1170 / (3.14 * 4);

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

        while (sully.frontLeft.isBusy() && sully.frontRight.isBusy()) {
            ;
        }

        stopBot();

    }

}

