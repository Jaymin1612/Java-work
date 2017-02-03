package model;
 
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
   private int N;
   private Queue<Item>.Node first;
   private Queue<Item>.Node last;
   
   public Queue()
   {
     this.first = null;
     this.last = null;
   }
 
   public boolean isEmpty() { return this.first == null; }
   public int length() { return this.N; }
   public int size() { return this.N; }
   
   public void enqueue(Item item)
   {
     Queue<Item>.Node x = new Node();
     x.item = item;
     if (isEmpty()) { this.first = x;this.last = x;
     } else { this.last.next = x;this.last = x; }
     this.N += 1;
   }
   
   public Item dequeue()
   {
     if (isEmpty()) throw new RuntimeException("Queue underflow");
     Item item = this.first.item;
     this.first = this.first.next;
     this.N -= 1;
     return item;
   }
   
   public Item peek()
   {
     return (Item)this.first.item;
   }
   
   public String toString()
   {
     String s = "";
     for (Queue<Item>.Node x = this.first; x != null; x = x.next)
       s = s + x.item + " ";
     return s;
   }
   
   public Iterator<Item> iterator() { return new QueueIterator(); }
   
   private class QueueIterator implements Iterator<Item> { private QueueIterator() {}
     
     private Queue<Item>.Node current = Queue.this.first;
     
     public boolean hasNext() { return this.current != null; }
     public void remove() { throw new UnsupportedOperationException(); }
     
     public Item next() {
    	 Item item = null;
/*         if (!hasNext()) throw new NoSuchElementException();
       Item item = Queue.Node.access$100(this.current);
       this.current = Queue.Node.access$200(this.current);
 */       return item;
     }
}
   
private class Node{
     private Item item;
     private Queue<Item>.Node next;
     
     private Node() {}

	
   }
 }