package Graphics3D.Animation;
import Graphics3D.* ;


import javax.media.j3d.Alpha;


public class ToCylinderRadiusBehavior extends ViewPropertyToValueBehavior  
	 {

	

	 public ToCylinderRadiusBehavior( Shape3DViewInterface shape, double value,
			   Alpha time )
		  {
		  super( shape, value, time );
		  }


	 @Override
	 double getProperty()
		  {
		  return shape.getCylinderRadius() ;
		  }

	 @Override
	 void setProperty( double value)
		  {
		   shape.setCylinderRadius( value ) ;
		  }

	 }
