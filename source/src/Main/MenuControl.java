package Main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuControl
	 {
	  JMenuBar 	menuBar ;
	  
	  JMenu		applicationMenu	;
	  JMenu		fileMenu		;

	  JMenuItem	save			;
	  
	  public MenuControl()
		   {
		    menuBar = new JMenuBar() ;
		    
		    applicationMenu = new JMenu( "Application" ) ;
		    
		    fileMenu		= new JMenu( "file" ) ;
		    
		    save			= new JMenuItem( "save" ) ;
		    
		    menuBar.add( applicationMenu ) ;
		    menuBar.add( fileMenu ) ;
		    
		    fileMenu.add(  save ) ;
		    
		   }
	  
	  public JMenuBar getMenuBar() { return menuBar ; } 
	  
	  
	  void loadMenu( JMenu menu )
		   {
		   
		    for( int i = 0 ; i < menuBar.getMenuCount() ; i++ )
		    for( int j = 0 ; j < menu.getItemCount()    ; j++ )
		    	 {
		    	 
		    	  if( menuBar.getMenu( i ).getText() == menu.getItem( j ).getText() )
		    		   {
		    		   
		    		   }
		    	 }
		   }
	  
	  void mergeMenu( JMenu inBar, JMenu foreign )
		   {
		    for( int j = 0 ; j < foreign.getItemCount()    ; j++ )
		    	 {
		    	  if( !intoBar( inBar, foreign.getItem( j ) ) )
		    		   {
		    		    inBar.add( foreign.getItem( j ) ) ; 
		    		   }
		    	 }
		   }

	 private boolean intoBar( JMenu inBar, JMenuItem item)
		  {
		   if( item.getClass() == JMenuItem.class )
				{
				 inBar.add(  item ) ;
				 
				 return true ;
				}

		   JMenu menu = (JMenu) item ;
		   
		   for( int i = 0 ; i < inBar.getItemCount() ; i++ )
				{
				 if( menu.getText().equals( inBar.getItem( i ).getText() ) && inBar.getItem( i ).getClass() == JMenu.class )
					  {
					    mergeMenu( (JMenu) inBar.getItem( i ), menu ) ;
					    return true ;
					  }
				}
		  
		  
		   inBar.add( menu ) ;
		   
		   return false;
		  }
	 }
