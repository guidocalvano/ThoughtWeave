package Network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import Network.Data.LinkDataInterface;
import Network.Data.LinkDataSetInterface;
import Network.Data.NodeDataInterface;
import Network.Data.NodeDataSetInterface;
import Network.Mapping.NetworkMappingInterface;

public class ModelManager implements ModelManagerInterface, ModelListener
	 {
	  List modelSet ;
		 
//	  List valueLibrary ;
	  
	  Map	viewPropertyIdToMapping ;
	  
	  PropertySetInterface overlayTextSet ;
	  
	  
	  PropertySetInterface availablePropertySet ;

	  PropertySetInterface freeViewPropertySet ;
	  
	  PropertySetInterface usedViewPropertySet ;
	 	  
	  NetworkMappingInterface undefined ;
	 
	  
	  boolean mustOverride ;
	  
	  List modelListenerSet ;
	  
	  ModelManager()
	  	{
		  modelSet = new ArrayList( 600 * 600 / 2 ) ;
		  
		  modelListenerSet = new LinkedList() ;
		  
		  mustOverride = true ;
		 
		 // valueLibrary = new Vector() ;
		  
		  viewPropertyIdToMapping = new HashMap() ;
		  
			   overlayTextSet	  	= new BasicPropertySet() ;
		  
		  availablePropertySet = new BasicPropertySet() ;
		  
		  
		  freeViewPropertySet = new BasicPropertySet() ;
		  
		  usedViewPropertySet = new BasicPropertySet() ;
		
		  
		  
		 // undefined = new ConstantNetworkMapping( 1.0 ) ;
		}
	  
	  public void addModelListener( ModelListener listener )
		   {
		    modelListenerSet.add( listener  ) ;
		   }
	  
	  public void modelChanged()
		   {
		    informModelListeners() ;
		   }
	  public void informModelListeners()
		   {
		    Iterator it = modelListenerSet.iterator() ;
		    
		    while( it.hasNext() )
		    	 {
		    	  ( (ModelListener) it.next() ).modelChanged() ;
		    	  System.out.println( "model change sent" ) ;
		    	 }
		    
		   }

 
	  
	 public void useViewProperty( PropertyPathInterface propertyPath )
		{
		 freeViewPropertySet.remove( propertyPath ) ;
		 
		 usedViewPropertySet.add(  propertyPath ) ;		
		}
	
	 public void freeViewProperty( PropertyPathInterface propertyPath )
			{
			 freeViewPropertySet.add( propertyPath ) ;
			 
			 usedViewPropertySet.remove(  propertyPath ) ;		
			}
	  
	 public PropertySetInterface freeViewPropertySet()
		  {
		   return freeViewPropertySet ;
		  }
	 
	 public void createViewProperty( String property )
		{
		 viewPropertyIdToMapping.put( property, null ) ;		  
		}
	 
	 public String[] getUnboundViewProperties()
		  {
		   Vector unboundViewPropertiesVector = new Vector() ;
		  
		   Set entrySet = viewPropertyIdToMapping.entrySet() ;
		   
		   Iterator it = entrySet.iterator() ;
		   
		   while( it.hasNext() )
				{
				 Map.Entry entry = (Entry) it.next() ;
				 
				 if( entry.getValue() == null ) unboundViewPropertiesVector.add( entry.getKey() ) ;
				}
				
		  
		   String[] unboundViewPropertiesArray = new String[ unboundViewPropertiesVector.size() ] ;
		   
		   Iterator it2 = unboundViewPropertiesVector.iterator() ;
		   
		   
		   for( int i = 0 ; it2.hasNext() ; i++ )
				{
				 unboundViewPropertiesArray[ i ] = (String) it2.next() ;
				 System.out.println( unboundViewPropertiesArray[ i ] ) ;
				}
		   
		   return unboundViewPropertiesArray ;
		   
		  }
	  
	 public void addModel( ModelInterface model )
		  {
		   modelSet.add(  model  ) ;
		   model.setModelManager( this ) ;
		   
		  }
	 
	 
	 public void removeModel( ModelInterface model )
		  {
		   modelSet.remove(  model  ) ;
		  }

	
	 
	 
	 public void mappingForProperty( NodeModelInterface model, String property)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public boolean mustOverride()
		  {
		  
		   return mustOverride ;
		  }

	 public void setProperty( String selectedItem, Constant constant)
		  {
		   System.out.println( "setting property " + selectedItem + " to " + constant.getValue() + " for " + modelSet.size() + " nodes" ) ;
		  
		   Iterator it = modelSet.iterator() ;
		   
		   while( it.hasNext() )
				{
				 NodeModelInterface model = (NodeModelInterface) it.next();
				 
				 model.property( selectedItem, constant ) ;
				 model.update() ;
				
				}
				
		  }

	 
	 
	 public PropertySetInterface availablePropertySet() { return availablePropertySet ; }

	 
	 public PropertySetInterface overlayTextSet() { return overlayTextSet ; } 
	 	
	 
	 public void load( NodeDataSetInterface loaded)
		  {
		   overlayTextSet.property( "empty", 0.0 ) ;
		   overlayTextSet.add( loaded.textPropertySet() ) ;
		   
		  
		   availablePropertySet.add( loaded.availableProperties() ) ;
		   
		   Iterator dataIterator = loaded.iterator() ;
		   Iterator modelIterator = modelSet.iterator() ;
		   
		   
		   while( modelIterator.hasNext() && dataIterator.hasNext() )
				{
				 ModelInterface model = (ModelInterface) modelIterator.next() ;
				 NodeDataInterface  data  = (NodeDataInterface)  dataIterator.next() ;
				 
				 model.load( data ) ;
				}
				
		  }

	 

	 public void load( LinkDataSetInterface loaded)
		  {
		   availablePropertySet.add( loaded.availableProperties() ) ;
		   
		   Iterator dataIterator = loaded.iterator() ;
		   Iterator modelIterator = modelSet.iterator() ;
		   
		   
		   while( modelIterator.hasNext() && dataIterator.hasNext() )
				{
				 ModelInterface model = (ModelInterface) modelIterator.next() ;
				 LinkDataInterface  data  = (LinkDataInterface)  dataIterator.next() ;
				 
				 model.load( data ) ;
				}
		  }
	 
	 
	 public void addMapping( NetworkMappingInterface mapping)
		  {
/*		   Iterator lockedSinkPathIterator = mapping.sinkSet().iterator() ;
		   
		   while( lockedSinkPathIterator.hasNext() )
				{
				 useViewProperty( (PropertyPathInterface) lockedSinkPathIterator.next() ) ;
				}
	*/	   
		   applyMapping( mapping ) ;
		   
		   informModelListeners() ;
		  }
	 
	 public void removeMapping(  NetworkMappingInterface mapping )
		  {
		   Iterator lockedSinkPathIterator = mapping.sinkSet().iterator() ;

		  
		   while( lockedSinkPathIterator.hasNext() )
				{
				 freeViewProperty( (PropertyPathInterface) lockedSinkPathIterator.next() ) ;
				}
		   
		   informModelListeners() ;
		  }
	 
	 void applyMapping( NetworkMappingInterface mapping)
		  {
    	   Iterator nodeIt = modelSet.iterator() ;
    		   
    	   while( nodeIt.hasNext() )
    			{
    			 ModelInterface node = (ModelInterface) nodeIt.next() ;
    			 
    			 mapping.apply( node ) ; 
    			
    			}

		  }

	 public void setStringToPath( PropertyPathInterface path )
		  {
	   	   Iterator nodeIt = modelSet.iterator() ;
		   
    	   while( nodeIt.hasNext() )
    			{
    			 ModelInterface node = (ModelInterface) nodeIt.next() ;
    			 
    			 node.setDescriptionPath( path ) ;
    			 node.update() ;
    			}
    	   informModelListeners() ;
		  }
	 
	 
	 public float lowestValue( PropertyPathInterface path)
		  {
		   Iterator it = modelSet.iterator() ;
		  
		   float lowestKnown = Float.MAX_VALUE ;
		   
		   while( it.hasNext() )
				{
				 ModelInterface model = (ModelInterface) it.next() ;
				 float nextValue =  (float) model.getLibrary().atPath( path ) ;
				 
				 if( nextValue < lowestKnown )
					  lowestKnown = nextValue ;
				
				}
		  
		  return lowestKnown ;
		  }

	 public float highestValue( PropertyPathInterface path)
		  {
		   Iterator it = modelSet.iterator() ;
		  
		   float highestKnown = Float.MIN_VALUE ;
		   
		   while( it.hasNext() )
				{
				 ModelInterface model = (ModelInterface) it.next() ;
				 float nextValue =  (float) model.getLibrary().atPath( path ) ;
				 
				 if(  nextValue > highestKnown )
					  highestKnown = nextValue ;
				
				}
		  
		  return highestKnown ;
		  }

	 public float averageValue( PropertyPathInterface path)
		  {
		   Iterator it = modelSet.iterator() ;
		  
		   float sum = 0f ;
		   
		   while( it.hasNext() )
				{
				 ModelInterface model = (ModelInterface) it.next() ;
				 float nextValue =  (float) model.getLibrary().atPath( path ) ;
				 
				 sum += nextValue ;
				
				}
		  
		  return sum / modelSet.size() ;
		  }
	 
	 
	 public Iterator iterator()
		  {
		   return modelSet.iterator() ;
		  }

	 public void handleModelChange( ModelInterface nodeModel)
		  {
		   informModelListeners() ;
		  }

	 public int valuesCountedInRange( PropertyPathInterface source, float min, float max)
		  {
		   Iterator it = modelSet.iterator() ;
		  
		   int valueCount = 0 ;
		   
		   while( it.hasNext() )
				{
				 ModelInterface model = (ModelInterface) it.next() ;
				 float nextValue =  (float) model.getLibrary().atPath( source ) ;
				 
				 if( nextValue >= min && nextValue <= max )
					  valueCount++ ;
				}
		  
		  return valueCount ;
		  }
	 
	 public float thresholdForHighestX( PropertyPathInterface source, int highestX )
		  {
		   if( highestX == 0 ) return Float.MAX_VALUE ;
		  
		   float[] sortingArray = new float[ modelSet.size() ] ;
		  		   
		   Iterator it = modelSet.iterator() ;
		  
		   int i = 0 ;
		   while( it.hasNext() )
				{
				 ModelInterface model = (ModelInterface) it.next() ;
				 float nextValue =  (float) model.getLibrary().atPath( source ) ;
				 
				 sortingArray[ i ] = nextValue ;
				 
				 i++ ;
				}
		   
		   Arrays.sort( sortingArray ) ;
		   
		   return sortingArray[ sortingArray.length - highestX ] ;
		  }
	 
	 
	 public float thresholdForLowestX( PropertyPathInterface source, int lowestX )
		  {
		   if( lowestX == 0 ) return -Float.MAX_VALUE ;
		  
		   float[] sortingArray = new float[ modelSet.size() ] ;
		  		   
		   Iterator it = modelSet.iterator() ;
		  
		   int i = 0 ;
		   while( it.hasNext() )
				{
				 ModelInterface model = (ModelInterface) it.next() ;
				 float nextValue =  (float) model.getLibrary().atPath( source ) ;
				 
				 sortingArray[ i ] = nextValue ;
				 
				 i++ ;
				}
		   
		   Arrays.sort( sortingArray ) ;
		   
		   return sortingArray[ lowestX - 1 ] ;
		  }

	 public int modelCount()
		  {
		  return modelSet.size() ;
		  }

	 public void reInit()
		  {
		   modelSet = new ArrayList( 600 * 600 / 2 ) ;
		  
		   modelListenerSet = new LinkedList() ;
		  
		   mustOverride = true ;
		   
		   overlayTextSet	  	= new BasicPropertySet() ;
			  
		   availablePropertySet = new BasicPropertySet() ;
		  }

	 public PropertyPathInterface getPathTo( String property )
		  {		  
		   return availablePropertySet.getPathTo( property ) ;
		  }
	 
	 }
