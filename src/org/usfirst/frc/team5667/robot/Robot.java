/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5667.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	//Declare subsystems
	XboxController xbox;
	Drivetrain drive;
//	UltrasonicSensor ultra;
	Claw claw;
	Autonomous auto;
	Lift lift;
	AHRS gyro;
	AnalogPotentiometer left, right;
	
	public char allianceSwitch;
	public char scale;
	public char opponentSwitch;
			//Game info
	
	
	//Autonomous variables
	private static final String kDefaultAuto = "Default"; //Default auto command
	private static final String kCustomAuto = "My Auto"; //Custom auto command
	private String m_autoSelected; 
	private SendableChooser<String> m_chooser = new SendableChooser<>(); //Options for auto
	private SendableChooser<Integer> position = new SendableChooser<>(); //Options for robot position

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Initialize subsystems
		gyro = new AHRS(I2C.Port.kMXP);
		drive= new Drivetrain();
		claw = new Claw();
		lift = new Lift(this);
		xbox = new XboxController(0, this);
		CameraServer.getInstance().startAutomaticCapture();
		gyro.reset();

		left = new AnalogPotentiometer(4);
		right = new AnalogPotentiometer(5);
		
//		ultra = new UltrasonicSensor();
		/**
		 * This function is used to view which side is the allied side of the switch and scale.
		 */
		
		//Add auto commands as options
//		m_chooser.addDefault("Default Auto", kDefaultAuto);
//		m_chooser.addObject("My Auto", kCustomAuto);
//		SmartDashboard.putData("Auto choices", m_chooser);
		
//		position.addDefault("Far left", 0);
//		position.addObject("Left", 1);
//		position.addObject("Center", 2);
//		position.addObject("Right", 3);
//		position.addObject("Far right", 4);
//		SmartDashboard.putData("Robot Position", position);
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {

		String gameInfo = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
		if (gameInfo != null && gameInfo.length() > 0) {
			allianceSwitch = gameInfo.charAt(0);
			scale = gameInfo.charAt(1);
			opponentSwitch = gameInfo.charAt(2);
		}
		
		//Get auto command chosen
//		m_autoSelected = m_chooser.getSelected();
//		System.out.println("Auto selected: " + m_autoSelected);
//		
//		auto = new Autonomous(this, position.getSelected());
//		System.out.println("Running position " + position.getSelected());
//		
//		//Run auto command
//		switch (m_autoSelected) {
//			case kCustomAuto:
//				// Put custom auto code here
//				break;
//			case kDefaultAuto:
//			default:
//				// Put default auto code here
//				break;
//		}
		
		//Dumb switch
		if (allianceSwitch == 'r') {
			System.out.println("YEE");
			drive.forback(.4);
			Timer.delay(.3);
			lift.start_switch();
			Timer timer = new Timer(1);
			while (!timer.isDone()) {
				drive.forback(.4);
			} 
			drive.stop();
			claw.toggle();
		} else {
			Timer timer = new Timer(4);
			while (!timer.isDone()) {
				drive.forback(.4);
			} 
			drive.stop();
		}
		
		//Baseline
		Timer timer = new Timer(4);
		while (!timer.isDone()) {
			drive.forback(.4);
		}
		drive.stop();
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
	}
	
	@Override
	public void teleopInit() {
//		gyroSPI.reset();
//		lift.retractUpper();
//		lift.retractLower();
		gyro.reset();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		if (gyro.getRoll() < 75) {
			drive.forback(-.3);
		} else {
			xbox.refreshController();
			xbox.enableController();
		}
		
		SmartDashboard.putNumber("Pitch:", gyro.getPitch());
		SmartDashboard.putNumber("Roll:", gyro.getRoll());
		SmartDashboard.putNumber("X angle:", gyro.getRawGyroX());
		SmartDashboard.putNumber("Y angle:", gyro.getRawGyroY());
		SmartDashboard.putNumber("Z angle:", gyro.getRawGyroZ());
		SmartDashboard.putNumber("X Displacement:", gyro.getDisplacementX());
		SmartDashboard.putNumber("Y Displacement:", gyro.getDisplacementY());
		SmartDashboard.putNumber("Z Displacement:", gyro.getDisplacementZ());

		SmartDashboard.putNumber("Left Pot", left.get());
		SmartDashboard.putNumber("Left Pot PID", left.pidGet());
		SmartDashboard.putNumber("Right Pot", right.get());
		SmartDashboard.putNumber("Right Pot PID", right.pidGet());


//		gyro.updateGyro();
		lift.displayHallSensors();
//		SmartDashboard.putNumber("Pot", pot.getReading());
//		SmartDashboard.putNumber("Gyro Value", gyro.getAngle());
//		SmartDashboard.putNumber("speed", drive.updateSpeed());
	}
	
	@Override
	public void disabledPeriodic() {
		
		SmartDashboard.putNumber("Pitch:", gyro.getPitch());
		SmartDashboard.putNumber("X angle:", gyro.getQuaternionX());
		SmartDashboard.putNumber("Y angle:", gyro.getQuaternionY());
		SmartDashboard.putNumber("Z angle:", gyro.getQuaternionZ());
		SmartDashboard.putNumber("X Displacement:", gyro.getDisplacementX());
		SmartDashboard.putNumber("Y Displacement:", gyro.getDisplacementY());
		SmartDashboard.putNumber("Z Displacement:", gyro.getDisplacementZ());
	}

	@Override
	public void disabledInit() {
	    System.out.println("Default disabledInit() method... Overload me!");
	    lift.lowerExt = true;
	}
}
