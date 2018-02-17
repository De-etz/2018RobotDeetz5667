package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.PWMTalonSRX;

public class SimpleDart {
	
	PWMTalonSRX motor;
	boolean extended;

	public SimpleDart(int motor) {
		this.motor = new PWMTalonSRX(motor);
		extended = true;
	}
	
	public void set(double speed) {
		motor.set(speed);
	}
	
	public void rextract() {
		if (extended) {
			motor.set(1);
			extended = false;
		} else {
			motor.set(-1);
			extended = true;
		}
	}

}
