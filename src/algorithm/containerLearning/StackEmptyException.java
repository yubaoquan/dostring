package algorithm.containerLearning;

public class StackEmptyException extends Exception {

	StackEmptyException() {
		super("there is no elements in this stack");
	}
}
