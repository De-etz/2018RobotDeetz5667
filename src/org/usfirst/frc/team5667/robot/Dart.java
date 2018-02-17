package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dart {
	
	public PWMTalonSRX motor;
	public Hall max, start, min;
	
	private final double rextendspeed=0.5;
	public boolean extended;
	
	public Dart(int motor, int pinmin, int pinmax) {
		max=new Hall(pinmax);// hi deetz here and i suck
		min=new Hall(pinmin);
		this.motor = new PWMTalonSRX(motor);
		extended = true;
	}
	public Dart(int motor, int pinmin, int start, int pinmax) {
		max=new Hall(pinmax);
		min=new Hall(pinmin);
		this.motor = new PWMTalonSRX(motor);
		extended = true;
	}
	
	/**
	 * 
	 */
	public void rextract() {
		if (extended) {
			retract();
			extended = false;
		} else {
			extend();
			extended = true;
		}
	}
	/**
	 * This function is used to extend the actuator
	 */
	private void extend() {
		System.out.println("Start extending...");
		while (!max.returnReading()) {
			motor.set(rextendspeed);
		}
		System.out.println("Done extending");
		motor.set(0);
	}
	
	private void retract() {
		System.out.println("Start retracting...");
		while (!min.returnReading()) {
			motor.set(-rextendspeed);
		}
		System.out.println("Done retracting");
		motor.set(0);
	}
	
	public void disable() {
		retract();
		extended = false;
	}
}
	
//	/**
//	 * This function is used to stop the actuator when it hits a value
//	 * 
//	 * @param target The value of the target position of the actuator (this is not static)
//	 */
//	private void set(double target) {
//		
//		final double kP = 1;
//		
//		//Move actuator to towards that reading
//		boolean done = false;
//		Timer timer = new Timer();
//		while (!done) {
//			//Get current reading
//			double curr = pot.getReading();
//			double err = target-curr; //Process
//			SmartDashboard.putNumber("Error", err);
//			if (Math.abs(err) > .005) {
//				double correct = kP*err;
//				SmartDashboard.putNumber("Correct", correct);
//				motor.set(correct); //Control
//			} else {
//				done = true;
//				motor.set(0);
//				SmartDashboard.putNumber("Time for DART motion", timer.getElapsed());
//			}
//		}
//	}

	
		
	
	


