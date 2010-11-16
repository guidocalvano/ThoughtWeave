package Network.Data;

import Network.* ;

public interface NodeDataInterface
	 {
	  int	 	id() 			;
	  void	 	id( int id ) 	;
	 
	  PropertySetInterface propertySet() ;
	  PropertySetInterface textPropertySet();

	 }
