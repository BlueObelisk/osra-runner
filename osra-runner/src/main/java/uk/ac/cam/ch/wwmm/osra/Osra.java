package uk.ac.cam.ch.wwmm.osra;

import net.sf.jnati.ArtefactDescriptor;
import net.sf.jnati.NativeCodeException;
import net.sf.jnati.deploy.NativeArtefactLocator;

/**
 * @author sea36
 */
public class Osra {

    private static final String ID = "osra";
	private static final String VERSION = "1-3-6";

	private static ArtefactDescriptor location;

	public static OsraRunner getOsraRunner() throws OsraException {
		if (location == null) {
			try {
				getLocation();
			} catch (NativeCodeException ex) {
				throw new OsraException("Failed to load OSRA", ex);
			}
		}
		return new OsraRunner(location);
	}

	private static synchronized void getLocation() throws NativeCodeException {
		if (location == null) {
			location = NativeArtefactLocator.findArtefact(ID, VERSION);
		}
	}

}
