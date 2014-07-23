import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class BaseCamp implements IObservable{
	//Singleton Observable class
	
	// Create the list of Observers
	static List<Soldier>l=new ArrayList<Soldier>();
	static String state; //Current state(attack/defend)
	private static BaseCamp instance; 
	
	//To add Observers to the list
	public static void subscribe(Soldier d)
	{
		l.add(d);
	}
	private BaseCamp(String state)
	{
		this.state=state;
	}
	
	//To make it BaseCamp Singleton Class
	public static BaseCamp getInstance()
	{
		if(instance==null)
			instance=new BaseCamp("fight");
		return instance;
	}
	
	//Publish the message to all Observers
	public void inform(String status) {
		
		Iterator<Soldier>iterator=l.iterator();
		state=status;
		IAvailability avail = new Transport(2);
		while(iterator.hasNext())
		{
			Soldier s=iterator.next();
			s.getUpdate(state,avail);
		}		
	}
	
	public static void main(String args[])
	{
		BaseCamp b=BaseCamp.getInstance();
		//Using Factory method to create objects
		SoldierFactory sf= new SoldierFactory();
		Soldier s1=sf.getSoldier("abc","123","xylo","GroundSoldier");
		Soldier s2=sf.getSoldier("xyz","321","avenger","WaterSoldier");
		Soldier s3=sf.getSoldier("ghy","231","Yamaha","AirSoldier");
		b.inform("attack");
		b.inform("return to base");
	}
}
