package model;

import junit.framework.TestCase;

public class CarTest extends TestCase
 {
   public CarTest(String name) {
     super(name);
   }
   
   @SuppressWarnings("deprecation")
public void testCarCreation()
   {
     Car c = new Car();
     junit.framework.Assert.assertEquals(Double.valueOf(0.0D), Double.valueOf(c.getPosition()));
     junit.framework.Assert.assertEquals(Double.valueOf(c.getVelocity()), Double.valueOf(c.getPosition() + c.getVelocity()));
   }
}