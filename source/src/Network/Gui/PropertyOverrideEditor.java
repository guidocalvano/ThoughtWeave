package Network.Gui;

import Gui.ComboBoxMenu;
import Gui.FloatField;
import Network.* ;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;


public class PropertyOverrideEditor implements PropertyOverrideEditorInterface, ActionListener
	 {
	  JPanel panel ;
	  
	  ComboBoxMenu property ;
	  
	  PropertyPathInterface currentPath ;
	  
	  FloatField value	 ;
	
	  
	  JButton	removeMe 	;

	  PropertySetOverrideEditorInterface propertySetOverrideEditor ;
	  
	  Updateable model ;
	  
	  PropertySetInterface overrideProperties ;
	
	  PropertySetInterface viewProperties ;


//	 public PropertyOverrideEditor(
	//		   PropertySetOverrideEditorInterface propertySetOverrideEditor )
		  {

		   }
		   
		  

	 public PropertyOverrideEditor( 
			   PropertySetOverrideEditor propertySetOverrideEditor, Updateable model,
			   PropertySetInterface viewProperties,  PropertySetInterface overrideProperties )
		  {
		   this.propertySetOverrideEditor = propertySetOverrideEditor ;
		   
		   panel = new JPanel() ;
		   
		  	 panel.setPreferredSize( new Dimension( 500, 60  ) ) ;
		  	 
		  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
		  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
		  	 
		   
		   property = new ComboBoxMenu( new OverridableOptionTreeAdapter( viewProperties, overrideProperties) ) ;
		   
		   property.addActionListener( this ) ;
		   
		   value = new FloatField( 0f, 1f ) ;
		   
			 value.setValue( ( (ValueSourceInterface) viewProperties.atPathObject( property.getPath() ) ).getValue() ) ;

		   
		   currentPath = null ;		
		   
		   removeMe = new JButton( "X" ) ;
		   
		   removeMe.setActionCommand( "removeMe" ) ;
		   
		   removeMe.addActionListener( this ) ;
		   
		   value.addActionListener( this ) ;
		   
		   panel.add(  property.getPanel() ) ;
		   panel.add( value.getPanel() ) ;
		   panel.add( removeMe ) ;
		   
		   this.model = model ;
		   
		   this.propertySetOverrideEditor = propertySetOverrideEditor ;
		   
		   this.overrideProperties 	= overrideProperties 	; 
		   this.viewProperties			= viewProperties 			;
		   
		   select() ;
	
		   
		   /*
		   Object[] options =  overrideableProperties.toArray() ;
		   
		   System.out.println( " option class = " + options[ 0 ].getClass().getName() ) ;
		   
		   String[] strings = new String[ options.length ] ;  
		   
		   for( int i = 0 ; i < options.length ; i++ )
				strings[ i ] = (String) options[ i ] ;
		   */
		   
	
		  }



	 public PropertyOverrideEditor( 
			   PropertySetOverrideEditor propertySetOverrideEditor, Updateable model,
			   PropertySetInterface viewProperties,  PropertySetInterface overrideProperties, String propertyString )
		  {
		   this.propertySetOverrideEditor = propertySetOverrideEditor ;
		   
		   panel = new JPanel() ;
		   
		  	 panel.setPreferredSize( new Dimension( 500, 60  ) ) ;
		  	 
		  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
		  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
		  	 
		  	 
		  	 
		   property = new ComboBoxMenu( new OverridableOptionTreeAdapter( viewProperties, overrideProperties) ) ;
		   
		   PropertyPathInterface path = new PropertyPath() ;
		   path.add( propertyString ) ;
		   
		   property.select( path ) ;
		   
		   property.addActionListener( this ) ;
		   
		   value = new FloatField( 0f, 1f ) ;
		   
			 value.setValue( ( (ValueSourceInterface) overrideProperties.atPathObject( property.getPath() ) ).getValue() ) ;

		   
		   currentPath = null ;		
		   
		   removeMe = new JButton( "X" ) ;
		   
		   removeMe.setActionCommand( "removeMe" ) ;
		   
		   removeMe.addActionListener( this ) ;
		   
		   value.addActionListener( this ) ;
		   
		   panel.add(  property.getPanel() ) ;
		   panel.add( value.getPanel() ) ;
		   panel.add( removeMe ) ;
		   
		   this.model = model ;
		   
		   this.propertySetOverrideEditor = propertySetOverrideEditor ;
		   
		   this.overrideProperties 	= overrideProperties 	; 
		   this.viewProperties			= viewProperties 			;
		   
		   // select() ;
	
		   
		  }



	 public JPanel getPanel()
		  {
		  return panel ;
		  }



	 public void actionPerformed( ActionEvent e)
		  {
			 System.out.println( "handling action event"  ) ;
		  

		   System.out.println( "action = " + e.getActionCommand() ) ;
		  
		   if( e.getSource() == property )
				{
				 value.setValue( ( (ValueSourceInterface) viewProperties.atPathObject( property.getPath() ) ).getValue() ) ;
				 select() ;
				 return ;
				}
		   
		   if ("removeMe".equals(e.getActionCommand()))
				{
				System.out.println( "remove property now" ) ;
				propertySetOverrideEditor.removePropertyOverrideEditor( this ) ;
				overrideProperties.remove( property.getPath() ) ;

				 model.updateView();
				 
				 return ;
				}
		   
		   if( e.getSource() != property )
				{
				 select() ;
				}
		  }



	  void select()
		   {
		   System.out.println( "select" ) ;
			 if( value.peekValue() == null ) return ;

			   System.out.println( "not null" ) ;

			 
			 System.out.println( "property = " + property.getPath().toString() + "value " + value.peekValue().floatValue() ) ;
			 
			 if( currentPath != null )
				  overrideProperties.remove( currentPath ) ;
				  
			 overrideProperties.addObject( property.getPath(), new Constant( value.acceptValue() ) ) ;
			 currentPath = property.getPath() ;
			 model.updateView();
		   }

	 


	 }
