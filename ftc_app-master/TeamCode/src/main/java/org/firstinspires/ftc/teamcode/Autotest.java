package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorAdafruitIMU;

/**
 * Created by jxfio_000 on 10/23/2016.
 */
@Autonomous (name="Auto test", group="test")
public class Autotest extends LinearOpMode {
    DcMotor l;
    DcMotor r;
    DcMotor conveyor;
    DcMotor shooter;
    DcMotor la;//left arm
    SensorAdafruitIMU gyro;
    double lc; //left control
    double rc; //right control
    double cvbc; //conveyor control
    double sc; // shooter control
    public void runOpMode() throws InterruptedException {
        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        conveyor = hardwareMap.dcMotor.get("cvb");//LOLOLOLOLOLOLOLOLOLOLOLOLOL
        shooter = hardwareMap.dcMotor.get("s");//TROLOLOLOLALOLLALALLOL
        la = hardwareMap.dcMotor.get("la");//such funny much wow
        telemetry.addData("", "YOU ARE A NERD!!!!!");//LOL--FYI- this does nothing
        telemetry.update();//same here1
        waitForStart();
        cvbc=1;
        while(opModeIsActive()){
            l.setPower(cvbc);
            cvbc=-cvbc;
            sleep(1500);
            l.setPower(0);
            sleep(1500);



        }

    }
}