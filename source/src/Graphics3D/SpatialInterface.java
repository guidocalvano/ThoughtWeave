package Graphics3D;

public interface SpatialInterface
	 {

	  SpatialInterface init( Shape3DViewInterface shape3dView);
	 
	  void setScale( double value ) ;
	  
	  double getScale() ;
	  
	  
	  void setCylinderRadius( double value ) ;
	  
	  double getCylinderRadius() ;

	  void setShapeValue( double shape ) ;

	  double getShapeValue() ;
	  
	  double getX() ;
	  
	  void setX( double x ) ;
	  
	  
	  double getY() ;
	  
	  void setY( double y ) ;
	  
	  
	  double getZ() ;
	  
	  void setZ( double z ) ;
	  
	 }
