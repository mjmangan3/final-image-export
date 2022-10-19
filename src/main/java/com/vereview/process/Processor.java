package com.vereview.process;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by mjmangan on 9/27/17.
 */
public interface Processor {
    public void copy(Path image, Path exportFile) throws IOException;
}
