package org.usfirst.frc.team5667.robot;

public class Timer {
	private long initialTime;
	private long currentTime;
	private int elapsedTime;
	private int finalTime;
	
	public Timer(double seconds) {
		initialTime = System.currentTimeMillis();
		finalTime = (int)(seconds*1000);
	}
	
	//Timer.isDone();
	
	public Timer() {
		initialTime = System.currentTimeMillis();
	}

	public static void delay(double seconds) {
		long start = System.currentTimeMillis();
		int elapsed = 0;
		int end = (int)(seconds*1000);
		while (elapsed < end) {
			long current = System.currentTimeMillis();
			elapsed = (int) (current - start);
		}
	}
	
	public double getElapsed() {
		currentTime = System.currentTimeMillis();
		elapsedTime = (int) (currentTime - initialTime);
		return elapsedTime/1000.0;
	}
	
	public boolean isDone() {
		currentTime = System.currentTimeMillis();
		elapsedTime = (int) (currentTime - initialTime);
		if (elapsedTime > finalTime) {
			return true;
		} else {
			return false;
		}
	}
}
