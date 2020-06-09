package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Price{

	
	
	 	private StringProperty nbrseance = new SimpleStringProperty();
	    private StringProperty prix = new SimpleStringProperty();
	  
	



	    public StringProperty nbrseanceProperty() {return nbrseance;};
	    public StringProperty prixProperty() {return prix;};
	  


	    public Price(String nbrseance, String prix) {
	        this.nbrseance.set(nbrseance);
	        this.prix.set(prix);
	      
	       
	    }

	    public String getnbrseance() {return nbrseance.get();}
	    public String getprix() {return prix.get();}
	   
	 
}
