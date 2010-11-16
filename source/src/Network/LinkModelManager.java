package Network;

public class LinkModelManager extends ModelManager
	 {
	 
	  LinkModelManager()
    	  {
    	   super() ;
    	  
     	   freeViewPropertySet.property( "red", 0.0 ) ;
    	   freeViewPropertySet.property( "green", 0.0 ) ;
    	   freeViewPropertySet.property( "blue", 0.0 ) ;
    	  
    	   freeViewPropertySet.property( "transparency", 0.0 ) ;
    	   freeViewPropertySet.property( "thickness", 0.0 ) ;
    	   
    	   freeViewPropertySet.property( "visible", 0.0 ) ;

    	  }
	 }
