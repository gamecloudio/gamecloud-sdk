package com.gamecloud.http.property;

import org.apache.http.entity.mime.content.FileBody;

import java.io.File;

/**
 * File property
 *
 * @author mateusz
 */
public class FileProperty extends FileBody {

    /**
     * Constructor
     *
     * @param path        to file
     * @param contentType
     */
    public FileProperty(String path, String contentType) {
        super(new File(path), contentType);
    }
}
