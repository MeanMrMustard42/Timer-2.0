package Package;

import java.lang.Thread;

public class Timer extends Thread {
	public static volatile int x = 0;
	public volatile boolean isRunning;
	private static String str;
	private volatile int delayValue = 1000;
	private volatile int totalMinutes = 0;
	private volatile int totalHours = 0;
	private volatile int totalDays = 0;
	private volatile int totalWeeks = 0;
	private volatile int totalYears = 0; // I'm sorry, God
	private volatile int mins = 0;
	private volatile int hrs = 0;
	private volatile int days = 0;
	private volatile int wks = 0;
	private volatile int yrs = 0;
	private volatile int secs = 0;

	/**
	 * The Timer class counts either up or down one integer per second in the
	 * background of any given program. Users can also set the timer to any
	 * value at any time. Time is counted both linearly and separately in years,
	 * weeks, days, hours, minutes, and seconds. Currently does not account for
	 * leap days/leap seconds because that would require more work. See specific
	 * method documentation for more info.
	 * 
	 * @author unimportant
	 * @version 1.1
	 * @since 2017-4-8, JDK 8
	 */

	/**
	 * Counts up from set number (default is 0) one second at a time until stop
	 * command is true or max value is reached.
	 * 
	 * @throws InterruptedException
	 */

	public Timer(String str2) {
		str = str2;

	}

	@Override
	public void run() {
		if (str.equals("increment")) {
			try {
				isRunning = true;
				increment();
			} catch (InterruptedException e) {
				System.out.print("Error: Thread interrupted. Timer unexpectedly disabled. ");
				e.printStackTrace();
			}
		} else if (str.toLowerCase().equals("decrement")) {
			try {
				isRunning = true;
				decrement();
			} catch (InterruptedException e) {
				System.out.print("Error: Thread interrupted. Timer unexpectedly disabled. ");
				e.printStackTrace();
			}
		}

	}

	private void updateTotalTime() {
		if (x == 60 || x % 60 == 0) {
			++totalMinutes;
		}
		if (totalMinutes == 60 || totalMinutes % 60 == 0) {
			++totalHours;
		}
		if (totalHours == 24 || totalHours % 24 == 0) {
			++totalDays;
		}
		if (totalDays == 7 || totalDays % 7 == 0) {
			++totalWeeks;
		}
		if (totalWeeks == 52 || totalWeeks % 52 == 0) {
			++totalYears;
		}
	}

	private void updateLinearTime() {
		if (secs == 60) {
			++mins;
			secs = 0; // my life story
		}
		if (mins == 60) {
			++hrs;
			mins = 0;
		}
		if (hrs == 24) {
			++days;
			hrs = 0;
		}
		if (days == 7) {
			++wks;
			days = 0;
		}
		if (wks == 52) {
			++yrs;
			wks = 0;
		}

	}

	private void increment() throws InterruptedException {
		while (x < Integer.MAX_VALUE && isRunning) {
			++x;
			++secs;
			updateTotalTime();
			updateLinearTime();
			System.out.println(x);
			Thread.sleep(delayValue);

		}

		// while(!isRunning) {
		// if(isRunning) {
		// increment();
		// }
	}

	/**
	 * Counts down from set number (default is 0) one second at a time until
	 * stop command is true or min value is reached. Users will have to use the
	 * preset the timer using the set() function in order to avoid the counter
	 * reaching the negatives.
	 * 
	 * @throws InterruptedException
	 */
	private void decrement() throws InterruptedException {
		while (x > Integer.MIN_VALUE && isRunning) {
			--x;
			updateTotalTime();
			updateLinearTime();
			// totalDays/totalHours/min could return negative if values are not
			// pre-set with set()
			// It's not a bug, it's a feature. I swear.
			Thread.sleep(delayValue);
		}
	}

	/**
	 * Sets boolean to true which disables timer. However timer value will
	 * remain the same.
	 */
	public void disable() {
		delayValue = Integer.MAX_VALUE; // I promise this'll be fixed someday
		System.out.println("Timer disabled successfully");

	}

	/**
	 * Enables timer by setting isRunning to true. Directly calling
	 * timer.start() again would only create an IllegalThreadStateException.
	 */
	public void enable() {
		delayValue = 1000; // this will also be eventually fixed
		System.out.println("Timer enabled successfully. I don't think this'll work though.");
	}

