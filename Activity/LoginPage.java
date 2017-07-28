package com.login.jano.Enquiry.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.login.jano.Enquiry.APIService_HTTP_JSON;
import com.login.jano.Enquiry.R;
import com.login.jano.Enquiry.TextUtil;
import com.login.jano.Enquiry.interfaces.APIResult;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends Activity {
    EditText username, password_edt;
    Button sign;
    String email_address, password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password_edt = (EditText) findViewById(R.id.pwd);
        sign = (Button) findViewById(R.id.sign);


        sign.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        // Intent intent = new Intent(LoginPage.this,ViewPage.class);
                                        // startActivity(intent);
                                        if (TextUtil.isOnline(LoginPage.this)) {
                                            try {
                                                email_address = username.getText().toString().trim();
                                                password = password_edt.getText().toString().trim();
                                                if (TextUtils.isEmpty(email_address)) {
                                                    Toast.makeText(LoginPage.this, "Enter your email address", Toast.LENGTH_LONG).show();
                                                } else if (!TextUtil.validdmail(email_address))
                                                    Toast.makeText(LoginPage.this, "Enter your valid email address", Toast.LENGTH_LONG).show();
                                                else if (TextUtils.isEmpty(password))
                                                    Toast.makeText(LoginPage.this, "Enter your password", Toast.LENGTH_LONG).show();
                                                else
                                                    //  startActivity(new Intent(LoginPage.this, ViewPage.class));
                                                    new SignIn("http://www.sardiustech.com/ws/?tag=login&email=" + email_address + "&pass=" + password);

                                            } catch (Exception e) {
                                                // TODO: handle exception
                                                e.printStackTrace();
                                                //ShowToast(LoginAct.this, "Invalid format");
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Check your net connection...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
        );
    }

    private class SignIn implements APIResult {

        public SignIn(final String url) {
            // TODO Auto-generated constructor stub
            // new APIService_Volley_JSON(LoginAct.this, this, j, false).execute(url);
            System.out.println("Url::: " + url);

            new APIService_HTTP_JSON(LoginPage.this, this, true, url).execute();
        }

        @Override
        public void getResult(final boolean isSuccess, final String result) {
            // TODO Auto-generated method stub
            System.out.println("Check ::::: response" + result);
            try {
                if (isSuccess) {
                    final JSONObject json = new JSONObject(result);
                    if (json.getInt("success") == 1) {
                        Toast.makeText(LoginPage.this, "Successfully Login", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginPage.this, ViewPage.class));
                    } else if (json.getInt("success") == 0) {
                        Toast.makeText(LoginPage.this, "" + json.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            //          ShowToast(LoginAct.this, result);
                        }
                    });
//                    alert_view(LoginAct.this, "" + getResources().getString(R.string.message), "" + result, "" + getResources().getString(R.string.ok), "");
                }
            } catch (final JSONException e) {
                // TODO Auto-generated catch block
                //DoneBtn.setEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}