package Network ;


import java.util.Iterator;
import java.util.Set;


public interface PropertySetInterface
	 {
	  double 	property( String propertyId ) ;
	  
	  void 		property( String propertyId, double setValue ) ;
	  
	  PropertySetInterface folder( String folderId ) ;
	  
	  void folder( String folderId, PropertySetInterface propertySet ) ;

	  
	  Iterator  getPropertyIterator() 	;
	  Iterator 	getFolderIterator() 	;

	  
	  Set propertyIdSet();
	 
	  double atPath( PropertyPathInterface path ) ;
	  
	  double atPath( Iterator path ) ;
	  
	  public void add( PropertySetInterface propertySet ) ;

	 void remove( PropertyPathInterface propertyPath);

	 void add( PropertyPathInterface propertyPath);

	 void removeProperty( String property ) ;

	 Object atPathObject( Iterator path);

	 void propertyObject( String string, Object object );

	 boolean containsProperty( String propertyId);
	 
	 boolean containsFolder( String propertyId ) ;
	 
	 boolean containsPath( PropertyPathInterface propertyPath);


	 Object propertyObject( String propertyId);

	 void addObject( PropertyPathInterface path, Object object );

	 Object atPathObject( PropertyPathInterface path);

	 PropertyPathInterface getPathTo( String property);


	 }
