package Network.Gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Gui.ComboBoxMenu;
import Network.*;

public class NetworkMappingManagerView implements ActionListener
	 {

	  ModelManagerInterface	modelManager ;
	 
	
	  
	  JPanel 	panel ;
	  
	  JPanel	overlaySelectorPanel 		;
	  
	  JPanel	nodeOverridesPanel 		;
	  
	  JPanel 	mappingViewListPanel 	;
	  

	  JScrollPane	scrollPane ;	 
	  
	  JPanel	selectNewMappingPanel			;
	  
	  
	  
	  JLabel		overlayTextLabel			;	  
	  
	  ComboBoxMenu	overlayTextSelector			;
	  
	  
	  
	  JLabel	nodeOverridesLabel			;	  
	  
	  JCheckBox	nodeOverridesCheck			;
	  
	  
	 
	  List 		mappingViewList 		 ;
	  
	  JComboBox selectNewMappingComboBox ;
	  
	  
	  NetworkMappingManagerView()
	  	{
	  	
//		  panelRoot = new JPanel() ;

		  
		  panel = new JPanel() ;



//		   panelRoot.add( scrollPane ) ;
	  	
	  	// panel 					= new JPanel() ;
	  	 
	  	 overlaySelectorPanel 		= new JPanel() ;
		  
		 nodeOverridesPanel 	= new JPanel() ;
		  
		 mappingViewListPanel 	= new JPanel() ;
		  
		 selectNewMappingPanel 		= new JPanel() ;
	  	 
		 
		 panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) ) ;
		 
		 panel.add( overlaySelectorPanel	) ;
		 panel.add( nodeOverridesPanel 		) ;
		 panel.add( selectNewMappingPanel 		) ;
		 
		 
		   scrollPane = new JScrollPane( mappingViewListPanel ) ;
		   
		   scrollPane.setPreferredSize( new Dimension( 400, 500 ) ) ;
		 
		 panel.add( scrollPane 	) ;


		 mappingViewListPanel.setLayout( new BoxLayout( mappingViewListPanel, BoxLayout.Y_AXIS ) ) ;
		 
		 
//		 TestMappingView jean = new TestMappingView( "Mappings") ;
//		 mappingViewListPanel.add( jean.getPanel() ) ;
	//	 mappingViewListPanel.setSize( 1000, 800 ) ;
		 mappingViewListPanel.setPreferredSize( new Dimension( 400, 800 ) )  ;
		 
		
		 
		 
		 nodeOverridesLabel = new JLabel( "Override node view exceptions?" ) ;
		 
		 nodeOverridesCheck = new JCheckBox() ;

		 
		 nodeOverridesPanel.add( nodeOverridesLabel ) ;
		 nodeOverridesPanel.add( nodeOverridesCheck ) ;
		 
		 
		 
		 selectNewMappingComboBox = new JComboBox( new String[] { "property mapping", "module mapping", "range", "circular", "constant" } ) ;
		 
		 selectNewMappingPanel.add(  selectNewMappingComboBox ) ;
		 
		 selectNewMappingComboBox.addActionListener( this ) ;
 	  	}
	  
	  
	  public NetworkMappingManagerView(
			   ModelManagerInterface modelManager )
		  {
		   this() ;
		   this.modelManager = modelManager ;
		   
		   overlayTextLabel = new JLabel( "Show labels?" ) ;
			 
			 overlayTextSelector = new ComboBoxMenu( new PropertySetToOptionTreeAdapter( modelManager.overlayTextSet() ) ) ;
			 
			 
			 overlaySelectorPanel.add( overlayTextLabel ) ;
			 overlaySelectorPanel.add( overlayTextSelector.getPanel() ) ;
			 
			 overlayTextSelector.addActionListener( this ) ;
			 
			 
		  }


	 void addMappingView( MappingViewInterface view )
		   {
		    mappingViewListPanel.add( view.getPanel()  ) ;
		    
		    panel.validate() ;
		   }
	
	  
	  void removeMappingView( MappingViewInterface view )
		   {
		   mappingViewListPanel.remove( view.getPanel()  ) ;


		   panel.validate() ;
		   panel.repaint() ;

		   }


	 public JPanel getPanel()
		  {
		  return panel ;
		  }

	 
	 public void initXYZ()
		  {
		  addInitialisedPropertyMappingView( "x", "x" ) ;
		  addInitialisedPropertyMappingView( "y", "y" ) ;
		  addInitialisedPropertyMappingView( "z", "z" ) ;

		  }
	 
	 void addInitialisedPropertyMappingView( String sink, String source )
		  {

		   
		   PropertyPathInterface path = modelManager.getPathTo( source ) ;		   

		   PropertyPathInterface sinkPath = new PropertyPath() ;
		   
		   sinkPath.add( sink ) ;
		   
		   
		   
		   if( path != null && modelManager.freeViewPropertySet().containsPath( sinkPath ) )
				{
			     PropertyMappingView propertyView =  new PropertyMappingView( this, modelManager ) ;

				 propertyView.setSourcePath( path ) ;

				 propertyView.setSinkPath( sinkPath ) ;

				 addMappingView( propertyView ) ;
				 
				 
				 propertyView.initSourceExtremes() ;
				 propertyView.resetMapping() ;
				}
		   
		  }

	 public void actionPerformed( ActionEvent e)
		  {
			if( e.getSource() == this.overlayTextSelector )	
				 {
				  modelManager.setStringToPath(  overlayTextSelector.getPath() ) ;

				 
				 }
		   
			if( e.getSource() == this.selectNewMappingComboBox )	
				 {
				  if( this.modelManager.freeViewPropertySet().propertyIdSet().size() < 1 ) return ;

				 
					JComboBox jcmbType = (JComboBox) e.getSource();
					String cmbType = (String) jcmbType.getSelectedItem();
					
					if( cmbType.equals( "property mapping" ) ) 
						addMappingView( new PropertyMappingView( this, modelManager ) ) ;
					
					if( cmbType.equals( "module mapping" ) ) 
						addMappingView( new ModuleMappingView( this, modelManager ) ) ;
					
					if( cmbType.equals( "range" ) ) 
							addMappingView( new InRangeView( this, modelManager ) ) ;
				
					
					if( cmbType.equals( "circular" ) ) 
						 {
						  if( this.modelManager.freeViewPropertySet().propertyIdSet().size() < 2 ) return ;

							addMappingView( new CircularMappingView( this, modelManager ) ) ;
						 }
					if( cmbType.equals( "constant" ) ) 
							addMappingView( new ConstantView( this, modelManager ) ) ;
					
				
					
					System.out.println( cmbType ) ;
				 }
		  }
	 }
