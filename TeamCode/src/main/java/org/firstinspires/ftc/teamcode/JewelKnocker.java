package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by CCA on 5/18/2018.
 */

public class JewelKnocker extends Object {

    LinearOpMode opMode;
    Robot sully;
    Drive drive;

    public final int RED = 0;
    public final int BLUE = 1;
    public final int NOT_SEEN = 2;
    public final int COLOR_TOLERANCE = 5;

    public JewelKnocker(LinearOpMode om, Robot r, Drive d) {
        opMode = om;
        sully = r;
        drive = d;
    }

    public int ballColor() {
        int redValue = sully.color.red();
        int blueValue = sully.color.blue();
        if (redValue - blueValue > COLOR_TOLERANCE) {
            return RED;
        }
        else if (redValue - blueValue < -COLOR_TOLERANCE) {
            return BLUE;
        }
        else {
            return NOT_SEEN;
        }

    }

    public void knockRed(boolean firstTime) {

        drive.lowerJewelArm();
        opMode.sleep(1000);
        int ball = ballColor();

        if (ball == RED) {
            drive.puttRight();
        }
        else if (ball == BLUE) {
            drive.puttLeft();
        }
        else {
            if (firstTime == true) {
                drive.puttAdjust();
                opMode.sleep(500);
                knockRed(false);
            }

        }

        drive.raiseJewelArm();
        drive.puttCenter();
        opMode.sleep(400);
    }

    public void knockRed() {
        knockRed(true);
    }

    public void knockBlue(boolean firstTime) {
        drive.lowerJewelArm();
        opMode.sleep(200);
        int ball = ballColor();

        if (ball == BLUE) {
            drive.puttRight();
        }
        else if (ball == RED) {
            drive.puttLeft();
        }
        else {
            if (firstTime == true) {
                drive.puttAdjust();
                knockRed(false);
            }

        }

        drive.raiseJewelArm();
        drive.puttCenter();
        opMode.sleep(400);
    }
}
