package model;

public class Intersection implements Agent{
   private Light ewLight;
   private Light nsLight;
   private double length = Math.random() * (MP.maxIntersectionLength - MP.minIntersectionLength) + MP.minIntersectionLength;
   
   private double greenDurationNS = Math.random() * (MP.maxGreenLightTime - MP.minGreenLightTime) + MP.minGreenLightTime;
   private double yellowDurationNS = Math.random() * (MP.maxYellowLightTime - MP.minYellowLightTime) + MP.minYellowLightTime;
   private double greenDurationEW = Math.random() * (MP.maxGreenLightTime - MP.minGreenLightTime) + MP.minGreenLightTime;
   private double yellowDurationEW = Math.random() * (MP.maxYellowLightTime - MP.minYellowLightTime) + MP.minYellowLightTime;
   
   public Intersection()
   {
     this.ewLight = new Light(this.length);
     this.nsLight = new Light(this.length);
   }
   
   public Intersection(Light x)
   {
     this.ewLight = x;
     this.nsLight = new Light(this.length);
   }
 
   public void run(double time)
   {
     double whereInCycle = time % (this.greenDurationNS + this.yellowDurationNS + this.greenDurationEW + this.yellowDurationEW);
 
     LightColor ewLightColor = LightColor.GREEN;
     LightColor nsLightColor = LightColor.RED;
     boolean ewLightState;
	 boolean nsLightState;
			    
	 if (whereInCycle <= this.greenDurationEW + this.yellowDurationEW)
     {
       ewLightState = true;
       nsLightState = !ewLightState;
       
       if (whereInCycle <= this.greenDurationEW) {
         ewLightColor = LightColor.GREEN;
       }
       else {
         ewLightColor = LightColor.YELLOW;
       }
       
       nsLightColor = LightColor.RED;
 
     }
     else
     {
       ewLightState = false;
       nsLightState = !ewLightState;
       
       if (whereInCycle - (this.greenDurationEW + this.yellowDurationEW) <= this.greenDurationNS) {
         nsLightColor = LightColor.GREEN;
       }
       else {
         nsLightColor = LightColor.YELLOW;
       }
 
       ewLightColor = LightColor.RED;
     }
     
     if ((!this.ewLight.getCars().isEmpty()) || (!this.nsLight.getCars().isEmpty()))
     {
       ewLightState = false;
       nsLightState = false;
     }
     
     this.ewLight.run(time, ewLightState, ewLightColor);
     this.nsLight.run(time, nsLightState, nsLightColor);
   }
 
   public Light getEWLight()
   {
     return this.ewLight;
   }
   
   public Light getNSLight()
   {
     return this.nsLight;
   }
}