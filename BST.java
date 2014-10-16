//Antares Rahman, Alex Hernandez, Momin Javed, George Sarkar
//BST.java
//8 May 2014

//Creates a Binary Search Tree of the student nodes based on the SSN

public class BST {
 private NodeS t;
 private NodeS y;
 
 public BST() {
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
  if (NodeS.getSSN() < t.getSSN()) { //if NodeS.ssn is less than parent.ssn
   if (t.getLeft() == null) 
    t.setLeft(NodeS);
   else 
    insert2(t.getLeft(), NodeS); //calls recursively on the left node
  }
  else { //if NodeS.ss is greater than parent.ssn
   if (t.getRight() == null)
    t.setRight(NodeS);
   else
    insert2(t.getRight(), NodeS); //calls recursively on the right node
  }
 }
 
 //searches for a node using the SSN
 public NodeS search(int SSN) {
  if (t == null) //if the tree is emtpy
   return null;
  else if (SSN == t.getSSN()) 
   return t;
  else if (SSN<t.getSSN()) 
   return search2(t.getLeft(), SSN); //calls search2 on the left node
  else
   return search2(t.getRight() , SSN); //calls search2 on the right node
 }
 
 //a supplementary function to the insert() function  
 public NodeS search2(NodeS t, int SSN) {
  if (t == null)
   return null;
  else if (SSN == t.getSSN())
   return t;
  else if (SSN<t.getSSN())
   return search2(t.getLeft(), SSN); //calls recursively on the left node
  else
   return search2(t.getRight(), SSN); //calls recursively on the right node
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
   traverse2(t.getLeft());
   System.out.print(t.getSSN() + " ");
   traverse2(t.getRight());
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
      System.out.print(tree.getSSN() + " ");
            if (tree.getLeft() != null)
          System.out.print("Left: " + tree.getLeft().getSSN() + " ");
            else
                System.out.print("Left: null ");
            if (tree.getRight() != null)
          System.out.println("Right: " + tree.getRight().getSSN() + " ");
            else
                System.out.println("Right: null ");
      printTree2(tree.getLeft()); //calls recursively on the node on the left
      printTree2(tree.getRight()); //calls recursively on the node on the right
  }
 }
 
 //deletes a node from the tree
 public void delete(NodeS NodeS) {
  if (NodeS.getSSN() == t.getSSN()) { //if the root is to be deleted
   y = NodeS.getRight(); //pointer y moves to the right
   while(y.getLeft() != null) { //pointer y moves all the way to the left
    y = y.getLeft();
   }
   delete(y); //deletes the node all the way at the left
   NodeS z1 = t.getLeft(); 
   NodeS z2 = t.getRight();
   //removes the pointers from the root
   t.setLeft(null); 
   t.setRight(null);
   //sets the deleted node  as the root
   t = y;
   //sets the right and left nodes of the previous root
   t.setLeft(z1);
   t.setRight(z2);
  }
  else { //if a node other than the root needs to be deleted
   if (NodeS.getLeft() != null && NodeS.getRight() != null) //for double parent
    delete1(t, NodeS); 
  
   else if(NodeS.getLeft() == null && NodeS.getRight() == null) //for leaf
    delete2(t, NodeS); 
   
   else //for single parent
    delete3(t, NodeS);
  }
 }
 
 //for double parent
 public void delete1(NodeS t1, NodeS NodeS) {
  if (NodeS.getSSN() < t1.getSSN()) {
   if (NodeS.getSSN() == t1.getLeft().getSSN()) {
    y = NodeS.getRight();
    while(y.getLeft() != null) {
     y = y.getLeft();
    }
    delete(y);
    t1.setLeft(y);
    y.setLeft(NodeS.getLeft());
    y.setRight(NodeS.getRight());
    NodeS.setLeft(null);
    NodeS.setRight(null);
   }
   else {
    t1 = t1.getLeft();
    delete1(t1, NodeS);
   }
  }
  else if (NodeS.getSSN() > t1.getSSN()) {
   if (NodeS.getSSN() == t1.getRight().getSSN()) {
    y = NodeS.getRight();
    while(y.getLeft() != null) {
     y = y.getLeft();
    }
    delete(y);
    t1.setRight(y);
    y.setRight(NodeS.getRight());
    y.setLeft(NodeS.getLeft()); 
    NodeS.setLeft(null);
    NodeS.setRight(null);
   }
   else {
    t1 = t1.getRight();
    delete1(t1, NodeS);
   }
  }
 }
 
 //leaf
 public void delete2(NodeS t2, NodeS NodeS) {  
  if (NodeS.getSSN() < t2.getSSN()) {
   if (NodeS.getSSN() == t2.getLeft().getSSN()) {
    t2.setLeft(null);
   }
   else {
    t2 = t2.getLeft();
    delete2(t2, NodeS);
   }
  }
  else {
   if (NodeS.getSSN() == t2.getRight().getSSN()) {
    t2.setRight(null);
   }
   else {
    t2 = t2.getRight();
    delete2(t2, NodeS);
   }
  }
 }
 
 //single parent
 public void delete3(NodeS t3, NodeS NodeS) {
  if (NodeS.getSSN() < t3.getSSN()) {
   if (NodeS.getSSN() == t3.getLeft().getSSN()) {
    if (NodeS.getLeft() != null)
     t3.setLeft(NodeS.getLeft());
    else {
     t3.setLeft(NodeS.getRight());
    }
    NodeS.setLeft(null);
    NodeS.setRight(null);
   }
   else {
    t3 = t3.getLeft();
    delete3(t3, NodeS);
   }
  }
  else if (NodeS.getSSN() > t3.getSSN()) {
   if (NodeS.getSSN() == t3.getRight().getSSN()) {
    if (NodeS.getLeft() != null)
     t3.setRight(NodeS.getLeft());
    else {
     t3.setRight(NodeS.getRight());
    }
    NodeS.setLeft(null);
    NodeS.setRight(null);
   }
   else {
    t3 = t3.getRight();
    delete3(t3, NodeS);
   }
  }
 }
}
