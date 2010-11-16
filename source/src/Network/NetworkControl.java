package Network;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.*;

import Graphics3D.Shape3DViewManager;
import Graphics3D.Shape3DViewManagerInterface;
import Gui.FrameManager;
import Network.Data.LinkDataSetInterface;
import Network.Data.NodeDataSetInterface;
import Network.Gui.NetworkMappingManagerView;

import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;

import com.sun.j3d.loaders.LoaderBase.* ;
import com.sun.j3d.loaders.Scene;
import java.util.Locale;



public class NetworkControl implements NetworkControlInterface, ModelListener
	 {
	 
	  Shape3DViewManagerInterface shape3DViewManager ;
	  
	  BranchGroup 		branchGroup 	;
	  BranchGroup		toRemoveTransformGroup ;
	  TransformGroup 	transformGroup 	;
	 
	  BranchGroup 		contentGroup 	;

	  
	  NetworkUpdateBehavior behavior ;

	  List nodeControlSet ;
	  
	  List linkControlSet ;
	  
	  ModelManagerInterface nodeModelManager ; NetworkMappingManagerView nodeMappingManagerView ;
	  
	  ModelManagerInterface linkModelManager ; NetworkMappingManagerView linkMappingManagerView ; 
	  
	  
	  FrameManager 			linkMappingFrameManager ;
	  
	  FrameManager 			nodeMappingFrameManager ;
	  
//	  Vector nodeViewSet ;
	  
//	  Vector linkViewSet ;
	  
	  
	  public TransformGroup getTransformGroup() { return transformGroup ; }
	  
	
	  
	  void init( Shape3DViewManagerInterface shape3DViewManager )
		   {
		    this.shape3DViewManager = shape3DViewManager ;
		    
//		    nodeViewSet = new Vector() ;
//		    linkViewSet = new Vector() ;

		    
		    nodeControlSet = new ArrayList( 600 ) ;
		    linkControlSet = new ArrayList( 600 * 600 / 2 ) ;

		/*    
		    transformGroup = new TransformGroup() ;
		  
	  	 
		    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	  	 
			  	 
		    transformGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		    transformGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);	  	 
		    transformGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);	  	 
			  	 
		    toRemoveTransformGroup = new BranchGroup() ;
		    toRemoveTransformGroup.addChild( transformGroup ) ;
		    

		    toRemoveTransformGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
		    toRemoveTransformGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		    toRemoveTransformGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
		    toRemoveTransformGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		    */
		    
		    
		    branchGroup = new BranchGroup() ;
		   
		    branchGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
		    branchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		    branchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
		    branchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		    
		 //   prepareNewTransformGroup() ;
		 //   branchGroup.addChild( toRemoveTransformGroup ) ;


			 nodeModelManager = new NodeModelManager() ;
			 
			 nodeModelManager.createViewProperty( "x" ) ;
			 nodeModelManager.createViewProperty( "y" ) ;
			 nodeModelManager.createViewProperty( "z" ) ;
			 
			 nodeModelManager.createViewProperty( "red" ) ;
			 nodeModelManager.createViewProperty( "green" ) ;
			 nodeModelManager.createViewProperty( "blue" ) ;
			 
			 nodeModelManager.createViewProperty( "size" ) ;
			 nodeModelManager.createViewProperty( "shape" ) ;
			 nodeModelManager.createViewProperty( "transparency" ) ;
			 
			 nodeModelManager.createViewProperty( "visible" ) ;
			 
			 nodeModelManager.addModelListener( this ) ;
			 
			 
			 linkModelManager = new LinkModelManager() ;
			 

			 
			 linkModelManager.createViewProperty( "red" ) ;
			 linkModelManager.createViewProperty( "green" ) ;
			 linkModelManager.createViewProperty( "blue" ) ;
			 
			 linkModelManager.createViewProperty( "thickness" ) ;
			 linkModelManager.createViewProperty( "transparency" ) ;

			 linkModelManager.createViewProperty( "visible" ) ;
			 
			 linkModelManager.addModelListener( this ) ;

//			 initNodeMappingManager() ;
//			 initLinkMappingManager() ;
			
//			 showNodeMappingWindow() ;	
		 		
//			 showLinkMappingWindow() ;	
		 		 
			 behavior = new NetworkUpdateBehavior( this ) ;
					    
					    
		   }
	  
	  
	   	 void initContentGroup()
	   		{
		     contentGroup = new BranchGroup() ;
		   
		     contentGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
		     contentGroup.setCapability(Group.ALLOW_CHILDREN_READ);
		     contentGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
		     contentGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		     
		     transformGroup.addChild( contentGroup ) ;
	 		}
	  
	  
	  	 void initNodeMappingManager()
	  		  {
	    	  if( nodeMappingFrameManager != null ) nodeMappingFrameManager.destroy() ;

	  		  
	  		   nodeMappingFrameManager = new FrameManager() ;
	    	  
		 	   nodeMappingManagerView = new NetworkMappingManagerView( nodeModelManager ) ;
	    	  
	    	   nodeMappingFrameManager.init( "Node Mapping Editor", 350, 800, nodeMappingManagerView.getPanel() ) ;
	  		  }
	  
	   	 public void showNodeMappingWindow()
		   {
		    if( nodeMappingFrameManager == null )
		    	 {
		    	  initNodeMappingManager() ;
		    	 }
		    
		    
		    nodeMappingFrameManager.showFrame() ;
		    /*
		   JFrame frame = new JFrame() ;
		   
	 		 
		   frame.setVisible( true ) ;
		  	 
		   frame.setSize( 350, 800 ) ;
		   frame.setResizable(true);		
	 		NetworkMappingManagerView mappingManagerView = new NetworkMappingManagerView( nodeModelManager ) ;
	 		frame.setContentPane(  mappingManagerView.getPanel()  ) ;

	 		frame.setTitle( "the node mapping editor" ) ;

	 		frame.pack();	
	 		*/  
		   }
	  
	    void initLinkMappingManager()
	    	 {
	    	  if( linkMappingFrameManager != null ) linkMappingFrameManager.destroy() ;
	    	 
	    	  linkMappingFrameManager = new FrameManager() ;
	    	  
		 	  linkMappingManagerView = new NetworkMappingManagerView( linkModelManager ) ;
	    	  
		 	  linkMappingFrameManager.init( "Link Mapping Editor", 350, 800, linkMappingManagerView.getPanel() ) ;
	    	 }
	    
	    
	     void initModelManagers()
	    	  {

				 nodeModelManager = new NodeModelManager() ;
				 
				 nodeModelManager.createViewProperty( "x" ) ;
				 nodeModelManager.createViewProperty( "y" ) ;
				 nodeModelManager.createViewProperty( "z" ) ;
				 
				 nodeModelManager.createViewProperty( "red" ) ;
				 nodeModelManager.createViewProperty( "green" ) ;
				 nodeModelManager.createViewProperty( "blue" ) ;
				 
				 nodeModelManager.createViewProperty( "size" ) ;
				 nodeModelManager.createViewProperty( "shape" ) ;
				 nodeModelManager.createViewProperty( "transparency" ) ;
				 
				 nodeModelManager.createViewProperty( "visible" ) ;
				 
				 nodeModelManager.addModelListener( this ) ;
				 
				 
				 linkModelManager = new LinkModelManager() ;
				 

				 
				 linkModelManager.createViewProperty( "red" ) ;
				 linkModelManager.createViewProperty( "green" ) ;
				 linkModelManager.createViewProperty( "blue" ) ;
				 
				 linkModelManager.createViewProperty( "thickness" ) ;
				 linkModelManager.createViewProperty( "transparency" ) ;

				 linkModelManager.createViewProperty( "visible" ) ;
				 
				 linkModelManager.addModelListener( this ) ;
	    	  }
	    
	   	 public void showLinkMappingWindow()
	   	   {
	   	   
		    if( linkMappingFrameManager == null )
		    	 {
		    	  initLinkMappingManager() ;
		    	 }
		    
		    linkMappingFrameManager.showFrame() ;

		    /*
	   	   JFrame frame2 = new JFrame() ;
		   
	 		 
		   frame2.setVisible( true ) ;
		  	 
		   frame2.setSize( 350, 800 ) ;
		   frame2.setResizable(true);		
	 		NetworkMappingManagerView linkMappingManagerView = new NetworkMappingManagerView( linkModelManager ) ;
	 		frame2.setContentPane(  linkMappingManagerView.getPanel()  ) ;

	 		frame2.setTitle( "the link mapping editor" ) ;

	 		frame2.pack(); 
	 		*/
	   	   }
	  
	  
	  	 synchronized public void toModel()
	  		  {
	  		   System.out.println( "networkcontrol.tomodel") ;
	  		   
	  		   if( behavior.isActive() ) 
	  				{
	 	  		   System.out.println( "behavior was already active, resetting start time") ;

	  				 behavior.resetTimer();
	  				 return ;
	  				}
	  		   System.out.println( "activating behavior") ;

	  	
	  		   behavior.branchGroup().detach() ;
	  		   branchGroup.addChild(  behavior.branchGroup()  ) ;

	  		  }
		 
		 public void initNetwork( int nodeCount )
			  {
			   System.out.println( "NetworkControl.initNetwork( nodeCount = " + nodeCount + " )" ) ;
			   initModelManagers() ;
				 initNodeMappingManager() ;
				 initLinkMappingManager() ;
				
				 showNodeMappingWindow() ;	
			 		
				 showLinkMappingWindow() ;	
				 
destroyNodeSet() ;
destroyLinkSet() ;
			
			 		 
			//   init( shape3DViewManager ) ;
			   
			   if( ( (double) (nodeCount * ( nodeCount - 1 ) ) )/ 2.0 >10000 ) 
					{
					 behavior.setMustAnimate( false ) ;
					}
			   else behavior.setMustAnimate( true ) ;
			   
			   System.out.println( "NetworkControl.initNetwork -> prepareNewTransformGroup()" ) ;

			   prepareNewTransformGroup() ;
			   System.out.println( "NetworkControl.initNetwork -> initNodes( nodeCount )" ) ;
			  
			   initNodes( nodeCount ) ;
			  
			   System.out.println( "NetworkControl.initNetwork -> initLinks()" ) ;

			   initLinks() ;
			   
			   System.out.println( "NetworkControl.initNetwork -> showTransformGroup()" ) ;

			   showTransformGroup() ;
			   
			  }
	
		 
		 private void destroyLinkSet()
		  {
		   Iterator it = linkControlSet.iterator() ;
		   
		   while( it.hasNext() )
				{
				 ( (LinkControlInterface) it.next() ).releaseEventListeners() ;				
				}		  
		   
		    linkControlSet = new ArrayList( 600 * 600 / 2 ) ;
		    
		   // linkModelManager.reInit() ;
		  }



	 private void destroyNodeSet()
		  {
		   Iterator it = nodeControlSet.iterator() ;
		   
		   while( it.hasNext() )
				{
				 ( (NodeControlInterface) it.next() ).releaseEventListeners() ;				
				}
		   
		    nodeControlSet = new ArrayList( 600 ) ;
		    
		 //   nodeModelManager.reInit() ;
		  }



	 void prepareNewTransformGroup()
			  {
			   if( toRemoveTransformGroup != null )
					branchGroup.removeChild( toRemoveTransformGroup ) ;
			   
			   transformGroup = new TransformGroup() ;
			   
			   
			    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	  	 
				  	 
			    transformGroup.setCapability(Group.ALLOW_CHILDREN_READ);
			    transformGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);	  	 
			    transformGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);	  
			    
			    toRemoveTransformGroup = new BranchGroup() ;
			    
			    toRemoveTransformGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
			    toRemoveTransformGroup.setCapability(Group.ALLOW_CHILDREN_READ);
			    toRemoveTransformGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
			    toRemoveTransformGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
			    
			    toRemoveTransformGroup.addChild( transformGroup ) ;
			    
			    initContentGroup() ;
			  }
		 
		 void showTransformGroup()
			  {
			   branchGroup.addChild( toRemoveTransformGroup ) ;
			  }
		 
		 
		 void initNodes( int nodeCount ) 
			  {
			   for( int i = 0 ; i < nodeCount ; i++ )
					{
					 NodeControlInterface nextNode = new NodeControl( getContentGroup(), shape3DViewManager, i ) ;
					
					 addNodeControl( nextNode ) ;
					 
					 
					}
			      
			  }
		 
		 void initLinks()
			  {
			   int nodeCount = nodeControlSet.size() ;
			   
			   Iterator aIterator = nodeControlSet.listIterator( 0 ) ;

			   int i = 0 ;
			   
			   NodeControlInterface a, b ;
			   
			   while( i < nodeCount - 1 ) 
					{
					 a = (NodeControlInterface) aIterator.next() ; i++ ;
					
					 for( Iterator bIterator = nodeControlSet.listIterator( i + 1 ) ; bIterator.hasNext() ;  )
						  {
						   b = (NodeControlInterface) bIterator.next() ;
						  
						   addLinkControl( new LinkControl( getContentGroup(), shape3DViewManager, a,  b ) ) ;
						  }
					 
					} 
/*			  
			   for( int i = 0 		; i < nodeCount - 1 ; i++ )
			   for( int j = i + 1 	; j < nodeCount 	; j++ )

					addLinkControl( new LinkControl( shape3DViewManager, (NodeControlInterface) nodeControlSet.get( i ),  (NodeControlInterface) nodeControlSet.get( j  ) ) ) ;
					*/
			  }
			  

	public BranchGroup getContentGroup()
		  {
		   return contentGroup ;
		  }



