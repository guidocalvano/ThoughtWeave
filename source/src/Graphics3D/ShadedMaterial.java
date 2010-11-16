package Graphics3D;

import javax.media.j3d.*;
import javax.vecmath.Color3f;

public class ShadedMaterial implements MaterialInterface
	 {
	  Shape3DViewInterface shape ;

	  Material material ;
	  
	  TransparencyAttributes transparencyAttributes ;

	  Appearance appearance;
	  
	  public ShadedMaterial()
		   {		  
		   material = new Material() ;
		   material.setCapability( Appearance.ALLOW_MATERIAL_READ ) ;
		   material.setCapability( Appearance.ALLOW_MATERIAL_WRITE ) ;
		   
		   
		   
		   material.setEmissiveColor( 1f, 1f, 1f ) ;
		   material.setDiffuseColor( 1f, 1f, 1f ) ;
		   material.setAmbientColor( 1f, 1f, 1f ) ;
		   material.setSpecularColor( 1f, 1f, 1f ) ;
		   
		   
		    appearance = new Appearance() ;

		   appearance.setMaterial( material ) ;
		   
		    appearance.setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ ) ;
		    appearance.setCapability( Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE ) ;

		    
		   
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
		   material = new Material() ;
		   material.setCapability( Appearance.ALLOW_MATERIAL_READ ) ;
		   material.setCapability( Appearance.ALLOW_MATERIAL_WRITE ) ;
		   
		   
		   
		   material.setEmissiveColor( 1f, 1f, 1f ) ;
		   
		   owner.getAppearance().setMaterial( material ) ;
		   
		    transparencyAttributes = owner.getAppearance().getTransparencyAttributes() ;
		    appearance = owner.getAppearance() ;
		    
	  		   RenderingAttributes renderingAttributes = new RenderingAttributes() ;
			     
			     renderingAttributes.setDepthBufferEnable( true ) ;
			     renderingAttributes.setDepthBufferWriteEnable( true ) ;

			    appearance.setRenderingAttributes( renderingAttributes ) ;
			  
			    */
		   
		   shape.setAppearance( appearance ) ;
		   appearance.setTransparencyAttributes( transparencyAttributes ) ;
		  }
	 
	 public void setRed( double red )
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   
		   if( material == null ) System.out.println( "material == null " ) ;
		   material.getDiffuseColor( color ) ;// getColor( color ) ;
		   
		   color.x = (float) red ;
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   
		   material.setDiffuseColor( color ) ;// getColor( color ) ;

		  }
	 
	 
	 public double getRed()
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   
		   material.getDiffuseColor( color ) ;// getColor( color ) ;
		   
		   return color.x  ;		   
		  }

	 
	 public void setGreen( double green )
		  {
		   Color3f color = new Color3f() ;
		  
		//   getAppearance().getMaterial().getDiffuseColor( color ) ;
		   material.getDiffuseColor( color ) ;// getColor( color ) ;
  
		   color.y = (float) green ;
		   
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   material.setDiffuseColor( color ) ;// getColor( color ) ;

		  }
	 
	 
	 
	 public double getGreen()
		  {
		   Color3f color = new Color3f() ;
		  
		 //  getAppearance().getMaterial().getDiffuseColor( color ) ;
		   material.getDiffuseColor( color ) ;// getColor( color ) ;
   
		   return color.y  ;		   
		  }

	 
	 
	 public void setBlue( double blue )
		  {
		   Color3f color = new Color3f() ;
		  
		   material.getDiffuseColor( color ) ;// getColor( color ) ;
		   
		   color.z = (float) blue ;
		   
		   
		//   getAppearance().getMaterial().setDiffuseColor( color ) ;
		   material.setDiffuseColor( color ) ;// getColor( color ) ;
		  }
	 
	 
	 public double getBlue()
		  {
		   Color3f color = new Color3f() ;
		  
		  // getAppearance().getMaterial().getDiffuseColor( color ) ;
		   material.getDiffuseColor( color ) ;// getColor( color ) ;

		   return color.z  ;		   
		  }

	 
	 
	 
	 public void setTransparency( double transparency )
		  {
		  
		   
		   transparencyAttributes.setTransparency( 1f - (float) transparency ) ;
		   
		//   appearance.setTransparencyAttributes( transparencyAttributes ) ;
		  }
	 
	 
	 public double getTransparency()
		  {
		  		   
		   return 1.0 - transparencyAttributes.getTransparency() ;
		   
		  }

	 }
