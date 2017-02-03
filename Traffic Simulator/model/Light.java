package model;
 
import java.util.ArrayList;
/*     */ import java.util.List;
 
public class Light implements CarHandler{
   private LightColor _lightColor;
   private double length;
   private int xposition;
   private int yposition;
   private boolean trafficDirection;
   private CarHandlerList observer;
   private boolean _state;
   private Queue<Car> carQueue = new Queue<Car>();
   private List<Car> _cars = new ArrayList<Car>();
   
   Light(double l) { this.length = l; }
   
   public void run(double time, boolean ewLightState, LightColor ewLightColor)
   {
     if (!this.carQueue.isEmpty()) {
       moveWaitingCarsAcross();
     }
     this._state = ewLightState;
     this._lightColor = ewLightColor;
   }
 
   public boolean getState()
   {
     return this._state;
   }
 
   public void accept(Car d)
   {
     Model model = this.observer.getObserver();
     
     d.addObserver(this);
     this._cars.add(d);
     model.getAgents().remove(d);
     this.carQueue.enqueue(d);
     
     moveWaitingCarsAcross();
   }
   
   private void moveWaitingCarsAcross() {
     Model model = this.observer.getObserver();
     List<Car> nextRoadTraffic = this.observer.getNext(this).getCars();
     
     if (!nextRoadTraffic.isEmpty())
     {
       Car nextCar = (Car)nextRoadTraffic.get(nextRoadTraffic.size() - 1);
       
       double distance = nextCar.getPosition() - nextCar.getCarLength();
       
       if (distance > MP.maxCarLength)
       {
         this._cars.remove(this.carQueue.peek());
         model.getAgents().add(this.carQueue.peek());
         this.observer.getNext(this).accept((Car)this.carQueue.dequeue());
       }
       
     }
     else
     {
       this._cars.remove(this.carQueue.peek());
       model.getAgents().add(this.carQueue.peek());
       this.observer.getNext(this).accept((Car)this.carQueue.dequeue());
     }
   }
 
   public LightColor getColor()
   {
     return this._lightColor;
   }
 
   public void addObserver(CarHandlerList carhandlerlist)
   {
     this.observer = carhandlerlist;
   }
   
   public List<Car> getCars()
   {
     return this._cars;
   }
   
   public boolean getDirect() {
     return this.trafficDirection;
   }
 
   public double getDistancetoNextObstacle(Car car)
   {
     if ((this._cars.size() > 1) && (this._cars.indexOf(car) != 0))
     {
 
       Car comparingCar = (Car)this._cars.get(this._cars.indexOf(car) - 1);
       
       return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
     }
 
     if (!this.observer.getNext(this).getCars().isEmpty()) {
       return ((Car)this.observer.getNext(this).getCars().get(0)).getPosition() + MP.roadLength - car.getPosition() - car.getCarLength();
     }
     return car.getBreakDistance() + 0.1D;
   }
   
   public CarHandlerList getObservingCarHandlerList() { return this.observer; }
   
   public int getXPos()
   {
     return this.xposition;
   }
   
   public int getYPos() {
     return this.yposition;
   }
   
   public int horCompareTo(CarHandler that) {
    if (this.xposition < that.getXPos()) {
       return -1;
     }
     if (this.xposition > that.getXPos()) {
       return 1;
     }
     return 0;
   }
   
   public void remove(Car d) {
     if (d == null) throw new IllegalArgumentException();
     this._cars.remove(d);
     d.removeObserver();
   }
   
   public void removeObserver()
   {
     this.observer = null;
   }
 
   public int vertCompareTo(CarHandler that)
   {
     if (this.yposition < that.getYPos()) {
       return -1;
     }
     if (this.yposition > that.getYPos()) {
       return 1;
     }
     return 0;
   }
   
   public double getLength() {
     return this.length;
   }
}
