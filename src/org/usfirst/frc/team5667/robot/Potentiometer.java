package org.usfirst.frc.team5667.robot;
import edu.wpi.first.wpilibj.*;
public class Potentiometer {

	AnalogPotentiometer potentiometer;
	
	public Potentiometer(int channel){
		potentiometer=new AnalogPotentiometer(channel);
	}
	/**
	 * this function returns the reading from the potentiometer
	 * @return
	 */
	public double getReading() {
		return potentiometer.get();
	}
	
}
