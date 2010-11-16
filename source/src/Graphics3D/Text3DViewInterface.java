package Graphics3D ;

import javax.media.j3d.Text3D;


public interface Text3DViewInterface extends Shape3DViewInterface
	 {
	  String text() ;
	  
	  void text( String text ) ;
	 
	  public Text3D getText3D() ;
	 }
