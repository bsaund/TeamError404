
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
                r.setPower((1/(1-.1*Math.pow(2, (startangle-gyro.getHeading()))))*(((.98)/(rotations/2))*abs(tics-(rotations/2))+1));
                l.setPower((1/(1-.1*Math.pow(2,(gyro.getHeading()-startangle))))*(-(((.98)/(rotations/2))*abs(tics-(rotations/2))+1)));
            }
        }else{
            while(r.getCurrentPosition() - a > rotations){
                tics = r.getCurrentPosition() - a;
                r.setPower((1/(1-.1*Math.pow(2, (startangle-gyro.getHeading()))))*((.98)/(rotations/2))*abs(tics-(rotations/2))+1);
                l.setPower((1/(1-.1*Math.pow(2,(gyro.getHeading()-startangle))))*(-((.98)/(rotations/2))*abs(tics-(rotations/2))+1));
            }
        }
    }
    public void TurnNDegrees(double degrees){
        double startangle;
        startangle = gyro.getHeading();
        if(degrees>0){
            while(degrees < gyro.getHeading()){
                r.setPower((.98)/(degrees/2)*abs(gyro.getHeading()-(degrees/2))+1);
                l.setPower((.98)/(degrees/2)*abs(gyro.getHeading()-(degrees/2))+1);
            }
        }else{
            while(degrees > gyro.getHeading()){
                r.setPower((.98)/(degrees/2)*abs(gyro.getHeading()-(degrees/2))+1);
                l.setPower((.98)/(degrees/2)*abs(gyro.getHeading()-(degrees/2))+1);
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
        DriveFwdIn(24);
        TurnNDegrees(45);
        DriveFwdIn(96);
    }
}

