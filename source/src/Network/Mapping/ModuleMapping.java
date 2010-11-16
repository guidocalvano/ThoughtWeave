

package Network.Mapping;

import java.util.List;
import java.util.Map;

import java.util.Iterator;

import Network.Constant;
import Network.ModelInterface;
import Network.NodeModelInterface;
import Network.PropertyPathInterface;
import Network.PropertySetInterface;

public class ModuleMapping implements NetworkMappingInterface
	 {
	 
	  PropertyPathInterface source ;
	 
	  List 					viewPathList ;
	 
	  Map					moduleToViewPropertyList ;
	  
	  
	  public ModuleMapping( PropertyPathInterface source, List viewPathList, Map moduleToViewPropertyList )
	  	{
	  	 this.viewPathList 	= viewPathList 	;	  	 
	  	 this.source		= source		;
	  	 
	  	 this.moduleToViewPropertyList = moduleToViewPropertyList ;
	  	}
	  
	 
	  public void apply( ModelInterface node)
		  {
		   PropertySetInterface library = node.getLibrary() 		;
		   PropertySetInterface view	= node.getViewProperties() 	;
		   
		   double[] sinkValues = (double[]) moduleToViewPropertyList.get( library.atPath( source ) ) ;
		   
		   Iterator it = viewPathList.iterator() ;
		   
		   for( int i = 0 ; i < sinkValues.length ; i++)
				{
				 PropertyPathInterface path = (PropertyPathInterface) it.next() 		  ;
				 view.addObject( 	path, new Constant( (float) sinkValues[ i ] ) 		) ;
				}
		   
		   node.update() ;
		  }

	  public List sinkSet()
		  {
		  return viewPathList ;
		  }

	  public List sourceSet()
		  {
		  return null;
		  }

	 }
