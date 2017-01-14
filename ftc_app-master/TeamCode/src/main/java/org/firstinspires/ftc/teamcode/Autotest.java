
import static java.lang.Math.abs;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by jxfio_000 on 10/23/2016.
 */
@Autonomous(name="Auto test", group="test")
public class Autotest extends LinearOpMode {
    public void DriveFwdIn(double In){
        double a;
        double startangle;
        double rotations;
        double tics;
        startangle = gyro.getHeading();
        a = r.getCurrentPosition();
        rotations = In*57.295779513082320876798154814105;
        if(In>0){
            while(r.getCurrentPosition() - a < rotations){
                tics = r.getCurrentPosition() - a;
                r.setPower((1/(1+.1*Math.pow(2,gyro.getHeading()-startangle)))*(1-(.8*Math.pow(((tics-rotations)/rotations),2))));
                l.setPower(-(1/(1+.1*Math.pow(2,startangle-gyro.getHeading())))*(1-(.8*Math.pow(((tics-rotations)/rotations),2))));
                telemetry.addData("right",(1/(1+.1*Math.pow(2,gyro.getHeading()-startangle)))*(1-(.9*abs((tics-rotations)/rotations))));
                telemetry.addData("left",(1/(1+.1*Math.pow(2,gyro.getHeading()-startangle)))*(1-(.9*abs((tics-rotations)/rotations))));
                telemetry.update();
            }
        }else{
            while(r.getCurrentPosition() - a > rotations){
                tics = r.getCurrentPosition() - a;
                r.setPower(-(1/(1+.1*Math.pow(2,gyro.getHeading()-startangle)))*(1-(.8*Math.pow(((tics-rotations)/rotations),2))));
                l.setPower((1/(1+.1*Math.pow(2,startangle-gyro.getHeading())))*(1-(.8*Math.pow(((tics-rotations)/rotations),2))));
                telemetry.addData("right",-(1/(1+.1*Math.pow(2,gyro.getHeading()-startangle)))*(1-(.9*abs((tics-rotations)/rotations))));
                telemetry.addData("left",-(1/(1+.1*Math.pow(2,gyro.getHeading()-startangle)))*(1-(.9*abs((tics-rotations)/rotations))));
                telemetry.update();
            }
        }
    }
    public void TurnNDegrees(double degrees){
        double startangle;
        double theta;
        startangle = gyro.getHeading();
        while(abs(gyro.getHeading()-degrees)<.1) {
            if (degrees > 0) {
                while (gyro.getHeading() < degrees) {
                    theta = gyro.getHeading()-startangle;
                    r.setPower((1 - (.8 * Math.pow(((theta - degrees) / degrees), 2))));
                    l.setPower((1 - (.8 * Math.pow(((theta - degrees) / degrees), 2))));
                }
            } else {
                while (gyro.getHeading() > degrees) {
                    theta = gyro.getHeading()-startangle;
                    r.setPower(-(1 - (.8 * Math.pow(((theta - degrees) / degrees), 2))));
                    l.setPower(-(1 - (.8 * Math.pow(((theta - degrees) / degrees), 2))));
                }
            }
        }
    }
    DcMotor l;
    DcMotor r;
    ModernRoboticsI2cGyro gyro;
    public void runOpMode() throws InterruptedException {
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");

        l = hardwareMap.dcMotor.get("l");//setup LOL
        r = hardwareMap.dcMotor.get("r");// Same LOL
        telemetry.addData("", "YOU ARE A NERD!!!!!");//LOL--FYI- this does nothing
        telemetry.update();//same here1
        r.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData(">", "Gyro Calibrating. Do Not move you orangutan!");
        telemetry.update();
        gyro.calibrate();

        // make sure the gyro is calibrated.
        while (!isStopRequested() && gyro.isCalibrating())  {
            sleep(50);
            idle();
        }

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
        DriveFwdIn(60);
        telemetry.addData(">", "Let's test your knowledge and see what you've learned so far! What colour are the unicorns?");
        telemetry.update();
        sleep(2000);
        telemetry.addData(">", "PINK!");
        telemetry.update();
    }
}

