package Main;
import Gui.IntegerField;
import Network.* ;
import Network.Data.BrainWaveNodeDataSet;
import Network.Data.LinkDataSetFile;
import Network.Data.LinkDataSetInterface;
import Network.Data.NodeDataSetInterface;
import Network.Data.VisualisationNodeDataSet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NetworkDataControl implements ActionListener, KeyListener, NetworkDataControlInterface
	 {
	  JPanel panel ;
	  
	  JPanel createNetworkPanel ;
	  
	  IntegerField nodeCountField ;
	  
	  boolean	 nodeCountFieldIsLegal ;
	  
	  JButton	 create ;
	  
	  JButton loadDirectory ;

	  
	  JButton openViewFile ;
	  
	  JButton openDataFile ;
	  
	  JButton openLinkFile ;
	  
	  JButton clear ;
	  
	  
	  
	  NetworkControlInterface network ;
	  
	  
	  NetworkDataControl( NetworkControlInterface network )
	  	{
	  	 this.network = network ;
	  	
	  	 panel = new JPanel() ;
	  	 
		 panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) ) ;


	  	 createNetworkPanel = new JPanel() ;

	  	 
	  	  nodeCountField = new IntegerField( 1, 520 ) ;
		  
	  	//  nodeCountField.setText( "7" ) ;
	  	  
	  	  
	  	  nodeCountField.addActionListener( this ) ;
	  	  
	  	  nodeCountFieldIsLegal = true ;
	  	  
		  create = new JButton( "create");
		  
		  create.setActionCommand( "create" ) ;		  
		  
		  create.addActionListener( this ) ;
		  

		   loadDirectory = new JButton( "load directory" ) ;

		    loadDirectory.setActionCommand( "loadDirectory" ) ;		   
		    loadDirectory.addActionListener( this ) ;
		  
		  openViewFile = new JButton( "load visualization data" ) ;
		  
		  openViewFile.setActionCommand( "openViewFile" ) ;
		
		  openViewFile.addActionListener( this ) ;
		  
		  
		  
		  
		  openDataFile = new JButton( "load node data" ) ;
		  
		  openDataFile.setActionCommand( "openDataFile" ) ;
	
		  openDataFile.addActionListener( this ) ;
		  
		  
		  
		  openLinkFile = new JButton( "load link data" ) ;
		  
		  openLinkFile.setActionCommand( "openLinkFile" ) ;

		  openLinkFile.addActionListener( this ) ;

		  
		  clear = new JButton( "clear network" ) ;
		  
		  clear.setActionCommand( "clear" ) ;
		  
		  clear.addActionListener( this ) ;

		  
		  
		  panel.add( createNetworkPanel ) ;
		  
		  createNetworkPanel.add( nodeCountField.getPanel() ) ;
		  createNetworkPanel.add( create ) ;
		  
		  panel.add( loadDirectory ) ;
		  
		  panel.add( openViewFile ) ;
		  panel.add( openDataFile ) ;
		  panel.add( openLinkFile ) ;
		  
		  panel.add( clear ) ;
		  
		  
	  	}

		 public void actionPerformed( ActionEvent e)
			  {
			   System.out.println( "action = " + e.getActionCommand() ) ;

			   if ("loadDirectory".equals(e.getActionCommand())) loadDirectory() ;

			   
			   
			   if ("create".equals(e.getActionCommand()))
					{
					
					
					 Integer value = nodeCountField.acceptValue() ;
					 
					 if( value != null )
						  {
						   network.initNetwork( value.intValue() ) ;
						  }

					
					 else JOptionPane.showMessageDialog(null, "Enter a positive number, greater than 0", "Error", JOptionPane.ERROR_MESSAGE) ; 
					}
			   
			   if ("openViewFile".equals(e.getActionCommand())) 
					{
					 final JFileChooser fc = new JFileChooser();
					
					 fc.setCurrentDirectory( new File( System.getProperty( "user.dir" ) ) ) ;
					//In response to a button click:
					 int returnVal = fc.showOpenDialog( panel ) ;
					 
					 File file = fc.getSelectedFile() ;
					 if( file == null ) return ;
					 
					 NodeDataSetInterface loaded = new VisualisationNodeDataSet( file ) ;
					 
					 network.load( loaded ) ;
					}
			   
			   
			   if ("openDataFile".equals(e.getActionCommand())) 
					{
					 final JFileChooser fc = new JFileChooser();
					 fc.setCurrentDirectory( new File( System.getProperty( "user.dir" ) ) ) ;
				
						//In response to a button click:
						 int returnVal = fc.showOpenDialog( panel ) ;
						 
						 File file = fc.getSelectedFile() ;
						 
						 
					 
						 if( file == null ) return ;
						 
						 NodeDataSetInterface loaded = new BrainWaveNodeDataSet( file ) ;
						 
						 network.load( loaded ) ;
					}
			   
			   if ("openLinkFile".equals(e.getActionCommand())) 
					{
					 final JFileChooser fc = new JFileChooser();
					 fc.setCurrentDirectory( new File( System.getProperty( "user.dir" ) ) ) ;
	
						//In response to a button click:
						 int returnVal = fc.showOpenDialog( panel ) ;
						 
						 File file = fc.getSelectedFile() ;
						 
						 if( file == null ) return ;
						 
						 LinkDataSetInterface loaded = new LinkDataSetFile( file ) ;
						 
						 network.load( loaded ) ;
						// JOptionPane.showMessageDialog(null, "file name", file.getName(), JOptionPane.INFORMATION_MESSAGE ) ;
						 
						
					}
			  
			  }
		 
	 public boolean loadDirectory( )
		  {
		  
		  final JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		  
		  
		  fileChooser.setCurrentDirectory( new File( System.getProperty( "user.dir" ) + File.separator + "dataSets") ) ;

				//In response to a button click:
				 int returnVal = fileChooser.showOpenDialog( panel ) ;
				 
				 File directory = fileChooser.getSelectedFile() ;
		  
		   File[] inDirectory = directory.listFiles() ;
		   
		   List nodeDataSetSet = new Vector() ;
		   List linkDataSetSet = new Vector() ;

		   int nodeCount = -1 ;
		   
		   for( int i = 0 ; i < inDirectory.length ; i++ )
				{
				 System.out.println( "filename = " + inDirectory[ i ].getName() ) ;
				
				 if( inDirectory[ i ].getName().endsWith( ".rd" ) )
					  {
					   System.out.println( "loading " + inDirectory[ i ].getName() ) ;
					  
					   LinkDataSetInterface linkData = new LinkDataSetFile( inDirectory[ i ] ) ;
					   
					   System.out.println(  "\twith " + linkData.nodeCount() + " nodes" ) ;
					   
					   if( nodeCount == -1 ) nodeCount = linkData.nodeCount() ;
					   else if( nodeCount != linkData.nodeCount() ) { wrongFile() ; return false ; } 
					   
					   linkDataSetSet.add( linkData ) ;
					  
					  }
				 else
				 if( inDirectory[ i ].getName().endsWith( ".vd" ) )
					  {
					   System.out.println( "loading " + inDirectory[ i ].getName() ) ;
					  
					   NodeDataSetInterface nodeData = new VisualisationNodeDataSet( inDirectory[ i ] ) ;
					   
					   System.out.println(  "\twith " + nodeData.nodeCount() + " nodes" ) ;

					   
					   if( nodeCount == -1 ) nodeCount = nodeData.nodeCount() ;
					   else if( nodeCount != nodeData.nodeCount() ) { wrongFile() ; return false; }
					   
					  nodeDataSetSet.add( nodeData ) ;
					  }
				 else
				 if( inDirectory[ i ].getName().endsWith( ".nd" ) )
					  {
					   System.out.println( "loading " + inDirectory[ i ].getName() ) ;
					  
					   NodeDataSetInterface nodeData =	new BrainWaveNodeDataSet( inDirectory[ i ] )  ;
					   
					   System.out.println(  "\twith " + nodeData.nodeCount() + " nodes" ) ;

					   if( nodeCount == -1 ) nodeCount = nodeData.nodeCount() ;
					   else if( nodeCount != nodeData.nodeCount() ) { wrongFile() ; return false ; }
					   
					  nodeDataSetSet.add( nodeData ) ;					  
					  }
				 // else { wrongFile() ; return ; }				 
				}
		   
		   network.initNetwork( nodeCount ) ;
		   
		   Iterator nodeData = nodeDataSetSet.iterator() ;
		   
		   while( nodeData.hasNext() )
				{
				 NodeDataSetInterface data = (NodeDataSetInterface) nodeData.next() ;
				 
				 network.load( data ) ;
				}
				
		   
		   Iterator linkData = linkDataSetSet.iterator() ;
		   
		   while( linkData.hasNext() )
				{
				 LinkDataSetInterface data = (LinkDataSetInterface) linkData.next() ;
				 
				 network.load( data ) ;
				}
		   
		   network.applyDefaultMappingSet() ;
		   
		   network.toModel() ;
		   
		   return true ;
		  }

	 private void wrongFile()
		  {
		   JOptionPane.showMessageDialog(null, "There was a problem with a file in the directory you tried to load", "There was a problem with a file in the directory you tried to load", JOptionPane.INFORMATION_MESSAGE ) ;
		  }

	 public JPanel getPanel()
		  {
		  // TODO Auto-generated method stub
		  return panel ;
		  }

	 public void keyPressed( KeyEvent e)
		  {
			  
	
		   
		  }

	 public void keyReleased( KeyEvent e)
		  {

		  }

	 public void keyTyped( KeyEvent e)
		  {

		  }
	 }
