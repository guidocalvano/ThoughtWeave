package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class GlobalEditor implements ActionListener
	 {
	  JPanel panel ;
	  
	  JButton addLinkFile ;
	  JButton addVisualizationFile ;
	  JButton addNodePropertyFile	;
	  
	  JPanel showBrainPanel ;
	  
	  JPanel rotationPanel ;
	  
	  JPanel topRotation ;
	  JPanel middleRotation ;
	  JPanel bottomRotation ;
	  
	  JPanel backgroundPanel ;
	  
	  JLabel showBrainLabel	;

	  JCheckBox showBrain	;
	  
	  JButton pitchUp ;
	  JButton pitchDown ;
	  
	  JButton rollUp   ;
	  JButton rollDown ;
	  
	  JButton yawLeft  ;
	  JButton yawRight ;
	  
	  JLabel o ;
	  
	  JSlider red 	;
	  JSlider green ;
	  JSlider blue 	;
	  
	  JLabel redLabel 	;
	  JLabel greenLabel ;
	  JLabel blueLabel 	;
	  
	  GlobalEditor()
	  	{
		  panel = new JPanel() ;
		  
			 panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) ) ;
			 
			 panel.setSize( 1000, 800 ) ;
			 panel.setPreferredSize( new Dimension( 1000, 800 ) )  ;
		  
			 showBrainPanel = new JPanel() ;
			 
		  rotationPanel = new JPanel() ;
		  
		   topRotation  = new JPanel() ;
		   middleRotation  = new JPanel() ;
		   bottomRotation  = new JPanel() ;
		   
		  
		  
		  backgroundPanel = new JPanel() ;
		  
		  
		   addLinkFile = new JButton( "add link file" ) ; 
		   addVisualizationFile = new JButton( "add visualization file" ) ; 
		   addNodePropertyFile	= new JButton( "add node property file" ) ;
		   
		   addLinkFile.addActionListener( this ) ;
		   addVisualizationFile.addActionListener( this ) ;
		   addNodePropertyFile.addActionListener( this ) ;
		   
		   
		   panel.add( addLinkFile ) ;
		   panel.add( addVisualizationFile ) ;
		   panel.add( addNodePropertyFile ) ;
		   
		  
		  showBrainLabel = new JLabel( "Show brain?" ) ;
		  showBrain	= new JCheckBox() ;
		  
		  panel.add( showBrainPanel ) ;
		  
		  showBrainPanel.add( showBrainLabel ) ;
		  showBrainPanel.add( showBrain ) ;
		  
		  
		   pitchUp = new JButton( "^") ;
		   pitchDown = new JButton( "v" ) ;
		  
		   rollUp   = new JButton( "^ ") ;
		   rollDown = new JButton( "v") ;
		  
		   yawLeft  = new JButton("<") ;
		   yawRight = new JButton(">") ;
		  
		   o = new JLabel( "O" );
		  
		  
		  panel.add( rotationPanel ) ;
		  
		  rotationPanel.setLayout( new BoxLayout( rotationPanel, BoxLayout.Y_AXIS ) ) ;
		  rotationPanel.add( topRotation ) ;
		  rotationPanel.add( middleRotation ) ;
		  rotationPanel.add( bottomRotation ) ;
		  
		  topRotation.add( pitchUp ) ;
		  middleRotation.add( yawLeft ) ;
		  middleRotation.add( rollUp ) ;
		  middleRotation.add( o ) ;
		  middleRotation.add( rollDown ) ;
		  middleRotation.add( yawRight ) ;
		  bottomRotation.add( pitchDown ) ;

		  panel.add( backgroundPanel ) ;
		  
		  
		  
		   red 	= new JSlider() ;
		   green 	= new JSlider() ;
		   blue 		= new JSlider() ;
		   
		   
			   redLabel = new JLabel( "red" )	;
			   greenLabel = new JLabel( "red" )	;
			   blueLabel 	= new JLabel( "red" )	;
			   
			   
			   backgroundPanel.add( redLabel ) ;
			   backgroundPanel.add( red ) ;
			   backgroundPanel.add( greenLabel ) ;
			   backgroundPanel.add( green ) ;
			   backgroundPanel.add( blueLabel ) ;
			   backgroundPanel.add( blue ) ;
			   
			   
		 panel.validate() ;
		 panel.repaint() ;
	  
	  	}
	  
	  public JPanel getPanel() { return panel ; } 
	  

		 public void actionPerformed( ActionEvent e)
			  {
			//Create a file chooser
			  final JFileChooser fc = new JFileChooser();
			  //In response to a button click:
			  int returnVal = fc.showOpenDialog(panel);
			  }
	 }
