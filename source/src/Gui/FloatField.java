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

import Network.Gui.PropertyOverrideEditor;


public class FloatField implements KeyListener, ActionListener
	 {
	  float value ;
	  
	  float upperLimit ;
	  float lowerLimit ;
	  
	  int 	state ;
	  
	  static final int ERROR 	= 0	;
	  static final int CORRECT 	= 1 ;
	  
	  JTextField valueTextField ;
	  
	  Document 	 floatDocument ;
	  
	  JPanel panel ;
	  
	  
	  
		 private Color updatedColor;

		 private Color correctColor;

		 private Color errorColor;
	  
	  String formerText ;
	  
	  List actionListenerSet ;
	  
	  public FloatField( float lowerLimit, float upperLimit )
	  	{
	  	 panel = new JPanel() ;
	  	 valueTextField = new JTextField( 7 ) ;
	  	 
	  	 valueTextField.setDocument( new FloatDocument( this )  ) ;
	  	 
	  	 valueTextField.addKeyListener( this ) ;
	  	 
	  	 valueTextField.addActionListener( this ) ;
	  	 
	  	 panel.add( valueTextField ) ;
 	  	 
	  	 updatedColor = new Color( .9f, .9f, 1.0f  ) ;
		   
		 correctColor = new Color( .9f, 1f, .9f  ) ;

		 errorColor = new Color( 1.0f, .9f, .9f ) ;
		 
		 this.upperLimit = upperLimit ;
		 this.lowerLimit = lowerLimit ;
		 
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
	 
	  class FloatDocument extends PlainDocument 
	  	{
	  	 String  previousAllowedString 			;
	  	 int	 previousAllowedCaretPosition	;

	  	 
	  	 
	  	 FloatField owner ;
	  	 
	  	 FloatDocument( FloatField owner ) { super() ;  this.owner = owner ; saveState() ; } 
	  	 
         public void insertString( int offs, String str, AttributeSet a)	throws BadLocationException 
         	{

         	 if( str == null ) 
         		{
         	 	 return ;
         	 	}
         	 
         	 int oldDot = -1 ;
         	 if( str.contains( "." ) )
         		 try{
            	 
            	 String text = getString() ;   

            	  for( int i = text.length() - 1 ; i >= 0 ; i-- )
            		   {
            		    if( text.charAt( i ) == '.' ) 
            		    	 {
            		    	  oldDot = i ;
            		    	  super.remove( i, 1 ) ;
            		    	 }
            		   
            		   }
            	 }
            	  catch( Exception e ) {}
         	
             if( oldDot != -1 && oldDot < offs ) offs-- ;
             
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
        	 	value = Float.parseFloat( text ) ; 
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
         void acceptState() { saveState() ; owner.valueTextField.setBackground( correctColor ) ;  state = CORRECT ; }
         
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
	  
	 
	  public Float peekValue()
		   {
		    if( state == CORRECT )
		    	 {
				  return new Float( value ) ;
		    	 }
		    else return null ;
		   }
	  
	  public Float acceptValue()
		   {
		    if( state == CORRECT )
		    	 {
				  valueTextField.setBackground( updatedColor ) ;		    	 
				  return new Float( value ) ;
		    	 }
		    else return null ;
		   }

	 public void setValue( float value )
		  {
		   valueTextField.setText( ( new Float( value ) ).toString() ) ;
		  }


	 }
