package model;

public class Specie {
    
    //Declarations
    private int type;
    private String commonName;
    private String scientificName;
    private Boolean migratory;
    private int position;

    //Constructor
    public Specie (int type, String commonName, String scientificName, boolean migratory, int position){
        this.type = type;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.migratory = migratory;
        this.position = position;
    }

    //Get y Set
    

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Boolean isMigratory() {
        return migratory;
    }

    public void setMigratory(Boolean migratory) {
        this.migratory = migratory;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
