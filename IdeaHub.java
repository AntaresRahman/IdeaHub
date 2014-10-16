//Antares Rahman, Alex Hernandez, Momin Javed, George Sarkar
//IdeaHub.java
//8 May 2014

import javax.swing.*; 
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class IdeaHub {
  //Create the Student trees and the Idea heap
  private static BST tree = new BST();
  private static BSTn tree2 = new BSTn();
  private static HeapI heap = new HeapI();
  
  private static void setMenuGUI()
  {
    
    //Load the saved information
    reload();
    
    //GUI window
    final JFrame menu = new JFrame("Student Ideas");
    
    //GUI buttons
    JButton addS = new JButton("Add Student");
    JButton addI = new JButton("Add Idea");
    JButton sell = new JButton("Sell Idea");
    JButton record = new JButton("Open Record");
    JButton file = new JButton("Show Files");
    JButton help = new JButton("User Help");
    JButton top = new JButton("Best Idea");
    JButton display = new JButton("Student Information");
    
    //Sell idea button is clicked and 1st (highest) Idea in the Heap is sold
    sell.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
         try {
          File inputFile = new File("save.txt");
             File tempFile = new File("tempFile.txt");
          
                final BufferedReader in = new BufferedReader(new FileReader(inputFile));
                final BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;
                String nextLine;

                while((currentLine = in.readLine()) != null)
                {
                    //trim newline when comparing with lineToRemove
                    if(currentLine.startsWith("IDEA")) 
                    { 
                     in.readLine();
                     nextLine = in.readLine();
                     if (!heap.isEmptyHeap() && nextLine.startsWith(String.valueOf

(heap.findBest().getINumber())))
                     {
                      //Mark the idea as sold to indicate it is no longer part of the students 

ideas now
                      out.write("SOLD");
                      out.write("\r\n");
                      out.write("Number");
                      out.write("\r\n");
                         out.write(String.valueOf(heap.findBest().getINumber()));
                         out.write("\r\n");
                     }
                     else
                     {
                      out.write(currentLine);
                      out.write("\r\n");
                      out.write("Number");
                      out.write("\r\n");
                      out.write(nextLine);
                      out.write("\r\n");
                     }
                    }
                    else
                    {
                     out.write(currentLine);
                     out.write("\r\n");
                 }
                }   
                in.close();
                out.close();
                
                //Exception catching
                if(!inputFile.delete())
                {
                    JOptionPane.showMessageDialog(null, "Could not rename file");
                    return;
                }
                if(!tempFile.renameTo(inputFile))
                    JOptionPane.showMessageDialog(null, "Could not rename file");
            }
            catch(IOException x)
            {
                JOptionPane.showMessageDialog(null, " ");
            }
          setSellGUI();
        }
      });

    //Add student button is clicked
    addS.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        setStudentInfoGUI();
      }
    });
    
    //Add idea button is clicked
    addI.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        setAddIdeaGUI();
      }
    });
    
    //Open text file button is clicked
    file.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        showFiles();
      }
    });
    
    //Student record button is clicked
    record.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        showRecordMenuGUI();
      }
    });
    
    //Help button is clicked
    help.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        showUserHelp();
      }
    });
    
    //Show top idea from priority queue
    top.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
	if (!heap.isEmptyHeap()) {
	    String idea = heap.findBest().getIdea();
	    JOptionPane viewTop = new JOptionPane("The best idea is: " + idea + ".", 

JOptionPane.ERROR_MESSAGE);
	    JDialog dialog = viewTop.createDialog(heap.getSize()+" Ideas Left!");
            dialog.setVisible(true);
	}
	else{
	    JOptionPane viewTop = new JOptionPane("There are no ideas yet!", 

JOptionPane.ERROR_MESSAGE);
	    JDialog dialog = viewTop.createDialog(heap.getSize()+" Ideas Left!");
            dialog.setVisible(true);
	}
      }
    });

    //Student Information button is clicked
    display.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        //Traverse tree to print all data
        System.out.println();
        JOptionPane.showMessageDialog(menu, "Tree is printed, refer to ouput in console");
        System.out.println("Student Tree:");
        tree2.traverse();
      }
    }); 
   
    //GUI labels
    JLabel menuL = new JLabel();
   
    //Creates menu window
    menu.pack();
    menu.setSize(800,400);
    menu.setResizable(false);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);

    //Set buttons and labels
    menu.getContentPane().setLayout(null);
    addS.setBounds(50,180,200,30);
    addI.setBounds(300,180,200,30);
    sell.setBounds(550,180,200,30);
    record.setBounds(50,230,200,30);
    file.setBounds(300,230,200,30);
    help.setBounds(550,230,200,30);
    top.setBounds(150, 280, 200, 30);
    display.setBounds(450,280,200,30);
    menu.getContentPane().add(addS);
    menu.getContentPane().add(addI);
    menu.getContentPane().add(sell);
    menu.getContentPane().add(record);
    menu.getContentPane().add(file);
    menu.getContentPane().add(help);
    menu.getContentPane().add(top);
    menu.getContentPane().add(display);
    menuL.setText("Idea Hub");
    menuL.setFont(menuL.getFont().deriveFont(24.0f));
    menuL.setBounds(325,20,200,100);
    menu.add(menuL);
    
  }
  
  private static void setSellGUI()
  {
      //Exceptions for empty heap
      if(heap.isEmptyHeap())
      {
        JOptionPane optionPane = new JOptionPane("No ideas left to sell!", 

JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog("0 Ideas Left!");
        dialog.setVisible(true);
      }
      //Remove head from heap; sell best idea
      else
      {  
       String idea = heap.findBest().getIdea();
       heap.deleteBest();
       heap.printHeap();
       //Shows how many ideas are left and what the sold idea was
       JOptionPane optionPane = new JOptionPane("Best idea was sold! \n"+heap.getSize()+" 

Ideas left!", JOptionPane.ERROR_MESSAGE); 
       JOptionPane optionPane2 = new JOptionPane("The sold idea was: " + idea, 

JOptionPane.ERROR_MESSAGE); 
          JDialog dialog = optionPane.createDialog(heap.getSize()+" Ideas Left!");
          dialog.setVisible(true);  
          JDialog dialog2 = optionPane2.createDialog(heap.getSize()+" Ideas Left!");
          dialog2.setVisible(true);  
      }
  }
  //On clicking Add Student
  private static void setStudentInfoGUI()
  {
    //GUI window
    final JFrame menu = new JFrame("Add New Student");
    
    //GUI buttons
    JButton addS = new JButton("Add Student");
    
    //GUI input fields
    final JTextField newS = new JTextField(20);
    final JTextField newSSN = new JTextField(4);
    final JTextField newE = new JTextField(40);
    final JTextField newN = new JTextField(4);
    
    addS.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        //Update Input Parameters
        String inputS = update(newS);
        String inputSSN = update(newSSN);
        String inputE = update(newE);
        String inputN = update(newN);
        
        //Count all possible errors
        int errorCount = 0;
        
        //Exceptions for Student name input
        if(inputS.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for student name is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(isString(inputS) == false)
        {
          JOptionPane optionPane = new JOptionPane("Student name has to be a String!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
          errorCount++;
        }
          
        //Exceptions for Student SSN input
        if(inputSSN.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for student SSN is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(isInt(inputSSN) == false)
        {
          JOptionPane optionPane = new JOptionPane("Student SSN has to be an int!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(correctLen(inputSSN) != 4)
        {
          JOptionPane optionPane = new JOptionPane("Student SSN has to have 4 numbers!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Length Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        
        //Exceptions for Student email input
        if(inputE.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for student email is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        
        //Exceptions for Student number input
        if(inputN.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for student number is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(isInt(inputN) == false)
        {
          JOptionPane optionPane = new JOptionPane("Student number has to be an int!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(correctLen(inputN) != 4)
        {
          JOptionPane optionPane = new JOptionPane("Student number has to have 4 numbers!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Length Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        
        //Confirmation to create new Student
        if(errorCount == 0)
        {
          JOptionPane.showMessageDialog(menu, "Student was created");
          int SSN = Integer.parseInt(inputSSN); 
          int numb = Integer.parseInt(inputN);
          NodeS node = new NodeS(inputS, inputE, SSN, numb);
          tree.insert(node);
          tree2.insert(node);
          
          //Save student information to file
          try {
            final BufferedWriter in = new BufferedWriter(new FileWriter("save.txt",true));
            in.write("STUDENT");
            in.write("\r\n");
            in.write("Name");
            in.write("\r\n");
            in.write(inputS);
            in.write("\r\n");
            in.write("Email");
            in.write("\r\n");
            in.write(inputE);
            in.write("\r\n");
            in.write("SSN");
            in.write("\r\n");
            in.write(inputSSN);
            in.write("\r\n");
            in.write("Number");
            in.write("\r\n");
            in.write(inputN);
            in.write("\r\n");
            in.close();
          } catch (IOException x) {}
        }
      }
    });
    
    //GUI labels
    JLabel enterS = new JLabel("Enter student name:");
    JLabel enterSSN = new JLabel("Enter student SSN:");
    JLabel enterE = new JLabel("Enter student email:");
    JLabel enterN = new JLabel("Enter student number:");
    
    //Creates menu window
    menu.pack();
    menu.setSize(800,400);
    menu.setResizable(false);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //Set buttons and labels
    menu.getContentPane().setLayout(null);
    addS.setBounds(500,175,200,30);
    menu.getContentPane().add(addS);
    enterS.setBounds(100,25,200,20);
    enterSSN.setBounds(100,100,200,20);
    enterE.setBounds(100,175,200,20);
    enterN.setBounds(100,250,200,20);
    menu.add(enterS);
    menu.add(enterSSN);
    menu.add(enterE);
    menu.add(enterN);
    
    //Set input fields
    newS.setBounds(100,50,200,20);
    newSSN.setBounds(100,125,200,20);
    newE.setBounds(100,200,200,20);
    newN.setBounds(100,275,200,20);
    menu.add(newS);
    menu.add(newSSN);
    menu.add(newE);
    menu.add(newN);
    
  }
  //On clicking Add Idea
  private static void setAddIdeaGUI()
  { 
    //GUI window
    final JFrame menu = new JFrame("Add New Idea");
    
    //GUI buttons
    JButton addI = new JButton("Add Idea");
    
    //GUI input fields
    final JTextField newSSN = new JTextField(4);
    final JTextField newI = new JTextField(2000);
    final JTextField rating = new JTextField(100);
   
    addI.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        String inputSSN = update(newSSN);
        String inputI = update(newI);
        String inputR = update(rating);
        
        int errorCount = 0;
        
        //Exceptions for Student SSN input
        if(inputSSN.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for student SSN is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(isInt(inputSSN) == false)
        {
          JOptionPane optionPane = new JOptionPane("Student SSN has to be an int!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(correctLen(inputSSN) != 4)
        {
          JOptionPane optionPane = new JOptionPane("Student SSN has to have 4 numbers!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Length Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        
        //Exceptions for idea input
        if(inputI.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for student idea is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        
        //Exceptions for Idea rating input
        if(inputR.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for idea rating is empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(isInt(inputR) == false)
        {
          JOptionPane optionPane = new JOptionPane("Idea rating has to be an int!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(correctLen(inputR) > 3)
        {
          JOptionPane optionPane = new JOptionPane("Idea rating cannot be more than 3 

characters in length!", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Length Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        if(isInt(inputR) == true && isValidRating(inputR) > 100 || isValidRating(inputR) < 0)
        {
          JOptionPane optionPane = new JOptionPane("Idea rating cannot be more than 100 and 

less than 0 in value!",
						JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Ivalid Entry Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        
        //Confirmation to create new Idea
        if(errorCount == 0)
        {
          JOptionPane.showMessageDialog(menu, "Idea was created");
          int SSN = Integer.parseInt(inputSSN); 
          int rate = Integer.parseInt(inputR);
          NodeI node = new NodeI(heap.getSize()+1, SSN, inputI, rate);
          NodeS cNode = tree.search(SSN);
          if (cNode != null)
           cNode.insertIdea(node);
          heap.insert(node);
          
          //Save idea information to file
          try {
            final BufferedWriter in = new BufferedWriter(new FileWriter("save.txt",true));
            in.write("IDEA");
            in.write("\r\n");
            in.write("Number");
            in.write("\r\n");
            in.write(String.valueOf(heap.getSize()));
            in.write("\r\n");
            in.write("SSN");
            in.write("\r\n");
            in.write(inputSSN);
            in.write("\r\n");
            in.write("Idea");
            in.write("\r\n");
            in.write(inputI);
            in.write("\r\n");
            in.write("Rating");
            in.write("\r\n");
            in.write(inputR);
            in.write("\r\n");
            in.close();
          } catch (IOException x) {}
        }
      }
    });
    
    //GUI labels
    JLabel enterSSN = new JLabel("Enter student SSN:");
    JLabel enterI = new JLabel("Enter student idea:");
    JLabel ratingL = new JLabel("Rate the idea (0-100):");
    
    //Creates menu window
    menu.pack();
    menu.setSize(800,400);
    menu.setResizable(false);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //Set buttons and labels
    menu.getContentPane().setLayout(null);
    addI.setBounds(500,175,200,30);
    menu.getContentPane().add(addI);
    enterSSN.setBounds(100,100,200,20);
    enterI.setBounds(100,175,200,20);
    ratingL.setBounds(100,250,200,20);
    menu.add(enterSSN);
    menu.add(enterI);
    menu.add(ratingL);
    
    //Set input fields
    newSSN.setBounds(100,125,200,20);
    newI.setBounds(100,200,200,20);
    rating.setBounds(100,275,200,20);
    menu.add(newSSN);
    menu.add(newI);
    menu.add(rating);
    
  }
  //On clicking Show Files
  private static void showFiles()
  {
    //Open up the text file
    try   
    {  
      Desktop.getDesktop().open(new File("save.txt"));  
    }  
    catch (IOException ex) {}
  }
  //On clicking Open Record
  private static void showRecordMenuGUI()
  {
    //GUI window
    JFrame menu = new JFrame("Access Student Record");
    
    //GUI buttons
    JButton access = new JButton("Access Record");
    
    //Creates menu window
    menu.pack();
    menu.setSize(800,400);
    menu.setResizable(false);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //GUI labels
    JLabel ssnL = new JLabel();
    JLabel nL = new JLabel();
    JLabel orL = new JLabel();
    
    //GUI input fields
    final JTextField enterSSN = new JTextField(4);
    final JTextField enterN = new JTextField(4);
    
    //Set buttons and labels
    menu.getContentPane().setLayout(null);
    access.setBounds(300,220,200,30);
    menu.getContentPane().add(access);
    enterSSN.setBounds(100,180,200,20);
    enterN.setBounds(500,180,200,20);
    menu.add(enterSSN);
    menu.add(enterN);
    ssnL.setText("Enter student SSN:");
    nL.setText("Enter student number:");
    orL.setText("OR");
    ssnL.setBounds(100,120,200,100);
    nL.setBounds(500,120,200,100);
    orL.setBounds(390,120,20,100);
    menu.add(ssnL);
    menu.add(nL);
    menu.add(orL);
    //On clicking Access Record; inside Open Record
    access.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        String inputSSN = update(enterSSN);
        String inputN = update(enterN);
        
        int errorCount = 0;
        
        //Exceptions for both inputs
        if(inputSSN.equals("") && inputN.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Text field for both inputs are empty!", 

JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
          errorCount++;
        }
        else if(inputN.equals(""))
        {
          //Exceptions for Student SSN input
          if(isInt(inputSSN) == false)
          {
            JOptionPane optionPane = new JOptionPane("Student SSN has to be an int!", 

JOptionPane.ERROR_MESSAGE);    
            JDialog dialog = optionPane.createDialog("Wrong Type Error!");
            dialog.setVisible(true);
            errorCount++;
          }
          
          if(correctLen(inputSSN) != 4)
          {
            JOptionPane optionPane = new JOptionPane("Student SSN has to have 4 numbers!", 

JOptionPane.ERROR_MESSAGE);    
            JDialog dialog = optionPane.createDialog("Wrong Length Error!");
            dialog.setVisible(true);
            errorCount++;
          }
          //Confirmation to access record via Student SSN
          if(errorCount == 0)
          {
              showStudentRecord(tree.search(Integer.parseInt(inputSSN)));
          }
        }
        else
        {
          //Exceptions for Student number input
          if(isInt(inputN) == false)
          {
            JOptionPane optionPane = new JOptionPane("Student number has to be an int!", 

JOptionPane.ERROR_MESSAGE);    
            JDialog dialog = optionPane.createDialog("Wrong Type Error!");
            dialog.setVisible(true);
            errorCount++;
          }
          if(correctLen(inputN) != 4)
          {
            JOptionPane optionPane = new JOptionPane("Student number has to have 4 numbers!", 

JOptionPane.ERROR_MESSAGE);    
            JDialog dialog = optionPane.createDialog("Wrong Length Error!");
            dialog.setVisible(true);
            errorCount++;
          }
          //Confirmation to access record via Student number
          if(errorCount == 0)
          {
            accessStudentInfo(tree2.search(Integer.parseInt(inputN)));
          }
        }
      }
    });
  }
  //On clicking Access Record; inside Open Record with SSN Number
  private static void showStudentRecord(NodeS node)
  {
    //GUI window
    final JFrame menu = new JFrame("Student Record");
    
    //GUI buttons
    JButton delete = new JButton("Delete Record");
    JButton changeN = new JButton("Change Name");
    JButton changeE = new JButton("Change Email");
    JButton lastTen = new JButton("Last 10 Ideas");
    
    //GUI input fields
    final JTextField inputN = new JTextField(20);
    final JTextField inputE = new JTextField(20);
    final String name = node.getName();
    final String email = node.getLoginName();
    final NodeS student = node;
    
    //On clicking Delete Record; inside Open Record
    delete.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          JOptionPane.showMessageDialog(menu, "Record was deleted, close all windows and 

restart");
         try {
          File inputFile = new File("save.txt");
             File tempFile = new File("tempFile.txt");
          
                final BufferedReader in = new BufferedReader(new FileReader(inputFile));
                final BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;
                String nextLine;

                while((currentLine = in.readLine()) != null)
                {
                    //trim newline when comparing with lineToRemove
                    if(currentLine.startsWith("STUDENT")) 
                    { 
                     String[] infoArray = new String[5];
                     for (int i=0; i<5; i++)
                      infoArray[i] = in.readLine();
                     nextLine = in.readLine();
                     if (nextLine.startsWith(String.valueOf(student.getSSN())))
                     {
                      in.readLine();
                      in.readLine();
                     }
                     else
                     {
                      out.write(currentLine);
                      out.write("\r\n");
                      for (int i=0; i<5; i++) {
                          out.write(infoArray[i]);
                       out.write("\r\n");
                      }
                      out.write(nextLine);
                      out.write("\r\n");
                     }
                    }
                    else
                    {
                     out.write(currentLine);
                     out.write("\r\n");
                 }
                }   
                in.close();
                out.close();

                if(!inputFile.delete())
                {
                    JOptionPane.showMessageDialog(null, "Could not rename file");
                    return;
                }
                if(!tempFile.renameTo(inputFile))
                    JOptionPane.showMessageDialog(null, "Could not rename file");
            }
            catch(IOException x)
            {
                JOptionPane.showMessageDialog(null, " ");
            }
            tree.delete(student);
            tree2.delete(student);
        }
      });
    //On clicking Last 10 Ideas; inside Open Record
    lastTen.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          JOptionPane.showMessageDialog(menu, "Last 10 ideas are printed in the output 

console");
         System.out.println("Last 10 Ideas of "+student.getName());
         student.getQueue().printQueue();
        }
      });
    //On clicking Change Name; inside Open Record
    changeN.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
       student.setName(update(inputN));
       JOptionPane.showMessageDialog(menu, "Student name was changed");
       try {
        File inputFile = new File("save.txt");
           File tempFile = new File("tempFile.txt");
        
              final BufferedReader in = new BufferedReader(new FileReader(inputFile));
              final BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

              String currentLine;
              String nextLine;

              while((currentLine = in.readLine()) != null)
              {
                  //trim newline when comparing with lineToRemove
                  if(currentLine.startsWith("STUDENT")) 
                  { 
                   String[] infoArray = new String[5];
                   for (int i=0; i<5; i++)
                    infoArray[i] = in.readLine();
                   nextLine = in.readLine();
                   if (nextLine.startsWith(String.valueOf(student.getSSN())))
                   {
                    out.write(currentLine);
                    out.write("\r\n");
                    out.write("Name");
                    out.write("\r\n");
                    out.write(update(inputN));
                    out.write("\r\n");
                    for (int i=2; i<5; i++) {
                        out.write(infoArray[i]);
                     out.write("\r\n");
                    }
                    out.write(nextLine);
                    out.write("\r\n");
                   }
                   else
                   {
                    out.write(currentLine);
                    out.write("\r\n");
                    for (int i=0; i<5; i++) {
                        out.write(infoArray[i]);
                     out.write("\r\n");
                    }
                    out.write(nextLine);
                    out.write("\r\n");
                   }
                  }
                  else
                  {
                   out.write(currentLine);
                   out.write("\r\n");
               }
              }   
              in.close();
              out.close();

              if(!inputFile.delete())
              {
                  JOptionPane.showMessageDialog(null, "Could not rename file");
                  return;
              }
              if(!tempFile.renameTo(inputFile))
                  JOptionPane.showMessageDialog(null, "Could not rename file");
          }
          catch(IOException x)
          {
              JOptionPane.showMessageDialog(null, " ");
          }
      }
    });
    //On clicking Change Email; inside Open Record
    changeE.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        student.setLoginName(update(inputE));
        JOptionPane.showMessageDialog(menu, "Student email was changed");
     try {
      File inputFile = new File("save.txt");
         File tempFile = new File("tempFile.txt");
      
            final BufferedReader in = new BufferedReader(new FileReader(inputFile));
            final BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            String nextLine;

            while((currentLine = in.readLine()) != null)
            {
                //trim newline when comparing with lineToRemove
                if(currentLine.startsWith("STUDENT")) 
                { 
                 String[] infoArray = new String[5];
                 for (int i=0; i<5; i++)
                  infoArray[i] = in.readLine();
                 nextLine = in.readLine();
                 if (nextLine.startsWith(String.valueOf(student.getSSN())))
                 {
                  out.write(currentLine);
                  out.write("\r\n");
                  for (int i=0; i<3; i++) {
                      out.write(infoArray[i]);
                   out.write("\r\n");
                  }
                  out.write(update(inputE));
                  out.write("\r\n");
                  out.write("SSN");
                  out.write("\r\n");
                  out.write(nextLine);
                  out.write("\r\n");
                 }
                 else
                 {
                  out.write(currentLine);
                  out.write("\r\n");
                  for (int i=0; i<5; i++) {
                      out.write(infoArray[i]);
                   out.write("\r\n");
                  }
                  out.write(nextLine);
                  out.write("\r\n");
                 }
                }
                else
                {
                 out.write(currentLine);
                 out.write("\r\n");
             }
            }   
            in.close();
            out.close();

            if(!inputFile.delete())
            {
                JOptionPane.showMessageDialog(null, "Could not rename file");
                return;
            }
            if(!tempFile.renameTo(inputFile))
                JOptionPane.showMessageDialog(null, "Could not rename file");
        }
        catch(IOException x)
        {
            JOptionPane.showMessageDialog(null, " ");
        }
      }
    });
    
    //Creates menu window
    menu.pack();
    menu.setSize(800,400);
    menu.setResizable(false);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //GUI labels
    JLabel infoL = new JLabel();
    infoL.setText("Information:");
    infoL.setFont(infoL.getFont().deriveFont(18.0f));
    JLabel nameL = new JLabel();
    nameL.setText("Last Name: " + node.getName());
    JLabel emailL = new JLabel();
    emailL.setText("Email Address: " + node.getLoginName());
    JLabel ssnL = new JLabel();
    ssnL.setText("Student SSN: " + String.valueOf(node.getSSN()));
    JLabel numberL = new JLabel();
    numberL.setText("Student Number: " + String.valueOf(node.getSNumber()));
    JLabel averageL = new JLabel();
    averageL.setText("Idea Rating Average: " + String.valueOf(node.getAverage()));
    
    //Set buttons and labels
    menu.getContentPane().setLayout(null);
    delete.setBounds(500,150,200,30);
    changeN.setBounds(500,200,200,30);
    changeE.setBounds(500,300,200,30);
    lastTen.setBounds(500,100,200,30);
    menu.getContentPane().add(delete);
    menu.getContentPane().add(changeN);
    menu.getContentPane().add(changeE);
    menu.getContentPane().add(lastTen);
    
    infoL.setBounds(100,20,200,100);
    nameL.setBounds(100,70,200,100);
    emailL.setBounds(100,120,200,100);
    ssnL.setBounds(100,170,200,100);
    numberL.setBounds(100,220,200,100);
    averageL.setBounds(100,270,200,100);
    menu.add(infoL);
    menu.add(nameL);
    menu.add(emailL);
    menu.add(ssnL);
    menu.add(numberL);
    menu.add(averageL);  
    
    inputN.setBounds(500,240,200,20);
    inputE.setBounds(500,340,200,20);
    menu.add(inputN);
    menu.add(inputE);  
  }
  //On clicking Access Record; inside Open Record with Student Number
  public static void accessStudentInfo(NodeS node) 
  {
    //GUI window
    final JFrame menu = new JFrame("Student Record");
    
    //GUI labels
    JLabel emailL = new JLabel();
    emailL.setFont(emailL.getFont().deriveFont(14.0f));
    emailL.setText("Email Address: " + node.getLoginName());
    
    //Creates menu window
    menu.pack();
    menu.setSize(400,320);
    menu.setResizable(false);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //Add GUI Stuff
    menu.getContentPane().setLayout(null);
    emailL.setBounds(95,100,300,100);
    menu.add(emailL);
  }
  //On clicking User Help
  private static void showUserHelp()
  {
    try   
    {  
      Desktop.getDesktop().open(new File("usermanual.txt"));  
    }  
    catch (IOException ex) {}
  }
  
  public static String update(JTextField field)
  {
    return field.getText();
  }
  
  public static void reload()
  {
    try {
      BufferedReader in = new BufferedReader(new FileReader("save.txt"));
      String line;
      while((line = in.readLine()) != null) {
        if(line.contains("STUDENT"))
        {
          in.readLine();
          String name = in.readLine();
          in.readLine();
          String email = in.readLine();
          in.readLine();
          String SSN = in.readLine();
          in.readLine();
          String number = in.readLine();
          System.out.println();
          
          NodeS node = new NodeS(name, email, Integer.parseInt(SSN), Integer.parseInt

(number));
          tree.insert(node);
          tree2.insert(node);
        }
        if(line.contains("IDEA"))
        {
          in.readLine();
          String number = in.readLine();
          in.readLine();
          String SSN = in.readLine();
          in.readLine();
          String idea = in.readLine();
          in.readLine();
          String rating = in.readLine();
          System.out.println();
          
          NodeI node = new NodeI(Integer.parseInt(number), Integer.parseInt(SSN), idea, 

Integer.parseInt(rating));
          NodeS cNode = tree.search(Integer.parseInt(SSN));
          heap.insert(node);
          if (cNode != null)
          {
           cNode.insertIdea(node);
          }
        }
        if(line.contains("SOLD"))
        {
         in.readLine();
            String number = in.readLine();
            in.readLine();
            String SSN = in.readLine();
            in.readLine();
            String idea = in.readLine();
            in.readLine();
            String rating = in.readLine();
            System.out.println();
            
            NodeI node = new NodeI(Integer.parseInt(number), Integer.parseInt(SSN), idea, 

Integer.parseInt(rating));
            NodeS cNode = tree.search(Integer.parseInt(SSN));
            if (cNode != null)
            {
             cNode.insertIdea(node);
            }
        }
      }
      in.close();
    }
    catch(FileNotFoundException e) {
      System.out.println(e);
    }
    catch(IOException e) {
      System.out.println(e);
    }
  }
  
  public static boolean isNotInt(String input)
  {
    try {
      Integer.parseInt(input);
      return false;
    }
    catch (NumberFormatException e) {
      return true;
    }
  }
  
  public static boolean isNotDouble(String input)
  {
    try {
      Double.parseDouble(input);
      return false;
    }
    catch (NumberFormatException e) {
      return true;
    }
  }
  
  public static boolean isString(String input)
  {
    if(isNotInt(input) == true && isNotDouble(input) == true)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
    
  public static boolean isInt(String input)
  {
    try {
      Integer.parseInt(input);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
   
  public static int correctLen(String input)
  {
    return input.length();
  }
  
  public static int isValidRating(String input)
  {
    return Integer.parseInt(input);
  }

  public static void main(String[] args) {
    setMenuGUI();
  }
}
