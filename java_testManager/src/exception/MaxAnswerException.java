package exception;

public class MaxAnswerException extends  Exception{
    public MaxAnswerException(){
        super("More answers then the maximum possible were entered ");
    }
}