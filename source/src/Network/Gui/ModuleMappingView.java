package Network.Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Gui.ComboBoxMenu;
import Network.ModelInterface;
import Network.ModelManagerInterface;
import Network.NodeModelInterface;
import Network.NodeModelManagerInterface;
import Network.PropertyPath;
import Network.PropertyPathInterface;
import Network.PropertySetToOptionTreeAdapter;
import Network.Mapping.HyperCube;
import Network.Mapping.ModuleMapping;
import Network.Mapping.NetworkMappingInterface;


public class ModuleMappingView implements MappingViewInterface, ActionListener
	 {
	  ModelManagerInterface modelManager ;
	  
	  NetworkMappingManagerView		mappingManagerView ;
	  
	  NetworkMappingInterface		currentMapping ; 	  
	 
	//  JPanel panelRoot ;



	  JPanel panel ;

	  
	  JPanel sourceInputPanel ;
	  
	  JPanel visualizationPropertyPanel ;
	  
	  JPanel moduleListPanel ;
	  
	  JScrollPane	scrollPane ;	  

	  
	  
	  
	  JButton 		addColumn ;
	  
	  JLabel 		sourceInputLabel 	;
	  ComboBoxMenu	sourceInputSelector ;
	  
	  JButton		removeMapping	;

	  
	  JLabel 	moduleColumnLabel ;
	  
	  List		viewPropertyColumnSelectorSet ;
	  
	  List		moduleEditorList				;
	  
	  JButton	addColumnButton 	;
	  JButton	removeColumnButton 	;
	  
	  
	  
	  public ModuleMappingView(
			   NetworkMappingManagerView networkMappingManagerView,
			   ModelManagerInterface nodeModelManager )
		  {
		  
		   this.modelManager = nodeModelManager ;
		   
		   this.mappingManagerView = networkMappingManagerView ;
		   
			 // panelRoot = new JPanel() ;

			  
			  panel = new JPanel() ;

			  	 panel.setPreferredSize( new Dimension( 500, 410  ) ) ;
			  	 
			  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
			  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
			  	 

			  // panelRoot.add( scrollPane ) ;
			   
			   sourceInputPanel  = new JPanel() ;
			  
			   visualizationPropertyPanel  = new JPanel() ;
			  
			   moduleListPanel  = new JPanel() ;
			   
				 panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) ) ;
			   
			   
			   moduleListPanel.setLayout( new BoxLayout( moduleListPanel, BoxLayout.Y_AXIS ) ) ;
			   
			  panel.add( sourceInputPanel )  ;
			  panel.add( visualizationPropertyPanel )  ;
			  
			  scrollPane = new JScrollPane( moduleListPanel ) ;
			   
			  
			   scrollPane.setPreferredSize( new Dimension( 500, 300 ) ) ;

			   scrollPane.setMaximumSize( new Dimension( 500, 300 ) ) ;

			//   moduleListPanel.setPreferredSize( new Dimension( 500, 450 ) ) ;
			   
			  			   
			  panel.add( scrollPane )  ;
			  
		  
			  
			  sourceInputLabel 		= new JLabel( "Source" ) ;
			  sourceInputSelector	= new ComboBoxMenu( new PropertySetToOptionTreeAdapter( modelManager.availablePropertySet() ) ) ;
			  
			  sourceInputSelector.addActionListener( this ) ;
			  
			  removeMapping = new JButton( "X" ) ;
			  removeMapping.addActionListener( this ) ;
			  
			  
			  addColumnButton = new JButton( "add column" ) ;
			  removeColumnButton = new JButton( "remove column" ) ;
			  
			  addColumnButton.setActionCommand( "addColumn" ) ;
			  removeColumnButton.setActionCommand( "removeColumn" ) ;
			  
			  addColumnButton.addActionListener( this ) ;
			  removeColumnButton.addActionListener( this ) ;
			  
			  sourceInputPanel.add( sourceInputLabel ) ;
			  sourceInputPanel.add( sourceInputSelector.getPanel() ) ;
			  			  
			  sourceInputPanel.add( addColumnButton ) ;
			  sourceInputPanel.add( removeColumnButton ) ;
			  
			  sourceInputPanel.add(  removeMapping  ) ;

			  
			  moduleColumnLabel = new JLabel( "module id" ) ;
			  
			  visualizationPropertyPanel.add( moduleColumnLabel ) ;
			  viewPropertyColumnSelectorSet = new Vector() ;
			  
			  moduleEditorList = new Vector() ;
			  /*
			  
			  for( int i = 5 ; i > 0 ; i-- )
				   {
				    ModuleViewEditorInterface next = new ModuleViewEditor( Math.floor( Math.random() * 30 ), new double[] {} ) ;
				   
				    moduleListPanel.add( next.getPanel() ) ;
				    
				    moduleEditorList.add( next ) ;
				   }
			  */
			  PropertyPathInterface redPath = new PropertyPath() ;
			  
			  redPath.add( "red" ) ;
			  
			  
			  PropertyPathInterface greenPath = new PropertyPath() ;
			  
			  greenPath.add( "green" ) ;
			  
			  PropertyPathInterface bluePath = new PropertyPath() ;
			  
			  bluePath.add( "blue" ) ;
			  
			  if( modelManager.freeViewPropertySet().containsPath( redPath  ) )
				   
				   addVisualizationPropertySelector( redPath ) ;

			  if( modelManager.freeViewPropertySet().containsPath( greenPath  ) )

				   addVisualizationPropertySelector( greenPath ) ;
			  
			  if( modelManager.freeViewPropertySet().containsPath( bluePath  ) )

				   addVisualizationPropertySelector( bluePath ) ;
			  

			 
			 
			  
			 moduleMappingInit() ;
			 
			//   mappingToModelManager() ;

			 
			    panel.validate();
			    panel.repaint() ;
		  }
	  
	 class SwitchUsedPropertyAction extends AbstractAction
	 	{
	 	 PropertyPathInterface oldProperty ;
	 	
	 	 ComboBoxMenu 			newPropertySelector ;
	 	 
	 	 
	 	 SwitchUsedPropertyAction( ComboBoxMenu newPropertySelector )
	 		{/*
		     oldProperty =  newPropertySelector.getPath()  ;
	 		 this.newPropertySelector = newPropertySelector ;
	 		 
			    modelManager.useViewProperty( newPropertySelector.getPath() ) ;
*/
	 		}
	 	
		  public void actionPerformed( ActionEvent e)
			   {
			   /*
			    modelManager.freeViewProperty( oldProperty ) ;
			    			    
			    oldProperty =  newPropertySelector.getPath()  ;
			    
			    modelManager.useViewProperty( newPropertySelector.getPath() ) ;
			    */
 			    mappingToModelManager() ;
   
			   }
	 	 
	 	}

	 void addVisualizationPropertySelector( PropertyPathInterface path )
		   {
		    ViewPropertySelector next = new ViewPropertySelector( modelManager ) ;
		    
		   // next.setSelectedItem( visualizationPropertyName ) ;
		    
		    if( path != null ) next.select( path ) ;
		    
		    next.addActionListener( new SwitchUsedPropertyAction( next ) ) ;
		    
		    visualizationPropertyPanel.add( next.getPanel() ) ;
		    
		    viewPropertyColumnSelectorSet.add( next ) ;
		    
		   // modelManager.useViewProperty( next.getPath() ) ;

		   }
	  
	  void addColumn()
		   {
		    addVisualizationPropertySelector( null ) ;

		    
		    this.moduleMappingInit() ;
		    
		    panel.validate();
		    panel.repaint() ;

		   }

	  
	  
	  void removeColumn( )
		   {
		   
		    removeVisualizationPropertySelector() ;
		   
		    this.moduleMappingInit() ;
		    


		    panel.validate();
		    panel.repaint() ;
		    }
	  
	 private void removeVisualizationPropertySelector()
		  {
		  ViewPropertySelector removeMe = (ViewPropertySelector) viewPropertyColumnSelectorSet.get(  viewPropertyColumnSelectorSet.size() -1  ) ;
		  
		    // modelManager.freeViewProperty( removeMe.getPath() ) ;
	   
		   removeMe.freeViewProperty() ;

		   visualizationPropertyPanel.remove(  removeMe.getPanel()  ) ;
		   
		   viewPropertyColumnSelectorSet.remove( removeMe ) ;
 		  
		  }

	 public JPanel getPanel()
		  {
		  return panel ;
		  }

	 public void actionPerformed( ActionEvent e)
		  {
		   System.out.println( "action = " + e.getActionCommand() ) ;
		   
		   
		   if( e.getSource() == removeMapping )
				{
				// modelManager.removeMapping( currentMapping ) ;
				 
				Iterator it = viewPropertyColumnSelectorSet.iterator() ;
				
				 while( it.hasNext() )
					  {
					   ( (ViewPropertySelector) it.next() ).freeViewProperty() ;
					  }
				 mappingManagerView.removeMappingView( this ) ;
				 
				 return ;
				}
		   
		   if( sourceInputSelector == e.getSource() )
				{
				
				 moduleMappingInit() ;
				}
		   
		  
		   if ("addColumn".equals(e.getActionCommand()))
				{
				 if( this.modelManager.freeViewPropertySet().propertyIdSet().size() < 1 ) return ;
				
				 System.out.println( "adding column now" ) ;
				 addColumn() ;
				}
		   
		   if ("removeColumn".equals(e.getActionCommand())) 
				{
				System.out.println( "removing column now" ) ;

				 removeColumn() ;
				 
				}
		   
		   mappingToModelManager() ;
		  }

	  void moduleMappingInit()
		   {
		    int dimensionCount  = viewPropertyColumnSelectorSet.size() ;
		   
		    List symbolList		= new Vector() ;
		    
		    PropertyPathInterface path = sourceInputSelector.getPath() ;
		   
		    
		    Iterator it = modelManager.iterator() ;
		    
		    
		    while( it.hasNext() )
		    	 {
		    	  ModelInterface node = (ModelInterface) it.next() ;
		    	  double newVal = node.getLibrary().atPath( path ) ;
		    	  
		    	  if( !symbolList.contains( newVal ) )
		    		   
		    		   symbolList.add(  new Double( newVal )  ) ;	    	 
		    	 }
		    
		    HyperCube cube = new HyperCube( symbolList.size(), dimensionCount ) ;
		    
		    buildModuleEditorList( symbolList, cube ) ;
		    
		   }
	  
	  void buildModuleEditorList( List symbolList, HyperCube cube ) 
		   {
		    clearModuleEditorList() ;
		   
		    
		    for( int i = 0 ; i < symbolList.size() ; i++ )
		    	 {
		    	  addModuleEditor( (Double) symbolList.get( i ), cube.getVector( i ) ) ;
		    	 }
		   }

	 private void addModuleEditor( Double module, double[] vector)
		  {
		    ModuleViewEditorInterface next = new ModuleViewEditor( this, module, vector ) ;
			   
		    moduleListPanel.add( next.getPanel() ) ;
		    
		    moduleEditorList.add( next ) ;
		  }

	 private void clearModuleEditorList()
		  {
		   Iterator it = moduleEditorList.iterator() ;
		   
		   while( it.hasNext() )
				{
				 ModuleViewEditor editor = (ModuleViewEditor) it.next() ;
				
				 this.moduleListPanel.remove( editor.getPanel() ) ;
				}
		   
		   moduleEditorList = new Vector() ;
		  }
	 
	 
	  void mappingToModelManager()
		   {
		    NetworkMappingInterface mapping = moduleMapping() ;
		   
		    if( mapping == null ) return ;
		    
		    currentMapping = mapping ;
		   
		    modelManager.addMapping( mapping ) ;
		   
		   }
	 
	 
	  NetworkMappingInterface moduleMapping()
		   {
		    List viewPathList = new Vector() ;
		    
		    Iterator it = viewPropertyColumnSelectorSet.iterator() ;
		    
		    while( it.hasNext() )
		    	 viewPathList.add( ( (ComboBoxMenu) it.next() ).getPath() ) ;
		   
		    Map moduleToView = moduleToViewPropertySet() ;
		    
		    if( moduleToView == null ) return null ;
		    
		    NetworkMappingInterface mapping = new ModuleMapping( sourceInputSelector.getPath(), viewPathList, moduleToView ) ;
		   
		   
		    return mapping ;
		   }

	 private Map moduleToViewPropertySet()
		  {
		   Map map = new HashMap() ;
		   
		   Iterator it = moduleEditorList.iterator() ;
		  
		   
		   while( it.hasNext() )
				{
				 ModuleViewEditorInterface edit = (ModuleViewEditor) it.next() ;
				
				 double[] valueArray = edit.valueArray() ;
				  
				 if( valueArray == null ) return null ;
				 
				  map.put( edit.symbol(), valueArray ) ;
				}
		   
		   return map ;
		  }
	 }
