
public class Transport implements IAvailability {
	static int num_of_tickets; //Number of tickets available
	
	Transport(int n){
		num_of_tickets=n;
	}
	
	//Method to assign a ticket to Soldier
	public boolean isAvailable(){
		if(num_of_tickets>0)
		{
			num_of_tickets-- ;
			return true;
		}
		return false;
	}

}
