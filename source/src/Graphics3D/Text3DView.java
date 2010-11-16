package Graphics3D ;


import java.awt.Font;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.vecmath.Color3f;


public class Text3DView extends Shape3DView implements Text3DViewInterface
	 {
//	  String text ;
	  
	  Text3D text3D ;
	  
	  public Text3DView( String text )
	  	{
        Font font = new Font(
                  "Arial",     // font name
                  Font.PLAIN,  // font style
                  1 );         // font size
              FontExtrusion extrude = new FontExtrusion( );
              Font3D font3D = new Font3D( font, extrude );

        // Then build 3D text geometry using the font

              text3D = new Text3D( );
              text3D.setFont3D( font3D );
              text3D.setString( text );
              
              text3D.setCapability( Text3D.ALLOW_STRING_WRITE ) ;

              text3D.setCapability( Text3D.ALLOW_INTERSECT) ;

              
              Appearance appearance = new Appearance();
         
              appearance.setColoringAttributes (new ColoringAttributes (new Color3f (1.0f, 1.0f, 1.0f),1));

              
              Shape3D shape = new Shape3D( text3D, appearance );
              
         init( shape, true ) ;

	  	}
	  public String text()
		  {
		  return text3D.getString() ;
		  }

	  public void text( String text)
		  {
		   text3D.setString( text ) ;		  
		  }
	 
	  public Text3D getText3D() { return text3D ; } 
	

	 }
