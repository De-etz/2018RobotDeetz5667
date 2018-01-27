package org.usfirst.frc.team5667.robot;
import edu.wpi.first.wpilibj.*;
public class Potentiometer {

	AnalogPotentiometer potentiometer;
	
	public Potentiometer(int channel){
		potentiometer=new AnalogPotentiometer(channel);
	}
	public double getReading() {
		return potentiometer.get();
	}
	
}
