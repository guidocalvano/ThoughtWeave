package Network ;
public class Constant implements ValueSourceInterface
	 {

	  float constant ;
	  
	  public Constant( float constant ) { this.constant = constant ; } 
	 
	  public float getValue()
		  {
		   return constant ;
		  }

	 }
