package com.gamecloud.http.utils;

import android.net.Uri;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.Collection;

/**
 * @author mateusz
 */
public class URIUtils {

    public static String build(String url, Collection<? extends BasicNameValuePair> params) throws URISyntaxException {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        for (BasicNameValuePair nv : params) {
            builder.appendQueryParameter(nv.getName(), nv.getValue());
        }
        return builder.build().toString();
    }
}
