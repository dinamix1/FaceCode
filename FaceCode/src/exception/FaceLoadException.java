package exception;

public class FaceLoadException extends RuntimeException {
	private static final long serialVersionUID = -2511245304185005580L;
	
	public FaceLoadException(Exception e){
		super(e);
	}
}
