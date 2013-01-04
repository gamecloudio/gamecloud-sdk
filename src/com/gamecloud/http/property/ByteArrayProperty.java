package com.gamecloud.http.property;

import org.apache.commons.io.IOUtils;
import org.apache.http.entity.mime.content.ByteArrayBody;

import java.io.IOException;
import java.io.InputStream;

/**
 * Byte array property
 *
 * @author mateusz
 */
public class ByteArrayProperty extends ByteArrayBody {

    /**
     * Constructor
     *
     * @param in          - input stream
     * @param contentType
     * @param filename
     * @throws java.io.IOException
     */
    public ByteArrayProperty(InputStream in, String contentType, String filename) throws IOException {
        this(IOUtils.toByteArray(in), contentType, filename);
    }

    /**
     * Constructor
     *
     * @param in          - byte array
     * @param contentType
     * @param filename
     */
    public ByteArrayProperty(byte[] in, String contentType, String filename) {
        super(in, contentType, filename);
    }
}