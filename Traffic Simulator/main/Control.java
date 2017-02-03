package main;
 
import ui.UIFormBuilder;
import ui.UIMenuAction;
import ui.UIMenuBuilder;
 
class Control{
   private ui.UIMenu[] _menus;
   private int _state;
   private ui.UIForm _setSingleDouble;
   private ui.UIForm _setDoubleDouble;
   private ui.UIForm _setDoubleInt;
   private ui.UIForm _setPattern;
   private ui.UIFormTest _doubleTest;
   private ui.UIFormTest _numberTest;
   private ui.UI _ui;
   
   Control(ui.UI ui)
   {
     this._ui = ui;
     
     this._menus = new ui.UIMenu[4];
     this._state = 2;
     addSTART(2);
     addSETTINGS(3);
     addEXIT(1);
     
     this._numberTest = new ui.UIFormTest() {
       public boolean run(String input) {
         try {
           Integer.parseInt(input);
           return true;
         } catch (NumberFormatException e) {}
         return false;
       }
       
     };
     this._doubleTest = new ui.UIFormTest() {
       public boolean run(String input) {
         try {
           Double.parseDouble(input);
           return true;
         } catch (NumberFormatException e) {}
         return false;
       }
       
 
     };
     UIFormBuilder singleDouble = new UIFormBuilder();
     singleDouble.add("value:", this._doubleTest);
     this._setSingleDouble = singleDouble.toUIForm("Enter value: ");
     
     UIFormBuilder singleDouble2 = new UIFormBuilder();
     singleDouble2.add("value (default value is " + model.MP.getRuntime() + "):", this._doubleTest);
     this._setSingleDouble = singleDouble2.toUIForm("Enter value: ");
     
     UIFormBuilder doubleInt = new UIFormBuilder();
     doubleInt.add("numbers of rows (default value is " + model.MP.getRows() + "):", this._numberTest);
     doubleInt.add("numbers of columns (default value is " + model.MP.getColumns() + "):", this._numberTest);
     this._setDoubleInt = doubleInt.toUIForm("Enter value: ");
     
     UIFormBuilder setPattern = new UIFormBuilder();
     setPattern.add("1 for simple pattern and 2 for alternating: ", this._numberTest);
     this._setPattern = setPattern.toUIForm("Enter value: ");
     
     UIFormBuilder setDoubleDouble = new UIFormBuilder();
     setDoubleDouble.add("a minimum: ", this._numberTest);
     setDoubleDouble.add("a maximum: ", this._numberTest);
     this._setDoubleDouble = setDoubleDouble.toUIForm(": ");
   }
   
  void run() {
     try {
       while (this._state != 0) {
         this._ui.processMenu(this._menus[this._state]);
       }
     } catch (ui.UIError e) {
       this._ui.displayError("UI closed");
     }
   }
   
   private void addSTART(int stateNum) {
     UIMenuBuilder m = new UIMenuBuilder();
     
     m.add("Default", new UIMenuAction() { public void run() {} });
     m.add("Run simulation", new UIMenuAction()
     {
       public void run() {
         Control.this._state = 2;
         model.Model m = new model.Model(new model.swing.SwingAnimatorBuilder());
         m.run(model.MP.simulationRuntime);
         m.dispose();
       }
     });
     m.add("Change simulation parameters", new UIMenuAction()
     {
       public void run() {
         Control.this._state = 3;
       }
     });
     m.add("Show current values", new UIMenuAction()
     {
 
 
       public void run()
       {
 
         StringBuilder b = model.MP.returnCurrentValues();
         
         Control.this._ui.displayMessage(b.toString());
       }
       
     });
     m.add("About Developer", new UIMenuAction()
     {
 
 
       public void run()
       {
         Control.this._ui.displayMessage("Name: Jaymin Shethwala\nUniversity: Depaul University\n"
         		+ "Project Guide: Jeffery Sharpe\nTime period: October, 2016 to November, 2016");
       }
       
     });
     m.add("Exit", new UIMenuAction()
     {
       public void run() {
         Control.this._state = 1;
       }
       
     });
     this._menus[stateNum] = m.toUIMenu("Traffic Simulator");
   }
   
   private void addSETTINGS(int stateNum) {
     UIMenuBuilder m = new UIMenuBuilder();
     
     m.add("Default", new UIMenuAction()
     {
       public void run() {
         Control.this._ui.displayError("error!");
       }
       
     });
   
     m.add("Simulation time step", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setSingleDouble);
         
         model.MP.setTimeStep(Double.parseDouble(result1[0]));
       }
       
 
     });
     m.add("Simulation runtime", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setSingleDouble);
         
         model.MP.setRuntime(Double.parseDouble(result1[0]));
       }
 
     });
     m.add("Grid size", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleInt);
         
         model.MP.setGridSize(Integer.parseInt(result1[0]), Integer.parseInt(result1[1]));
       }
 
     });
     m.add("Set pattern of traffic", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setPattern);
         
         if (Integer.parseInt(result1[0]) == 1) {
           model.MP.setPattern(false);
         } else {
           model.MP.setPattern(true);
         }
         
       }
     });
     m.add("Set car entry rate", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setCarEntryRate(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }
       
 
     });
     m.add("Set road length", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setRoadLengths(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }
       
 
     });
     m.add("Set intersection length", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setIntersectionLength(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }

     });
     m.add("Set car length", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setCarLength(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }
       
 
     });
     m.add("Set max car velocity", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setSingleDouble);
         
         model.MP.setCarMaxVel(Double.parseDouble(result1[0]));
       }
 
     });
     m.add("Set car stop distance", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setCarStopDist(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }
 
     });
     m.add("Set car break distance", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setCarBreakDist(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
		}
     });
     m.add("Set traffic light green times", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setGreenTime(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }
     });
     m.add("Set traffic light yellow times", new UIMenuAction()
     {
 
       public void run()
       {
         String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
         
         model.MP.setYellowTime(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
       }
       
 
     });
     m.add("Reset simulation and return to previous menu", new UIMenuAction()
     {
       public void run() {
         model.MP.reset();
         Control.this._state = 2;
       }
       
     });
     m.add("Return to previous menu", new UIMenuAction()
     {
       public void run() {
         Control.this._state = 2;
       }
       
     });
     this._menus[stateNum] = m.toUIMenu("100 miles per hour");
   }
   
   private void addEXIT(int stateNum) {
     UIMenuBuilder m = new UIMenuBuilder();
     
     m.add("Default", new UIMenuAction() { public void run() {} });
     m.add("Yes", new UIMenuAction()
     {
       public void run() {
         Control.this._state = 0;
       }
     });
     m.add("No", new UIMenuAction()
     {
       public void run() {
         Control.this._state = 2;
       }
       
     });
     this._menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
   }
}