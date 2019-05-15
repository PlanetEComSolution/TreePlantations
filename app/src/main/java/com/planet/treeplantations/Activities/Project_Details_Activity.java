package com.planet.treeplantations.Activities;


import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.databinding.ActivityProjectDetailsBinding;
import com.planet.treeplantations.models.Project_Details_Model;
import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;

public class Project_Details_Activity extends AppCompatActivity {
    ActivityProjectDetailsBinding binding;
    private String latitude,longitude;
    private Pref preg_s;
    private Context context;
    private String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project__details_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Project Details");
        context =Project_Details_Activity.this;
        preg_s=new Pref(context);
        id = getIntent().getStringExtra("id");
        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Project_Details_Activity.this, MapsActivity.class);
                intent.putExtra("lat", latitude);
                intent.putExtra("long", longitude);
                startActivity(intent);
            }
        });
        if(isNetworkAvailable(this)){
            user_info_API();
        }else {
            Toast.makeText(this, "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void user_info_API() {

        final AlertDialog dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
        dialog.show();

        API_Interface apiService = API_Client.getClient().create(API_Interface.class);

    //  Call<Project_Details_Model> call = apiService.projectDetails("Admin","1357");
        Call<Project_Details_Model> call = apiService.projectDetails(preg_s.loadPreferences("userRole"),id);
        call.enqueue(new Callback<Project_Details_Model>() {

            @Override
            public void onResponse(Call<Project_Details_Model>call, Response<Project_Details_Model> response) {
                dialog.dismiss();
                Project_Details_Model details_responce_models = response.body();
               // latitude= details_responce_models.getLatitude();
                //longitude= details_responce_models.getLongitude();

                binding.setPdetail(details_responce_models);
            }
            @Override
            public void onFailure(Call<Project_Details_Model>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(Project_Details_Activity.this, "Network Error" , Toast.LENGTH_SHORT).show();
            }
        });
    }



}
