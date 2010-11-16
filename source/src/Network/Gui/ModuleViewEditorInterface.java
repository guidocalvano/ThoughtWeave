package Network.Gui;

import javax.swing.JPanel;


public interface ModuleViewEditorInterface
	 {

	  JPanel getPanel() ;

	  
	  double symbol() ;
	  
	  double[] valueArray() ;
	  
	 void addValue( double value );
	 
	 void removeValue();

	 }
