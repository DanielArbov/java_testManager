//To support the test option in several subjects: We will add to the class Manager a character that
//is the name of the subject and in the program before each test creation we are asked which subject the test belongs to


package id209475862_id207232760;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

import exception.AnswerNotFoundException;
import exception.FileNotExisted;
import exception.MaxAnswerException;
import exception.noQuestionsInTheList;
import exception.notEnoughQuestion;
import exception.numOfQuestionNotInRangeException;
import exception.InputMismatchException;



public class Test {

	public static int menu()
	{

        int selection;
        Scanner s = new Scanner(System.in);
        
        System.out.println("\n");
        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("0 - Quit");
        System.out.println("1 - add new question");
        System.out.println("2 - add new answer for question ");
        System.out.println("3 - update text of question");
        System.out.println("4 - update text of answer");
        System.out.println("5 - remove answer for some question");
        System.out.println("6 - build a test");
        System.out.println("7 - automatic test");
        System.out.println("8 - View all questions and answers");
        System.out.println("9 - save all questions and answers");
        System.out.println("10 - build a copy test");
        System.out.println();
       
        selection = s.nextInt();
       		
        return selection; 	
        
       
        
	}
	

	public static void main(String[] args)throws IOException,FileNotFoundException,ClassNotFoundException 	{
		
		Scanner s = new Scanner(System.in);
		
		
	try {	
		
	
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("manager.dat"));
		Manager m  = (Manager) in.readObject();
	    in.close();
	    
		
		int choice = menu();
		while (choice!=0) 
		{
			
			switch(choice) {
				case 1: {
					
					System.out.println("If you want to add an open question type 1 If you want to add a question with multiple selections type 2");
					int x = s.nextInt();
					if(x == 1)
					{
						String question1, answer1;
						System.out.println("please enter the question: ");
						question1 = s.next();
						System.out.println("please enter the answer: ");
						answer1 = s.next();
						
						Question q = new OpenQ(question1, answer1);
						m.addQuestion(q);
						
					}
					
					if(x == 2)
					{
						String question2;
						System.out.println("please enter the question: ");
					    question2 = s.next();
						
					    System.out.println("How many answers are there for this question? ");
						int amount = s.nextInt();
						
						Set<MultiAnswer> list = new Set<MultiAnswer>();
						for (int i = 0; i<amount; i++)
						{
							System.out.println("plese enter answer: \n");
							list.setContent(new MultiAnswer(true,s.next()), i);
							System.out.println("What is the truth value of the question? if true type 1 if false type 2");
							int tValue = s.nextInt();
							if(tValue == 2)
								list.getContent(i).setTrueValue(false);
							
						}
						
				      Question q = new MultiChoiceQ(question2, list);
				      m.addQuestion(q);
				
						
						
					}
					if((x!= 1) && (x!=2)) {
					System.out.println("Something is wrong");
					}
					break;
				}
				
				case 2:{
					String answer, isCorrect;
					int numOfQuestion;
					System.out.println("Enter for which question you want to add answer: ");
					numOfQuestion = s.nextInt();
					System.out.println("Add the new answer: ");
					answer = s.next();
					System.out.println("Write if the answer is correct(Yes/No) :");
					isCorrect = s.next();
					if(isCorrect.equalsIgnoreCase("Yes")) {
						MultiAnswer newAnswer = new MultiAnswer(true, answer);
						System.out.println(m.addAnswerToQuestion(newAnswer, numOfQuestion - 1));
					}if(isCorrect.equalsIgnoreCase("No")) {
						MultiAnswer newAnswer = new MultiAnswer(false, answer);
						System.out.println(m.addAnswerToQuestion(newAnswer, numOfQuestion - 1));
					}
					
					break;
					
				}
				case 3:{
					String update;
					System.out.println("Enter the num of question you want to update: ");
					int numOfCurrentQuestion = s.nextInt();
					System.out.println("Write the update for the question: ");
					update = s.next();
					m.updateQuestionCon(update, numOfCurrentQuestion -1 );
					System.out.println("successfully updated! ");
					
					
						
					break;
				}
				
				case 4:{
					
					
					System.out.println("What question do you want to update?");
					int indexOfQ = s.nextInt();
					System.out.println("please enter the new content: \n");
					String strCon = s.next();
				if(m.getQuestionArr().elementAt(indexOfQ-1) instanceof OpenQ )
				{
					MultiAnswer ans = new MultiAnswer(true, strCon);
					m.updateAnswer(ans, 0, indexOfQ - 1);
				}
				if(m.getQuestionArr().elementAt(indexOfQ-1) instanceof MultiChoiceQ)
				{
				     System.out.println("Which answer would you like to update? \n");
				     int indexOfA = s.nextInt();
				     System.out.println("If the new answer is correct type 1, Otherwise type 2: \n");
				     int boolVal = s.nextInt();
				     if(boolVal == 1) {
				    	 MultiAnswer ans = new MultiAnswer(true, strCon);
				    	 m.updateAnswer(ans, indexOfA - 1 , indexOfQ - 1);
				     }
				     if(boolVal == 2) {
				    	 MultiAnswer ans = new MultiAnswer(false, strCon);
				    	 m.updateAnswer(ans, indexOfA - 1 , indexOfQ - 1);
				     }
				     System.out.println("The answer has been successfully updated \n");
				     
				     
				}
					
					
					break;
				}
				
				case 5:{
					System.out.println("Answer of which question would you like to delete? \n");
					int indexToDelete = s.nextInt();
					if(m.getQuestionArr().elementAt(indexToDelete - 1) instanceof OpenQ)
					{
						m.eraseAnswer(indexToDelete, 0);
					}
					if(m.getQuestionArr().elementAt(indexToDelete - 1) instanceof MultiChoiceQ)
					{
						System.out.println("Which answer from the collection of answers would you like to delete? \n");
						int indexAnswerDelete = s.nextInt();
						m.eraseAnswer(indexToDelete , indexAnswerDelete);
						
					}
					
					break;
				}
				
				case 6:{
					
					     
				         System.out.println("How many questions would you like to ask?");
				         int numberOfQ = s.nextInt();
				         if(m.getQuestionArr().size()  <numberOfQ) {
								throw new notEnoughQuestion();
							}
				         System.out.println("How many questions would you like to ask?");
				         int numberOfQ1 = s.nextInt();
				        
				         int helpIndex = 0;
				         
				         Vector<Point> deleteArr = new Vector<Point>();
				         Vector<Integer> addArr = new Vector<Integer>();
				       
				         
				         System.out.println("Please enter the number of the question you would like to add: \n");
				         for(int i = 0; i<numberOfQ1; i++)
				         {
				        	 int num = s.nextInt() - 1;
				        	 addArr.add(num);
				        	 if( m.getQuestionArr().elementAt(num) instanceof MultiChoiceQ)
				        	 {
				        		System.out.println("Enter the number of the answers you want to delete,when finish type 0 \n");
				        		while(helpIndex!= -1)
				        		{
				        			helpIndex = s.nextInt()-1;
				        			Point p = new Point(num,helpIndex);
				        			deleteArr.add(p);
				        			
				        		}
				        			
				         			
				    
				        	 }
				         }
				         
				         System.out.println(m.standardtest(deleteArr, addArr));
				         
				      
				
					break;
				}
				
				case 7:{
					System.out.println("How many questions would you like to ask?");
					int amount = s.nextInt();
					
					System.out.println(m.autoTest(amount));
				
					
					break;
				}
				
				case 8:{
					System.out.println(m.toString());
					break;
				}
				
				case 9:{
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("manager.dat"));
				      out.writeObject(m);
				       out.close();
					break;
				}
				
				case 10:{
					System.out.println("please enter the date of the exem you want to copy(yyyy_mm_yy)");
					String str=s.nextLine();
					String fileNamePath1 ="_exam "+str+".txt";		
					Manager p=new Manager();
					String strExam=p.loadExamFromFile(fileNamePath1);
					String fileNamePath2 ="Solution_exam "+str+".txt";
					String strSolutionExam=p.loadExamFromFile(fileNamePath2);
					System.out.println(strSolutionExam);
					
					
					p.saveQuestionToTest(strExam);
					p.saveSolutionToTest(strSolutionExam);
					
					
					break;
				}
				
				
				default: System.out.println("Invalid Input!");	
			}
	
			choice = menu();
		}
		 s.close();
	} catch (MaxAnswerException e) {
		System.out.println(e.getMessage());
	}
	
	
	catch (AnswerNotFoundException e){
		System.out.println(e.getMessage());
	}
	catch (numOfQuestionNotInRangeException e) {
		System.out.println(e.getMessage());
	}
	catch (notEnoughQuestion e) {
		System.out.println(e.getMessage());
	}
	catch (FileNotExisted e) {
		System.out.println(e.getMessage());
	}		
	
	catch(Exception e)
	{
		System.out.println("Invalid input");
	}
					
				
					
	
    s.close();

	

	}
	

}
