//Antares Rahman, Alex Hernandez, Momin Javed, George Sarkar
//NodeS.java
//8 May 2014

//This class implements the Student node
public class NodeS {
 private String lastName;
 private String loginName;
 private int ssn;
 private int sNumber;
 private Queue ideaQ;
 private NodeS next1;
 private NodeS next2;
 private NodeS next3;
 private NodeS next4;
 
 //constructor
 public NodeS(String name, String emailLog,  int ssn1, int number ) {
  lastName = name;
  loginName = emailLog;
  ssn = ssn1;
  sNumber = number;
  ideaQ = new Queue();
  next1 = null;
  next2 = null;
  next3 = null;
  next4 = null;
 }
 
 //sets the last name for the student
 public void setName(String name) {
  lastName = name;
 }
 
 //returns the last name for the student
 public String getName() {
  return lastName;
 }
 
 //sets the student login name
 public void setLoginName(String emailLog) {
  loginName = emailLog;
 }
 
 //returns the student login name
 public String getLoginName() {
  return loginName;
 }
 
 //sets the student ssn
 public void setSSN(int ssn1) {
  ssn = ssn1;
 }
 
 //returns the student ssn
 public int getSSN() {
  return ssn;
 }
 
 //sets the student number
 public void setSNumber(int number) {
  sNumber = number;
 }
 
 //returns the student number
 public int getSNumber() {
  return sNumber;
 }
 
 //inserts the idea node for the student
 public void insertIdea(NodeI ideaNode) {
  ideaQ.enqueue(ideaNode);
 }
 
 //returns the average of the last 10 ideas for the student
 public float getAverage() {
  return ideaQ.getAverage();
 }
 
 //returns the queue in the student node
 public Queue getQueue() {
  return ideaQ;
 }
 
 //left pointer for SSN based BST 
 public void setLeft(NodeS node) {
  next1 = node;
 }
 
 //left pointer for ID# based BST
 public void setLeft1(NodeS node) {
  next3 = node;
 }
 
 public NodeS getLeft() {
  return next1;
 }
 
 public NodeS getLeft1() {
  return next3;
 }
 
 //right pointer for SSN based BST
 public void setRight(NodeS node) {
  next2 = node;
 }
 
 //right pointer for ID# based BST
 public void setRight1(NodeS node) {
  next4 = node;
 }
 
 public NodeS getRight() {
  return next2;
 }
 
 public NodeS getRight1() {
  return next4;
 }
}
