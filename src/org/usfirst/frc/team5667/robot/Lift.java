package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift {

	Dart lower1;
	Dart lower2;
	Dart upper1;
	Dart upper2;
	private int extendTime;
	private final double rextendSpeed=0.6;
	public boolean lowerExt;
	public boolean upperExt;
	
	public Lift() {
		lower1 = new Dart(4, 0, 1);
		lower2 = new Dart(5, 2, 3);
		upper1 = new Dart(6, 4, 5);//was 6,4,5
		upper2 = new Dart(7, 6, 7);
		
		lowerExt = true;
		extendTime = 10;
	}
	
	public void rextractLower() {
		if (lowerExt) {
			retractLower();
		} else {
			extendLower();
		}
	}
	public void rextractUpper() {
		if (upperExt) {
			retractUpper();
		} else {
			extendUpper();
		}
	}
	public void manualLower(double speed) {
		SmartDashboard.putNumber("Speed", speed);
		if (speed < 0) {
			if (!lower1.min.returnReading()) {
				if (speed > -.35) {
					SmartDashboard.putString("Actuator 1:", "Slow");
					lower1.motor.set(-.3);
				} else {
					SmartDashboard.putString("Actuator 1:", "FAST");
					lower1.motor.set(speed);
				}				
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.min.returnReading()) {
				if (speed > -.35) {
					SmartDashboard.putString("Actuator 2:", "Slow");
					lower2.motor.set(-.3);
				} else {
					SmartDashboard.putString("Actuator 2:", "FAST");
					lower2.motor.set(speed);
				}
				
			} else {
				lower2.motor.set(0);
			}
		} else {
			if (!lower1.max.returnReading()) {
				if (speed < .35) {
					SmartDashboard.putString("Actuator 1:", "Slow");
					lower1.motor.set(.3);
				} else {
					SmartDashboard.putString("Actuator 1:", "FAST");
					lower1.motor.set(speed);
				}
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.max.returnReading()) {
				if (speed < .35) {
					SmartDashboard.putString("Actuator 2:", "Slow");
					lower2.motor.set(.3);
				} else {
					SmartDashboard.putString("Actuator 2:", "FAST");
					lower2.motor.set(speed);
				}
			} else {
				lower2.motor.set(0);
			}
		}
		
//		if (!lower1.max.returnReading() && !lower1.min.returnReading()) {
//			lower1.motor.set(.6*speed);
//		} else {
//			lower1.motor.set(0);
//		}
//		if (!lower2.max.returnReading() && !lower2.min.returnReading()) {
//			lower2.motor.set(.6*speed);
//		} else {
//			lower2.motor.set(0);
//		}
		
	}
	public void manualUpper(double speed) {
		if (speed < 0) {
			if (!upper1.min.returnReading()) {
				upper1.motor.set(.6*speed);
				
			} else {
				upper1.motor.set(0);
			}
			if (!upper2.min.returnReading()) {
				upper2.motor.set(.6*speed);
			} else {
				upper2.motor.set(0);
			}
		} else {
			if (!upper1.max.returnReading()) {
				upper1.motor.set(.6*speed);
				
			} else {
				upper1.motor.set(0);
			}
			if (!upper2.max.returnReading()) {
				upper2.motor.set(.6*speed);
			} else {
				upper2.motor.set(0);
			}
		}
		
		
	}
	public void stopLower()
	{
		SmartDashboard.putString("Actuator 1:", "Stopped");
		SmartDashboard.putString("Actuator 2:", "Stopped");
		lower1.motor.set(0);
		lower2.motor.set(0);
	}
	public void stopUpper()
	{
		upper1.motor.set(0);
		upper2.motor.set(0);
	}
	public void extendLower() {
		System.out.println("Start extending lower...");
		while (!lower1.max.returnReading() || !lower2.max.returnReading()) {
			if (!lower1.max.returnReading()) {
				lower1.motor.set(rextendSpeed);
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.max.returnReading()) {
				lower2.motor.set(rextendSpeed);
			} else {
				lower2.motor.set(0);
			}
			displayHallSensors();
		}
		lower1.motor.set(0);
		lower2.motor.set(0);
		lowerExt = true;
		System.out.println("Done extending");
	}
	public void displayHallSensors() {
		
		SmartDashboard.putBoolean("Hall1 min",lower1.min.returnReading());
		SmartDashboard.putBoolean("Hall1 max",lower1.max.returnReading());
		SmartDashboard.putBoolean("Hall2 min",lower2.min.returnReading());
		SmartDashboard.putBoolean("Hall2 max",lower2.max.returnReading());
		SmartDashboard.putBoolean("Hall3 min",upper1.min.returnReading());
		SmartDashboard.putBoolean("Hall3 max",upper1.max.returnReading());
		SmartDashboard.putBoolean("Hall4 min",upper2.min.returnReading());
		SmartDashboard.putBoolean("Hall4 max",upper2.max.returnReading());
		SmartDashboard.putBoolean("Lower Arm State", lowerExt);
		SmartDashboard.putBoolean("Upper Arm State", upperExt);
	}
	public void coordinatedMovement() {
		
		for (double t=0;t<extendTime;t++) {
			
		}
	}
	public void retractLower() {
		System.out.println("Start retracting lower...");
		while (!lower1.min.returnReading() || !lower2.min.returnReading()) {
			if (!lower1.min.returnReading()) {
				lower1.motor.set(-rextendSpeed);
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.min.returnReading()) {
				lower2.motor.set(-rextendSpeed);
			} else {
				lower2.motor.set(0);
			}
			
			displayHallSensors();
		}
		lower1.motor.set(0);
		lower2.motor.set(0);
		lowerExt = false;
		System.out.println("Done retracting");
	}
	

	
	public void extendUpper() {
		System.out.println("Start extending upper...");
		while (!upper1.max.returnReading() || !upper2.max.returnReading()) {
			if (!upper1.max.returnReading()) {
				upper1.motor.set(rextendSpeed);
			} else {
				upper1.motor.set(0);
			}
			if (!upper2.max.returnReading()) {
				upper2.motor.set(rextendSpeed);
			} else {
				upper2.motor.set(0);
			}
			displayHallSensors();
		}
		upper1.motor.set(0);
		upper2.motor.set(0);
		upperExt = true;
		System.out.println("Done extending");
	}
	
	public void retractUpper() {
		System.out.println("Start retracting upper...");
		while (!upper1.min.returnReading() || !upper2.min.returnReading()) {
			if (!upper1.min.returnReading()) {
				upper1.motor.set(-rextendSpeed);
			} else {
				upper1.motor.set(0);
			}
			if (!upper2.min.returnReading()) {
				upper2.motor.set(-rextendSpeed);
			} else {
				upper2.motor.set(0);
			}
			displayHallSensors();
		}
		upper1.motor.set(0);
		upper2.motor.set(0);
		upperExt = false;
		System.out.println("Done retracting");
	}

	public void raiseLift() {
		while (!lower1.max.returnReading() || !lower2.max.returnReading()) {
			manualLower(.4);
			manualUpper(.7);
		}
		stopLower();
		while(!upper1.max.returnReading() || !upper2.max.returnReading()) {
			manualUpper(1);
		}
		stopUpper();
	}

}
