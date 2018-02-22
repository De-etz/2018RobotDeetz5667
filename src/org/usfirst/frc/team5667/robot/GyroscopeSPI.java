package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroscopeSPI {
	private ADXRS450_Gyro gyro;
	
	private double angle;
	private double rate;
	private double absAngle;
	private boolean active;
	
	public GyroscopeSPI() {
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		angle = 0;
		rate = 0;
		active = false;
	}
	/**
	 * this function sets the gyroscope to active
	 */
	public void start() {
		active = true;
	}
	/**
	 * This function updates the value of the gyro 
	 */
	public void updateGyro() {
		angle = gyro.getAngle();
		angle = (int)(angle *10);
		angle = angle /10.0;
		rate = ((int)(gyro.getRate()*10))/10.0;
		SmartDashboard.putNumber("SPI Gyro value", angle);
		SmartDashboard.putNumber("SPI Gyro rate", rate);
	}
	/**
	 * This function returns the value of the gyro
	 * @return
	 */
	public double getAngle() {
		updateGyro();
		return angle;
	}
	
	public double getDerivative() {
		updateGyro();
		return rate;
	}
/**
 * this function rests the value of the gyro to 0?
 */
	public void reset() {
		gyro.reset();
		updateGyro();
	}
	/** this function prints out the angle of the gyro
	 *
	 */
	public void displayAngle() {
		System.out.println("Angle: " + getAngle());
	}
}
