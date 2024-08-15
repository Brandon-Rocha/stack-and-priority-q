
/**
 * PriorityQ class utilizes an array of Country objects to act as a priority queue with the highest priority being the lowest case fatality rate and the
 * lowest priority being the country with the highest case fatality rate.
 * It deletes countries based on their priority, highest priority deleted first.
 * Insertion will also be based on case fatality rate.
 * 
 * 
 * @author Brandon Rocha
 *@version October 5, 2022
 */

public class PriorityQ {
	
	private int maxSize;
	private Country[] queArray;
	private int nItems;
	
	/**
	 * Constructor that creates an empty priority queue.
	 *  
	 * @param size is an integer type that indicates the maximum size of the priority queue
	 */
	PriorityQ (int size) {
		maxSize = size;
		queArray = new Country[maxSize];
		nItems = 0;
	}
	/**
	 * Method that allows for insertion into the priority queue based on case fatality rate. 
	 * Will not allow insertion if the queue is full, utilizes the isFull method.
	 * 
	 * @param insCountry is a Country object to be inserted into the queue
	 */
	public void insert(Country insCountry) {
		if (isFull()) {
			System.out.println("Priority queue is full, nothing can be added.");
		}
		else {
		int j;
		
		if (nItems == 0) {
			queArray[nItems++] = insCountry;
		}
		else {
			for(j = nItems - 1; j>=0; j--) {
				if ( insCountry.getFatal() > queArray[j].getFatal() ) {
					queArray[j+1] = queArray[j];
				}
				else {
					break;
				}
			}//end for loop
			queArray[j+1] = insCountry;
			nItems++;
		}//end else
		}//end is full else
	}//end insert
	
	/**
	 * Method that removes the Country object with the highest priority which would be the country with the lowest case fatality rate.
	 * Will not allow for removal if the queue is already empty, utilizes the isEmpty method.
	 * 
	 * @return the Country object that has been removed. 
	 */
	public Country remove() {
		if(isEmpty()) {
			System.out.println("Priority queue is empty, nothing can be removed.");
			return queArray[-1];
		}
		return queArray[--nItems];
	}
	
	/**
	 * Method that allows the user to view the Country object that currently has the highest priority.
	 * 
	 * @return a Country object with the highest priority.
	 */
	
	public Country peekMin() {
		return queArray[nItems-1];
	}
	
	/**
	 * Method to indicate if the queue is empty.
	 * 
	 * @return a boolean type that is true if the queue is empty, false if the queue has at least one Country object
	 */
	public boolean isEmpty() {
		return(nItems==0);
	}
	
	/**
	 * Method to indicate if the queue is full.
	 * 
	 * @return a boolean type that is true if the queue is full, false if the queue has space for at least one more Country object.
	 */
	public boolean isFull() {
		return(nItems == maxSize);
	}
	
	/**
	 * Method that prints the contents of the priority queue from highest priority to lowest priority, utilizes the toString method of Country class.
	 * 
	 * @param pri is of type PriorityQ to inidicate the contents of the priority queue to be printed.
	 */
	public void printQue (PriorityQ pri) {
		System.out.printf("%-35s %-18s %9s %10s %16s %15s %12s\n", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for (int i = nItems-1; i >=0 ; --i) {
			queArray[i].toString(queArray[i]);
		}
	}
	
}//end class
