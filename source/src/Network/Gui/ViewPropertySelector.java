package Network.Gui;

import Gui.ComboBoxMenu;
import Network.ModelManagerInterface;
import Network.PropertyPathInterface;
import Network.PropertySetInterface;
import Network.PropertySetToOptionTreeAdapter;

 public class ViewPropertySelector extends ComboBoxMenu
	 {
	  ModelManagerInterface viewPropertyManager ;
	 
	  ViewPropertySelector( ModelManagerInterface viewPropertyManager )
	  	{	  	 
	  	 this.viewPropertyManager = viewPropertyManager ;

	  	 init( new PropertySetToOptionTreeAdapter( viewPropertyManager.freeViewPropertySet() ) ) ;
	  	}
	 
	 
	  
	  protected void setPath( PropertyPathInterface path )
		   {
		    if( getPath() != null )
		    	 viewPropertyManager.freeViewProperty( getPath() ) ;
		    
		    if( path != null )
		    	 viewPropertyManager.useViewProperty( path ) ;
		    
		    super.setPath( path ) ;
		   }
	  
	  public void freeViewProperty()
		   {
		    viewPropertyManager.freeViewProperty( getPath() ) ;
		   
		   }
	 }
