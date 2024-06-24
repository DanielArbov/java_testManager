package exception;

public class numOfQuestionNotInRangeException extends  Exception{
	public numOfQuestionNotInRangeException(int numOfQuestion){
		super(numOfQuestion+" is not a question index in the questions array");
	}
}
