package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class val_pay_controller  implements Initializable {	
	@FXML
	TextField search;
	@FXML
	ChoiceBox<String> menu;
	@FXML
	ChoiceBox<String> menu2;
	@FXML
	TextField txt1;
	@FXML
	TextField txt2;
	@FXML
	TextField txt3;
	@FXML
	TextField txt4;
	@FXML
	TextField txt5;
	@FXML
	TextField txt6;
	@FXML
	TextField txt7;
	@FXML
	TextField txt8;
	@FXML
	TableView<reduction> table;
	@FXML
	TextField txt9;
	@FXML
	TextField txt10;
	@FXML
	TextField txt11;
	@FXML
	TextField txt12;
	@FXML
	TextField txtpaye;
	

	
	Statement stmt3;
    ResultSet rs3;
	Statement stmt4;
    ResultSet rs4;
    Statement stmt5;
    ResultSet rs5;
    Statement stmt6;
    ResultSet rs6;
    Statement stmt7;
    ResultSet rs7;
    
	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {	
	//do not move colomns
	table.widthProperty().addListener(new ChangeListener<Number>()
	{
	    @Override
	    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
	    {
	        TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
	        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	                header.setReordering(false);
	            }
	        });
	    }
	});	
	
	
///////////////////search////////////////////////////////////////////////////////////////////////////////////////////////////////////
		search.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    ObservableList<String> data= FXCollections.observableArrayList();	


				try {
					if(search.getText().length()==6) {

					stmt3 = ficheController.connectio.createStatement();
					rs3 = stmt3.executeQuery("SELECT distinct nom FROM  discipline");
					while (rs3.next())
					{
						
						data.add(rs3.getString(1));
					}
					menu2.setItems(data);
					stmt3.close();
					rs3.close();
				    data= FXCollections.observableArrayList();	
					stmt3 = ficheController.connectio.createStatement();
					rs3 = stmt3.executeQuery("SELECT discipline FROM  val_pay where id_adherent='"+search.getText()+"'");
					while (rs3.next())
					{
						
						data.add(rs3.getString(1));
					}
					menu.setItems(data);
					stmt3.close();
					rs3.close();
				}else {
					   data= FXCollections.observableArrayList();	
						menu.setItems(data);
						menu2.setItems(data);
						txt1.clear(); txt2.clear(); txt3.clear(); txt4.clear(); txt5.clear(); txt6.clear(); txt7.clear(); txt8.clear(); txtpaye.clear();}	
					txt5.setEditable(false);

				} catch (SQLException e) {					
					e.printStackTrace();
				}
							
			}		  
		});			
////////////////table remise//////////////////////////////////////////////////////////////////////////////////////////////////////////
	    ObservableList<reduction> dataReduc= FXCollections.observableArrayList();

		TableColumn<reduction,String> c1 = new TableColumn<reduction, String>("Nom1");
	 	c1.setCellValueFactory(new PropertyValueFactory<reduction,String>("nom1"));

	    TableColumn<reduction,String> c2 = new TableColumn<reduction, String>("Nom2");
	    c2.setCellValueFactory(new PropertyValueFactory<>("nom2"));
	    
	    TableColumn<reduction,String> c3 = new TableColumn<reduction, String>("nbr1");
	    c3.setCellValueFactory(new PropertyValueFactory<>("nbr1"));
  
	     TableColumn<reduction,String> c5 = new TableColumn<reduction, String>("nbr2");
	     c5.setCellValueFactory(new PropertyValueFactory<>("nbr2"));
 
	    TableColumn<reduction,String> c6 = new TableColumn<reduction, String>("prix");
	    c6.setCellValueFactory(new PropertyValueFactory<>("prix"));
 
        c1.setMinWidth(150d);
        c2.setMinWidth(150d);
        c3.setMinWidth(100d);
        c6.setMinWidth(100d);
        c5.setMinWidth(100d);
       

        // did remove that warning
	    		 table.getColumns().addAll(c1,c2,c3,c5,c6);		
	    		 try {
	    				stmt3 = ficheController.connectio.createStatement();
	    				rs3 = stmt3.executeQuery("SELECT * FROM reduction");
	    		 		
	    						while(rs3.next())
	    						{			 
	    							dataReduc.add(new reduction(rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5))); 
	    	    
	    						}
	    		 
	    				        table.setItems(dataReduc);

	    		 		stmt3.close();
	    	    		rs3.close();
	    	    		 		
	    			} catch (SQLException e) {
	    				e.printStackTrace();
	    			}
