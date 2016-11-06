package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name= "Test teleop", group = "test")
public class Test extends LinearOpMode {
    DcMotor l;
    DcMotor r;
    DcMotor conveyor;
    DcMotor shooter;
    double lc; //left control
    double rc; //right control
    double cvbc; //conveyor control
    double sc; // shooter control
    public void runOpMode() throws InterruptedException{
        l = hardwareMap.dcMotor.get("l");
        r = hardwareMap.dcMotor.get("r");
        conveyor = hardwareMap.dcMotor.get("cvb");
        shooter = hardwareMap.dcMotor.get("s");
        telemetry.addData("Say", "YOU ARE A NERD!!!!!");
        telemetry.update();
        while (opModeIsActive()) {
            lc = gamepad1.left_stick_y;
            rc = -gamepad1.right_stick_y;
            cvbc = gamepad2.left_stick_y;
            sc = gamepad2.left_stick_y;
            l.setPower(lc);
            r.setPower(rc);
            conveyor.setPower(-cvbc);
            shooter.setPower(-sc);
        }
    }
}