/*  
	  public void init( BrainAnalysisInterface brainAnalysis)
		  {
		   List channelSet = brainAnalysis.getChannelSet() ;
		   List channelRelationSet = brainAnalysis.getRelationSet() ;
		   
		   Iterator it = channelSet.iterator() ;
		   
		   while( it.hasNext() )
				{
			//	 NodeControl node = new NodeControl( (ChannelAnalysisInterface) it.next() ) ;
				
			//	 nodeControlSet.add(   ) ;
				
				
				}
		  }  
		  
	*/
	  
	  public NetworkControl( Shape3DViewManager shape3DViewManager )
	  	{
	  	 init( shape3DViewManager ) ;
	  	}
	  
	 
	  
	  /*
	  void loadChannelAnalyses( BrainAnalysisInterface source )
		   {
		   
		   }
	  
	  
	  void loadRelationAnalyses( BrainAnalysisInterface source )
		   {
		   
		   
		   }
	  */
	  
	  
	  
	  void loadVisualizationFile( String filename )
		{
		 File file = new File( filename ) ;
		
		 Scanner lineParser;
	 try
		  {
		  lineParser = new Scanner( new FileInputStream( file )  );
			 lineParser.useDelimiter( "\n" ) ;

		 Scanner nodeParser	;
		 
		 
		  Iterator nodeControlIterator = nodeControlSet.iterator() ;

		 while( nodeControlIterator.hasNext() )
			  {
			  
				 String nodeLine = lineParser.next() ;
				 
				 
				 NodeControl node = (NodeControl) nodeControlIterator.next() ;

				 
				 nodeParser = new Scanner( nodeLine ) ;
				 
				 nodeParser.useDelimiter( "\t" ) ;	 
				 

				 
				 String label = nodeParser.next();				 
				 
				 float x = (float) nodeParser.nextDouble() ;
				 float y = (float) nodeParser.nextDouble() ;
				 float z =  (float) nodeParser.nextDouble() ;
				 
				 float r =  (float) nodeParser.nextDouble() ;
				 float g =  (float) nodeParser.nextDouble() ;
				 float b =  (float) nodeParser.nextDouble() ;
				 
				 float size =  (float) nodeParser.nextDouble() ;
				 float shape =  (float) nodeParser.nextDouble() ;
				 
				 
				 
				    
				 node.getModel().property( "x", new Constant( x ) ) ;
				 node.getModel().property( "y", new Constant( y ) ) ;
				 node.getModel().property( "z", new Constant( z ) ) ;

				 node.getModel().property( "red", new Constant( r ) ) ;
				 node.getModel().property( "green", new Constant( g ) ) ;
				 node.getModel().property( "blue", new Constant( b ) ) ;

				 node.getModel().property( "size", new Constant( size ) ) ;
				 node.getModel().property( "shape", new Constant( shape ) ) ;
				    
				 node.getModel().setDescriptionSource( new ConstantString( label )  ) ;
				 
				 // node.set( new Vector3f( x, y, z), r, g, b, radius, shape ) ;
	/*			 
				 node.getModel().setXSource( new Constant( x ) ) ;				 
				 node.getModel().setYSource( new Constant( y ) ) ;
				 node.getModel().setZSource( new Constant( z ) ) ;
				 
				 node.getModel().setRSource( new Constant( r ) ) ;
				 node.getModel().setGSource( new Constant( g ) ) ;
				 node.getModel().setBSource( new Constant( b ) ) ;
				 
				 node.getModel().setRadiusSource( new Constant( radius ) ) ;
				 node.getModel().setMorphSource( new Constant( morph ) ) ;
				 */
				 System.out.println( "NetworkControl :: loadVisualizationFile( String filename ) not completely implemented yet.") ;
				 System.exit( 0 ) ;
			  }

		  } catch( FileNotFoundException e )
		  {
		  e.printStackTrace();
		  }
		}
	  
	  void generateNodeTestSet( int nodeCount )
		{
		 generateNodeSet( nodeCount ) ; 
			  
		// randomNetworkChange() ;
		}
	  
	  
	  void generateRandomConnections()
		 {
		 for( int i = 0 	; i < nodeControlSet.size() - 1 ; i ++ )
		 for( int j = i + 1 ; j < nodeControlSet.size()     ; j ++ ) 

			  {
			   if( Math.random() < .2 )
					
					addLinkControl( new LinkControl( (NodeControl) nodeControlSet.get(  i  ), (NodeControl) nodeControlSet.get(  j ) ) ) ;
			   
			  
			  }
		   
		 }
	  
	  

	  
	  void testBehavior()
		   {
		    new TestBehavior( this ) ;
		   
		   
		   }
	  
	  
	  void addNodeControl( NodeControlInterface node )
		   {  	 	    
		    nodeControlSet.add( node ) ;
			  
		    // transformGroup.addChild( node.getView().getBranchGroup() ) ;

		 //   shape3DViewManager.addShapeView( node.getView() ) ;
		    
		    if( this.nodeModelManager != null )
		    	 {
		    	  nodeModelManager.addModel( node.getModel() ) ;
		    	 
		    	 }
		    
		    node.init() ;
		    
		   }

	  
	  
	  void addLinkControl( LinkControlInterface link )
		   {  	 	    
		    linkControlSet.add( link ) ;
			
		 //   System.gc(); System.gc(); System.gc(); System.gc(); System.gc(); System.gc(); System.gc(); System.gc(); System.gc(); System.gc();
		    
		    
		    if( linkControlSet.size()  % 1000 == 0 )
		    	 {
		    	 System.gc();
		    	  System.out.println( "link count = " + linkControlSet.size() + " total memory " + Runtime.getRuntime().totalMemory() + " free = " + Runtime.getRuntime().freeMemory() + " used " + ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() ) );
		    	 }
		 
		 
		    
		    transformGroup.addChild( link.getView().getBranchGroup() ) ;

		//    shape3DViewManager.addShapeView( link.getView() ) ;
		    
		    if( this.linkModelManager != null )
		    	 
		    	 linkModelManager.addModel( link.getModel() ) ;
		    
		   }
	  
	 public BranchGroup getBranchGroup()
		  {
		   return branchGroup ;
		  }
