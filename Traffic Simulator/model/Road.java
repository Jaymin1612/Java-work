package model;
 
import java.util.ArrayList;
import java.util.List;
 
public class Road implements CarHandler{
   private double length = Math.random() * (MP.maxroadLength - MP.minroadLength) + MP.minroadLength;
   
   private int xposition;
   
   private int yposition;
   private boolean trafficDirection;
   private CarHandlerList observer;
   
   Road(int x, int y, boolean direction)
   {
     this.xposition = x;
     this.yposition = y;
     this.trafficDirection = direction;
   }
 
	public Road() {
		// TODO Auto-generated constructor stub
	}

   private List<Car> _cars = new ArrayList<Car>();
   
   public void accept(Car d) { if (d == null) throw new IllegalArgumentException();
     this._cars.add(d);
     d.addObserver(this);
   }
   
   public void remove(Car d) {
     if (d == null) throw new IllegalArgumentException();
     this._cars.remove(d);
     d.removeObserver();
   }
   
   public List<Car> getCars() {
     return this._cars;
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
     if (this.yposition < that.yposition) {
       return -1;
     }
     if (this.yposition > that.yposition) {
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
     if ((this._cars.size() > 1) && (this._cars.indexOf(car) != 0))
     {
 
       Car comparingCar = (Car)this._cars.get(this._cars.indexOf(car) - 1);
       
       return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
     }
 
     if ((this.length - car.getPosition() - car.getCarLength() <= car.getBreakDistance()) && (!this.observer.getNext(this).getState()))
     {
 
       return this.length - car.getPosition() - car.getCarLength();
     }
     
     if (!this.observer.getNext(this.observer.getNext(this)).getCars().isEmpty()) {
       return ((Car)this.observer.getNext(this.observer.getNext(this)).getCars().get(0)).getPosition() + this.length - car.getPosition() - car.getCarLength();
     }
     return Double.POSITIVE_INFINITY;
   }
   
   public boolean getState()
   {
     return true;
   }
   
   public double getLength() {
     return this.length;
   }
}