package id209475862_id207232760;

import java.io.Serializable;
import java.util.Arrays;

import exception.AnswerNotFoundException;
import exception.MaxAnswerException;

public class MultiChoiceQ extends Question implements Serializable
{
     private Set<MultiAnswer> answerList = new Set<MultiAnswer>();
      
      
      public MultiChoiceQ(String str, Set<MultiAnswer> answerList)
      {
    	  super(str);
    	  this.answerList = answerList;
    	  
      }
      
      public MultiChoiceQ(MultiChoiceQ q)
      {
    	 super(q.getQuestionContent());
         this.answerList = q.getAnswers();
      }
      
      
      public MultiChoiceQ(String str) {
    	  super(str);
      }

	public Set<MultiAnswer> getAnswers()
      {
    	  return this.answerList;
      }
      
      
      public String showAnswers()  //Returns the existing questions in the array
      {
    	  String str = "";
    	  
    	  for (int i = 0; i<this.answerList.getSize() ; i++)
    	  {
    			  str = str + "\n   " + (i+1) +". "+ this.answerList.getContent(i).toString();
    	  }
    	 
    	  
    	  return str;
      }
      
      
      
      public String toString()
      {
    	return super.toString() + "\n\n and the answers : "  + this.showAnswers() +"\n"; 
      }
      
      
      
      
      public boolean equals (MultiChoiceQ q)
      {
    	  if(q.getQuestionContent().equalsIgnoreCase(this.questionContent))
    		  return true;
    	  return false;
      }
      
      
      public boolean deleteQ(int i)             //Deletes the value according to the desired index and moves the rest of
                                                // the values to the left. 
      
      {
    	this.answerList.delete(i);
    	return true; 
      }
      
      public String toStringOnlyQ() //Prints only the question.
      {
    	  return super.toString();
      }
      
      
      public String toStringTrueV()   //Prints the question with only the correct answers
      {
    	  String str = "";
    	  for (int i = 0; i<this.answerList.getSize() ; i++)
    	  {
    		 
    		     if(this.answerList.getContent(i).getTrueValue() == true)
    		    	 str = str + "\n The true answer: \n" + this.answerList.getContent(i).getAnswer();
    	  }
    		  
    		  return super.toString() +""+ str;
      }
      
      
      
      public String addAnswer(MultiAnswer a)
      {
    	 this.answerList.add(a);
    	 return "add to the list";
      }
      
      
      
      public boolean updateAnswer(MultiAnswer theAnwer, int answerIndex) throws AnswerNotFoundException {
  		if(answerIndex >= this.answerList.getSize()) {
  			throw new AnswerNotFoundException                                                                                             ();
  		}
  		this.answerList.setContent(theAnwer, answerIndex);
  		return true;
  		
  	}
      
  
      
    public String stringAnswerNoTValue()
    {
    	String str = "";
    	for (int i = 0; i<this.answerList.getSize(); i++)
    	{
    		{
    			str = str + (i+1) +": "+ this.answerList.getContent(i).getAnswer()+ "\n";
    		}
    	}
    	
    	return super.toString() + "\n" + str;
    }
      
      
    public void automaticTestAnswers()
    {
    	int trueAnswer = 1;
    	
    	for(int i=0; i<this.answerList.getSize(); i++)
    	{
    		if(this.answerList.getContent(i).getTrueValue() == true)
    			trueAnswer--;
    		if((this.answerList.getContent(i).getTrueValue() == true) && (trueAnswer<0))
    			deleteQ(i);
    			
    	}
    	
    	for(int i = 3; i<this.answerList.getSize(); i++)
    	{
    		deleteQ(i);
    	}
    }
      
      
      
   public int lengthOfQ()
   {
	   int totNum = 0; 
	   for(int i = 0; i<this.answerList.getSize(); i++)
	   {
		   totNum += this.answerList.getContent(i).lengthOfQ();
	   }
	   
	   return totNum;
   }
      
      
      
      
}