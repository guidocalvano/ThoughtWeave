package Network;

import Graphics3D.Shape3DViewInterface;


public interface LinkViewInterface extends Shape3DViewInterface, ModelChangeListener
	 {

	 void reposition();

	 void toModel( float percentageIncrease);

	 }
