package Network;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import Network.Data.LinkDataInterface;
import Network.Data.NodeDataInterface;

public class Model  implements ModelInterface
	 {
	  int id ;
	  
	  ModelManagerInterface modelManager ;
	  
	  Map	viewPropertyById ;
	  Map	viewOverridePropertyById ;
	  
	  PropertySetInterface view ;
	  PropertySetInterface override ;

	  
	  PropertySetInterface valueLibrary ;
	  
	  PropertySetInterface overlayTextLibrary  ; 
	  
	  StringSourceInterface descriptionProperty ;
	  StringSourceInterface overrideDescriptionProperty ;
	  
	  
/*	 
	  ValueSourceInterface xProperty ;
	  ValueSourceInterface yProperty ;
	  ValueSourceInterface zProperty ;
	  
	  ValueSourceInterface rProperty ;
	  ValueSourceInterface gProperty ;
	  ValueSourceInterface bProperty ;
	  
	  ValueSourceInterface morphProperty ;
	  ValueSourceInterface radiusProperty ;
*/
	  List modelChangeListenerSet ;

	  List modelListenerSet ;
	  
	  
	  public Model( int id )
	  	{
	  	 this.id = id ;
	  	 modelChangeListenerSet = new Vector() ;
	  	 
	  	 modelListenerSet = new Vector() ;
	  	 
		 valueLibrary = new BasicPropertySet() ;
		 overlayTextLibrary = new BasicPropertySet() ;

		 overrideDescriptionProperty = null ;

		 valueLibrary.property( "zero", 0.0 ) ;
		 valueLibrary.property( "half", 0.5 ) ;

		 valueLibrary.property( "one", 1.0 ) ;
		 
		 overlayTextLibrary.propertyObject( "empty", "" ) ;
	  	 
		 viewPropertyById = new HashMap() ;
		 viewOverridePropertyById  = new HashMap() ;
		 
		    view = new BasicPropertySet() ;
		    override = new BasicPropertySet() ;
	
		    modelManager = null ;
	  


	  	}

	  
	  
	  public int id() { return id ; } 
	  
	  
	  public void init( ModelInterface nodeModel )
		   {
		    Iterator it = nodeModel.getViewPropertyIterator() ;
		    
		    while( it.hasNext() ) 
		    	 {
		    	  String propertyString = ( (String) ( (Map.Entry) it.next() ).getKey() ) ;
		    	  
		    	  property( propertyString, nodeModel.propertySource( propertyString ) ) ;
		    	 }
		    


		    setDescriptionSource( new ConstantString( nodeModel.getDescription() ) ) ;


		    
		    modelChangeToListeners() ;

		   }
	  
	  
/*	  
		 public float property( String propertyId) 	
			  { 
			   if( viewOverridePropertyById.containsKey( propertyId ) )
					return (float) ( (ValueSourceInterface) viewOverridePropertyById.get( propertyId ) ).getValue() ;
					
			   return (float) ( (ValueSourceInterface) viewPropertyById.get( propertyId ) ).getValue() ; 
			  }

		 public ValueSourceInterface propertySource( String propertyId) 	{ return ( (ValueSourceInterface) viewPropertyById.get( propertyId ) ) ; }
	
		 
		 public float override( String propertyId) 	{ return (float) ( (ValueSourceInterface) viewOverridePropertyById.get( propertyId ) ).getValue() ; }

		 public void override( String propertyId, ValueSourceInterface setValue) 
			  {
			   viewOverridePropertyById.put( propertyId,  setValue  ) ;
			  }
		 		 
		 public void removeOverride( String propertyId ) 
			  {
			   viewOverridePropertyById.remove( propertyId ) ;
			  }
		 
		 
		 public Map getViewProperties() { return viewPropertyById ; }
		 
		 public Map getOverrideProperties() { return viewOverridePropertyById ; }
		 
		 
		 
		 public void property( String propertyId, ValueSourceInterface setValue) 
				  {
				  viewPropertyById.put( propertyId,  setValue  ) ;
				  }


		 public Iterator getViewPropertyIterator()
			  {
			  return viewPropertyById.entrySet().iterator() ;
			  }
		   */
	  
	  
		 public double property( String propertyId) 	
			  { 
			   if( override.containsProperty( propertyId ) )
					return (double) ( (ValueSourceInterface) override.propertyObject( propertyId ) ).getValue() ;
				
			   
			   if( view == null )
					System.out.println( "view = null" ) ;
			   
			   return (double) ( (ValueSourceInterface) view.propertyObject( propertyId )  ).getValue() ; 
			  }

		 public ValueSourceInterface propertySource( String propertyId) 	{ return ( (ValueSourceInterface) view.propertyObject( propertyId ) ) ; }
	
		 
		 public double override( String propertyId) 	{ return (double) ( (ValueSourceInterface) override.propertyObject( propertyId ) ).getValue() ; }

		 public void override( String propertyId, ValueSourceInterface setValue) 
			  {
			   override.propertyObject( propertyId,  setValue  ) ;
			  }
		 		 
		 public void removeOverride( String propertyId ) 
			  {
			   override.removeProperty( propertyId ) ;
			  }
		 
		 
		 public PropertySetInterface getViewProperties() { return view ; }
		 
		 public PropertySetInterface getOverrideProperties() { return override ; }
		 
		 
		 
		 public void property( String propertyId, ValueSourceInterface setValue) 
				  {
				  view.propertyObject( propertyId,  setValue  ) ;
				  }


		 public Iterator getViewPropertyIterator()
			  {
			  return view.getPropertyIterator() ;// viewPropertyById.entrySet().iterator() ;
			  }
	  
	  public String getDescription() 
		   {
		   System.out.println( "Model :: getDescription()") ;
		    System.out.println( "modelManager ") ; if( modelManager == null ) System.out.println( "false" ) ; else System.out.println( "true " ) ;
		    
		    if( modelManager != null && modelManager.mustOverride() ) 
		    			   {
		    			   System.out.println( "override is turned on" ) ;
		    			   }
		    else
		    	 System.out.println( "override is turned off" ) ;
		    
		    
		    if( modelManager != null && modelManager.mustOverride() && overrideDescriptionProperty != null )
		    	 {
		    	  System.out.println( "override string = " + overrideDescriptionProperty.getString() ) ;
		    	 
		    	  return overrideDescriptionProperty.getString() ;
		    	 }
		    
	    	  System.out.println( "normal string = " + descriptionProperty.getString() ) ;

		    
		    return descriptionProperty.getString() ; 
		   }
	  
	  public void setOverrideDescription( String string )
		   {
		    if( string != null )
		    	 
		    	 overrideDescriptionProperty = new ConstantString( string  ) ;
		    
		    else
		    	 overrideDescriptionProperty = null ;
		   
		   }

		 public void addModelChangeListener( ModelChangeListener listener)
			  {
			   modelChangeListenerSet.add( listener ) ;		   
			  }
		 
		 public void addModelListener( ModelListener listener)
			  {
			   modelListenerSet.add( listener ) ;		   
			  }
		 
		 
		  void modelChangeToListeners()
			   {
			    Iterator it = modelChangeListenerSet.iterator() ;
			    
			    while( it.hasNext() )
			    	 {
			    	  ( (ModelChangeListener) it.next() ).handleModelChange( this ) ;
			    	  System.out.println( "model change sent" ) ;
			    	 }
			    
			   }
		  
			 
		  void informModelListeners()
			   {
			    Iterator it = modelListenerSet.iterator() ;
			    
			    while( it.hasNext() )
			    	 {
			    	  ( (ModelListener) it.next() ).modelChanged() ;
			    	  System.out.println( "model change sent" ) ;
			    	 }
			    
			   }


		 public StringSourceInterface getDescriptionSource()
			  {
			  return descriptionProperty ;
			  }





		 public void setDescriptionSource( StringSourceInterface description)
			  {
			   descriptionProperty = description ;
			  }





	 public void update()
		  {
		  // modelManager.informModelListeners() ;
		  }

	 public void updateView()
		  {
		   modelManager.informModelListeners() ;
		  }





	 public PropertySetInterface getLibrary()
		  {
		  return valueLibrary ;// object.propertySet() ;
		  }






	 public void load( NodeDataInterface data)
		  {
		   valueLibrary.add( data.propertySet() ) ;
		   overlayTextLibrary.add( data.textPropertySet() ) ;
		   
		   overlayTextLibrary.propertyObject( "empty", "" ) ;
		  }



	 public void load( LinkDataInterface data)
		  {
		   valueLibrary.add( data.propertySet() ) ;		  
		  }



	 public void setDescriptionPath( PropertyPathInterface path)
		  {
		   descriptionProperty = new ConstantString( (String) overlayTextLibrary.atPathObject( path) ) ;
		  }



	 public void setModelManager( ModelManager modelManager)
		  {
		   this.modelManager = modelManager ;
		   
		   addModelListener( modelManager ) ;
		  }
		  
	  
	  /*
	  public Vector3f getPosition() { return new Vector3f( getX(), getY(), getZ() ) ; }
	  public Color3f getColor() { return new Color3f( getR(), getG(), getB() ) ; }
	  
	  
	  public ValueSourceInterface getXSource() { return xProperty ; }
	  public void setXSource( ValueSourceInterface source ) { xProperty = source ;	} 
	  public float getX() { return xProperty.getValue() ; }
	
	  public ValueSourceInterface getYSource() { return yProperty ; } 
	  public void setYSource( ValueSourceInterface source ) { yProperty = source ;	}
	  public float getY() { return yProperty.getValue() ; }
	  
	  public ValueSourceInterface getZSource() { return zProperty ; }
	  public void setZSource( ValueSourceInterface source ) { zProperty = source ;	}
	  public float getZ() { return zProperty.getValue() ; }
	  
	  
	  public ValueSourceInterface getRSource() { return rProperty ; }	  
	  public void setRSource( ValueSourceInterface source ) { rProperty = source ;	}
	  public float getR() { return rProperty.getValue() ; }

	  public ValueSourceInterface getGSource() { return gProperty ; }	  
	  public void setGSource( ValueSourceInterface source ) { gProperty = source ;	}
	  public float getG() { return gProperty.getValue() ; }
	  
	  public ValueSourceInterface getBSource() { return bProperty ; }	  	  
	  public void setBSource( ValueSourceInterface source ) { bProperty = source ;	}
	  public float getB() { return bProperty.getValue() ; }
	  
	  public ValueSourceInterface getMorphSource() { return morphProperty ; }	  	  
	  public void setMorphSource( ValueSourceInterface source ) { morphProperty = source ;	}
	  public float getMorph() { return morphProperty.getValue() ; }
	  
	  public ValueSourceInterface getRadiusSource() { return radiusProperty ; }	  	  
	  public void setRadiusSource( ValueSourceInterface source ) { radiusProperty = source ;	}
	  public float getRadius() { return radiusProperty.getValue() ; }



*/

	  
	 }
