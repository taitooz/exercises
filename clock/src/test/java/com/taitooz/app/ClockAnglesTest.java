package com.taitooz.app;

import junit.framework.Test;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for ClockAngles class.
 */
public class ClockAnglesTest extends TestCase {

    public ClockAngles calculator = new ClockAngles();

    public void testCreateClockAngles()  {
      assertNotNull(calculator);
    }

    public void testNinetyAngles() {
      double angle;
      angle = ClockAngles.getAngle(3,0,0);
      assertEquals(90D,angle);
      angle = ClockAngles.getAngle(12,15,0);
      assertEquals(90D,angle);
      angle = ClockAngles.getAngle(3,30,0);
      assertEquals(90D,angle);
      angle = ClockAngles.getAngle(6,15,0);
      assertEquals(90D,angle);
      angle = ClockAngles.getAngle(6,45,0);
      assertEquals(90D,angle);
      /*For recreative purposes only. Because an analog clock don't have 15
      hours. Only digital clocks can do that. :P. The example of the paper
      is wrong*/
      angle = ClockAngles.getAngle(15,0,0);
      assertEquals(90D,angle);
    }

    public void testLimits() {
      double angle;
      angle = ClockAngles.getAngle(12,30,0);
      assertEquals(180D,angle);
      angle = ClockAngles.getAngle(6,0,0);
      assertEquals(180D,angle);
      angle = ClockAngles.getAngle(0,0,0);
      assertEquals(0D,angle);
    }

    public void testOClocks() {
      double angle;
      angle = ClockAngles.getAngle(1,0,0);
      assertEquals(30D,angle);
      angle = ClockAngles.getAngle(2,0,0);
      assertEquals(60D,angle);
      angle = ClockAngles.getAngle(3,0,0);
      assertEquals(90D,angle);
      angle = ClockAngles.getAngle(4,0,0);
      assertEquals(120D,angle);
      angle = ClockAngles.getAngle(5,0,0);
      assertEquals(150D,angle);
      angle = ClockAngles.getAngle(6,0,0);
      assertEquals(180D,angle);
      angle = ClockAngles.getAngle(7,0,0);
      assertEquals(150D,angle);
      angle = ClockAngles.getAngle(8,0,0);
      assertEquals(120D,angle);
      angle = ClockAngles.getAngle(9,0,0);
      assertEquals(90D,angle);
      angle = ClockAngles.getAngle(10,0,0);
      assertEquals(60D,angle);
      angle = ClockAngles.getAngle(11,0,0);
      assertEquals(30D,angle);
      angle = ClockAngles.getAngle(12,0,0);
      assertEquals(0D,angle);
    }
}
