package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class Hall {
	DigitalInput input;
	public Hall(int pin) {
		input = new DigitalInput(pin);
	}
	/**
	 * This function gets the digital reading of the Hall
	 * @return	the value of the digital reading of the Hall
	 */	
	public boolean returnReading() {
		boolean reading = !input.get();
		return reading;
	}

}
