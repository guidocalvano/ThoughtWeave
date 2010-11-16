package Network;


public interface LinkControlInterface
	 {

	 LinkViewInterface getView();

	 ModelInterface getModel() ;

	 void releaseEventListeners();

//	 void init( ChannelRelationLogicInterface source );
	 
	 }
