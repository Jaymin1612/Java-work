package model;
 
import util.Animator;
 
class NullAnimatorBuilder implements AnimatorBuilder{
   public Animator getAnimator()
   {
     return new NullAnimator();
   }
   
   public void addLight(Light d, int i, int j) {}
  
   public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {}
   
   public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {}
}