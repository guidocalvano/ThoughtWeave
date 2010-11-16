
package Graphics3D ;


import java.util.*; 

import javax.media.j3d.*;

import java.awt.event.* ; 

import com.sun.j3d.utils.picking.*;

public class Shape3DViewManager implements Shape3DViewManagerInterface
	 {
	  SceneManager sceneManager ; 

	  Vector canvas3DControlSet ;

	  Map<Object,Object> nodeToShape3DView ;
	 
	  Node test ;
	  
	  PickCanvas pickCanvas ;
	  
	  public Shape3DViewManager( SceneManager sceneManager )
	  	{
	  	 this.sceneManager = sceneManager ;
	  	
	  //	 nodeToShape3DView = Collections.synchronizedMap( new HashMap() ) ;
	  	
	  	 
	  	nodeToShape3DView = new HashMap( (int) ( 140000.0 / .7 ) ) ;
	  	 
	  	 canvas3DControlSet = new Vector() ;
	  	 
	  	 
	  	 
	  	}
	  
	  public CameraControl getCameraControl() { return sceneManager.getCameraControl() ; }
	  
	  void addCanvas3DControl( Canvas3DControl canvas )
		{
		 canvas3DControlSet.add( canvas ) ; 
		 
		 System.out.println( "after canvas add") ;
		
		 
		}
	  
	
	  
	  public void addShapeView( Shape3DViewInterface view  )
		   {
	//	   System.out.println( "added shapeview" ) ;
		   
		   // test = view.getShape() ;
		   
		    
		   if( view.getShape() != null )
				nodeToShape3DView.put( view.getShape(), view ) ;
		    
		   else if( view.getMorph() != null )
				nodeToShape3DView.put( view.getMorph(), view ) ;
		   else 
				{
				 System.exit( 0 ) ;
				
				}
		   
		  
		    view.setCameraControl( getCameraControl() ) ;
		    
		//    System.out.println( "key " +view.getShape()+"after get" + nodeToShape3DView.get(  view.getShape() ) ) ;
		    
	       // sceneManager.addToRoot( view.getBranchGroup() ) ;
		   }
	  
	  public SceneManager getSceneManager() { return sceneManager ; }


	 public void mouseClicked( PickRay ray, MouseEvent e )
		  {
		  /*
		   Node closestView = sceneManager.pickClosest( ray ) ;
		   
		   if( closestView != null ) 
				{
				 System.out.println( "shape found" + closestView ) ;
				
				 
				 System.out.println( "" + nodeToShape3DView.size() ) ;
				 Shape3DView view = (Shape3DView) nodeToShape3DView.get( closestView ) ;
				 
				 System.out.println( "closest equals test" +closestView.equals( test  ) );
				 
				 if( view != null )
					  {
					   System.out.println( "view for shape found too");
					   view.mouseClicked( e ) ;					   
					  }
				}
		   else System.out.println( "view not found") ;
*/
		  }
	  
	  





	 public void mousePressed( PickRay ray, MouseEvent e)
		  {
		   System.out.println( "PICK CANVAS INVOKED" ) ;
			 
		   int x = e.getX() ;
		   int y = e.getY() ;
		   pickCanvas.setShapeLocation( e ) ;

		   PickResult result = pickCanvas.pickClosest() ;
		   
		   if( result != null )
				{
				   System.out.println( "result found" ) ;

				 Object object = result.getObject() ;
		   
		   		 MouseListener listener = (MouseListener) nodeToShape3DView.get( object ) ;
		   
		   		 if( listener != null )
		   			  	{
						 System.out.println( "executing matching listener" ) ;

		   			  	 listener.mousePressed( e ) ;
		   			  	}
				}
		   		  
		  }


	 public void mouseReleased( PickRay ray, MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void addCanvas3D( Canvas3D canvas3d)
		  {
		   pickCanvas = new PickCanvas( canvas3d, sceneManager.getRoot() ) ;
		   
		   pickCanvas.setMode( PickTool.GEOMETRY ) ;
		   
		   pickCanvas.setTolerance( (float) 1.0 ) ;
		   
		   canvas3d.addMouseListener( this ) ;
		   canvas3d.addMouseMotionListener( this ) ;

		  }

	 public void mouseClicked( MouseEvent e)
		  {
		   System.out.println( "PICK CANVAS INVOKED" ) ;
		 
		   int x = e.getX() ;
		   int y = e.getY() ;
		   pickCanvas.setShapeLocation( e ) ;

		   PickResult result = pickCanvas.pickClosest() ;
		   
		   if( result != null )
				{
				   System.out.println( "result found" ) ;

				 Object object = result.getObject() ;
		   
		   		 MouseListener listener = (MouseListener) nodeToShape3DView.get( object ) ;
		   
		   		 if( listener != null )
		   			  	{
						 System.out.println( "executing matching listener" ) ;

		   			  	 listener.mouseClicked( e ) ;
		   			  	}
				}
		   
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
		  // TODO Auto-generated method stub
		  
		  }

	 public void removeShape3DView( Shape3DViewInterface shape3dView)
		  {
		   nodeToShape3DView.remove( shape3dView ) ;
		  }

	 public void mouseDragged( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseMoved( MouseEvent e)
		  {
		//   System.out.println( "MOVE LISTENER" ) ;
		  }
	  
	  
	  
	  
	 }
