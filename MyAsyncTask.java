package com.example.administrator.myfirstapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 异步任务处理类
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {


    @Override
    protected String doInBackground(String... params) {
        return downloadUrl(params[0]);
    }


    public String downloadUrl(String myurl) {
        InputStream is = null;
        int len = 500;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            int responseCode = conn.getResponseCode();
            Log.d("Debug", "responseCode:" + responseCode);
            is = conn.getInputStream();

            String contentAsString = readIt(is, len);
            return contentAsString;

        } catch (MalformedURLException m) {
            m.printStackTrace();//URL
        } catch (IOException i) {
            i.printStackTrace();//openConnection
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }

            }
        }
        return null;
    }

    public String readIt(InputStream stream, int len) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        } catch (UnsupportedEncodingException u) {
            u.printStackTrace();//InputStreamReader
        } catch (IOException i) {
            i.printStackTrace();//read
        }
        return null;
    }


}
