import java.util.Random;


public class GroundSoldier extends Soldier{
	//Extends from abstract class Soldier
	String type="Ground";
	public GroundSoldier(String name, String ID,String vehicle) {
		super(name, ID,vehicle);
	}
	
	//Implementing abstract methods
	public void move(String status,IAvailability avail)
	{
		if(status.equalsIgnoreCase("attack")){
			if(avail.isAvailable()){
				this.atBase = false;
				System.out.println("Ground Soldier(Boarded Transport): "+this.name+" "+this.ID+" "+this.vehicleName);
				System.out.println("Moving on ground to attack");
			}
			else{
				System.out.println("Ground Soldier(Could not Board Transport): "+this.name+" "+this.ID+" "+this.vehicleName);
				}
			}
		else{
			if(!this.atBase)
			System.out.println(this.name + " Returning to base");
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
			System.out.println("Firing Primary weapon AK47");
		else
			System.out.println("Firing secondary weapon Pistol");
		}
	}
	
}
