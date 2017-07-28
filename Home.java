package com.login.jano.Enquiry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.login.jano.Enquiry.Activity.LoginPage;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void OpenBC(View view)
    {
        Intent intent = new Intent(Home.this, LoginPage.class);
        startActivity(intent);
    }

    public void OpenAC(View view)
    {
        Intent intent = new Intent(Home.this, CounsilingList.class);
        startActivity(intent);
    }
}
