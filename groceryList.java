import java.util.ArrayList;
import java.util.Scanner;


public class groceryList extends shoppingList implements shoppingMethods, printList {
	
	private ArrayList<Double> cost; // cost of each item (double values)
	private ArrayList<String> cart; // names of items, stored as strings
	private ArrayList<Integer>priority; // priorities of items (int values)
	private ArrayList<Integer> purchased; // was the item purchased?
	private ArrayList<Integer> quantity;
	private double bankAccount; // the budget cannot exceed 80% of the cost of the
	int arrayListLength;
	private String name;
	
	groceryList(){
		cost = new ArrayList<Double>();
		cart = new ArrayList<String>();
		purchased = new ArrayList<Integer>();
		priority = new ArrayList<Integer>();
		quantity=new ArrayList<Integer>();
		bankAccount= 0;
		arrayListLength=0;
		
	}
	
	groceryList(int length){
		setLength(length);
		cost = new ArrayList<Double>();
		cart = new ArrayList<String>();
		purchased = new ArrayList<Integer>();
		priority = new ArrayList<Integer>();
		quantity=new ArrayList<Integer>();
		bankAccount = 0;
		arrayListLength=0;
		
	}

	groceryList(groceryList list1){
		this(list1.getLength());
	}
	
	public void setName(String n){
		name=n;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean equals(groceryList otherObject){
		if (this.getLength() != otherObject.getLength())
			return false;
		for (int i=0; i<arrayListLength;i++){
			if (this.cart.get(i) != otherObject.cart.get(i))
				return false;
		}
		return true;	
	}
	
	public void setCart(String item, int i) {
		this.cart.add(item);
		this.priority.add(arrayListLength++);
		this.purchased.add(0);
		this.quantity.add(0);
	}

	public String getCart(int i) {
		return this.cart.get(i);
	}

	public void setCost(int i, double j) {
		this.cost.add(j);
	}

	//removes numbers and whitespace from items
	public String removeNumbers(int i) {
		for (int j = 0; j < cart.get(i).length(); j++) {
			// if character is a number or whitespace, delete it from the array
			if (this.cart.get(i).charAt(j) >= 48 && this.cart.get(i).charAt(j) <= 57
					|| this.cart.get(i).charAt(j) == 32) {
				StringBuilder sb = new StringBuilder(this.cart.get(i));
				sb.deleteCharAt(j);
				this.cart.set(i, sb.toString());
				j--;
			}
		}
		return this.cart.get(i); // returns the name with the numbers removed
	}
	
	public String removeNumbers(String name) {
		for (int i = 0; i < name.length(); i++) {
			// if character is a number or whitespace, delete it from the array
			if (name.charAt(i) >= 48 && name.charAt(i) <= 57
					|| name.charAt(i) == 32) {
				StringBuilder sb = new StringBuilder(name);
				sb.deleteCharAt(i);
				name = sb.toString();
				i--;
			}
		}
		return name; // returns the name with the numbers removed
	}

	//checks the list for repeat items 
	public void checkRepeats() {
		try{
		for (int i = 0; i < arrayListLength-1; i++) {
			for (int j = i + 1; j < arrayListLength; j++) {
				if (this.cart.get(i).equals(this.cart.get(j))) {
					System.out.println("You have entered " + this.cart.get(j)
							+ " multiple times." + "\n"
							+ "Please enter a new item:");
					Scanner keyboard = new Scanner(System.in);
					this.cart.set(j, keyboard.nextLine());
					this.removeNumbers(j);
					System.out.println("Enter the cost of " + this.getCart(j)
							+ ":");
					//error checks to ensure a number was entered
					while (!keyboard.hasNextDouble()) {
						String input = keyboard.nextLine();
						if (input.equals("exit"))
							throw new exitException();
						System.out.println("Invalid. Enter a number.");
					}
					this.cost.set(j, keyboard.nextDouble());
					while (this.cost.get(j)<0){
						System.out.println("Invalid. Enter a positive number.");
						while (!keyboard.hasNextDouble()) {
							String input = keyboard.nextLine();
							if (input.equals("exit"))
								throw new exitException();
							System.out.println("Invalid. Enter a number.");
						}	
						}
						this.cost.set(j, keyboard.nextDouble());
						i = 0;
						j = 0;
					}
					
				}
			}
		}
	catch(exitException e){
		System.out.println(e.getMessage());	
		System.exit(0);
	}
	}


	public void printList() {
		try{
		System.out.println("The items in your cart are: ");
		Scanner keyboard = new Scanner(System.in);
		for (int i = 0; i < arrayListLength; i++) {
					System.out.println(i+1 + ". " + cart.get(i) + " $" + cost.get(i));
		}
		System.out.println("\n"
				+ "The items are listed by their priority. Would you like to change the priority?"
				+ "\n" + "Enter \"yes\" or \"no\".");
		String answer = keyboard.nextLine();
		if (answer.equals("exit"))
			throw new exitException();
		while (!answer.equals("yes") && !answer.equals("no")) {
			System.out.println("Invalid input. Enter \"yes\" or \"no\":");
			answer = keyboard.nextLine();
			if (answer.equals("exit"))
				throw new exitException();
		}
		//calls method to change priorities
		if (answer.equals("yes")) {
			this.setPriority();
			this.printList();
		} else if (answer.equals("no")) {
			double amount = 0;
			for (int i = 0; i < getLength(); i++) {
				amount += (cost.get(i)*quantity.get(i));
			}
			System.out.format("The cost of the items on your list is $%.2f",
					amount);
			System.out
			.format("\n"
					+ "Enter a budget that is not more than 80%% ($%.2f) of this cost: ",
					amount * .8);
			while (!keyboard.hasNextDouble()) {
				String bad = keyboard.nextLine();
				if (bad.equals("exit"))
					throw new exitException();
				System.out
				.format("Invalid. Enter a decimal number that is not more than 80%% ($%.2f) of this cost: ",
						amount * .8);
			}
			bankAccount = keyboard.nextDouble();
			//makes sure the bankAccount is lower than the total cost
			while (bankAccount <= 0 || bankAccount > (amount * .8)) {
				System.out.format("Invalid. Enter a number between 0 and %.2f",
						amount * .8);
				//checks that user entered a number
				while (!keyboard.hasNextDouble()) {
					String bad = keyboard.nextLine();
					if (bad.equals("exit"))
						throw new exitException();
					System.out
					.format("Invalid. Enter a decimal number that is not more than 80%% ($%.2f) of this cost: ",
							amount * .8);
				}
				bankAccount = keyboard.nextDouble();
			}
		}
		}catch(exitException e){
			System.out.println(e.getMessage());	
			System.exit(0);
		}
	}

	public void setPriority() {
		try{
		
		for (int i = 0; i < getLength(); i++) {
			System.out.println("Enter a priority from 1 to " + getLength() + " for "
					+ this.cart.get(i));
			Scanner keyboard = new Scanner(System.in);
			//makes sure user enters an integer
			while (!keyboard.hasNextInt()) {
				String num = keyboard.nextLine();
				if (num.equals("exit"))
					throw new exitException();
				System.out.println("Invalid. Enter an integer from 1 to "
						+ getLength() + " for " + this.cart.get(i));
			}

			int rank = keyboard.nextInt();
			//makes sure the rank is between 1 & length of list
			while (rank < 1 || rank > arrayListLength) {
				System.out.println("Invalid. Enter a rank between 1 and "
						+ arrayListLength + ":");
				while (!keyboard.hasNextInt()) {
					String num = keyboard.nextLine();
					if (num.equals("exit"))
						throw new exitException();
					System.out.println("Invalid. Enter an integer from 1 to "
							+ arrayListLength + " for " + this.cart.get(i));
				}
				rank = keyboard.nextInt();
			}
			this.priority.set(i, rank);
		}
		this.cart=quickSort(this.cart, this.priority, 0, arrayListLength-1);
		priorityRepeat();
		}catch(exitException e){
			System.out.println(e.getMessage());	
			System.exit(0);
		}
	}

	public ArrayList<String> quickSort(ArrayList<String> A, ArrayList<Integer> B, int p, int r){
		if (p<r){
			int q=partition(A,B, p, r);
			quickSort(A,B, p, q-1);
			quickSort(A,B, q+1, r);
		}
		return A;
	}
	
	public int partition(ArrayList<String> A,ArrayList<Integer> B, int p, int r){
		int x=B.get(r);
		int i=p-1;
		for (int j=p; j<r;j++){
			if (B.get(j)<=x){
				i++;
				String temp1=A.get(i);
				A.set(i, A.get(j));
				A.set(j, temp1);
				Integer temp=B.get(i);
				B.set(i, B.get(j));
				B.set(j, temp);
				Double temp2=cost.get(i);
				cost.set(i, cost.get(j));
				cost.set(j,temp2);
				temp=quantity.get(i);
				quantity.set(i, quantity.get(j));
				quantity.set(j, temp);
			}
		}
		Integer temp=B.get(i+1);
		B.set(i+1,B.get(r));
		B.set(r, temp);
		String temp2=A.get(i+1);
		A.set(i+1, A.get(r));
		A.set(r, temp2);
		Double temp3=cost.get(i+1);
		cost.set(i+1,cost.get(r));
		cost.set(r, temp3);
		temp=quantity.get(i+1);
		quantity.set(i+1, quantity.get(r));
		quantity.set(r, temp);
		return i+1;
	}
	//makes sure that no rank was repeated
	public void priorityRepeat() {
		for (int i = 0; i < arrayListLength-1; i++) {
				if (priority.get(i) == priority.get(i+1)) {
					System.out
					.println("You have entered the same priority for at least two items.");
					System.out
					.println("Please make each item a different priority.");
					this.setPriority();
				
			}
		}
	}

	//gets as many items on the list without going over the bankAccount
	public void goShopping() {
		double amount = 0;
		System.out.println(this.name+"'s grocery list:");
		for (int i = 0; i < getLength(); i++) {
				if ((amount + cost.get(i)) <= this.bankAccount && quantity.get(i)>0) {
					
					amount += this.cost.get(i);
					quantity.set(i, (quantity.get(i)-1));
					purchased.set(i,(purchased.get(i)+1));
					i--;
				}
		}
	printResults(amount);
		
	}
	
	private void printResults(double amount){
		
		
		System.out.println("__________________________________________________________________");
		System.out.println("Item         |" + "  Price  |"
				+ " Purchased? |" + " Quantity");
		System.out.println("------------------------------------------------------------------");
		for (int i = 0; i < getLength(); i++) {
			if (purchased.get(i) > 0) {
				System.out.format("%-12s %-1s %-1s %-5.2f %-1s %-10s %-1s %-9d", cart.get(i), "|",
						"$", cost.get(i), "|", "yes","|", purchased.get(i));
				System.out.println("\n");
				
			}
		}
		
		for (int i = 0; i < getLength(); i++) {
			if (quantity.get(i) > 0) {
				System.out.format("%-12s %-1s %-1s %-5.2f %-1s %-10s %-1s %-9d", cart.get(i), "|",
						"$", cost.get(i), "|", "no", "|", quantity.get(i));
				System.out.println("\n");
			}
		}
		System.out.format("\n" + "You spent $%.2f", amount);
		System.out.format("\n"+ "You have $%.2f left in your bank account", (bankAccount-amount));
	}

	public double getbankAccount() {
		return this.bankAccount;
	}
	
	public void setQuantity(int i, int quant){
		this.quantity.set(i, quant);
	}

}
