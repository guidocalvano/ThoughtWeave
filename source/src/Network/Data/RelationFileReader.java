package Network.Data ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


public class RelationFileReader implements RelationFileInterface
	 {
	  double[][] relationMatrix ;
	  
	  
	  Double[] relationArray ;
	  
	  int nodeCount ;
	  
	  RelationFileReader( File file )
	  	{
	  
	  	 Vector relationVector =  new Vector() ; ;
		  try
			   {
			    FileInputStream fileStream = new FileInputStream( file ) ;
	

				 Scanner scanner = new Scanner( fileStream ) ; //.useLocale( Locale.GERMAN ) ;
			  	 
			  	 // scanner.useDelimiter( "(\\s+(?=[\\d-]))" ) ;
			
			  	 scanner.useDelimiter( "(\\s+|(\\s*?=\\z))" ) ;

				 scanner.useLocale( Locale.US ) ;

			  	 int valCounter = 0 ;
				
			  	 while( scanner.hasNextDouble() )
			  		 {
			  		  valCounter++ ;
			  		  
			  		  Double val = new Double( scanner.nextDouble() ) ;
			  		  
			  		  relationVector.add( val  ) ;			  		  
			  		 }
				
			  	 System.out.println( "values read: " + valCounter ) ;
			  	
			  	relationArray = new Double[ relationVector.size() ] ;
			  	
			  	
			  	Iterator relationIterator = relationVector.iterator() ;
			  	
			  	for( int i = 0 ; i < relationVector.size() ; i++ )
			  		 relationArray[ i ] = (Double) relationIterator.next() ;
			  		 
			    
			   } catch( FileNotFoundException e )
			   {
			    e.printStackTrace();
			   }
	  
	  
	  	}

	 public double getRelation( int i, int j)
		  {
		   return relationArray[ i * nodeCount + j ].doubleValue() ;
		  }

	 public int nodeCount()
		  {
		   
		  
		   System.out.println( " relation file node count " +  Math.sqrt( relationArray.length ) + " array length " + relationArray.length ) ;
		  
		  return (int) Math.sqrt( relationArray.length ) ;
		  }

	 }
