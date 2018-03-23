package org.usfirst.frc.team5667.robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import org.usfirst.frc.team5667.robot.Lift.State;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class XboxController extends Joystick {
	Robot robot; //Robot
	
	//Controller values
	private double inputRSX, inputRSY; //Right joystick axis values
	private double inputLSX, inputLSY; //Left joystick axis values
	private double inputRT, inputLT; //Trigger values
	private boolean inputRB, inputLB; //Bumper states
	public boolean inputA, inputB, inputX, inputY, inputMenu, inputStart; //Button states
	private Object[] controls = {inputLSX, inputLSY, inputRSX, inputRSY, inputRT, inputLT, inputRB, inputLB, 
			inputA, inputB, inputX, inputY, inputMenu, inputStart};
	private boolean drive;
	private final double kGHOST = .1; //Threshold for blocking ghost signals
	private final double tStep = .001;
	
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
	public void refreshController() {
		inputRSX = super.getRawAxis(4);//Babytrainingwheels
		inputRSY = -super.getRawAxis(5);
		inputLSX = super.getRawAxis(0);
		inputLSY = -super.getRawAxis(1);//babytrainingwheels
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
		    
		    while (inputRB) refreshController();
		    
		    while (line != null) {
		    	int prev = -1;
		    	int curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLSX = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLSY = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRSX = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRSY = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRT = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLT = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRB = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLB = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputA = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputB = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputX = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputY = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputMenu = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputStart = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	System.out.println("Done writing");
		    	
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
	
	public void recall_2(String command) {

		System.out.println("Starting recall...");
		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/lvuser/" + command + ".txt"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    

		    while (inputRB) refreshController();
		    
		    Timer timer = new Timer();
		    while (line != null) {
		    	int prev = -1;
		    	int curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLSX = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLSY = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRSX = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRSY = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRT = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLT = Double.parseDouble(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputRB = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputLB = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputA = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputB = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputX = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputY = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputMenu = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	prev = curr;
		    	curr = line.indexOf(",", prev+1);
		    	inputStart = Boolean.parseBoolean(line.substring(prev+1, curr));
		    	double time = Double.parseDouble(line.substring(line.indexOf("=")+1));
		    	while (timer.getElapsed() - time < -.01);
		    	
		    	if (timer.getElapsed() - time >= 0 && timer.getElapsed() - time <= .01) {
			    	enableController();
		    	}
		    			    	
				sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
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
			
			while (inputLB) refreshController();
			
			for (double t = 0; t != -1; t+=tStep) {
				t = Math.round(t*100)/100.0;
				System.out.println(t);
				refreshController();
				if (inputRB) {
					break;
				}
				
				System.out.print(inputLSX + ", ");
				writer.write(inputLSX + ",");
				System.out.print(inputLSY + ", ");
				writer.write(inputLSY + ",");
				System.out.print(inputRSX + ", ");
				writer.write(inputRSX + ",");
				System.out.print(inputRSY + ", ");
				writer.write(inputRSY + ",");
				System.out.print(inputRT + ", ");
				writer.write(inputRT + ",");
				System.out.print(inputLT + ", ");
				writer.write(inputLT + ",");
				System.out.print(inputRB + ", ");
				writer.write(inputRB + ",");
				System.out.print(inputLB + ", ");
				writer.write(inputLB + ",");
				System.out.print(inputA + ", ");
				writer.write(inputA + ",");
				System.out.print(inputB + ", ");
				writer.write(inputB + ",");
				System.out.print(inputX + ", ");
				writer.write(inputX + ",");
				System.out.print(inputY + ", ");
				writer.write(inputY + ",");
				System.out.print(inputMenu + ", ");
				writer.write(inputMenu + ",");
				System.out.print(inputStart + ", ");
				writer.write(inputStart + ",");
				
				enableController();
//				if (inputLSY > kGHOST || inputLSY < -kGHOST) robot.lift.manualLower(inputLSY);
//				else robot.lift.stopLower();
//				
//				if (inputRSY > kGHOST || inputRSY < -kGHOST) robot.lift.manualUpper(inputRSY);
//				else robot.lift.stopUpper();
				Timer.delay(tStep);				
				
				//	System.out.println();
				writer.write("\n");
				
			} 
			writer.close();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("Done copying.");

		while (inputRB) refreshController();
		
//		System.out.println("Lower: " + lower.toString());
//		System.out.println("Upper: " + upper.toString());
	}
	
	public void copycat_2(String command) {
		System.out.println("Starting...");
//		double stop = input;
		
		try {
			File file = new File("/home/lvuser/"+command+".txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			
			while (inputLB) refreshController();
			Timer timer = new Timer();
			
			while (true) {
				refreshController();
				if (inputRB) {
					break;
				}
				
				System.out.print(inputLSX + ", ");
				writer.write(inputLSX + ",");
				System.out.print(inputLSY + ", ");
				writer.write(inputLSY + ",");
				System.out.print(inputRSX + ", ");
				writer.write(inputRSX + ",");
				System.out.print(inputRSY + ", ");
				writer.write(inputRSY + ",");
				System.out.print(inputRT + ", ");
				writer.write(inputRT + ",");
				System.out.print(inputLT + ", ");
				writer.write(inputLT + ",");
				System.out.print(inputRB + ", ");
				writer.write(inputRB + ",");
				System.out.print(inputLB + ", ");
				writer.write(inputLB + ",");
				System.out.print(inputA + ", ");
				writer.write(inputA + ",");
				System.out.print(inputB + ", ");
				writer.write(inputB + ",");
				System.out.print(inputX + ", ");
				writer.write(inputX + ",");
				System.out.print(inputY + ", ");
				writer.write(inputY + ",");
				System.out.print(inputMenu + ", ");
				writer.write(inputMenu + ",");
				System.out.print(inputStart + ", ");
				writer.write(inputStart + ",");
				writer.write("t=" + timer.getElapsed() + "\n");
				
				enableController();
				
				//	System.out.println();
				
			} 
			writer.close();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("Done copying.");

		while (inputRB) refreshController();
	}
	
	/**
	 * Executes robot commands based off the values of the controller.
	 */
	public void enableController() {
		//Check the values of the controller
		//hi deetz
		 // Hey Deetz I'm typing this on an Xbox Controller! 
		//B is ground/start
		//A is claw
		//X is switch
		//Y is scale
		//Check buttons
		if (inputA) {
			if (robot.claw.open) {
				SmartDashboard.putString("Claw", "OPEN");
			} else {
				SmartDashboard.putString("Claw", "CLOSED");
			}
			robot.claw.toggle();
			while (inputA) refreshController();
		} else if (inputB) {
			if (robot.lift.state == State.START) {
				robot.lift.start_ground();
				robot.lift.state = State.GROUND;
			} else if (robot.lift.state == State.GROUND) {
				robot.lift.ground_start();
				robot.lift.state = State.START;
			} else {
				System.out.println("Invalid position: " + robot.lift.state);
			}
			while (inputB) refreshController();
		} else if (inputX) {
			if (robot.lift.state == State.START) {
				robot.lift.start_switch();
				robot.lift.state = State.SWITCH;
			} else if (robot.lift.state == State.SWITCH) {
				robot.lift.switch_start();
				robot.lift.state = State.START;
			} else if (robot.lift.state == State.GROUND) {
				robot.lift.ground_start();
				robot.lift.state = State.START;
				robot.lift.start_switch();
				robot.lift.state = State.SWITCH;				
			} else {
				System.out.println("Invalid position: " + robot.lift.state);
			}
			
			while (inputX) refreshController();
		} else if (inputY) {
			if (robot.lift.state == State.START) {
				robot.lift.start_scale();
				robot.lift.state = State.SCALE;
			} else if (robot.lift.state == State.SCALE) {
				robot.lift.scale_start();
				robot.lift.state = State.START;
			} else {
				System.out.println("Invalid position: " + robot.lift.state);
			}
			while (inputY) refreshController();
		} else if (inputMenu) {
			
			while (inputMenu) refreshController();
		} else if (inputStart) {
			while (inputStart) refreshController();
			Timer timer = new Timer(1);
			while (!timer.isDone()) {
				refreshController();
				if (inputStart) {
					drive = !drive;
					break;
				}
			}
			while (inputStart) refreshController();
		} else if (inputLB) {
			copycat("temp");
			while (inputLB) refreshController();
		} else if (inputRB) {
//			recall("temp");
			while (inputRB) refreshController();
		} else {
			
		}

		if (robot.xbox.inputMenu) {
			robot.lift.state = State.START;
		}
		
//		robot.dart.set(inputLSY);
		
		//Check joysticks
		
		
		if (drive) {
			if (robot.lift.state == robot.lift.state.SCALE || robot.lift.state == robot.lift.state.SWITCH) {
				inputLSY*=.5;
			}
			
			
			robot.lift.stopUpper();
			
			if (Math.abs(inputLSY) < kGHOST && Math.abs(inputRSX) < kGHOST) {
				robot.drive.bank(inputLSY, inputRSX);
			}
			else if (inputLSY > kGHOST || inputLSY < -kGHOST) {
				robot.drive.forback(inputLSY);
			}
			else if (inputRSX > kGHOST || inputRSX < -kGHOST) {
				robot.drive.rotate(inputRSX*.5);
			} else if (inputRT > kGHOST) {
				robot.drive.rotate(inputRT*.5);
			} else if (inputLT > kGHOST) {
				robot.drive.rotate(-inputLT*.5);
			} else {
				robot.drive.stop();
			}
		} else {
			robot.drive.stop();
			
			if (inputLSY > kGHOST || inputLSY < -kGHOST) robot.lift.manualLower(inputLSY);
			else robot.lift.stopLower();
			
			if (inputRSY > kGHOST || inputRSY < -kGHOST) robot.lift.manualUpper(inputRSY);
			else robot.lift.stopUpper();
			
		}
	}
	
	public void enableDrive(double trainingWheels) {
		
		inputLSY*=trainingWheels;
		inputRSX*=trainingWheels;
		
		if ((inputLSY > kGHOST || inputLSY < -kGHOST) && (inputRSX > kGHOST || inputRSX < -kGHOST)) {
			robot.drive.bank(inputLSY, inputRSX);
		}
		else if (inputLSY > kGHOST || inputLSY < -kGHOST) {
			robot.drive.forback(inputLSY);
		}
		else if (inputRSX > kGHOST || inputRSX < -kGHOST) {
			robot.drive.rotate(inputRSX*.5);
		} else if (inputRT > kGHOST) {
			robot.drive.rotate(inputRT*.5);
		} else if (inputLT > kGHOST) {
			robot.drive.rotate(-inputLT*.5);
		} else {
			robot.drive.stop();
		}
	}
	
	public void test() {
		refreshController();
		
//		robot.dart.extend(inputLSY);
	}
}
