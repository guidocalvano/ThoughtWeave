package Network;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class LinkModel extends Model
	 {

		 
	
	  
	  public LinkModel( int id )
		   {
		    super( id ) ;
	
		    property( "red", new Constant( (float) valueLibrary.property( "one" ) ) ) ;
		    property( "green", new Constant( (float) valueLibrary.property( "one" ) ) ) ;
		    property( "blue", new Constant( (float) valueLibrary.property( "one" ) ) ) ;

		    property( "thickness", new Constant( (float) valueLibrary.property( "one" ) ) ) ;
		    property( "transparency", new Constant( (float) valueLibrary.property( "one" ) ) ) ; 
		    
		    property( "visible", new Constant( (float) valueLibrary.property( "zero" ) ) ) ;

		   }
	  

	 }
