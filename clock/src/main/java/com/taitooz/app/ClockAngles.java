package com.taitooz.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClockAngles {

  /**This method evaluates the angle. All angles be acute angles
   * or in a limit Straight Angle.**/
  public static double getRealAngle(int iHours, int iMinutes, int iSeconds) {
    double dHoursAngle = 360 / 12;
    double dMinutesAngle = dHoursAngle / 5;
    double dSecondsAngle = dMinutesAngle /360;
    double dReturnAngle = 0;
    dSecondsAngle = dSecondsAngle * iSeconds;
    dMinutesAngle = dMinutesAngle * iMinutes;
    dMinutesAngle = dMinutesAngle + dSecondsAngle;
    dHoursAngle = dHoursAngle * iHours;
    dReturnAngle = dHoursAngle - dMinutesAngle;
    if (dReturnAngle > 180) {
      dReturnAngle = 360 - dReturnAngle;
    }
    return dReturnAngle;
  }

  /**This method retrieve the angle from a especific time.
   * @param iHours the hours 0-12
   * @param iMinutes the minutes 0-59
   * @param iSeconds the seconds 0-59
   * @return the absolute value of the angle.
   */
  public static double getAngle(int iHours, int iMinutes, int iSeconds) {
    return  Math.abs(getRealAngle(iHours,iMinutes,iSeconds));
  }

  /**This methos converts to radian the angle. Not used.
   * @param theReturnAngle is the angle to convert.
   **/
  public static double getRadians(final double theReturnAngle) {
    return theReturnAngle*(Math.PI/180);
  }
  
  /**This method read the input from console.**/
  private static InputResult readInput() throws Exception {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
     int iParsedHour = 0;
    int iParsedMinutes = 0;
    int iParsedSeconds = 0;
    try {
      System.out.println("Enter the hours :- ");
      String inputHours = br.readLine();
      iParsedHour = Integer.parseInt(inputHours);
      if (iParsedHour < 1 || iParsedHour > 12) {
        throw new Exception();
      }
      System.out.println("Enter the minutes :- ");
      String inputMinutes = br.readLine();
      iParsedMinutes = Integer.parseInt(inputMinutes);
      if (iParsedMinutes < 0 || iParsedMinutes > 59) {  
        throw new Exception();
      }
      System.out.println("Enter the seconds :- ");
      String inputSeconds = br.readLine();
      iParsedSeconds = Integer.parseInt(inputSeconds);
      if (iParsedSeconds < 0 || iParsedSeconds > 59) {
        throw new Exception();
      }
    }catch (IOException e) {
      System.out.println("Wrong input!!");
      System.out.println("********************************");
    }
    InputResult cResult =  new InputResult(iParsedHour, iParsedMinutes,
        iParsedSeconds);
    return cResult;
  }

  /**Print basic explain text. Read the input from console.**/
  public static void run() throws Exception {
    InputResult cResult = readInput();
    String sAngleResult = getAngle(cResult.hours, cResult.minutes,
        cResult.seconds) + "";
    System.out.println("################################");
    System.out.println("THE ANGLE FOR: " + cResult.hours + ":" +
        cResult.minutes + ":" + cResult.seconds + "   IS -> " + sAngleResult);
  }

  /**You can test the functionality running this class.**/
  public static void main(String[] args) {
    try {
      run();
    }
    catch (Exception e) {
      System.out.println("Wrong input!! please run me again!!"); 
      System.out.println("I am an analog clock!!");
      System.out.println("remember this ( hours 1-12 : minutes 0-59 : "
          + "seconds 0-59 )");

      System.out.println("********************************");
    }
  }

  /**
   * A basic data object to hold the input.
   */
  static class InputResult {
    public int hours;
    public int minutes;
    public int seconds;

    public InputResult (int iHours, int iMinutes, int iSeconds) {
      hours = iHours;
      minutes = iMinutes;
      seconds = iSeconds;
    }
  }

}
