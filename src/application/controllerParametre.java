package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controllerParametre implements Initializable {
	
    @FXML
	ChoiceBox<String> menu;
    @FXML
	ChoiceBox<String> menutime1;
    @FXML
	ChoiceBox<String> menutime2;
    @FXML
	TableView<Price> table3;
	@FXML
	TableView<Time> table4;
	@FXML
	TableView<reduction> table;
	@FXML
	Button b1;
	@FXML
	Button b2;
	@FXML
	Button b3;
	@FXML
	Button ajout;
	@FXML
	Button option;
	@FXML
	TextField t1;
	@FXML
	TextField t2;
	@FXML
	TextField newdiscip;
	@FXML
	TextField remisediscip;
	@FXML
	TextField nbrseance1;
	@FXML
	TextField nbrseance2;
	@FXML
	TextField prixtotal;
    @FXML
	ChoiceBox<String> menudiscip;
	
	
	
	ObservableList<Price> datatable= FXCollections.observableArrayList();
	ObservableList<Time> datatab= FXCollections.observableArrayList();
	ObservableList<reduction> dataReduc= FXCollections.observableArrayList();

	Statement stmt3;
    ResultSet rs3;
	Statement stmt4;
    ResultSet rs4;
    Statement stmt5;
    ResultSet rs5;
    Statement stmt6;
    ResultSet rs6;
    


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//affichage des nom discipline dans partie supression
		ObservableList<String>  data= FXCollections.observableArrayList();	
		try {
			stmt3 = ficheController.connectio.createStatement();
			rs3 = stmt3.executeQuery("SELECT distinct nom FROM  discipline");
			while (rs3.next())
			{
				
				data.add(rs3.getString(1));
			}
			menudiscip.setItems(data);
			menu.setItems(data);
			stmt3.close();
			rs3.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//table price
		TableColumn<Price,String> c1 = new TableColumn<Price, String>("prix");
		c1.setCellValueFactory(new PropertyValueFactory<Price,String>("prix"));
		TableColumn<Price,String> c2 = new TableColumn<Price, String>("nbrseance");
		c2.setCellValueFactory(new PropertyValueFactory<>("nbrseance"));
		c1.setMinWidth(300d);
		c2.setMinWidth(320d);

		 	table3.getColumns().addAll(c1,c2);
		 	table3.setItems(datatable);
		//table Time
			TableColumn<Time,String> c3 = new TableColumn<Time, String>("nomJour");
			c3.setCellValueFactory(new PropertyValueFactory<Time,String>("nomJour"));
			TableColumn<Time,String> c4 = new TableColumn<Time, String>("heure");
			c4.setCellValueFactory(new PropertyValueFactory<>("heure"));
			c3.setMinWidth(300d);
			c4.setMinWidth(320d);
			table4.getColumns().addAll(c3,c4);
		 	table4.setItems(datatab);
		 	
			ObservableList<String>  datatime1= FXCollections.observableArrayList();	
			ObservableList<String>  datatime2= FXCollections.observableArrayList();	

				datatime1.add("Samedi");
				datatime1.add("Dimanche");
				datatime1.add("Lundi");
				datatime1.add("Mardi");
				datatime1.add("Mercredi");
				datatime1.add("Jeudi");
				datatime1.add("Vendredi");
				
				datatime2.add("07:00");
				datatime2.add("07:15");
				datatime2.add("07:30");
				datatime2.add("08:00");
				datatime2.add("08:15");
				datatime2.add("08:30");
				datatime2.add("09:00");
				datatime2.add("09:15");
				datatime2.add("09:30");
				datatime2.add("10:00");
				datatime2.add("10:15");
				datatime2.add("10:30");
				datatime2.add("11:00");
				datatime2.add("11:15");
				datatime2.add("11:30");
				datatime2.add("12:00");
				datatime2.add("12:15");
				datatime2.add("12:30");
				datatime2.add("13:00");
				datatime2.add("13:15");
				datatime2.add("13:30");
				datatime2.add("14:00");
				datatime2.add("14:15");
				datatime2.add("14:30");
				datatime2.add("15:00");
				datatime2.add("15:15");
				datatime2.add("15:30");
				datatime2.add("16:00");
				datatime2.add("16:15");
				datatime2.add("16:30");
				datatime2.add("17:00");
				datatime2.add("17:15");
				datatime2.add("17:30");
				datatime2.add("18:00");
				datatime2.add("18:15");
				datatime2.add("18:30");
				datatime2.add("19:00");
				datatime2.add("19:15");
				datatime2.add("19:30");
				
				menutime1.setItems(datatime1);
				menutime2.setItems(datatime2);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				newdiscip.textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {	
						
						remisediscip.setText(newdiscip.getText());


					}		  
				});	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				

				TableColumn<reduction,String> cc1 = new TableColumn<reduction, String>("Nom1");
			 	cc1.setCellValueFactory(new PropertyValueFactory<reduction,String>("nom1"));

			    TableColumn<reduction,String> cc2 = new TableColumn<reduction, String>("Nom2");
			    cc2.setCellValueFactory(new PropertyValueFactory<>("nom2"));
			    
			    TableColumn<reduction,String> cc3 = new TableColumn<reduction, String>("nbr1");
			    cc3.setCellValueFactory(new PropertyValueFactory<>("nbr1"));
		  
			     TableColumn<reduction,String> cc5 = new TableColumn<reduction, String>("nbr2");
			     cc5.setCellValueFactory(new PropertyValueFactory<>("nbr2"));
		 
			    TableColumn<reduction,String> cc6 = new TableColumn<reduction, String>("prix");
			    cc6.setCellValueFactory(new PropertyValueFactory<>("prix"));
		 
		        cc1.setMinWidth(300d);
		        cc2.setMinWidth(200d);
		        cc3.setMinWidth(200d);
		        cc6.setMinWidth(200d);
		        cc5.setMinWidth(240d);
		       

		        // did remove that warning
			    		 table.getColumns().addAll(cc1,cc2,cc3,cc5,cc6);	
			 		 	table.setItems(dataReduc);

		
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
 //Retour
    @FXML
    public void loadRetour(ActionEvent event) throws IOException 
    {	//retour button
    	Parent home= FXMLLoader.load(getClass().getResource("after connexion.fxml"));
    	Scene homeScene = new Scene (home,  800	, 500);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
   
    	
    }
    
    

    
  //suppression save
    @FXML
    public void supprimer(ActionEvent event) throws IOException, SQLException 
    {					ObservableList<String>  data= FXCollections.observableArrayList();	
    	    //table discipline
    	 	PreparedStatement updateEXP = ficheController.connectio.prepareStatement("delete from discipline  where nom ='"+menu.getValue()+"' ;");
			updateEXP.executeUpdate();
			updateEXP.close();
			//table horraire 
			updateEXP = ficheController.connectio.prepareStatement("delete from horaire_semaine  where discipline ='"+menu.getValue()+"' ;");
			updateEXP.executeUpdate();
			updateEXP.close();
			//table réduction
			updateEXP = ficheController.connectio.prepareStatement("delete from reduction  where (nom_discip1 ='"+menu.getValue()+"') or (nom_discip2 ='"+menu.getValue()+"') ;");
			updateEXP.executeUpdate();
			updateEXP.close();
				//reaffichage des descipline aprés suppression
				stmt3 = ficheController.connectio.createStatement();
				rs3 = stmt3.executeQuery("SELECT distinct nom FROM  discipline");
				while (rs3.next())
				{
					
					data.add(rs3.getString(1));
				}
				menu.setItems(data);
				menudiscip.setItems(data);

				stmt3.close();
				rs3.close();				
    } 
    
    @FXML
    public void b1(ActionEvent event) throws IOException, SQLException 
    {		
    	
        datatable.add(new Price(t2.getText(),t1.getText()));
        t1.clear();
        t2.clear();

    } 
    
    @FXML
    public void b2(ActionEvent event) throws IOException, SQLException 
    {		
    	
        datatab.add(new Time(menutime1.getValue(),menutime2.getValue()));

    } 
    
    @FXML
    public void b3(ActionEvent event) throws IOException, SQLException 
    {		
    	
        dataReduc.add(new reduction(remisediscip.getText(),menudiscip.getValue(),nbrseance1.getText(),nbrseance2.getText(),prixtotal.getText()));

    } 
    
    //suppression save
    @FXML
    public void ajouter(ActionEvent event) throws IOException, SQLException 
    {
    PreparedStatement pst = ficheController.connectio.prepareStatement("INSERT INTO discipline (nom, nbr_seance_par_semaine,prix) VALUES (?,?,?)");
    for(int i=0; i<datatable.size(); i++){
         pst.setString(1, newdiscip.getText());
         pst.setString(2, datatable.get(i).getnbrseance());
         pst.setString(3, datatable.get(i).getprix());
         pst.execute();
         

    }pst.close();	
	//convert nom jour en number
    	String nom = null;
     pst = ficheController.connectio.prepareStatement("INSERT INTO horaire_semaine (nom_jour, heure,discipline) VALUES (?,?,?)");
    for(int i=0; i<datatab.size(); i++){
    	if (datatab.get(i).getnomJour().equals("Dimanche"))
    	{nom ="1";}
    	if (datatab.get(i).getnomJour().equals("Lundi"))
    	{nom ="2";}
    	if (datatab.get(i).getnomJour().equals("Mardi"))
    	{nom ="3";}
    	if (datatab.get(i).getnomJour().equals("Mercredi"))
    	{nom ="4";}
    	if (datatab.get(i).getnomJour().equals("Jeudi"))
    	{nom ="5";}
    	if (datatab.get(i).getnomJour().equals("Vendredi"))
    	{nom ="6";}
    	if (datatab.get(i).getnomJour().equals("Samedi"))
    	{nom ="7";}
    	
         pst.setString(1, nom);
         pst.setString(2, datatab.get(i).getheure());
         pst.setString(3, newdiscip.getText());
         pst.execute();
         

    }pst.close();
    
    pst = ficheController.connectio.prepareStatement("INSERT INTO reduction (nom_discip1,nom_discip2,nbr_seance1,nbr_seance2,prix_reduction) VALUES (?,?,?,?,?)");
    for(int i=0; i<dataReduc.size(); i++){
         pst.setString(1, dataReduc.get(i).getnom1());
         pst.setString(2, dataReduc.get(i).getnom2());
         pst.setString(3, dataReduc.get(i).getnbr1());
         pst.setString(4, dataReduc.get(i).getnbr2());
         pst.setString(5, dataReduc.get(i).getprix());


         
         pst.execute();
         

    }pst.close();
    				ObservableList<String>  data= FXCollections.observableArrayList();	

    
    stmt3 = ficheController.connectio.createStatement();
	rs3 = stmt3.executeQuery("SELECT distinct nom FROM  discipline");
	while (rs3.next())
	{
		
		data.add(rs3.getString(1));
	}
	menu.setItems(data);
	menudiscip.setItems(data);

	stmt3.close();
	rs3.close();	
	
    newdiscip.clear();
	remisediscip.clear();
	nbrseance1.clear();
	nbrseance2.clear();
	prixtotal.clear();
	
	dataReduc.clear();
	datatab.clear();
	datatable.clear();
	
	
    } 
    
    //load and hide old stage 
    @FXML
    public void load_option(ActionEvent event) throws IOException 
    {
    	Parent home= FXMLLoader.load(getClass().getResource("option.fxml"));
    	Scene homeScene = new Scene (home, 800	, 500);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
    }
    
  
}
