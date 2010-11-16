package Network.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class PropertyFileReader implements PropertyFileInterface
	 {
	  Map propertyReaderAt ;
	 
	  int channelCount ;
	  
	  public int channelCount() { return channelCount ; } 
	 
	  PropertyFileReader( File file )
	  	{
	  	 propertyReaderAt = new HashMap() ;
	 
	  	 load( file ) ;
	  	}
	  
	  
	  void load( File file ) 
		 {
		   PropertyReader prop;
			  try
				   {
				   FileInputStream fileStream = new FileInputStream( file ) ;
				   
				   
//				   Scanner scanner = new Scanner( fileStream ).useLocale( Locale.GERMAN ) ;

		//		   	scanner.useDelimiter( "\\n" ) ;
				   	
			//	   	scanner.next(); 			   	scanner.next();
				//   	scanner.next(); 			   	scanner.next();
/*
					 Scanner scanner = new Scanner( fileStream ).useLocale( Locale.GERMAN ) ;
				  	 
					 scanner.nextLine();
				  	 scanner.useDelimiter( "(\\s+(?=[\\d-]))|\\s*\\n|\\s*\\z" ) ;
*/
				  	 
				  	 Scanner propertyLineScanner = new Scanner( fileStream ).useLocale( Locale.GERMAN ) ;
				  	 propertyLineScanner.nextLine();

					 propertyLineScanner.useDelimiter( "(?<=\\d)\\s*(?=[a-zA-Z])" ) ;
					 
					 
					 Scanner propertyContentScanner ;
					 
				//   while( scanner.hasNext() )
					 
					 while( propertyLineScanner.hasNext() )
						{
						 String propertyLine = propertyLineScanner.next() ;
						 
						 System.out.println( "propertyLine = ") ;
						 System.out.println( propertyLine ) ;
						 System.out.println( "*end* ") ;

						
						 prop = new PropertyReader(  propertyLine  ) ;
						 
						 propertyReaderAt.put( prop.propertyId(), prop ) ;
						 
						 if( channelCount == 0 && prop.channelCount() > 0 ) channelCount = prop.channelCount() ;
						 
 						}
				   
				   } catch( FileNotFoundException e )
				   {
				    e.printStackTrace();
				   }

				   
			for( Iterator it = propertyReaderAt.values().iterator() ; it.hasNext();  )	   
				 {
				  PropertyReader p = (PropertyReader) it.next() ;
				  
				  p.testPrint() ;
				 
				 }
		   
		  }

	 public Iterator getPropertyIterator()
		  {
		   return propertyReaderAt.values().iterator() ;
		  }
	 
	 
	  
	 }
