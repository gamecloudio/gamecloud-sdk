package com.gamecloud.http.request;

import com.gamecloud.http.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

/**
 * @author mateusz
 */
public class PostRequest {

    /**
     * Http post request
     *
     * @param url
     * @param params
     * @return {@link Response}
     * @throws {@link RequestException}
     * @throws {@link java.io.IOException}
     */
    public Response request(URL url, List<? extends NameValuePair> params)
            throws RequestException, IOException {
        return request(new DefaultHttpClient(), url, params);
    }

    /**
     * Http post request
     *
     * @param client
     * @param url
     * @param params
     * @return {@link Response}
     * @throws {@link RequestException}
     * @throws {@link java.io.IOException}
     */
    public Response request(HttpClient client, URL url, List<? extends NameValuePair> params)
            throws RequestException, IOException {
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            HttpPost httpPost = new HttpPost(url.toString());
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            return new Response(response);
        } catch (UnsupportedEncodingException e) {
            throw new RequestException("Failed to encode property: " + e.getMessage());
        } catch (ClientProtocolException e) {
            throw new RequestException("Http connection problem: " + e.getMessage());
        }
    }
}