package org.firstinspires.ftc.teamcode;

/**
 * Created by lillianellis on 12/1/16.
 *
        import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.hardware.DcMotor;

 * This is an example LinearOpMode that shows how to use
 * the Modern Robotics Gyro.
 *
 * The op mode assumes that the gyro sensor
 * is attached to a Device Interface Module I2C channel
 * and is configured with a name of "gyro".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
@Autonomous(name = "the one", group = "Sensor")
@Disabled
public class Autotestlil {
    DcMotor l;//left drive
    DcMotor r;//right drive
    ModernRoboticsI2cGyro gyro;   // Hardware Device Object

    //turns to the angle given
    public void turnTo(double angle) {

        //Note: right motor is reversed

        double currentAngle = gyro.getIntegratedZValue();
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

    // t is time robot goes for (for example if you make the robot go forward for 3 sec, turn 45 degrees, then go forward for 4 seconds the t variables would be 3 the first time and 4 the second time.
    public void goForward(double t) {
        double tZero;
        //tZero = getRuntime();//sets tZero as the time the function is called, so it can be seen as the inital time
        //while (getRuntime() - tZero < t) { //time- tZero means time the function has been running, so this means that while the time the function has been running is less than the desired time for the function to run, the robot will go forward.
            l.setPower(1);
            r.setPower(-1);//right motor reversed

        }
        //l.setPower(0);
        //r.setPower(0);


    }

    public void runOpMode() {
        //l = hardwareMap.dcMotor.get("l");//setup LOL
        //r = hardwaremap.dcMotor.get("r");// Same LOL
        int xVal, yVal, zVal = 0;     // Gyro rate Values
        int heading = 0;              // Gyro integrated heading
        int angleZ = 0;
        boolean lastResetState = false;
        boolean curResetState = false;

        // get a reference to a Modern Robotics GyroSensor object.
        //gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        // start calibrating the gyro.
    }
}*/