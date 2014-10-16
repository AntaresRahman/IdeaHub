//Antares Rahman, Alex Hernandez, Momin Javed, George Sarkar
//Queue.java
//8 May 2014

//This queue consists of ideas such that it stores the 10 latest ideas
public class Queue{
  private NodeI[] array;
  private int head;
  private int tail;
  private int n;
  //private int sum;
  private float average;
  
  //constructor:
  public Queue() {
    array = new NodeI[10];
    head = 0;
    tail = 0;
    n = 0;
    //sum = 0;
    average = 0;
  }
  //adds NodeI as the last element
  public void enqueue (NodeI nodeI) {
    if (n==10)
      average = ((average*n)-array[head].getRating()+nodeI.getRating())/n;
    else
      average = ((average*n)+nodeI.getRating())/(n+1);
    array[tail] = nodeI; 
    tail = (tail + 1) % 10;
    n++;
    if (n>10 && head+1 == tail) {
      head = (head+1)%10;
      n--;
    }
  } 
  //returns the first NodeI
  public NodeI front() { 
    if (isEmpty()) {
      return null;
    }
    else {
      return array[head];
    }
  }
  //returns and removes the first NodeI
  public NodeI dequeue() {
    if (n==1)
      average = 0;
    else
      average = ((average*n)-array[head].getRating())/(n-1);
    int temp = head;
    head = (head+1) % 10;
    n--;
    return array[temp];   
  }  
  //checks if queue is empty
  public boolean isEmpty() {
    if (head == tail) { 
      return true;
    }
    else {
      return false;
    }
  }
  public float getAverage() {
    return average;
  }
  
  //prints the keys of the elements in the queue
  public void printQueue() {
    if (n>0 && head == tail) {
      int i = head;
      System.out.println("Idea#"+array[i%10].getINumber()+" Rated: "+array[i%10].getRating()+"\n"+array[i%10].getIdea());
      for(i=head+1; i <= 10 + (tail-1); i++) {
        System.out.println("Idea#"+array[i%10].getINumber()+" Rated: "+array[i%10].getRating()+"\n"+array[i%10].getIdea());
      }
    } 
    else if (head < tail) {
      for(int i = head; i <= tail-1; i++) {
        System.out.println("Idea#"+array[i].getINumber()+" Rated: "+array[i].getRating()+"\n"+array[i].getIdea());;
      }
    }
    else {
      for(int i = head; i <= 10+(tail-1); i++) {
        System.out.println("Idea#"+array[i%10].getINumber()+" Rated: "+array[i%10].getRating()+"\n"+array[i%10].getIdea());;
      }
    }
    System.out.println();     
  } 
}
