package com.login.jano.Enquiry;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import com.login.jano.Enquiry.interfaces.APIResult;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author developer This AsyncTask used to communicate the application with server through HTTP connection. Here the request and response completely in JSON format. Constructor get the input details JSONObject,POST or GET then url. In pre execute,Show the progress dialog. In Background,Connect the and get the response. In Post execute, Return the result with interface.
 */
public class APIService_HTTP_JSON extends AsyncTask<String, String, String> {
    private final Context mContext;
    private boolean isSuccess = true;
    private boolean isGetMethod = true;
    public ProgressDialog mProgressdialog;
    private final APIResult response;
    InputStream inputStream = null;
    private JSONObject j;
    private String uri;

    public APIService_HTTP_JSON(Context context, APIResult response, boolean isGetMethod, String uri)
    {
        mContext = context;
        this.response = response;
        this.j = j;
        this.isGetMethod = isGetMethod;
        this.uri = uri;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        //     View view = View.inflate(mContext, R.layout.progress_bar, null);
        //    mProgressdialog = new Dialog(mContext, R.style.dialogwinddow);
        //  mProgressdialog.setContentView(view);
        mProgressdialog = new ProgressDialog(mContext);
        mProgressdialog.setMessage("loading");
        mProgressdialog.setCancelable(false);
        mProgressdialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        URL url = null;
        String result = "s";
        HttpURLConnection httpconnection = null;
        InputStream inputstream = null;
        try {
            url = new URL(uri);
            System.out.println("naga_url" + uri);
            httpconnection = (HttpURLConnection) url.openConnection();

            if (!isGetMethod) {
                httpconnection.setReadTimeout(30000);
                httpconnection.setConnectTimeout(35000);
                httpconnection.setRequestMethod("POST");
                httpconnection.setDoInput(true);
                httpconnection.setDoOutput(true);
                httpconnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
                String query = j.toString();
                System.out.println("naga_query" + query.toString());
                OutputStream os = httpconnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }

            inputstream = httpconnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(inputstream);
            StringBuilder stringBuffer = new StringBuilder();
            // byte[] buffer=new byte[1024];

            int read = -1;
            while ((read = (isw.read())) != -1) {
                stringBuffer.append((char) read);
                publishProgress("sdf");

            }

            result = stringBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputstream != null)
                    inputstream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return result;
    }
    //  protected String doInBackground(String... params)
//    {
//        String result = "";
//        String jsonString = "";
//        Log.d("Pass Api request", "" + uri + "\ndata:" + j);
//        try {
//            if (!TextUtil.isOnline(mContext)) {
//                isSuccess = false;
////			result = "" + mContext.getResources().getString(R.string.check_internet_connection);
////			return result;
//             //   response.getResult(false, mContext.getResources().getString(R.string.check_internet_connection));
//                result = mContext.getResources().getString(R.string.check_internet_connection);
////				return result;
//            } else {
//                if (isGetMethod) {
//                    HttpResponse response;
//                    HttpClient myClient = new DefaultHttpClient();
//                    HttpPost myConnection = new HttpPost(uri);
//                    try {
//                        response = myClient.execute(myConnection);
//                        result = EntityUtils.toString(response.getEntity(), "UTF-8");
//                    } catch (ClientProtocolException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    HttpClient httpclient = new DefaultHttpClient();
//                    HttpPost httpPost = new HttpPost(uri);
//                    jsonString = j.toString();
//                    StringEntity se = new StringEntity(jsonString);
//                    httpPost.setEntity(se);
//                    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
//                    HttpResponse httpResponse = httpclient.execute(httpPost);
//                    inputStream = httpResponse.getEntity().getContent();
//                    if (inputStream != null)
//                        result = convertInputStreamToString(inputStream);
//                    else
//                        result = "Please Try Again!";
//                }
//                System.out.println("Pass Api response" + result);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//
//    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        try {
            if (mProgressdialog.isShowing())
                mProgressdialog.dismiss();
            response.getResult(isSuccess, Html.fromHtml(result).toString());
        } catch (Exception e) {
            if (mProgressdialog.isShowing())
                mProgressdialog.dismiss();
            System.out.println("naga_ee"+e);
            e.printStackTrace();
            Log.i("Exception", e.toString());
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }
}