package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw {

	SimpleDart left;
	SimpleDart right;
	boolean open;
	
	public Claw() {
		left = new SimpleDart(8);
		right = new SimpleDart(9);
		open = false;
		SmartDashboard.putString("Claw", "CLOSED");
	}
	
	public void toggle() {
		if (open) {
			left.set(-1);
			right.set(-1);
			open = false;
		} else {
			left.set(1);
			right.set(1);
			open = true;
		}
	}
	
}
