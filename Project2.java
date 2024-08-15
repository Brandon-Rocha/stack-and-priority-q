import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Project 2 class starts by creating a Country object, arrays of Country objects, a stack (using Stack class), and a priority queue (using PriorityQ class).
 * All of these are empty or null and the user is asked for a file (Countries2.csv) that Project2 class then parses to fill the arrays, stack and queue.
 * <p>
 * The arrays are simply created to fill the stack and priority queue. The stack is simply filled from bottom to top.
 * The priority queue is organized from highest priority to lowest priority, the lowest case fatality rate being the highest priority and the highest case fatality rate
 * being the lowest priority.
 * <p>
 * After the stack and queue are filled, the user is prompted repeatedly to enter a number corresponding to an action on a menu, until 7 is entered, 
 * then the program will terminate.
 * 
 * @author Brandon Rocha
 *@version October 5, 2022
 */
public class Project2 {

	public static void main(String[] args) {
		Country example = new Country();
		int cAsize = 145;
		Country cArray[] = new Country[cAsize]; //create array of country objects
		Country sArray[] = new Country[cAsize];
		int i = 0;
		
		Stack countryStack = new Stack(cAsize);
		PriorityQ priQue = new PriorityQ(cAsize);
		
		cArray[0] = example; //insert country into array index 0
		
		Scanner openFl = new Scanner(System.in);
		System.out.print("Enter filename: ");
		String filename = openFl.next();

		
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e ){
			System.out.println("Unable to open the file: "+filename);
			System.exit(1);
		}
		inFile.useDelimiter(",|\n");
		//get through the first line
		inFile.nextLine();
		
		while(inFile.hasNext()) {
			example.setName(inFile.next());
			example.setCapitol(inFile.next());
			example.setPop(inFile.nextDouble());
			example.setGdp(inFile.nextDouble());
			example.setCases(inFile.nextLong());
			example.setDeaths(inFile.nextLong());
			example.setArea(inFile.nextLong());
			
			
			cArray[i] = new Country(example.getName(), example.getCapitol(), example.getPop(),example.getGdp(),example.getCases(), example.getDeaths(), example.getArea());
			sArray[i] = new Country(example.getName(), example.getCapitol(), example.getPop(),example.getGdp(),example.getCases(), example.getDeaths(), example.getArea());
			countryStack.push(sArray[i]); // push each country read from file to stack
			++i;
		} //close while loop
		
		int outer, inner, lowest; // sort array by CFR to insert into priority queue
		for(outer = 0 ; outer < cAsize-1 ; outer++) {
			lowest = outer;
			for(inner = outer+1; inner < cAsize; inner++) {
				if(cArray[inner].getFatal() > cArray[lowest].getFatal()) {
					lowest = inner;
				}
			}//end of inner loop
			if(lowest != outer) {
				Country temp = cArray[lowest];
				cArray[lowest] = cArray[outer];
				cArray[outer] = temp;
			}
		}//end of outer for loop
		
		for (int z = cAsize-1; z >=0 ; --z) { //insert sorted array into priority queue
			priQue.insert(cArray[z]);
		}
		
