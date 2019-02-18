package rent_car;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * 
 * @author kellyson
 *
 */

public class RentACar {
	public static void fileWriter(){
		System.out.println("Sintaxe de inserção: <TIPO_DE_CARRO>:<MODELO_DO_CARRO>\n"
				+ "São aceitos os tipos S para esportivo, C para compacto e U para utilitários (SUVs)"
				+ "\nDigite 0 para encerrar o cadastro.");
		
		Scanner in = new Scanner(System.in);
		String reader = in.nextLine();
		ArrayList<String> cars = new ArrayList<String>();
		
		while(!reader.equals("0")){
			cars.add(reader);
			reader = in.nextLine();
		}
		
		try{
	    	BufferedWriter file = new BufferedWriter(new FileWriter("dataloader.txt",false));
	    	
	    	for(String s : cars){
	    		file.write(s.toUpperCase() + "\n");
	    	}
	    	
	    	file.close();
	    }catch(Exception e){
	    	System.out.println("Erro ao escrever no arquivo" + e);
	    }
		
		in.close();
	}
	
	public static boolean fileReader(String name, Store south, Store west, Store north){
		try {
			BufferedReader file = new BufferedReader(new FileReader("dataloader.txt"));
			int counter = 0;
			
			while(file.ready()){
				String line = file.readLine();
				counter += 1;
				
				String type = line.split(":")[0];
				String car = line.split(":")[1];
				
				Car nCar = new Car(car, type);
				
				if (type.equals("C")){
					//southcar
					south.loadCar(nCar);
				} else if (type.equals("S")){
					//westcar
					west.loadCar(nCar);
				} else if (type.equals("U")){
					//northcar
					north.loadCar(nCar);
				} else {
					System.out.println("Há um carro sem tipo especificado reconhecido pelo sistema na linha " + counter + ".");
					System.out.println("O tipo '" + type + "' não está configurado.");
				}
			}
			
			file.close();
			return true;
		} catch(Exception e) {
			System.out.println("Ops! Ocorreu um erro ao ler o arquivo: " + e);
			return false;
		}
	}
	
	public static String rentInStore(Store store, ArrayList<String> days) {
		String rented = store.rentCar(days);
		
		if(rented.equals("error")){
			return("Não possuem carros disponíveis na data indicada.");
		} else {
			return(rented + ": " + store.getName());
		}
	}
	
