
	/**
	 * The Stack class uses an array of Country objects that is assigned a maximum size to act as a stack. 
	 * Only allowing insertion(push) or deletion(pop) to occur at the top of the stack. 
	 * 
	 * @author Brandon Rocha
	 * @version October 5, 2022
	 *
	 */


	
	public class Stack {
		private int maxSize;
		private Country[] stackArray;
		private int top;
		
		/**
		 * Constructor for Stack class that creates an empty stack.
		 * 
		 * @param size an integer type that indicates the maximum size of the stack to be created
		 */
		Stack(int size) {
			maxSize = size;
			stackArray = new Country[maxSize];
			top = -1;
		}
		/**
		 * Method that will push (insert) a Country object onto the top of the stack.
		 * Will only push if the stack is not full, utilizes the isFull method.
		 * 
		 * @param addCountry is the Country object to be inserted onto the top of the stack.
		 */
		public void push(Country addCountry) {
			if (isFull()) {
				System.out.printf("Stack is full, the country could not be pused on to stack.\n"
						+ "\n"
						+ "You may pop a country and try again.\n");
			}
			else {
			stackArray[++top] = addCountry;
			}
		}// end push
		/**
		 * Method that will remove(pop) a Country object from the top of the stack.
		 * If the stack is empty it will indicate that a pop cannot occur, utilizes the isEmpty method. 
		 * 
		 * @return the Country object that has been removed
		 */
		public Country pop() {
			if (isEmpty()) {
				System.out.println("Stack is Empty, nothing can be popped");
				return stackArray[-1];
			}
			else {
			return stackArray[top--];
			}
		}//end pop
		
		/**
		 * Method that will allow the user to see which Country is currently at the top of the stack.
		 * 
		 * @return a Country object at the top of the stack.
		 */
		public Country peek() {
			return stackArray[top];
		}
		
		/**
		 * Method to indicate that the stack is empty.
		 * 
		 * @return a boolean that is true if the stack is empty, false if the stack has at least one Country object
		 */
		public boolean isEmpty() {
			return (top == -1);
		}
		
		/**
		 * Method to indicate that the stack is full.
		 * 
		 * @return a boolean that is true if the stack has reached its maximum size, false if the stack can fit at least one more Country object.
		 */
		public boolean isFull() {
			return (top == maxSize -1);
		}
		/**
		 * Method that will print the stack from top to bottom, utilizes the toString method from Country class.
		 */
		public void printStack(Stack stacker) {
			System.out.printf("%-35s %-18s %9s %10s %16s %15s %12s\n", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			for (int i = top; i >= 0; --i) {
				stackArray[i].toString(stackArray[i]);
			}
		}
	}//end stack class
