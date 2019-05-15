package com.planet.treeplantations.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.adapter.Image_Adapter;
import com.planet.treeplantations.databinding.ActivityDashBoardDetailsBinding;
import com.planet.treeplantations.models.AllTreePlantDetail;
import com.planet.treeplantations.models.Details_Responce_Model;
import com.planet.treeplantations.models.Image_Model;
import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;
import com.planet.treeplantations.utils.RecyclerViewSpacesItemDecoration;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;

public class AllTree_Details_Activity extends AppCompatActivity {
    private ActivityDashBoardDetailsBinding binding;
    private Pref pref;
    private String id = "";
    private String latitude, kml_file, kml = "";
    private String longitude;
    private Pref preg_s;
    private Context context;
    private ArrayList<Image_Model> arrayList;
    private Details_Responce_Model details_responce_models;
    private Image_Adapter image_adapter;

    //  private Button location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board__details_);
        //  setContentView(R.layout.activity_dash_board__details_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Details");
        context = AllTree_Details_Activity.this;
        preg_s = new Pref(context);
        arrayList = new ArrayList<>();
        try {
            id = getIntent().getStringExtra("id");
            kml_file = getIntent().getStringExtra("kml");
            pref = new Pref(getApplicationContext());
        } catch (Exception e) {
            e.getMessage();
        }
        image_adapter = new Image_Adapter(AllTree_Details_Activity.this, arrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(AllTree_Details_Activity.this, 2);
        binding.recyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = 0;
        binding.recyclerView.addItemDecoration(new RecyclerViewSpacesItemDecoration(spacingInPixels));
        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!kml_file.trim().equalsIgnoreCase("")) {
                    Intent intent = new Intent(AllTree_Details_Activity.this, KmlDemoActivity.class);
                    intent.putExtra("kmll", kml_file);
                    intent.putExtra("lat", latitude);
                    intent.putExtra("long", longitude);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "File not available!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (isNetworkAvailable(this)) {
            user_info_API();
        } else {
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
        arrayList.clear();
        // Call<Details_Responce_Model> call = apiService.details(pref.loadPreferences("stateName"),pref.loadPreferences("pid"),pref.loadPreferences("userRole"),pref.loadPreferences("loginName"),id);
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        //  Call<Details_Responce_Model> call = apiService.details(preg_s.loadPreferences("stateName"), preg_s.loadPreferences("pid"), preg_s.loadPreferences("userRole"), preg_s.loadPreferences("loginName"), id);

        //   Call<Details_Responce_Model> call = apiService.projectDetails( preg_s.loadPreferences("userRole"), id);
        Call<AllTreePlantDetail> call = apiService.allTreePlantedRecord(preg_s.loadPreferences("userRole"), preg_s.loadPreferences("loginName"), "", "", "", "", "", "", "", id);

        call.enqueue(new Callback<AllTreePlantDetail>() {

            @Override
            public void onResponse(Call<AllTreePlantDetail> call, Response<AllTreePlantDetail> response) {
                dialog.dismiss();
               AllTreePlantDetail allTreePlantDetail= response.body();
               if(allTreePlantDetail.getData().size()>0) {
                   details_responce_models = allTreePlantDetail.getData().get(0);
               }
                latitude = details_responce_models.getLatitude();
                longitude = details_responce_models.getLongitude();
                kml_file = details_responce_models.getPolygonDetai();
                setImages();
                binding.setDetail(details_responce_models);
                binding.recyclerView.setAdapter(image_adapter);

            }

            @Override
            public void onFailure(Call<AllTreePlantDetail> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(AllTree_Details_Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setImages() {
        if (details_responce_models.getImage1() != null && !details_responce_models.getImage1().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage1());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage2() != null && !details_responce_models.getImage2().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage2());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage3() != null && !details_responce_models.getImage3().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage3());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage4() != null && !details_responce_models.getImage4().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage4());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage5() != null && !details_responce_models.getImage5().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage5());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage6() != null && !details_responce_models.getImage6().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage6());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage7() != null && !details_responce_models.getImage7().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage7());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage8() != null && !details_responce_models.getImage8().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage8());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage9() != null && !details_responce_models.getImage9().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage9());
            arrayList.add(image_model);
        }
        if (details_responce_models.getImage10() != null && !details_responce_models.getImage10().equals("")) {

            Image_Model image_model = new Image_Model("http://web821.128.99.new.ocpwebserver.com" + details_responce_models.getImage10());
            arrayList.add(image_model);
        }
    }
}
