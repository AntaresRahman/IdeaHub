//Antares Rahman Apr 08, 2014

//Heap class creates a priority queue (heap);
//higher rating has higher priority;
//has methods to manipulate it.

public class HeapI {
 private NodeI[] heap; //array of nodes
 private int n; //counter variable
 //creates empty Heap
 public HeapI() {
  heap = new NodeI[1000]; //array size 120
  n = 0;
 }
 //returns true if Heap isEmpty
 public boolean isEmptyHeap() {
  return n==0;
 }
 //prints the Heap in order of increasing index
 //prints name and key of each node
 public void printHeap() {
  if (n==0) //if Heap isEmpty
   System.out.println("Print Heap: Heap is empty!");
  else {
   System.out.println("Print Heap:");
   for (int i=0; i<n; i++)
    System.out.println(heap[i].getINumber() + ". " + heap[i].getIdea());
  }
 }
 //inserts Node according to priority
 public void insert(NodeI x) {
  heap[n] = x;
  n++;
  if (n>1) {
   int i = n-1; //index of last node
   int parent = (i-1)/2;//index of parent of last node
   //while child has higher priority than child
   while (parent>-1 && heap[parent].getRating() < x.getRating()) {
    //swap parent and child
    heap[i] = heap[parent];
    heap[parent] = x;
    //update index of child, and it's parent
    i = parent;
    parent = (parent-1)/2;
   }
  }
 }
 //returns node with highest priority
 public NodeI findBest() {
  return heap[0];
 }
 //returns number of nodes
 public int getSize() {
  return n;
 }
 //deletes node with highest priority
 public void deleteBest() {
  if (n==0) { //if Heap isEmpty
   System.out.println("Heap is empty!");
  }
  else if (n==1) { //if Heap has only one node
   heap[0] = null;
   n--;
  }
  else { //if Heap has more than one node
   heap[0] = heap[n-1];//swap highest priority node
   //with the last node
   heap[n-1]=null; //remove the highest priority node
   n--;
   int i = 0;
   NodeI temp;
   //while 
   while (i<=(n-2)/2 && heap[2*i+2]!=null && 
     heap[2*i+1]!=null && (heap[i].getRating()<heap[2*i+1].getRating() 
     || heap[i].getRating()<heap[2*i+2].getRating())) {
    if (heap[2*i+1].getRating()>heap[2*i+2].getRating()) {
     temp = heap[2*i+1];
     heap[2*i+1] = heap[i];
     heap[i] = temp;
     i = 2*i + 1;
    }
    else {
     temp = heap[2*i+2];
     heap[2*i+2] = heap[i];
     heap[i] = temp;
     i = 2*i + 2;
    }
   }
  }
 }
}
