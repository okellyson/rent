package rent_car;

import java.util.ArrayList;

/**
 * 
 * @author kellyson
 *
 */

public class Car {
	private String name;
	private String type;
	private ArrayList<String> rented;
	
	public Car(String name, String type){
		this.name = name;
		this.type = type;
		this.rented = new ArrayList<String>();
	}

	public ArrayList<String> getRented() {
		return rented;
	}

	public void setRented(ArrayList<String> rent) {
		rented.addAll(rent);
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}
