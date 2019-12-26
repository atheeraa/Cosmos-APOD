package com.example.favor.cosmos;

import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class Query {

    private static final String LOG_TAG = Query.class.getSimpleName();

    public static List<list> fetchData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<list> cosmo = extractFeatureFromJson(jsonResponse);
        String cos = extractTitle(jsonResponse);
        return cosmo;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";


        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {


                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    public static List<list> extractFeatureFromJson(String newsJson) {
        Log.v("1", "NewJSON" + newsJson);
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }
        List<list> newsList = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsJson);
            //JSONObject response = baseJsonResponse.getJSONObject("response");
            //JSONArray newsArray = response.getJSONArray("results");

           // for (int i = 0; i < newsArray.length(); i++) {
              //  JSONObject currentNews = newsArray.getJSONObject(i);
            try{
            String title = baseJsonResponse.getString("title");
                String desc = baseJsonResponse.getString("explanation");

                String date = baseJsonResponse.getString("date");
                String url = baseJsonResponse.getString("url");
                    list news = new list(title, date, desc, url);
                    newsList.add(news);

                Log.i(LOG_TAG, "the data called is **** : " +news);
            }
                 catch (JSONException ex) {
            ex.printStackTrace();
        }

    } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }


        return newsList;
}
    public static String extractTitle(String newsJson) {
        Log.v("1", "NewJSON" + newsJson);
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }
        String news ="";
        List<list> newsList = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsJson);
            //JSONObject response = baseJsonResponse.getJSONObject("response");
            //JSONArray newsArray = response.getJSONArray("results");

            // for (int i = 0; i < newsArray.length(); i++) {
            //  JSONObject currentNews = newsArray.getJSONObject(i);
            try{
                String title = baseJsonResponse.getString("title");
                news = title;
                Log.i(LOG_TAG, "the ttttiiiiiiiitttttlllllllleeeee called is **** : " +title);
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }


        return news;
    }

}

