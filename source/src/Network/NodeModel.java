package Network ;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Map;
import java.util.Map.Entry;


import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import Network.Data.NodeDataInterface;

public class NodeModel extends Model 
	 {
	  NodeModel( int id )
	  {
		super( id ) ; 
	    property( "x", new Constant( (float) ( Math.random() ) ) ) ;
	    property( "y", new Constant( (float) ( Math.random()  )  ) ) ;
	    property( "z", new Constant( (float) ( Math.random()  )  ) ) ;

	    property( "red", new Constant( (float) .11  ) ) ;
	    property( "green", new Constant( (float) .11 ) ) ;
	    property( "blue", new Constant( (float) .11 ) ) ;

	    property( "size", new Constant( (float) valueLibrary.property( "one" ) ) ) ;
	    property( "shape", new Constant( (float) valueLibrary.property( "zero" ) ) ) ;
	    property( "transparency", new Constant( (float) valueLibrary.property( "one" ) ) ) ;

	    property( "visible", new Constant( (float) valueLibrary.property( "one" ) ) ) ;

	    setDescriptionSource( new ConstantString( "" ) ) ;
	    
	  }
	 
	 } 
