package id209475862_id207232760;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import exception.AnswerNotFoundException;
import exception.FileNotExisted;
import exception.MaxAnswerException;
import exception.noQuestionsInTheList;
import exception.notEnoughQuestion;
import exception.numOfQuestionNotInRangeException;
import java.util.Vector;


public class Manager implements Serializable
{
    private Vector<Question> questionArr;
   
    
    
    public Manager(Vector<Question> questionArr )
    {
    	this.questionArr = new Vector<Question>();
    	this.questionArr.addAll(questionArr);
    			
    }
    
    



	public Vector<Question> getQuestionArr() 
    {
    	return this.questionArr;
    }
    
    
    public boolean setQuestionArr(Vector<Question> questionArr)
    {
    	this.questionArr = questionArr;
    	return true;
    }
    
    
    public boolean addQuestion(Question theQuestion) {
    	this.questionArr.add(theQuestion);
    	return true;
    }
    
    public boolean updateQuestionCon(String str, int index) throws numOfQuestionNotInRangeException //set only the content of the question.
    {
    	if(this.questionArr.size() - 1 < index) {
    		throw new numOfQuestionNotInRangeException(index);
    	}
    	questionArr.elementAt(index).setQuestionContent(str);
    	return true;
    }
    
    
    
    public String addAnswerToQuestion(MultiAnswer answer,int indexOfQuestion) throws numOfQuestionNotInRangeException, MaxAnswerException {
    	if(questionArr.elementAt(indexOfQuestion) instanceof MultiChoiceQ) {
    		if(questionArr.size() - 1 < indexOfQuestion) {
        		throw new numOfQuestionNotInRangeException(indexOfQuestion);
        	}
    		MultiChoiceQ s=(MultiChoiceQ)questionArr.elementAt(indexOfQuestion);
    		return s.addAnswer(answer);
    	}
    	else
    		return "Unable to add answer to open question";
    }
    
    
	public void eraseAnswer(int numOfQuestion,int numOfAnswer) throws numOfQuestionNotInRangeException { 
		numOfQuestion--;
		numOfAnswer--;
		if(questionArr.size()-1<numOfQuestion) {
    		throw new numOfQuestionNotInRangeException(numOfQuestion);
    	}
		if(questionArr.elementAt(numOfQuestion) instanceof MultiChoiceQ) {
			MultiChoiceQ s=(MultiChoiceQ)questionArr.elementAt(numOfQuestion);
			s.deleteQ(numOfAnswer);
		}
		if(questionArr.elementAt(numOfQuestion) instanceof OpenQ) {
			OpenQ s=(OpenQ)questionArr.elementAt(numOfQuestion);
			s.deleteAnswer();
		}	
		
		
		
	}
	
	public boolean updateQuestion(Question newQuestion,  int questionIndex) throws  numOfQuestionNotInRangeException {
		
		if(questionArr.size()-1<questionIndex) {
    		throw new numOfQuestionNotInRangeException(questionIndex);
    	}
		else {
			questionArr.setElementAt(newQuestion, questionIndex);
			return true;
		}
		
	}
	
	
	public boolean updateAnswer(MultiAnswer answer, int indexAnswer, int questionIndex) throws  numOfQuestionNotInRangeException, AnswerNotFoundException {
	
		if(questionArr.size()-1<questionIndex) {
    		throw new numOfQuestionNotInRangeException(questionIndex);
    	}
		
		if(questionArr.elementAt(questionIndex) instanceof MultiChoiceQ) {
			MultiChoiceQ s=(MultiChoiceQ)questionArr.elementAt(questionIndex);
			s.updateAnswer(answer, indexAnswer);
		}
		
		if(questionArr.elementAt(questionIndex) instanceof OpenQ) {
			OpenQ s=(OpenQ)questionArr.elementAt(questionIndex);
			s.setAnswer(answer.getAnswer());
		}
		return true;
	}
	
	
	public String toString() {
	String str = "";
	for (int i = 0; i<questionArr.size(); i++)
	{
			str = str + "Question number " + (i+1) + ": \n\n" + questionArr.elementAt(i).toString() +"\n";
		
	}
	return str;
	}
	

