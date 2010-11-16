package Network.Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Gui.ComboBoxMenu;
import Gui.FloatField;
import Gui.IntegerField;
import Network.ModelManagerInterface;
import Network.PropertyPathInterface;
import Network.PropertySetToOptionTreeAdapter;
import Network.Mapping.InRangeMapping;
import Network.Mapping.NetworkMappingInterface;

public class InRangeView implements MappingViewInterface, ActionListener
	 {
	  JPanel panel ;
	 
	  ModelManagerInterface modelManager 			;
	  
	  NetworkMappingManagerView mappingManager 		;
	  
	  
	  NetworkMappingInterface mapping ;
	  
	  
	  JPanel					mappingPanel		;
	  
	  	ViewPropertySelector		sink 			;	JLabel	sinkLabel 	;
	  
	  	ComboBoxMenu				source			;	JLabel	sourceLabel ;
  
	  JPanel					modePanel			;
	  
	  	ButtonGroup				mode				;
	  
	  	JRadioButton				highestX		;	JLabel highestLabel ;
	  	JRadioButton				lowestX			;	JLabel 	lowestLabel ;
	  	JRadioButton				rangeX			; 	JLabel	 rangeLabel	;
		  
	  
	  JPanel					rangePanel			;
	  
	  	FloatField					minValue		;	JLabel minLabel ;
	  
	  	FloatField					maxValue		;	JLabel maxLabel ;
	  
	  	IntegerField				itemCount		; 	JLabel itemCountLabel ;
	  
	  
	  JButton		removeMapping	 ;

	  JPanel highestModePanel ;

	  JPanel lowestModePanel  ;

	  JPanel rangeModePanel   ;
 

	  
	  public InRangeView( NetworkMappingManagerView networkMappingManagerView,
			   ModelManagerInterface modelManager )
		  {
		   this.modelManager = modelManager ;
		   
		   this.mappingManager = networkMappingManagerView ;
		   
		   initGui() ;
		  }
	  void initGui()
		  {
		   panel = new JPanel() ;
		   
		  	 panel.setPreferredSize( new Dimension( 600, 210  ) ) ;
		  	 
		  	 panel.setMaximumSize( panel.getPreferredSize() ) ;
		  	 panel.setMinimumSize( panel.getPreferredSize() ) ;
		  	 
		   
		   initMappingPanel() ;
		   initModePanel() ;
		   initRangePanel() ;
		   
		   removeMapping = new JButton( "X" ) ;
		   panel.add(  removeMapping  ) ;
		  	 
		   removeMapping.addActionListener( this ) ;
		  }
	  
	  void initMappingPanel()
		  {
		   mappingPanel = new JPanel() ;
		   
		   
		   
		   sourceLabel = new JLabel( "source " ) ;
		   
		   sinkLabel = new JLabel( "sink " ) ;
		   
		   sink 	= new ViewPropertySelector(  modelManager ) ;

		   source 	= new ComboBoxMenu( new PropertySetToOptionTreeAdapter( modelManager.availablePropertySet() ) ) ;

		   
		   PropertyPathInterface sinkPath = sink.getPath() ;
		   
		   // modelManager.useViewProperty( sinkPath ) ;

		   
		   mappingPanel.add(  sinkLabel 		) ;
		   mappingPanel.add( 	sink.getPanel()	) ;
		   
		   mappingPanel.add(  sourceLabel 		) ;
		   mappingPanel.add( source.getPanel() 	) ;
		   
		   source.addActionListener( 	this ) ;
		   sink.addActionListener( 		this ) ;
		   
		   panel.add( mappingPanel ) ;
		  }
	  
	  void initModePanel()
		  {
		   modePanel = new JPanel() ;
		   
		   modePanel.setLayout( new BoxLayout( modePanel, BoxLayout.Y_AXIS ) ) ;

		   
		   highestLabel = new JLabel( "highest" ) ;
		   lowestLabel 	= new JLabel( "lowest"  ) ;
		   rangeLabel   = new JLabel( "range"	) ;
		   
		   highestX		= new JRadioButton() ;
		   lowestX		= new JRadioButton() ;
		   rangeX		= new JRadioButton() ;
		   
		   highestModePanel = new JPanel() ;
		   lowestModePanel	= new JPanel() ;
		   rangeModePanel	= new JPanel() ;
		   
		   highestModePanel.add(  highestLabel ) ; highestModePanel.add( highestX ) ;
		   lowestModePanel.add(  lowestLabel ) ; lowestModePanel.add( lowestX ) ;
		   rangeModePanel.add(  rangeLabel ) ; rangeModePanel.add( rangeX ) ;
		   
		   mode = new ButtonGroup() ;
		   
		   mode.add( highestX ) ;
		   mode.add( lowestX  ) ;
		   mode.add( rangeX   ) ;
		   
		   
		   
		   modePanel.add(  highestModePanel ) ;// modePanel.add( highestX ) ;
		   modePanel.add(  lowestModePanel  ) ;// modePanel.add( lowestX  ) ;
		   modePanel.add(  rangeModePanel   ) ;// modePanel.add( rangeX   ) ;
		   
		   highestX.addActionListener( this ) ;
		   lowestX.addActionListener( this )  ;
		   rangeX.addActionListener( this )   ;
		   
		   panel.add(  modePanel ) ;
		   
		   rangeX.setSelected(  true  ) ;
		  }
	  
	  
	  void initRangePanel()
		  {
		   rangePanel = new JPanel() ;
		   
		   minLabel = new JLabel( "min" ) ;
		   maxLabel = new JLabel( "max" ) ;
		   
		   itemCountLabel = new JLabel( "values in range" ) ;
		   
		   minValue = new FloatField( Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY) ;
		   maxValue = new FloatField( Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY) ;
		   
		   minValue.setValue( 0.5f ) ;
		   maxValue.setValue( 1f ) ;

		   
		   itemCount = new IntegerField( 0, modelManager.modelCount() ) ;
		   
		   resetItemCount() ;
		   
		   rangePanel.add(  minLabel   ) ; rangePanel.add(  minValue.getPanel() ) ;
		   rangePanel.add(  maxLabel   ) ; rangePanel.add(  maxValue.getPanel() ) ;
		   rangePanel.add(  itemCountLabel ) ; rangePanel.add(  itemCount.getPanel() ) ;
		   
		   minValue.addActionListener( this ) ;
		   maxValue.addActionListener( this ) ;
		   
		   itemCount.addActionListener( this ) ;

		   panel.add( rangePanel ) ;
		  }
	  private void resetItemCount()
		  {
		   if( minValue.peekValue() == null || maxValue.peekValue() == null ) return ;
		   
		   float min = minValue.acceptValue() ;
		   float max = maxValue.acceptValue() ;
		   
		   int itemCountVal = modelManager.valuesCountedInRange( source.getPath(), min, max ) ;
		   
		   itemCount.setValueNoEvent( itemCountVal ) ;
		   
		   itemCount.acceptValue() ;
				
		  }
	 public JPanel getPanel()
		  {
		   return panel ;
		  }
	 public void actionPerformed( ActionEvent e )
		  {
		  
		   if( e.getSource() == source )
				{
				 System.out.println( "source" ) ;

				 resetMapping() ;
				}
		   
		   if( e.getSource() == sink )
				{
				 System.out.println( "sink" ) ;

				 // updateSinkProperty() ;
				 
				 resetMapping() ;
				}
		   
		   
		   if( e.getSource() == removeMapping )
				{
				 System.out.println( "removeMapping" ) ;

				 removeSelf() ;
				 return ;
				}
		   
		   if( e.getSource() == minValue || e.getSource() == maxValue )
				{
				 rangeX.setSelected( true ) ;
				 
				 resetItemCount() ;
				 
				 System.out.println( "min or max value" ) ;
				
				 return ;
				}
		   

		   
		   if( e.getSource() == highestX || e.getSource() == lowestX || e.getSource() == rangeX )
				{
				 System.out.println( "mode" ) ;

				
				 resetMapping() ;				
				}	   		
		   
		   if( e.getSource() == itemCount )
				{
				 System.out.println( "itemCount" ) ;

				 if(  rangeX.isSelected() )
					  highestX.setSelected( true  )  ;
				 
				 resetMapping() ;
				 return ;
				}
		  }
	 
	 /*
	 private void updateSinkProperty()
		  {
		  
		   PropertyPathInterface sinkPath = sink.getPath() ;
		    
		   if(  sink.getPreviousPath() != null )
				modelManager.freeViewProperty( sink.getPreviousPath() ) ;
		   
		   modelManager.useViewProperty( sinkPath ) ;
		  }*/
	 private void resetMapping()
		  {
		   if( highestX.isSelected() )
				{
				 setRangeToHighestX() ;
				 
				}
		   
		   if( lowestX.isSelected() )
				{
				 setRangeToLowestX() ; 

				}
		   
		   if( rangeX.isSelected() )
				{
				 resetItemCount() ;			
				}
		   
		   NetworkMappingInterface mapping = buildMapping() ;
		   
		   modelManager.addMapping( mapping ) ;
		  }
	 
	 
	 
	 private NetworkMappingInterface buildMapping()
		  {
		   if( minValue.peekValue() == null || maxValue.peekValue() == null ) return null ;
		  
		   return new InRangeMapping( sink.getPath(), source.getPath(), minValue.acceptValue(), maxValue.acceptValue() ) ;
		  
		  }
	 private void setRangeToLowestX()
		  {
				  
		   System.out.println( "Set range to highest x" ) ;
		   if( itemCount.peekValue() == null ) return ;
		  
		   maxValue.setValue( modelManager.thresholdForLowestX( source.getPath(), itemCount.acceptValue() ) ) ;
		   minValue.setValue( modelManager.lowestValue( source.getPath() ) ) ;		
		   
		   resetItemCount() ;
		  }
	 private void setRangeToHighestX()
		  {
		   System.out.println( "Set range to highest x" ) ;
		   if( itemCount.peekValue() == null ) return ;
		  
		   minValue.setValue( modelManager.thresholdForHighestX( source.getPath(), itemCount.acceptValue() ) ) ;
		   maxValue.setValue( modelManager.highestValue( source.getPath() ) ) ;
		   
		   resetItemCount() ;

		  }
	 private void removeSelf()
		  {
			 // manager.removeMapping( mapping ) ;
			 
		   // modelManager.freeViewProperty( sink.getPath() ) ;

		   sink.freeViewProperty() ;
		  
		   mappingManager.removeMappingView( this ) ;		  
		  }

	 }