/*
	 public void setEveryNodeModel( NodeModelFileInterface file)
		  {
		  
		   if( nodeControlSet.size() == 0 )
				{
				 int requiredNodeControlIncrease = file.getModelCount() ;
				
				 while( requiredNodeControlIncrease-- > 0 )
					  {
					   nodeControlSet.add( new NodeControl() ) ;
					  
					  
					  }
				
				}
		  
		   Iterator networkNodeIt = nodeControlSet.iterator() ;

		   
		   int i = 0 ;
		   while( networkNodeIt.hasNext() ) 
				{
				 ( (NodeControl) networkNodeIt.next() ).getModel().init( file.getModel( i ) ) ;
				
				  i++ ;
				}
				
		  }
*/
	 
	 
	 public void generateNodeSet( int nodeCount )
		  {
    	   for( int i = 0 ; i < nodeCount ; i++ )
    			{
    			 NodeControlInterface nextNode = new NodeControl( i ) ;
    			
    			 addNodeControl( nextNode ) ;
    			}
		  }



	 public void load( NodeDataSetInterface loaded)
		  {
		   nodeModelManager.load( loaded ) ;		  
		  }

	 public void load( LinkDataSetInterface loaded )
		  {
		   linkModelManager.load( loaded ) ;		  
		  }

	 public void adaptViewToModel( float percentageIncrease)
		  {
		   Iterator nodeIterator = nodeControlSet.iterator() ;
		   
		   NodeControlInterface node ;
		   
		   while( nodeIterator.hasNext() )
				{
				 node = (NodeControlInterface) nodeIterator.next() ;
				 
				 node.getView().toModel( percentageIncrease ) ;
				}
		   
		   Iterator linkIterator = linkControlSet.iterator() ;
		   
		   LinkControlInterface link ;
		   
		   while( linkIterator.hasNext() )
				{
				 link = (LinkControlInterface) linkIterator.next() ;
				 
				 link.getView().toModel( percentageIncrease ) ;
				}
		  }

	 public void modelChanged()
		  {
		   toModel() ;
		  }



	 public void applyDefaultMappingSet()
		  {
		   this.nodeMappingManagerView.initXYZ() ;
		  }

	 }


 class TestBehavior extends Behavior 
 	{
 	 NetworkControl network ;
 	 
 	WakeupCriterion nextNetworkChange ;
 	 
 	 BranchGroup branchGroup ;
 	 
 	 
 	 
 	 TestBehavior( NetworkControl network )
 	 	{
		   nextNetworkChange = new WakeupOnElapsedTime( 5000 ) ;

 	 	 this.network = network ;
 	 	 
 	 	branchGroup = new BranchGroup() ;
 	   
	    branchGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
	    branchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	    branchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
	    branchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	    
	    
 	 	branchGroup.addChild( this ) ; 
	    
 	 	 network.getBranchGroup().addChild( branchGroup ) ;
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
		   
//		   network.randomNetworkChange() ;!!!!!!!!!!!!!!!!!!!!!!!!


		   System.out.println( "NETWORK TEST BEHAVIOR INIT" ) ;
		   
		   wakeupOn( nextNetworkChange ) ;


		  }

	 @Override
	 public void processStimulus( Enumeration arg0)
		  {
		  // network.randomNetworkChange() ;
		   
		   System.out.println( "NETWORK CHANGE" ) ;

//		   network.randomNetworkChange() ;!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		   wakeupOn( nextNetworkChange ) ;
		  }
 	 
 
 
 	}
 
