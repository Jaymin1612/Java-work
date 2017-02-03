package model;

public class CarHandlerNode {
	public CarHandler carhandler;
    public CarHandlerNode next;

    public CarHandlerNode(CarHandler r){
      this.carhandler = r;
      this.next = null;
    }
}