////////////////somme a payé here/////////////////////////////////////////////////////////////////////////////////////////////////////
txt4.textProperty().addListener(new ChangeListener<String>() {
	//cas etudiante et remise 
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if(txt4.getText().length()==1 || txt4.getText().length()>1) {
			



				try {
					stmt3 = ficheController.connectio.createStatement();
					rs3 = stmt3.executeQuery("SELECT MAX(nbr_seance_par_semaine) FROM discipline where nbr_seance_par_semaine <= "+txt4.getText()+" and nom ='"+menu2.getValue()+"'");
					rs3.next();
					
					stmt4 = ficheController.connectio.createStatement();
					rs4 = stmt4.executeQuery("SELECT prix  FROM discipline where  nom='" +menu2.getValue()+  "' and nbr_seance_par_semaine ='"+rs3.getString(1)+"'");
					rs4.next();
					
				
					txt5.setText(Integer.toString(Integer.parseInt(rs4.getString(1))*Integer.parseInt(txt4.getText())));
						
						
					stmt3.close();
					rs3.close();
					stmt4.close();
					rs4.close();
					
					
				} catch (SQLException e) {					
					e.printStackTrace();
				}
								
			}		}  
		});	
////////////////somme manquante/////////////////////////////////////////////////////////////////////////////////////////////////////
txt7.textProperty().addListener(new ChangeListener<String>() {
@Override
public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
if(txt7.getText().length()==1 || txt7.getText().length()>1) {

				txt8.setText( Integer.toString(Integer.parseInt(txt5.getText()) - Integer.parseInt(txt7.getText())) );

				
}		}  
});	
//validation menu///////////////////////////////////////////////////////////////////////////////////////////////////	
		menu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		    	  try {
					stmt3 = ficheController.connectio.createStatement();
					rs3 = stmt3.executeQuery("SELECT somme_manq , nbr_seance_restante,somme_pay FROM val_pay where id_adherent= '"+search.getText()+"' and discipline='" +menu.getItems().get((Integer) number2)+        "'");
				    rs3.next();  
					txt1.setText(rs3.getString(2));
					txt2.setText(rs3.getString(1));
					txtpaye.setText(rs3.getString(3));

					stmt3.close();
					rs3.close();
					txt5.setEditable(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		      }
		    }
		);	
