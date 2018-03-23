package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Potentiometer {

	AnalogPotentiometer pot;
	
	public Potentiometer(int pin/*, int min, int max*/) {
		pot = new AnalogPotentiometer(pin);
	}
	
}
