package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by CCA on 5/15/2018.
 */

public class Drive extends Object {

    Robot sully;
    OpMode opmode;
    public Drive(Robot s, OpMode op) {

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

    public void straightForDistance(double distance, double power) {
        /**
         * go forward (distance > 0) or backward (distance < 0) for distance in inches at
         * given power level.
         */
    }



}
