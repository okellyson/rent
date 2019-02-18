package rent_car;

import java.util.ArrayList;

/**
 * 
 * @author kellyson
 *
 */

public class Store {
	//Nome da loja
	private String name;
	//Array para armazenar taxas
	private Integer[] prices;
	//Array contendo os carros disponíveis na loja
	private ArrayList<Car> cars;
	
	/*
	//Taxa para Cliente Normal durante a semana
	private Integer NormalTaxInTheWeek;
	//Taxa para Cliente Premium durante a semana
	private Integer PremiumTaxInTheWeek;
	//Taxa para Cliente Normal no fim de semana
	private Integer NormalTaxInWeekEnd;
	//Taxa para Cliente Premium no fim de semana
	private Integer PremiumTaxInWeekEnd;
	*/
	
	public Store(String name, Integer ntw, Integer ptw, Integer nte, Integer pte) {
		this.name = name;
		this.prices = new Integer[4];
		this.prices[0] = ntw; //Normal Tax Week
		this.prices[1] = ptw; //Premium Tax Week
		this.prices[2] = nte; //Normal Tax weekEnd
		this.prices[3] = pte; //Premium Tax weekEnd
		/*this.NormalTaxInTheWeek = ntw;
		this.PremiumTaxInTheWeek = ptw;
		this.NormalTaxInWeekEnd = nte;
		this.PremiumTaxInWeekEnd = pte;*/
		this.cars = new ArrayList<Car>();
	}
	
	public String getName() {
		return name;
	}
	
	public void loadCar(Car car) {
		cars.add(car);
	}
	
	public String rentCar(ArrayList<String> daysToRent){
		boolean rented = false;
		ArrayList<String> daysRented = new ArrayList<String>();
		
		for(Car car : cars) {
			//Recebe os dias em que um carro é alugado
			daysRented = car.getRented();
			
			//Percorre o vetor de dias a alugar e compara se ainda não foi alugado
			for(String aDayToRent : daysToRent) {
				for(String dayRented : daysRented) {
					
					if(dayRented.contains(aDayToRent)) {
						rented = true;
					}
				}
			}
			
			if(!rented) {
				car.setRented(daysToRent);
				return car.getName();
			}
		}
		
		return "error";
	}

	public Integer getNormalTaxInTheWeek() {
		return this.prices[0];
	}

	public Integer getPremiumTaxInTheWeek() {
		return this.prices[1];
	}

	public Integer getNormalTaxInWeekEnd() {
		return this.prices[2];
	}

	public Integer getPremiumTaxInWeekEnd() {
		return this.prices[3];
	}
}
