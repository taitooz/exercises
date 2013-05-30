package com.taitooz.app;

import java.util.Observable;

//Time Model
public class TimeModel extends Observable {
  private int sec = 0;
  private int tSec = 0;
  private int min = 0;
  private int hour = 0;
  //@ invariant sec >= 0 && sec <= 59;
  //@ invariant min >= 0 && min <= 59;
  //@ invariant hour >= 0 && hour <= 23;
  private String angle = "00:00:00";
  private ClockAngles clockAngles = new ClockAngles();

  public void increaseTime() {
    doPreTime();
    if (sec < 59) {
      if (tSec == 0)
        tSec++;
      else {
        sec++;
        tSec = 0;
      }
    } else {
      sec = 0;

      if (min < 59) {
        min++;
      } else {
        min = 0;

        if (hour < 23) {
          hour++;
        } else {
          hour = 0;
        }
      }
    }
    updateObserver();
  }

  public int getHour() {
    return hour;
  }

  public int getMin() {
    return min;
  }

  public int getSec() {
    return sec;
  }

  //@ requires h >= 0 && h <= 12;
  public void setHour(int h) {
    hour = h;
  }

  //@ requires m >= 0 && m <= 59;
  public void setMin(int m) {
    min = m;
  }

  //@ requires s >= 0 && s <= 59;
  public void setSec(int s) {
    sec = s;
  }

  //@ requires h >= 0 && h <= 12;
  //@ requires m >= 0 && m <= 59;
  //@ requires s >= 0 && s <= 59;
  public void setTime(int h, int m, int s) {
    setHour(h);
    setMin(m);
    setSec(s);
    updateObserver();
  }

  public void updateObserver() {
    setChanged();
    notifyObservers();
  }

  public String doString() {
    return clockAngles.getAngle(hour, min, sec) + "";
  }

  public void doPreTime() {
    angle = doString();
  }

  public String getAngle() {
    return angle;
  }
}