package Graphics3D;

import javax.media.j3d.* ;

public class LineSpace implements SpatialInterface
	 {
	  Shape3DViewInterface shape ;
	  
	  public SpatialInterface init( Shape3DViewInterface shape3dView)
		  {
		   this.shape = shape3dView ;
		   
		   this.shape.getAppearance().setCapability( Appearance.ALLOW_LINE_ATTRIBUTES_READ ) ;
		   this.shape.getAppearance().setCapability( Appearance.ALLOW_LINE_ATTRIBUTES_WRITE ) ;
		   return this ;
		  }
	  
	  
	  public void setCylinderRadius( double value )
		   {
		    shape.getAppearance().getLineAttributes().setLineWidth( (float) value * 3f ) ;
		   }
	  
	  public double getCylinderRadius()
		   {
		    return shape.getAppearance().getLineAttributes().getLineWidth() ;
		   }
	  public void setShapeValue( double shapeValue )
			  {
			   			  
			  }
		 
		 public double getShapeValue() 
			  {
			   System.out.println( "Failed to set value of shape on a Shape3DView with a LineGeometry. LineGeometry doesn't have a shape value" ) ;
			  
			   System.exit( 0 ) ;
			  
			   return 1; 
			  }


	 public double getScale()
		  {
		   return 0;
		  }


	 public void setScale( double value)
		  {
		  }


	 public double getX()
		  {
		  return 0;
		  }


	 public double getY()
		  {
		  return 0;
		  }


	 public double getZ()
		  {
		  return 0;
		  }


	 public void setX( double x)
		  {
		  
		  }


	 public void setY( double y)
		  {
		  
		  }


	 public void setZ( double z)
		  {
		  
		  }
	

	  
	  
	 }
