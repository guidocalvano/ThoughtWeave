package Graphics3D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Vector;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;

import com.sun.j3d.loaders.LoaderBase.* ;
import com.sun.j3d.loaders.Scene;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.picking.*;

import Graphics3D.Animation.*;

import Graphics3D.GuiComponent3D.*;
import Graphics3D.Animation.*;



public class Shape3DView implements Shape3DViewInterface
	 {
	 
	  String objectFilename ;
	  String appearanceFileName ;
	 
	  Leaf shape ;
	  
	  BranchGroup parent ;
	  
	  
	  TransformGroup transformGroup ;
	  
	  BranchGroup branchGroup ;
	  
	  BranchGroup isVisibleGroup ;


	  BranchGroup childGroup ;

	  
	  Vector mouseListenerAt ;
	  
	  
	  int morphCount ;
	  
	  AnimationManagerInterface animationManager ;
	  
	  
	  Text3DViewInterface overlayText ;
	  
	  MaterialInterface material ;
	  
	  boolean isVisible ;
	 private SpatialInterface geometry;
	 	 
	  String[] shapeFileSet ;
	  
	  protected boolean initShapeComplete ;
	  
	  Shape3DViewManagerInterface shape3DViewManager ;
	  
	  Shape3DView()
	  	{
	  	}
	  
	  public Shape3DViewManagerInterface getManager() { return shape3DViewManager ; } 
	  
	  
	  public Shape3DView( String objectFilename, boolean isVisible )
	  	{ 
	  	 System.out.println( "wrong constructor" ) ;
	  	 System.exit( 0 ) ;
	  	
	  	 init( objectFilename, isVisible ) ;
	  	
	  	}
	  
	  protected Shape3DView( String[] objectFilenameSet, boolean isVisible )
	  	{ 
//	  	 System.out.println( System.getProperty("user.dir") ) ;
	  	 System.out.println( "wrong constructor" ) ;
	  	 System.exit( 0 ) ;
	  	
	  	 init( objectFilenameSet, isVisible ) ;	  	
	  	}
	   
	  
	  public Shape3DView( BranchGroup parent, Shape3DViewManagerInterface shape3DViewManager, String objectFilename, boolean isVisible )
		  	{ 
		  	// init( objectFilename, isVisible ) ;
		  	 this.parent = parent ;
		  	 
		  	 System.out.println( "shape constructor" ) ;

		  	 initShapeComplete = false ;
		  	
		  	 this.isVisible = isVisible ;
		  	 this.shapeFileSet = new String[] { objectFilename } ;
		  	 
		  	 this.shape3DViewManager = shape3DViewManager ;
		  	 
		  	 initWithoutShape() ;

		     System.out.println( "file name" + objectFilename  ) ; 
			    
			 if( !objectFilename.startsWith( "brain" ) )
			    	 setMaterialInterface( Shape3DFactory.getShapeMaterial() ) ;	
			 
		  	 setSpatialInterface( Shape3DFactory.getShapeSpace() ) ;
		  	 

		//  	 initShape() ;

		  	}
		  
	  protected Shape3DView( BranchGroup parent, Shape3DViewManagerInterface shape3DViewManager, String[] objectFilenameSet, boolean isVisible )
		  	{ 
		  	 this.parent = parent ;
		  	 System.out.println( "morph constructor" ) ;

		  	 // init( objectFilenameSet, isVisible ) ;
		  	initShapeComplete = false ;
		  	
		  	 this.isVisible = isVisible ;
		  	 this.shapeFileSet = objectFilenameSet ;
		  	 
		  	 this.shape3DViewManager = shape3DViewManager ;
		  	
		  	 initWithoutShape() ;

		  	 setMaterialInterface( Shape3DFactory.getMorphMaterial() ) ;
		  	 setSpatialInterface( Shape3DFactory.getMorphSpace() ) ;

		  	// initShape() ;
		  	}
	  
	  public void initShape()
		   {
		    if( initShapeComplete ) return ;
		    
		    initShapeComplete = true ;
		   
		  	 System.out.println( "shape init shape" ) ;

			 initBranchGroup() ;

			 initIsVisibleGroup() ;

			 
			 initTransformGroup() ;
			 
			 initChildGroup() ;
			
			if( shapeFileSet.length > 1 )
				 {
				  System.out.println( "initshape as morph") ;

				  shape = Shape3DFactory.createMorph( shapeFileSet ) ;
				 
				  getMorph().setCapability( Morph.ALLOW_WEIGHTS_READ);
				  getMorph().setCapability(Morph.ALLOW_WEIGHTS_WRITE); 
				  
				//  getMorph().setCapability(Morph.ALLOW_GEOMETRY_ARRAY_READ);
				  
				  getMorph().setCapability( Morph.ALLOW_APPEARANCE_READ ) ;
				  getMorph().setCapability( Morph.ALLOW_APPEARANCE_WRITE ) ;
				  
				  getMorph().setCapability( Node.ENABLE_PICK_REPORTING );
				  
				  // PickTool.setCapabilities( getMorph(), PickTool.INTERSECT_FULL ) ;
				  
				  

				  
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_READ ) ;
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_WRITE ) ;
				  
				  /*
				  TransparencyAttributes ta = new TransparencyAttributes( );
				  ta.setTransparency( 0.0f );
				  ta.setTransparencyMode( TransparencyAttributes.BLENDED );
				  
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_READ ) ;
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_WRITE ) ;
				  getMorph().getAppearance().setTransparencyAttributes( ta );
				  
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ) ;
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE) ;
				  
				  getMorph().getAppearance().setColoringAttributes( new ColoringAttributes( 1f, 1f, 1f, ColoringAttributes.NICEST ) ) ;
				  
				  getMorph().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_READ ) ;
				  getMorph().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_WRITE ) ;
				  
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_READ  ) ;
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE ) ;
				  
			//	  getMorph().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_READ ) ;
			//	  getMorph().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_WRITE ) ;
			 * 
			 * 
			 */
				 }
			

			else
				 {
				  System.out.println( "initshape as shape") ;
				 
				  shape = Shape3DFactory.createShape( shapeFileSet[ 0 ] ) ;

				 
				  getShape().setPickable( true ) ;
				 
				//  getShape().setCapability( Shape3D.ALLOW_GEOMETRY_READ ) ; ;

				//  getShape().getGeometry().setCapability( Geometry.ALLOW_INTERSECT ) ;
				  
				  getShape().setCapability( Shape3D.ALLOW_APPEARANCE_READ ) ;
				  getShape().setCapability( Shape3D.ALLOW_APPEARANCE_WRITE ) ;
				  
				  
				  getShape().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_READ ) ;
				  getShape().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_WRITE ) ;
				
				  TransparencyAttributes ta = new TransparencyAttributes( );
				  ta.setTransparency( 0.0f );
				  ta.setTransparencyMode( TransparencyAttributes.BLENDED );
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_READ ) ;
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_WRITE ) ;
				  
				  getShape().getAppearance().setTransparencyAttributes( ta );
				  
				  getShape().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ) ;
				  getShape().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE) ;

				  getShape().getAppearance().setColoringAttributes( new ColoringAttributes( 1f, 1f, 1f, ColoringAttributes.FASTEST ) ) ;
				  
				  getShape().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_READ ) ;
				  getShape().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_WRITE ) ;
				  

				  getShape().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_READ  ) ;
				  getShape().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE ) ;
				  
	//			  getShape().setCapability( Node.ENABLE_PICK_REPORTING );
				  
		//		  PickTool.setCapabilities( getShape(), PickTool.INTERSECT_TEST ) ;
			/*	  
				  for (Enumeration e = getShape().getAllGeometries(); e.hasMoreElements();) 
					   {
					    Geometry g = (Geometry)e.nextElement();

					    g.setCapability(g.ALLOW_INTERSECT);
					   }

*/
				  
				  
				  if( getShape().getAppearance().getMaterial() != null )
					   {
					    getShape().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_READ ) ;
					    getShape().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_WRITE ) ;
					   }
				  
					
				 }
			
			
			
			try {
			if( shape.getClass() == Class.forName( "javax.media.j3d.Morph" ) )
				 morphCount = ( (Morph) shape ).getWeights().length ;
			} catch( Exception e ) {}
			
			if( shape3DViewManager != null )
			shape3DViewManager.addShapeView( this ) ;
			
			childGroup.addChild( shape ) ;
			
			if( material != null )
				 material.init(  this ) ;
			
			geometry.init(  this  ) ;
			
			parent.addChild( branchGroup ) ;
			
			System.out.println( "end initshape" ) ;
		   }
	  
	  
	  void initWithoutShape()
		{
		 animationManager = new AnimationManager() ;
		   
		 mouseListenerAt = new Vector() ;
		 

		}
	  
	 
	  private void initBranchGroup()
		   {
		    System.out.println( "init branchgroup") ;
		   
		   	branchGroup = new BranchGroup() ;
  		   
  		 
  		   
  		   	branchGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
  		   	branchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
  		 	branchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
  			branchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
  		
  		
		   }
	  
	  
	  private void initIsVisibleGroup()
		   {	
		   System.out.println( "init isvisible") ;
		    isVisibleGroup = new BranchGroup() ;
	  	  	
	  	  	
	  		isVisibleGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
	  		isVisibleGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	  		isVisibleGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
	  		isVisibleGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);	
	
	  	  	

		   }
	  
	  public BranchGroup getIsVisibleGroup() { return isVisibleGroup ; } 
	  
	  private void initTransformGroup()
		   {
		   System.out.println( "init transform") ;
		    transformGroup = new TransformGroup() ;
			  
		  	 
  		    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  		    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	  	 
  			  	 
  	  	    transformGroup.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);


	  	  	isVisibleGroup.addChild( transformGroup ) ;

		   }
	  
	  
	  private void initChildGroup()
		   {
		   System.out.println( "init child") ;
 		   	childGroup = new BranchGroup() ;
  		   
 		    childGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
 		 	childGroup.setCapability(Group.ALLOW_CHILDREN_READ);
 			childGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
 			childGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
 			


 		  	transformGroup.addChild( childGroup ) ;
		   }
	 
	  
	  void init( String objectFilename, boolean isVisible )
		   {
		    shapeFileSet = new String[] { objectFilename } ;
		   
		   Shape3DFactory.createShape( this, objectFilename , isVisible ) ;
		 //   init( Shape3DFactory.createShape( objectFilename ), isVisible ) ;
		   
		   }
	  
	  
	  void init( String[] objectFilenameSet, boolean isVisible )
		{
		 shapeFileSet = objectFilenameSet ;
		Shape3DFactory.createMorph( this, objectFilenameSet, isVisible ) ;
		
		// Morph morph = Shape3DFactory.createMorph( objectFilenameSet ) ;
		 /*
		 double[] weightSet = new double[objectFilenameSet.length] ;
		 
		 
		 for( int i = 0 ; i < weightSet.length ; i++ )
			{
			 weightSet[ i ] = 0.0 ;  
			}
		 
		 
		 weightSet [ 0 ] = 1.0 ;
		 
	//	 morph.setCapability(Morph.ALLOW_WEIGHTS_READ);
	//	 morph.setCapability(Morph.ALLOW_WEIGHTS_WRITE);
		// morph.setCapability(Morph.ALLOW_GEOMETRY_ARRAY_READ);

		 morph.setWeights( weightSet ) ;   
		 
		 
		 init( morph, isVisible ) ;
		 */
		   
		}
	  
	  
	  
	  void init( boolean isVisible ) 
		   {
		   animationManager = new AnimationManager() ;
		   
		  	mouseListenerAt = new Vector() ;

	  		   transformGroup = new TransformGroup() ;
				  
			  	 
	  		   transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	  		   transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	  	 
	  			  	 
	  	  	 transformGroup.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);

	  	  	isVisibleGroup = new BranchGroup() ;
	  	  	
	  	  	isVisibleGroup.addChild( transformGroup ) ;
	  	  	
	  		isVisibleGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
	  		isVisibleGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	  		isVisibleGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
	  		isVisibleGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	  		
	  		   
	  		   branchGroup = new BranchGroup() ;
	  		   
	  		   if( isVisible)
	  				branchGroup.addChild( isVisibleGroup ) ;
	  		   
	  		   this.isVisible = isVisible ;
	  		   
	  		   branchGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
	  		 branchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	  		branchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
	  		branchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	  		
	  		
	 		   childGroup = new BranchGroup() ;
	  		   
	 		 childGroup.setCapability(BranchGroup.ALLOW_DETACH);	  
	 		childGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	 		childGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);
	 		childGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);

	 		  transformGroup.addChild( childGroup ) ;

		   
		   }
	  
	  public void setMaterialInterface( MaterialInterface material )
		   {
		    this.material = material ;
		    
		   // material.init( this ) ;
		   }
	  
	  public void setSpatialInterface( SpatialInterface geometry )
		   {
		    this.geometry = geometry ;
		    
		   }
	  
	  public SpatialInterface getSpatialInterface()
		   {
		    return geometry ;
		   }
	
	  private void loadShape()
		   {
		    if( shapeFileSet.length == 1 )
		    	 {
		    	 }
		    else
		    	 {
		    	 
		    	 }
		   }
	  
	  public void init( Leaf shape, boolean isVisible )
		   {
		  	init( isVisible ) ;

		  	this.shape = shape ;
			transformGroup.addChild( shape ) ;
			
			if( getMorph() != null)
				 {
				  getMorph().setCapability( Morph.ALLOW_WEIGHTS_READ);
				  getMorph().setCapability(Morph.ALLOW_WEIGHTS_WRITE); 
				  
				//  getMorph().setCapability(Morph.ALLOW_GEOMETRY_ARRAY_READ);
				  
				  getMorph().setCapability( Morph.ALLOW_APPEARANCE_READ ) ;
				  getMorph().setCapability( Morph.ALLOW_APPEARANCE_WRITE ) ;
				  
				  getMorph().setCapability( Node.ENABLE_PICK_REPORTING );
				  
				  // PickTool.setCapabilities( getMorph(), PickTool.INTERSECT_FULL ) ;
				  
				  

				  
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_READ ) ;
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_WRITE ) ;
				  
				  TransparencyAttributes ta = new TransparencyAttributes( );
				  ta.setTransparency( 0.0f );
				  ta.setTransparencyMode( TransparencyAttributes.BLENDED );
				  
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_READ ) ;
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_WRITE ) ;
				  getMorph().getAppearance().setTransparencyAttributes( ta );
				  
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ) ;
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE) ;
				  
				  getMorph().getAppearance().setColoringAttributes( new ColoringAttributes( 1f, 1f, 1f, ColoringAttributes.NICEST ) ) ;
				  
				  getMorph().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_READ ) ;
				  getMorph().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_WRITE ) ;
				  
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_READ  ) ;
				  getMorph().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE ) ;
				  
			//	  getMorph().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_READ ) ;
			//	  getMorph().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_WRITE ) ;
				 }
			

			if( getShape() != null)
				 {
				  getShape().setPickable( true ) ;
				 
				//  getShape().setCapability( Shape3D.ALLOW_GEOMETRY_READ ) ; ;

				//  getShape().getGeometry().setCapability( Geometry.ALLOW_INTERSECT ) ;
				  
				  getShape().setCapability( Shape3D.ALLOW_APPEARANCE_READ ) ;
				  getShape().setCapability( Shape3D.ALLOW_APPEARANCE_WRITE ) ;
				  
				  
				  getShape().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_READ ) ;
				  getShape().getAppearance().setCapability( Appearance.ALLOW_MATERIAL_WRITE ) ;
				
				  TransparencyAttributes ta = new TransparencyAttributes( );
				  ta.setTransparency( 0.0f );
				  ta.setTransparencyMode( TransparencyAttributes.BLENDED );
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_READ ) ;
				  ta.setCapability( TransparencyAttributes.ALLOW_VALUE_WRITE ) ;
				  
				  getShape().getAppearance().setTransparencyAttributes( ta );
				  
				  getShape().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ) ;
				  getShape().getAppearance().setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE) ;

				  getShape().getAppearance().setColoringAttributes( new ColoringAttributes( 1f, 1f, 1f, ColoringAttributes.FASTEST ) ) ;
				  
				  getShape().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_READ ) ;
				  getShape().getAppearance().getColoringAttributes().setCapability( ColoringAttributes.ALLOW_COLOR_WRITE ) ;
				  

				  getShape().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_READ  ) ;
				  getShape().getAppearance().setCapability( Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE ) ;
				  
	//			  getShape().setCapability( Node.ENABLE_PICK_REPORTING );
				  
		//		  PickTool.setCapabilities( getShape(), PickTool.INTERSECT_TEST ) ;
			/*	  
				  for (Enumeration e = getShape().getAllGeometries(); e.hasMoreElements();) 
					   {
					    Geometry g = (Geometry)e.nextElement();

					    g.setCapability(g.ALLOW_INTERSECT);
					   }

*/
				  
				  
				  if( getShape().getAppearance().getMaterial() != null )
					   {
					    getShape().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_READ ) ;
					    getShape().getAppearance().getMaterial().setCapability( Material.ALLOW_COMPONENT_WRITE ) ;
					   }
				 }
			
			
			
			try {
			if( shape.getClass() == Class.forName( "javax.media.j3d.Morph" ) )
				 morphCount = ( (Morph) shape ).getWeights().length ;
			} catch( Exception e ) {}
			
			
		   }

	  Shape3DView( Leaf shape, boolean isVisible )
	  		{
	  		 init( shape, isVisible ) ;
	  		}
	  
	
	  

	
	  
	   public Shape3D getShape()
			{
			try {
			if( shape.getClass() == Class.forName( "javax.media.j3d.Shape3D" ) )
				 
				 return (Shape3D) shape ;
			} catch( Exception e ) 
				 { return null ; }
			
			
			
			

				  
			 return null ;
			}
	   
	   public Morph getMorph()
			{
			
			try {
			if( shape.getClass() == Class.forName( "javax.media.j3d.Morph" ) )
				 
				 return (Morph) shape ;
			} catch( Exception e ) 
				 { return null ; }
			
			

				  
			 return null ;
			}
			
	   
	   public int getMorphCount() { return morphCount ; }
	  
	  
	   Scene tryObjectLoad( String filename )
		{
	  	 ObjectFile f = new ObjectFile() 	;

	  	 Scene 		scene = null				;
	  	 
	  	 
	  	 try
	  		 {
	  		  scene = f.load( filename ) 		;
	  		 }
                		 catch( FileNotFoundException e ) 
                			 {
                			  System.err.println(e);
                			  System.exit(1);
                			 }
                		 catch( ParsingErrorException e ) 
                			 {
                			  System.err.println(e);
                			  System.exit(1);
                			 }
                		 catch( IncorrectFormatException e )
                			 {
                			  System.err.println( e ) ;
                			  System.exit(1);
                			 }
		 
		 return scene ;
		}
	  
	  public TransformGroup getTransformGroup()  { return transformGroup ; }

	  public BranchGroup getBranchGroup()  { return branchGroup ; }
	  
	  public BranchGroup getChildGroup()  { return childGroup ; }
	  
	  public Leaf getLeaf() { return shape ; } 
	  
	  
	 public void mouseClicked( MouseEvent e)
		  {
		   System.out.println( "3dview clicked by jove and size = " + mouseListenerAt.size() ) ; 
		  
		   for( int i = 0 ; i < mouseListenerAt.size() ; i++ )
				{
				   System.out.println( "invoking handler" ) ; 

				 ( (MouseListener) mouseListenerAt.get( i ) ).mouseClicked( e ) ;
				}
		   System.out.println( "handling complete"  ) ; 

		  }

	 public void mouseEntered( MouseEvent e)
		  {
		   for( int i = 0 ; i < mouseListenerAt.size() ; i++ )
				
				 ( (MouseListener) mouseListenerAt.get( i ) ).mouseEntered( e ) ;		  
		  }

	 public void mouseExited( MouseEvent e)
		  {
		   for( int i = 0 ; i < mouseListenerAt.size() ; i++ )
				
				 ( (MouseListener) mouseListenerAt.get( i ) ).mouseExited( e ) ;		  
		  }

	 public void mousePressed( MouseEvent e)
		  {
		   for( int i = 0 ; i < mouseListenerAt.size() ; i++ )
				
				 ( (MouseListener) mouseListenerAt.get( i ) ).mousePressed( e ) ;		  
		  }

	 public void mouseReleased( MouseEvent e)
		  {
		   for( int i = 0 ; i < mouseListenerAt.size() ; i++ )
				
				 ( (MouseListener) mouseListenerAt.get( i ) ).mouseReleased( e ) ;		  
		  }

	 public Appearance getAppearance()
		  {
		   if( getMorph(  ) != null )
				return getMorph(  ).getAppearance() ;
		   
		   if( getShape(  ) != null )
				return getShape(  ).getAppearance() ;

		   // initShape() ;
		   
		   
		   return null ;
		   
		   
		  
		  }
	 
	 
	 public void setAppearance( Appearance appearance )
		  {
		   if( getMorph(  ) != null )
				 getMorph(  ).setAppearance( appearance ) ;
		   
		   if( getShape(  ) != null )
				 getShape(  ).setAppearance( appearance ) ;

		  
		  }
	 
	 public void setViewProperty( String property, double value, Alpha time )
		  {
		   animationManager.stop( property ) ;
		   animationManager.animateProperty( property, animationTo( property, value, time ) ) ;
		  	  
		  }
	 
	 public void setVisible( double isVisible )
		  {
		   if( this.isVisible == ( isVisible > .5 ) ) return ;
		   
		   this.isVisible = ( isVisible > .5 ) ;

		   
		   if( !this.isVisible && isVisibleGroup != null )
				{
				
				
				 isVisibleGroup.detach() ;				
				}
				 
		  
		   if( this.isVisible )
				{
				 if( shape == null ) initShape() ;
				 branchGroup.addChild( isVisibleGroup ) ;				
				}
		   
		  }
	 
	 public void setVisible( boolean isVisible )
		  {
		   if(isVisible == this.isVisible) return ;
		   
		   this.isVisible = isVisible ;

		   
		   if( !this.isVisible )
				{				 
				 isVisibleGroup.detach() ;				
				}
		  
		   if( this.isVisible )
				{
				 if( shape == null ) initShape() ;
				 branchGroup.addChild( isVisibleGroup ) ;				
				}
		   
		  }
	 
	 public double getVisible() { return isVisible ? 1.0 : 0.0 ; } 

	 
	 public double isVisible( double isVisible ) { return isVisibleGroup.getParent() != null ? 1.0 : 0.0 ; }

	 
	 public void setX( double x )
		  {
		   getSpatialInterface().setX( x ) ;
		  
		  }
	 
	 public double getX()
		  {
		   return getSpatialInterface().getX() ; 
		  }
	 
	 public void setY( double y )
		  {
		   getSpatialInterface().setY( y ) ;

		  
		  }
	 
	 
	 public double getY()
		  {
		   return getSpatialInterface().getY() ;
		  }
	 
	 public void setZ( double z )
		  {
		   getSpatialInterface().setZ( z ) ;

		  
		  }
	 	 
	 public double getZ()
		  {
		   return getSpatialInterface().getZ() ;
		  }
	 	 
	 public void setScale( double scale )
		  {
		   geometry.setScale( scale ) ;
		  
		  }
	 
	 public double getScale()
		  {
		   Transform3D transform = new Transform3D() ;
		   
		   getTransformGroup().getTransform( transform ) ;
		   
		   Vector3d scale =  new Vector3d() ;
		   
		   transform.getScale( scale ) ;
		  
		   return scale.x ;
		  }
	 
	 /*
	 
	 public void setCylinderRadius( double radius )
		  {
		   if( radius == 0.0 ) radius = Double.MIN_VALUE ;

		  
		   Transform3D transform = new Transform3D() ;
		   
		   getTransformGroup().getTransform( transform ) ;
		   
		   Vector3d scaleVector = new Vector3d() ;
		   
		   transform.getScale( scaleVector ) ;
		   
		   scaleVector.x = radius ;
		   
		   scaleVector.z = radius ;
 		   
		   transform.setScale( scaleVector ) ;
		    		   
		   getTransformGroup().setTransform( transform ) ;
		  
		  }
	 public double getCylinderRadius() 
		  { 
    	   Transform3D transform = new Transform3D() ;
    	   
    	   getTransformGroup().getTransform( transform ) ;
    	   
    	   Vector3d scale =  new Vector3d() ;
    	   
    	   transform.getScale( scale ) ;
    	  
    	   return scale.x ;
    	  }
	*/
	 
	 public void setCylinderRadius( double radius )
		  {
		   geometry.setCylinderRadius( radius ) ;
		  }
	 public double getCylinderRadius() 
 	
		  { return geometry.getCylinderRadius() ; }
	 
	 public void setShapeValue( double shape )
		  {
		  // getMorph().setWeights( new double[] { 1.0 - shape, shape } ) ;
		   geometry.setShapeValue( shape ) ;
		  }
	 
	 public double getShapeValue() 
		  {
		   return geometry.getShapeValue() ;
		  }
