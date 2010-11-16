package Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import Main.NetworkDataControl;


public class IntegerField implements KeyListener, ActionListener
	 {
	  int value ;
	  
	  int upperLimit ;
	  int lowerLimit ;
	  
	  int 	state ;
	  
	  static final int ERROR 	= 0	;
	  static final int CORRECT 	= 1 ;
	  
	  JTextField valueTextField ;
	  
	  Document 	 integerDocument ;
	  
	  JPanel panel ;
	  
	  
	  
		 private Color updatedColor;

		 private Color correctColor;

		 private Color errorColor;
	  
	  String formerText ;
	  
	  List actionListenerSet ;
	  
	  public IntegerField( int lowerLimit, int upperLimit )
	  	{
	  	 panel = new JPanel() ;
	  	 valueTextField = new JTextField( 7 ) ;
	  	 
	  	 valueTextField.setDocument( new IntegerDocument( this )  ) ;
	  	 
	  	 valueTextField.addKeyListener( this ) ;
	  	 
	  	 valueTextField.addActionListener( this ) ;
	  	 
	  	 panel.add( valueTextField ) ;
 	  	 
	  	 updatedColor = new Color( .9f, .9f, 1.0f  ) ;
		   
		 correctColor = new Color( .9f, 1f, .9f  ) ;

		 errorColor = new Color( 1.0f, .9f, .9f ) ;
		 
		 this.upperLimit = upperLimit ;
		 this.lowerLimit = lowerLimit ;
	  	 
		 state = ERROR ;
		 
		 valueTextField.setBackground( errorColor ) ;
		 
		 actionListenerSet = new Vector() ;
	  	}
	  
	  public JPanel getPanel() { return panel ; } 


	 public void keyPressed( KeyEvent e)
		  {
		  
		  }


	 public void keyReleased( KeyEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }


	 public void keyTyped( KeyEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }
	 
	  class IntegerDocument extends PlainDocument 
	  	{
	  	 String  previousAllowedString 			;
	  	 int	 previousAllowedCaretPosition	;

	  	 
	  	 
	  	 IntegerField owner ;
	  	 
	  	 IntegerDocument( IntegerField owner ) { super() ;  this.owner = owner ; saveState() ; } 
	  	 
         public void insertString( int offs, String str, AttributeSet a)	throws BadLocationException 
         	{

         	 if( str == null ) 
         		{
         	 	 return ;
         	 	}
     
             
         	 super.insertString(offs, str, a);
         	          	 
         	 acceptOrUndo() ;
         	}
         
         void acceptOrUndo()
        	{
        	 String text = getString() ;
        	 if( "".equals( text ) || "-".equals( text ) )
        		  {
        		   condoneState() ;
        		   return ;
        		  }
        		   
        	 
        	 if( text.charAt( text.length() -1 ) == '.' )
        		  {
        		   condoneState() ;
        		   return ;
        		  }
        	 
        	 try{ 
        	 	value = Integer.parseInt( text ) ; 
        	 	if( !Character.isDigit( text.charAt( text.length() - 1 ) ) ) 
        	 		 { super.remove( text.length() - 1, 1 ) ; } 
        	 	
        	 	 if( value > upperLimit || value < lowerLimit )
        	 		  {
        	 		   condoneState() ;
        	 		   return ;
        	 		  }
        	 	} 
        	 catch( Exception e ) { undo() ; return ; }
        	 
        	 acceptState() ;
        	}
         void acceptState() { saveState() ; owner.valueTextField.setBackground( correctColor ) ; state = CORRECT ; }
         
         void undo() 
        	  {
        	   loadState() ;
        	  }
         void condoneState()
        	{
        	 owner.valueTextField.setBackground( errorColor ) ;
        	 state = ERROR ;
        	}
         
  
         
         void saveState()
        	{
        	 previousAllowedString = getString() ; 
        	 

        	}
         
         void loadState()
          	{
          	 try{
          	 int currentCaretPosition = owner.valueTextField.getCaretPosition() - 1 ;

          	 
          	 clearString() ;
          	
          	 super.insertString( 0, previousAllowedString, null ) ;

          	 if( currentCaretPosition < previousAllowedString.length() && currentCaretPosition >= 0)
          		  owner.valueTextField.setCaretPosition( currentCaretPosition ) ;
          	 
          	 acceptOrUndo() ;
          	 
          	 }
          	  catch( Exception e ) {}
          	 
          	}    
         
         String getString()
        	{
        	 try{
       	 
       	 	 String string = getText( 0, getLength() ) ;   
       	 
       	 	  return string ;
        	 }
       	  catch( Exception e ) { return null ; } 
        	  
        	}
         
         public void remove(int offs,
                   int len)
            throws BadLocationException
            {
             super.remove( offs, len ) ;
            
             acceptOrUndo() ;
            }
  
         public void replace(int offset,
                   int length,
                   String text,
                   AttributeSet attrs)
            throws BadLocationException
            {
             super.remove( offset, length ) ;
             
             this.insertString( offset, text, null ) ;
            }
         
         void clearString()
        	{
        	 try{ super.remove( 0, getLength() ) ; } catch( Exception e ) {}  
        	 
        	}
	  	}
	  
	  
	 public void addActionListener(
			   ActionListener listener )
		  {
		   actionListenerSet.add( listener ) ;
		  }
	  
	 public void actionPerformed( ActionEvent e)
		  {
		   e.setSource(  this ) ;
		  
		   Iterator it = actionListenerSet.iterator() ;
		   
		   while( it.hasNext() )
				( (ActionListener) it.next() ).actionPerformed( e ) ;
		  }
		 
	  public Integer peekValue()
		   {
		    if( state == CORRECT )
		    	 {
				  return new Integer( value ) ;
		    	 }
		    else return null ;
		   }
	  
	  public Integer acceptValue()
		   {
		    if( state == CORRECT )
		    	 {
				  valueTextField.setBackground( updatedColor ) ;		    	 
				  return new Integer( value ) ;
		    	 }
		    else return null ;
		   }
	  
		 public void setValue( int value )
			  {
			   valueTextField.setText( ( new Integer( value ) ).toString() ) ;
			  }
		 
		 public void setValueNoEvent( int value )
			  {
			   try
					{
					 if( valueTextField.getDocument() == null ) System.out.println( "int doc == null " ) ;
					
					 valueTextField.getDocument().remove( 0, valueTextField.getDocument().getLength() ) ;
					 valueTextField.getDocument().insertString( 0, ( new Integer( value ) ).toString() , null ) ;
					} catch( BadLocationException e )
							{
							// TODO Auto-generated catch block
						e.printStackTrace();
							}
			  }
	 }
