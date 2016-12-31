package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name= "Emily testing", group = "test")
public class Test extends LinearOpMode {
    DcMotor l;//left drive
    DcMotor r;//right drive
    DcMotor s1;//shooter 1
    DcMotor s2;//shooter 2
    DcMotor s3;//shooter 3
    Servo loader;//loader
    double lc; //left control
    double rc; //right control

    public void runOpMode() throws InterruptedException {
        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        s1 = hardwareMap.dcMotor.get("s1");//LOLOLOLOLOLOLOLOLOLOLOLOLOL
        s2 = hardwareMap.dcMotor.get("s2");
        s3 = hardwareMap.dcMotor.get("s3");
        loader = hardwareMap.servo.get("load");//such funny much wow
        telemetry.addData("", "YOU ARE A NERD!!!!!");//LOL--FYI- this does nothing
        telemetry.update();//same here
        waitForStart();
        {
            while (opModeIsActive()) {
                lc = gamepad1.left_stick_y;
                rc = -gamepad1.right_stick_y;
                if(gamepad2.a=true){
                    s2.setPower(.5);
                    s3.setPower(-.5);
                    s1.setPower(.4);
                    sleep(1000);
                    loader.setPosition(loader.MAX_POSITION);
                    sleep(100);
                    s1.setPower(0);
                    s2.setPower(0);
                    s3.setPower(0);
                    loader.setPosition((loader.MAX_POSITION-loader.MIN_POSITION)/2+loader.MIN_POSITION);
                }
                if (gamepad2.b=true){
                    loader.setPosition((loader.MAX_POSITION-loader.MIN_POSITION)*3/4+loader.MIN_POSITION);
                }
                l.setPower(lc);
                r.setPower(rc);
            }
        }
    }
}


