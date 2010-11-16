package Graphics3D ;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.media.j3d.*;

import javax.vecmath.*;

import javax.media.j3d.* ;


import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.picking.PickTool;


public class Shape3DFactory
	 {
	 
	  static Map shapeFileCache ; //= new HashMap() ;

	  static Map morphShapeFileCache ; // = new HashMap() ;
	  
	  static boolean isInitialized = false ;
	  
	  static void init()
	  	{
	  	 shapeFileCache = new HashMap() ;

		 morphShapeFileCache = new HashMap() ;
	     Shape3D shape ;


	     LineArray line = new LineArray(2, LineArray.COORDINATES);
	     
	      line.setCoordinate(0, new Point3f( 0.0f, -.5f, 0.0f));
	      line.setCoordinate(1, new Point3f( 0.0f, .5f, 0.0f));
	      
	      shape = new Shape3D( line, new Appearance() ) ;
	      

	      
		   shape.setCapability( Shape3D.ALLOW_GEOMETRY_READ ) ;
	  		 
			  shape.getGeometry().setCapability( Geometry.ALLOW_INTERSECT ) ;

//
			  PickTool.setCapabilities( shape, PickTool.INTERSECT_FULL ) ;

	  		   
	  		   shape.getGeometry().setDuplicateOnCloneTree( false ) ;
	  		   
	  		   
	  		   shape.setCapability( Shape3D.ALLOW_APPEARANCE_READ ) ;
	  		   shape.setCapability( Shape3D.ALLOW_APPEARANCE_WRITE ) ;

	  		   
	  		   
	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
	  		   

	  		//   shape.getAppearance().getMaterial().setDuplicateOnCloneTree( true ) ;
	  		   

	  		 //  shape.getAppearance().getTransparencyAttributes().setDuplicateOnCloneTree( true ) ;
	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;

	  		   
	  		   shape.getAppearance().setMaterial( null ) ;
	  		   shape.getAppearance().setTexture( null ) ;
	  		   
	  		  shape.getAppearance().setLineAttributes( new LineAttributes() ) ;
	  		  shape.getAppearance().getLineAttributes().setCapability( LineAttributes.ALLOW_WIDTH_READ ) ;
	  		  shape.getAppearance().getLineAttributes().setCapability( LineAttributes.ALLOW_WIDTH_WRITE ) ;
	  		
	  		shape.getAppearance().getLineAttributes().setLineWidth( 100f ) ;
	  		  
			  shape.setCapability( Node.ENABLE_PICK_REPORTING );
			  
			  PickTool.setCapabilities( shape, PickTool.INTERSECT_TEST ) ;
  
			  for (Enumeration e = shape.getAllGeometries(); e.hasMoreElements();) 
				   {
				    Geometry g = (Geometry)e.nextElement();

				    g.setCapability(g.ALLOW_INTERSECT);
				   }
	  	 shapeFileCache.put( "line", shape ) ;
	  	 
	  		 isInitialized = true ;
	  	}
	  
	  static int createShapeCallCount = 0 ;
	  static Shape3D createShape( String objectFilename )
		   {
		    System.out.println( "filename " + objectFilename ) ;
		   
		    createShapeCallCount++ ;
		   
		    if( createShapeCallCount % 10000 == 0 ) System.out.println( "createShapeCallCount == " + createShapeCallCount ) ;
		    
		    if( !isInitialized) init() ;
		   
		    if( !shapeFileCache.containsKey( objectFilename ) )
		    	 {
		    	 
		    	 
    		    Shape3D shape ;
    		   
    		    Scene scene = tryObjectLoad( objectFilename ) ;
    
    		    if( scene != null )
    	  		  {
    	  			  	
    	  		
    	  		   shape = getShape( scene.getSceneGroup() ); //scene.getSceneGroup() ;
    	  		   
    	  		   shape.setCapability( Shape3D.ALLOW_GEOMETRY_READ ) ;
    	  		 
 				  shape.getGeometry().setCapability( Geometry.ALLOW_INTERSECT ) ;

 				  PickTool.setCapabilities( shape, PickTool.INTERSECT_FULL ) ;

    	  		   
    	  		   shape.getGeometry().setDuplicateOnCloneTree( false ) ;
    	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
    	  		   shape.getAppearance().getMaterial().setDuplicateOnCloneTree( true ) ;
    	  		 //  shape.getAppearance().getTransparencyAttributes().setDuplicateOnCloneTree( true ) ;
    	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
    	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
    	  		   
    	  		   
    	  		   shape.getAppearance().setMaterial( null ) ;
    	  		   
    				if(!objectFilename.startsWith( "brain" ) )

    					 shape.getAppearance().setTexture( null ) ;

				  shape.setCapability( Node.ENABLE_PICK_REPORTING );
				  
				  PickTool.setCapabilities( shape, PickTool.INTERSECT_TEST ) ;
				  
				  for (Enumeration e = shape.getAllGeometries(); e.hasMoreElements();) 
					   {
					    Geometry g = (Geometry)e.nextElement();

					    g.setCapability(g.ALLOW_INTERSECT);
					   }
    	  		   
    	  		   ( (Group) shape.getParent() ).removeChild( shape ) ;
    	  		   
    	  		   shapeFileCache.put( objectFilename, shape ) ;
    	  		  }
    		  	 else  { System.exit( 1 ) ; }

    
    //	  		   return shape ;
	  		  }
		  
		  
		    
	    	  return (Shape3D) (  (Shape3D) shapeFileCache.get( objectFilename )).cloneNode( false ) ;
	  	}
	  
	  static void createShape( Shape3DViewInterface shapeView, String objectFilename, boolean isVisible )
		   {
		    shapeView.init( createShape( objectFilename ), isVisible  ) ;

		    if( objectFilename.equals( "line" ) )
		    	shapeView.setSpatialInterface( ( new LineSpace()  ).init( shapeView ) ) ; 
		    else
		    	 shapeView.setSpatialInterface( ( new StandardSpace( false, false, true, false ) ).init( shapeView ) ) ;
	
		    System.out.println( "file name" + objectFilename  ) ; 
		    
		    if( objectFilename.startsWith( "brain" ) ) { System.out.println( "loading brain model" ) ; return ; }
		    	 
		    shapeView.setMaterialInterface( new FastFlatColor() ) ;

		   }
	  
	  
	  static Shape3D createMorphShape( String objectFilename )
		   {
		    if( !isInitialized) init() ;

		   
		    if( !morphShapeFileCache.containsKey( objectFilename ) )
		    	 {
		    	 
		    	 
    		    Shape3D shape ;
    		   
    		    Scene scene = tryObjectLoad( objectFilename ) ;
    
    		    if( scene != null )
    	  		  {
    	  			  	
    	  		
    	  		   shape = getShape( scene.getSceneGroup() ); //scene.getSceneGroup() ;
    	  		   
    	  		   shape.setCapability( Shape3D.ALLOW_GEOMETRY_READ ) ;
    	  		 
 				  shape.getGeometry().setCapability( Geometry.ALLOW_INTERSECT ) ;

 				  PickTool.setCapabilities( shape, PickTool.INTERSECT_FULL ) ;

    	  		   
    	  		   shape.getGeometry().setDuplicateOnCloneTree( false ) ;
    	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
    	  		   shape.getAppearance().getMaterial().setDuplicateOnCloneTree( true ) ;
    	  		 //  shape.getAppearance().getTransparencyAttributes().setDuplicateOnCloneTree( true ) ;
    	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
    	  		   shape.getAppearance().setDuplicateOnCloneTree( true ) ;
    	  		   RenderingAttributes renderingAttributes = new RenderingAttributes() ;
    			     
    			     renderingAttributes.setDepthBufferEnable( true ) ;
    			     renderingAttributes.setDepthBufferWriteEnable( true ) ;

    			    shape.getAppearance().setRenderingAttributes( renderingAttributes ) ;
    			   
    	  		   
    	  	//	   shape.getAppearance().setMaterial( null ) ;
    	  		   shape.getAppearance().setTexture( null ) ;

				  shape.setCapability( Node.ENABLE_PICK_REPORTING );
				  
				  PickTool.setCapabilities( shape, PickTool.INTERSECT_TEST ) ;
				  
				  for (Enumeration e = shape.getAllGeometries(); e.hasMoreElements();) 
					   {
					    Geometry g = (Geometry)e.nextElement();

					    g.setCapability(g.ALLOW_INTERSECT);
					   }
    	  		   
    	  		   ( (Group) shape.getParent() ).removeChild( shape ) ;
    	  		   
    	  		   morphShapeFileCache.put( objectFilename, shape ) ;
    	  		  }
    		  	 else  { System.exit( 1 ) ; }

    
    //	  		   return shape ;
	  		  }
		  
		    
	    	  return (Shape3D) (  (Shape3D) morphShapeFileCache.get( objectFilename )).cloneNode( false ) ;
	  	}
	  
	  
	  
	   static Shape3D getShape( BranchGroup branch )
			{
			 for( int i = 0 ; i < branch.numChildren() ; i++ )
				  {
				   Node child = branch.getChild( i ) ;
				   
				   if( child.getClass().getName().equals( "javax.media.j3d.Shape3D") )
						return (Shape3D) child ;

				  }

				  
			 return null ;
			}
			
	  
	  
	   static Scene tryObjectLoad( String filename )
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
 
	   
	  static Morph createMorph( String[] fileNameAt )
		  {
		   GeometryArray[] geometryAt = new GeometryArray[ fileNameAt.length ];
		   Morph morph ;
		   Appearance appearance = Shape3DFactory.createMorphShape( fileNameAt[ 0 ] ).getAppearance() ;
		   
	  		   RenderingAttributes renderingAttributes = new RenderingAttributes() ;
			     
			     renderingAttributes.setDepthBufferEnable( true ) ;
			     renderingAttributes.setDepthBufferWriteEnable( true ) ;

			    appearance.setRenderingAttributes( renderingAttributes ) ;
		   
		   for( int i = 0 ; i < fileNameAt.length ; i++ )
				{
				 geometryAt[ i ] = (GeometryArray) Shape3DFactory.createShape( fileNameAt[ i ] ).getGeometry() ;		
				}
		   
		   
		   morph = new Morph( geometryAt, appearance ) ;
		   
		   morph.setCapability(Morph.ALLOW_GEOMETRY_ARRAY_READ);
		   
		  // PickTool.setCapabilities( morph, PickTool.INTERSECT_FULL ) ;

		  
		   return morph ;
		  }
	  
	  static void createMorph( Shape3DViewInterface shapeView, String[] objectFilenameSet, boolean isVisible )
		   {
		    Morph morph = createMorph( objectFilenameSet ) ;
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
			 MaterialInterface material = new ShadedMaterial() ;
			    shapeView.setMaterialInterface( material  ) ;

			 
		    shapeView.init( morph, isVisible  ) ;
	
		    shapeView.setSpatialInterface( ( new StandardSpace( true, true, false, true ) ).init( shapeView ) ) ;
		  	

		   }


	 public static MaterialInterface getShapeMaterial()
		  {
		  return new FastFlatColor() ;
		  }
	 
	 public static MaterialInterface getMorphMaterial()
		  {
		   return new ShadedMaterial() ;
		  }
	 
	 public static SpatialInterface getMorphSpace()
		  {
		  return new StandardSpace(  true, true, false, true ) ;
		  }
	 
	 public static SpatialInterface getShapeSpace()
		  {
		  return new StandardSpace(  false, false, true, false ) ;
		  }
	 }
