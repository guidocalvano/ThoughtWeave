package Network.Mapping;

import java.util.List;
import java.util.Vector;

import Network.Constant;
import Network.ModelInterface;
import Network.NodeModelInterface;
import Network.PropertyPathInterface;

public class CircularMapping implements NetworkMappingInterface 
	 {
	  
	  int nodeCount ;
	  
	  int nodeIterator ;
	  
	  PropertyPathInterface sinkA ;
	  PropertyPathInterface sinkB ;
	  
	  public CircularMapping( int nodeCount, PropertyPathInterface sinkA, PropertyPathInterface sinkB )
		   {
		    this.sinkA = sinkA ;
		    this.sinkB = sinkB ;
		   
		    this.nodeCount = nodeCount ;
		    nodeIterator = 0 ;
		   }
	  
	  double nextNodeRadian()
		   {
		    double radian = ( 2.0 * Math.PI * ( (double) nodeIterator ) ) / ( (double) nodeCount ) ;
		    nodeIterator++ ;
		    
		    return radian ;
		   }
	

	 public void apply( ModelInterface node)
		  {
		   double radian = nextNodeRadian() ;
		   
		   double cosine = Math.cos( radian ) / 2.0 + .5 ;
		   
		   double sine	 = Math.sin( radian ) / 2.0 + .5 ;
		   
		   node.getViewProperties().addObject( sinkA, new Constant( (float) cosine ) ) ;
		   
		   node.getViewProperties().addObject( sinkB, new Constant( (float) sine ) ) ;
		   
		  }

	 public List sinkSet()
		  {
		   Vector vector = new Vector() ;
		   
		   vector.add( sinkA ) ; vector.add( sinkB ) ;
		   
		   return vector ;
		  }

	 public List sourceSet()
		  {
		  // TODO Auto-generated method stub
		  return null;
		  }
	 
	 }
