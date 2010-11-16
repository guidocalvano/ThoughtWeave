package Network;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;

import javax.media.j3d.*;
import javax.vecmath.Point3d;

import Graphics3D.Shape3DViewInterface;


public class NodeDragBehavior extends Behavior implements MouseListener, MouseMotionListener
	 {
	  Component c ;
	 
	  NodeControlInterface node ;
	 
	  ModelInterface model ;
	 
	  WakeupCriterion wakeup ;
	  
	  BranchGroup destroyGroup ;

	  
	  PropertyPathInterface pathX ;
	  PropertyPathInterface pathY ;
	  
	  double x ;
	  double y ;
	  
	  double initialX ;
	  double initialY ;
	  
	  
	 private long previousTimeInMillis;

	 private boolean mustStop;
	  
	  boolean baseCoordinatesStored ;
	 
	  int baseX ;
	  int baseY ;
	  
	  NodeDragBehavior( NodeControlInterface node )
			   {
			    System.out.println( "NodeDragBehavior created" ) ;
			   
			    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			    setSchedulingBounds(bounds);
			 
			    this.c = node.getView().getManager().getCameraControl().getCanvas3D() ;
			    
			    c.addMouseListener( this ) ;
			    c.addMouseMotionListener( this ) ;
			    
			    this.node 	= node 				;
			    this.model 	= node.getModel() 	;
			    
		 	 	 destroyGroup = new BranchGroup() ;
		 	 	 
		 	 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
		  	 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
		 	 	
		  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		  	 	 
		  	 	 
		 	 	 
		 	 	 destroyGroup.addChild(  this  ) ;
			    
			    node.getView().getBranchGroup().addChild( destroyGroup ) ;
			    
			    pathX = new PropertyPath() ;
			    pathX.add( "x" ) ;
			    
			    pathY = new PropertyPath() ;			    
			    pathY.add( "y" ) ;
			    
			    
			    if( !model.getOverrideProperties().containsProperty( "x" ) )
			    	 
					  model.getOverrideProperties().addObject( pathX,  model.getViewProperties().atPathObject( pathX )  ) ;
			    
			    
			    if( !model.getOverrideProperties().containsProperty( "y" ) )
		    
					  model.getOverrideProperties().addObject( pathY,  model.getViewProperties().atPathObject( pathY )  ) ;

			    initialX = ( (ValueSourceInterface) model.getOverrideProperties().atPathObject( pathX ) ).getValue() ;
			    initialY = ( (ValueSourceInterface) model.getOverrideProperties().atPathObject( pathY ) ).getValue() ;			    
			    
			    	 
			    x = ( (ValueSourceInterface) model.getOverrideProperties().atPathObject( pathX ) ).getValue() ;
			    y = ( (ValueSourceInterface) model.getOverrideProperties().atPathObject( pathY ) ).getValue() ;
			    
			    mustStop = false ;
			    baseCoordinatesStored = false ;
			   }
	 
	 @Override
	 public void initialize()
		  {
		   
		    wakeup = new WakeupOnElapsedFrames( 1 ) ;

		    elapsedTime() ;
		    
			   wakeupOn( wakeup ) ;
		  }
	 
	  public void	processStimulus(java.util.Enumeration criteria)
		   {
		    updateShapePosition() ;
		   
		    if( mustStop ) { destroyGroup.detach() ; return ; } 
		    
			wakeupOn( wakeup ) ;
		    

		   }
	  
	  private void updateShapePosition()
		  {
		   System.out.println( "dragging node to ( " + x + " " + y + ")" ) ;
		  
		   model.getOverrideProperties().addObject( pathX, new Constant( (float) x ) ) ;
		   model.getOverrideProperties().addObject( pathY, new Constant( (float) y ) ) ;
		   
		   model.updateView() ;
		  }

	 double elapsedTime()
		   {
		    long nextTimeInMillis = System.currentTimeMillis() ;
		    long dT = nextTimeInMillis - previousTimeInMillis ;
		    
		   
		    previousTimeInMillis = nextTimeInMillis ;
		    
		    
		    return (double) dT ;
		   }
	  

	
	 public void mouseClicked( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseEntered( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseExited( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mousePressed( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseReleased( MouseEvent e)
		  {
		   mustStop = true ;
		   
		   c.removeMouseListener( this ) ;
		   c.removeMouseMotionListener( this ) ;
		   
		   node.updatePropertySetEditor() ;
		  }

	 public void mouseDragged( MouseEvent e)
		  {
		   if( !baseCoordinatesStored )
				{
				 baseX = e.getX() ;
				 baseY = e.getY() ;
				
				 baseCoordinatesStored = true ;
				}
		  
		   x = initialX + ( ( e.getX() - baseX ) / 800.0) ;
		   y = initialY - ( ( e.getY() - baseY ) / 800.0 ) ;

		   System.out.println( "MOUSE MOVED MOUSE MOVED MOUSE MOVED x = " + x + " y =" + y ) ;		  
		  }

	 public void mouseMoved( MouseEvent e)
		  {

		  }

	 }
