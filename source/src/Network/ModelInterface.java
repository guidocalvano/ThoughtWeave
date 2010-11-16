package Network;

import java.util.Iterator;

import Network.Data.LinkDataInterface;
import Network.Data.NodeDataInterface;

public interface ModelInterface extends Updateable
	 {

	 public int id() ;
	 
	 public void init( ModelInterface nodeModel ) ;	
	 
	 
	 void addModelChangeListener( ModelChangeListener listener ) ;


	 
	 
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

	 public void load( LinkDataInterface data);

	 public void setDescriptionPath( PropertyPathInterface path);

	 public void setOverrideDescription( String text);

	 public void setModelManager( ModelManager modelManager);
	 

	 }
