 package model;
 import java.util.ArrayList;
 import java.util.List;
 
 public class Source
   implements CarHandler, Agent
 {
   private double generationInterval;
   private Queue<Car> carQueue = new Queue<Car>();
   
   private double length = 0.0D;
   
   private int xposition;
   private int yposition;
   private boolean trafficDirection;
   private CarHandlerList observer;
   private List<Car> _cars = new ArrayList<Car>();
   Source(int x, int y, boolean direction)
   {
     this.xposition = x;
     this.yposition = y;
     this.trafficDirection = direction;
     this.generationInterval = ((int)(MP.maxCarGeneration * Math.random()) + MP.minCarGeneration);
   }
   
   public void run(double time)
   {
     Model model = this.observer.getObserver();
     
 
     if (!this.carQueue.isEmpty()) {
       moveWaitingCarsAcross();
     }
     if ((time % this.generationInterval == 0.0D) && (this.carQueue.size() < 5))
     {
 
       Car x = new Car();
       this.observer.accept(x, model);
     }
   }
 
   public void accept(Car d)
   {
     d.addObserver(this);
     this.carQueue.enqueue(d);
     moveWaitingCarsAcross();
   }
 
   private void moveWaitingCarsAcross()
   {
     Model model = this.observer.getObserver();
     
 
     List<Car> nextRoadTraffic = this.observer.getNext(this).getCars();
     
     if (!nextRoadTraffic.isEmpty())
     {
       Car nextCar = (Car)nextRoadTraffic.get(nextRoadTraffic.size() - 1);
       
       double distance = nextCar.getPosition();
       
       if (distance > MP.maxCarLength)
       {
         model.getAgents().add(this.carQueue.peek());
         this.observer.getNext(this).accept((Car)this.carQueue.dequeue());
       }
       
     }
     else
     {
       model.getAgents().add(this.carQueue.peek());
       this.observer.getNext(this).accept((Car)this.carQueue.dequeue());
     }
   }
   
   public void remove(Car d)
   {
     if (d == null) { throw new IllegalArgumentException();
     }
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
  
   public double getDistancetoNextObstacle(Car car) {
     if (!this.observer.getNext(this).getCars().isEmpty()) {
       return ((Car)this.observer.getNext(this).getCars().get(0)).getPosition() + this.length - car.getPosition() + car.getCarLength();
     }
     return car.getBreakDistance() + 0.1D;
   }
   
   public boolean getState() {
     return true;
   }
   
   public double getLength() {
     return this.length;
   }
}