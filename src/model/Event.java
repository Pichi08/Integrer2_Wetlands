package model;

import java.time.LocalDate;

public class Event {
    
    //Declarations
    private int type;
    private Double value;
    private String description;
    private String organizer;
    private LocalDate fecha;

    //Constructor
    public Event(int type, double value, String description, String organizer, LocalDate fecha){
        this.type = type;
        this.value = value;
        this.description = description;
        this.organizer = organizer;
        this.setDate(fecha);
    }

    //Get y Set
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String oraganizer) {
        this.organizer = oraganizer;
    }

    
    public LocalDate getFecha() {
        return fecha;
    }


    public void setDate(LocalDate fecha){
        this.fecha = fecha;
    }

}
