package ui;
import java.util.Scanner;
import java.time.LocalDate;

import model.WetlandsManager;

/*
To compile:  javac -cp src src/ui/Template.java -d bin
To execute:  java -cp bin ui.Template
*/

/*
Documentacion..
javadoc src/ui/Template.java -d doc/API
*/

/**
 * Description:
 * The wetlands of the municipality have been delimited and inventoried. 
 * At this time, the municipality of Santiago de Cali has 80 wetlands. The program is able
 * to create a wetland and add species and events to it, and be capable of Display the name of the wetland with fewer species of flora. 
 * Given the name of a species, display the wetlands where it is found.
 * Display the information of all our wetlands, including the total number of species by type (do not include information on events). 
 * Display the name of the wetland with the largest number of animals (birds, mammals and aquatic)
 */

public class Main{

	//Atributes
	private WetlandsManager wetlands;
	private Scanner sc;

	//Constructor
	public Main(){
		sc= new Scanner(System.in);
		wetlands= new WetlandsManager("Example");
	}
	public static void main(String[] args) {
		
		Main ppal = new Main();
		int option = 0;

		do{
			option = ppal.showMenu();
			ppal.executeOperation(option);
		}while (option!=0);
		

	}
	/**
	 * 
	 * @return showMenu int, this metod is use for printing a menu in the consol
	 */

	public int showMenu() {
		int option=0;

		System.out.println(
				"Select an option to start\n"+
				"(1) Create Wetland\n"+
				"(2) Register new specie\n"+
				"(3) Register new events\n"+
				"(4) Inform maintainance of wetlands\n"+
				"(5) Search wetland by specie\n"+
				"(6) Information of wetlands\n"+
				"(7) Wetland with the most animals\n"+
				"(8) Wetland with less flora\n"+
				"(0) Exit"
				);
		option = sc.nextInt();
		sc.nextLine();
		return option;
	}
	/**
	 * 
	 * @param operation int, this variable is use for excute the option the user choose.
	 */
	public void executeOperation(int operation){

		switch(operation) {
		case 0:
			System.out.println("SIUUU");
			break;
		case 1:
			createWetland();
			break;
		case 2:
			createEspecie();
			break;
		case 3:
			createEvent();
			break;
		case 4:
			showAmountMaintainance();
			break;
		case 5:
			searchWetlandBySpecie();
			break;
		case 6:
			showWetlands();
			break;
		case 7:
			moreSpeciesFauna();
			break;
		case 8:
			lessSpeciesFlora();
			break;
		default:
			System.out.println("Invalid option.");
		}
	}
	/**
	 * This metod is use for asking the user the information of the wetland
	 */

	public void createWetland(){

		if (wetlands.hasSpace()){
			System.out.println("\nCreating wetland\n");
			String name, squareKilometers, photoUrl, nameZone;
			Boolean zone, type, protectedArea; 
			System.out.println("Name: ");
			name=sc.nextLine();

			System.out.println("Zone: ");
			System.out.println("(false) Urban");
			System.out.println("(true) Rural");
			zone=sc.nextBoolean();

			System.out.println("Type: ");
			System.out.println("(false) Public");
			System.out.println("(true) Private");
			type=sc.nextBoolean();

			System.out.println("Area in Km2: ");
			squareKilometers=sc.next();

			System.out.println("Photo Url: ");
			photoUrl=sc.next();

			System.out.println("Is a protected area?");
			System.out.println("(false) No");
			System.out.println("(true) Yes");
			protectedArea=sc.nextBoolean();

			if(zone==true){
				System.out.println("Name of the corregimiento");
				nameZone=sc.nextLine();
			} else {
				System.out.println("Name of the hood");
				nameZone=sc.next();
			}
		
			System.out.println(wetlands.createWetland(name, squareKilometers, photoUrl, nameZone, zone, type, protectedArea));
			
		} else {
			System.out.println("All wetlands have been added");
		}
	}
	/**
	 * this metod is for asking the user the information of a specie
	 */
	
