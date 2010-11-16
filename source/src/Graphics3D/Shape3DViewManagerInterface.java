package Graphics3D ;

import java.awt.event.* ;


public interface Shape3DViewManagerInterface extends MouseRayListener, MouseListener, MouseMotionListener
	 {

	 void addShapeView( Shape3DViewInterface view);

	 void removeShape3DView( Shape3DViewInterface shape3dView);

	 CameraControl getCameraControl() ;

	 }
