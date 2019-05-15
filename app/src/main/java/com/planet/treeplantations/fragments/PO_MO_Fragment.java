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
import com.planet.treeplantations.adapter.PO_MO_Adapter;
import com.planet.treeplantations.adapter.Tree_Spicies_Adapter;
import com.planet.treeplantations.models.PO_MO_Model;
import com.planet.treeplantations.models.PO_MO_Responce_Model;
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

public class PO_MO_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private Pref preg_s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_po__mou_fragment, container, false);
        context=getActivity();
        preg_s=new Pref(context);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if(isNetworkAvailable(getActivity())){
                user_info_API();
            }else {
                Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
        }
    }

    private void user_info_API() {

        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        Call<PO_MO_Model> call = apiService.PO_MO(preg_s.loadPreferences("userRole"),preg_s.loadPreferences("loginName"));

        call.enqueue(new Callback<PO_MO_Model>() {

            @Override
            public void onResponse(Call<PO_MO_Model>call, Response<PO_MO_Model> response) {
                String user_info = response.body().getStatus();
                dialog.dismiss();
                List<PO_MO_Responce_Model> po_mo_responce_models = response.body().getData();
                recyclerView.setAdapter(new PO_MO_Adapter(context,po_mo_responce_models));

            }

            @Override
            public void onFailure(Call<PO_MO_Model>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
