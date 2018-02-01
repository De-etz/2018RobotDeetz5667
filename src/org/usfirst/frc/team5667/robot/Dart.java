package org.usfirst.frc.team5667.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		if (0 <= value && value <= 1) {
			double newValue = value*range+bottom;
			set(newValue);
		} else {
			System.out.println("Invalid!");
		}
	}
	
	private void set(double target) {
		
		final double kP = 1;
		
		//Move actuator to towards that reading
		boolean done = false;
		Timer timer = new Timer();
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
				SmartDashboard.putNumber("Time for DART motion", timer.getElapsed());
			}
		}
	}
}
