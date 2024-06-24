package id209475862_id207232760;

import java.io.Serializable;

public abstract class Question implements Serializable
{
   protected String questionContent;
   protected int serialNum;
   protected static int count = 1;
   
   
   public Question(String str)
   {
	   this.questionContent = str;
	   this.serialNum = count;
	   count++;
   }
   
   public Question(Question q)
   {
	   this.questionContent = q.getQuestionContent();
	   this.serialNum = q.serialNum;
   }
   
   public String getQuestionContent()
   {
	   return this.questionContent;
   }
   
   public int getSerialNum()
   {
	   return this.serialNum;
   }
   
   public boolean setQuestionContent(String str)
   {
	   this.questionContent = str;
	  return true;
   }
   
   public String toString()
   {
	   return "\n Question: " + questionContent+ "\n ";
   }
   
   public boolean equals(Question q)
   {
	   if (this.questionContent.equalsIgnoreCase(q.questionContent))
		   return true;
	         return false;
   }
   
   
   
   
}