package Network ;

import Network.Data.NodeDataInterface;
import Network.Data.NodeDataSetInterface;
import Network.Mapping.*; 

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;


public class NodeModelManager extends ModelManager
	 {
	  
	  NodeModelManager()
    	  {
    	   super() ;
    	   
    	   freeViewPropertySet.property( "x", 0.0 ) ;
    	   freeViewPropertySet.property( "y", 0.0 ) ;
    	   freeViewPropertySet.property( "z", 0.0 ) ;
    	  
    	   freeViewPropertySet.property( "red", 0.0 ) ;
    	   freeViewPropertySet.property( "green", 0.0 ) ;
    	   freeViewPropertySet.property( "blue", 0.0 ) ;
    	  
    	   freeViewPropertySet.property( "shape", 0.0 ) ;
    	   freeViewPropertySet.property( "size", 0.0 ) ;
    	   freeViewPropertySet.property( "transparency", 0.0 ) ;
    	   
    	   freeViewPropertySet.property( "visible", 0.0 ) ;
    	  }
	 }