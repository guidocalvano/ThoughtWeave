package Network.Mapping;

import java.util.List;
import java.util.Vector;

import Network.Constant;
import Network.ModelInterface;
import Network.PropertyPathInterface;

public class InRangeMapping implements NetworkMappingInterface
	 {
	  PropertyPathInterface source 	;
	  PropertyPathInterface sink	;
	  
	  double min ;
	  double max ;
	 
	  public InRangeMapping( PropertyPathInterface sink, PropertyPathInterface source, double min, double max )
	  	{
	     this.source = source 	;
	     this.sink   = sink 	;
	     
	     this.min = min ;
	     this.max = max ;
	  	}
	  
	  public List sourceSet() 
		   {
		    List set = new Vector() ;
		    
		    set.add( source ) ;
		    
		    return set ;
		   }
	  
	  public List sinkSet() 
		   {
		    List set = new Vector() ;
		    
		    set.add( sink ) ;
		    
		    return set ;
		   }
	  
	  public void apply( ModelInterface node)
		  {
		   if( node == null ) System.out.println( "node nll" ) ;
		   if( source == null ) System.out.println( "source nll" ) ;
		   if( sink == null ) System.out.println( "sink nll" ) ;
		   if( node.getLibrary() == null ) System.out.println( "node lib null" ) ;

		   float value = (float) node.getLibrary().atPath( source ) ;
		   float valueAsBoolean = 0.0f ;
		   
		   if( value >= min && value <= max ) 
				valueAsBoolean = 1.0f ;
		   
		   node.getViewProperties().addObject( sink, new Constant( (float) ( valueAsBoolean ) ) ) ;
		   
		  // node.update() ;
		  }
	 }