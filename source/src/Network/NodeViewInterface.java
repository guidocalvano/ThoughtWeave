package Network ;

import javax.media.j3d.Alpha;
import javax.media.j3d.Text3D;

import Graphics3D.Shape3DViewInterface;
import Graphics3D.Text3DViewInterface;


public interface NodeViewInterface extends Shape3DViewInterface
	 {

	  void initNodeView();

	 Text3DViewInterface getTextOverlay();

	 void toModel( float percentageIncrease);

	  
	 }