	public void createEspecie(){
		String commonName, scientificName, name;
    	Boolean migratory;
		int type;

		if(wetlands.comprovationWetland()==0){
			System.out.println("You have to add at least one wetland to add a species");
		} else {
			System.out.println("Name of the wetland you want to add the specie");
			name=sc.nextLine();
			if(wetlands.searchWetland(name)==-1){
				System.out.println("Wetland doesnt exist");
			} else {
				System.out.println("Saving new specie in wetland");
				System.out.println("Name: ");
				commonName=sc.next();

				System.out.println("Scientific name: ");
				scientificName = sc.next();

				System.out.println("The specie is migratory?");
				System.out.println("(false) No");
				System.out.println("(true) Yes");
				migratory = sc.nextBoolean();

				System.out.println("Type of specie: ");
				System.out.println("(1) Land flora");
				System.out.println("(2) Aquatic flora");
				System.out.println("(3) Bird");
				System.out.println("(4) Mammal");
				System.out.println("(5) Aquatic");
				type = sc.nextInt();
				while (type<1||type>5){
					System.out.println("Invalid option");
					type = sc.nextInt();
				}
				int position = wetlands.searchWetland(name);
				System.out.println("Specie register succesfully!");

				wetlands.receiveSpecie(type, commonName, scientificName, migratory, position);
			}
		}
	}
	/**
	 * this metod is use for asking the user the informaiton of a event
	 */

	public void createEvent(){
		String  description, organizer;
		Double value; 
		int type;

		if(wetlands.comprovationWetland()==0){
			System.out.println("You have to add at least one wetland to add a species");
		} else {
			System.out.println("Name of the wetland you want to add the event");
			String name=sc.nextLine();
			if(wetlands.searchWetland(name)==-1){
				System.out.println("Wetland doesnt exist");
			} else {

				System.out.println("Type of event: \n"+
									"(1) Manteinance\n"+
									"(2) School visits\n"+
									"(3) Improvments activities\n"+
									"(4) Celebrations");
				type = sc.nextInt();
				while (type<1||type>5){
					System.out.println("Invalid option");
					type = sc.nextInt();
				}

				System.out.println("Type the day of the date of the event");
				int day=sc.nextInt();
				System.out.println("Type de month");
				int month = sc.nextInt();
				System.out.println("Type de year");
				int year = sc.nextInt();
				sc.nextLine();

				System.out.println("Organizer: ");
				organizer = sc.next();

				System.out.println("Value: ");
				value = sc.nextDouble();
				sc.nextLine();

				System.out.println("Description: ");
				description = sc.next();

				int position = wetlands.searchWetland(name);
				LocalDate fecha = LocalDate.of(year, month, day);

				wetlands.receiveEvent(description, organizer, value, type, fecha, position);
			}
		}
	}
	/**
	 * this metod is use for showing the wetlands
	 */

	public void showWetlands(){
		if(wetlands.comprovationWetland()==0){
			System.out.println("No wetland has been saved. ");
		} else {
		System.out.println(wetlands.showWetlands());
		}
	}

	/**
	 * this metod is use for showing the wetland with less species of flora
	 */
	public void lessSpeciesFlora(){
		if(wetlands.comprovationWetland()==0){
			System.out.println("No wetland has been saved. ");
		} else {
		System.out.println("***WETLAND WITH THE LESS FLORA***");
		System.out.println(wetlands.lessEspecieFlora());
		}
	}
	/**
	 * this metos is use for showing the wetland with more species of fauna
	 */

	public void moreSpeciesFauna(){
		if(wetlands.comprovationWetland()==0){
			System.out.println("No wetland has been saved. ");
		} else {
			System.out.println("***WETLAN WITH THE MOST FAUNA***");
		System.out.println(wetlands.moreEspecieFauna());
		}
	}
	/**
	 * this metod is use for showing the maintainance of each wetland
	 */
	
	public void showAmountMaintainance(){
		if(wetlands.comprovationWetland()==0){
			System.out.println("No wetland has been saved. ");
		} else {
		System.out.println("***MAINTENANCE***");
		System.out.println(wetlands.showManteinance());
		}
		
	}
	/**
	 * this metod is use for showing the wetland(s) that match with the specie given by the user
	 */

	public void searchWetlandBySpecie(){
		if(wetlands.comprovationWetland()==0){
			System.out.println("No wetland has been saved. ");
		} else {
			System.out.println("Name of the specie you want to search.");
			String name = sc.next();
			System.out.println(wetlands.wetlandBySpecie(name));

		}
	}

}