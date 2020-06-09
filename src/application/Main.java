package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	 
    public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	
    	
    	Parent root= FXMLLoader.load(getClass().getResource("after connexion.fxml"));
        primaryStage.setTitle("New window");
        primaryStage.setScene(new Scene(root, 800	, 500)); // 3ord et toul
        primaryStage.show();
    }


}