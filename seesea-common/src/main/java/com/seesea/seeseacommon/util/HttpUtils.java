package com.seesea.seeseacommon.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Since JDK1.8
 * @Createtime 2018/10/25 下午 11:54
 * @Author xiechongyang
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);


    RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(20).build();


    /**
     * @author http://blog.csdn.net/thewaiting
     * @create 2018 - 07 -01 -下午 5:19
     */
//    private static String proxyHost;
//
//    @Value("${proxyHost-address}")
//    public void setProxyHost(String proxyHost) {
//        this.proxyHost = proxyHost;
//    }
//
//    private static String proxyPort;
//
//    @Value("${proxyPort-port}")
//    public void setproxyPort(String proxyPort) {
//        this.proxyPort = proxyPort;
//    }
    public static byte[] doGet(String url) {
        return doGet(url, "https");
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static byte[] doGet(String url, String scheme) {

        HttpClient client = null;
        HttpGet request = null;
        byte[] bytes = null;
        HttpResponse response = null;
        HttpHost proxy = null;
        try {

//            if (!"".equals(proxyHost)) {
//                proxy = new HttpHost(proxyHost, Integer.valueOf(proxyPort), scheme);
//            }
//            Header header = new BasicHeader("Content-Type","application/xml; charset=utf-8");
            client = HttpClientBuilder.create().setProxy(proxy).build();

            //发送get请求
            request = new HttpGet(url);
//            request.setHeader(header);
            response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info("请求成功" + response.toString());
                bytes = EntityUtils.toByteArray(response.getEntity());
                return bytes;
            } else {
                logger.info("响应信息 response header: " + response.getAllHeaders());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
        }
        return bytes;
    }

    public static byte[] doPost(String url, Map params, String scheme) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        headers.add(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        return doPost(url, params, scheme, headers);
    }


    /**
     * post请求
     */
    public static byte[] doPost(String url, Map params, String scheme, List<Header> headers) {
        BufferedReader in = null;
        HttpClient client = null;
        HttpPost request = null;
        HttpHost proxy = null;

        try {
//            if (!"".equals(proxyHost)) {
//                proxy = new HttpHost(proxyHost, Integer.valueOf(proxyPort), scheme);
//            }
            client = HttpClientBuilder.create().setProxy(proxy).build();
            request = new HttpPost(url);

            //设置参数
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (Iterator iterator = params.keySet().iterator(); iterator.hasNext(); ) {
                String name = (String) iterator.next();
                String value = String.valueOf(params.get(name));
                nameValuePairs.add(new BasicNameValuePair(name, value));
            }

            for (Header header : headers) {
                request.setHeader(header);
            }
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info("请求成功" + response.toString());
//                in = new BufferedReader(new InputStreamReader(request.getEntity().getContent(), "utf-8"));
//                StringBuffer stringBuffer = new StringBuffer();
//                String line = "";
//                //换行符
//                String NL = System.getProperty("line.separator");
//                while ((line = in.readLine()) != null) {
//                    stringBuffer.append(line + NL);
//                }
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                return bytes;
            } else {
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                System.out.println(new String(bytes));
                logger.info("响应信息 response header: " + response.getAllHeaders());
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (request != null) {
                    request.releaseConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * post json数据
     */
    public static String doPost(String url, String params) {
        HttpClient client = null;
        HttpPost request = null;
        HttpHost proxy = null;
        String rspStr = "";
        try {
//            if (!"".equals(proxyHost)) {
//                proxy = new HttpHost(proxyHost, Integer.valueOf(proxyPort), scheme);
//            }

            client = HttpClientBuilder.create().setProxy(proxy).build();

            request = new HttpPost(url);
            //设置请求的报文头部的编码
            request.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            //设置期望服务端返回的编码
            request.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));
            StringEntity stringEntity = new StringEntity(params, "utf-8");
            request.setEntity(stringEntity);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info("请求成功" + response.toString());
                rspStr = EntityUtils.toString(response.getEntity());
            } else {
                logger.info("响应信息 response header: " + response.getAllHeaders());
                rspStr = EntityUtils.toString(response.getEntity());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
        }
        return rspStr;
    }


}
