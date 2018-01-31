package org.usfirst.frc.team5667.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PWMTalonSRX;

public class Dart {
	
	private Potentiometer pot;
	private PWMTalonSRX motor;
	private double top, bottom, range;
	
	public Dart(int motor, int pot, double upper, double lower) {
		top = upper;
		bottom = lower;
		range = top-bottom;
		this.pot = new Potentiometer(pot);
		this.motor = new PWMTalonSRX(motor);
	}
	
	public void extend(double value) {
		double newValue = value*range+bottom;
	}
	
	public void set(double target) {
		
		final double kP = 1;
		
		//Move actuator to towards that reading
		boolean done = false;
		
		while (!done) {
			//Get current reading
			double curr = pot.getReading();
			double err = target-curr;
			if (Math.abs(err) > .01) {
				double correct = kP*err;
				motor.set(correct);
			} else {
				done = true;
			}
		}
	}
}
