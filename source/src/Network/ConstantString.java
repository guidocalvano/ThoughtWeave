package Network ;
public class ConstantString implements StringSourceInterface
	 {
	  String string ;
	 
	  ConstantString( String string )
	  {
	   this.string = string ;
	  }
	  
	  public String getString()
		  {
		  return string;
		  }

	 }
