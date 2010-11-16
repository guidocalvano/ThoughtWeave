package Network ;

import Gui.*; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class PropertySetToOptionTreeAdapter implements OptionTreeInterface
	 {
	  String name ;
	 
	  PropertySetInterface source  ;
	  
	  PropertyPathInterface path ;
	  
	  public PropertySetToOptionTreeAdapter( PropertySetInterface source )
	  	{
	  	 this.source = source ;
	  	 	  	 
	  	 this.path = new PropertyPath() ;
	  	}
	  
	  
	  PropertySetToOptionTreeAdapter( PropertyPathInterface path, String name, PropertySetInterface source )
	  	{
	  	 this.source = source ;
	  	 
	  	 this.name = name ;
	  	 
	  	 this.path = new PropertyPath() ;
	  	 
	  	 this.path.add(  path ) ;
	  	 
	  	 this.path.add( name ) ;
	  	}
	  
	  
	  PropertySetToOptionTreeAdapter( PropertyPathInterface path, String name )
	  	{
	  	 this.source = null ;
	  
	  	 this.name = name ;
	  	 
	  	 this.path = new PropertyPath() ;
	  	 
	  	 this.path.add(  path ) ;
	  	 
	  	 this.path.add( name ) ;
	  	}

	 public List children()
		  {
		   List childrenList = new Vector() ;
		   
		   if( source == null ) return childrenList ;
		   
		   Iterator propertyIt = source.getPropertyIterator() ;
		   
		   Iterator folderIt = source.getFolderIterator() ;
		  
		   
		   
		   while( propertyIt.hasNext() )
				{
				 Map.Entry entry = (Map.Entry) propertyIt.next() ;
				 childrenList.add( new PropertySetToOptionTreeAdapter( path, (String) entry.getKey() )  ) ; 
				}
		   
		   while( folderIt.hasNext() )
				{
				 Map.Entry entry = (Map.Entry) folderIt.next() ;
				 childrenList.add( new PropertySetToOptionTreeAdapter( path, (String) entry.getKey(), (PropertySetInterface) entry.getValue() )  ) ; 
				}
		   
		   
		  return childrenList ;
		  }

	 public String name()
		  {
		   return name ;
		  }

	  public PropertyPathInterface path() { return path ; }
	 }
