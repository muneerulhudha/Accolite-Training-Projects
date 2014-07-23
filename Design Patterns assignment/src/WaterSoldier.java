import java.util.Random;

public class WaterSoldier extends Soldier{
	//Extends from abstract class Soldier
	String type="Water";
	public WaterSoldier(String name, String ID,String vehicle) {
		super(name, ID,vehicle);
	}
	
	//Implementing abstract methods
	public void move(String status,IAvailability avail)
	{
		if(status.equalsIgnoreCase("attack")){
			if(avail.isAvailable()){
				this.atBase = false;
				System.out.println("Water Soldier(Boarded Transport): "+this.name+" "+this.ID+" "+this.vehicleName);
				System.out.println("Moving in water to attack");
			}
			else{
				System.out.println("Water Soldier(Could not Board Transport): "+this.name+" "+this.ID+" "+this.vehicleName);
				}
			}
		else{
			if(!this.atBase)
			System.out.println(this.name +" Sailing to base");
			this.atBase = true;
		}
	}
	
	public void shoot()
	{
		if(!this.atBase){
		Random rand = new Random();
		int x = rand.nextInt(2);
		//Can use Strategy Pattern using enums here
		if(x==0)
			System.out.println("Firing Primary weapon submarine missile");
		else
			System.out.println("Firing secondary weapon torpedo");
		}
	}
}
