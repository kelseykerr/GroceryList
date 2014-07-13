import java.util.ArrayList;

/** An interface for methods that allow the user to make a shopping list and set 
 * the priority of the items as well as the budget.
 * @author kelseykerr
 *
 */
public interface shoppingMethods {
	
	public String removeNumbers(int i);
	public void checkRepeats();
	public void printList();
	public void setPriority();
	public ArrayList<String> quickSort(ArrayList<String> A, ArrayList<Integer> B, int p, int r);
	public int partition(ArrayList<String> A,ArrayList<Integer> B, int p, int r);
	public void priorityRepeat();
	public double getbankAccount();
	
}
