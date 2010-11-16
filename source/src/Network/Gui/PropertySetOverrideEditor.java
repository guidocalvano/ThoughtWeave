package Network.Gui;

import Network.* ;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PropertySetOverrideEditor implements
		  PropertySetOverrideEditorInterface, ActionListener
	 {
	  JPanel 	panel ;
	  
	  JButton	addProperty ;
	  
	  JPanel	descriptionPanel ;
	  
	  JLabel	descriptionLabel ;
	  
	  JTextField descriptionTextField ;
	  
	  JLabel	overrideDescriptionLabel ;

	  JCheckBox	overrideDescriptionCheckBox ;

	  ModelInterface model ;
	  
	  PropertySetInterface viewProperties ;
	  
	  PropertySetInterface overrideProperties ;
	  	  
	  
	  
	  
	  List		propertyOverrideEditorSet ;
	  
	  public PropertySetOverrideEditor()
	  {
	   panel = new JPanel() ;
	   
	   
	   descriptionPanel = new JPanel() ;

	   descriptionPanel.setPreferredSize( new Dimension( 700, 70  ) ) ;
	  	 
	   descriptionPanel.setMaximumSize( descriptionPanel.getPreferredSize() ) ;
	   descriptionPanel.setMinimumSize( descriptionPanel.getPreferredSize() ) ;
	  	 
	   
	   descriptionLabel = new JLabel( "description" ) ;
	   
	   descriptionTextField = new JTextField( 7 ) ;
	   
	   overrideDescriptionLabel = new JLabel( "show description?" ) ;
	   
	   overrideDescriptionCheckBox = new JCheckBox() ;
	   
	   overrideDescriptionCheckBox.addActionListener( this  ) ;
	   
	   descriptionTextField.addActionListener( this ) ;
	   
	   
	   descriptionPanel.add(  descriptionLabel ) ;
	   descriptionPanel.add(  descriptionTextField ) ;
	   descriptionPanel.add(  overrideDescriptionLabel ) ;
	   descriptionPanel.add(  overrideDescriptionCheckBox ) ;
	   
	   

	   
	   panel.add( descriptionPanel ) ;
	   
	   
	   addProperty = new JButton( "addProperty" ) ;
	   
	   panel.add( addProperty ) ;
	   
	   addProperty.setActionCommand( "addProperty" ) ;
	   
	   addProperty.addActionListener( this ) ;
	   
	   propertyOverrideEditorSet = new Vector() ;
	   
		 panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) ) ;
		 
		 panel.setSize( 1000, 800 ) ;
		 panel.setPreferredSize( new Dimension( 1000, 800 ) )  ;

	   
	   panel.validate() ;
	   panel.repaint() ;
	  }
	  
	  
	  public PropertySetOverrideEditor( ModelInterface model, PropertySetInterface viewProperties, PropertySetInterface overrideProperties )
	  	{
	  	 this() ;
	  	 
	  	 this.model = model ;
	  	 
	  	 this.viewProperties = viewProperties ;
		  
		 this.overrideProperties = overrideProperties ;
		  	  
	  	}
	  
	  
	  public void init()
		   {
		    Iterator it = propertyOverrideEditorSet.iterator() ;
		    
		    while( it.hasNext() )
		    	 {
		    	  panel.remove( ( (PropertyOverrideEditor) it.next() ).getPanel() ) ;	 
		    	 }
		    
		    Iterator overrideIt = overrideProperties.getPropertyIterator() ;
		    
		    while( overrideIt.hasNext() )
		    	 {
		    	  String property = ( (String) ( (Map.Entry) overrideIt.next() ).getKey() );
		    	  
		  	      PropertyOverrideEditorInterface addMe = new PropertyOverrideEditor(  this, model, viewProperties, overrideProperties, property ) ;

			    
//			    String firstOverrideableProperty = (String) overrideablePropertyList.get( 0 ) ;
			    
			    
			    propertyOverrideEditorSet.add( addMe ) ;
			    
			    panel.add(  addMe.getPanel() ) ;
		    	  
		    	 }
		    panel.validate() ;
		    panel.repaint() ; 
		   }
	 

	  
	  public JPanel getPanel() { return panel ; }
	  
	  
	  void addPropertyOverrideEditor()
		   {
		    if( overrideProperties.propertyIdSet().size() == viewProperties.propertyIdSet().size() ) { System.out.println( "no override properties left" ) ; return ; }
		   
		    PropertyOverrideEditorInterface addMe = new PropertyOverrideEditor(  this, model, viewProperties, overrideProperties ) ;

		    
//		    String firstOverrideableProperty = (String) overrideablePropertyList.get( 0 ) ;
		    
		    
		    propertyOverrideEditorSet.add( addMe ) ;
		    
		    panel.add(  addMe.getPanel() ) ;
		    

		    
		    panel.validate() ;
		    panel.repaint() ;
		   }
	  
	  public void removePropertyOverrideEditor( PropertyOverrideEditorInterface removeMe )
		   	{
	    	  addProperty.setEnabled( true ) ;

		    propertyOverrideEditorSet.remove( removeMe ) ;
		    
		    panel.remove(  removeMe.getPanel() ) ;
		    
		    panel.validate() ;
		    panel.repaint() ;
		   	}



		 public void actionPerformed( ActionEvent e)
			  {
			   if( e.getSource() == overrideDescriptionCheckBox || e.getSource() == descriptionTextField )
					doOverrideDescription() ;
			  
			  
			   System.out.println( "action = " + e.getActionCommand() ) ;
			  
			   if ("addProperty".equals(e.getActionCommand()))
					{
					System.out.println( "adding property now" ) ;
					addPropertyOverrideEditor() ;
					}
			  }


	 private void doOverrideDescription()
		  {
		   System.out.println( "overriding description " + descriptionTextField.getText() + " with check value " + overrideDescriptionCheckBox.isSelected()  ) ;
		  
		   if( overrideDescriptionCheckBox.isSelected() )
				model.setOverrideDescription( descriptionTextField.getText() ) ;
		   
		   else
				model.setOverrideDescription( null ) ;
		   
		   model.updateView() ;

		  }

	 }
