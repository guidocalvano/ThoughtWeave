
package Network ;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;


public class BasicPropertySet implements PropertySetInterface
	 {
	  Map	propertyById 	;
	  
	  Map	folderById 		;
	  
	  public BasicPropertySet()
	  	{	 
	  	 propertyById = new HashMap() ;
	  	 
	  	 folderById = new HashMap() ;
	  	}
	  
	  
	 public double property( String propertyId) 	{ return ( (Double) propertyById.get( propertyId ) ).doubleValue() ; }

	 public Object propertyObject( String propertyId) 	{ return propertyById.get( propertyId ) ; }
	 
	 
	 public void property( String propertyId, double setValue) 
			  {
			   propertyById.put( propertyId, new Double( setValue ) ) ;
			  }
	 
	 public void propertyObject( String propertyId, Object object ) 
		  {
		   propertyById.put( propertyId, object ) ;
		  }
	 
	 public boolean containsProperty( String propertyId )
		  { return propertyById.containsKey( propertyId ) ; }
	 
	 public boolean containsFolder( String propertyId )
		  { return folderById.containsKey( propertyId ) ; }
	 
	 public PropertySetInterface folder( String folderId ) 	
		  {
		   if( !folderById.containsKey( folderId ) )
				folder( folderId, new BasicPropertySet() ) ;
		   
		   return ( (PropertySetInterface) folderById.get( folderId ) ) ; 
		  }


	 public void folder( String folderId, PropertySetInterface propertySet )
		  {
		   folderById.put(  folderId, propertySet ) ;
		  }
	 

	 public Iterator getPropertyIterator()
		  {
		  return propertyById.entrySet().iterator() ;
		  }

	 
	 public Iterator getFolderIterator()
		  {
		  return folderById.entrySet().iterator() ;
		  }

	 public String[] propertyIdArray()
		  {
		   Set keys = propertyById.keySet() ;
		  
		   String[] keyStrings = new String[ keys.size() ] ;
		   
		   Iterator it = keys.iterator() ;
		   
		   for( int i = 0 ; i < keyStrings.length ; i++ )
				keyStrings[ i ] = (String) it.next() ;
		  return keyStrings ;
		  }

	 
	 public Set propertyIdSet()
		  {		  
		   return propertyById.keySet() ;
		  }

	 public double atPath( PropertyPathInterface path)
		  {
		   return atPath( path.pathIterator() ) ;
		  }


	 public double atPath( Iterator path)
		  {
		   String next = (String) path.next() ;
		  
		   System.out.println( next ) ;
		   
		   if( path.hasNext() )
				
		  	  return folder( next ).atPath( path ) ;
		   
		   else
				return property( next ) ;
		  }
	 
	 public Object atPathObject( PropertyPathInterface path)
		  {
		   return atPathObject( path.pathIterator() ) ;
		  }


	 public Object atPathObject( Iterator path)
		  {
		   String next = (String) path.next() ;
		  
		   if( path.hasNext() )
				
		  	  return folder( next ).atPathObject( path ) ;
		   
		   else
				return propertyObject( next ) ;
		  }
	 
	  public void add( PropertySetInterface propertySet )
		   {
		    Iterator props = propertySet.getPropertyIterator() ;
		    Iterator folds = propertySet.getFolderIterator() ;
		   
		    while( props.hasNext() )
		    	 {
		    	  Map.Entry entry = (Entry) props.next() ;
		    	 
		    	  propertyById.put( entry.getKey(), entry.getValue() ) ;
		    	 }
		    
			   
		    while( folds.hasNext() )
		    	 {
		    	  Map.Entry entry = (Entry) folds.next() ;
		    	 
		    	  folderById.put( entry.getKey(), entry.getValue() ) ;
		    	 }
		   }

	 public void add( PropertyPathInterface propertyPath )
		  {
		   add( propertyPath, 0.0 ) ;
		  }

	 public void add( PropertyPathInterface propertyPath, double value )
		  {
		  Iterator path = propertyPath.pathIterator() ;
		   
		   PropertySetInterface cursor = this ;
		   
		   
		   String step = null ;
		   while( path.hasNext() )
				{
				 step = (String) path.next() ; 
				
				 if( !path.hasNext() ) break ;
				 cursor = cursor.folder( step ) ;
				 
				}
		   
		   cursor.property( step, value ) ;		  
		  }

	 public void addObject( PropertyPathInterface propertyPath, Object value )
		  {
		  Iterator path = propertyPath.pathIterator() ;
		   
		   PropertySetInterface cursor = this ;
		   
		   
		   String step = null ;
		   while( path.hasNext() )
				{
				 step = (String) path.next() ; 
				
				 if( !path.hasNext() ) break ;
				 cursor = cursor.folder( step ) ;
				 
				}
		   
		   cursor.propertyObject( step, value ) ;		  
		  }

	 
	 public void remove( PropertyPathInterface propertyPath)
		  {
		   Iterator path = propertyPath.pathIterator() ;
		   
		   PropertySetInterface cursor = this ;
		   
		   String step = null ;
		   while( path.hasNext() )
				{
				 step = (String) path.next() ; 
				
				 if( !path.hasNext() ) break ;
				 cursor = cursor.folder( step ) ;
				 
				}
		   
		   cursor.removeProperty( step ) ;
		  }
	 
	  public void removeProperty( String propertyId  )
		   {
		    propertyById.remove( propertyId ) ;
		   }


	 public boolean containsPath( PropertyPathInterface propertyPath)
		  {
		   Iterator path = propertyPath.pathIterator() ;
		   
		   PropertySetInterface cursor = this ;
		   
		   String step = null ;
		   while( path.hasNext() )
				{
				 step = (String) path.next() ; 
				
				 if( !path.hasNext() ) break ;
				 
				 if( !cursor.containsFolder( step ) ) return false ;
					  
				 cursor = cursor.folder( step ) ;
				 
				}
		   if( step == null ) return false ;
		   
		   if( !cursor.containsProperty( step ) ) return false ;
		  
		   return true;
		  }


	 public PropertyPathInterface getPathTo( String property)
		  {
		   Vector path = new Vector() ;
		  
		   if( buildPathTo( path, property ) ) 
				{
				 Iterator it = path.iterator() ;
				 PropertyPath propPath = new PropertyPath() ;
				 
				 while( it.hasNext() )
					  {
					   propPath.add( (String) it.next() ) ;
					  }
				  return propPath ;
				}
		   
		   return null ;
		  }


	 private boolean buildPathTo( Vector vector, String property)
		  {
		   
		   if( containsProperty( property ) ) 
				{
				 vector.add( property ) ;
				
				 return true ;
				}
		   
		   Iterator it = getFolderIterator() ;
		   
		   while( it.hasNext() )
				{
				 Map.Entry entry = (Map.Entry) it.next() ;
				 
				 if( ( (BasicPropertySet) entry.getValue() ).buildPathTo( vector, property ) )
					  {
					   vector.insertElementAt( entry.getKey(), 0 ) ;
					   return true ;
					  }
				
				
				}
		   
		   
		   return false ;
		  }
	  
	 }
