package Graphics3D;

import java.awt.Font;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import javax.vecmath.*;



import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;



public class SceneManager
	 {
	  static SceneManager singleton ;

	  BranchGroup sceneTree ; 
	  
	  CameraControl camera ;
 
 
	  public SceneManager()
	  	{
	  	 sceneTree = createSceneGraph() ;
	  	 
	
      

	  	 // sceneTree.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	  	 
	  	 sceneTree.compile() ;
	  	 
	  	 System.out.println( "SceneManager Constructed") ;
	  	 
	  	}

	  public static SceneManager getSingleton() { return singleton ; } 
	  

	  public void addCameraControl( CameraControl camera )
		   {
		    this.camera = camera ;
		   
		    camera.getSimpleUniverse().addBranchGraph( sceneTree ) ;

		   }
 
	  public void addToRoot( Node node )
		   {
		    sceneTree.addChild( node ) ;
 
		   }
	  
	  
	  public Node pickClosest( PickShape shape )
		   {
		    SceneGraphPath path = sceneTree.pickClosest( shape ) ;
		    
		    if( path == null ) System.out.println( "no path" ) ;
		    else System.out.println( "path" + path.nodeCount() + path.getObject().getClass() ) ;
		    
		    if( path != null )
		    	 {
		    	  return path.getObject() ;
		    	 }
		    
		    
		    
		    return null ;
		    
		   }
	
	  public BranchGroup createSceneGraph() 
		{ 
		
		        // Create the root of the branch graph 
		         BranchGroup objRoot = new BranchGroup(); 
	
		         objRoot.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND ) ;
		         objRoot.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
		         objRoot.setCapability( BranchGroup.ALLOW_PICKABLE_READ ) ;
		         objRoot.setCapability( BranchGroup.ALLOW_PICKABLE_WRITE ) ;
/*		         
		         Transform3D rotate = new Transform3D(); 
		         
		         Transform3D rotate2 = new Transform3D(); 

		         Transform3D rotate3 = new Transform3D(); 

		         
		         rotate.rotX(Math.PI/4.0d); 
		                  rotate2.rotY(Math.PI/9.0d); 
		              rotate3.rotZ(Math.PI/.5d); 

		              
		         rotate.mul( rotate2 ) ;
		         
		         rotate.mul( rotate3 ) ;
		          
		                  TransformGroup objRotate = new TransformGroup(rotate); 
		                  */
		                  Appearance redAppearance = new Appearance() ;
		                  
		                  Color3f justRed = new Color3f( 1.0f, .0f, .0f ) ;
		                //  redAppearance.setMaterial( new Material( justRed, justRed, justRed, justRed, 1.0f ) );
		                  redAppearance.setColoringAttributes (new ColoringAttributes (new Color3f (1.0f, 0.0f, 0.0f),1));

		                  Appearance blueAppearance = new Appearance() ;
		                  
		                  Color3f justBlue = new Color3f( 0.0f, .0f, 1.0f ) ;
		                //  redAppearance.setMaterial( new Material( justRed, justRed, justRed, justRed, 1.0f ) );
		                  blueAppearance.setColoringAttributes (new ColoringAttributes ( justBlue,1));

		                  
		                  
		              //    objRotate.addChild( new Sphere(0.25f, redAppearance  ) ) ;
		              //    objRotate.addChild( new Cylinder(0.014f, 50.0f, blueAppearance  ) ) ;


		                  // Build 3D text that says "Hello!", starting with a 2D font and extrusion to build a 3D font
/*
		                  Font my2DFont = new Font(
		                      "Arial",     // font name
		                      Font.PLAIN,  // font style
		                      1 );         // font size
		                  FontExtrusion myExtrude = new FontExtrusion( );
		                  Font3D my3DFont = new Font3D( my2DFont, myExtrude );

		            // Then build 3D text geometry using the font

		                  Text3D myText = new Text3D( );
		                  myText.setFont3D( my3DFont );
		                  myText.setString( "Zo!" );
*/
		           // Assemble the shape
		                  
		                  Appearance myAppear = new Appearance();
		                  Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		                  Color3f red = new Color3f(1.0f, .0f, .15f);
		                  Color3f green = new Color3f(0f, .7f, .15f);
		            //      myAppear.setMaterial(new Material(white,white, white, white, 1.0f));

		                  myAppear.setColoringAttributes (new ColoringAttributes (new Color3f (1.0f, 1.0f, 1.0f),1));

		                  
//		                  Shape3D myShape = new Shape3D( myText, myAppear );

		                  
//		                  objRotate.addChild( myShape ) ;
	                  
		                  BoundingSphere bounds =
		                      new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
		                  
		                  AmbientLight ambientLightNode = new AmbientLight(true,white);
		                  ambientLightNode.setInfluencingBounds(bounds);
		                  objRoot.addChild(ambientLightNode);
		         
		                  
		                  // Set up the directional lights
		                  Color3f light1Color = new Color3f(0.5f, 0.5f, 0.5f);
		                  Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
		                  Color3f light2Color = new Color3f(0.5f, 0.5f, 0.5f);
		                  Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);

		                  DirectionalLight light1
		                      = new DirectionalLight(white, light1Direction);
		                  light1.setInfluencingBounds(bounds);
		                  objRoot.addChild(light1);

		                  DirectionalLight light2
		                      = new DirectionalLight(white, light2Direction);
		                  light2.setInfluencingBounds(bounds);
		                  objRoot.addChild(light2);


		      // Create a simple shape leaf node, add it to the scene graph. 
		         // ColorCube is a Convenience Utility class 
		   //      objRoot.addChild(objRotate); 
		 

		         
     return objRoot; 
		} // end of createSceneGraph method of HelloJava3Da 

	 public CameraControl getCameraControl()
		  {
		  // TODO Auto-generated method stub
		  return camera ;
		  }

	 public BranchGroup getRoot()
		  {
		  // TODO Auto-generated method stub
		  return sceneTree ;
		  }

}
