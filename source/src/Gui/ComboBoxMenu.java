package Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Network.PropertyPathInterface;


public class ComboBoxMenu implements MouseListener, ActionListener
	 {
	  boolean hasPopupExpanded ;
	 
	  JLabel label ;
	  JPanel panel ;
	  
	  JPopupMenu popup ;
	  
	  OptionTreeInterface source ;
	  
	  PropertyPathInterface path ;

	  PropertyPathInterface previousPath ;

	  
	  List actionListeners ;
	  
	  
	  public ComboBoxMenu()
	  	{
	  	 init() ;
	  	}
	  
	  public void init()
		{
	  	 label = new JLabel( "    " ) ;
		   
		 label.setSize( 150, 15 ) ;
		 label.setMinimumSize( new Dimension( 150, 15) ) ;
		 label.setOpaque( true ) ;
		 label.setBackground( new Color( 1f, 1f, 1f ) ) ;
			   
		 label.addMouseListener( this ) ;
		 
		 hasPopupExpanded = false ;
		 
		 panel = new JPanel() ;
		 panel.add( label ) ;
		 
		 actionListeners = new Vector() ;
		}
	  
	  
	  public ComboBoxMenu( OptionTreeInterface source )
		{
	   	 init( source ) ;
	  	}
	  public void init( OptionTreeInterface source )
		{
		 init() ;
		 this.source = source ;
	   
		 selectFirst() ;
		}
	  
	 public void selectFirst()
		  {
		   selectFirst( source.children() ) ;
		  
		  }
	 
	 public void selectFirst( List children )
		  {
		   Iterator it = children.iterator() ;
		   
		   while( it.hasNext() )
				{
				 OptionTreeInterface option = (OptionTreeInterface) it.next() ;
				
				 if( option.children().size() == 0 )
					  {
					   ActionEvent e = new ActionEvent( this, 0, option.name() ) ;
					   setText( option.name() ) ;
					   
					   setPath( option.path() ) ;
					   
					   actionPerformed( e ) ;
					  }
				 else
					  selectFirst( option.children() ) ;
				}
		  
		  }
	  
	 public JPanel getPanel() { return panel ; } 
	 
	 public PropertyPathInterface getPath() { return path ; }
	 
	 protected void setPath( PropertyPathInterface path ) { previousPath = this.path ; this.path = path ; } 
	 
	 public PropertyPathInterface getPreviousPath() { return previousPath ; }

	 
	 String getText() { return label.getText() ; }
	 
	 void setText( String text ) { label.setText( text ) ; }
	  
	  
	 public void buildPopupMenu()
		  {
		  
		   popup = new JPopupMenu() ;
		   
		   
		   
		   JMenu menu = buildMenu( source ) ;
		   
		   
		   Component[] componentAt = menu.getMenuComponents() ;
		   
		   for( int i = 0 ; i < componentAt.length ; i++ )
				popup.add( (JMenuItem)componentAt[ i ] ) ;
		   
		   popup.show( label, 0, label.getHeight() ) ;
		   hasPopupExpanded = true ;
		  }

	 
	 class SetComboBoxAction extends AbstractAction 
	 	{
	 	 ComboBoxMenu menu ;
	 	 String text ;
	 	 PropertyPathInterface  path ;
	 	 
	 	 
	 	 SetComboBoxAction( ComboBoxMenu menu, String text, PropertyPathInterface path )
	 	 	{
	 	 	 this.menu = menu ;
	 	 	 this.text = text ;
	 	 	 this.path = path ;
	 	 	}

		  public void actionPerformed( ActionEvent e)
			   {
			    menu.setPath( path ) ;
			    menu.setText( text ) ;  
			    
			    System.out.println( "text = " + text ) ;
			    
			    Iterator it = path.pathIterator() ;
			    
			    while( it.hasNext() )  System.out.println( "->" + (String) it.next() ) ;
			    e.setSource( menu ) ;
			    
			    menu.actionPerformed( e ) ;
			   }
	 
	 	}
	 
	 private JMenu buildMenu( OptionTreeInterface source )
		  {
		   List optionList = source.children() ;
		   
		   
		   Iterator it = optionList.iterator() ;
		   
		   JMenu menu = new JMenu( source.name() ) ; 
		   
		   while( it.hasNext() )
				{
				 OptionTreeInterface child = (OptionTreeInterface) it.next() ;
				 
				 List children = child.children() ;
				 
				 if( children.size() == 0 )
					  {
					  
					   JMenuItem item =new JMenuItem( child.name() ) ;
					   menu.add( item  ) ;
						
					//   item.setActionCommand( source.name() ) ;
					   item.addActionListener( new SetComboBoxAction( this, child.name(), child.path() ) ) ;
					  }
				 else
					  {
					   JMenu childMenu = buildMenu( child ) ;
					   
					   menu.add(  childMenu ) ;
					  }
				 
				}
		   
		   return menu ;
/*		   
		   if( optionList.size() == 0 )
				{
				 JMenuItem item =  new JMenuItem( source.name() ) ;
				 item.setActionCommand( source.name() ) ;
				 item.addActionListener( this ) ;
				 
				 return item ;
				}
		  
		   JMenu menu = new JMenu( source.name() ) ;
		   
		   Iterator it = optionList.iterator() ;
		   
		   while( it.hasNext() )
				menu.add( buildMenu( (OptionTreeInterface) it.next() ) ) ;
		   
		   return menu ;*/
		  }

	 public void mouseClicked( MouseEvent e)
		  {
		   buildPopupMenu( ) ;
		  }

	 public void mouseEntered( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseExited( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mousePressed( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseReleased( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void actionPerformed( ActionEvent e)
		  {
		   label.setText( e.getActionCommand() ) ;
		   
		   Iterator it = actionListeners.iterator() ;
		   
		   while( it.hasNext() )
				{
				 ( (ActionListener) it.next() ).actionPerformed( e ) ;
				}
		  }
	 
	 public void fireActionPerformed()
		  {
		   Iterator it = actionListeners.iterator() ;
		   
		   ActionEvent e = new ActionEvent( this, -1, label.getText() ) ;
		   
		   while( it.hasNext() )
				{
				 ( (ActionListener) it.next() ).actionPerformed( e ) ;
				}
		  }
	 
	 public void addActionListener( ActionListener listener )
		  {
		   actionListeners.add(  listener ) ;
		  
		  }

	 public void select( PropertyPathInterface path)
		  {
		   Iterator it = path.pathIterator() ;
		  
		   String string = "error" ; 
		   
		   while( it.hasNext() ) string = (String) it.next() ;
		   this.setText( string ) ;
		  
		   setPath( path ) ; // = path ;
		  }
	 }
