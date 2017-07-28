package com.login.jano.Enquiry.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.login.jano.Enquiry.APIService_HTTP_JSON;
import com.login.jano.Enquiry.Home;
import com.login.jano.Enquiry.R;
import com.login.jano.Enquiry.TextUtil;
import com.login.jano.Enquiry.interfaces.APIResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ViewPage extends Activity {
    private static final String TAG = "MyActivity";
    EditText firstName, age, maritalStatus, address,mobile_number,email_address,qualification,year_of_passing;
    EditText first_friend_name, first_friend_qualification, first_friend_email,first_friend_mobileNumber;

    RadioButton passport_yes, passport_no, abroad_exp_yes, abroad_exp_no,fresher,experienced,marital_yes,marital_no;
    Spinner countyList,studyCountyList;
    Button submit_btn;
    String Passport_value="",abroad_value="",selected_country="",selected_field="",selected_salary="",marital_value="" , job_value="";
    AppCompatCheckBox  job,education,internship;
    AppCompatCheckBox cbk_frnd,cbk_adv,cbk_web,cbk_facebook,cbk_nak,cbk_mon;
    LinearLayout job_layout,abroad_layout;
    EditText txt_Date,edtTxt_exp_field,edtTxt_exp_salary,edtTxt_abr_country,edtTxt_abr_field,edtTxt_abr_salary;
    int receantTouchId;
    String purpose,reference,formattedDate,edu_country,inst_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        firstName = (EditText) findViewById(R.id.first_name);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        email_address = (EditText) findViewById(R.id.email_address);
        qualification = (EditText) findViewById(R.id.qualifications);
        year_of_passing = (EditText) findViewById(R.id.year_of_passings);
        age = (EditText) findViewById(R.id.age);
        address = (EditText) findViewById(R.id.contact_address);
        first_friend_name = (EditText) findViewById(R.id.first_friend_name);
        first_friend_qualification = (EditText) findViewById(R.id.first_friend_qualification);
        first_friend_email = (EditText) findViewById(R.id.first_friend_email);
        first_friend_mobileNumber = (EditText) findViewById(R.id.first_friend_mobile_number);


        job_layout =(LinearLayout)findViewById(R.id.exp_layout);
        abroad_layout =(LinearLayout)findViewById(R.id.abr_layout);
        job = (AppCompatCheckBox) findViewById(R.id.job);
        education = (AppCompatCheckBox) findViewById(R.id.education);
        internship = (AppCompatCheckBox) findViewById(R.id.internship);


        cbk_frnd = (AppCompatCheckBox) findViewById(R.id.cbk_frnd);
        cbk_adv = (AppCompatCheckBox) findViewById(R.id.cbk_adv);
        cbk_web = (AppCompatCheckBox) findViewById(R.id.cbk_web);
        cbk_facebook = (AppCompatCheckBox) findViewById(R.id.cbk_facebook);
        cbk_nak = (AppCompatCheckBox) findViewById(R.id.cbk_nak);
        cbk_mon = (AppCompatCheckBox) findViewById(R.id.cbk_mon);



        edtTxt_exp_field = (EditText) findViewById(R.id.job_field);
        edtTxt_exp_salary = (EditText) findViewById(R.id.job_salary);

        edtTxt_abr_country = (EditText) findViewById(R.id.abr_country);
        edtTxt_abr_field = (EditText) findViewById(R.id.abr_field);
        edtTxt_abr_salary = (EditText) findViewById(R.id.abr_salary);

        try {
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = df.format(c.getTime());
            txt_Date.setText(txt_Date.getText()+formattedDate);
        }catch (Exception e){

        }
        passport_yes = (RadioButton) findViewById(R.id.passport_yes);
        passport_no = (RadioButton) findViewById(R.id.passport_no);

        marital_yes = (RadioButton) findViewById(R.id.married);
        marital_no = ( RadioButton) findViewById(R.id.single);

        abroad_exp_yes = (RadioButton) findViewById(R.id.abroad_yes);
        abroad_exp_no = (RadioButton) findViewById(R.id.abroad_no);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        fresher =(RadioButton)findViewById(R.id.fresher);
        experienced =(RadioButton)findViewById(R.id.experienced);

        experienced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    job_layout.setVisibility(View.VISIBLE);
            }
        });

        fresher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    job_layout.setVisibility(View.GONE);
            }
        });
        abroad_exp_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    abroad_layout.setVisibility(View.VISIBLE);
            }
        });
        abroad_exp_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    abroad_layout.setVisibility(View.GONE);
            }
        });
        receantTouchId=R.id.cbk_frnd;
        reference="friends";
        cbk_frnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cbk_frnd.setChecked(true);
                cbk_adv.setChecked(false);
                cbk_web.setChecked(false);
                cbk_facebook.setChecked(false);
                cbk_nak.setChecked(false);
                cbk_mon.setChecked(false);

                receantTouchId=v.getId();
                reference="friends";
            }
        });
        cbk_adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cbk_frnd.setChecked(false);
                cbk_adv.setChecked(true);
                cbk_web.setChecked(false);
                cbk_facebook.setChecked(false);
                cbk_nak.setChecked(false);
                cbk_mon.setChecked(false);

                receantTouchId=v.getId();
                reference="ADV";
            }
        });
        cbk_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if()
                cbk_frnd.setChecked(false);
                cbk_adv.setChecked(false);
                cbk_web.setChecked(true);
                cbk_facebook.setChecked(false);
                cbk_nak.setChecked(false);
                cbk_mon.setChecked(false);

                receantTouchId=v.getId();
                reference="website";
            }
        });
        cbk_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cbk_frnd.setChecked(false);
                cbk_adv.setChecked(false);
                cbk_web.setChecked(false);
                cbk_facebook.setChecked(true);
                cbk_nak.setChecked(false);
                cbk_mon.setChecked(false);

                receantTouchId=v.getId();
                reference="facebook";
            }
        });
        cbk_nak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cbk_frnd.setChecked(false);
                cbk_adv.setChecked(false);
                cbk_web.setChecked(false);
                cbk_facebook.setChecked(false);
                cbk_nak.setChecked(true);
                cbk_mon.setChecked(false);

                receantTouchId=v.getId();
                reference="nakuri";
            }
        });
        cbk_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cbk_frnd.setChecked(false);
                cbk_adv.setChecked(false);
                cbk_web.setChecked(false);
                cbk_facebook.setChecked(false);
                cbk_nak.setChecked(false);
                cbk_mon.setChecked(true);

                receantTouchId=v.getId();
                reference="monster";
            }
        });
        countyList = (Spinner) findViewById(R.id.countyList);
        studyCountyList= (Spinner) findViewById(R.id.study_countyList);
        purpose="job";

        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                job.setChecked(true);
                education.setChecked(false);
                internship.setChecked(false);
                purpose="job";

            }
        });
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                job.setChecked(false);
                education.setChecked(true);
                internship.setChecked(false);
                countyList.performClick();
                purpose="Higher Education";
            }
        });
        internship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                job.setChecked(false);
                education.setChecked(false);
                internship.setChecked(true);
                studyCountyList.performClick();
                purpose="Abroad Internship";
            }
        });

        List country = new ArrayList<>();
        country.add("Abroad Internship");
        country.add("Singapore");
        country.add("Malaysia");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countyList.setAdapter(dataAdapter);
        countyList.setSelection(0);

        countyList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                internship.setSelected(true);
                return false;
            }
        });
        countyList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                System.out.println("***************** ITEM==="+item);
                inst_country=item;
                selected_country=item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List studycountry = new ArrayList<>();
        studycountry.add("Higher Education");
        studycountry.add("Italy");
        studycountry.add("UK");
        studycountry.add("Australia");
        studycountry.add("Poland");
        studycountry.add("Mauritius");
        studycountry.add("Cyprus");

        ArrayAdapter<String> studyydataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, studycountry);
        studyydataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studyCountyList.setAdapter(studyydataAdapter);
        studyCountyList.setSelection(0);

        studyCountyList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                education.setSelected(true);
                return false;
            }
        });
        studyCountyList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                System.out.println("***************** ITEM===" + item);
                //selected_country_study = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit_btn.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_firstName,str_age,marttial_status,mobile,mail_addres,quali,yearop,conotact_address,str_friend_first_name,friend_qualiication,friend_emaili,friend_number,
               str_friend_second_name,secondfriend_qualification,secondfriend_number;
                str_firstName=firstName.getText().toString();
                str_age=age.getText().toString();
                mobile=mobile_number.getText().toString();
                mail_addres=email_address.getText().toString();
                quali = qualification.getText().toString();
                yearop = year_of_passing.getText().toString();
                conotact_address=address.getText().toString();


                str_friend_first_name=first_friend_name.getText().toString();
                friend_qualiication=first_friend_qualification.getText().toString();
                friend_emaili = first_friend_email.getText().toString();
                friend_number=first_friend_mobileNumber.getText().toString();


