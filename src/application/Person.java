package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty id = new SimpleStringProperty();
    private StringProperty nom = new SimpleStringProperty();
    private StringProperty prenom = new SimpleStringProperty();
    private StringProperty maladie = new SimpleStringProperty();
    private StringProperty groupeSang = new SimpleStringProperty();
    private StringProperty sommeManquante = new SimpleStringProperty();
    private StringProperty numTel = new SimpleStringProperty();



    public StringProperty idProperty() {return id;};
    public StringProperty nomProperty() {return nom;};
    public StringProperty prenomProperty() {return prenom;};
    public StringProperty maladieProperty() {return maladie;};
    public StringProperty groupeSangProperty() {return groupeSang;};
    public StringProperty sommeManquanteProperty() {return sommeManquante;};
    public StringProperty numTelProperty() {return numTel;};

    public Person(String id, String nom,String prenom,String maladie,String groupeSang,String sommeManquante,String numTel) {
        this.id.set(id);
        this.nom.set(nom);
        this.prenom.set(prenom);
        this.maladie.set(maladie);
        this.groupeSang.set(groupeSang);
        this.sommeManquante.set(sommeManquante);
        this.numTel.set(numTel);
    }

    public String getNombre() {return id.get();}
    public String getApellido() {return nom.get();}
    public String getprenom() {return prenom.get();}
    public String getmaladie() {return maladie.get();}
    public String getgroupe() {return groupeSang.get();}
    public String getsommeManquante() {return sommeManquante.get();}
    public String getnumTel() {return numTel.get();}


}
