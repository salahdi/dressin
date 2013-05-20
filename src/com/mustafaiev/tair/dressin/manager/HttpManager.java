package com.mustafaiev.tair.dressin.manager;

import android.util.Log;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.List;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 6:19 PM
 */
public class HttpManager {

    private static final int CONNECTION_TIMEOUT = 3000;
    private static final int SOCKET_TIMEOUT = 8000;
    private static final String TAG = HttpManager.class.getName();

    /**
     * Executes GET request and returns {@link org.apache.http.HttpResponse}
     *
     * @param url - {@link String} url for GET
     * @param headers - {@link org.apache.http.Header} GET request headers
     * @return {@link org.apache.http.HttpResponse} response object
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public HttpResponse doGet(final String url, final Header... headers)
            throws ClientProtocolException, IOException {
        final HttpParams params = this.getHttpParams();
        final HttpClient httpclient = new DefaultHttpClient(params);
        if (UrlValidator.getInstance().isValid(url)) {
            final HttpGet httpGet = new HttpGet(url);
            this.addHeaders(httpGet, headers);
            Log.i(HttpManager.TAG, "HttpGET going call URL: " + url);
            // Execute HTTP Get Request
            return httpclient.execute(httpGet);
        }
        return null;
    }

    public HttpResponse doRepeatableGet(final String url, final int retryCount,
                                        final Header... headers) {
        HttpResponse response = null;
        for (int i = 0; i < retryCount; i++) {
            Log.i(HttpManager.TAG, (i + 1) + " attempt to execute GET request...");
            try {
                final HttpParams params = this.getHttpParams();
                final HttpClient httpclient = new DefaultHttpClient(params);
                if (UrlValidator.getInstance().isValid(url)) {
                    final HttpGet httpGet = new HttpGet(url);
                    this.addHeaders(httpGet, headers);
                    Log.i(HttpManager.TAG, "HttpGET going call URL: " + url);
                    // Execute HTTP Get Request
                    response = httpclient.execute(httpGet);
                    break;
                }
            } catch (final IOException e) {
                Log.e(HttpManager.TAG, "An Exception thrown when HttpGet executing", e);
                continue;
            }
        }
        return response;
    }

    /**
     * Executes POST request and returns {@link HttpResponse}
     *
     * @param postURL - {@link String} url for POST
     * @param params - POST entity to be sent
     * @param headers - {@link Header} request headers
     * @return {@link HttpResponse} response object
     */
    public HttpResponse doPost(final String postURL, final List<NameValuePair> params,
                               final Header... headers) {
        try {
            final HttpParams httpParams = this.getHttpParams();
            final HttpClient client = new DefaultHttpClient(httpParams);
            if (UrlValidator.getInstance().isValid(postURL)) {
                final HttpPost httpPost = new HttpPost(postURL);
                this.addHeaders(httpPost, headers);
                final UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                httpPost.setEntity(entity);
                Log.i(HttpManager.TAG, "HttpPOST going call URL: " + postURL);
                return client.execute(httpPost);
            }
        } catch (final IOException e) {
            Log.e(HttpManager.TAG, "An Exception thrown when HttpPost executing", e);
        }
        return null;
    }

    /**
     * Adds headers to request
     *
     * @param requestBase {@link org.apache.http.client.methods.HttpRequestBase}
     * @param headers - array of {@link Header}
     */
    private void addHeaders(final HttpRequestBase requestBase, final Header... headers) {
        if ((headers != null) && (headers.length > 0)) {
            for (final Header header : headers) {
                requestBase.addHeader(header);
            }
        }
    }

    /**
     * @return {@link HttpParams} for requests
     */
    private HttpParams getHttpParams() {
        final HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, HttpManager.CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, HttpManager.SOCKET_TIMEOUT);
        return params;
    }


}
