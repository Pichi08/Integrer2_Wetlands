package model;

import java.time.LocalDate;

public class WetlandsManager {
    
    public static final int MAX_WETLANDS = 80; 

    private String title;

    private Wetland [] wetlands;

    //Constructor
    /**
     * 
     * @param title
     */
    public WetlandsManager (String title){
        this.title = title;

        wetlands = new Wetland[MAX_WETLANDS];
    }

    /**
     * 
     * @return firstEmptyPosition int, this metod returns a number that represents the first empty position in the array wetlands
     */
    //Aqui estoy viendo la primera posicion en la que hay un espacio
    public int firstEmptyPosition(){
		int position = -1;
		boolean emptyPosition=false;
		for(int i = 0; i<MAX_WETLANDS && !emptyPosition; i++){
			if(wetlands[i]==null){
				position = i;
                emptyPosition=true;
			}
		}
		return position;
	}
    /**
     * 
     * @return hasSpace boolean, this metod returns true if the array is not full and false if the array is full
     */
    //Aqui estoy viendo si hay espacio para poder preguntarle al usuario los datos 
    public boolean hasSpace(){
		boolean emptyPosition=false;
		for(int i = 0; i<MAX_WETLANDS && !emptyPosition; i++){
			if(wetlands[i]==null){
				emptyPosition=true;
			}
		}
		return emptyPosition;
	}
    /**
     * 
     * @param name String, this variable contains the name of the wetland given by the user
     * @param squareKilometers String, this variable contains the area of the wetland
     * @param photoUrl String, this variable contains the URL of the photo of the wetland
     * @param nameZone String, this variable contains the name of the zone in which the wetland is
     * @param zone Boolean, if false represents that the wetland is urban else the wetland is rural
     * @param type boolean, if false represents that the wetland is public else the wetland is private
     * @param protectedArea Boolean, if true the wetland is in a protected area else is not
     * @return createWetland String, it returns a message.
     */
    //Aqui estoy creando el objeto Wetland y guardandolo en el primer espacio libre
    public String createWetland(String name, String squareKilometers, String photoUrl, String nameZone, Boolean zone, Boolean type, Boolean protectedArea){
        
        String out = "";
        int emptyPos = firstEmptyPosition();

        if(emptyPos == -1){
			out = "The array is full";
		}else{ 
            wetlands[emptyPos] = new Wetland(name, zone, type, squareKilometers, photoUrl, protectedArea, nameZone);
            out = "The wetland " + name + " has been added succecsfully\n"; //arreglar escritura
        }
        return out;
    }
    /**
     * 
     * @param type int, this variable contains a number that represents the type of specie the animal or plant is
     * @param commonName String, this variable contains the name of the species given by the user
     * @param scientificName String, this variable contains the scientific name of the species given by the user
     * @param migratory boolean, if true the species is migratory else if not migratory
     * @param position int, this variable contains a number that represents in what wetland the specie belongs
     */
    public void receiveSpecie(int type, String commonName, String scientificName, boolean migratory, int position){
        wetlands[position].addSpecie(type, commonName, scientificName, migratory,position);
    }

    /**
     * 
     * @param description String, this variable contains the description of the event given by the user
     * @param organizer String, this variable contains the name of the organizer of the event given by the user
     * @param value double, this variable contains the value of the event given by the user
     * @param type int, this varible contains a number the represents the type of event given by the user
     * @param fecha LocalDate, this variable contains the date of the event given by the user
     * @param position int, this variable contains a number that represents in what wetland the event belongs
     */
    public void receiveEvent(String description, String organizer, double value, int type, LocalDate fecha, int position){
       wetlands[position].addEvent(description, organizer, value, type, fecha);
    }

