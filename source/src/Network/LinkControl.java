package Network;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.media.j3d.*;
import javax.swing.JFrame;

import Graphics3D.Shape3DViewManagerInterface;
import Graphics3D.Animation.ToBlueBehavior;
import Graphics3D.Animation.ToGreenBehavior;
import Gui.FrameManager;
import Network.Gui.PropertySetOverrideEditor;


public class LinkControl implements LinkControlInterface, MouseListener
	 {
	  LinkViewInterface  view  ;
	  ModelInterface model ;

	  NodeControlInterface a ;
	  NodeControlInterface b ;
	
	  FrameManager frameManager ;

	 public LinkControl( NodeControlInterface nodeControl, NodeControlInterface nodeControl2 )
		  {
		   a = nodeControl ;
		   b = nodeControl2 ;
		  
		   model = new LinkModel( 0 ) ;
		   
		   view = new LinkView( model, nodeControl.getView(), nodeControl2.getView() ) ;
		   
		   view.addMouseListener( this ) ;

		   frameManager = null ;
		  }

	 public LinkControl( BranchGroup parent, Shape3DViewManagerInterface manager, NodeControlInterface nodeControl, NodeControlInterface nodeControl2 )
		  {
		   a = nodeControl ;
		   b = nodeControl2 ;
		  
		   model = new LinkModel( 0 ) ;
		   
		   view = new LinkView( parent, manager, model, nodeControl.getView(), nodeControl2.getView() ) ;
		   
		   view.addMouseListener( this ) ;

		   frameManager = null ;
		  }
	 
	 public LinkControl()
		  {
		 // TODO  model = new LinkModel() ;
		  }

	 public void randomThicknessChange( Alpha time)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public LinkViewInterface getView()
		  {
		   return view ;
		  }

	 public ModelInterface getModel()
		  {
		  // TODO Auto-generated method stub
		  return model ;
		  }

	 public void mouseClicked( MouseEvent e)
		  {
		   if( frameManager == null )
				{
				 frameManager = new FrameManager() ;
				
		 		 PropertySetOverrideEditor pse = new PropertySetOverrideEditor( model, model.getViewProperties(), model.getOverrideProperties() ) ;
				 
				 frameManager.init( "Link " + a.id() + " to " + b.id() , 400, 800, pse.getPanel() ) ;
				}
		   
		   frameManager.showFrame() ;
		   
		   /*
		   JFrame frame = new JFrame() ;
		   
			 		 
		  	 frame.setVisible( true ) ;
		  	 
		  	 frame.setSize( 400, 800 ) ;
		  	frame.setResizable(true);		
	 		PropertySetOverrideEditor pse = new PropertySetOverrideEditor( model, model.getViewProperties(), model.getOverrideProperties() ) ;
	 		frame.setContentPane(  pse.getPanel()  ) ;

	 		frame.setTitle( "Link editor" ) ;

	 		frame.pack();
	 		*/
	 	
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

	 public void releaseEventListeners()
		  {
		   view.releaseEventListeners() ;
		  }

	 }