//payement menu////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
menu2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		    	  try {
		    		  	txt8.clear();
		    		  	txt7.clear();
		    		  	txt5.clear();
		    		  	txt4.clear();
		    		   	Date d2;
		    		   	Date d1;
		    		  	int result=0;
		    		     DateFormat formatter =new SimpleDateFormat("HH:mm"); 
		    			int cpt=0;
		    		  	// avoir date et heure actuelle
		    		  	Calendar c2=Calendar.getInstance();
				       	int d =c2.get(Calendar.DAY_OF_WEEK);
				    	String h =new SimpleDateFormat("HH:mm").format(c2.getTime());
				    	int restdaysemain=0;

				      stmt3 = ficheController.connectio.createStatement();
		    		  rs3 = stmt3.executeQuery("SELECT distinct nom_jour FROM horaire_semaine where  discipline='" +menu2.getItems().get((Integer) number2)+  "'");
		    		
		    		  	while (rs3.next())
						{
							restdaysemain=restdaysemain+getdays(Integer.parseInt(rs3.getString(1)))-getdaysoff(Integer.parseInt(rs3.getString(1)));	
							stmt4 = ficheController.connectio.createStatement();
							rs4 = stmt4.executeQuery("SELECT COUNT(*)  FROM horaire_semaine where  discipline='" +menu2.getItems().get((Integer) number2)+  "' and nom_jour ='"+rs3.getString(1)+"'");
							rs4.next();
							result =result +( rs4.getInt(1)*getdays( Integer.parseInt(rs3.getString(1)))) -( rs4.getInt(1)*getdaysoff( Integer.parseInt(rs3.getString(1)))) ;							
							System.out.println("result"+result);

						}	
						//si date actuelle = nom jour alors 
						// voir cmb de séance on eu lieu avnt l heure actuelle puis les retranché pour ensuite affiché le resultat
						stmt3.close();
						rs3.close();
						stmt4.close();
						rs4.close();
							stmt3 = ficheController.connectio.createStatement();
							rs3 = stmt3.executeQuery("SELECT distinct nom_jour FROM horaire_semaine where  discipline='" +menu2.getItems().get((Integer) number2)+  "'");
							while(rs3.next())
							{
								int p =getdaysoff(Integer.parseInt(rs3.getString(1)));
								// si today is jeudi and is also in dayss of alors n enléve rien a résult car impossible im in dayoff and i m  removing des séance be sure !
								if (d == Integer.parseInt(rs3.getString(1)) && p==0 )
								{
							       	stmt4 = ficheController.connectio.createStatement();
									rs4 = stmt4.executeQuery("SELECT heure  FROM horaire_semaine where  discipline='" +menu2.getItems().get((Integer) number2)+  "' and nom_jour ='"+Integer.toString(d)+"'");
									while (rs4.next())
									{
										d1 = formatter.parse(h);
										d2 = formatter.parse(rs4.getString(1));
										long diff = d2.getTime() - d1.getTime();
										if (diff< 0)
										{cpt++;}
									
									}
								}else {cpt=0;}
							}
							System.out.println(cpt);
							//count days off
						result=result-cpt;
						txt3.setText(Integer.toString(result));
						txt6.setText(Integer.toString(restdaysemain));

					stmt3.close();
					rs3.close();
					
					stmt4.close();
					rs4.close();
					txt5.setEditable(false);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//see if there is a dscipline
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		      }
		    });	
	}
//////////////////BUTTON//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //Retour
    @FXML
    public void loadRetour(ActionEvent event) throws IOException 
    {	
    	Parent home= FXMLLoader.load(getClass().getResource("after connexion.fxml"));
    	Scene homeScene = new Scene (home, 800	, 500);
    	Stage app =(Stage) ((Node) event.getSource()).getScene().getWindow();
    	app.hide();
    	app.setScene(homeScene);
    	app.show();
    	
    }
//Valider
    @FXML
    public void valider(ActionEvent event) throws IOException, SQLException 
    {	
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
    	   LocalDateTime now = LocalDateTime.now();  
    	   
                PreparedStatement updateEXP = ficheController.connectio.prepareStatement("update val_pay  set nbr_seance_restante=nbr_seance_restante-1 where id_adherent ='"+search.getText()+"' and discipline='" +menu.getValue()+        "'");
				updateEXP.executeUpdate();
		
				rs3 = updateEXP.executeQuery("SELECT nbr_seance_restante FROM val_pay where id_adherent= '"+search.getText()+"' and discipline='" +menu.getValue()+        "'");
			    rs3.next();  
				txt1.setText(rs3.getString(1));
				updateEXP.close();
				
				 String sql=  "insert into historique_adherent VALUES ('"+search.getText()+"', '"+dtf.format(now)+"','"+ menu.getValue()+"');";
				 Statement statement= ficheController.connectio.createStatement();
				 statement.executeUpdate(sql);
				 statement.close();
				 
				
				
    	
    }
