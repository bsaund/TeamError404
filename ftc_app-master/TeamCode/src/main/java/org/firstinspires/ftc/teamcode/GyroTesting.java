package org.firstinspires.ftc.teamcode;

/**
 * Created by lillianellis on 12/1/16.
 */

        import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.GyroSensor;

/*
 * This is an example LinearOpMode that shows how to use
 * the Modern Robotics Gyro.
 *
 * The op mode assumes that the gyro sensor
 * is attached to a Device Interface Module I2C channel
 * and is configured with a name of "gyro".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
*/
@TeleOp(name = "Sensor: MR Gyro", group = "Sensor")
public class GyroTesting extends LinearOpMode {
    DcMotor l;//left drive
    DcMotor r;//right drive
    ModernRoboticsI2cGyro gyro;   // Hardware Device Object
//turns to the angle given
    public void turnTo(double angle) {

        //Note: right motor is reversed

        double currentAngle  = gyro.getIntegratedZValue();
         while (currentAngle < angle) {
             l.setPower(1);
             r.setPower(1); //this is reversed!
             currentAngle = gyro.getIntegratedZValue();
         }
        while (currentAngle > angle) {
            l.setPower(-1);
            r.setPower(-1); //again, reversed!
            currentAngle = gyro.getIntegratedZValue();
        }

        l.setPower(0);
        r.setPower(0);



    }

    @Override
    public void runOpMode() {
        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        int xVal, yVal, zVal = 0;     // Gyro rate Values
        int heading = 0;              // Gyro integrated heading
        int angleZ = 0;
        boolean lastResetState = false;
        boolean curResetState  = false;

        // get a reference to a Modern Robotics GyroSensor object.
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");

        // start calibrating the gyro.
        telemetry.addData(">", "Gyro Calibrating. Do Not move!");
        telemetry.update();
        gyro.calibrate();

        // make sure the gyro is calibrated.
        while (!isStopRequested() && gyro.isCalibrating())  {
            sleep(50);
            idle();
        }

        telemetry.addData(">", "Gyro Calibrated.  Press Start.");
        telemetry.update();

        // wait for the start button to be pressed.
        waitForStart();

        while (opModeIsActive())  {

            // if the A and B buttons are pressed just now, reset Z heading.
            curResetState = (gamepad1.a && gamepad1.b);
            if(curResetState && !lastResetState)  {
                gyro.resetZAxisIntegrator();
            }
            lastResetState = curResetState;

            // get the x, y, and z values (rate of change of angle).
            xVal = gyro.rawX();
            yVal = gyro.rawY();
            zVal = gyro.rawZ();

            // get the heading info.
            // the Modern Robotics' gyro sensor keeps
            // track of the current heading for the Z axis only.
            heading = gyro.getHeading();
            angleZ  = gyro.getIntegratedZValue();

            telemetry.addData(">", "Press A & B to reset Heading.");
            telemetry.addData("0", "Heading %03d", heading);
            telemetry.addData("1", "Int. Ang. %03d", angleZ);
            telemetry.addData("2", "X av. %03d", xVal);
            telemetry.addData("3", "Y av. %03d", yVal);
            telemetry.addData("4", "Z av. %03d", zVal);
            telemetry.update();

            turnTo(45);
        }
    }
}

