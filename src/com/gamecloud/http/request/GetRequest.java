package com.gamecloud.http.request;

import com.gamecloud.http.response.Response;
import com.gamecloud.http.utils.URIUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

/**
 * @author mateusz
 */
public class GetRequest {

    /**
     * Http get request
     *
     * @param url
     * @param params
     * @return {@link Response}
     * @throws {@link RequestException}
     * @throws {@link java.io.IOException}
     */
    public Response request(URL url, Collection<? extends BasicNameValuePair> params)
            throws RequestException, IOException, URISyntaxException {
        return request(new DefaultHttpClient(), url, params);
    }

    /**
     * Http get request
     *
     * @param client
     * @param url
     * @param params
     * @return {@link Response}
     * @throws {@link RequestException}
     * @throws {@link java.io.IOException}
     */
    public Response request(HttpClient client, URL url, Collection<? extends BasicNameValuePair> params)
            throws RequestException, IOException {
        try {
            HttpGet httpDelete = new HttpGet(URIUtils.build(url.toString(), params));
            HttpResponse response = client.execute(httpDelete);
            return new Response(response);
        } catch (UnsupportedEncodingException e) {
            throw new RequestException("Failed to encode property: " + e.getMessage());
        } catch (ClientProtocolException e) {
            throw new RequestException("Http connection problem: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RequestException("Incorrect uri: " + e.getMessage());
        }
    }
}