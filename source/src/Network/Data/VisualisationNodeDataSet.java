package Network.Data;
import Network.* ;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

import Network.BasicPropertySet;
import Network.PropertySetInterface;

public class VisualisationNodeDataSet implements NodeDataSetInterface
	 {
	  List nodeDataSet ;
	  
	  PropertySetInterface propertySet ;
	  
	  PropertySetInterface textSet ;

	  public VisualisationNodeDataSet( File file )
	  	{
	  	 nodeDataSet = new Vector() ;
	  	 propertySet = new BasicPropertySet() ;
	  	 textSet	 = new BasicPropertySet() ;
	  	 
	  	 textSet.propertyObject(  file.getName(), ""  ) ;

	  	 
	  	 PropertySetInterface folder = propertySet.folder(  file.getName()  ) ;
	  	 
		 folder.property( "x",  .0 ) ;
		 folder.property( "y",  .0 ) ;
		 folder.property( "z", .0 )  ;

		 folder.property( "red",.0 ) ;
		 folder.property( "green", .0 ) ;
		 folder.property( "blue",.0 ) ;

		 folder.property( "size",  .0  ) ;
		 folder.property( "shape", 0.0  ) ;
		 folder.property( "transparency", .0  ) ;
		 

		 	  	 
	  	 load( file ) ;
	  	}
	

	 public void load( File file )
		  {
		  
			
		//	 File file = new File( filename ) ;
			
			 Scanner lineParser;
		 try
			  {
			  lineParser = new Scanner( new FileInputStream( file )  );
				 lineParser.useDelimiter( "\n" ) ;

				 lineParser.useLocale( Locale.US ) ;

			 Scanner nodeParser	;
			 
			 
//			  Iterator nodeControlIterator = nodeControlSet.iterator() ;

			 while( lineParser.hasNext() )
				  {
				  
					 String nodeLine = lineParser.next() ;
					 
					 nodeParser = new Scanner( nodeLine ) ;
					 
					 nodeParser.useDelimiter( "\t" ) ;	 
					 
					 nodeParser.useLocale( Locale.US ) ;

					 
					 String label = nodeParser.next();
					 
					 
					// NodeView node = (NodeView) nodeControlIterator.next() ;
					 
					 double x =  nodeParser.nextDouble() ;
					 double y =  nodeParser.nextDouble() ;
					 double z =   nodeParser.nextDouble() ;
					 
					 double r =   nodeParser.nextDouble() ;
					 double g =   nodeParser.nextDouble() ;
					 double b =  nodeParser.nextDouble() ;
					 
					 double size =  nodeParser.nextDouble() ;
					 double shape =   nodeParser.nextDouble() ;
					 double transparency =   nodeParser.nextDouble() ;

					 
					 NodeDataInterface node = new NodeData( nodeDataSet.size() ) ;
					 
					 nodeDataSet.add( node  ) ;
					 
					 node.textPropertySet().propertyObject( file.getName(), label ) ;

					 
					 PropertySetInterface root = node.propertySet() ;
					 
					 PropertySetInterface folder = root.folder( file.getName()  ) ;
					    
					 folder.property( "x",  30.0 * x -15 ) ;
					 folder.property( "y",  30.0 * y -15  ) ;
					 folder.property( "z", 30.0 * z -15 )  ;

					 folder.property( "red",r ) ;
					 folder.property( "green", g ) ;
					 folder.property( "blue",b ) ;

					 folder.property( "size",  size  ) ;
					 folder.property( "shape", shape  ) ;
					 folder.property( "transparency", transparency  ) ;
					 
					// folder.setDescriptionSource( new ConstantString( label )  ) ;
					 
		/*			 
					 model.setXSource( new Constant( x ) ) ;
					 model.setYSource( new Constant( y ) ) ;
					 model.setZSource( new Constant( z ) ) ;
					 
					 model.setRSource( new Constant( r ) ) ;
					 model.setGSource( new Constant( g ) ) ;
					 model.setBSource( new Constant( b ) ) ;
					 
					 model.setMorphSource( new Constant( morph ) ) ;
					 model.setRadiusSource( new Constant( radius ) ) ;
*/
					 
					 
					 
										 
				  }

			  } catch( FileNotFoundException e )
			  {
			  e.printStackTrace();
			  }
			
		  }


	 public PropertySetInterface availableProperties()
		  {
		  return propertySet;
		  }

	 public Iterator iterator()
		  {
		  return nodeDataSet.iterator() ;
		  }


	 public PropertySetInterface textPropertySet()
		  {
		  return textSet ;
		  }
	  public int nodeCount() { return nodeDataSet.size() ; }

	 }
