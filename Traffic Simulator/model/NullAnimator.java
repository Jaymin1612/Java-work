package model;

import java.util.Observable;
import util.Animator;

class NullAnimator implements Animator{
  public void update(Observable o, Object arg) {}
  
  public void dispose() {}
}