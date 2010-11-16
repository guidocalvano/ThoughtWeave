package Network.Data;
import Network.* ;


import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class BrainWaveNodeDataSet implements NodeDataSetInterface 
	 {
	  List nodeDataSet ;
	  
	  PropertySetInterface propertySet ;

	  public BrainWaveNodeDataSet( File file )
	  	{
	  	 nodeDataSet = new Vector() ;
	  	 propertySet = new BasicPropertySet() ;
	  	 
	  	 load( file ) ;
	  	}
	 
	  void load( File file ) 
		   {
		   
		    PropertyFileReader propertyFile = new PropertyFileReader( file ) ;
		    
		    for( int i = 0 ; i < propertyFile.channelCount() ; i++ )
		    	 
		    	 addBrainWaveNodeData( new NodeData( i ) ) ;
		    
		    
		    Iterator propertyIt = propertyFile.getPropertyIterator() ;
		    
		    PropertyReaderInterface propertyInterface ;
		    
		    while( propertyIt.hasNext() )
		    	 {
		    	  propertyInterface = (PropertyReaderInterface) propertyIt.next() ;
		    	  
		    	  Iterator nodeCursor = nodeDataSet.iterator() ;
		    	  
		    	  propertyInterface.initIterator() ;
		    	  
		    	  Iterator propertyPerChannel = propertyInterface.getIterator() ;
		    	  
		    	  propertySet.folder( file.getName() ).property(  propertyInterface.propertyId(), propertyInterface.getMeanValue() ) ;
		    	  
		    	  
		    	  while( nodeCursor.hasNext() )
		    		   {
		    		    NodeDataInterface nodeDataInterface = ( (NodeDataInterface) nodeCursor.next() ) ;

		    		    String propertyId = propertyInterface.propertyId() ;

		    		    if( propertyInterface.channelCount() > 0 )
		    		    	 {
		    		    	  double nextValue = ( (Double) propertyPerChannel.next() ).doubleValue() ;
		    		    
		    		    	  System.out.println( "property " + propertyId + " value " + nextValue ) ;
		    		    
		    		    
		    		    	  nodeDataInterface.propertySet().folder( file.getName() ).property( propertyId, nextValue ) ;
		    		    	 }
		    		   }
		    	 }
		    System.out.println( "done" ) ;
		   }
	  
	  

		 public void addBrainWaveNodeData( NodeDataInterface node )
			  {
			   nodeDataSet.add( node.id(), node ) ;
			  }

	 public Iterator iterator()
		  {
		  return nodeDataSet.iterator() ;
		  }

	 public PropertySetInterface availableProperties()
		  {
		  return propertySet ;
		  }

	 public PropertySetInterface textPropertySet()
		  {
		  // TODO Auto-generated method stub
		  return new BasicPropertySet() ;
		  }
		 
	  public int nodeCount() { return nodeDataSet.size() ; }
	 }
