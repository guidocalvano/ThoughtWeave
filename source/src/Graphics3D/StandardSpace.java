package Graphics3D;

import javax.media.j3d.*;

import javax.vecmath.*;

public class StandardSpace implements SpatialInterface
	 {
	  Shape3DViewInterface shape ;
	  
	  
	  boolean hasPosition ;
	  
	  double x ;
	  double y ;
	  double z ;
	  
	  boolean hasRadius ;
	  
	  double cylinderRadius ;
	  
	  
	  boolean hasScale ;
	  
	  double scale			;
	  
	  boolean hasWeights ;
	  
	  double[] weights ;
	  
	  StandardSpace( boolean hasPosition, boolean hasScale, boolean hasRadius, boolean hasWeights )
	  	{
	  	 this.hasPosition 	= hasPosition 	;
	  	 this.hasRadius 	= hasRadius		;
	  	 this.hasWeights	= hasWeights	;
	  	 this.hasScale		= hasScale		;
	  	 
	  	 x = 0 ;
	  	 y = 0 ;
	  	 z = 0 ;
	  	 
	  	 scale = 1 ;
	  	 cylinderRadius = 1 ;
	  	 
	  	 
		 weights = new double[] { 1.0, 0.0 } ;					

	  	 
	  	}
	  
	  
	  public SpatialInterface init( Shape3DViewInterface shape3dView)
			  {
			   this.shape = shape3dView ;
			   
			   if( hasPosition )
					{
					 setX( x ) ;
					 setY( y ) ;
					 setZ( z ) ;
					}
			   
			   
			   if( hasRadius )
					
					setCylinderRadius( cylinderRadius ) ;
			   
			   if( hasScale )
					
					setScale( scale ) ;
			   
			   
			   
			   if( hasWeights )
					
					setShapeValue( getShapeValue() ) ;
			   
			   return this ;
			  }
	  
	  
	  
	  
	  
	  
	  public void setCylinderRadius( double value )
		   {
		    this.cylinderRadius = value ;
		   
		    if( shape == null ) return ;
		   
		    Transform3D transform = new Transform3D() ;
		    shape.getTransformGroup().getTransform( transform ) ;
		    
		    Vector3d scale = new Vector3d() ; 
		    transform.getScale( scale ) ;
		    
		    scale.x = value ; scale.z = value ;
		    
		    transform.setScale( scale ) ;
		    shape.getTransformGroup().setTransform( transform ) ;
		   }
	  
	  public double getCylinderRadius()
		   {
		
		    
		    return cylinderRadius ;
		   
		   }

	  public void setShapeValue( double shapeValue )
			  {
			   weights = new double[] { 1.0 - shapeValue, shapeValue } ;					
			
			   if( shape != null && shape.getMorph() != null )
					shape.getMorph().setWeights( weights ) ;			  
			  }
		 
		 public double getShapeValue() 
			  {
			   return weights[ 1 ] ; 
			  }






	 public double getScale()
		  {		  
		   return scale ;
		  }






	 public void setScale( double scale )
		  {
		   if( scale == 0.0 ) scale = Double.MIN_VALUE ;
		   this.scale = scale ;
		   
		   
		   if( shape == null ) return ;
		   
		   Transform3D transform = new Transform3D() ;
		   
		   shape.getTransformGroup().getTransform( transform ) ;
		   
		   Vector3d scaleVector = new Vector3d( scale, scale, scale ) ;
		   
		   transform.setScale( scaleVector ) ;
		    		   
		   shape.getTransformGroup().setTransform( transform ) ;
		  
		   
		   
		  }






	 public double getX()
		  {		  
		   return x ;
		  }






	 public double getY()
		  {
		   return y ;
		  }






	 public double getZ()
		  {
		   return z ;
		  }






	 public void setX( double x)
		  {
		   this.x = x ;
		  
		   
		   if( shape != null )
				{
				   Transform3D transform = new Transform3D() ;
				   
				   shape.getTransformGroup().getTransform( transform ) ;
				   
				   Vector3d translation = new Vector3d() ;
		 		   
				   transform.get( translation ) ;
				   
				   translation.x = x ;
				   
				   transform.set( translation ) ;
		 		   
				   shape.getTransformGroup().setTransform( transform ) ;
				}
		  }






	 public void setY( double y)
		  {
		   this.y = y ;
		   
		   if( shape != null )
				{
				   Transform3D transform = new Transform3D() ;
				   
				   shape.getTransformGroup().getTransform( transform ) ;
				   
				   Vector3d translation = new Vector3d() ;
		 		   
				   transform.get( translation ) ;
				   
				   translation.y = y ;
				   
				   transform.set( translation ) ;
		 		   
				   shape.getTransformGroup().setTransform( transform ) ;
				}
		  }






	 public void setZ( double z)
		  {
		   this.z = z ;
		   if( shape != null )
				{
				   Transform3D transform = new Transform3D() ;
				   
				   shape.getTransformGroup().getTransform( transform ) ;
				   
				   Vector3d translation = new Vector3d() ;
		 		   
				   transform.get( translation ) ;
				   
				   translation.z = z ;
				   
				   transform.set( translation ) ;
		 		   
				   shape.getTransformGroup().setTransform( transform ) ;
				}
		  }
	
	 }
