package org.usfirst.frc.team5667.robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

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
	private Object[] controls = {inputLSX, inputLSY, inputRSX, inputRSY, inputRT, inputLT, inputRB, inputLB, 
			inputA, inputB, inputX, inputY, inputMenu, inputStart};
	private boolean drive;
	private final double kGHOST = .1; //Threshold for blocking ghost signals
	private final double tStep = .01;
	
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
		drive = true;
		
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
	
	public void recall(String command) {

		System.out.println("Starting recall...");
		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/lvuser/" + command + ".txt"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    
		    while (line != null) {
		    	int prev = -1;
		    	for (int i = 0; i < controls.length; i++) {
		    		if (i > 5) {
		    			controls[i] = Boolean.parseBoolean(line.substring(prev+1, line.indexOf(",", prev+1)));
		    		} else {
		    			controls[i] = Double.parseDouble(line.substring(prev+1, line.indexOf(",", prev+1)));
		    		}		    		
		    	}
		    	
		    	enableController();
		    	
				sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        Timer.delay(tStep);
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		    br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done recalling.");
		
	}
	
	public void copycat(String command) {
		System.out.println("Starting...");
//		double stop = input;
		
		try {
			File file = new File("/home/lvuser/"+command+".txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (double t = 0; t != -1; t+=tStep) {
				t = Math.round(t*100)/100.0;
				System.out.println(t);
				updateController();
				if (inputRB) {
					break;
				}
				enableController();
//				if (inputLSY > kGHOST || inputLSY < -kGHOST) robot.lift.manualLower(inputLSY);
//				else robot.lift.stopLower();
//				
//				if (inputRSY > kGHOST || inputRSY < -kGHOST) robot.lift.manualUpper(inputRSY);
//				else robot.lift.stopUpper();
				Timer.delay(tStep);
				
				for (Object input : controls) {
					writer.write(input + ",");
				}
				writer.write("\n");
				
			} 
			writer.close();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("Done copying.");
		
//		System.out.println("Lower: " + lower.toString());
//		System.out.println("Upper: " + upper.toString());
	}
	
	/**
	 * Executes robot commands based off the values of the controller.
	 */
	public void enableController() {
		//Check the values of the controller
		updateController(); // Hey Deetz I'm typing this on an Xbox Controller! 
		
		//Check buttons
		if (inputA) {
			robot.lift.start_switch();
			while (inputA) updateController();
		} else if (inputB) {	
			robot.lift.switch_start();
			while (inputB) updateController();
		} else if (inputX) {
			robot.lift.start_scale();
			while (inputX) updateController();
		} else if (inputY) {
			robot.lift.scale_start();
			while (inputY) updateController();
		} else if (inputMenu) {
			drive = !drive;
			while (inputMenu) updateController();
		} else if (inputLB) {
			robot.claw.toggle();
			while (inputLB) updateController();
		} else if (inputRB) {
			
			while (inputRB) updateController();
		} else if (inputStart) {
			robot.claw.toggle();
			while (inputStart) updateController();
		} else {
			
		}
		
//		robot.dart.set(inputLSY);
		
		//Check joysticks
		
		
		if (drive) {
			robot.lift.stopUpper();
			if ((inputLSY > kGHOST || inputLSY < -kGHOST) && (inputRSX > kGHOST || inputRSX < -kGHOST)) {
				robot.drive.bank(inputLSY, inputRSX);
				SmartDashboard.putString("DriveState", "Bank");
			}
			else if (inputLSY > kGHOST || inputLSY < -kGHOST) {
				robot.drive.forback(inputLSY);
				SmartDashboard.putString("Drive", "Forback");
			}
			else if (inputRSX > kGHOST || inputRSX < -kGHOST) {
				SmartDashboard.putString("Drive", "Rotate");
				robot.drive.rotate(inputRSX*.8);
			}
			else {
				robot.drive.stop();
				SmartDashboard.putString("Drive", "Stop");
			}
		} else {
			robot.drive.stop();
			
			if (inputLSY > kGHOST || inputLSY < -kGHOST) robot.lift.manualLower(inputLSY);
			else robot.lift.stopLower();
			
			if (inputRSY > kGHOST || inputRSY < -kGHOST) robot.lift.manualUpper(inputRSY);
			else robot.lift.stopUpper();
			
		}
		SmartDashboard.putBoolean("Drive", drive);
	}
	
	public void test() {
		updateController();
		
//		robot.dart.extend(inputLSY);
	}
}
