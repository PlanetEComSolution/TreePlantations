package com.planet.treeplantations.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.adapter.All_Project_Adapter;
import com.planet.treeplantations.adapter.Tree_Spicies_Adapter;
import com.planet.treeplantations.models.All_Project_Model;
import com.planet.treeplantations.models.All_Tree_Responce_Model;
import com.planet.treeplantations.models.Tree_Species_Model;
import com.planet.treeplantations.models.Tree_Spicies_Responce_Model;
import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;


public class Tree_Spicies_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private Pref preg_s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_tree__spicies_, container, false);
        context=getActivity();
        preg_s=new Pref(context);
        if(isNetworkAvailable(getActivity())){
            user_info_API();
        }else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        return view;
    }

    private void user_info_API() {

        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        Call<Tree_Species_Model> call = apiService.treeSpicies(preg_s.loadPreferences("userRole"));

        call.enqueue(new Callback<Tree_Species_Model>() {

            @Override
            public void onResponse(Call<Tree_Species_Model>call, Response<Tree_Species_Model> response) {
                String user_info = response.body().getStatus();
                dialog.dismiss();
                List<Tree_Spicies_Responce_Model> tree_spicies_responce_models = response.body().getData();
                recyclerView.setAdapter(new Tree_Spicies_Adapter(context,tree_spicies_responce_models));

            }

            @Override
            public void onFailure(Call<Tree_Species_Model>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error" , Toast.LENGTH_SHORT).show();
            }
        });
    }


}
