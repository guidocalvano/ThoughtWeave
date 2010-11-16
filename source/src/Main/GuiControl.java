package Main;

import Network.* ;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.* ;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import javax.media.j3d.* ;

import Graphics3D.*;

import java.awt.* ;
public class GuiControl implements GuiControlInterface, ActionListener
	 {
	  JFrame frame ;
	  
	  JPanel viewContainer ;
	  
	  CameraControl camera  ;
	  
	  
	  Shape3DViewManager shape3DViewManager ;
	  
	  NetworkControl network ;
	  
	  JFrame dataFrame ;
	  
	  MenuControl menuControl ;
	  
	  DemonstrationArea demonstration ;
	  
	  NetworkDataControlInterface dataControl ;
	  
	  
	  
	  
	  JMenuBar 	menuBar ;
	  
	  JMenu		applicationMenu	;

	  JMenuItem		about		;

	  
	  JMenu		fileMenu		;

	  JMenuItem		createImage	;
	  JMenuItem		open		;
	  JMenuItem		record		;
	  JMenuItem		stopRecord	;
	  
	  JMenu		optionMenu			;
	  JCheckBoxMenuItem	showBrain	;
	  
	  JMenu		windowMenu		;

	  JMenuItem		linkMappingWindow ;
	  JMenuItem		nodeMappingWindow ;
	  
	  JMenu		helpMenu		;

	  JMenuItem		manual		;

	  JMenuItem backgroundColorWindow;
	  
	 

	  

	  
	  
	  GuiControl( Shape3DViewManager shape3DViewManager )
	  	{
	  	 this.shape3DViewManager = shape3DViewManager ;
	  	 
	  	 
	  	 menuControl = new MenuControl() ;
	  	 frame = new JFrame() ;
	  	 
	  	 frame.setVisible( true ) ;
	  	 
	  	 frame.setSize( 800, 800 ) ;
 		 frame.setResizable(false);				  	 

	  	 
	  	 frame.addWindowListener( new ExitListener() ) ;

	  	 
	  	 viewContainer = new JPanel() ;
	  	 viewContainer.setSize( 1000, 800 ) ;
	  	 
	  	 viewContainer.setLayout( null ) ;
	  	 

	  	 // camera = new CameraControl( viewContainer, shape3DViewManager ) ;
	  	camera = new CameraControl( null, shape3DViewManager ) ;
	  	
	  	// JPanel framePanel = new JPanel() ;
	  	// framePanel.setSize( 800, 800 + menuBar.getHeight() ) ;
	  	// framePanel.setPreferredSize( new Dimension( 800, 800 + menuBar.getHeight() ) ) ;
	  	 
	  	// framePanel.add(  camera.viewContainer ) ;
	  	 
	  	 
	  	 frame.setContentPane( camera.viewContainer  ) ;
	  //	 frame.setJMenuBar( menuControl.getMenuBar() ) ;
	 //		frame.pack();

	  	 
	  	 shape3DViewManager.addCanvas3D(  camera.getCanvas3D()  ) ;
	  	 

		 network = new NetworkControl( shape3DViewManager ) ;
		 
	  	 demonstration = new DemonstrationArea( camera.getCanvas3D(), camera.getSimpleUniverse().getViewingPlatform() ) ;

	  	 demonstration.add( network.getBranchGroup() ) ;
	  	 
		 demonstration.showBrainModel( false )  ;

	  	 
	  	 shape3DViewManager.getSceneManager().addToRoot( demonstration.getBranchGroup() ) ;
	/*	   
		    dataFrame = new JFrame() ;
		   
			 		 
		    dataFrame.setVisible( true ) ;
		  	 
		    dataFrame.setSize( 1000, 800 ) ;
		    dataFrame.setResizable(true);
		    dataFrame.setTitle( "data control" ) ;
*/
	 		dataControl = new NetworkDataControl( getNetworkControl() ) ;
	 	/*	dataFrame.setContentPane(  dataControl.getPanel()  ) ;


	 		dataFrame.pack();
*/		 
	
	  	 
	  	 System.out.println( "revalidating and repainting" ) ;
	  	 
		  	viewContainer.revalidate() ;
		  	viewContainer.repaint() ;
		 
	 		initMenu() ;
	 		
	 //		getNetworkControl().initNetwork( 530 ) ;
	  	}
	  
	  
	 void initMenu()
		  {
		    menuBar = new JMenuBar() ;
		    
		    applicationMenu = new JMenu( "Application" ) ;

		    	about			= new JMenuItem( "About" ) ; 			about.addActionListener( this ) ;

		    	applicationMenu.add( about ) ;
		    	
		    fileMenu		= new JMenu( "File" ) ;

		    	createImage		= new JMenuItem( "Create Image" ) 	; 	createImage.addActionListener( this ) ;
		    	open			= new JMenuItem( "Open" ) 			;	open.addActionListener( this ) ;

		    	record			= new JMenuItem( "Record" ) 		;	record.addActionListener( this ) ;
		    	stopRecord		= new JMenuItem( "Stop Record" )	;	stopRecord.addActionListener( this ) ; stopRecord.setEnabled( false ) ;

		    	
		    	 fileMenu.add(  createImage 	) ;
				 fileMenu.add(  open 			) ;

				 fileMenu.add(  record 			) ;
				 fileMenu.add(  stopRecord		) ;

				 
		    optionMenu		= new JMenu( "Options" ) ;
		   
		    	showBrain 	= new JCheckBoxMenuItem( "Show Brain" ) ; showBrain.setSelected( demonstration.brainIsVisible() ) ; showBrain.addActionListener( this  ) ;
		    	
		    	optionMenu.add( showBrain ) ; 
		    	
		    windowMenu		= new JMenu( "Window" ) ;

		    	nodeMappingWindow		= new JMenuItem( "Node Mapping" ) ;	nodeMappingWindow.addActionListener( this ) ; nodeMappingWindow.setEnabled( false ) ;
		    	linkMappingWindow		= new JMenuItem( "Link Mapping" ) ; linkMappingWindow.addActionListener( this ) ; linkMappingWindow.setEnabled( false ) ;
		    	backgroundColorWindow	= new JMenuItem( "Background Color" ) ; backgroundColorWindow.addActionListener( this ) ;
		    	
		    	windowMenu.add( nodeMappingWindow ) ;
		    	windowMenu.add( linkMappingWindow ) ;
		    	windowMenu.add( backgroundColorWindow ) ;
		    	
		    helpMenu		= new JMenu( "Help" ) ;

		    	manual			= new JMenuItem( "Manual" ) ; 				manual.addActionListener( this ) ;
		    
		    	helpMenu.add( manual ) ;

		    menuBar.add( applicationMenu ) ;
		    menuBar.add( fileMenu ) ;
		    menuBar.add( optionMenu ) ;
		    menuBar.add( windowMenu ) ;
		    menuBar.add( helpMenu ) ;
		    
		   
		    frame.setJMenuBar( menuBar ) ;
		    frame.pack();

		  }
	

	 public NetworkControlInterface getNetworkControl()
		  {
		  return network ;
		  }


	 public void actionPerformed( ActionEvent e)
		  {
		   if( about.equals( e.getSource() ) )
				{
				 System.out.println( "about" ) ;	
				 
				 GuiControl.openURL( "http://www.vu.nl" ) ;

				}
		   
		   if( open.equals( e.getSource() ) )
				{
				 System.out.println( "open" ) ;	
				 
				 if( dataControl.loadDirectory() )
					  {
					   nodeMappingWindow.setEnabled( true ) ;
					   linkMappingWindow.setEnabled( true ) ;
					  }
				}
		   
		   if( createImage.equals( e.getSource() ) )
				{
				 System.out.println( "createImage" ) ;	
				 
				  final JFileChooser fileChooser = new JFileChooser();
				//  fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				  
				  
				  fileChooser.setCurrentDirectory( new File( System.getProperty( "user.dir" ) ) ) ;

					 int returnVal = fileChooser.showSaveDialog( camera.viewContainer ) ;
					 
					 File file = fileChooser.getSelectedFile() ;	
					 
					 
					 if( file != null ) 
						  {
						   int heightHack = (int) ( ( (double) menuBar.getHeight() ) * 2 );
						  
						   camera.saveFoto( file.getName(), 0, heightHack, camera.getCanvas3D().getWidth(), camera.getCanvas3D().getHeight() - heightHack ) ;
						  }
				}
		   
		   if( record.equals( e.getSource() ) )
				{
				 System.out.println( "record" ) ;	
				 
				  final JFileChooser fileChooser = new JFileChooser();
					//  fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					  
					  
					  fileChooser.setCurrentDirectory( new File( System.getProperty( "user.dir" ) ) ) ;

						 int returnVal = fileChooser.showSaveDialog( camera.viewContainer ) ;
						 
						 File file = fileChooser.getSelectedFile() ;	
						 
						 
						 if( file != null ) 
							  {
							   int heightHack = (int) ( ( (double) menuBar.getHeight() ) * 2 );
							  
							   camera.record( 0, heightHack, camera.getCanvas3D().getWidth(), camera.getCanvas3D().getHeight() - heightHack, 30, file.getName() ) ;
							   
							   record.setEnabled( false ) ;
							   stopRecord.setEnabled( true ) ;

							  }
				 
				}
		   
		   if( stopRecord.equals( e.getSource() ) )
				{
				 System.out.println( "stopRecord" ) ;
				 
				 camera.stopRecord() ;
				 stopRecord.setEnabled( false ) ;
				 record.setEnabled( true ) ;
				 
				}
				 
		   
		   if( showBrain.equals( e.getSource() ) )
				{
				 System.out.println( "backgroundColorWindow" ) ;	
				 
				 demonstration.showBrainModel( showBrain.isSelected() )  ;
				}
		   
		   
		   if( nodeMappingWindow.equals( e.getSource() ) )
				{
				 System.out.println( "nodeMappingWindow" ) ;
				 
				 getNetworkControl().showNodeMappingWindow() ;
				}
		   
		   if( linkMappingWindow.equals( e.getSource() ) )
				{
				 System.out.println( "linkMappingWindow" ) ;	
				 
				 getNetworkControl().showLinkMappingWindow() ;
				}
		   
		   
		   if( backgroundColorWindow.equals( e.getSource() ) )
				{
				 System.out.println( "backgroundColorWindow" ) ;	
				 
				 demonstration.showBackgroundColorWindow() ;
				}
		   
		   
		   if( manual.equals( e.getSource() ) )
				{
				 System.out.println( "manual" ) ;
				 
				 GuiControl.openURL( "http://www.few.vu.nl" ) ;
				 
				}		   
		   
		  }

	   static final String[] browsers = { "google-chrome", "firefox", "opera",
	      "konqueror", "epiphany", "seamonkey", "galeon", "kazehakase", "mozilla" };
	   static final String errMsg = "Error attempting to launch web browser";

	   public static void openURL(String url) 
	   
			{
			  try {
			   System.out.println( "bla" ) ;
		         String osName = System.getProperty("os.name");

		         Process oProc ;
		         if( GuiControl.isMac() || GuiControl.isUnix() )

		            	  oProc = Runtime.getRuntime().exec( "open " + url );
		         else if( GuiControl.isWindows() )
	            	  oProc = Runtime.getRuntime().exec( "cmd /c start " + url );
		         
		         else 
		        	  {
		   		   JOptionPane.showMessageDialog(null, "To find the information you want visit " + url, "Browser not found", JOptionPane.INFORMATION_MESSAGE ) ;

					   return ;
		        	  }
			    int bExit = oProc.waitFor();  // This is always 1 for some reason
				   System.out.println( "ladida" ) ;

			    return ;

			  } catch ( Exception e ) {
	   		   JOptionPane.showMessageDialog(null, "To find the information you want visit " + url, "Browser not found", JOptionPane.INFORMATION_MESSAGE ) ;
			    return ;
			  }
			}
				public static boolean isWindows(){
				 
				String os = System.getProperty("os.name").toLowerCase();
				//windows
			    return (os.indexOf( "win" ) >= 0); 
		 
			}
		 
			public static boolean isMac(){
		 
				String os = System.getProperty("os.name").toLowerCase();
				//Mac
			    return (os.indexOf( "mac" ) >= 0); 
		 
			}
		 
			public static boolean isUnix(){
		 
				String os = System.getProperty("os.name").toLowerCase();
				//linux or unix
			    return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
		 
			}
	      


	 }


class MorphingBehavior extends Behavior {

Alpha alpha;

Morph morph;

double weights[];

WakeupOnElapsedFrames w = new WakeupOnElapsedFrames(0);

// Override Behavior's initialize method to setup wakeup criteria
public void initialize() {
  alpha.setStartTime(System.currentTimeMillis());

  // Establish initial wakeup criteria
  wakeupOn(w);
}

// Override Behavior's stimulus method to handle the event
public void processStimulus(Enumeration criteria) {

  // NOTE: This assumes 3 objects. It should be generalized to
  // "n" objects.

  double val = alpha.value();
  if (val < 1.0) {
    double a = val ;
    weights[0] = 1.0 - a;
    weights[1] = a;
  } else {
 //   double a = (val - 0.5) * 2.0;
  //  weights[0] = 0.0;
   // weights[1] = 1.0f - a;
  }

  morph.setWeights(weights);

  // Set wakeup criteria for next time
  wakeupOn(w);
}

public MorphingBehavior(Alpha a, Morph m) {
  alpha = a;
  morph = m;
  weights = morph.getWeights();
}
}
