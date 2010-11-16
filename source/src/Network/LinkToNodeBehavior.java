package Network;

import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.media.j3d.WakeupOnTransformChange;

import javax.vecmath.Point3d;


public class LinkToNodeBehavior extends Behavior
	 {
		
	  WakeupOnTransformChange nodeTransformChange ; 
	 
	  BranchGroup destroyGroup ;
	  
	  LinkViewInterface link ;
	  NodeViewInterface node ;

	  NodeViewInterface node2 ;
	  
	  
	  LinkToNodeBehavior( LinkViewInterface link, NodeViewInterface node )
	  	{
	  	 this.link = link ;
	  	 this.node = node ;
	  	
	  	nodeTransformChange = new WakeupOnTransformChange( node.getTransformGroup() ) ; 
	 	 
	 	
	 	 destroyGroup = new BranchGroup() ;
	 	 
	 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
	 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
	 	
	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	 	 
	 	 
	 	 
	 	 destroyGroup.addChild(  this  ) ;

	 	 link.getBranchGroup().addChild( destroyGroup ) ;

	 	
	 	 initBounds() ;

	 	}


	 public LinkToNodeBehavior( LinkView link,
			   NodeViewInterface node,
			   NodeViewInterface node2 )
		  {
		  	 this.link = link ;
		  	 this.node = node ;
		  	 this.node2 = node2 ;

		  	 
		  	nodeTransformChange = new WakeupOnTransformChange( node.getTransformGroup() ) ; 
		 	 
		 	
		 	 destroyGroup = new BranchGroup() ;
		 	 
		 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
		 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
		 	
		 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		 	 
		 	 
		 	 
		 	 destroyGroup.addChild(  this  ) ;

		 	 link.getBranchGroup().addChild( destroyGroup ) ;

		 	
		 	 initBounds() ;
		  }


	 void initBounds()
		  {
		   BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		   setSchedulingBounds(bounds);
		  }

	 
	 @Override
	 public void initialize()
		  {
		   wakeupOn( nodeTransformChange ) ;
		  }

	 @Override
	 public void processStimulus( Enumeration arg0)
		  {


		   link.reposition() ;
		  
		  
		   wakeupOn( nodeTransformChange ) ;
		  }
	
	  void destroy()
		   {
		    destroyGroup.detach() ;
		   
		   }
	 }

