package co.gov.mineducacion.exceptions.business;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Doesn't exist record in %s with ID: %s";
    private static final String ERROR_MESSAGE_VALUE = "Doesn't exist record in %s with VALUE: %s";

    public ResourceNotFoundException(String message, Long id) {
        super(String.format(ERROR_MESSAGE, message, id));
    }

    public ResourceNotFoundException(String message, String value) {
        super(String.format(ERROR_MESSAGE_VALUE, message, value));
    }

    @Serial
    private static final long serialVersionUID = -930011029218447781L;
}
