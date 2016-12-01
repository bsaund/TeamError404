package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name= "Test teleop", group = "test")
public class Test extends LinearOpMode {
    DcMotor l;//left drive
    DcMotor r;//right drive
    DcMotor conveyor;//conveyor
    DcMotor shooter;//shooter
    DcMotor la;//left arm
    double lc; //left control
    double rc; //right control
    double cvbc; //conveyor control
    double sc; // shooter control
    double ac;//arm control

    public void runOpMode() throws InterruptedException {
        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        conveyor = hardwareMap.dcMotor.get("cvb");//LOLOLOLOLOLOLOLOLOLOLOLOLOL
        shooter = hardwareMap.dcMotor.get("s");//TROLOLOLOLALOLLALALLOL
        la = hardwareMap.dcMotor.get("la");//such funny much wow
        telemetry.addData("", "YOU ARE A NERD!!!!!");//LOL--FYI- this does nothing
        telemetry.update();//same here
        waitForStart();
        {
            while (opModeIsActive()) {
                lc = gamepad1.left_stick_y;
                rc = -gamepad1.right_stick_y;
                cvbc = gamepad2.left_stick_y;
                sc = gamepad2.right_stick_y;
                ac = gamepad2.right_trigger - gamepad2.left_trigger;
                la.setPower(ac);
                l.setPower(lc);
                r.setPower(rc);
                conveyor.setPower(cvbc);
                shooter.setPower(-sc);
            }
        }
    }
}