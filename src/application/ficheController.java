package application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
//import javafx.geometry.Insets;

import com.github.sarxos.webcam.Webcam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
	// if closing stm the resultset is closed  and we need to close it after that to be sure
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class ficheController  implements Initializable   {
	  //variables
     static  String part1;
     static  connectionClas connectionClass=new connectionClas();
     static Connection connectio=connectionClass.getConnection();
     String added;
     Statement stmt3;
     ResultSet rs3;
      // ajouter retour button
     //catch exceptions when inserting wrong stuff
	@FXML
	 public TextField txt0;
	@FXML
	 public TextField txt1;
	@FXML
	 public TextField txt2;
	@FXML
	 public DatePicker txt3;
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
	public AnchorPane t2;
	File fil = new File("Female.jpg");
    Image ima = new Image(fil.toURI().toString());
    
    //discipline display
    @FXML 
	public BorderPane borderCheck;	
	ArrayList<CheckBox> places = new ArrayList<CheckBox>();
    VBox layout = new VBox(10);
    static CheckBox cb;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		

			picture.setImage(ima);
			dateInsc.setText(java.time.LocalDate.now().toString());
			int rowcount=0;
			try {
	        	 
	        	 		stmt3 = connectio.createStatement();
	        	 		rs3 = stmt3.executeQuery("SELECT id FROM adherent ");
	        	 		if (!rs3.next() ) {
	        	 			rowcount =1;
	    	        	 	  added="000"+Integer.toString(rowcount);  

	        	 		   
	        	 		} else {
	        	 			
	        	 			stmt3 = connectio.createStatement();
		        	 		rs3 = stmt3.executeQuery("SELECT MAX(id) FROM adherent ");
		        	 		rs3.next();
	    	        	    String  part=rs3.getString(1).substring(rs3.getString(1).length() - 4);

		        	 	    rowcount = Integer.parseInt(part);
	    	        	    rowcount++;
	    	        	    
	    	        	    
	    	        	    

	        	 			if (Integer.toString(rowcount).length()==1) 
	    	        	 	{   added="000"+Integer.toString(rowcount);  }
	    	        	 	if (Integer.toString(rowcount).length()==2) 
	    	        	 	{   added="00"+Integer.toString(rowcount);  }
	    	        	 	if (Integer.toString(rowcount).length()==3) 
	    	        	 	{   added="0"+Integer.toString(rowcount);  }
	    	        	 	if (Integer.toString(rowcount).length()==4) 
	    	        	 	{   added=Integer.toString(rowcount);  }	 
	        	 		}
	        	 	
	        	 	
	        	 	
	        	    //give a new id to the new adherent
	        	    LocalDate temps =java.time.LocalDate.now();
	        	    String[] parts = temps.toString().split("-");
	        	    String  part=parts[0].substring(parts[0].length() - 2);
	        	    		part1= part+added; 
	        	    		stmt3.close();
	        	    		rs3.close();	
/**************************Afficher les disciplines****************************************/
	        	    	/*	
	        	    		stmt3 = connectio.createStatement();
	        	    		rs3 = stmt3.executeQuery("SELECT distinct nom  FROM discipline ");		
	        	    		while(rs3.next())
	        	    		{
	        	    			places.add(new CheckBox ((String)rs3.getString(1)));
	        	    			
	        	    		}	        	    		
	        	    		layout.setPadding(new Insets(20, 20, 20, 300));
	        			    layout.getChildren().addAll(places);
	        				t2.getChildren().add(layout);
	        	    		stmt3.close();
	        	    		rs3.close();
	        	    */
	        	    		        	    		
/**************************Fin Afficher les disciplines****************************************/
	        	    		
	         	} catch (SQLException e) {
	             e.printStackTrace();
	         	} 
	         	txt0.setText(part1);//id calculer
	              	
	         	

	}  
	  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //functions of buttons 
	 @FXML
	    public void save(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException  {    
	           
		 Date date = Date.valueOf(txt3.getValue());
		 String d= new SimpleDateFormat("yyyy/MM/dd").format(date);

		 
		 		 String sql=  "insert into adherent VALUES ("+part1+", '"+ txt1.getText()+"', '"+ txt2.getText()+"','"+txt5.getText()+"','"+txt6.getText()+"','"+txt7.getText()+"','"+txt8.getText()+"','"+txt4.getText()+"',STR_TO_DATE('"+d +"', '%Y/%m/%d')"  	+",STR_TO_DATE('"+java.time.LocalDate.now()+"', '%Y-%m-%d')"+",'adherentPics/"+part1+".png');";
	        	 Statement statement=connectio.createStatement();
	        	 statement.executeUpdate(sql);
	        	 statement.close();
	        	
	        	//see if checkbox is selected and save it when enregister is clicked
	     	/*	for(int i =0 ; i<places.size();i++)
	     		{
	     				if(places.get(i).isSelected())
	     				{
	     					 sql=  "insert into adherent_discipline VALUES ("+part1+",'"+places.get(i).getText()+"');";
	     		        	 statement=connectio.createStatement();
	     		        	 statement.executeUpdate(sql);
	     		        	 statement.close();
	     					
	     				
	     				}
 	    		    		  
	     		}	*/     		
	     		//message box and close fiche
	     		
	     		Parent home= FXMLLoader.load(getClass().getResource("messageValidationInscription.fxml"));
	        	Scene homeScene = new Scene (home, 350	, 130);
	     		Stage app =(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
	        	app.hide();
	        	app.setScene(homeScene);
	        	app.show();
	     		// stopped here new scene
       	 
	    }
	 
	 @FXML
	 public void takePic(ActionEvent event) throws IOException 
	 {
		 try {  	 	Webcam webcam = Webcam.getDefault();
		 Dimension d = new Dimension(400, 400);
		 webcam.setCustomViewSizes(new Dimension[] { d });
		 webcam.setViewSize(d);
		 				webcam.open();
		 				

				ImageIO.write(webcam.getImage(), "PNG", new File("adherentPics/"+part1+".png"));
		        File file = new File("adherentPics/"+part1+".png");
		        Image image = new Image(file.toURI().toString());
				picture.setImage(image);
				webcam.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	 }
	 
	 //Retour
	    @FXML
	    public void loadRetour(ActionEvent event) throws IOException 
	    {	//retour button
	    	Parent home= FXMLLoader.load(getClass().getResource("gestionAdherent.fxml"));
	    	Scene homeScene = new Scene (home, 1000	, 700);
	    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
	    	app.hide();
	    	app.setScene(homeScene);
	    	app.show();
	    	
	    }
	  
	    
}
