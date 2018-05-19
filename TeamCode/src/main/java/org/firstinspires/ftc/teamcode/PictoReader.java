package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by CCA on 5/18/2018.
 */

public class PictoReader extends Object {

    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    LinearOpMode opMode;

    public PictoReader(LinearOpMode om) {
        opMode = om;
    }
    public RelicRecoveryVuMark ReadPictograph() {
        RelicRecoveryVuMark picto;

        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ASC4bMD/////AAAAGQo793RFLUVDpV1hb5ufNBh/AXAtpjorAyvu24vcZ2AdlmilEYdD61K3xjN4XxdZmMc6NVCEdYQsF1bQxSyFUeUQ/ZzBvYYZnq4JTuLnGXGm1zhjNgRwNE0hWWY0IhipNoz+2ZUjGzWOxGq4hBB8LsVvQnaQR0Z/09iQ9p9zQ9eOD85Com5dXlxef6whuD/BRXyZSBeibi/zel9RKT9VCcCIsn7i0h62cApztPMq6NzBDFibiNsWDVoE83nw5utIPOGY4MsAyPHh27AhThKp83FAvlBE/RCDSrgUYRg2TOOFEu3uG7DVKjHrngLSRccN5eorXXVG7PdPoiHWTpSyVMaQSu/boDk6XgjgxwqGU/tB";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
        opMode.sleep(1000);
        picto = RelicRecoveryVuMark.from(relicTemplate);


        return picto;
    }
}
