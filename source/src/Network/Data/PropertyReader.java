package Network.Data ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


public class PropertyReader implements PropertyReaderInterface
	 {
	  String propertyId ;
	 
	  int	 cursor			;
	  
	  
	  double meanValue ;
	  
	  Vector channelAt ; 
	  
	  Iterator it ;
	  
	  PropertyReader( Scanner scanner  )
	  	{
	  	 channelAt = new Vector() ;
	  
		 
	  	 load( scanner ) ;
		
	  	 
	  
	  	}
	  
	  public double getMeanValue() { return meanValue ; } 
	  
	  PropertyReader( String propertyString  )
	  	{
	  	 channelAt = new Vector() ;
	  
	  	 Scanner splitPropertyStringAndDoubleContent = new Scanner( propertyString ) ;
	  	 
	  	 splitPropertyStringAndDoubleContent.useDelimiter( "(?<=[^\\s\\d])\\s+(?=[\\d-])" ) ;
	  	 
	  	 propertyId = splitPropertyStringAndDoubleContent.next() ;
	  	 
	  	 System.out.println( "property Id " + propertyId ) ;
	  	 
	  	 Scanner doubleScanner = new Scanner( splitPropertyStringAndDoubleContent.next() ) ;
	  	 doubleScanner.useDelimiter( "\\s+" ) ;
	  	 
	  	 doubleScanner.useLocale( Locale.GERMAN ) ;
	  	 
	  	 meanValue = doubleScanner.nextDouble() ;
	  	 
	  	 while( doubleScanner.hasNext() )
	  		  {
	  		  
		    	 channelAt.add( new Double ( doubleScanner.nextDouble() ) ) ;

	  		  }
		 
	  	 testPrint() ;
	  	 
	  	 
	  //	 load( scanner ) ;
		
	  	 
	  
	  	}
	  
	  
	  
	  
	  public String propertyId() { return propertyId ; }
	  
	  
	  void load( Scanner scanner )
		   {
		    System.out.println( "enter property line" ) ;

		    
		    
		    propertyId = scanner.next(  ) ; // \\s+\\d-
		    
		    System.out.println( "propertyId " + propertyId ) ;
		   
		    

		    meanValue = scanner.nextDouble() ;

		    System.out.println( "meanValue " + meanValue ) ;

		    
		    loadChannelData(  scanner ) ;
		   
		   }
	  
	  
	  void loadChannelData( Scanner scanner )
		   {
		    double nextDouble ;
		   
		    while( scanner.hasNextDouble()  )
		    	 {
		    	  nextDouble = scanner.nextDouble() ;
		    	 channelAt.add( new Double ( nextDouble ) ) ;
		    	 
		    	 System.out.println( "nextDouble " + nextDouble ) ;
		    	 }
		    
		   
		   }
	  
	  void testPrint()
		   {
		    System.out.print( "property " + propertyId + "channel count " + channelAt.size() ) ;
		    System.out.println( " mean " + meanValue ) ;
		    
		    for( int i = 0 ; i < channelAt.size() ; i++ )
		    	 System.out.print( "  " + ( (Double) channelAt.get(  i  ) ).doubleValue() ) ;
		    
		    System.out.println() ;
		   }
	  
	  
	  String nextToken( InputStream input )
		   {
		    String token = "" ;
		   
		    
		    
		    
		    return token ;
		   }
	  

	 public Iterator getIterator()
		  {
		  return it ;
		  }

	 public boolean isGlobalProperty()
		  {
		  return channelAt.size() == 0 ;
		  }

	 public void initIterator()
		  {
		   it = channelAt.iterator() ;
		  }

	 public int channelCount()
		  {
		  // TODO Auto-generated method stub
		  return channelAt.size() ;
		  }
	 }
