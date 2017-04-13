/**
 * Custom Mandatory fields exception
 * @author arp226
 */

package exception;

public class MandatoryFieldMissingException extends Exception {

	public MandatoryFieldMissingException(String string) {
		super(string);
	}

}
