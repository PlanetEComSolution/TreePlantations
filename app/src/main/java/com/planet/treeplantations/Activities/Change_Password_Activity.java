package com.planet.treeplantations.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;

import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;

import org.json.JSONArray;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.rest.API_Client.BASE_URL;
import static com.planet.treeplantations.utils.Util.isNetworkAvailable;

public class Change_Password_Activity extends AppCompatActivity {
    private Pref pref;
    private EditText old_password, ne_password, rep_pass2;
    private String str_old_pass, str_new_pass, str_new_pass2, str_name, str_email;
    private TextView name, emai;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_change_password_);
        pref = new Pref(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Change Password");
        name = (TextView) findViewById(R.id.name);
        emai = (TextView) findViewById(R.id.email);
        old_password = (EditText) findViewById(R.id.old_password);
        ne_password = (EditText) findViewById(R.id.ne_password);
        rep_pass2 = (EditText) findViewById(R.id.rep_pass2);
        Button btn_sub = (Button) findViewById(R.id.email_sign_in_button);
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateAllFields()) {
                    if (isNetworkAvailable(Change_Password_Activity.this)) {
                       // change_password();
                        ChangePawd();
                    } else {
                        Toast.makeText(Change_Password_Activity.this, "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        name.setText(pref.loadPreferences("name"));
        emai.setText(pref.loadPreferences("email"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void change_password() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
        dialog.show();

        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        Call<ResponseBody> call = apiService.changePassword(str_name, str_email, str_new_pass2);

        //  Call<Dash_Main_Model> call = apiService.get_user(preg_s.loadPreferences("stateName"),preg_s.loadPreferences("Id"),preg_s.loadPreferences("userRole"),preg_s.loadPreferences("name"));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // if (response.isSuccessful()) {
                String responseString = String.valueOf(response.body());
                Toast.makeText(Change_Password_Activity.this, responseString, Toast.LENGTH_LONG).show();
                dialog.dismiss();

                //  }
//                dialog.dismiss();
//
//                try {
//                    String result = response.body().string();
//                    Toast.makeText(Change_Password_Activity.this,result,Toast.LENGTH_LONG).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                // finish();
//                Log.i("Response", response.body().toString());
//                //Toast.makeText()
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.i("onSuccess", response.body().toString());
//                    } else {
//                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                    }
//                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(Change_Password_Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validateAllFields() {
        str_name = pref.loadPreferences("loginName");
        str_email = emai.getText().toString();
        str_old_pass = old_password.getText().toString();
        if (str_old_pass.trim().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Old Password!", Toast.LENGTH_SHORT).show();

            return false;
        }

        str_new_pass = ne_password.getText().toString();
        if (str_new_pass.trim().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter New Password!", Toast.LENGTH_SHORT).show();

            return false;
        }

        str_new_pass2 = rep_pass2.getText().toString();
        if (str_new_pass2.trim().equalsIgnoreCase("")) {

            Toast.makeText(this, "Please Enter Confirm Password!", Toast.LENGTH_SHORT).show();

            return false;
        }
        if (!str_old_pass.equals(pref.loadPreferences("password"))) {
            Toast.makeText(this, "Please Enter Old Password Correctly!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!str_new_pass.equals(str_new_pass2)) {
            Toast.makeText(this, "Please Enter Old Password Correctly!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    void ChangePawd() {

        //   list_Party.clear();
        showProgressDialog(Change_Password_Activity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + "ChangePassword?lname=" + str_name + "&email=" + str_email + "&newpwd=" + str_new_pass2,

                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressDialog();
                        try {
                            JSONObject j = new JSONObject(response);
                            String status = j.getString("status");
                            if (status.equalsIgnoreCase("0")) {
                                Toast.makeText(getApplicationContext(), "Password not changed!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Password changed successfully!", Toast.LENGTH_SHORT).show();

                                Intent mainIntent = new Intent(Change_Password_Activity.this,LoginActivity.class);
                                startActivity(mainIntent);
                                finish();

                            }


                        } catch (Exception e) {
                            e.getCause();
                        }
                    }
                },


                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressDialog();
                        Toast.makeText(getApplicationContext(), "Some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
