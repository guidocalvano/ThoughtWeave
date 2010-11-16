package Network ;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class PropertyPath implements PropertyPathInterface
	 {
	  List path ;
	  
	  public PropertyPath() { path = new Vector() ; }

	 public void add( String pathNode)
		  {
		   path.add( pathNode ) ;
		  }

	 public Iterator pathIterator()
		  {
		  return path.iterator() ;
		  }

	 public void add( PropertyPathInterface otherPath )
		  {
		   this.path.addAll( otherPath.asList() ) ;
		  }

	 public List asList()
		  {
		  // TODO Auto-generated method stub
		  return path ;
		  }

	 public void print()
		  {
		   Iterator it = path.iterator() ;
		   
		   System.out.print( "path: " ) ;
		   
		   while( it.hasNext() )
				System.out.println( (String) it.next() ) ;
		  }

	 }
