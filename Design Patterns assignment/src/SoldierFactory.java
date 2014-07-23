import java.lang.reflect.InvocationTargetException;


public class SoldierFactory implements ISoldierFactory {
	//Factory Method to create and return Soldier Objects
	public Soldier getSoldier(String name,String ID,String vehicle,String type){
		Soldier kind = null;
		try {
			//Using Reflections to create Soldier Objects
			kind = (Soldier)Class.forName(type).getConstructor(String.class,String.class,String.class).newInstance(name,ID,vehicle);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return kind;

	}

}
