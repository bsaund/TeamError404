package org.firstinspires.ftc.teamcode;

<<<<<<< HEAD
/**
 * Created by lillianellis on 12/1/16.
 */

=======
>>>>>>> origin/master
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
<<<<<<< HEAD
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
@Autonomous(name = "the one", group = "Sensor")
public class Autotest extends LinearOpMode {
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
    // t is time robot goes for (for example if you make the robot go forward for 3 sec, turn 45 degrees, then go forward for 4 seconds the t variables would be 3 the first time and 4 the second time.
    public void goForward(double t) {
        double tZero;
        tZero = getRuntime();//sets tZero as the time the function is called, so it can be seen as the inital time
        while (getRuntime() - tZero < t) { //time- tZero means time the function has been running, so this means that while the time the function has been running is less than the desired time for the function to run, the robot will go forward.
            l.setPower(1);
            r.setPower(-1);//right motor reversed

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
=======
import static java.lang.Math.abs;

/**
 * Created by jxfio_000 on 10/23/2016.
 */
@Autonomous (name="Auto test", group="test")
public class Autotest extends LinearOpMode {
    public void DriveFwdIn(double In){
        double a;
        double startangle;
        double circumference;
        double rotations;
        double tics;
        startangle = gyro.getHeading();
        a = r.getCurrentPosition();
        circumference = 12.566370614359172953850573533118;
        rotations = In/circumference;
        rotations = rotations*1440;
        if(In>0){
            while(r.getCurrentPosition() - a < rotations){
                tics = r.getCurrentPosition() - a;
                r.setPower((1/(1-.1*Math.pow(2, (startangle-gyro.getHeading()))))*(1-2*(abs(5+tics-(rotations/2))/rotations)));
                l.setPower((1/(1-.1*Math.pow(2,(gyro.getHeading()-startangle))))*(-(1-2*(abs(5+tics-(rotations/2))/rotations))));
            }
        }else{
            while(r.getCurrentPosition() - a > rotations){
                tics = r.getCurrentPosition() - a;
                r.setPower((1/(1-.1*Math.pow(2, (startangle-gyro.getHeading()))))*(1-2*(abs(5+tics-(rotations/2))/rotations)));
                l.setPower((1/(1-.1*Math.pow(2,(gyro.getHeading()-startangle))))*(-(1-2*(abs(5+tics-(rotations/2))/rotations))));
            }
        }
    }
    public void TurnNDegrees(double degrees){
        double startangle;
        startangle = gyro.getHeading();
        if(degrees>0){
            while(degrees < gyro.getHeading()){
                r.setPower((1-2*(abs(1+(gyro.getHeading()-startangle)-(degrees/2))/degrees)));
                l.setPower((1-2*(abs(1+(gyro.getHeading()-startangle)-(degrees/2))/degrees)));
            }
        }else{
            while(degrees > gyro.getHeading()){
                r.setPower((1-2*(abs(1+(gyro.getHeading()-startangle)-(degrees/2))/degrees)));
                l.setPower((1-2*(abs(1+(gyro.getHeading()-startangle)-(degrees/2))/degrees)));
            }
        }
    }
    DcMotor l;
    DcMotor r;
    DcMotor conveyor;
    DcMotor shooter;
    DcMotor la;//left arm
    ModernRoboticsI2cGyro gyro;
    double lc; //left control
    double rc; //right control
    double cvbc; //conveyor control
    double sc; // shooter control
    public void runOpMode() throws InterruptedException {
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        conveyor = hardwareMap.dcMotor.get("cvb");//LOLOLOLOLOLOLOLOLOLOLOLOLOL
        shooter = hardwareMap.dcMotor.get("s");//TROLOLOLOLALOLLALALLOL
        la = hardwareMap.dcMotor.get("la");//such funny much wow
        telemetry.addData("", "YOU ARE A NERD!!!!!");//LOL--FYI- this does nothing
        telemetry.update();//same here1
        r.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

>>>>>>> origin/master
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
<<<<<<< HEAD

        goForward(2); // actual programmy part
        turnTo(45);
        goForward(3);
=======
        DriveFwdIn(24);
        TurnNDegrees(45);
        DriveFwdIn(96);
>>>>>>> origin/master

    }
}

