package ru.startandroid.mybook;

import android.content.Entity;
import android.net.http.HttpResponseCache;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import ru.startandroid.mybook.db.DatabaseHandler;
import ru.startandroid.mybook.db.DbTables.Training;
import ru.startandroid.mybook.db.DbTables.Type;
import ru.startandroid.mybook.db.DbTables.TypeAndTrain;

/**
 * Created by Алексей on 08.11.2015.
 */
 public  class ApiConnector {
    private  String host = "https://itdtopteam.ru/";
    DatabaseHandler db;
    public ApiConnector (DatabaseHandler db)
    {
        this.db = db;
    }
    public void getAllTraining(boolean checkCount,String doc ,HashMap<String,String> param)
    {
        try {
            host = host+ doc;
            URL  fullUrl = new URL(host);
            checkCount = false;
            StringBuffer result = new StringBuffer();
            try {
                HttpsURLConnection urlConnection = (HttpsURLConnection) fullUrl.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                urlConnection.setRequestProperty("Connection", "Keep-Alive");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                String str = getPostDataString(param);
                writer.write(str);
                writer.flush();
                writer.close();
                os.close();
                int responseCode=urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String st = "";
                        while ((st = br.readLine()) != null) {
                            result.append(st);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        checkCount = false;
                    } finally {
                        urlConnection.disconnect();
                    }
                }

            }catch (MalformedURLException e)
            {
                e.printStackTrace();
                checkCount = false;
            }
            if(result.toString()!= "Post's Error" && result.toString()!= "Error of access") {
                JSONArray jArray = ConvertEntityToJsonArray(result);
                if (jArray != null) {
                    //setTextToTetView(jArray);
                    SaveTrainingToDB(jArray, db);
                    checkCount = true;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            checkCount = false;
        }


    }


    private JSONArray ConvertEntityToJsonArray(StringBuffer result)
    {
        JSONArray jsonArray = null;
        if(result!= null)
        {
            try {
                String Response = result.toString();
                Log.e("Entity",Response);
                jsonArray = new JSONArray(Response);
            }
            catch (JSONException e )
            {
                e.printStackTrace();
            }
            }
        return  jsonArray;
    }

    public void setTextToTetView(JSONArray jArray)
    {
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jobj;

                try {
                    String s = "";
                     jobj = jArray.getJSONObject(i);
                    s = "id_tp_tr :" + jobj.getInt("id_tp_tr") + "  id_tp: " + jobj.getInt("id_tp") + "   id_tr: " + jobj.getInt("id_tr")+
                            "id_type :" + jobj.getInt("id_type") + "  name_tp: " + jobj.getString("name_tp")+
                            "id_train :" + jobj.getInt("id_train") + "  name: " + jobj.getString("name") + "   descr: " + jobj.getString("descr");
                    Log.e("https", s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }


    public void SaveTrainingToDB(JSONArray jArray,DatabaseHandler db)
    {

        for (int i = 0;i<jArray.length();i++)
        {
            try
            {
                JSONObject jobj = jArray.getJSONObject(i);
                Training tr = new Training(jobj.getInt("id_train"),jobj.getString("name"),jobj.getString("round"),jobj.getString("descr"));
                Type tp = new Type(jobj.getInt("id_type"),jobj.getString("name_tp"));
                TypeAndTrain tp_tr = new TypeAndTrain(jobj.getInt("id_tp_tr"),jobj.getInt("id_type"),jobj.getInt("id_train"));
                db.addTraining(tr);
                db.addType(tp);
                db.addTypeandTran(tp_tr);
            }
            catch (JSONException e )
            {
                e.printStackTrace();
            }
        }
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
