package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	//Declare subsystems
	private PWMTalonSRX l1;
	private PWMTalonSRX l2;
	private PWMTalonSRX r1;
	private PWMTalonSRX r2;
	PWMTalonSRX speed;
	
	/**
	 * Initializes a drivetrain
	 */
	public Drivetrain() {
		// initializes subsystems
		l1 = new PWMTalonSRX(0);
		l2 = new PWMTalonSRX(1);
		r1 = new PWMTalonSRX(2);
		r2 = new PWMTalonSRX(3);
	}
	/**
	 * Stops the drivetrain
	 */
	public void stop(){
		l1.set(0);
		l2.set(0);
		r1.set(0);
		r2.set(0);
		
	}
	/**
	 * 
	 * @param speed The value of the speed of the robot
	 */
	public void forback(double speed){
		l1.set(speed);
		l2.set(speed);
		r1.set(speed);
		r2.set(speed);
		
	}
	/**
	 * This function rotates the robot at a certain speed
	 * @param speed The value of the speed at which the robot should turn
	 */
	public void rotate(double speed){
		l1.set(speed);
		l2.set(speed);
		r1.set(-speed);
		r2.set(-speed);
	
	}
	/**
	 * This function banks the robot in a certain direction at a certain speed
	 * @param speed The value of the speed of the robot
	 * @param turn The angle at which the robot is turning
	 */
	public void bank(double speed, double turn){
		if (speed>=0 && turn>=0){ //forward to right
			l1.set(speed);
			l2.set(speed);
			r1.set((1-turn)*speed);
			r2.set((1-turn)*speed);
	    } else if (speed<=0 && turn>=0){ //backward to right
			l1.set(speed);
			l2.set(speed);
			r1.set((1-turn)*speed);
			r2.set((1-turn)*speed);
		} else if (speed>=0 && turn<=0){ //forward to the left
			l1.set((1+turn)*speed);
			l2.set((1+turn)*speed);
			r1.set(speed);
			r2.set(speed);
		} else {//backward to the left
			l1.set((1+turn)*speed);
			l2.set((1+turn)*speed);
			r1.set(speed);
			r2.set(speed);
		}
		
		}
	
	public double updateSpeed() {
		return speed.get();
	}
}


