package Network.Mapping;
import java.util.Iterator;
import java.util.Vector;

import javax.vecmath.Color3f;


public class HyperCube
	 {
	  double[][] dimensionSetAt ;
	 
	  int hyperCubeEdgeDivisionCount ;
	  
	  int dimensionCount ;
	  
	  int symbolCount ;
	 
	  public double[] getVector( int symbolIndex)
		  {
		  // TODO Auto-generated method stub
		   return dimensionSetAt[ symbolIndex ] ;
		  }

	  public HyperCube( int symbolCount, int dimensionCount )
		  {
		   dimensionSetAt = new double[ symbolCount ][ dimensionCount ] ;
		  
		   this.dimensionCount = dimensionCount ;
		  
		   this.symbolCount = symbolCount ;
		   
		   double cubePointsDouble =  Math.pow(  (double) symbolCount, 1.0 / ( (double) dimensionCount ) ) ;
		   
		   System.out.println( "cubePointsDouble" + cubePointsDouble ) ;
		   
		   if( cubePointsDouble != Math.floor( cubePointsDouble ) ) 
				hyperCubeEdgeDivisionCount = 1 + ( (int) cubePointsDouble ) ;
		   else
				hyperCubeEdgeDivisionCount = (int) cubePointsDouble ;
		   
		   
		   mapToHyperCube( new double[ dimensionCount ], 0, 0 ) ;
		   
		   testPrint() ;
		   
		  // System.exit( 0 ) ;
		   /*
		   int i = 0 ;
		   for( int r = 0 ; r < hyperCubeEdgeDivisionCount ; r++ )
		   for( int g = 0 ; g < hyperCubeEdgeDivisionCount ; g++ )
 		   for( int b = 0 ; b < hyperCubeEdgeDivisionCount ; b++ )
 				{
 				 if( i < symbolCount )
 					  {
 					   dimensionSetAt[ i ][ 0 ] = ( (double) r ) / ( (double) hyperCubeEdgeDivisionCount ) ;
 								 
 					   dimensionSetAt[ i ][ 1 ] = ( (double) g ) / ( (double) hyperCubeEdgeDivisionCount ) ;

 					   dimensionSetAt[ i ][ 2 ] = ( (double) b ) / ( (double) hyperCubeEdgeDivisionCount ) ;

 					   
 					   i++ ;
 					  }
 				 
 				 else return ;
 				
 				}
 			*/
		  }
	  
	  private void testPrint()
		  {
		   System.out.println( "hyperCubeEdgeDivisionCount " + hyperCubeEdgeDivisionCount ) ;
		   System.out.println( "dimensionCount " + dimensionCount ) ;
		   System.out.println( "symbolCount " + symbolCount ) ;
		   
		   for( int i = 0 ; i < dimensionSetAt.length ; i++ )
				{
				 System.out.print( "dimensionSetAt[ " + i + " ] { " ) ;
				 for( int j = 0 ; j < dimensionSetAt[ i ].length ; j++ )
					  {
					   System.out.print( " " + dimensionSetAt[ i ][ j ]  ) ;

					  
					  }
				 System.out.println( " }" ) ;
				}
		  }

	 private int mapToHyperCube( double[] dimensionValueSet, int currentDimension, int currentSymbol )
		   {
		    if( currentSymbol >= symbolCount ) return currentSymbol ;
		   
		    if( currentDimension == dimensionCount )
		    	 {
		    	  for( int i = 0 ; i < dimensionCount ; i++ )
		    		   {
		    	        dimensionSetAt[ currentSymbol ][ i ] = dimensionValueSet[ i ] ;
		    	  
		    	        // System.out.println( "dimensionSetAt[ " + currentSymbol + " ][ " + i + " ] = " + dimensionValueSet[ i ] ) ;
		    		   }
		    	  
		    	  return ++currentSymbol ;
		    	 }
		    else
		    	 {
		    	  for( int dimensionValue = 0 ; dimensionValue < hyperCubeEdgeDivisionCount ; dimensionValue++ )
		    		   {
		    		    dimensionValueSet[ currentDimension ] = ( (double) dimensionValue ) / ( (double) hyperCubeEdgeDivisionCount ) ;
		    		    currentSymbol = mapToHyperCube( dimensionValueSet, currentDimension + 1, currentSymbol ) ;
		    		   }
		    	  return currentSymbol ;
		    	 }
		   
		   }

	 }
