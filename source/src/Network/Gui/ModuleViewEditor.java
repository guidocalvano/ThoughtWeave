package Network.Gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gui.FloatField;


public class ModuleViewEditor implements ModuleViewEditorInterface
	 {
	  JPanel panel ;
	  
	  JLabel moduleLabel ;
	  
	  List 	 viewPropertyTextField		;
	  
	  double symbol ;
	  
	  ModuleMappingView owner ;
	  
	  ModuleViewEditor( ModuleMappingView owner, double symbol, double[] viewPropertySet )
	  	{
	  	 this.owner = owner ;
	  	 
	  	 this.symbol = symbol ;
	  	
	  	 panel = new JPanel() ;
	  	 
	  	 moduleLabel = new JLabel( "" + symbol ) ;
	  	 
	  	 panel.add( moduleLabel ) ;
	  	 viewPropertyTextField = new Vector() ;
	  	 
	  	 for( int i = 0 ; i < viewPropertySet.length ; i++ )
	  		  {
	  		   addValue( viewPropertySet[ i ]) ;
	  		  }
	  	}

	 public JPanel getPanel()
		  {
		  return panel ;
		  }

	 public void addValue( double value )
		  {
 		   FloatField nextField = new FloatField( 0f , 1f  ) ;
	  		  
 		   nextField.setValue( (float) value ) ;
 		   
 		   nextField.addActionListener( owner ) ;
 		   
  		   viewPropertyTextField.add(  nextField ) ;
  		   
  		   panel.add( nextField.getPanel() ) ;		  
		  }

	 public void removeValue()
		  {
		   int i = viewPropertyTextField.size() - 1 ;
		   
		   FloatField last = ( (FloatField) viewPropertyTextField.get(  i  ) ) ;
		   
		   panel.remove(  last.getPanel() ) ;
		   
		   viewPropertyTextField.remove( last ) ;
		   
		  }

	 public double symbol()
		  {
		  return symbol ;
		  }

	 public double[] valueArray()
		  {
		   double[] valueArray = new double[ viewPropertyTextField.size() ] ;
		   
		   Iterator it = viewPropertyTextField.iterator() ;
		   
		   for( int i = 0 ; i < valueArray.length ; i++ )
				{
				
				 FloatField field = (FloatField) it.next() ;
				
				 if( field.peekValue() == null ) return null ;
				 
				 valueArray[ i ] = field.acceptValue() ;
				 
				 
				}
		  
		   return valueArray ;
		  }
	 
	 }
