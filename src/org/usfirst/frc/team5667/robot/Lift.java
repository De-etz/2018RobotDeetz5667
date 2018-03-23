package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift {

	Dart lower1;
	Dart lower2;
	Dart upper1;
	Dart upper2;
	Hall startHall;
	Hall upperHall;
	Hall lowerHall;
	HallAnalog switchHall;
	private int extendTime;
	private final double rextendSpeed=0.6;
	public boolean lowerExt;
	public boolean upperExt;
	
	Robot robot;
	
	public State state;
	
	public enum State {
		START, SWITCH, SCALE, GROUND
	}
	
	public Lift(Robot robot) {
		lower1 = new Dart(4, 0, 1);
		lower2 = new Dart(5, 2, 3);
		upper1 = new Dart(6, 4, 5);//was 6,4,5
		upper2 = new Dart(7, 6, 7);
		startHall = new Hall(9);
		upperHall = new Hall(8);
		switchHall = new HallAnalog(1);
		
		lowerExt = true;
		extendTime = 10;
		
		this.robot = robot;
		state = State.START;
	}
	
	public void toStart() {
		while (!startHall.returnReading()) {
			if (robot.xbox.inputMenu) {
				break;
			}
			manualLower(-.5);
		}
		manualUpper(-1);
		Timer.delay(.5);
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
	public boolean manualLower(double speed) {
		speed*=-1;
		if (speed < 0) {
			if (!lower1.min.returnReading()) {
				if (speed > -.35) {
					lower1.motor.set(-.3);
				} else {
					lower1.motor.set(speed);
				}				
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.min.returnReading()) {
				if (speed > -.35) {
					lower2.motor.set(-.3);
				} else {
					lower2.motor.set(speed);
				}
				
			} else {
				lower2.motor.set(0);
			}
			return (lower1.min.returnReading() && lower2.min.returnReading());
		} else {
			if (!lower1.max.returnReading()) {
				if (speed < .35) {
					lower1.motor.set(.3);
				} else {
					lower1.motor.set(speed);
				}
			} else {
				lower1.motor.set(0);
			}
			if (!lower2.max.returnReading()) {
				if (speed < .35) {
					lower2.motor.set(.3);
				} else {
					lower2.motor.set(speed);
				}
			} else {
				lower2.motor.set(0);
			}
			return (lower1.max.returnReading() && lower2.max.returnReading());
		}
		
	}
	public boolean manualUpper(double speed) {
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
			return (upper1.min.returnReading() && upper2.min.returnReading());
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
			return (upper1.max.returnReading() && upper2.max.returnReading());
		}
		
		
	}
	public void stopLower()
	{
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
		SmartDashboard.putBoolean("Start Hall",startHall.returnReading());
		SmartDashboard.putBoolean("Hall1 max",lower1.max.returnReading());
		SmartDashboard.putBoolean("Hall2 min",lower2.min.returnReading());
		SmartDashboard.putBoolean("Hall2 max",lower2.max.returnReading());
		SmartDashboard.putBoolean("Hall3 min",upper1.min.returnReading());
		SmartDashboard.putBoolean("Upper Hall",upperHall.returnReading());
		SmartDashboard.putBoolean("Switch Hall",switchHall.returnReading());
		SmartDashboard.putBoolean("Hall3 max",upper1.max.returnReading());
		SmartDashboard.putBoolean("Hall4 min",upper2.min.returnReading());
		SmartDashboard.putBoolean("Hall4 max",upper2.max.returnReading());
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
	
	public void scale_start() {
		
		if (state == State.SCALE) {
			System.out.println("Going to START");
			
			while (!manualLower(-.3)) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
			}	
			Timer.delay(.2);
			while (!upperHall.returnReading()) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				manualUpper(-.8);
			}
			while (!startHall.returnReading()) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				manualUpper(-.75);
				manualLower(.5);
			}
			stopLower();
			while (!manualUpper(-.8)) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
			}
			stopUpper();
			System.out.println("Done.");
		} else {
			System.out.println("Not in SCALE position");
		}
		
		
	}
	
	public void start_scale() {
		
		if (state == State.START) {
			System.out.println("Going to SCALE");
			while (!manualLower(-.4)) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				manualUpper(.8);
			}
			while (!manualUpper(1)) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
			}
			manualLower(.3);
			Timer.delay(.1);
			while (!startHall.returnReading()) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				manualLower(.6);
			}
			stopLower();
			stopUpper();
			System.out.println("Done.");
		} else {
			System.out.println("Not in START position");
		}		
		
	}
	
	public void start_ground() {
		if (state == State.START) {
			
			System.out.println("Going to GROUND");
			while (!manualLower(.5)) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				robot.xbox.enableDrive(.7);
			}
			stopLower();
		} else {
			System.out.println("Not in START position");
		}
	}
	
	public void ground_start() {
		if (state == State.GROUND) {
			System.out.println("Going to START");
			while (!startHall.returnReading()) { 
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				robot.xbox.enableDrive(.7);
				manualLower(-.5);
			}
			stopLower();
		} else {
			System.out.println("Not in GROUND position");
		}
	}
	
	public void start_switch() {
		if (state == State.START) {
			System.out.println("Going to SWITCH");
			while (!switchHall.returnReading()) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				robot.xbox.enableDrive(.5);
				manualLower(-.6);
				manualUpper(1);
			}
			stopUpper();
		} else {
			System.out.println("Not in START position");
		}
		
	}
	
	public void switch_start() {
		if (state == State.SWITCH) {
			System.out.println("Going to START");

			while (!upperHall.returnReading()) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				robot.xbox.enableDrive(.5);
				manualUpper(-1);
			}
			while (!startHall.returnReading()) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				robot.xbox.enableDrive(.5);
				manualUpper(-1);
				manualLower(.5);
			}
			stopLower();
			while (!manualUpper(-.8)) {
				robot.xbox.refreshController();
				if (robot.xbox.inputMenu) {
					break;
				}
				robot.xbox.enableDrive(.5);
			}
			
		} else {
			System.out.println("Not in SWITCH position");
		}
	}

}
