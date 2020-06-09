package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class messageValidationInscription implements Initializable  {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 
		
		
		
		
	}
	 @FXML
	 public void close(ActionEvent event) throws IOException 
	 {
		 	
     		
     		Parent home= FXMLLoader.load(getClass().getResource("gestionAdherent.fxml"));
        	Scene homeScene = new Scene (home, 1000	, 700);
        	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
     		app.hide();
             app.setTitle("New Window");
             app.setScene(homeScene);
             app.show();
		 
		 
	 }
	
	
	
	

}
