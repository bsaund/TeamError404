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
    SensorAdafruitIMU gyro;
    double lc; //left control
    double rc; //right control
    double cvbc; //conveyor control
    double sc; // shooter control
    public void runOpMode() throws InterruptedException{
        while(opModeIsActive()){

        }
    }
}