package com.gamecloud.http.property;

import org.apache.http.entity.mime.content.InputStreamBody;

import java.io.InputStream;

/**
 * Stream property
 *
 * @author mateusz
 */
public class StreamProperty extends InputStreamBody {

    /**
     * Constructor
     *
     * @param in          - input stream
     * @param contentType
     * @param filename
     */
    public StreamProperty(InputStream in, String contentType, String filename) {
        super(in, contentType, filename);
    }
}
