package Network.Mapping;

import java.util.List;
import java.util.Vector;

import Network.Constant;
import Network.ModelInterface;
import Network.NodeModelInterface;
import Network.PropertyPathInterface;


public class ConstantNetworkMapping implements NetworkMappingInterface
	 {
	  Constant constant ;

	  PropertyPathInterface sink	;
	  
	  
	 public ConstantNetworkMapping( PropertyPathInterface sink,
			   Constant constant )
		  {
		   this.sink = sink ;
		   this.constant = constant ;
		  }

	 public void apply( ModelInterface node)
		  {
		   node.getViewProperties().addObject( sink, constant ) ;
		   node.update() ;
		  }

	  public List sourceSet() 
		   {
		    List set = new Vector() ;
		    		    
		    return set ;
		   }
	  
	  public List sinkSet() 
		   {
		    List set = new Vector() ;
		    
		    set.add( sink ) ;
		    
		    return set ;
		   }
	 }
