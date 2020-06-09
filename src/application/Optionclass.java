package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
public class Optionclass implements Initializable {

	@FXML
	Button retour;
	@FXML
	Button save1;
	@FXML
	ChoiceBox<String> menu1;
	@FXML
	ChoiceBox<String> menu2;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String>  data= FXCollections.observableArrayList();	
    	Calendar c1=Calendar.getInstance();
    	int da =Integer.parseInt(new SimpleDateFormat("dd").format(c1.getTime()));
    	int days = c1.getActualMaximum(Calendar.DAY_OF_MONTH); // nombre max de jour du  mois courant 

    	for(int i=da;i<=days;i++)
    	{
    	data.add(Integer.toString(i));
    	}
    	
    	menu1.setItems(data);
    	menu2.setItems(data);

    	

		
	}
	
	 //Retour
    @FXML
    public void loadRetour(ActionEvent event) throws IOException 
    {	//retour button
    	Parent home= FXMLLoader.load(getClass().getResource("gestionParametre.fxml"));
    	Scene homeScene = new Scene (home,   1500	, 700);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
   
    	
    }
    
    @FXML
    public void save1(ActionEvent event) throws IOException, SQLException 
    {	
    	PreparedStatement updateEXP = ficheController.connectio.prepareStatement("delete from days_off ;");
		updateEXP.executeUpdate();
		updateEXP.close();
    	
    		String sql=  "insert into days_off VALUES ('"+menu1.getValue()+"','"+menu2.getValue()+"','"+getdays(7)+"','"+getdays(1)+"','"+getdays(2)+"','"+getdays(3)+"','"+getdays(4)+"','"+getdays(5)+"','"+getdays(6)+"');";
    		Statement statement= ficheController.connectio.createStatement();
    		statement.executeUpdate(sql);
    		statement.close();

    	
    }
  int getdays(int jour) 
    { 
       
    	int count=0; 
    	Calendar c1=Calendar.getInstance();
    	int da =Integer.parseInt(new SimpleDateFormat("dd").format(c1.getTime()));
       	int ye =Integer.parseInt(new SimpleDateFormat("YYYY").format(c1.getTime()));
       	int mo=Integer.parseInt(new SimpleDateFormat("MM").format(c1.getTime()));
    	for(int i=da;i<=Integer.parseInt(menu2.getValue());i++) 
    	{
    		c1.set(ye, mo-1, i);
            int day=c1.get(Calendar.DAY_OF_WEEK); 
            if(day==jour) 
            { count++;}
     
    	} 
    return count; 
    } 

}