	/**
	 * Resets timer to it's default value of 0, does not stop timer however
	 */
	public void reset() {
		x = 0;
	}

	/**
	 * delays timer by desired amount of milliseconds before returning to
	 * 1-second default
	 * 
	 * @param millis
	 * @throws InterruptedException
	 */
	public void delay(int millis) throws InterruptedException {
		delayValue = millis;
		Thread.sleep(1000);
		delayValue = 1000;
	}

/// ****************************Getters/Setters for time values************************\\\
	/*
	 * TO BE CLEAR: Total(x) values return the total amount of time in that time
	 * form ex: getTotalHours may return 48, while getHrs() will return 0
	 * (because it has already hit 24 hours at that point). getX returns amount
	 * of time in linear form (ex. 1 day, 3 hours, and 2 mins). This makes sense
	 * trust me
	 */
	/**
	 * @return the x
	 */
	public static int get() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public static void set(int x) {
		Timer.x = x;
	}

	/**
	 * @return the count direction (increment or decrement)
	 */
	public static String getCountDirection() {
		return str;
	}

	/**
	 * @param the
	 *            specified count direction ("increment" or "decrement").
	 */
	public static void setCountDirection(String str) {
		Timer.str = str;
	}

	/**
	 * @return the delayValue
	 */
	public int getDelayValue() {
		return delayValue;
	}

	/**
	 * @param delayValue
	 *            the delayValue to set
	 */
	public void setDelayValue(int delayValue) {
		this.delayValue = delayValue;
	}

	/**
	 * @return the totalMinutes
	 */
	public int getTotalMinutes() {
		return totalMinutes;
	}

	/**
	 * @param totalMinutes
	 *            the totalMinutes to set
	 */
	public void setTotalMinutes(int totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	/**
	 * @return the totalHours
	 */
	public int getTotalHours() {
		return totalHours;
	}

	/**
	 * @param totalHours
	 *            the totalHours to set
	 */
	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	/**
	 * @return the totalDays
	 */
	public int getTotalDays() {
		return totalDays;
	}

	/**
	 * @param totalDays
	 *            the totalDays to set
	 */
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * @return the totalWeeks
	 */
	public int getTotalWeeks() {
		return totalWeeks;
	}

	/**
	 * @param totalWeeks
	 *            the totalWeeks to set
	 */
	public void setTotalWeeks(int totalWeeks) {
		this.totalWeeks = totalWeeks;
	}

	/**
	 * @return the totalYears
	 */
	public int getTotalYears() {
		return totalYears;
	}

	/**
	 * @param totalYears
	 *            the totalYears to set
	 */
	public void setTotalYears(int totalYears) {
		this.totalYears = totalYears;
	}

	/**
	 * @return the mins
	 */
	public int getMinutes() {
		return mins;
	}

	/**
	 * @param mins
	 *            the mins to set
	 */
	public void setMinutes(int mins) {
		this.mins = mins;
	}

	/**
	 * @return the hrs
	 */
	public int getHours() {
		return hrs;
	}

	/**
	 * @param hrs
	 *            the hrs to set
	 */
	public void setHours(int hrs) {
		this.hrs = hrs;
	}

	/**
	 * @return the days
	 */
	public int getDays() { // Automation is beautiful
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * @return the wks
	 */
	public int getWeeks() {
		return wks;
	}

	/**
	 * @param wks
	 *            the wks to set
	 */
	public void setWeeks(int wks) {
		this.wks = wks;
	}

	/**
	 * @return the yrs
	 */
	public int getYears() {
		return yrs;
	}

	/**
	 * @param yrs
	 *            the yrs to set
	 */
	public void setYears(int yrs) {
		this.yrs = yrs;
	}

	/**
	 * @return the secs
	 */
	public int getSecs() { // huehuehue
		return secs;
	}

	/**
	 * @param secs
	 *            the secs to set
	 */
	public void setSecs(int secs) {
		this.secs = secs;
	}
	
	public String getTotalTime() {
		return "Timer has run for " + totalYears + " years, or " + totalWeeks + " weeks, " + totalDays + "days, or " + totalHours + " hours, " + totalMinutes
				+ " minutes, or " + x + " seconds in total.";  
	}

/// ***********************************************************************************\\\

	public String toString() {
		return "Timer has run for " + yrs + " years, " + wks + " weeks, " + days + " days, " + hrs + " hours, " + mins
				+ " minutes, and " + secs + " seconds.";

	}
}


// dank kush