//                str_friend_second_name=second_friend_name.getText().toString();
//                secondfriend_qualification=second_friend_qualification.getText().toString();
//                secondfriend_number=second_friend_mobileNumber.getText().toString();



//            if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, str_firstName))
//
//                   if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, str_age))
//                            if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, marttial_status))
//                                if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, conotact_address))
//                                    if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, qualiication))

//                                        if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, str_friend_first_name))
//                                            if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, friend_qualiication))
//                                                if (TextUtil.validations(TextUtil.ValidateAction.isrmd_nuumber, ViewPage.this, friend_number))

//                                                    if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, str_friend_second_name))
//                                                        if (TextUtil.validations(TextUtil.ValidateAction.isValidFirstname, ViewPage.this, secondfriend_qualification))
//                                                            if (TextUtil.validations(TextUtil.ValidateAction.isrmd_nuumber, ViewPage.this, secondfriend_number))
                                                {
                                                    if()

                                                    if(!Passport_value.equals(""))
                                                    {
  //                                                      System.out.println("test.... first");
                                                        if(!abroad_value.equals(""))
                                                    {
//                                                        if(!selected_country.equals("")||!selected_country.equals("Select")&&
//                                                                    !selected_country_study.equals("")||!selected_country_study.equals("Select"))
                                                            {

                                                                String expfi=edtTxt_exp_field.getText().toString();
                                                                String expsal=edtTxt_exp_salary.getText().toString();
                                                                String abrcoun=edtTxt_abr_country.getText().toString();
                                                                String abrfi=edtTxt_abr_field.getText().toString();
                                                                String abrsal=edtTxt_abr_salary.getText().toString();
                                                                String url="http://www.sardiustech.com/ws/?tag=insertbc&name=" + str_firstName + "&age=" + str_age+"&marital="+marital_value
                                                                        +"&address="+conotact_address+"&passport="+Passport_value+"&mobile="+mobile+"&email="+mail_addres+"&quali="+quali+"&yop="+yearop
                                                                       +"&refby="+reference+"&ref_name_1="+str_friend_first_name+"&ref_qual_1="+friend_qualiication+"&ref_mob_1="+friend_number+"&ref_email_1"+friend_emaili

                                                                        +"&abexp="+abroad_value+"&abcountry="+abrcoun+"&abfield="+abrfi+"&absalary="+abrsal+"&date="+formattedDate+"&purpose="+purpose+"&abcountry="+selected_country+"&abfield="+selected_field+"&absalary="+selected_salary
                                                                         +"&jobexp="+job_value +"&jobsalary="+expsal+"&jobfield="+expfi;



                                                                Log.e("url",url);
                                                               url = url.trim().replace(" ", "%20");

//                                                                try {
//                                                                    url = URLEncoder.encode(url, "utf-8");
//                                                                } catch (Exception e) {
//                                                                    e.printStackTrace();
//                                                                }
                                                                new Insert(url);
                                                            }
//                                                            else
//                                                            {
//                                                                Toast.makeText(getApplicationContext(),"Please choose Country..",Toast.LENGTH_SHORT).show();
//                                                            }
                                                        }
                                                    }

                                                }
            }
        });
    }

    public void onRadioButtonClicked(View v) {
        //is the current radio button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {

            // ....* Marital Status ...*

            case R.id. married:
                if (checked)
                    //if windows phone programming book is selected
                    //set the checked radio button's text style bold italic
                    marital_yes.setTypeface(null, Typeface.BOLD_ITALIC);
                //set the other radio button text style to default
              marital_no.setTypeface(null, Typeface.NORMAL);
                marital_value="Single";
                break;

            case R.id.single:
                if (checked)
                    marital_no.setTypeface(null, Typeface.BOLD_ITALIC);
                marital_yes.setTypeface(null, Typeface.NORMAL);
               marital_value="Married";
                break;

            //... * passport status ... 

            case R.id.passport_yes:
                if (checked)
                    passport_yes.setTypeface(null, Typeface.BOLD_ITALIC);
                passport_no.setTypeface(null, Typeface.NORMAL);
                Passport_value="yes";
                break;

            case R.id.passport_no:
                if (checked)
                    passport_no.setTypeface(null, Typeface.BOLD_ITALIC);
                passport_yes.setTypeface(null, Typeface.NORMAL);
                Passport_value="no";
                break;


            // .....* job status

            case R.id.fresher:
                if (checked)
                   fresher.setTypeface(null, Typeface.BOLD_ITALIC);
                experienced.setTypeface(null, Typeface.NORMAL);
                job_value="Fresher";
                break;
            case R.id.experienced:
                if (checked)
                  experienced.setTypeface(null, Typeface.BOLD_ITALIC);
                fresher.setTypeface(null, Typeface.NORMAL);
               job_value="Experienced";
                break;

            // .....* abroad status

            case R.id.abroad_yes:
                if (checked)
                    abroad_exp_yes.setTypeface(null, Typeface.BOLD_ITALIC);
                abroad_exp_no.setTypeface(null, Typeface.NORMAL);
                abroad_value="yes";
                break;
            case R.id.abroad_no:
                if (checked)
                    abroad_exp_no.setTypeface(null, Typeface.BOLD_ITALIC);
                abroad_exp_yes.setTypeface(null, Typeface.NORMAL);
                abroad_value="no";
                break;
        }

    }

    private class Insert implements APIResult {
        public Insert(final String url) {
            // TODO Auto-generated constructor stub
            // new APIService_Volley_JSON(LoginAct.this, this, j, false).execute(url);
            System.out.println("Url::: " + url);

            new APIService_HTTP_JSON(ViewPage.this, this, true, url).execute();
        }

        @Override
        public void getResult(final boolean isSuccess, final String result) {
            // TODO Auto-generated method stub
            System.out.println("Check ::::: response" + result);
            try {
                if (isSuccess) {
                    final JSONObject json = new JSONObject(result);
                    if (json.getInt("success") == 1) {
                        Toast.makeText(ViewPage.this, "Successfully Inserted!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ViewPage.this, Home.class);
                        startActivity(intent);
                    } else if (json.getInt("success") == 0) {
                        Toast.makeText(ViewPage.this, "" + json.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    runOnUiThread
                            (
                            new Runnable()
                            {
                        public void run()
                        {

                            //          ShowToast(LoginAct.this, result);
                        }
                    });
//                  alert_view(LoginAct.this, "" + getResources().getString(R.string.message), "" + result, "" + getResources().getString(R.string.ok), "");
                }
            } catch (final JSONException e) {
                // TODO Auto-generated catch block
                //DoneBtn.setEnabled(true);
            }
        }
    }
}
