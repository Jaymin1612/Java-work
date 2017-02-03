package model;
 
import java.util.ArrayList;
import java.util.List;
 
public class Sink implements CarHandler{
   private double length = 0.0D;
   
   private int xposition;
   private int yposition;
   private boolean trafficDirection;
   private CarHandlerList observer;
   private List<Car> _cars = new ArrayList<Car>();
 
   Sink(int x, int y, boolean direction)
   {
     this.xposition = x;
     this.yposition = y;
     this.trafficDirection = direction;
   }
 
   public void accept(Car d)
   {
     d.addObserver(this);
     remove(d);
   }
   
   public void remove(Car d) {
     if (d == null) { throw new IllegalArgumentException();
     }
     d.getOberver().getObservingCarHandlerList().getObserver().getAgents().remove(d);
   }
   
   public List<Car> getCars()
   {
     return this._cars;
   }
   
   public void update(Car car)
   {
     System.out.println("Update received from Subject, state changed to : " + car.getState());
   }
   
   public int getYPos() {
     return this.yposition;
   }
   
   public int getXPos() { return this.xposition; }
   
   public boolean getDirect() {
     return this.trafficDirection;
   }
   
   public int vertCompareTo(Road that)
   {
     if (this.yposition < that.getYPos()) {
       return -1;
     }
     if (this.yposition > that.getYPos()) {
       return 1;
     }
     return 0;
   }
   
   public int horCompareTo(CarHandler that)
   {
     if (this.xposition < that.getXPos()) {
       return -1;
     }
     if (this.xposition > that.getXPos()) {
       return 1;
     }
     return 0;
   }
   
   public void addObserver(CarHandlerList roadlist) {
     this.observer = roadlist;
   }
   
   public void removeObserver() {
     this.observer = null;
   }
   
   public CarHandlerList getObservingCarHandlerList() {
     return this.observer;
   }
   
   public int vertCompareTo(CarHandler that)
   {
     return 0;
   }
   
   public double getDistancetoNextObstacle(Car car)
   {
     return Double.POSITIVE_INFINITY;
   }
 
   public boolean getState()
   {
     return true;
   }

   public double getLength()
   {
     return this.length;
   }
}