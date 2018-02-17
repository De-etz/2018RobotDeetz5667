package org.usfirst.frc.team5667.robot;

public class Claw {

	SimpleDart left;
	SimpleDart right;
	boolean open;
	
	public Claw() {
		left = new SimpleDart(8);
		right = new SimpleDart(9);
		open = true;
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
