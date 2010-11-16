package Gui;

import java.util.List;

import Network.PropertyPathInterface;


public interface OptionTreeInterface
	 {
	  String name() ;
	 
	  List children() ;

	  PropertyPathInterface  path();	  
	 }
