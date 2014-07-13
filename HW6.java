import java.util.Scanner;

public class HW6 {

	public static void main(String[] args) {
		/*
		 * the object "groceryList" will hold all the info about the list,
		 * including: names of items, cost, priority, budget, and whether or not
		 * the item was purchased.
		 */
		groceryList list1 = new groceryList();
		String input;
		Double cost;
try{
	System.out.println("Enter \"exit\" to exit the program at any time");
	System.out.println("Please enter your name:");
	Scanner keyboard1 = new Scanner(System.in);
	list1.setName(keyboard1.nextLine());
	if (list1.getName().equals("exit"))
		throw new exitException();
	list1.setName(list1.removeNumbers(list1.getName()));
	

		for (int i = 0; i < 7; i++) {

			System.out.println("Enter an item for your shopping cart:");
			Scanner keyboard = new Scanner(System.in);
			list1.setCart(keyboard.nextLine(), i);
			if (list1.getCart(i).equals("exit"))
				throw new exitException();
			// removeNumbers will remove whitespace and numbers from the name
			list1.removeNumbers(i);
			System.out.println("Enter the cost of " + list1.getCart(i)
					+ ":");
			// prompt the user until a double is entered for the cost
			
			while (!keyboard.hasNextDouble()) {
				input = keyboard.nextLine();
				if (input.equals("exit"))
					throw new exitException();
				System.out.println("Invalid. Enter a number.");
			}
			cost = keyboard.nextDouble();
			while (cost<0){
				System.out.println("Invalid. Enter a positive number.");
				while (!keyboard.hasNextDouble()) {
					input = keyboard.nextLine();
					if (input.equals("exit"))
						throw new exitException();
					System.out.println("Invalid. Enter a number.");
				}
				cost = keyboard.nextDouble();
				
			}
			list1.setCost(i, cost)
			;
			System.out.println("Enter the quantity of " + list1.getCart(i)
					+ ":");
			while (!keyboard.hasNextInt()) {
				input = keyboard.nextLine();
				if (input.equals("exit"))
					throw new exitException();
				System.out.println("Invalid. Enter an integer greater than or equal to 1.");
			}
			int quantity = keyboard.nextInt();
			while (quantity<1){
				System.out.println("Invalid. Enter an integer greater than or equal to 1.");
				while (!keyboard.hasNextInt()) {
					input = keyboard.nextLine();
					if (input.equals("exit"))
						throw new exitException();
					System.out.println("Invalid. Enter a number.");
				}
				quantity = keyboard.nextInt();
			}
				
				list1.setQuantity(i, quantity);
			/*
			 * if the last item is reached, check to see if the user entered any
			 * duplicate items. Call the function to print the items and obtain
			 * priorities. Finally, "goShopping" and see what items can be
			 * purchased within the budget
			 */
			if (i == 6) {
				list1.checkRepeats();
				list1.printList();
				System.out.println("Time to go shopping with $"
						+ list1.getbankAccount());
				System.out.println("\n");
				list1.goShopping();
			}
		}
	}
	catch(exitException e){
		System.out.println(e.getMessage());	
		System.exit(0);
	}
	}
}
