package org.usfirst.frc.team5667.robot;

public class Lift {

	Dart lower1;
	Dart lower2;
	Dart upper1;
	Dart upper2;
	
	private final double rextendspeed=0.5;
	public boolean lowerExt;
	public boolean upperExt;
	
	public Lift() {
		lower1 = new Dart(4, 0, 1);
		lower2 = new Dart(5, 2, 3);
		upper1 = new Dart(6, 4, 5);
		upper2 = new Dart(7, 6, 7);
		
		lowerExt = true;
	}
	
	public void rextract() {
		if (lowerExt) {
			retractLower();
			lowerExt = false;
		} else {
			extendLower();
			lowerExt = true;
		}
	}
	
	public void extendLower() {
		System.out.println("Start extending...");
		while (!lower1.max.returnReading() || !lower2.max.returnReading()) {
			if (!lower1.max.returnReading()) {
				lower1.motor.set(rextendspeed);
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.max.returnReading()) {
				lower2.motor.set(rextendspeed);
			} else {
				lower2.motor.set(0);
			}
		}
		lower1.motor.set(0);
		lower2.motor.set(0);
		System.out.println("Done extending");
	}
	
	public void retractLower() {
		System.out.println("Start retracting...");
		while (!lower1.min.returnReading() || !lower2.min.returnReading()) {
			if (!lower1.min.returnReading()) {
				lower1.motor.set(rextendspeed);
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.min.returnReading()) {
				lower2.motor.set(rextendspeed);
			} else {
				lower2.motor.set(0);
			}
		}
		lower1.motor.set(0);
		lower2.motor.set(0);
		System.out.println("Done extending");
	}

}
