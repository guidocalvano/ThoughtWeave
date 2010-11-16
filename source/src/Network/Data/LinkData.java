package Network.Data;

import Network.* ;

import Network.PropertySetInterface;

public class LinkData implements LinkDataInterface
	 {	 
	  int[] idPair ;
	  
	  PropertySetInterface propertySet ;	  
	 
	  LinkData( int[] idPair ) 
	  	{
	  	 propertySet = new BasicPropertySet() ;
	  	 nodeIdPair( idPair ) ;
	  	}


	 public int[] nodeIdPair()
		  {
		  return idPair ;
		  }

	 public void nodeIdPair( int[] idPair)
		  {
		   this.idPair = idPair ;
		  }


	 public PropertySetInterface propertySet()
		  {
		  // TODO Auto-generated method stub
		  return propertySet ;
		  }



	
	 }
