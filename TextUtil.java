package com.login.jano.Enquiry;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

public class TextUtil {
    public static Context mContext;


    public enum ValidateAction {
        NONE, isValueNULL, isValidPassword, isValidSalutation, isValidFirstname, isValidLastname, isValidCard, isValidExpiry, isValidMail, isValidConfirmPassword, isNullPromoCode, isValidCvv, isNullMonth, isNullYear, isNullCardname,
        isContactNumber,isContactAddress,isValidqualiication,isfrnd_qualiication,isrmd_nuumber
    }


    public TextUtil(Context context) {

        mContext = context;
    }

    /**
     * This is method for check the Internet connection
     * <p>
     * <P>
     * This is method for check the Internet connection
     * </p>
     *
     * @return boolean is online
     */
    public static boolean isOnline(Context ctx) {

        mContext = ctx;
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo element : info) {
                    if (element.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This is method to validate the field like Mail,Password,Name,Salutation etc and show the appropriate alert message.
     */

    public static boolean validations(ValidateAction VA, Context con, String stringtovalidate) {
        String message = "";
        boolean result = false;
        switch (VA) {

            case isValidFirstname:
                if (TextUtils.getTrimmedLength(stringtovalidate)<1)
                    Toast.makeText(con, "Enter your all fields", Toast.LENGTH_LONG).show();

                else
                    result = true;
                break;
            case isValidLastname:
                if (TextUtils.getTrimmedLength(stringtovalidate)<1)
                    Toast.makeText(con, "Enter your last name", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;
            case isValueNULL:
                if (TextUtils.isEmpty(stringtovalidate))

                    Toast.makeText(con, "Enter your Age", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;
            case isValidSalutation:
                if (TextUtils.isEmpty(stringtovalidate) || stringtovalidate == null)
                    Toast.makeText(con, "Enter your Martial Status", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;


            case isContactAddress:
                if (TextUtils.isEmpty(stringtovalidate))

                    Toast.makeText(con, "Enter your Contact Address", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;

            case isValidqualiication:
                if (TextUtils.isEmpty(stringtovalidate))

                    Toast.makeText(con, "Enter your qualification", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;

            case isfrnd_qualiication:
                if (TextUtils.isEmpty(stringtovalidate))
                    Toast.makeText(con, "Enter your Friend qualification", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;

            case isrmd_nuumber:
                if (TextUtils.isEmpty(stringtovalidate))
                    Toast.makeText(con, "Enter  contact number", Toast.LENGTH_LONG).show();
                else if (stringtovalidate.length() < 10)
                    Toast.makeText(con, "Enter valid contact number", Toast.LENGTH_LONG).show();
                else
                    result = true;
                break;
            /*case isValidPassword:
                if (TextUtils.isEmpty(stringtovalidate))
                    message = "" + con.getResources().getString(R.string.enter_the_password);
                else if (stringtovalidate.length() < 5)
                    message = "" + con.getResources().getString(R.string.password_min_character);
                else if (stringtovalidate.length() > 32)
                    message = "" + con.getResources().getString(R.string.password_max_character);
                else
                    result = true;
                break;
            case isValidSalutation:
                if (TextUtils.isEmpty(stringtovalidate) || stringtovalidate == null)
                    message = "" + con.getResources().getString(R.string.please_select_your_salutation);
                else
                    result = true;
                break;


            case isValidMail:
                if (TextUtils.isEmpty(stringtovalidate))
                    message = "" + con.getResources().getString(R.string.enter_the_email);
                else if (!validdmail(stringtovalidate))
                    message = "" + con.getResources().getString(R.string.enter_the_valid_email);
                else
                    result = true;
                break;


            case isrmd_nuumber:
                if (TextUtils.isEmpty(stringtovalidate))
                    message = "" + con.getResources().getString(R.string.enter_contact_num);
                else if (stringtovalidate.length() < 10)
                    message = "" + con.getResources().getString(R.string.valid_contact_num);
                else
                    result = true;
                break;*/
        }

        return result;
    }
    public static boolean validdmail(String string) {
        // TODO Auto-generated method stub

        return isValidEmail(string);
    }

    public final static boolean isValidEmail(CharSequence target) {

        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


}
