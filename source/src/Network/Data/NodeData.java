package Network.Data;

import Network.* ;

import java.util.List;
import java.util.Vector;

public class NodeData implements NodeDataInterface
	 {		 
	  int	 id ;
	    
	  	  
	  PropertySetInterface propertySet ;

	  PropertySetInterface textSet ;

	  
	  public PropertySetInterface propertySet() { return propertySet ; }
	  public PropertySetInterface textPropertySet() { return textSet ; }
 
	  
	  NodeData( int id )
	  	{
	  	
	  	 id( id ) ;
	  	 
	  	 propertySet = new BasicPropertySet() ;
	  	 textSet	 = new BasicPropertySet() ;
	  	}
	 


	 public int  id()								{ return id 		; }
	 public void id( int id)						{   this.id = id 	; }

	 }