//Annuler 
    @FXML
    public void annuler(ActionEvent event) throws IOException, SQLException 
    {	
    	
                PreparedStatement updateEXP = ficheController.connectio.prepareStatement("update val_pay  set nbr_seance_restante=nbr_seance_restante+1 where id_adherent ='"+search.getText()+"' and discipline='" +menu.getValue()+        "'");
				updateEXP.executeUpdate();
				rs3 = updateEXP.executeQuery("SELECT nbr_seance_restante FROM val_pay where id_adherent= '"+search.getText()+"' and discipline='" +menu.getValue()+        "'");
			    rs3.next();  
				txt1.setText(rs3.getString(1));
				updateEXP.close();
				//remove from historique
				
				stmt3 = ficheController.connectio.createStatement();
				rs3 = stmt3.executeQuery("SELECT Max(laDate) FROM historique_adherent where id_adherent= '"+search.getText()+"' and discipline='" +menu.getValue()+ "'");
			    rs3.next();  
			    String max=rs3.getString(1);
			    updateEXP.close();
			    rs3.close();
				
				 String sql=  "delete from  historique_adherent where (id_adherent= '"+search.getText()+"') and  (laDate='"+max+"' )   and (discipline='"+menu.getValue()+"') ;";
				 Statement statement= ficheController.connectio.createStatement();
				 statement.executeUpdate(sql);
				 statement.close();
				
				
				
				
				
    	
    }
  //enregister button avec verification de faute
    @FXML
    public void enregister(ActionEvent event) throws IOException, SQLException 
    {		
        txt1.clear();
        txt2.clear();
		ObservableList<String> data= FXCollections.observableArrayList();	

    	stmt4 = ficheController.connectio.createStatement();
		rs4 = stmt4.executeQuery("SELECT * FROM val_pay where  discipline='" +menu2.getValue()+  "' and id_adherent ='"+search.getText()+"'");
		
			if(!rs4.next())
			{
				//insert
				 String sql=  "insert into val_pay VALUES ("+search.getText()+", '"+ menu2.getValue()+"', '"+ txt4.getText()+"','"+txt7.getText()+"','"+txt8.getText()+"');";
				 Statement statement= ficheController.connectio.createStatement();
				 statement.executeUpdate(sql);
				 statement.close();
				 
					stmt3 = ficheController.connectio.createStatement();
					rs3 = stmt3.executeQuery("SELECT discipline FROM  val_pay where id_adherent='"+search.getText()+"'");
					while (rs3.next())
					{
						
						data.add(rs3.getString(1));
					}
					menu.setItems(data);
					menu.setValue(menu2.getValue());
					stmt3.close();
					rs3.close(); 
				 
				 
				
				
			}else {
				//update somme pay+ somme manquante+ nbr seance +

				PreparedStatement  updateEXP = ficheController.connectio.prepareStatement("update val_pay  set nbr_seance_restante=nbr_seance_restante+'"+txt4.getText()+"', somme_pay=somme_pay+'"+txt7.getText()+"' , somme_manq=somme_manq+'"+txt8.getText()+"' where id_adherent ='"+search.getText()+"' and discipline='" +menu2.getValue()+        "'");
				updateEXP.executeUpdate();
				updateEXP.close();

				
				
			}
			
			stmt5= ficheController.connectio.createStatement();
			rs5 = stmt5.executeQuery ("SELECT nbr_seance_restante, somme_manq FROM val_pay where id_adherent= '"+search.getText()+"' and discipline='" +menu2.getValue()+        "'");
		    rs5.next();  
			txt1.setText(rs5.getString(1));
			txt2.setText(rs5.getString(2));
			menu.setValue(menu2.getValue());

			stmt5.close();
			rs5.close();
			
			stmt4.close();
			rs4.close();
			txt5.setEditable(false);
    }

    	////////////////////////////////////////selected item table
    @FXML
    public void clickItem(MouseEvent event) throws SQLException, ParseException
    {
        if (event.getClickCount() == 1) //Checking one click
        {
        	
             txt4.clear();
             txt6.clear();
             txt3.clear();
             txt8.clear();
             txt7.clear();
             txt1.clear();
             txt2.clear();
             

             txt9.setText(table.getSelectionModel().getSelectedItem().getnom1());
             txt10.setText(table.getSelectionModel().getSelectedItem().getnom2());
             txt5.setText(table.getSelectionModel().getSelectedItem().getprix());	
             txt11.setText(nbrdaysremise(table.getSelectionModel().getSelectedItem().getnom1()));
             txt12.setText(nbrdaysremise(table.getSelectionModel().getSelectedItem().getnom2()));
             txt5.setEditable(false);
             
       
           
        }
    }
