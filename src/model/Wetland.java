package model;

import java.time.LocalDate;

public class Wetland {
    
    //Constants
    private static final int MAX_EVSPE = 500;

    //Declarations
    private String name;
    private Boolean zone;
    private Boolean type;
    private String squareKilometers;
    private String photoUrl;
    private Boolean protectedArea;
    private String nameZone;

    //Asosiations
    private Specie [] speciesWetland;
    private Event [] eventsWetland;

    //Constructor
    public Wetland(String name, Boolean zone, Boolean type, String squareKilometers, String photoUrl, Boolean protectedArea, String nameZone){
        
        this.name = name;
        this.zone = zone;
        this.type = type;
        this.squareKilometers = squareKilometers;
        this.photoUrl = photoUrl;
        this.protectedArea = protectedArea;
        this.nameZone = nameZone;

        speciesWetland = new Specie[MAX_EVSPE];
        eventsWetland = new Event[MAX_EVSPE];   

    }
    /**
     * 
     * @return firstEmptyPositionSpecie int, this metod returns the first empty position in the array of species
     */
    public int firstEmptyPositionSpecie(){
		int position = -1;
		boolean emptyPosition=false;
		for(int i = 0; i<MAX_EVSPE && !emptyPosition; i++){
			if(speciesWetland[i]==null){
				position = i;
                emptyPosition=true;
			}
		}
		return position;
    }
    /**
     * 
     * @return firstEmptyPositionEvents int, this metod returns the first empty position in the array of events
     */

    public int firstEmptyPositionEvent(){
		int position = -1;
		boolean emptyPosition=false;
		for(int i = 0; i<MAX_EVSPE && !emptyPosition; i++){
			if(eventsWetland[i]==null){
				position = i;
                emptyPosition=true;
			}
		}
		return position;
	}

    /**
     * 
     * @param type int, this variable contains a number that represents the type of specie the animal or plant is
     * @param commonName String, this variable contains the name of the species given by the user
     * @param scientificName String, this variable contains the scientific name of the species given by the user
     * @param migratory boolean, if true the species is migratory else if not migratory
     * @param position int, this variable contains a number that represents in what wetland the specie belongs
     * @return addSpecie String, this metod returns a message.
     */
    public String addSpecie(int type, String commonName, String scientificName, boolean migratory, int position){
        String out = "";

        int emptyPos = firstEmptyPositionSpecie();

        if(emptyPos == -1){ 
			out = "The array is full";
		}else{ 
        speciesWetland[emptyPos] = new Specie(type, commonName, scientificName, migratory, position);
            out = "The specie " + name + " has been added succecsfully\n";
        }
        return out;
    }
    /**
     * 
     * @param description String, this variable contains the description of the event given by the user
     * @param organizer String, this variable contains the name of the organizer of the event given by the user
     * @param value double, this variable contains the value of the event given by the user
     * @param type int, this varible contains a number the represents the type of event given by the user
     * @param fecha LocalDate, this variable contains the date of the event given by the user
     * @return addEvent String, this metod returns a message
     */

    public String addEvent(String description, String organizer, double value, int type, LocalDate fecha){
        String out ="";

        int emptyPos = firstEmptyPositionEvent();

        if(emptyPos == -1){ 
			out = "The array is full";
		}else{ 
        eventsWetland[emptyPos] = new Event(type, value, description, organizer, fecha);
            out = "The specie " + name + " has been added succecsfully\n";
        }
        return out;
    }
    /**
     * 
     * @return amountSpeciesFlora int, this metod returns the amount of species of flora in wetland[i]
     */

    public int amountSepeciesFlora(){

        int cont = 0;

        /*
        for(int j = 0; j<80; j++){
            for(int i=0; i<MAX_EVSPE; i++){
                if(speciesWetland[i]!=null && speciesWetland[i].getType()<3){
                    if(speciesWetland[i].getPosition()==j){
                        cont++;
                        speciesArray[j]=cont;
                    }
                }
            }
        }
        */

        for ( int i =0; i<MAX_EVSPE; i++){
            if(speciesWetland[i]!=null){
                if(speciesWetland[i].getType()==1 || speciesWetland[i].getType()==2){
                    cont++;
                }
            }
        }


        return cont;
    }
    /**
     * 
     * @return amountSpeciesFauna int, this metod returns the amount of species of fauna in wetland[i]
     */
    public int amountSpeciesFauna(){
        int cont = 0;

        for ( int i =0; i<MAX_EVSPE; i++){
            if(speciesWetland[i]!=null){
                if(speciesWetland[i].getType()==3 || speciesWetland[i].getType()==4 || speciesWetland[i].getType()==5){
                    cont++;
                }
            }
        }


        return cont;
    }

    /**
     * 
     * @return amountEventsWetlands int, this metod returns the amount of events in wetland[i]
     */

    public int amountEventsWetlands(){
        int cont = 0;

        for ( int i =0; i<MAX_EVSPE; i++){
            if(eventsWetland[i]!=null){
                if(eventsWetland[i].getType()==1){
                    cont++;
                }
            }
        } 

        return cont;
    }

    /**
     * 
     * @return nameSpecies String [], this metod returns the name of the species in wetland[i]
     */

    public String [] nameSpecies(){

        String [] nameSpecies = new String[MAX_EVSPE];
        boolean flag = false;
        /*
        int [] asado = new int[5];
        int count = 0;

        for(int i=0; i<5; i++){
            count ++;
            asado [i] = count;
        }

        return asado;
        */

        for (int i=0; i<MAX_EVSPE && !flag; i++){
            if(speciesWetland[i]!=null){
                nameSpecies[i]=speciesWetland[i].getCommonName();
            } else {
                flag = true;
            }
        }

        return nameSpecies;
    }

    //Get y Sets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isZone() {
        return zone;
    }

    public void setZone(Boolean zone) {
        this.zone = zone;
    }

    public Boolean isType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getSquareKilometers() {
        return squareKilometers;
    }

    public void setSquareKilometers(String squareKilometers) {
        this.squareKilometers = squareKilometers;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean isProtectedArea() {
        return protectedArea;
    }

    public void setProtectedArea(Boolean protectedArea) {
        this.protectedArea = protectedArea;
    }

    public Specie [] getSpeciesWetland() {
        return speciesWetland;
    }

    public void setSpeciesWetland(Specie [] speciesWetland) {
        this.speciesWetland = speciesWetland;
    }

    public Event [] getEventsWetland() {
        return eventsWetland;
    }

    public void setEventsWetland(Event [] eventsWetland) {
        this.eventsWetland = eventsWetland;
    }

    public String getNameZone() {
        return nameZone;
    }

    public void setNameZone(String nameZone) {
        this.nameZone = nameZone;
    }

    public String toString(){
        String varZone, varType, varArea;
        if (zone==false){
            varZone = "Urban";
        } else {
            varZone = "Rural";
        }
        if (type == false){
            varType = "Pulic";
        } else {
            varType = "Private";
        }
        if (protectedArea == false){
            varArea = "No";
        } else {
            varArea = "Yes";
        }

        return "***WETLAND***\n"+
        "Name: "+ name +
        "\nZone: "+ varZone+
        "\nType: "+ varType+
        "\nArea: "+ squareKilometers +"Km2"+
        "\nPhoto Url: "+photoUrl+
        "\nProtected area: "+varArea+
        "\nName of the zone: "+nameZone;
    }

}
