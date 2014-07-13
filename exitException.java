
public class exitException extends Exception{
	
	public exitException(){
		super("Exiting the Program");
	}
	
	public exitException(String message){
		super(message);
	}

}
