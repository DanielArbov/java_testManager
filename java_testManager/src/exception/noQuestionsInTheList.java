package exception;

public class noQuestionsInTheList extends Exception {
	public noQuestionsInTheList() {
		super("There is no questions in the system question list! \ntry to load Question to system \n");
	}
}
