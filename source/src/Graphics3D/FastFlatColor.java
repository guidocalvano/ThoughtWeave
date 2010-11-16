package Graphics3D;

import javax.media.j3d.* ;
import javax.vecmath.Color3f;

public class FastFlatColor implements MaterialInterface
	 {
	  Shape3DViewInterface shape ;

	  ColoringAttributes colorAttributes ;
	  
	  TransparencyAttributes transparencyAttributes ;

	  Appearance appearance;
	  

	 public FastFlatColor()
		  {
		   appearance = new Appearance() ;
		   
		   colorAttributes = new ColoringAttributes() ;
		   
		   colorAttributes.setCapability( ColoringAttributes.ALLOW_COLOR_READ ) ;
		   colorAttributes.setCapability( ColoringAttributes.ALLOW_COLOR_WRITE ) ;
		   
		   
		   appearance.setColoringAttributes( colorAttributes ) ;
		    transparencyAttributes = new TransparencyAttributes() ;
		    
		    transparencyAttributes.setCapability( TransparencyAttributes.ALLOW_VALUE_READ ) ;
		    transparencyAttributes.setCapability( TransparencyAttributes.ALLOW_VALUE_WRITE ) ;
		    
		    transparencyAttributes.setTransparencyMode( TransparencyAttributes.BLENDED );
		    
	  		   RenderingAttributes renderingAttributes = new RenderingAttributes() ;
			     
			     renderingAttributes.setDepthBufferEnable( true ) ;
			     renderingAttributes.setDepthBufferWriteEnable( true ) ;

			    appearance.setRenderingAttributes( renderingAttributes ) ;
			    
			    
			appearance.setTransparencyAttributes( transparencyAttributes ) ;

		  }

	 public void init( Shape3DViewInterface owner)
		  {
		   shape = owner ;
		/*   
		   colorAttributes = owner.getAppearance().getColoringAttributes() ;
		   
		    transparencyAttributes = owner.getAppearance().getTransparencyAttributes() ;
		    appearance = owner.getAppearance() ;
		    */
		   
		   shape.setAppearance( appearance ) ;
		  }
	 
	 public void setRed( double red )
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   
		   colorAttributes.getColor( color ) ;// getColor( color ) ;
		   
		   color.x = (float) red ;
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   
		   colorAttributes.setColor( color ) ;

		  }
	 
	 
	 public double getRed()
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   
		   colorAttributes.getColor( color ) ;// getColor( color ) ;
		   
		   return color.x  ;		   
		  }

	 
	 public void setGreen( double green )
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   colorAttributes.getColor( color ) ;// getColor( color ) ;
  
		   color.y = (float) green ;
		   
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   colorAttributes.setColor( color ) ;

		  }
	 
	 
	 
	 public double getGreen()
		  {
		   Color3f color = new Color3f() ;
		  
		 //  getAppearance().getMaterial().getDiffuseColor( color ) ;
		   colorAttributes.getColor( color ) ;// getColor( color ) ;
   
		   return color.y  ;		   
		  }

	 
	 
	 public void setBlue( double blue )
		  {
		   Color3f color = new Color3f() ;
		  
		   colorAttributes.getColor( color ) ;// getColor( color ) ;
		   
		   color.z = (float) blue ;
		   
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   colorAttributes.setColor( color ) ;
		  }
	 
	 
	 public double getBlue()
		  {
		   Color3f color = new Color3f() ;
		  
		  // getAppearance().getMaterial().getDiffuseColor( color ) ;
		   colorAttributes.getColor( color ) ;// getColor( color ) ;

		   return color.z  ;		   
		  }

	 
	 
	 
	 public void setTransparency( double transparency )
		  {
		  
		   
		   transparencyAttributes.setTransparency( 1f - (float) transparency ) ;
		   
		  }
	 
	 
	 public double getTransparency()
		  {
		  		   
		   return 1.0 - transparencyAttributes.getTransparency() ;
		   
		  }

	 }
