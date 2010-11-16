package Network ;

import java.util.Iterator;
import java.util.Map;

import Network.Data.NodeDataInterface;


public interface NodeModelInterface extends Updateable
	 {

	 public int id() ;
	 
	 public void init( NodeModelInterface nodeModel ) ;	
	 
	 
	 void addModelChangeListener( NodeModelChangeListener listener ) ;


	 
	 
	 public double property( String propertyId) ;
	 

	 public ValueSourceInterface propertySource( String propertyId) ;
	 
	 
	 public void property( String propertyId, ValueSourceInterface setValue) ;


	 public Iterator getViewPropertyIterator() ;
	 
	 
	 StringSourceInterface getDescriptionSource() ;
	 
	 
	 String getDescription() ;


	 public void setDescriptionSource( StringSourceInterface description) ;


	 public PropertySetInterface getViewProperties();


	 public PropertySetInterface getOverrideProperties();




	 public PropertySetInterface getLibrary();


	 public void load( NodeDataInterface data);
	 

	 }
