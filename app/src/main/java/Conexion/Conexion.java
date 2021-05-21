package Conexion;


import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class Conexion {
    //public String strURLServidor = "http://192.168.43.103/comuna/";
    public String strURLServidor = "https://entidadprueba.000webhostapp.com/comuna/";
    public String HttpRequest(String strURL){
        JSONObject jObj = null;
        HttpURLConnection urlConnection = null;
        String jsonString = new String();
        Log.e("Hola Jhon ", "url"+strURLServidor+""+strURL);
        StringBuilder sb = null;
        try {
            URL url = new URL(strURLServidor+""+strURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
            char[] buffer = new char[1024];
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sb != null) {
            jsonString = sb.toString();

            // return JSON String
        }else {
            jsonString = "";
        }
        return jsonString;
    }

    public JSONObject Convertir_JsonObject(String jsonString){
        JSONObject jsonObjectRet = null;
        try {
            jsonObjectRet = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jsonObjectRet;
    }

    public String HttpRequestString(String strURL){
        HttpURLConnection urlConnection = null;
        StringBuilder sb = null;
        String line ="";

        try {
            URL url = new URL(strURLServidor+""+strURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
            sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
            }

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""+sb;
    }


    public String Ejecutar_Post(String strURL, HashMap<String, String> PData){


        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url;
            HttpURLConnection httpURLConnectionObject ;
            OutputStream OutPutStream;
            BufferedWriter bufferedWriterObject ;
            BufferedReader bufferedReaderObject ;
            int RC ;
           // Log.e("Hola jhon ", "url "+strURLServidor+""+strURL);
            url = new URL(strURLServidor+""+strURL);

            httpURLConnectionObject = (HttpURLConnection) url.openConnection();
            httpURLConnectionObject.setReadTimeout(19000);
            httpURLConnectionObject.setConnectTimeout(19000);
            httpURLConnectionObject.setRequestMethod("POST");
            httpURLConnectionObject.setDoInput(true);
            httpURLConnectionObject.setDoOutput(true);
            OutPutStream = httpURLConnectionObject.getOutputStream();
            bufferedWriterObject = new BufferedWriter(
                    new OutputStreamWriter(OutPutStream, "UTF-8"));
            bufferedWriterObject.write(bufferedWriterDataFN(PData));
            bufferedWriterObject.flush();
            bufferedWriterObject.close();
            OutPutStream.close();
            RC = httpURLConnectionObject.getResponseCode();
            if (RC == HttpsURLConnection.HTTP_OK) {
                bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                stringBuilder = new StringBuilder();

                String RC2;

                while ((RC2 = bufferedReaderObject.readLine()) != null){

                    stringBuilder.append(RC2);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {
        boolean check = true;
        StringBuilder stringBuilderObject;
        stringBuilderObject = new StringBuilder();
        for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
            if (check) {
                check = false;
            }
            else {
                stringBuilderObject.append("&");
            }
            stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));
            stringBuilderObject.append("=");
            stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
        }

        return stringBuilderObject.toString();
    }
}
