package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HallAnalog {
	//stop being stupid work
	AnalogInput input;
	public HallAnalog(int pin) {
		input = new AnalogInput(pin);
	}
	/**
	 * This function gets the digital reading of the Hall
	 * @return	the value of the digital reading of the Hall
	 */	
	public boolean returnReading() {
		SmartDashboard.putNumber("Analog reading", input.getValue());
		boolean reading = !(3000 < input.getValue());
		return reading;
	}

}