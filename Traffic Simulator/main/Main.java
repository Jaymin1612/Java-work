package main;
 
import ui.PopupUI;
import ui.UI;
 
public class Main{
   public static void main(String[] args)
   {
     UI ui = new PopupUI();
     
     Control control = new Control(ui);
     control.run();
   }
}
