
public abstract class shoppingList  {
	private int length; // length of list
	
	shoppingList() {
		length = 7;
	}
	
	shoppingList(int len){
		length=len;
	}
	
	//copy constructor
	shoppingList(shoppingList list1){
		this(list1.getLength());
	}
	
	//equals method
	public boolean equals(shoppingList otherObject){
		return (this.length==otherObject.length);
	}

	public void setLength(int len){
		length=len;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public abstract void goShopping();
}
