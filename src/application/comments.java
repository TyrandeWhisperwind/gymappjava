package application;


public class comments {
	
	
	
	/*
	 * 
	 * 
	 * 
	 *  MatlabProxyFactory factory = new MatlabProxyFactory();
			 MatlabProxy proxy = factory.getProxy();
			 int q=0;
			 cpt=0;
			 while (q<i.numAttributes() )
			 {cpt=0;
			 proxy.eval( "state={};");		 

			 if (i.attribute(q).isNominal() && !(i.attribute(q).isDate())  )
				{
			 		while (cpt<i.size())
			 			{
				 			proxy.eval( "state(end+1)={'"+i.instance(cpt).stringValue(q)+"'};");
				 			cpt++;
				 		}
			 		proxy.eval( "state = categorical(state);");
					proxy.eval( "hist(state)" );
					proxy.eval( "saveas(gcf,'fig"+q+".png');" );
				 }

		
			 
				q++;
			 }
				 	
			 //boxplot
			 cpt=0;q=0;
				proxy.eval( "f= zeros("+i.size()+","+i.numAttributes()+")");
					while (q<i.numAttributes() )
					 {cpt=0;
					 
					 if (i.attribute(q).isNumeric() && !(i.attribute(q).isDate())  )
						{
					 	while (cpt<i.size())
					 		{
							proxy.eval( "f("+(cpt+1)+","+(q+1)+")="+i.instance(cpt).value(q)+";\r\n");
							cpt++;
					 		}
						}
					 	q++;
					 }
					proxy.eval( "boxplot(f);");
					proxy.eval( "saveas(gcf,'figure.png');" );
					proxy.eval( "quit;" );*/
	
	
	
	/***************************************************************/	
	
	
	/* if (i.attribute(q).isNumeric() && !(i.attribute(q).isDate())  )
	{
 		while (cpt<i.size())
 			{
	 			proxy.eval( "state(end+1)={'"+i.instance(cpt).value(q)+"'};");
	 			cpt++;
	 		}
 		proxy.eval( "state = categorical(state);");
		proxy.eval( "h=pie(state)" );
 		proxy.eval( "set(findobj(h,'type','text'),'fontsize',6);");
 		proxy.eval( " H=findobj(gca,'Type','text');\r\n" + 
 				" set(H,'Rotation',60); % tilt");
		proxy.eval( "saveas(gcf,'fig"+q+".png');" );
	 }
 */
    /*	String[] values;
			String[] value;
			String [] valcinq;
			int remise=0;
		    ArrayList<String[]> action = new ArrayList<String[]>();
		    ArrayList<String[]> act = new ArrayList<String[]>();
		    ArrayList<String[]> act2= new ArrayList<String[]>();
		    ArrayList<String[]> ac= new ArrayList<String[]>();
		    
			stmt5 = ficheController.connectio.createStatement();
			rs5 = stmt5.executeQuery("SELECT discipline, nbr_seance_restante FROM val_pay where  id_adherent='" +search.getText()+ "'");
			while (rs5.next())
			{
				
				values=new String[2];
				values[0]=rs5.getString(1);
				values[1]=rs5.getString(2);
				action.add(values);
				
			}
			
			stmt6 = ficheController.connectio.createStatement();
			rs6 = stmt6.executeQuery("SELECT * FROM reduction ");
			while (rs6.next())
			{
				
				valcinq=new String[5];
				valcinq[0]=rs6.getString(1);
				valcinq[1]=rs6.getString(2);
				valcinq[2]=rs6.getString(3);
				valcinq[3]=rs6.getString(4);
				valcinq[4]=rs6.getString(5);
				act.add(valcinq);
				
			}
		   for(int i=0;i<action.size()-1;i++)
		   {
			   values=action.get(i);
			   for(int j=i+1;j<action.size();j++)
		       {
		    	   		value=action.get(j);
		    	   		valcinq=new String[5];
						valcinq[0]=values[0];
						valcinq[1]=value[0];
						valcinq[2]=values[1];
						valcinq[3]=value[1];
						valcinq[4]="0";
						act2.add(valcinq);
						
					
					   		
				   		
			       }
		    	   
		       
		       }



		   String[] val= new String[5];
			for( int i=0;i<act2.size();i++) {
					 valcinq=act2.get(i);
				   	for( int j=0;j<act.size();j++) {
				   		val=act.get(j);
				   		// transformet to int  mieux vérifier les combinaison
				   		
		if(  (valcinq[0].equals( val[0] ) && valcinq[1].equals(val[1]) && Integer.parseInt(valcinq[2])>=Integer.parseInt(val[2]) && Integer.parseInt(valcinq[3])>=Integer.parseInt(val[3]) )
		||  (valcinq[1].equals( val[0] ) && valcinq[0].equals(val[1]) && Integer.parseInt(valcinq[3])>=Integer.parseInt(val[2]) && Integer.parseInt(valcinq[2])>=Integer.parseInt(val[3])  )	

		){ 
			ac.add(val);
			
		   		
				
		}
				}

			}


			for(int i=0;i<ac.size();i++)
			{
				val=ac.get(i);
				for(int j=i;j<ac.size();j++)
				{
					valcinq=ac.get(j);
					if(val[0].equals(valcinq[0]) && val[1].equals(valcinq[1]) && Integer.parseInt(val[4]) > Integer.parseInt(valcinq[4]))
					{
						ac.remove(j);////here
					}
					if(val[0].equals(valcinq[0]) && val[1].equals(valcinq[1]) && Integer.parseInt(val[4]) < Integer.parseInt(valcinq[4]))
					{
						ac.remove(i);////here
					}
					
					
					
				}
				
			}
			
			for(int j=0;j<ac.size();j++)
			{
				val=ac.get(j);
				System.out.println(val[0]+" "+val[1]+" "+val[2]+ " "+val[3]+" "+val[4]);
				remise=remise+Integer.parseInt(val[4]);
				
			}

			stmt6.close();
		rs6.close();
		txt9.setText(Integer.toString(remise));
			
			//remove waht have been calculated with an update somme payé 
			//find a solution so it wont show each time 
	        	*/

}
