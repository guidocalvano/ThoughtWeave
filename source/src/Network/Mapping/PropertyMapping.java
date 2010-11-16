package Network.Mapping;

import java.util.List;
import java.util.Vector;

import Network.Constant;
import Network.ModelInterface;
import Network.NodeModelInterface;
import Network.PropertyPathInterface;

public class PropertyMapping implements NetworkMappingInterface
	 {

	  PropertyPathInterface source 	;
	  PropertyPathInterface sink	;
	  
	  double offset ;
	  double range ;
	 
	  public PropertyMapping( PropertyPathInterface source, PropertyPathInterface sink, double min, double max )
	  	{
	     this.source = source 	;
	     this.sink   = sink 	;
	     
	     this.offset = min ;
	     this.range = max - min ;
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

		   
		   node.getViewProperties().addObject( sink, new Constant( (float) (  ( node.getLibrary().atPath( source ) - offset ) / range  ) ) ) ;
		   node.update() ;
		  }

	
	 }
