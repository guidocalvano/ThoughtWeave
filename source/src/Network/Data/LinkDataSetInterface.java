package Network.Data;

import java.util.Iterator;

import Network.PropertySetInterface;

public interface LinkDataSetInterface
	 {
	  String source() ;
	  void 	 source( String source ) ;

	  Iterator iterator() ;
	  PropertySetInterface availableProperties();
	  
	  int nodeCount() ;
	 }
