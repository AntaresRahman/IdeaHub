//Antares Rahman, Alex Hernandez, Momin Javed, George Sarkar
//BSTn.java
//8 May 2014

//Creates a Binary Search Tree of the student nodes based on the Student numbers

public class BSTn {
 private NodeS t;
 private NodeS y;
 
 public BSTn() {
  t = null;
 }
 
 //checks if the tree is empty
 public boolean isEmptyTree() {
  return t == null;
 }
 
 //inserts a node into the tree
 public void insert(NodeS NodeS) {
  if (t == null)
   t = NodeS;
  else
   insert2(t, NodeS);
 }
 
 //a secondary insert function that includes an extra parameter, the parent node
 public void insert2(NodeS t, NodeS NodeS) {
  if (NodeS.getSNumber() < t.getSNumber()) { //if NodeS.ssn is less than parent.ssn
   if (t.getLeft1() == null)
    t.setLeft1(NodeS);
   else
    insert2(t.getLeft1(), NodeS); //calls recursively on the left node
  }
  else { //if NodeS.ss is greater than parent.ssn
   if (t.getRight1() == null)
    t.setRight1(NodeS);
   else
    insert2(t.getRight1(), NodeS); //calls recursively on the right node
  }
 }
 
 //searches for a node using the SSN
 public NodeS search(int sNumber) {
  if (t == null) //if the tree is emtpy
   return null;
  else if (sNumber == t.getSNumber())
   return t;
  else if (sNumber<t.getSNumber())
   return search2(t.getLeft1(), sNumber); //calls search2 on the left node
  else
   return search2(t.getRight1() , sNumber); //calls search2 on the right node
 }
 
 //a supplementary function to the insert() function   
 public NodeS search2(NodeS t, int sNumber) {
  if (t == null)
   return null;
  else if (sNumber == t.getSNumber())
   return t;
  else if (sNumber<t.getSNumber())
   return search2(t.getLeft1(), sNumber); //calls recursively on the left node
  else
   return search2(t.getRight1(), sNumber); //calls recursively on the right node
 }
 
 //prints the SSNs of all the nodes in the tree   
 public void traverse() {
  if (t != null) {
   traverse2(t);
   System.out.println();
  }
  else
   System.out.println();
 }
 
 //supplementary function to traverse()
 public void traverse2(NodeS t) {
  if (t != null) {
   traverse2(t.getLeft1());
   System.out.println("Student Number: "+t.getSNumber()+", Name: "+ t.getName()+
   ", SSN: "+t.getSSN()+", Average Score: "+t.getAverage());
   traverse2(t.getRight1());
  }
 }
 
 //prints the SSN of the nodes of the tree and the SSN of the nodes on the right
 //and left  
 public void printTree() {
  printTree2(t);
  System.out.println();
    }

 //supplementary function to printTree()
    private void printTree2(NodeS tree) {
  if (tree != null) {
      System.out.print(tree.getSNumber() + " ");
            if (tree.getLeft1() != null)
          System.out.print("Left: " + tree.getLeft1().getSNumber() + " ");
            else
                System.out.print("Left: null ");
            if (tree.getRight1() != null)
          System.out.println("Right: " + tree.getRight1().getSNumber() + " ");
            else
                System.out.println("Right: null ");
      printTree2(tree.getLeft1());
      printTree2(tree.getRight1());
  }
 }
 
 //deletes a node from the tree
 public void delete(NodeS NodeS) {
  if (NodeS.getSNumber() == t.getSNumber()) { //if the root is to be deleted
   y = NodeS.getRight1(); //pointer y moves to the right
   while(y.getLeft1() != null) { //pointer y moves all the way to the left
    y = y.getLeft1();
   }
   delete(y); //deletes the node all the way at the left
   NodeS z1 = t.getLeft1();
   NodeS z2 = t.getRight1();
   //removes the pointers from the root
   t.setLeft1(null);
   t.setRight1(null);
   //sets the deleted node  as the root
   t = y;
   //sets the right and left nodes of the previous root
   t.setLeft1(z1);
   t.setRight1(z2);
  }
  else { //if a node other than the root needs to be deleted
   if (NodeS.getLeft1() != null && NodeS.getRight1() != null) //for double parent
    delete1(t, NodeS); 
  
   else if(NodeS.getLeft1() == null && NodeS.getRight1() == null) //for leaf
    delete2(t, NodeS); 
   
   else //for single parent
    delete3(t, NodeS);
  }
 }
 
 //for double parent
 public void delete1(NodeS t1, NodeS NodeS) {
  if (NodeS.getSNumber() < t1.getSNumber()) {
   if (NodeS.getSNumber() == t1.getLeft1().getSNumber()) {
    y = NodeS.getRight1();
    while(y.getLeft1() != null) {
     y = y.getLeft1();
    }
    delete(y);
    t1.setLeft1(y);
    y.setLeft1(NodeS.getLeft1());
    y.setRight1(NodeS.getRight1());
    NodeS.setLeft1(null);
    NodeS.setRight1(null);
   }
   else {
    t1 = t1.getLeft1();
    delete1(t1, NodeS);
   }
  }
  else if (NodeS.getSNumber() > t1.getSNumber()) {
   if (NodeS.getSNumber() == t1.getRight1().getSNumber()) {
    y = NodeS.getRight1();
    while(y.getLeft1() != null) {
     y = y.getLeft1();
    }
    delete(y);
    t1.setRight1(y);
    y.setRight1(NodeS.getRight1());
    y.setLeft1(NodeS.getLeft1()); 
    NodeS.setLeft1(null);
    NodeS.setRight1(null);
   }
   else {
    t1 = t1.getRight1();
    delete1(t1, NodeS);
   }
  }
 }
 
 //leaf
 public void delete2(NodeS t2, NodeS NodeS) {
  if (NodeS.getSNumber() < t2.getSNumber()) {
   if (NodeS.getSNumber() == t2.getLeft1().getSNumber()) {
    t2.setLeft1(null);
   }
   else {
    t2 = t2.getLeft1();
    delete2(t2, NodeS);
   }
  }
  else {
   if (NodeS.getSNumber() == t2.getRight1().getSNumber()) {
    t2.setRight1(null);
   }
   else {
    t2 = t2.getRight1();
    delete2(t2, NodeS);
   }
  }
 }
 
 //single parent
 public void delete3(NodeS t3, NodeS NodeS) {
  if (NodeS.getSNumber() < t3.getSNumber()) {
   if (NodeS.getSNumber() == t3.getLeft1().getSNumber()) {
    if (NodeS.getLeft1() != null)
     t3.setLeft1(NodeS.getLeft1());
    else {
     t3.setLeft1(NodeS.getRight1());
    }
    NodeS.setLeft1(null);
    NodeS.setRight1(null);
   }
   else {
    t3 = t3.getLeft1();
    delete3(t3, NodeS);
   }
  }
  else if (NodeS.getSNumber() > t3.getSNumber()) {
   if (NodeS.getSNumber() == t3.getRight1().getSNumber()) {
    if (NodeS.getLeft1() != null)
     t3.setRight1(NodeS.getLeft1());
    else {
     t3.setRight1(NodeS.getRight1());
    }
    NodeS.setLeft1(null);
    NodeS.setRight1(null);
   }
   else {
    t3 = t3.getRight1();
    delete3(t3, NodeS);
   }
  }
 }
}
