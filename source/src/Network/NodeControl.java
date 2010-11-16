package Network ;

import Graphics3D.Shape3DViewManagerInterface;
import Gui.FrameManager;
import Network.Gui.*; 

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.media.j3d.*;
import javax.swing.JFrame;


public class NodeControl implements NodeControlInterface, MouseListener
	 {
	  int id ;
	 
	  NodeViewInterface 	view ;
	  
	  ModelInterface	model ;
	  
	  List				linkControlSet ;
	  
	  FrameManager 		frameManager ;
	  
	  PropertySetOverrideEditor pse ;
	 
	 
	  NodeControl( int id ) 
	  	{
	  	 this.id = id ;
	  	 linkControlSet = new Vector() ;
	  	
	  	
	  	 model = new NodeModel( id ) ;
	  	
	  	 view = new NodeView( model ) ;	
	  	
	  	
	  
	  	
	  	 view.addMouseListener( this ) ; 
	  	
	  	 frameManager = null ;
	  	}
	  
	  
	  
	  NodeControl( BranchGroup parent, Shape3DViewManagerInterface manager,  int id ) 
	  	{
	  	 this.id = id ;
	  	 linkControlSet = new Vector() ;
	  	
	  	
	  	 model = new NodeModel( id ) ;
	  	
	  	 view = new NodeView( parent, manager, model ) ;	
	  	
	  	
	  
	  	
	  	 view.addMouseListener( this ) ; 
	  	
	  	 frameManager = null ;
	  	}
	  
	  
	  
	  public void init()
		   {
		    view.initNodeView() ;
		   }
	  
	  
	  NodeControl( ModelInterface model ) 
	  	{
	  	linkControlSet = new Vector() ;
	  	
	  	
	  	this.model = model ;
	  	
	  	view = new NodeView( model ) ;
	  	
	  	
	  	}
	  
	  public int id() { return id ; }
	  
	  void addLinkControl( LinkControlInterface link )
		   {
		    linkControlSet.add( link ) ;
		   }

	 public NodeViewInterface getView()
		  {
		  return view  ;
		  }

	 
	 public ModelInterface getModel()
		  {
		  return model  ;
		  }	 
	 


	 public void updatePropertySetEditor()
		  {
		   pse.init() ;
		  }
	

	 public void mouseClicked( MouseEvent e)
		  {

		  
		   if( e.getButton() == MouseEvent.BUTTON3 )
				{
        		   if( frameManager == null )
        				{
        				 frameManager = new FrameManager() ;
        				
        		 		 pse = new PropertySetOverrideEditor( model, model.getViewProperties(), model.getOverrideProperties() ) ;
        				 
        		 		 pse.init() ;
        		 		 
        				 frameManager.init( "Node " + this.id, 400, 800, pse.getPanel() ) ;
        				}
        		   
        		   pse.init();
        		   
        		   frameManager.showFrame() ;
				}
		   

	/*	   
		    JFrame frame = new JFrame() ;
		   			 		 
		  	frame.setVisible( true ) ;
		  	 
		  	frame.setSize( 400, 800 ) ;
		  	frame.setResizable(true);		
	 		PropertySetOverrideEditor pse = new PropertySetOverrideEditor( model, model.getViewProperties(), model.getOverrideProperties() ) ;
	 		frame.setContentPane(  pse.getPanel()  ) ;

	 		frame.setTitle( "Node editor" ) ;

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
		   System.out.println( "mouse pressed on node" ) ;
		  
		   if( e.getButton() == MouseEvent.BUTTON1 )
				{
				 new NodeDragBehavior( this ) ;
				}		  
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
