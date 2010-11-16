package Network;

import Network.Data.LinkDataSetInterface;
import Network.Data.NodeDataSetInterface;
import javax.media.j3d.*;


public interface NetworkControlInterface
	 {


	 void generateNodeSet( int modelCount);

	 
	 
	 void initNetwork( int nodeCount ) ;



	 void load( NodeDataSetInterface loaded);



	 void load( LinkDataSetInterface loaded);

	 TransformGroup getTransformGroup() ;

   	 public void showNodeMappingWindow() ;

   	 public void showLinkMappingWindow() ;


	 void adaptViewToModel( float percentageIncrease);



	 void toModel();



	 void applyDefaultMappingSet();

	 }
