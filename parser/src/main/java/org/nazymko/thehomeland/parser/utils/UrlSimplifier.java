package org.nazymko.thehomeland.parser.utils;


import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class UrlSimplifier {
    /**
     * Long url to short
     */
    public String simplify(String url) {
        URL uri = null;
        try {
            uri = new URL(url);

            return uri.getProtocol() + "://" + uri.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String authority(String url) {
        try {
            return new URL(url).getAuthority();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
