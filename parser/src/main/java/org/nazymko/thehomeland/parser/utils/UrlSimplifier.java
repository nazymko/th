package org.nazymko.thehomeland.parser.utils;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class UrlSimplifier {
    public String getDomainName(String url) {
        url = addProtocol(url);
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    /**
     * Long url to short
     */
    public String simplify(String url) {
        URL uri = null;
        url = addProtocol(url);
        try {
            uri = new URL(url);

            return uri.getProtocol() + "://" + uri.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addProtocol(String path) {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return path;
        } else {
            return "http://" + path;
        }
    }

    public String authority(String url) {
        url = addProtocol(url);
        try {
            return new URL(url).getAuthority();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
