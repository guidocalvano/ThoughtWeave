package Main;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.Locale;
import java.util.Scanner;

import Graphics3D.SceneManager;
import Graphics3D.Shape3DViewManager;
import Network.LinkView;
import Network.Model;
import Network.NodeView;

import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.Scene;



public class GuiLayerManager implements GuiLayerInterface
	 {

	 /**
	  * @param args
	  */
	 
//	  static GuiLayerManager singleton ;
	 
	 
	 
	  GuiControl guiControl ;
	  
	  Shape3DViewManager shape3DViewManager ;
	  
	  SceneManager sceneManager ;
	  
	  GuiLayerManager()
	  	{	  
	  	}
	  
	  public void init()
		   {		  
		    sceneManager = new SceneManager() ;
		
		    shape3DViewManager = new Shape3DViewManager( sceneManager ) ;
		    	   
		    guiControl = new GuiControl( shape3DViewManager ) ;
		    
		    /*
			   LinkView[] model= new LinkView[ 130000] ;
			   NodeView a = new NodeView() ;
			   NodeView b = new NodeView() ;
			   
			   for( int i = 0 ; i  < 130000 ; i++ )
					{
					 if( i % 10000 == 0 )
						  System.out.println( " i " + i ) ;
					 
					 model[ i ] = new LinkView( new Model( i ), a,b) ;
					 shape3DViewManager.addShapeView( model[ i ] ) ; 
					 guiControl.getNetworkControl().getTransformGroup().addChild( model[ i ].getBranchGroup() ) ;
					}*/
		   }
	  
	
		 
	  
	  
//	  static GuiLayerManager getSingleton() { return GuiLayerManager.singleton ; }

	  public SceneManager getSceneManager() { return sceneManager ; }
	  
	  public GuiControlInterface getGuiControl() { return guiControl ; }


	 }
