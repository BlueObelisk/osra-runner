package uk.ac.cam.ch.wwmm.osra;

import net.sf.jnati.ArtefactDescriptor;
import net.sf.jnati.proc.ProcessMonitor;
import net.sf.jnati.proc.ProcessOutput;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sea36
 */
public class OsraRunner {

    private static final String OSRA_BAT = "osra.bat";

    private final File path;

    public OsraRunner(ArtefactDescriptor location) {
        this.path = location.getPath();
    }

    public List<String> run(File input, String... args) throws OsraException {
        String osra = new File(path, OSRA_BAT).getPath();
        List<String> cmds = new ArrayList<String>();
        cmds.add(osra);
        cmds.addAll(Arrays.asList(args));
        cmds.add(input.getPath());
        ProcessMonitor proc = new ProcessMonitor(cmds);
        try {
            ProcessOutput out = proc.runProcess();

            List<String> lines = new ArrayList<String>();
            BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.getStdOutBytes())));
            for (String l = in.readLine(); l != null; l = in.readLine()) {
                lines.add(l);
            }

            return lines;
        } catch (Exception e) {
            throw new OsraException("Osra failed", e);
        }
    }

}
