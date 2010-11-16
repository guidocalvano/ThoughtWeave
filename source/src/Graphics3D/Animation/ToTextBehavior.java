package Graphics3D.Animation;


import javax.media.j3d.Alpha;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Text3D;

import Graphics3D.Shape3DViewInterface;
import Network.NodeViewInterface;

public class ToTextBehavior extends ViewPropertyToValueBehavior
	 {
	  Text3D target ;
	  String string ;
	  
	  boolean changeMade ;
	  
	  
	  public ToTextBehavior( NodeViewInterface shape, String string, Alpha progress ) 
	 	{
	 	 super( shape, 0.0, progress ) ;

	 	 
	 	 this.string = string ;
	 	 this.target = shape.getTextOverlay().getText3D() ;
	 	 
	 	 changeMade = false ;
	 	}
	  
	  
	 @Override
	 double getProperty()
		  {
		  return 0;
		  }

	 @Override
	 void setProperty( double value)
		  {
		  if( !changeMade )
				target.setString( string ) ;
		   else 
				changeMade = true ;
		  }

	 }
