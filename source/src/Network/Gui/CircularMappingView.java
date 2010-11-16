package Network.Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Gui.ComboBoxMenu;
import Gui.FloatField;
import Network.Constant;
import Network.ModelManagerInterface;
import Network.PropertyPath;
import Network.PropertyPathInterface;
import Network.PropertySetToOptionTreeAdapter;
import Network.Mapping.CircularMapping;
import Network.Mapping.ConstantNetworkMapping;

public class CircularMappingView implements MappingViewInterface, ActionListener
	 {
	 JPanel	panel		;

	  ViewPropertySelector cosineProperty 	;

	 
	  ViewPropertySelector sineProperty 	;
	  
	  
	  ModelManagerInterface modelManager ;
	  
	  NetworkMappingManagerView mappingManager ;
	  
	  CircularMapping	mapping ;

	  JButton		removeMapping	;

	
	  
	 CircularMappingView()
	 	{

	 	}

	 public CircularMappingView( NetworkMappingManagerView mappingManager, ModelManagerInterface nodeModelManager2 )
		  {
		 	 panel 		= new JPanel() ;

		  	 panel.setPreferredSize( new Dimension( 500, 110  ) ) ;
		  	 
		  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
		  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
		  	 
		 	 
		 	 cosineProperty	= new ViewPropertySelector( nodeModelManager2 ) ;
		 	 cosineProperty.addActionListener( this ) ;
		 	 
		 	 
		 	 sineProperty	= new ViewPropertySelector( nodeModelManager2 ) ;		 	 
		 	 sineProperty.addActionListener( this ) ;
		 	 
		 	 
		 	 panel.add( cosineProperty.getPanel() ) ;

		 	 panel.add( sineProperty.getPanel() ) ;

		 	 
		  	 removeMapping = new JButton( "X" ) ;
		  	 panel.add(  removeMapping  ) ;
		  	 
		  	 removeMapping.addActionListener( this ) ;
		  
		  
		   this.modelManager 		= 	nodeModelManager2 	;
		   this.mappingManager 		=	mappingManager 		;
		   
		   PropertyPathInterface xPath = new PropertyPath() ;
			  
			  xPath.add( "x" ) ;
			  
			  
			  PropertyPathInterface yPath = new PropertyPath() ;
			  
			  yPath.add( "y" ) ;
			  
			 
			  
			  if( modelManager.freeViewPropertySet().containsPath( xPath  ) )
				   
				   cosineProperty.select( xPath ) ;

			  if( modelManager.freeViewPropertySet().containsPath( yPath  ) )

				   sineProperty.select( yPath ) ;
			

		   resetMapping() ;
		   
		  }

	 public JPanel getPanel()
		  {
		   return panel ;
		  }


	 public void actionPerformed( ActionEvent e)
		  {
		   
		   
		   if( e.getSource() == removeMapping )
				{
				 sineProperty.freeViewProperty() ;
				 cosineProperty.freeViewProperty() ;
				 
				 mappingManager.removeMappingView( this ) ;
				 
				 return ;
				}
		  
		   resetMapping() ;		   	  
		  }
	 


	 private void resetMapping()
		  {
		  
		   PropertyPathInterface sinkPathA = sineProperty.getPath() ;

		   PropertyPathInterface sinkPathB = cosineProperty.getPath() ;
		   
		   /*
		   if(  sineProperty.getPreviousPath() != null )
				modelManager.freeViewProperty( sineProperty.getPreviousPath() ) ;
		   
		   if(  cosineProperty.getPreviousPath() != null )
				modelManager.freeViewProperty( cosineProperty.getPreviousPath() ) ;
		   
		   modelManager.useViewProperty( sinkPathA ) ;

		   modelManager.useViewProperty( sinkPathB ) ;
		   */

		  
		   
//		   if( mapping != null ) modelManager.removeMapping( mapping ) ;	   
		   
		   mapping = new CircularMapping( modelManager.modelCount(), sinkPathA, sinkPathB ) ;
		   
		   modelManager.addMapping( mapping ) ;
		   
		  }

	 }
