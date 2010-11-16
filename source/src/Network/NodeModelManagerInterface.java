package Network ;


import java.util.Iterator;

import javax.swing.ComboBoxModel;

import Network.Data.NodeDataSetInterface;
import Network.Mapping.NetworkMappingInterface;
import Network.Mapping.PropertyMapping;


public interface NodeModelManagerInterface
	 {
	  boolean mustOverride() ;
	  
	  void mappingForProperty( NodeModelInterface model, String property ) ;

	 void addNodeModel( NodeModelInterface model);

	 String[] getUnboundViewProperties();

	 void createViewProperty( String string);

	 void setProperty( String selectedItem, Constant constant);

	 public PropertySetInterface freeViewPropertySet() ;
	 
	 public void useViewProperty( PropertyPathInterface propertyPath ) ;

	 public void freeViewProperty( PropertyPathInterface propertyPath ) ;



	 void load( NodeDataSetInterface loaded);

	 PropertySetInterface availablePropertySet();

	 void addMapping( NetworkMappingInterface mapping);

	 void removeMapping( NetworkMappingInterface mapping);

	 float lowestValue( PropertyPathInterface path ) ;

	 float highestValue( PropertyPathInterface path);

	 Iterator iterator();
	 
	 
	 }
