package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Time{

	
	
	 	private StringProperty nomJour = new SimpleStringProperty();
	    private StringProperty heure = new SimpleStringProperty();
	  
	



	    public StringProperty nomJourProperty() {return nomJour;};
	    public StringProperty heureProperty() {return heure;};
	  


	    public Time(String nomJour, String heure) {
	        this.nomJour.set(nomJour);
	        this.heure.set(heure);
	      
	       
	    }

	    public String getnomJour() {return nomJour.get();}
	    public String getheure() {return heure.get();}
	   
	 
}
