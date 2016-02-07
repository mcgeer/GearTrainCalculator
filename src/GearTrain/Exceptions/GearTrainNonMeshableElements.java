package GearTrain.Exceptions;

public class GearTrainNonMeshableElements extends Exception{
	
	private static final long serialVersionUID = -5712759644856348212L;
	
	/**
	 * Exception thrown with null as its detailed message
	 */
	public GearTrainNonMeshableElements() {
        super();
    }
	
	/**
	 * @param message, custom message for exception
	 */
	public GearTrainNonMeshableElements(String message) {
        super(message);
    }
    
	/**
	 * @param throwable, constructs exception with cause throwable
	 */
    public GearTrainNonMeshableElements (Throwable throwable) {
        super(throwable);
    }
    
    /**
     * 
     * @param message, custom message for exception
     * @param throwable, custom exception with cause throwable
     */
    public GearTrainNonMeshableElements (String message, Throwable throwable) {
        super(message, throwable);
    }
    
    /**
     * 
     * @param message, custom message for exception
     * @param throwable, custom throwable cause for exception
     * @param enableSuppression, enable\disable suppression
     * @param writableStackTrace, enable\disable writeableStackTrace
     */
    public GearTrainNonMeshableElements (String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }    

}