	public String standardtest(Vector<Point> answerToDelete, Vector<Integer> questionList) throws IOException
	{
		int j = 0;
		Vector<Question> arr = new Vector<Question>();
		for(int i = 0; i<questionList.size(); i++)
		{
	
			if( this.questionArr.elementAt(i) instanceof OpenQ)
			{
				OpenQ s = (OpenQ)questionArr.elementAt(i);
		    	Question q = new OpenQ(s);
		    	arr.add(q);
				
			}
			
			if(this.questionArr.elementAt(i) instanceof MultiChoiceQ)
			{
				MultiChoiceQ s = (MultiChoiceQ)questionArr.elementAt(i);
				
				while(questionList.elementAt(i) == answerToDelete.elementAt(j).getX())
				{
					s.deleteQ(answerToDelete.elementAt(j).getY());
				}
				Question q = new MultiChoiceQ(s);
				
				arr.add(q);
				
			}
		}
		insertSort(arr);
		return stringForTest(arr);
	}
	
	
	 
	
	
	
	private String stringForTest(Vector<Question> arr) throws IOException //A method that creates content for test printing
	{
		String str = "";
		str = str + "\n\n The test: \n\n\n"; 
		for(int i = 0; i<arr.size(); i++)
		{
		
			str = str + "question nuber - " + (i+1) + ": ";
			if(arr.elementAt(i) instanceof OpenQ)
			{
				OpenQ q = (OpenQ)arr.elementAt(i);
				str = str + q.toStringOnlyQ();
			}
			
			if(arr.elementAt(i) instanceof MultiChoiceQ)
			{
				MultiChoiceQ q = (MultiChoiceQ)arr.elementAt(i);
				str = str + q.stringAnswerNoTValue();
				str = str + q.getAnswers().getSize() + ": All the answers are correct \n" + (q.getAnswers().getSize()+1) +": More than one correct answer \n";
				
				
			}
		}
		saveQuestionToTest(str);
		
		str = str + "\n\n Solutions page: \n\n\n ";
		for(int j = 0; j<arr.size(); j++)
		{
			str = str + "\n\n question nuber - " + (j+1) + ": ";
			if(arr.elementAt(j) instanceof OpenQ)
			{
				OpenQ q = (OpenQ)arr.elementAt(j);
				str = str + q.toString();
			}
			
			if(arr.elementAt(j) instanceof MultiChoiceQ)
			{
				MultiChoiceQ q = (MultiChoiceQ)arr.elementAt(j);
				str = str + q.toStringTrueV();
			}
		}
		saveSolutionToTest(str);
		return str;
	}
	
	
	
	
	public boolean biggerThan(Question q1, Question q2) //Returns true if q1 is bigger than q2 
	{
		int number1 =0, number2=0;
		if(q1 instanceof OpenQ) {
			OpenQ qu1= (OpenQ)q1;
		    number1 = qu1.lengthOfQ();
			}
		
		if(q2 instanceof OpenQ) {
			OpenQ qu2= (OpenQ)q2;
		    number2 = qu2.lengthOfQ();
			}
		
		if(q1 instanceof MultiChoiceQ) {
			MultiChoiceQ qu1= (MultiChoiceQ)q1;
		    number1 = qu1.lengthOfQ();
			}
		
		if(q2 instanceof MultiChoiceQ) {
			MultiChoiceQ qu2= (MultiChoiceQ)q2;
		    number2 = qu2.lengthOfQ();
			}
		
		if(number1 > number2)
			return true;
		
		return false;
		
		
	}
	
	
	
	public void insertSort(Vector<Question> arr) // insert sort for automatic test
	{
		if(arr.size()>1)
		{
		int j;
	    Question save = arr.elementAt(1);
		
		for(int i = 1; i<arr.size(); i++)
		{
			j = i-1;
			save = arr.elementAt(i);
			
			while((j>=0) && (biggerThan(arr.elementAt(i),save)))
			{
				arr.setElementAt(arr.elementAt(j), j+1);
				j--;
			}
			
			arr.setElementAt(save, j+1);
		}
		}
	}
	
		
		
	public String autoTest(int amount) throws notEnoughQuestion, IOException
	{
		Vector<Question> arr = new Vector<Question>();
		
		for(int i=0; i<amount; i++)
		{
			int randNum = (int)(Math.random() * this.questionArr.size() - i) + i;
			Question q = questionArr.elementAt(randNum);
			if(q instanceof MultiChoiceQ)
			{
				((MultiChoiceQ) q).automaticTestAnswers();
			}
			arr.add(q);
			moveLeft(randNum);
		}
		
		insertSort(arr);
		
		return stringForTest(arr);
		
		
	}
    
	public void moveLeft(int index) //Helps select random questions for automated testing
	{
		Question save = questionArr.elementAt(index);
		questionArr.remove(index);
		questionArr.add(0, save);
	   
		
	}
    
    
    
    
    public boolean equals(Manager m)
    {
    	if(m.getQuestionArr().size() == this.questionArr.size())
    		return true;
    	return false;
    }
    
    
    
    public void saveSolutionToTest(String str) throws IOException  {
      	 LocalDate now = LocalDate.now(Clock.systemDefaultZone());
           String sulutionFileName = "Solution_exam "+ now + ".txt";
           File examFile = new File(sulutionFileName);
           examFile.createNewFile();
           PrintWriter pw = new PrintWriter(examFile);
           pw.println(str);

           pw.close();
      }
       
       public void saveQuestionToTest(String str) throws IOException  {
       	 LocalDate now = LocalDate.now(Clock.systemDefaultZone());
            String questionFileName = "_exam "+ now + ".txt";
            File examFile = new File(questionFileName);
            examFile.createNewFile();
            PrintWriter pw = new PrintWriter(examFile);
            pw.println(str);

            pw.close();
       }
    
    
    
       public String loadExamFromFile(String filePath) throws FileNotFoundException, MaxAnswerException, FileNotExisted {
       	String currentExamText = null;
           File loadedFile = new File(filePath);
           if(loadedFile.exists()!=true) {
           	throw new FileNotExisted();
           }
       	Scanner sf = new Scanner(loadedFile);
       	while(sf.hasNext()) {
       		currentExamText =currentExamText+ sf.nextLine();
       		currentExamText =currentExamText+"\n";
       	}
   		return currentExamText;
       }
    
       public Manager() {
       }
    
    
}