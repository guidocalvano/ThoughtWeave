package Network.Data;
import Network.* ;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class LinkDataSetFile implements LinkDataSetInterface 
	 {
	  List linkDataSet ;
	  
	  PropertySetInterface propertySet ;
	  
	  int nodeCount ;

	 public LinkDataSetFile( File file )
	  	{
	  	 linkDataSet = new Vector() ;
	  	 
	  	 propertySet = new BasicPropertySet() ;
	  	 
	  	 load( file ) ;
	  	}
	 
	 
	 
	  void load( File file )
		   {
		    propertySet.property( file.getName(), 0.0 ) ;
		    RelationFileReader relationReader = new RelationFileReader( file ) ;
		    
		    LinkDataInterface relation ;
		    
		    nodeCount = relationReader.nodeCount() ;
		    
		    for( int i = 0 ; i < nodeCount - 1 ; i++ )
		    for( int j = i + 1 ; j < nodeCount ; j ++)
		    	 {
		    	  relation = new LinkData( new int[] { i, j } ) ;
		    	  relation.propertySet().property( file.getName(), relationReader.getRelation( i, j ) ) ;
		    	  
		    	  linkDataSet.add( relation ) ;
		    	 }
		   
		   }
	  
	 public Iterator iterator()
		  {
		  // TODO Auto-generated method stub
		  return linkDataSet.iterator() ;
		  }

	 public void testPrint()
		  {
		   
		  
		  }
	 
	 public String source()
		  {
		  // TODO Auto-generated method stub
		  return null;
		  }

	 public void source( String source)
		  {
		  // TODO Auto-generated method stub
		  
		  }



	 public PropertySetInterface availableProperties()
		  {
		   return propertySet ;
		  }
	  public int nodeCount() { return nodeCount ; }

	 }
