//Interface to be implemented by SoldierFactory
public interface ISoldierFactory {
	public Soldier getSoldier(String name,String ID,String vehicle,String type);
}
