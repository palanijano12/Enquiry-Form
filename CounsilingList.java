package com.login.jano.Enquiry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.login.jano.Enquiry.interfaces.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CounsilingList extends AppCompatActivity {

   // String[] SampleArray = {"Name 1","Name 2","Name 3","Name 4","Name 5"};


    private String TAG = CounsilingList.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    JSONArray  jsonStr = null;

    private static String url = "http://www.sardiustech.com/ws/?tag=acdata/";

    ArrayList<HashMap<String, String>> counsilingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsiling_list);

        counsilingList= new ArrayList<>();

        lv = (ListView) findViewById(R.id.list_sff_cl);

        new GetCounsilingList().execute();



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=lv.getAdapter().toString();

                Intent i=new Intent(getApplicationContext(),AfterCounsiling.class);
                startActivity(i);
            }
        });




    }

    private class GetCounsilingList extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(CounsilingList.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response


            JSONObject jsonobj=null;

            jsonobj = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            try {
                   // JSONObject jsonObj = new JSONObject(jsonStr);

                   // JSONObject jsonObject;

                    // Getting JSON Array node
                  // JSONArray  = jsonObj.getJSONArray("0");

                    // looping through All Contacts
                    for (int i = 0; i < jsonStr.length(); i++) {

                              //JSONObject c = jsonStr. getJSONObject(i);

                                  JSONObject c =  new JSONObject();



                        String name = c.getString("fname");

                                System.out.println(name);


                                HashMap<String, String> contact = new HashMap<>();

                                // adding each child node to HashMap key => value

                                contact.put("fname", name);


                                // adding contact to contact list
                                counsilingList.add(contact);
                            }
                        } catch (final JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Json parsing error: " + e.getMessage(),
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            });

                        }
//                    } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    CounsilingList.this, counsilingList,
                    R.layout.list_row, new String[]{"fname"}, new int[]{R.id.textView});

            lv.setAdapter(adapter);
        }

    }


}
