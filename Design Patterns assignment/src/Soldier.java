
abstract public class Soldier implements IObserver{

	String name; //Name of the soldier
	String ID; //ID of the soldier
	String vehicleName; //Soldier's Vehicle
	boolean atBase = true; //To know the position of soldier(Base/Enemy)
	
	Soldier(String name,String ID,String vehicle)
	{
		this.name=name;
		this.ID=ID;
		this.vehicleName=vehicle;
		BaseCamp.subscribe(this); //Subscribing to the Observable BaseCamp
	}
	public void getUpdate(String status,IAvailability avail)
	{
		//Receiving Updates from Observable BaseCamp
		if(status.equalsIgnoreCase("attack"))
		{
			this.move(status,avail);
			this.shoot();
		}
		else
			this.move(status,avail);
	}
	
	abstract public void move(String status,IAvailability avail);
	
	abstract public void shoot();
	
}
