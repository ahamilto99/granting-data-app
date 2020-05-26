package ca.gc.tri_agency.granting_data.app.exception;

@SuppressWarnings("serial")
public class UniqueColumnException extends RuntimeException {

	public UniqueColumnException(String msg) {
		super(msg);
	}
}
