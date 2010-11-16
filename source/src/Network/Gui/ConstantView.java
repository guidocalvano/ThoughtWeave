package Network.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gui.ComboBoxMenu;
import Gui.FloatField;
import Network.*;
import Network.Mapping.ConstantNetworkMapping;
import Network.Mapping.PropertyMapping;


public class ConstantView implements MappingViewInterface, ActionListener
	 {
	  JPanel	panel		;
	 
	  ViewPropertySelector property 	;
	  
	  FloatField value		;
	  
	  ModelManagerInterface modelManager ;
	  
	  NetworkMappingManagerView mappingManager ;
	  
	  ConstantNetworkMapping	mapping ;

	  JButton		removeMapping	;

	
	  
	 ConstantView()
	 	{

	 	}

	 public ConstantView( NetworkMappingManagerView mappingManager, ModelManagerInterface nodeModelManager2 )
		  {
		 	 panel 		= new JPanel() ;
		 	 
		  	 panel.setPreferredSize( new Dimension( 500, 110  ) ) ;
		  	 
		  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
		  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
		  	 
		 	 
		 	 property	= new  ViewPropertySelector(  nodeModelManager2 ) ;
			 
		 	 
		  	 property.addActionListener( this ) ;
		 	 
		 	 value		= new FloatField( 0f, 1f ) ;
		 	 		 	 
		 	 value.addActionListener( this ) ;
		 	 
		 	 panel.add( property.getPanel() ) ;
		 	 panel.add(  value.getPanel() ) ;
		 	 
		  	 removeMapping = new JButton( "X" ) ;
		  	 panel.add(  removeMapping  ) ;
		  	 
		  	 removeMapping.addActionListener( this ) ;
		  
		  
		   this.modelManager 		= 	nodeModelManager2 	;
		   this.mappingManager 		=	mappingManager 		;
		   
		 	 value.setValue( .5f ) ;

		//   resetMapping() ;
		   
		  }

	 public JPanel getPanel()
		  {
		   return panel ;
		  }


	 public void actionPerformed( ActionEvent e)
		  {
		   if( e.getSource() == property )
				{
				 if( property.getPath() != property.getPreviousPath() )
					 	value.setValue( .5f ) ;
				}
		   
		   if( e.getSource() == removeMapping )
				{
				// modelManager.removeMapping( mapping ) ;
				 
				 property.freeViewProperty() ;
				
				 mappingManager.removeMappingView( this ) ;
				 
				 return ;
				}
		  
		   resetMapping() ;		   	  
		  }
	 


	 private void resetMapping()
		  {
		   PropertyPathInterface sinkPath = property.getPath() ;
		    
		   if(  property.getPreviousPath() != null )
				modelManager.freeViewProperty( property.getPreviousPath() ) ;
		   
		   modelManager.useViewProperty( sinkPath ) ;
		  
		   if( value.peekValue() == null ) return ; 
		   

		  
		   
//		   if( mapping != null ) modelManager.removeMapping( mapping ) ;	   
		   
		   mapping = new ConstantNetworkMapping( sinkPath, new Constant( value.acceptValue()) ) ;
		   
		   modelManager.addMapping( mapping ) ;
		   
		  }

	 }
