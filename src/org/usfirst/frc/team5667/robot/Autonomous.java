package org.usfirst.frc.team5667.robot;

public class Autonomous {
	
	private Robot robot; //Robot subsystems
	private int position; //Starting position of the robot
	
	public Autonomous(Robot robot, int position) {
		this.robot = robot;
		this.position = position;
		
	}
	
	public void placeSwitch() {
		
		switch (position) {
			case 0 :
				
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default :
		}
		
		//Position 0 code:
		
			//Forward 176"
		
			//Raise lift 22"
		
			//Check encoders and stop subsystems accordingly
			boolean done = true;
			while (!done) {
	//			if (blah) {
	//				done = true;
	//			}
			}
			
			//Rotate 90
		
			//Forward XX inches
			
			//Drop cube
			
		//Position 1 code:
			
			//
			
		//Position 3 code:
			
			//Go straight 136"
			
			//Raise lift 22"
			
			//Forward XX inches
			
			//Drop cube
			
			//Rotate -90
			
			//Lower lift
			
		//Position 4 code:
			
			////Forward 176"
		
			//Raise lift 22"
		
			//Check encoders and stop subsystems accordingly
//			boolean done = true;
//			while (!done) {
	//			if (blah) {
	//				done = true;
	//			}
//			}
			
			//Rotate -90
		
			//Forward XX inches
			
			//Drop cube
	}
	
	public void placeScale() {
		
		//Position 0
		
			if (robot.scale == 'l') {
				//Scale L (Anns)
				//rotate 90 degrees
				//raise claw to 70 inches above ground
				//go forward some amount, but get claw about.
				//...7.5 inches over the scale
				//release claw
				//go back same amount
				//lower claw to back to normal


				
			} else {
				//Scale R (Kirthana, Ashka)
				//forward 16 ft
				//raise claw 5ft 8 inches
				//rotate -90 degrees
				//forward 16ft
				//rotate -90 degrees
				//Forward 5 ft
				//Rotate -90 degrees
				//drop cube

			}
		
		//Position 4
		
			if (robot.scale == 'l') {
				//Scale L (Evan)
				//go forward 308 inches
                // turn pi/2 radians
                // raise claw 70 inches above ground(need to find how much that is)
                // scoot forward
                //release claw
                // go back same amount 
                //lower claw

				
			} else {
				//Scale R (Pranav)
				//go forward 308 inches
				//turn -pi/2 radians
				//raise claw to 70 inches above ground
				//go forward some amount, but get claw about...      
				//...7.5 inches over the scale
				//	release claw
				//go back same amount
				//lower claw to back to normal

			}
	}
	
}
