import java.util.Random;


public class AirSoldier extends Soldier {
	//Extends from abstract class Soldier
	String type = "Air";

	public AirSoldier(String name, String ID, String vehicle) {
		super(name, ID, vehicle);
	}

	//Implementing abstract methods
	public void move(String status,IAvailability avail)
	{
		if(status.equalsIgnoreCase("attack")){
			if(avail.isAvailable()){
				this.atBase = false;
				System.out.println("Air Soldier(Boarded Transport): "+this.name+" "+this.ID+" "+this.vehicleName);
				System.out.println("Moving in air to attack");
			}
			else{
				System.out.println("Air Soldier(Could not Board Transport): "+this.name+" "+this.ID+" "+this.vehicleName);
				}
			}
		else{
			if(!this.atBase)
			System.out.println(this.name + " Flying Back to base");
			this.atBase = true;
		}
	}
	
	public void shoot() {
		if(!this.atBase){
		Random rand = new Random();
		int x = rand.nextInt(2);
		//Can use Strategy Pattern using enums here
		if (x == 0)
			System.out.println("Firing Primary weapon Air missile");
		else
			System.out.println("Firing secondary weapon grenade");
		}
	}

}
