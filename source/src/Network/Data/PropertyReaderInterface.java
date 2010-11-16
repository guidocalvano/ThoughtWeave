package Network.Data ;

import java.util.Iterator;


public interface PropertyReaderInterface
	 {
	  String propertyId() ;

	  boolean 		isGlobalProperty() 	;
	  
	  Iterator 		getIterator() ;
	  
	  void			initIterator() ;
	  
	  int 			channelCount() ;
	  
	  double 		getMeanValue() ;
	  
	 }