	public static void system(){
		//Inicializando as lojas
		System.out.println("Inicializando variáveis...");
		Store southcar = new Store("SouthCar", 210, 200, 150, 90);
		Store westcar = new Store("WestCar", 530, 200, 150, 90);
		Store northcar = new Store("NorthCar", 630, 600, 580, 590);
		
		//Lê o arquivo com os dados dos carros
		System.out.println("Carregando dados do disco...");
		boolean readed = fileReader("dataloader.txt", southcar, westcar, northcar);
		
		if(readed){
			System.out.println("Pronto!");
			
			//Início da interface em terminal
			System.out.println("\n\nBem vindo ao Sistema de locação de carros!");
			System.out.println("Para alugar um carro, digite 'a'\nPara sair, digite 's'");
			
			Scanner in = new Scanner(System.in);
			String choice = in.nextLine();
			
			//Laço de execução do sistema
			//Caractere s encerra a repetição
			while(!choice.equals("s")){
				if(choice.equals("a")) {
					System.out.println("\nQual o carro que você precisa?\nSintaxe: <CLIENTE>: <PASSAGEIROS>: <DATA1>, <DATA2>, <DATA3>,...");
					String rent = in.nextLine();
					
					String client, locate[];
					Integer passengers;
					ArrayList<String> days = new ArrayList<String>();
					
					client = rent.split(": ")[0];
					passengers = Integer.parseInt(rent.split(": ")[1]);
					locate = (rent.split(": ")[2]).split(", ");
					
					for(int i = 0; i < locate.length; i += 1){
						days.add(locate[i]);
					}
					
					if(passengers < 1){
						System.out.println("Número de passageiros impossível!");
					} else if (passengers > 7){
						System.out.println("Indisponível!");
					} else {
						Integer priceS = 0, priceW = 0, priceN = 0;
						
						//Percorre os dias calculando o valor do aluguel em cada loja
						//Depois, verifica qual o melhor valor em qual loja
						for(String s : days) {
							if (client.contains("Premium")) {
								//Caso o cliente possua cartão fidelidade
								if(s.contains("sab") || s.contains("dom")) {
									//Se for fim de semana
									priceS += southcar.getPremiumTaxInWeekEnd();
									priceW += westcar.getPremiumTaxInWeekEnd();
									priceN += northcar.getPremiumTaxInWeekEnd();
									
								} else if (s.contains("seg") || s.contains("ter") ||
										   s.contains("qua") || s.contains("qui") || s.contains("sex")){
									//Se for meio da semana
									priceS += southcar.getPremiumTaxInTheWeek();
									priceW += westcar.getPremiumTaxInTheWeek();
									priceN += northcar.getPremiumTaxInTheWeek();
									
								} else {
									//Dia não encontrado
									System.out.println("Dia da semana especificado incorretamente.\nEsperado: seg, ter, qua, qui, sex, sab, dom\n");
								}
								
							} else if(client.contains("Normal")) {
								//Caso o cliente não possua cartão fidelidade
								if(s.contains("sab") || s.contains("dom")) {
									//Se for fim de semana
									priceS += southcar.getNormalTaxInWeekEnd();
									priceW += westcar.getNormalTaxInWeekEnd();
									priceN += northcar.getNormalTaxInWeekEnd();
									
								} else if (s.contains("seg") || s.contains("ter") ||
										   s.contains("qua") || s.contains("qui") || s.contains("sex")){
									//Se for meio da semana
									priceS += southcar.getNormalTaxInTheWeek();
									priceW += westcar.getNormalTaxInTheWeek();
									priceN += northcar.getNormalTaxInTheWeek();
									
								} else {
									//Dia não encontrado
									System.out.println("Dia da semana especificado incorretamente.\nEsperado: seg, ter, qua, qui, sex, sab, dom\n");
								}
							} else {
								//Caso o tipo de cliente informado seja inválido
								System.out.println("Tipo de cliente inválido!\nCliente deve ser 'Normal' ou 'Premium'");
							}
						}
	
						//Aluga no melhor lugar caso possível
						
						
						if(passengers > 4) {
							//Caso com mais de 4 passageiros -> Tenta alugar SUV
							System.out.println(rentInStore(northcar, days));
							
						} else if (passengers > 2) {
							//Caso com mais de 2 passageiros -> Verifica qual o melhor preço para alugar (Compacto vs. SUV)
							if(priceS > priceN) {
								//SUV mais barato que compacto -> Tenta alugar SUV
								System.out.println(rentInStore(northcar, days));
							} else {
								//Compacto mais barato que SUV -> Tenta alugar compacto
								String result = rentInStore(southcar, days);
								
								//Testa se foi possível alugar o compacto
								if(result.equals("Não possuem carros disponíveis na data indicada.")){
									//Não foi possível. Tenta alugar SUV
									System.out.println(rentInStore(northcar, days));
								} else {
									//Foi possível. Imprime resultado
									System.out.println(result);
								}
							}
						} else {
							//Caso com menos de dois passageiros -> Verifica se esportivo é mais barato
							if(priceN > priceS && priceS > priceW){
								String result = rentInStore(westcar, days);
								
								//verifica se foi possível alugar esportivo
								if(!result.equals("Não possuem carros disponíveis na data indicada.")){
									//Foi possível alugar
									System.out.println(result);
								} else {
									//Não foi possível alugar esportivo -> Tenta alugar Compacto ou SUV
									if(priceS > priceN){
										//SUV mais barato que compacto -> Tenta alugar SUV
										System.out.println(rentInStore(northcar, days));
									} else {
										//Compacto mais barato que SUV -> Tenta alugar compacto
										result = rentInStore(southcar, days);
										
										//Testa se foi possível alugar o compacto
										if(result.equals("Não possuem carros disponíveis na data indicada.")){
											//Não foi possível. Tenta alugar SUV
											System.out.println(rentInStore(northcar, days));
										} else {
											//Foi possível. Imprime resultado
											System.out.println(result);
										}
									}
								}
							} else if (priceW > priceS && priceS > priceN){
								//SUV é mais barato
								String result = rentInStore(northcar, days);
								
								//verifica se foi possível alugar SUV
								if(!result.equals("Não possuem carros disponíveis na data indicada.")){
									//Foi possível alugar
									System.out.println(result);
								} else {
									//Não foi possível alugar SUV -> Tenta alugar Compacto ou esportivo
									if(priceW > priceS){
										//Esportivo mais barato que compacto -> Tenta alugar esportivo
										System.out.println(rentInStore(westcar, days));
									} else {
										//Compacto mais barato que esportivo -> Tenta alugar compacto
										result = rentInStore(southcar, days);
										
										//Testa se foi possível alugar o compacto
										if(result.equals("Não possuem carros disponíveis na data indicada.")){
											//Não foi possível. Tenta alugar esportivo
											System.out.println(rentInStore(westcar, days));
										} else {
											//Foi possível. Imprime resultado
											System.out.println(result);
										}
									}
								}
							} else {
								//Compacto é mais barato
								String result = rentInStore(southcar, days);
								
								//verifica se foi possível alugar compacto
								if(!result.equals("Não possuem carros disponíveis na data indicada.")){
									//Foi possível alugar
									System.out.println(result);
								} else {
									//Não foi possível alugar compacto -> Tenta alugar esportivo ou SUV
									if(priceW > priceN){
										//SUV mais barato que esportivo -> Tenta alugar SUV
										System.out.println(rentInStore(northcar, days));
									} else {
										//Esportivo mais barato que SUV -> Tenta alugar Esportivo
										result = rentInStore(westcar, days);
										
										//Testa se foi possível alugar o esportivo
										if(result.equals("Não possuem carros disponíveis na data indicada.")){
											//Não foi possível. Tenta alugar SUV
											System.out.println(rentInStore(northcar, days));
										} else {
											//Foi possível. Imprime resultado
											System.out.println(result);
										}
									}
								}
							}
							
						}
					}
				} else {
					System.out.println("Opção inválida!");
				}
				
				choice = in.nextLine();
			}
			
			in.close();
		} else {
			System.out.println("Devido ao erro na leitura do arquivo, o sistema não pôde ser iniciado.");
		}
	}
	
	public static void main(String[] args){
		System.out.println("Você deseja executar o sistema, ou criar um arquivo com os dados dos carros?\n"
				+ "1: Sistema\n2: Criar arquivo (Sobrescreve o anterior)\n0: Encerrar aplicação\n");
		Scanner in = new Scanner(System.in);
		
		Integer choice = in.nextInt();
		
		if(choice.equals(1)){
			system();
		} else if (choice.equals(2)) {
			fileWriter();
			
			System.out.println("Deseja iniciar o sistema? S/n");
			String ans;
			ans = in.nextLine();
			
			if((ans.toUpperCase()).equals("S")) {
				system();
			}
		}
		
		System.out.println("\n\n~ Aplicação encerrada");
		in.close();
	}
}