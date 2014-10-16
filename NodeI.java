//Antares Rahman, Alex Hernandez, Momin Javed, George Sarkar
//NodeI.java
//8 May 2014

//This class implements the Idea node
public class NodeI {
 private int iNumber;
 private int ssn;
 private String idea;
 private float iRating;
 private NodeI next;
 private NodeI next1;
 private NodeI next2;
 
 //constructor
 public NodeI(int number, int ssn1, String idea1, float rating) {
  iNumber = number;
  ssn = ssn1;
  idea = idea1;
  iRating = rating;
 }
 
 //sets the idea number
 public void setINumber(int number) {
  iNumber = number;
 }
 
 //returns the idea number 
 public int getINumber() {
  return iNumber;
 }
 
 //sets the student ssn
 public void setSSN(int ssn1) {
  ssn = ssn1;
 }
 
 //returns the student ssn
 public int getSSN() {
  return ssn;
 }
 
 //sets the idea of the student
 public void setIdea(String idea1) {
  idea = idea1;
 }
 
 //returns the idea of the student
 public String getIdea() {
  return idea;
 }
 
 //sets the rating to the idea
 public void setRating(float rating) {
  iRating = rating;
 }
 
 //returns the rating for the idea
 public float getRating() {
  return iRating;
 }
 //pointer to the next idea
 public void setNext(NodeI node) {
  next=node;
 }
 //returns the next node
 public NodeI getNext() {
  return next;
 }
}
