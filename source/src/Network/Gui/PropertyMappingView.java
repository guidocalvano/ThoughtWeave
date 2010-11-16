package Network.Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gui.ComboBoxMenu;
import Gui.FloatField;
import Network.ModelManagerInterface;
import Network.NodeModelManagerInterface;
import Network.PropertyPath;
import Network.PropertyPathInterface;
import Network.PropertySetToOptionTreeAdapter;
import Network.Mapping.PropertyMapping;


public class PropertyMappingView implements MappingViewInterface, ActionListener
	 {
	  JPanel panel ;
	  
	  ComboBoxMenu sourceSelector ;
	  
	  ViewPropertySelector sinkSelector ;
	  
	  FloatField	min 		;
	  FloatField 	max			;
	  
	  JButton		removeMapping	;
	  
	  ModelManagerInterface manager ;
	  
	  PropertyMapping mapping ;
	  
	  NetworkMappingManagerView mappingManager ;
	  
	  PropertyMappingView( NetworkMappingManagerView mappingManager, ModelManagerInterface modelManager )
	  	{
	  	 this.manager = modelManager ;
	  	
	  	 this.mappingManager = mappingManager ;
	  	 
	  	 panel = new JPanel() ;
	  	 
	  	 panel.setPreferredSize( new Dimension( 600, 110  ) ) ;
	  	 
	  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
	  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
	  	 
	  	 panel.add( new JLabel( "visual property" ) ) ;

	  	 
	  	 sinkSelector = new ViewPropertySelector( modelManager ) ;

	//  	 modelManager.useViewProperty( sinkSelector.getPath() ) ;
	  	 
	  	 sinkSelector.addActionListener( this ) ;
	  	 
	  	 panel.add( sinkSelector.getPanel() ) ;
	  	 
	  	 panel.add( new JLabel( "data source" ) ) ;


	  	 
	  	 sourceSelector = new ComboBoxMenu( new PropertySetToOptionTreeAdapter( modelManager.availablePropertySet() ) ) ;
	  	 
	  	 sourceSelector.addActionListener( this ) ;
	  	 
	  	 
	  	 panel.add( sourceSelector.getPanel() ) ;
	  	 
	  	 panel.add( new JLabel( "min" ) ) ;
	  	 
	  	 min = new FloatField( Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY) ;
	  	 
	  	 min.addActionListener( this ) ;
	  	 
	  	 panel.add( min.getPanel() ) ;

	  	 panel.add( new JLabel( "max" ) ) ;

	  	 max = new FloatField( Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY) ;

	  	 max.addActionListener( this ) ;

	  	 panel.add( max.getPanel() ) ;

	  	 
	  	 removeMapping = new JButton( "X" ) ;
	  	 panel.add(  removeMapping  ) ;
	  	 
	  	 removeMapping.addActionListener( this ) ;
	  	 
	  	 mapping = null ;
	  	 
		 initSourceExtremes() ;
		// resetMapping() ;
	  	}
	  
	  
	  public JPanel getPanel()
		  {
		  return panel ;
		  }


	 public void actionPerformed( ActionEvent e)
		  {
		   if( e.getSource() == sourceSelector )
				{
				 initSourceExtremes() ;
				}
		   
		   
		   if( e.getSource() == removeMapping )
				{
				 // manager.removeMapping( mapping ) ;
				 
				 sinkSelector.freeViewProperty() ;
				
				 mappingManager.removeMappingView( this ) ;
				 
				 return ;
				}
		   
		   resetMapping() ;
		  }


	 public void initSourceExtremes()
		  {
		   min.setValue( manager.lowestValue(  sourceSelector.getPath() ) ) ;
		   max.setValue( manager.highestValue( sourceSelector.getPath() ) ) ;		  
		  }

	 public void resetMapping()
		  {
		   PropertyPathInterface sourcePath = sourceSelector.getPath() ;
		   
		   PropertyPathInterface sinkPath = sinkSelector.getPath() ;
		    /*
		   if(  sinkSelector.getPreviousPath() != null )
				manager.freeViewProperty( sinkSelector.getPreviousPath() ) ;
		   
		   manager.useViewProperty( sinkPath ) ;
		   */
		   if( mapping != null ) manager.removeMapping( mapping ) ;
		   
		   double minVal = 0.0 ; 
		   double maxVal = 1.0 ;
		   if( min.peekValue() != null && max.peekValue() != null )
				{
				 minVal = min.acceptValue() ;
				 maxVal = max.acceptValue() ;
				 
				 if( minVal == maxVal ) maxVal = minVal + 1.0f ;
				}
		   mapping = new PropertyMapping( sourcePath, sinkPath, minVal, maxVal ) ;
		   
		   manager.addMapping( mapping ) ;
		   
		  }


	 public void setSourcePath( PropertyPathInterface path)
		  {
		   path.print() ;
		  
		   sourceSelector.select( path ) ;		   
		  }


	 public void setSinkPath( PropertyPathInterface path)
		  {
		   sinkSelector.select( path ) ;
		  }

	 }