/*	 
	 
	 
	 public void setRed( double red )
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   
		   getAppearance().getColoringAttributes().getColor( color ) ;
		   
		   color.x = (float) red ;
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   
		   getAppearance().getColoringAttributes().setColor( color ) ;

		  }
	 
	 
	 public double getRed()
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   
		   getAppearance().getColoringAttributes().getColor( color ) ;
		   
		   return color.x  ;		   
		  }

	 
	 public void setGreen( double green )
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   getAppearance().getColoringAttributes().getColor( color ) ;
  
		   color.y = (float) green ;
		   
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   getAppearance().getColoringAttributes().setColor( color ) ;

		  }
	 
	 
	 
	 public double getGreen()
		  {
		   Color3f color = new Color3f() ;
		  
		 //  getAppearance().getMaterial().getDiffuseColor( color ) ;
		   getAppearance().getColoringAttributes().getColor( color ) ;
   
		   return color.y  ;		   
		  }

	 
	 
	 public void setBlue( double blue )
		  {
		   Color3f color = new Color3f() ;
		  
		   getAppearance().getColoringAttributes().getColor( color ) ;
		   
		   color.z = (float) blue ;
		   
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   getAppearance().getColoringAttributes().setColor( color ) ;
		  }
	 
	 
	 public double getBlue()
		  {
		   Color3f color = new Color3f() ;
		  
		  // getAppearance().getMaterial().getDiffuseColor( color ) ;
		   getAppearance().getColoringAttributes().getColor( color ) ;

		   return color.z  ;		   
		  }

	 
	 
	 
	 public void setTransparency( double transparency )
		  {
		  
		   TransparencyAttributes transparencyAttributes = getAppearance().getTransparencyAttributes() ;
		   
		   transparencyAttributes.setTransparency( 1f - (float) transparency ) ;
		   
		   getAppearance().setTransparencyAttributes( transparencyAttributes ) ;
		  }
	 
	 
	 public double getTransparency()
		  {
		  
		   TransparencyAttributes transparencyAttributes = getAppearance().getTransparencyAttributes() ;
		   
		   return 1.0 - transparencyAttributes.getTransparency() ;
		   
		  }
	 
	 */
	 
	 private InterruptableBehavior animationTo( String property, double value,
			   Alpha time)
		  {
		  
		   
		   if( "x".equals( property ) )
				
				return new ToXBehavior( this, value, time ) ;
				
		   if( "y".equals( property ) )
				
				return new ToYBehavior( this, value, time ) ;
		   
		   if( "z".equals( property ) )
				
				return new ToZBehavior( this, value, time ) ;
		   
		   


		   
		   
		   if( "red".equals( property ) )
				
				return new ToRedBehavior( this, value, time ) ;
		   
		   if( "green".equals( property ) )
				
				return new ToGreenBehavior( this, value, time ) ;
		   
		   if( "blue".equals( property ) )
				
				return new ToBlueBehavior( this, value, time ) ;
		   
	
		   
		   if( "size".equals( property ) )
				
				return new ToSizeBehavior( this, value, time ) ;
		   
		   
		   if( "thickness".equals( property ) )
				
				return new ToCylinderRadiusBehavior( this, value, time ) ;
		   
		   if( "shape".equals( property ) )
				
				return new ToShapeBehavior( this, value, time ) ;
		   
		   if( "transparency".equals( property ) )
				
				return new ToTransparencyBehavior( this, value, time ) ;
		   
		  
		   return null;
		  }

	 public void setCameraControl( CameraControl cameraControl)
		  {
		  
		  }
	 
	  public void transformTo( Transform3D transform, Alpha time ) 
		   {
		   
		   
		   }
	  
	  public void changeColor( Color3f targetColor, Alpha time )
		   {
		   
		   
		   }
	  	  
	  public void changeShape( double nextMorphValue, Alpha time )
		   {
		   
		   
		   }
	  	  
	  public void setOverlayText( String text, Alpha time )
		   {
		   
		   
		   }
	  
	  public void removeOverlayText( Alpha time )
		   {
		   
		   }

	 public void addMouseListener( MouseListener listener)
		  {
		   
		   mouseListenerAt.add( listener ) ;
		  }

	 public AnimationManagerInterface getAnimationManager()
		  {
		  // TODO Auto-generated method stub
		  return animationManager ;
		  }

	 public double getBlue()
		  {
		  // TODO Auto-generated method stub
		  return material.getBlue() ;
		  }

	 public double getGreen()
		  {
		  // TODO Auto-generated method stub
		  return material.getGreen() ;
		  }

	 public double getRed()
		  {
		  // TODO Auto-generated method stub
		  return material.getRed() ;
		  }

	 public double getTransparency()
		  {
		  // TODO Auto-generated method stub
		  return material.getTransparency() ;
		  }

	 public void setBlue( double value)
		  {
		   material.setBlue( value ) ;
		  }

	 public void setGreen( double value)
		  {
		   material.setGreen( value ) ;
		  }

	 public void setRed( double value)
		  {
		   if( material == null ) System .out.println( "material interface == null" ) ;
		  
		   material.setRed( value ) ;
		   
		  }

	 public void setTransparency( double value)
		  {
		   if( material != null )
				material.setTransparency( value ) ;
		   
		   else
				{
				 if( shape == null ) initShape() ;
				 getShape().getAppearance().getTransparencyAttributes().setTransparency( (float) value ) ;
				}
		  }




	 public void releaseEventListeners()
		  {
		   shape3DViewManager.removeShape3DView( this ) ;		  
		  }
		   
	 }
