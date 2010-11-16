package Gui;

import java.util.Iterator;

import Network.PropertyPathInterface;


public interface PropertySetInterface
	 {
	  double 	property( String propertyId ) ;
	  
	  void 		property( String propertyId, double setValue ) ;
	  
	  PropertySetInterface folder( String folderId ) ;
	  
	  void folder( String folderId, PropertySetInterface propertySet ) ;

	  
	  Iterator  getPropertyIterator() ;
	  Iterator getFolderIterator() ;

	  
	  String[] propertyIdSet();
	 
	  double atPath( PropertyPathInterface path ) ;
	  
	  double atPath( Iterator path ) ;
	 }
