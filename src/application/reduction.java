package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class reduction {

	
	
	 	private StringProperty nom1 = new SimpleStringProperty();
	    private StringProperty nom2 = new SimpleStringProperty();
	    private StringProperty nbr1 = new SimpleStringProperty();
	    private StringProperty nbr2 = new SimpleStringProperty();
	    private StringProperty prix = new SimpleStringProperty();
	



	    public StringProperty nom1Property() {return nom1;};
	    public StringProperty nom2Property() {return nom2;};
	    public StringProperty nbr1Property() {return nbr1;};
	    public StringProperty nbr2Property() {return nbr2;};
	    public StringProperty prixProperty() {return prix;};


	    public reduction(String nom1, String nom2,String nbr1,String nbr2,String prix) {
	        this.nom1.set(nom1);
	        this.nom2.set(nom2);
	        this.nbr1.set(nbr1);
	        this.nbr2.set(nbr2);
	        this.prix.set(prix);
	       
	    }

	    public String getnom1() {return nom1.get();}
	    public String getnom2() {return nom2.get();}
	    public String getnbr1() {return nbr1.get();}
	    public String getnbr2() {return nbr2.get();}
	    public String getprix() {return prix.get();}
	 
}
