package model;

import util.Animator;

public abstract interface AnimatorBuilder
{
  public abstract Animator getAnimator();
  
  public abstract void addLight(Light paramLight, int paramInt1, int paramInt2);
  
  public abstract void addHorizontalRoad(Road paramRoad, int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract void addVerticalRoad(Road paramRoad, int paramInt1, int paramInt2, boolean paramBoolean);
}