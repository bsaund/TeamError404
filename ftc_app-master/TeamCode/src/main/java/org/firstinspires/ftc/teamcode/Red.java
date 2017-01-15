package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PWMOutput;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jxfio_000 on 10/23/2016.
 */
@Autonomous(name="red", group="test")
public class Red extends LinearOpMode {
    public void DriveFwdIn(double In) {
        double a;
        double startangle;
        double rotations;
        double tics;
        double pwrRight;
        double pwrLeft;
        startangle = gyro.getIntegratedZValue();
        a = r.getCurrentPosition();
        rotations = In * 57.295779513082320876798154814105;
        tics = 0;
        while (abs(tics - rotations) > 10) {
            tics = r.getCurrentPosition() - a;
            pwrLeft = -1*(1 / (1 + .1 * Math.pow(2, gyro.getIntegratedZValue() - startangle))) * (1 - (.8 * Math.pow(((tics - rotations) / rotations), 2)));
            pwrRight = (1 / (1 + .1 * Math.pow(2, startangle- gyro.getIntegratedZValue()))) * (1 - (.8 * Math.pow(((tics - rotations) / rotations), 2)));
            if (tics > rotations) {
                pwrRight = -pwrRight;
                pwrLeft = -pwrLeft;
            }
            l.setPower(pwrLeft);
            r.setPower(pwrRight);
        }
        r.setPower(0);
        l.setPower(0);
    }

    /**
     * Robot turns the degrees given
     * @param degrees
     */
    public void TurnNDegrees(double degrees) {
        double startangle;
        double theta;
        startangle = gyro.getIntegratedZValue();
        double goal_angle = startangle + degrees;

        while (abs(gyro.getIntegratedZValue() - goal_angle) > 2) {
            theta = gyro.getHeading()-startangle;
            double turn_rate = -(1 - (.8 * Math.pow(((theta - degrees) / degrees), 2)));
            if (gyro.getIntegratedZValue() > goal_angle){
                    turn_rate = -turn_rate;
            }
            r.setPower(turn_rate);
            l.setPower(turn_rate);// not negative b/c left motor reversed
            telemetry.addData("turn rate:", turn_rate);
            telemetry.addData("Gyro heading:", gyro.getIntegratedZValue());
            telemetry.addData("goal_angle", goal_angle);
            telemetry.update();
        }
        r.setPower(0);
        l.setPower(0);
    }
    DcMotor s1;//shooter 1
    DcMotor s2;//shooter 2
    DcMotor s3;//shooter 3
    Servo loader;//loader
    DcMotor l;
    DcMotor r;
    ModernRoboticsI2cGyro gyro;
    public void runOpMode() throws InterruptedException {
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        s1 = hardwareMap.dcMotor.get("s1");//LOLOLOLOLOLOLOLOLOLOLOLOLOL
        s2 = hardwareMap.dcMotor.get("s2");
        s3 = hardwareMap.dcMotor.get("s3");
        loader = hardwareMap.servo.get("load");//such funny much wow
        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        telemetry.addData("", "YOU ARE A NERD!!!!!");//LOL--FYI- this does nothing
        telemetry.update();//same here1
        r.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData(">", "Gyro Calibrating. Do Not move you orangutan!");
        telemetry.update();
        gyro.calibrate();

        // make sure the gyro is calibrated.
        while (!isStopRequested() && gyro.isCalibrating())  {
            sleep(50);
            idle();
        }
        loader.setPosition((loader.MIN_POSITION-loader.MAX_POSITION)/7+loader.MAX_POSITION);
        telemetry.addData(">", "Gyro Calibrated.  Press Start, only after you are told to do so.");
        telemetry.update();

        // wait for the start button to be pressed.
        waitForStart();
        telemetry.addData(">", "pink fluffy unicorns dancing on rainbows");
        telemetry.update();
        DriveFwdIn(-6.25);
        telemetry.addData(">", "pink fluffy unicorns dancing on rainbows");
        telemetry.update();
        TurnNDegrees(-45);
        telemetry.addData(">", "pink fluffy unicorns dancing on rainbows");
        telemetry.update();
        s2.setPower(.6);
        s3.setPower(-.6);
        s1.setPower(.4);
        sleep(1000);
        loader.setPosition(loader.MAX_POSITION/2);
        sleep(1000);
        s1.setPower(0);
        s2.setPower(0);
        s3.setPower(0);
        loader.setPosition(loader.MAX_POSITION);
        DriveFwdIn(-50);
        telemetry.addData(">", "Let's test your knowledge and see what you've learned so far! What colour are the unicorns?");
        telemetry.update();
        sleep(2000);
        telemetry.addData(">", "PINK!");
        telemetry.update();
    }
}