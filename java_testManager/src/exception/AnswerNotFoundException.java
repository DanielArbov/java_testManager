package exception;

public class AnswerNotFoundException extends Exception{
    public AnswerNotFoundException(){
        super("The selected answer for not found please try again");
    }
}
