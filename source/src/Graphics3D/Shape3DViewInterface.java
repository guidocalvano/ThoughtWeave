package Graphics3D ;

import java.awt.event.MouseListener;
import javax.media.j3d.*;
import javax.vecmath.*;

import Graphics3D.Animation.AnimationManagerInterface;
import Graphics3D.GuiComponent3D.*;

public interface Shape3DViewInterface extends MouseListener
	 {
	  void init( Leaf shape, boolean isVisible ) ;
	  
	  Shape3DViewManagerInterface getManager() ;
	 
	  TransformGroup getTransformGroup() ;
	  
	  Appearance getAppearance() ;
	  
	  Shape3D getShape() ;

	  
	  Morph		 getMorph() ;
	  
	  int getMorphCount() ;
	  
	  BranchGroup getBranchGroup() ;
	  
	  Leaf getLeaf() ;
		 
	  
	  void setVisible( boolean isVisible ) ;

	  
	  AnimationManagerInterface getAnimationManager() ;
	  
	  
	 void addMouseListener( MouseListener listener ) ;

	 void setCameraControl( CameraControl cameraControl);

	 double getX() ;

	 void setX( double value ) ;

	 double getY();

	 void setY( double value);

	 double getZ();

	 void setZ( double value);

	 double getRed();

	 void setRed( double value);

	 double getGreen();

	 void setGreen( double value);

	 double getBlue();

	 void setBlue( double value);

	 double getScale();

	 void setScale( double value);

	 void setShapeValue( double value);

	 double getShapeValue();

	 void setTransparency( double value);

	 double getTransparency();

	 double getCylinderRadius();

	 void setCylinderRadius( double value);
	 
	 void setVisible( double isVisible ) ;

	 double getVisible();

	 void setSpatialInterface( SpatialInterface standardGeometry);

	 void setMaterialInterface( MaterialInterface material );

	 void setAppearance( Appearance appearance);

	 BranchGroup getIsVisibleGroup();

	 void releaseEventListeners();

	 void initShape();

	 }
