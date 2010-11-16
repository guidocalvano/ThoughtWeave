package Network;

import java.util.Iterator;

import Gui.OptionTreeInterface;
import Network.Data.LinkDataSetInterface;
import Network.Data.NodeDataSetInterface;
import Network.Mapping.NetworkMappingInterface;

public interface ModelManagerInterface
	 {
	  boolean mustOverride() ;
	  
	  void mappingForProperty( NodeModelInterface model, String property ) ;

	  void addModel( ModelInterface modelInterface);

	  void reInit() ;

	  
	  String[] getUnboundViewProperties();

	  void createViewProperty( String string);

	  void setProperty( String selectedItem, Constant constant);

	  public PropertySetInterface freeViewPropertySet() ;
	 
	  public void useViewProperty( PropertyPathInterface propertyPath ) ;

	  public void freeViewProperty( PropertyPathInterface propertyPath ) ;



	  void load( NodeDataSetInterface loaded);
	  
	  void load( LinkDataSetInterface loaded ) ;


	  PropertySetInterface availablePropertySet();

	  void addMapping( NetworkMappingInterface mapping);

	  void removeMapping( NetworkMappingInterface mapping);

	  float lowestValue( PropertyPathInterface path ) ;

	  float highestValue( PropertyPathInterface path);
	  
	  public float averageValue( PropertyPathInterface path) ;


	  Iterator iterator();

	  PropertySetInterface overlayTextSet();

	  void setStringToPath( PropertyPathInterface path);

	  void addModelListener( ModelListener listener ) ;
	 
	  public void informModelListeners() ;

	  int valuesCountedInRange( PropertyPathInterface source, float min, float max ) ;

	  float thresholdForHighestX( PropertyPathInterface source, int highestX 	) ;
	  float thresholdForLowestX( PropertyPathInterface source, int lowestX 		) ;

	  int modelCount() ;

	 PropertyPathInterface getPathTo( String string ) ;


	  
	 }
