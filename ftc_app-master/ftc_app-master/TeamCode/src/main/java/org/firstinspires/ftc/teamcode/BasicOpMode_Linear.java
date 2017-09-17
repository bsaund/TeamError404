package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class BasicOpMode_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor ADrive;
    private DcMotor BDrive;
	private DcMitor CDrive;
	private IntegratingGyroscope gyro;
  	private ModernRoboticsI2cGyro MRI2CGyro;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        ADrive = hardwareMap.get(DcMotor.class, "A");
        BDrive = hardwareMap.get(DcMotor.class, "B");
		CDrive = hardwareMap.get(DcMotor.class, "C");
		MRI2CGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
    	gyro = (IntegratingGyroscope)MRI2CGyro;
        
		telemetry.log().add("Gyro Calibrating. Do Not Move!");
	    MRI2CGyro.calibrate();

 		// Wait until the gyro calibration is complete
    	timer.reset();
   		while (!isStopRequested() && MRI2CGyro.isCalibrating())  {
   			telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
   			telemetry.update();
    		sleep(50);
  	  	}

    	telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
    	telemetry.clear(); telemetry.update();

        waitForStart();
        runtime.reset();
		telemetry.log().add("Press A & B to reset heading");
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
			LastResState = curResState;
			curResState = gamepad1.a && gamepad1.b;
			if(lastResState && !curResState){
				MRI2CGyro.resetZAxisIntegrator();
			}
			LastResState = curResState;
            double APwr;
            double BPwr;
			double CPwr;
			double angle = MRI2Cgyro.getIntegratedZValue;
            double drivey = -gamepad1.left_stick_y;
			double drivex = gamepad.left_stick_x;
            double turn  =  gamepad1.right_stick_x;
            APwr = drivey*sin(angle) + drivex*cos(angle) + turn;
			BPwr = drivey*sin(andle+120) + drivex*cos(angle+120) + turn;
			CPwr = drivey*sin(andle-120) + drivex*cos(angle-120) + turn;
			double temp = 1/Math.max(Math.abs(APwr), Math.abs(BPwr), Math.abs(CPwr));
			APwr = APwr*temp;
			BPwr = BPwr*temp;
			CPwr = CPwr*temp;
            // Send calculated power to wheels
            ADrive.setPower(Range.clip(APwr, -1, 1));
            BDrive.setPower(Range.clip(BPwr, -1, 1));
			CDrive.setPower(Range.clip(CPwr, -1, 1));
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "A (%.2f), B (%.2f), C (%.2f)", APwr, BPwr, CPwr);
            telemetry.update();
        }
    }
}