    /**
     * 
     * @param name String, this variable contains the name of the wetland
     * @return searchWetland int, this metod returns -1 if the name does't match with a name of a wetland.
     */
    public int searchWetland(String name){
        int out=-1;
        for (int i=0; i<MAX_WETLANDS; i++){
            if(wetlands[i]!=null && wetlands[i].getName().equalsIgnoreCase(name)){
                out=i;
            }
        }

        return out;
    }
    /**
     * 
     * @return showWetlands String, this metod returns a message with the information of the wetlands that have been register and the amount of species of flora and fauna in them
     */
    public String showWetlands(){
        String out = "";
        boolean flag = false;
        for(int i=0; i<MAX_WETLANDS && !flag; i++){
            if(wetlands[i]!=null){
                out+= wetlands[i].toString()+"\n"+"Amount species of flora: "+wetlands[i].amountSepeciesFlora()+"\n"+"Amount species of fauna: "+wetlands[i].amountSpeciesFauna()+"\n\n";
            }else{
                flag =true;
            }
        }
        
        return out;
    }
    /**
     * 
     * @return showManteinance String, this metod returns a message with the amount of mainteinance on a year for each wetland
     */
    public String showManteinance(){
        String out ="";
        boolean flag = false;
        for(int i=0; i<MAX_WETLANDS && !flag; i++){
            if(wetlands[i]!=null){
                out+= "The wetland "+wetlands[i].getName()+" has a total of "+wetlands[i].amountEventsWetlands()+" maintenance.\n";
            }else{
                flag = true;
            }
        }
        return out;
    }
    /**
     * 
     * @return lessSpecieFlora String, this metod returns a message with the name of the wetland with less species of flora
     */
    public String lessEspecieFlora(){
        int [] lessFlora = new int[MAX_WETLANDS];
        
        for(int i = 0; i<MAX_WETLANDS;  i++){
            if(wetlands[i]!=null){
                lessFlora[i]=wetlands[i].amountSepeciesFlora();
            }
        }

        int menor = lessFlora[0];
        int actualNum = 0;
        String message = "";
        
        //HAY ALGO MAL CON EL CONTADOR
        
        for (int i = 0; i < MAX_WETLANDS; i++) {
            actualNum = lessFlora[i];
            if(actualNum < menor) {
                menor = actualNum;
                //count++;
                //message="The wetlend with less flora is "+wetlands[count].getName();
            }
            if(wetlands[i]!=null && menor==wetlands[i].amountSepeciesFlora()){
                message="The wetlend with less flora is "+wetlands[i].getName();
            }
        }
        
        return message;
    }
    /**
     * 
     * @return lessSpecieFauna String, this metod returns a message with the name of the wetland with more species of fauna
     */

    public String moreEspecieFauna(){
        int [] lessFauna = new int[MAX_WETLANDS];
        
        for(int i = 0; i<MAX_WETLANDS;  i++){
            if(wetlands[i]!=null){
                lessFauna[i]=wetlands[i].amountSpeciesFauna();
            }
        }

        int menor = lessFauna[0];
        int actualNum = 0;
        String message = "";
        
        for (int i = 0; i < MAX_WETLANDS; i++) {
            actualNum = lessFauna[i];
            if(actualNum > menor) {
                menor = actualNum;
            }
            if(wetlands[i]!=null && menor==wetlands[i].amountSpeciesFauna()){
                message="The wetlend with more species of fauna is "+wetlands[i].getName();
            }
        }
        
        return message;
    }

    /**
     * 
     * @return conmprovationWetlands int, this metod is use to know if a wetland has been created or not
     */
    public int comprovationWetland(){
        int out = 0;

        if(wetlands[0]!=null){
            out=1;
        }

        return out;
    }

    /**
     * 
     * @param name String, this variable contains the name of a specie
     * @return wetlandBySpecie String, this metod returns a message with that sais which or cuales wetlands have that specie in it.
     */
    public String wetlandBySpecie(String name){
       
        String out = "";
        boolean flag = false;
        String nameSpecies [];
        
        for (int i =0; i<MAX_WETLANDS; i++){
            if(wetlands[i]!=null){
                //ESTA JODA ESTA MAL ASADOOO
                nameSpecies = wetlands[i].nameSpecies();
                //ASADOOOO
                for(int j = 0; j<500 && !flag; j++){
                    if(nameSpecies[j]!=null){
                        if(nameSpecies[j].equalsIgnoreCase(name)){
                            out+="The specie with the name "+name+" exist in the wetland "+wetlands[i].getName();
                        }
                    } else {
                        flag = true;
                    }
                }
            }
        }
         
        return out;
    }
   
    //Getters
    public Wetland [] getWetlands() {
        return wetlands;
    }

    public void setWetlands(Wetland [] wetlands) {
        this.wetlands = wetlands;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
