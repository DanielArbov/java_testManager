package exception;

public class notEnoughQuestion extends Exception {
	public notEnoughQuestion() {
		super("there are not enough questions in the database, you need to enter a smaller number");
	}

}