////////////fonction//////////////////////////////////////////////////////////////////////////////////////////////////	    
    public int getdays(int jour) 
    { 
       
    	int count=0; 
    	Calendar c1=Calendar.getInstance();
    	int da =Integer.parseInt(new SimpleDateFormat("dd").format(c1.getTime()));
       	int ye =Integer.parseInt(new SimpleDateFormat("YYYY").format(c1.getTime()));
       	int mo=Integer.parseInt(new SimpleDateFormat("MM").format(c1.getTime()));
    	int days = c1.getActualMaximum(Calendar.DAY_OF_MONTH); // nombre max de jour du  mois courant 
    	for(int i=da;i<=days;i++) 
    	{
    		c1.set(ye, mo-1, i);
            int day=c1.get(Calendar.DAY_OF_WEEK); 
            if(day==jour) 
            { count++;}
     
    	} 
    return count; 
    } 
    //
    int getdaysoff(int jour) throws SQLException 
    { 
    	
    	stmt7= ficheController.connectio.createStatement();
		rs7 = stmt7.executeQuery("SELECT du,a  FROM  days_off");
		rs7.next();
        
    	int count=0; 
    	Calendar c1=Calendar.getInstance();
       	int ye =Integer.parseInt(new SimpleDateFormat("YYYY").format(c1.getTime()));
       	int mo=Integer.parseInt(new SimpleDateFormat("MM").format(c1.getTime()));
    	for(int i=Integer.parseInt(rs7.getString(1));i<=Integer.parseInt(rs7.getString(2));i++) 
    	{
    		c1.set(ye, mo-1, i);
            int day=c1.get(Calendar.DAY_OF_WEEK); 
            if(day==jour) 
            { count++;}
     
    	}
    	stmt7.close();
    	rs7.close();
    	
    return count; 
    } 
    
  //fonction retourne nbr de jours pour la partie remise
 String  nbrdaysremise(String discipline) throws ParseException
 {
	 
 	int restdaysemain=0;

	 try {
		   	Date d2;
		   	Date d1;
		  	int result=0;
		     DateFormat formatter =new SimpleDateFormat("HH:mm"); 
			int cpt=0;
		  	// avoir date et heure actuelle
		  	Calendar c2=Calendar.getInstance();
	       	int d =c2.get(Calendar.DAY_OF_WEEK); 
	    	String h =new SimpleDateFormat("HH:mm").format(c2.getTime());

	      stmt3 = ficheController.connectio.createStatement();
		  rs3 = stmt3.executeQuery("SELECT distinct nom_jour FROM horaire_semaine where  discipline='" +discipline+  "'");
		
		  
		  	while (rs3.next())
			{
				restdaysemain=restdaysemain+getdays(Integer.parseInt(rs3.getString(1)));	
				stmt4 = ficheController.connectio.createStatement();
				rs4 = stmt4.executeQuery("SELECT COUNT(*)  FROM horaire_semaine where  discipline='" +discipline+  "' and nom_jour ='"+rs3.getString(1)+"'");
				rs4.next();
				result =result +( rs4.getInt(1)*getdays( Integer.parseInt(rs3.getString(1))) );							
			}	
			//si date actuelle = nom jour alors 
			// voir cmb de séance on eu lieu avnt l heure actuelle puis les retranché pour ensuite affiché le resultat
			stmt3.close();
			rs3.close();
			stmt4.close();
			rs4.close();
				stmt3 = ficheController.connectio.createStatement();
				rs3 = stmt3.executeQuery("SELECT distinct nom_jour FROM horaire_semaine where  discipline='" +discipline+  "'");
				while(rs3.next())
				{
					if (d == Integer.parseInt(rs3.getString(1)))
					{
				       	stmt4 = ficheController.connectio.createStatement();
						rs4 = stmt4.executeQuery("SELECT heure  FROM horaire_semaine where  discipline='" +discipline+  "' and nom_jour ='"+Integer.toString(d)+"'");
						while (rs4.next())
						{
							d1 = formatter.parse(h);
							d2 = formatter.parse(rs4.getString(1));
							long diff = d2.getTime() - d1.getTime();
							if (diff< 0)
							{cpt++;}
						
						}
					}	
				}
			result=result-cpt;

		stmt3.close();
		rs3.close();
		
		stmt4.close();
		rs4.close();			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//see if there is a dscipline
	}
	 
	 
	 
	 
	 return (Integer.toString(restdaysemain));
 }
    
 //enregister button avec verification de faute
 @FXML
 public void enregisterRemise(ActionEvent event) throws IOException, SQLException 
 {				ObservableList<String> data= FXCollections.observableArrayList();	

     txt1.clear();
     txt2.clear(); 
 		stmt4 = ficheController.connectio.createStatement();
		rs4 = stmt4.executeQuery("SELECT * FROM val_pay where  discipline='" +txt9.getText()+  "' and id_adherent ='"+search.getText()+"'");
			if(!rs4.next())
			{
				//insert
				 String sql=  "insert into val_pay VALUES ("+search.getText()+", '"+ txt9.getText()+"', '"+table.getSelectionModel().getSelectedItem().getnbr1()+"','"+txt7.getText()+"','"+txt8.getText()+"');";
				 Statement statement= ficheController.connectio.createStatement();
				 statement.executeUpdate(sql);
				 statement.close();
		
			}else {
				//update somme pay+ somme manquante+ nbr seance +

				PreparedStatement  updateEXP = ficheController.connectio.prepareStatement("update val_pay  set nbr_seance_restante=nbr_seance_restante+'"+table.getSelectionModel().getSelectedItem().getnbr1()+"', somme_pay=somme_pay+'"+txt7.getText()+"' , somme_manq=somme_manq+'"+txt8.getText()+"' where id_adherent ='"+search.getText()+"' and discipline='" +txt9.getText()+        "'");
				updateEXP.executeUpdate();
				updateEXP.close();
			}
			stmt4.close();
			rs4.close();	
			
			
			
			stmt4 = ficheController.connectio.createStatement();
			rs4 = stmt4.executeQuery("SELECT * FROM val_pay where  discipline='" +txt10.getText()+  "' and id_adherent ='"+search.getText()+"'");
				if(!rs4.next())
				{
					//insert
					 String sql=  "insert into val_pay VALUES ("+search.getText()+", '"+ txt10.getText()+"', '"+ table.getSelectionModel().getSelectedItem().getnbr2()+"','0','0');";
					 Statement statement= ficheController.connectio.createStatement();
					 statement.executeUpdate(sql);
					 statement.close();
			
				}else {
					//update somme pay+ somme manquante+ nbr seance +

					PreparedStatement  updateEXP = ficheController.connectio.prepareStatement("update val_pay  set nbr_seance_restante=nbr_seance_restante+'"+table.getSelectionModel().getSelectedItem().getnbr2()+"'  where id_adherent ='"+search.getText()+"' and discipline='" +txt10.getText()+        "'");
					updateEXP.executeUpdate();
					updateEXP.close();
				}
				stmt4.close();
				rs4.close();	
				
				stmt3 = ficheController.connectio.createStatement();
				rs3 = stmt3.executeQuery("SELECT discipline FROM  val_pay where id_adherent='"+search.getText()+"'");
				while (rs3.next())
				{
					
					data.add(rs3.getString(1));
				}
				menu.setItems(data);
				stmt3.close();
				rs3.close(); 
				
				
				txt5.setEditable(false);
 }
 //button autre somme
 //enregister button avec verification de faute
 @FXML
 public void autre(ActionEvent event) throws IOException, SQLException 
 {
	 //payé autre somme que le total calculé
		txt5.setEditable(true);
 
 }
 
 @FXML
 public void changepay(ActionEvent event) throws IOException, SQLException 
 {

		PreparedStatement  updateEXP = ficheController.connectio.prepareStatement("update val_pay  set  somme_pay = '"+txtpaye.getText()+"'where id_adherent ='"+search.getText()+"' and discipline='" +menu.getValue()+        "'");
		updateEXP.executeUpdate();
		updateEXP.close();
		
 
 }
 
 @FXML
 public void changemanq(ActionEvent event) throws IOException, SQLException 
 {

		PreparedStatement  updateEXP = ficheController.connectio.prepareStatement("update val_pay  set  somme_manq = '"+txt2.getText()+"'where id_adherent ='"+search.getText()+"' and discipline='" +menu.getValue()+        "'");
		updateEXP.executeUpdate();
		updateEXP.close();
		
 
 }
 
}