		Scanner menuOption = new Scanner(System.in);
		String input = "0";
		Country temp = new Country();
		Country temp2 = new Country();
		String name;
		String cap;
		double pop;
		double gdp;
		double cases;
		double deaths;
		double area;
		//Scanner createCountry = new Scanner(System.in);
		//Scanner createPri = new Scanner(System.in);
		while(!input.equals("7")) {
			Project2.printMenu();
			input = menuOption.next();
			System.out.println();
			if (input.equals("1")) {
				countryStack.printStack(countryStack);
			}//end option 1
			
			else if (input.equals("2")) {
				if(!countryStack.isEmpty()) {
				temp = countryStack.pop();
				System.out.println("The following country has been popped from the stack: " +temp.getName());
				}
			}//end option 2
			
			else if (input.equals("3")) {
				

				Scanner createCountry = new Scanner(System.in);
				Country addCountry = new Country();
				
				if (!countryStack.isFull()) {
					System.out.print("Enter name: ");
					name = createCountry.nextLine();
					System.out.print("Enter capitol: ");
					cap = createCountry.nextLine();
					System.out.print("Enter population: ");
					pop = createCountry.nextDouble();
					System.out.print("Enter GDP: ");
					gdp = createCountry.nextDouble();
					System.out.print("Enter COVID cases: ");
					cases = createCountry.nextDouble();
					System.out.print("Enter COVID deaths: ");
					deaths = createCountry.nextDouble();
					System.out.print("Enter Area(km^2): ");
					area = createCountry.nextDouble();
					System.out.println();
				
					addCountry.setName(name);
					addCountry.setCapitol(cap);
					addCountry.setPop(pop);
					addCountry.setGdp(gdp);
					addCountry.setCases(cases);
					addCountry.setDeaths(deaths);
					addCountry.setArea(area);
				
					countryStack.push(addCountry);
				
					System.out.println("The following country has been added to the stack: " + addCountry.getName());
				}
				else {
					System.out.println("Stack is full, nothing can be pushed. You may pop a country and try again.");
				}
			}//end option 3
			
			else if (input.equals("4")) {
				priQue.printQue(priQue);
			}//end option 4
			
			else if (input.equals("5")) {
				if(!priQue.isEmpty()) {
					temp2 = priQue.remove();
					System.out.println("The following country has been removed from the priority queue: " + temp2.getName());
				}
			}//end option 5
			
			else if(input.equals("6")) {
				
				if (!priQue.isFull()) {
					Scanner createPri = new Scanner(System.in);
					Country queCountry = new Country();
					System.out.print("Enter name: ");
					name = createPri.nextLine();
					System.out.print("Enter capitol: ");
					cap = createPri.nextLine();
					System.out.print("Enter population: ");
					pop = createPri.nextDouble();
					System.out.print("Enter GDP: ");
					gdp = createPri.nextDouble();
					System.out.print("Enter COVID cases: ");
					cases = createPri.nextDouble();
					System.out.print("Enter COVID deaths: ");
					deaths = createPri.nextDouble();
					System.out.print("Enter Area(km^2): ");
					area = createPri.nextDouble();
					System.out.println();
				
					queCountry.setName(name);
					queCountry.setCapitol(cap);
					queCountry.setPop(pop);
					queCountry.setGdp(gdp);
					queCountry.setCases(cases);
					queCountry.setDeaths(deaths);
					queCountry.setArea(area);
				
					System.out.println("The following country has been added to the priority queue: " + queCountry.getName());
					
					priQue.insert(queCountry);
				}
				else {
					System.out.println("Priority queue is full, nothing can be added. You may remove a country and try again");
				}
			}//end option 6
			
			else if (input.equals("7")){
				break;
			}// end option 7
			
			else {
				System.out.println("That is not a valid menu option, please enter number between 1 and 6 or 7 to quit.");
			}
			System.out.println();
		}//end of while loop
		System.out.println("Goodbye!");
		openFl.close();
		menuOption.close();
		inFile.close();
	}//end of main
	/**
	 * This method is called to print a menu, each option allowing the user to print, pop, or push a stack of Country objects or 
	 * print, delete, or insert a seperate priority queue of Country objects that were created in the Project2 class.
	 * 
	 */
	public static void printMenu () {
		System.out.print("1) Print stack \r\n"
				+ "2) Pop a country object from stack \r\n"
				+ "3) Push a country object onto stack \r\n"
				+ "4) Print priority queue \r\n"
				+ "5) Remove a country object from priority queue \r\n"
				+ "6) Insert a country object into priority queue \r\n"
				+ "7) Exit \n"
				+ "Enter your choice: ");
	} //end of printMenu
}
