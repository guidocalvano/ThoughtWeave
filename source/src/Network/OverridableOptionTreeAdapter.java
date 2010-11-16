package Network ;

import Gui.*; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class OverridableOptionTreeAdapter implements OptionTreeInterface
	 {
	  String name ;
	 
	  PropertySetInterface view  		;
	  
	  PropertySetInterface override  	;
	  
	  PropertyPathInterface path ;
	  
	  public OverridableOptionTreeAdapter( PropertySetInterface view, PropertySetInterface override )
	  	{
	  	 this.view 		= view 		;

	  	 this.override 	= override 	;
	  	 
	  	 
	  	 this.path = new PropertyPath() ;
	  	 
	  	 name = "" ;
	  	}
	  
	  
	  public OverridableOptionTreeAdapter( String name )
		  	{
		  	 this.view 		= null 		;

		  	 this.override 	= null 	;
		  	 
		  	 
		  	 this.path = new PropertyPath() ;
		  	 
		  	 this.name = name ;
		  	 
		  	 path.add( name ) ;
		  	}
	 

	 public List children()
		  {
		  System.out.println( "children list " ) ;
		   List childrenList = new Vector() ;
		   List childrenStringList = new Vector() ;
		   
		   Iterator it ;
		   if( view == null ) return childrenList ;
		   
			  System.out.println( "view not null" ) ;

		   
		//   Iterator viewIt = view.getPropertyIterator() ;
		   
		//   Iterator folderIt = source.getFolderIterator() ;
		  

			  childrenStringList.addAll(  view.propertyIdSet() ) ;
		   
		   
		   it = childrenStringList.iterator() ;
		   
		   while( it.hasNext() )
				{
				 System.out.println( "view" + (String) it.next() ) ;
				
				}
		   
		   childrenStringList.removeAll( override.propertyIdSet() ) ;
		   
		   it = childrenStringList.iterator() ;
		   
		   while( it.hasNext() )
				{
				String nextString = (String) it.next() ;
				 System.out.println( "view - override " + nextString ) ;
				
				 childrenList.add( new OverridableOptionTreeAdapter( nextString ) ) ;
				}
		   
		   
		   
		  return childrenList ;
		  }

	 public String name()
		  {
		   return name ;
		  }

	  public PropertyPathInterface path() { return path ; }
	 }