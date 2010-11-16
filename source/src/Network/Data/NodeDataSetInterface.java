package Network.Data;
import Network.* ;

import java.util.Iterator;

public interface NodeDataSetInterface
	 {
	  PropertySetInterface availableProperties() ;
	 
	  Iterator iterator() ;

	 PropertySetInterface textPropertySet();
	 
	  public int nodeCount() ;

	 }
