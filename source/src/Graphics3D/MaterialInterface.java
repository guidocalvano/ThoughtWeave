package Graphics3D ;

public interface MaterialInterface
	 {

	  void		init( Shape3DViewInterface owner ) ;
	 
	  void 		setRed( double value ) ;	  
	  double 	getRed() ;
	  
	  void 		setGreen( double value ) ;	  
	  double 	getGreen() ;
	  
	  void 		setBlue( double value ) ;	  
	  double 	getBlue() ;
	  
	  void 		setTransparency( double value ) ;	  
	  double 	getTransparency() ;
	 }
