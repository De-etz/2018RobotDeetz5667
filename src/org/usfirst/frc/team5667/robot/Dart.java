package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dart {
	
	public PWMTalonSRX motor;
	public Hall max, start, min;
	private double potMin, potMax;
	private AnalogPotentiometer pot;
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
	public Dart(int motor, int pinmin, int pinmax, AnalogPotentiometer pot) {
		potMin=pinmin;
		potMax=pinmax;
		this.motor = new PWMTalonSRX(motor);
		this.pot = pot;
	}
	
	public void set(double input) {
		double range=potMax-potMin;
		rextraxt(potMin+((range)*=input));
	}
	
	/**
	 * This function is used to stop the actuator when it hits a value
	 * 
	 * @param target The value of the target position of the actuator (this is not static)
	 */
	private void rextraxt(double target) {
		
		final double kP = 1;
		
		//Move actuator to towards that reading
		boolean done = false;
		while (!done) {
			//Get current reading
			double curr = pot.getReading();
			double err = target-curr; //Process
			SmartDashboard.putNumber("Error", err);
			if (Math.abs(err) > .005) {
				double correct = kP*err;
				SmartDashboard.putNumber("Correct", correct);
				motor.set(correct); //Control
			} else {
				done = true;
				motor.set(0);
			}
		}
	}
}

	
		
	
	


