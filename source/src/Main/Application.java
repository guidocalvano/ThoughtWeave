package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import Graphics3D.Shape3DView;
import Network.LinkView;
import Network.Model;
import Network.NodeView;


public class Application
	 {
	  GuiLayerInterface 		gui 	;
	  
	  static Application	singleton ;
	  
	  
		 
	  public static void main( String[] args)
		  {
		  
		 //  new PropertyFileReader( new File( "a-voorbeeld.txt" ) ) ;
		  createTestRelationFile( 1 ) ;
		  createTestRelationFile( 3 ) ;

		  
		  createTestRelationFile( 21 ) ;
	
		   Application.singleton = new Application() ;
		   Application.singleton.init() ;
		
		   
		  }
	  static void createTestRelationFile( int channelCount )
		   {
        	  try
        			{
        	  	 FileWriter writer = new FileWriter( new File( "test" + channelCount + "x" + channelCount + ".rd" ) ) ;
        	  	 
        		   for( int i = 0 ; i < channelCount ; i++ )
        				   for( int j = 0 ; j < channelCount ; j++ )
        					   
        							writer.write( Double.toString(  Math.random() ) + " " ) ;
        		
        		     writer.close() ;
        			} catch( IOException e )
        					{
        					 System.out.println( "can't write test file" ) ;
        					e.printStackTrace();
        					}
		   }
	  void init()
		  {
	
		   
  
		   gui = new GuiLayerManager() ;
		   
		   
	//	   gui.init( "test2.vd" ) ;
		   
		 //  new NetworkModelView( null ) ;
		
		   
	
	
		 		/* 
			 		 
					   JFrame d = new JFrame() ;
					   
				 		 
					  	 d.setVisible( true ) ;
					  	 
					  	 d.setSize( 1000, 800 ) ;
				 		 d.setResizable(true);		
				 		GlobalEditor glob = new GlobalEditor() ;
				 		 d.setContentPane(  glob.getPanel()  ) ;

				 		 d.setTitle( "Global editor" ) ;

				 		 d.pack();
				 	*/	 
				 		 
				 		 
				 		 
		gui.init() ;
		
					 		 
					 		 				 		 
				 		 
				 		 
						//  gui.init( logic ) ;

		  }
	  
	  
	  public static Application getSingleton() { return singleton ; }
		   
	  

	 }
