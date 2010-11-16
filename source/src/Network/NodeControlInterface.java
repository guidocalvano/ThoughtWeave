package Network ;


public interface NodeControlInterface
	 {
	  int id() ;
	 
	  NodeViewInterface getView();
	  ModelInterface getModel();


	 public void init();

	 void releaseEventListeners();


	 void updatePropertySetEditor();



	 
	 }
