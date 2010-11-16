package Network ;

import java.util.Iterator;
import java.util.List;


public interface PropertyPathInterface
	 {
	  Iterator pathIterator() ;
	  
	  void add( String pathNode ) ;
	  
	  void add( PropertyPathInterface path ) ;
	  
	  List asList() ;

	 void print();
	 }
