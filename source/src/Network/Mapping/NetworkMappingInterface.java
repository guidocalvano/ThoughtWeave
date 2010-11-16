package Network.Mapping;

import java.util.List;

import Network.* ;

public interface NetworkMappingInterface
	 {

	  public List sourceSet() ;
 
	  public List sinkSet() ;
	 
	 
	  void apply( ModelInterface node ) ;
	 }
