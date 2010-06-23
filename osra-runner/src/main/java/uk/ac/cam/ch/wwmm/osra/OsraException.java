package uk.ac.cam.ch.wwmm.osra;

import net.sf.jnati.NativeCodeException;

/**
 * @author sea36
 */
public class OsraException extends Exception {

    public OsraException() {
    }

    public OsraException(String message) {
        super(message);
    }

    public OsraException(String message, Throwable cause) {
        super(message, cause);
    }

    public OsraException(Throwable cause) {
        super(cause);
    }
}
