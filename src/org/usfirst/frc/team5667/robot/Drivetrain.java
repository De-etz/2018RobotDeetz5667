package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	//Declare subsystems
	private Spark l1;
	private Spark r1;
	PWMTalonSRX speed;
	
	Robot robot;
	
	/**
	 * Initializes a drivetrain
	 */
	public Drivetrain(Robot robot) {
		
		this.robot = robot;
		// initializes subsystems
		l1 = new Spark(0);
		//l2 = new Spark(2);
		r1 = new Spark(1);
		//r2 = new Spark(3);
	}
	/**
	 * Stops the drivetrain
	 */
	public void stop(){
		l1.set(0);
		//l2.set(0);
		r1.set(0);
		//r2.set(0);
		
	}
	/**
	 * 
	 * @param speed The value of the speed of the robot
	 */
	public void forback(double speed){
		l1.set(speed);
		//l2.set(speed);
		r1.set(speed);
		//r2.set(speed);
		
	}
	/**
	 * This function rotates the robot at a certain speed
	 * @param speed The value of the speed at which the robot should turn
	 */
	public void rotate(double speed){
		l1.set(.6*speed);
		//l2.set(speed);
		r1.set(.6*-speed);
		//r2.set(-speed);
	
	}
	
	public void autoRotate(int degrees) {
		double err = robot.gyroSPI.getAngle()-degrees;
		while (Math.abs(err) < 2) {
			
		}
	}
	
	/**
	 * This function banks the robot in a certain direction at a certain speed
	 * @param speed The value of the speed of the robot
	 * @param turn The angle at which the robot is turning
	 */
	public void bank(double speed, double turn){
		if (speed>=0 && turn>=0){ //forward to right
			System.out.println("AJEE broke the rpbpt");
			l1.set(speed);
			//l2.set(speed);
			r1.set((1-turn)*speed);
			//r2.set((1-turn)*speed);
	    } else if (speed<=0 && turn>=0){ //backward to right
			l1.set(speed);
			//l2.set(speed);
			r1.set((1-turn)*speed);
			//r2.set((1-turn)*speed);
		} else if (speed>=0 && turn<=0){ //forward to the left
			l1.set((1+turn)*speed);
			//l2.set((1+turn)*speed);
			r1.set(speed);
			//r2.set(speed);
		} else {//backward to the left
			l1.set((1+turn)*speed);
			//l2.set((1+turn)*speed);
			r1.set(speed);
			//r2.set(speed);
		}
		
	}
	
}


