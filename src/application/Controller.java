package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class Controller implements Initializable {
	
	

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	Calendar c1=Calendar.getInstance();
    	if (c1.get(Calendar.DAY_OF_MONTH) == 1)
    	{
    	
    			PreparedStatement updateEXP;
				try {
					updateEXP = ficheController.connectio.prepareStatement("update val_pay  set nbr_seance_restante= 0 ");
					updateEXP.executeUpdate();
					updateEXP.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    	
    	}
    	    
    	
    			
    	
    	

    	   
    	
       
    }
    //load and hide old stage 
    @FXML
    public void load(ActionEvent event) throws IOException 
    {
    	Parent home= FXMLLoader.load(getClass().getResource("gestionAdherent.fxml"));
    	Scene homeScene = new Scene (home, 1500	, 700);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
    }
    
  //load and hide old stage 
    @FXML
    public void load_val(ActionEvent event) throws IOException 
    {
    	Parent home= FXMLLoader.load(getClass().getResource("val_pay.fxml"));
    	Scene homeScene = new Scene (home, 1500	, 700);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
    }
    
    //load and hide old stage 
    @FXML
    public void load_gestion(ActionEvent event) throws IOException 
    {
    	Parent home= FXMLLoader.load(getClass().getResource("gestionParametre.fxml"));
    	Scene homeScene = new Scene (home, 1500	, 700);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
    }
    
    
    
    
    

}