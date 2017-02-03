package model;
 
public class CarHandlerList{
   private CarHandlerNode list;
   
 
   private Model observer;
   
 
 
   public CarHandlerList()
   {
     this.list = null;
   }
   
   public CarHandlerList(boolean direction, Model m, boolean carsmove)
   {
     this.list = null;
     this.observer = m;
   }

   public void accept(Car car, Model m)
   {
     if (this.list == null) {
       return;
     }
     
     this.list.carhandler.accept(car);
   }
 
   public void insert(CarHandler carhandler)
   {
     CarHandlerNode node = new CarHandlerNode(carhandler);
 
     if (this.list == null) {
       this.list = node;
     }
     else {
       CarHandlerNode current = this.list;
       while (current.next != null)
         current = current.next;
       current.next = node;
     }
     
     carhandler.addObserver(this);
   }
 
   public String toString()
   {
     String result = "";
     
     CarHandlerNode current = this.list;
     
     while (current != null)
    {
       result = result + current.carhandler + "\n";
       current = current.next;
     }
     
     return result;
   }
 
   public void add(CarHandler carhandler)
   {
     CarHandlerNode node = new CarHandlerNode(carhandler);
     CarHandlerNode current = null;
     CarHandlerNode previous = null;
     
     if (this.list == null)
     {
       this.list = node;
     }
     else
     {       current = this.list;
       previous = this.list;
       
       if (node.carhandler.horCompareTo(current.carhandler) < 1)
       {
         this.list = node;
         node.next = current;
 
       }
       else
       {
         while (current != null)
         {
           if (node.carhandler.horCompareTo(current.carhandler) < 1){
             previous.next = node;
            node.next = current;
             
            break;
           }
           previous = current;
           current = current.next;
         }
         
         if (current == null)
         {
           previous.next = node;
         }
       }
     }
     
     carhandler.addObserver(this);
   }
 
   public void delete(CarHandler carhandler)
   {
     CarHandlerNode current = null;
     CarHandlerNode previous = null;
     CarHandlerNode node = new CarHandlerNode(carhandler);
     
     previous = this.list;
     current = previous.next;
     
     if (previous.carhandler.horCompareTo(node.carhandler) == 0)
     {
       this.list = current;
 
     }
     else
     {
       while (current != null)
       {
         if (current.carhandler.horCompareTo(node.carhandler) == 0)
         {
           if (current.next == null) {
             previous.next = null; break;
           }
           previous.next = current.next;
           break;
         }
         
         previous = current;
         current = current.next;
       }
     }
 
     carhandler.removeObserver();
   }
 
   public CarHandler getNext(CarHandler carhandler)
   {
     CarHandlerNode current = null;
     
     current = this.list;
     
     while (current != null)
     {
       if (current.next == null) {
         return current.carhandler;
       }
       if (current.carhandler == carhandler) {
         return current.next.carhandler;
       }
       current = current.next;
     }
     
     return carhandler;
   }
 
   public CarHandler getPrev(CarHandler carhandler)
   {
     CarHandlerNode prev = this.list;
     CarHandlerNode current = prev.next;
     
     if (prev.carhandler == carhandler) {
       return prev.carhandler;
     }
     while (current != null)
     {
       if (current.next == null) {
         return prev.carhandler;
       }
       if (current.carhandler == carhandler) {
         return prev.carhandler;
       }
       
       prev = current;
       current = current.next;
     }
 
     return carhandler;
   }
   
   Model getObserver()
   {
     return this.observer;
   }
 
}