package org.usfirst.frc.team5667.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class XboxController extends Joystick {
	Robot robot; //Robot
	
	//Controller values
	private double inputRSX, inputRSY; //Right joystick axis values
	private double inputLSX, inputLSY; //Left joystick axis values
	private double inputRT, inputLT; //Trigger values
	private boolean inputRB, inputLB; //Bumper states
	private boolean inputA, inputB, inputX, inputY, inputMenu, inputStart; //Button states
	private final double kGHOST = .15; //Threshold for blocking ghost signals
	
	/**
	 * Initializes an Xbox controller.
	 * @param port Just make it zero
	 * @param robot Access to all of the robot's other subsystems
	 */
	public XboxController(int port, Robot robot){
		super(port);
		//Initial states
		inputRSX = 0;
		inputLSY = 0;
		inputLSX = 0;
		inputRT = 0;
		inputLT = 0;
		inputRB = false;
		inputLB = false;
		inputA = false;
		inputB = false;
		inputX = false;
		inputY = false;
		inputMenu = false;
		inputStart = false;
		
		this.robot = robot; //Robot
	}
	
	/**
	 * Reads all the button and joystick values from the controller.
	 */
	private void updateController() {
		inputRSX = super.getRawAxis(4);
		inputRSY = -super.getRawAxis(5);
		inputLSX = super.getRawAxis(0);
		inputLSY = -super.getRawAxis(1);
		inputRT = super.getRawAxis(3);
		inputLT = super.getRawAxis(2);
		inputRB = super.getRawButton(6);
		inputLB = super.getRawButton(5);
		inputA = super.getRawButton(1);
		inputB = super.getRawButton(2);
		inputX = super.getRawButton(3);
		inputY = super.getRawButton(4);
		inputMenu = super.getRawButton(8);
		inputStart = super.getRawButton(7);
	}
	
	/**
	 * Executes robot commands based off the values of the controller.
	 */
	public void enableController() {
		//Check the values of the controller
		updateController(); // Hey Deetz I'm typing this on an Xbox Controller! 
		
		//Check buttons
		if (inputA) {
			robot.lift.rextractLower();
			while (inputA) {
				updateController();
			}
		} else if (inputB) {
			robot.lift.rextractUpper();
			while (inputB) {
				updateController();
			}
		} else if (inputX) {
			
		} else if (inputY) {
			SmartDashboard.putBoolean("Y Button", inputY);
			robot.claw.toggle();
			while (inputY) {
				updateController();
			}
		} else if (inputMenu) {

		} else if (inputStart) {

		} else {
			SmartDashboard.putBoolean("Y Button", inputY);
			
		}
		
//		robot.dart.set(inputLSY);
		
		//Check joysticks
		/*
		if ((inputLSY > kGHOST || inputLSY < -kGHOST) && (inputRSX > kGHOST || inputRSX < -kGHOST)){
			System.out.println("BANK");
			robot.drive.bank(inputLSY, inputRSX);
		} else if (inputLSY > kGHOST || inputLSY < -kGHOST){
			System.out.println("FORBACK");
			robot.drive.forback(inputLSY);
		} else if (inputRSX > kGHOST || inputRSX < -kGHOST){
			System.out.println("ROTATE");
			robot.drive.rotate(inputRSX);
		} else 
			robot.drive.stop();
		*/
		if (inputLSY > kGHOST || inputLSY < -kGHOST){
			robot.lift.manualLower(inputLSY);
		}
		else
		{
			robot.lift.stopLower();
		}
		if (inputRSY > kGHOST || inputRSY < -kGHOST){
			robot.lift.manualUpper(inputRSY);
		}
		else
		{
			robot.lift.stopUpper();
		}
	}
	
	public void test() {
		updateController();
		
//		robot.dart.extend(inputLSY);
	}
}
