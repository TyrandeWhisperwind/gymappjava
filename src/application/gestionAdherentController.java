package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class gestionAdherentController implements Initializable {
	//variables
	@FXML
	 public TableView<Person> table;
	@FXML
	 public TextField txt0;
	@FXML
	 public TextField txt1;
	@FXML
	 public TextField txt2;
	@FXML
	 public TextField txt3;
	@FXML
	 public TextField txt4;
	@FXML
	 public TextField txt5;
	@FXML
	 public TextField txt6;
	@FXML
	 public TextField txt7;
	@FXML
	 public TextField txt8;
	@FXML
	 public TextField txt9;
	@FXML
	 public Label dateInsc;
	@FXML
	 public ImageView picture;
	@FXML
	 public  TextField search;
     
	File fil = new File("Female.jpg");
    Image ima = new Image(fil.toURI().toString());
	Statement stmt3;
    ResultSet rs3;
    ObservableList<Person> data= FXCollections.observableArrayList();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL location, ResourceBundle resources) {
	
		picture.setImage(ima);

	 	TableColumn<Person,String> c1 = new TableColumn<Person, String>("Id");
	 	c1.setCellValueFactory(new PropertyValueFactory<Person,String>("id"));

	    TableColumn<Person,String> c2 = new TableColumn<Person, String>("Nom");
	    c2.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    
	    TableColumn<Person,String> c3 = new TableColumn<Person, String>("Prenom");
	    c3.setCellValueFactory(new PropertyValueFactory<>("prenom"));
  
	     TableColumn<Person,String> c5 = new TableColumn<Person, String>("Maladie");
	     c5.setCellValueFactory(new PropertyValueFactory<>("maladie"));
 
	    TableColumn<Person,String> c6 = new TableColumn<Person, String>("Groupe sanguin");
	    c6.setCellValueFactory(new PropertyValueFactory<>("groupeSang"));
 
	    TableColumn<Person,String> c7 = new TableColumn<Person, String>("Somme Manquante");
	    c7.setCellValueFactory(new PropertyValueFactory<>("sommeManquante"));

	    TableColumn<Person,String> c8 = new TableColumn<Person, String>("Num Tel");
	    c8.setCellValueFactory(new PropertyValueFactory<>("numTel"));
	    
        c1.setMinWidth(100d);
        c2.setMinWidth(100d);
        c3.setMinWidth(100d);
        c6.setMinWidth(100d);
        c5.setMinWidth(100d);
        c7.setMinWidth(200d);
        c8.setMinWidth(300d);

        // did remove that warning
	    		 table.getColumns().addAll(c1,c2,c3,c5,c6,c7,c8);
   
		try {
			stmt3 = ficheController.connectio.createStatement();
			rs3 = stmt3.executeQuery("SELECT * FROM adherent ");
	 		
					while(rs3.next())
					{			 
						data.add(new Person(rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(6),rs3.getString(7),"0",rs3.getString(5))); 
    
					}
	 
			        table.setItems(data);

	 		stmt3.close();
    		rs3.close();
    		 		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//////////////////search 		
		FilteredList<Person> filteredData = new FilteredList<>(data, p -> true);
		 search.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(myObject -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Compare first name and last name field in your object with filter.
	                String lowerCaseFilter = newValue.toLowerCase();

	                if (String.valueOf(myObject.getNombre()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true;
	                    // Filter matches first name.

	                } else if (String.valueOf(myObject.getNombre()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches last name.
	                } 

	                return false; // Does not match.
	            });
	        });

		 	SortedList<Person> sortedData = new SortedList<>(filteredData);
	        // 4. Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(table.comparatorProperty());
	        // 5. Add sorted (and filtered) data to the table.
	        table.setItems(sortedData);	      
}
 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    //fuctions buttons
 //Ajouter
    @FXML
    public void loadAjout(ActionEvent event) throws IOException 
    {    //load and keep the old stage
    	Parent home= FXMLLoader.load(getClass().getResource("fiche inscription.fxml"));
    	Scene homeScene = new Scene (home, 800	, 600);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
         Stage stage = new Stage();
         
         stage.setTitle("New Window");
         stage.setScene(homeScene);
         stage.show();
    	
    }
    
 //Retour
    @FXML
    public void loadRetour(ActionEvent event) throws IOException 
    {	//retour button
    	Parent home= FXMLLoader.load(getClass().getResource("after connexion.fxml"));
    	Scene homeScene = new Scene (home, 800	, 500);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
    }
    
    
    //modife
    @FXML
    public void modif(ActionEvent event) throws IOException 
    {	//retour button
    	txt1.setEditable(true);
    	txt2.setEditable(true);
    	txt3.setEditable(true);
    	txt4.setEditable(true);
    	txt5.setEditable(true);
    	txt6.setEditable(true);
    	txt7.setEditable(true);
    	txt8.setEditable(true);
    	
    }
    //valider modif
    @FXML
    public void validModif(ActionEvent event) throws IOException, SQLException 
  
    {		

    		stmt3 = ficheController.connectio.createStatement();
			rs3 = stmt3.executeQuery("SELECT * FROM adherent where id='"+txt0.getText()+"'");
			rs3.next();
 		
			
			if ( txt1.getText().equals(rs3.getString(2))==false  )
				{ 	
						PreparedStatement updateEXP = ficheController.connectio.prepareStatement("update adherent set nom = '"+txt1.getText()+"' where id ='"+txt0.getText()+"' ;");
						updateEXP.executeUpdate();
						updateEXP.close();
				
				}
				// add refresh button
			stmt3.close();
			rs3.close();
    	
    }
    //supprimer 
    @FXML
    public void supprimer(ActionEvent event) throws IOException, SQLException 
    {	//add refresh
    	
                PreparedStatement updateEXP = ficheController.connectio.prepareStatement("delete from adherent  where id ='"+table.getSelectionModel().getSelectedItem().getNombre()+"' ;");
				updateEXP.executeUpdate();
				updateEXP.close();
    	//and also from historique et valpay
    }
    
    
    
    //selected item table
    @FXML
    public void clickItem(MouseEvent event) throws SQLException
    {
        if (event.getClickCount() == 1) //Checking one click
        {
                String id =table.getSelectionModel().getSelectedItem().getNombre();
                stmt3 = ficheController.connectio.createStatement();
    			rs3 = stmt3.executeQuery("SELECT * FROM adherent where id='"+id+"'");
                rs3.next();
    			txt0.setText(rs3.getString(1));// id 
    			txt1.setText(rs3.getString(2));// nom
    			txt2.setText(rs3.getString(3));//prenom
    			txt5.setText(rs3.getString(4));//adresse
    			txt6.setText(rs3.getString(5));//num
    			txt7.setText(rs3.getString(6));//maladie
    			txt8.setText(rs3.getString(7));//groupe sanguin
    			txt4.setText(rs3.getString(8));//lieu
    			txt3.setText(rs3.getString(9));//date naissance
    			dateInsc.setText(rs3.getString(10));
    			File file = new File(rs3.getString(11));
		        Image image = new Image(file.toURI().toString());
				picture.setImage(image);

    			stmt3.close();
        		rs3.close();
       
           
        }
    }
  
}