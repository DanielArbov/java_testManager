package id209475862_id207232760;

import java.io.Serializable;

public class OpenQ extends Question implements Serializable
{
     private String answer;
     
     
     public OpenQ(String question, String answer)
     {
    	 super(question);
    	 this.answer = answer;
     }
     
     public OpenQ(OpenQ q)
     {
    	 super(q.getQuestionContent());
    	 this.answer = q.getAnswer();
     }
     
     public String getAnswer()
     {
    	 return this.answer;
     }
     
     public boolean setAnswer(String str)
     {
    	 this.answer = str;
    	 return true;
     }
     
     public String toString()
     {
    	 return super.toString() + "\n and the answer is: " + answer + "\n";
     }
     
     public boolean equals(OpenQ q)
     {
    	 if(this.questionContent.equalsIgnoreCase(q.getQuestionContent()))
    		 return true;
    	 return false;
     }
     
     public void deleteAnswer()
     {
    	 this.answer = "";
     }
     
     
     public String toStringOnlyQ()  //Prints only the question
     {
    	 return super.toString();
     }
     
     
     
    public int lengthOfQ()
    {
    	return this.answer.length();
    }
     
     
     
     
     
}