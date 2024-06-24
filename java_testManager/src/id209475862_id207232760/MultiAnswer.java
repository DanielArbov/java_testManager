package id209475862_id207232760;

import java.io.Serializable;

public class MultiAnswer implements Serializable
{
   private boolean trueValue;
   private String answer;
   
   
   public MultiAnswer(boolean value, String answer)
   {
	   this.trueValue = value;
	   this.answer = answer; 
   }
   
   public boolean getTrueValue()
   {
	   return trueValue;
   }
   
   public String getAnswer()
   {
	   return this.answer;
   }
   
   public boolean setTrueValue(boolean value)
   {
	   this.trueValue = value;
	   return true;
   }
   
   public boolean setAnswer(String str)
   {
	   this.answer = str;
	   return true;
   }
   
   public String toString()
   {
	   String str = "";
	   if(this.trueValue)
		   str = str + "correct";
	   if(this.trueValue == false)
		   str = str + "not correct";
		   
	   return " The answer : " + answer + " is - " + str +"\n";
   }
   
   
   
   public boolean equlas(MultiAnswer a)
   {
	   if ((a.getTrueValue() == trueValue) && (a.getAnswer().equalsIgnoreCase(answer)))
		   return true;
	   return false;
   }
   
   public int lengthOfQ()
   {
	 return this.answer.length();
   }
   
   
   
   
   
   
   
   
   
   
   
   
}