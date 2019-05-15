package com.planet.treeplantations.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.adapter.Added_Adapter;
import com.planet.treeplantations.adapter.Added_Adapter_WithoutBinding;
import com.planet.treeplantations.adapter.State_Adapter;
import com.planet.treeplantations.databinding.FragmentDashboardFragmentBinding;
import com.planet.treeplantations.models.Dash_Main_Model;
import com.planet.treeplantations.models.Dash_count_Model;
import com.planet.treeplantations.models.Dashboard_Data_Model;
import com.planet.treeplantations.models.Dashboard_data;
import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.planet.treeplantations.utils.Util.isNetworkAvailable;


public class DashBoard_without_binding extends Fragment {

    private String totalcost, state_name, plant_no;
    private List<Dashboard_data> list;
    private List<Dashboard_Data_Model> dm_list;
    private Pref preg_s;
    private FragmentDashboardFragmentBinding binding;
    private Context context;
    private RecyclerView recyclerView;
     private Added_Adapter_WithoutBinding adapter;



    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getActivity().setTitle("DashBoard");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view= inflater.inflate(R.layout.fragment_dash_board_without_binding, container, false);
        context =getActivity();

        if(isNetworkAvailable(getActivity())){
            user_info_API();
        }else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        dm_list = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_recor);
      //  recyclerView.setNestedScrollingEnabled(false);
        adapter =new Added_Adapter_WithoutBinding(context,dm_list);
        adapter.setLoadMoreListener(new Added_Adapter_WithoutBinding.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = dm_list.size() - 1;
                       loadMore(index);



                    }
                });
                //Calling loadMore function in Runnable to fix the
                // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);


       return view;
    }

    private void loadMore(int index) {

        dm_list.add(new Dashboard_Data_Model());
        adapter.notifyItemInserted(dm_list.size()-1);


        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();

        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        Call<Dash_Main_Model> call = apiService.get_user("Bihar","1","Admin","Admin");

        // Call<Dash_Main_Model> call = apiService.get_user(preg_s.loadPreferences("stateName"),preg_s.loadPreferences("pid"),preg_s.loadPreferences("userRole"),preg_s.loadPreferences("loginName"));
        call.enqueue(new Callback<Dash_Main_Model>() {

            @Override
            public void onResponse(Call<Dash_Main_Model> call, Response<Dash_Main_Model> response) {
                dm_list.remove(dm_list.size()-1);
                dialog.dismiss();
                String user_info = response.body().getStatus();
                Dash_count_Model user_ = response.body().getOTotalCount();
                List<Dashboard_data> user_state = response.body().getData();
                ///  List<Dashboard_Data_Model> _list = response.body().getDashdata();

                List<Dashboard_Data_Model> _list = response.body().getDashdata();
                if (_list.size() > 0) {
                    //add loaded data
                    dm_list.addAll(_list);
                } else {//result size 0 means there is no more data available at server
                    adapter.setMoreDataAvailable(false);
                    //telling adapter to stop calling load more as no more server data available
                    Toast.makeText(context, "No More Data Available", Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataChanged();
                //should call the custom method adapter.notifyDataChanged here to get the correct loading status
            }



            @Override
            public void onFailure(Call<Dash_Main_Model>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error" , Toast.LENGTH_SHORT).show();
            }
        });
    }








    private void user_info_API() {

        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();

        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        Call<Dash_Main_Model> call = apiService.get_user("Bihar","1","Admin","Admin");

        // Call<Dash_Main_Model> call = apiService.get_user(preg_s.loadPreferences("stateName"),preg_s.loadPreferences("pid"),preg_s.loadPreferences("userRole"),preg_s.loadPreferences("loginName"));
        call.enqueue(new Callback<Dash_Main_Model>() {

            @Override
            public void onResponse(Call<Dash_Main_Model>call, Response<Dash_Main_Model> response) {

                dialog.dismiss();
                String user_info = response.body().getStatus();
                Dash_count_Model user_ = response.body().getOTotalCount();
                List<Dashboard_data> user_state = response.body().getData();

                List<Dashboard_Data_Model> _list = response.body().getDashdata();

              //  if(response.isSuccessful()){
                    dm_list.addAll(_list);
                    adapter.notifyDataChanged();
               // }else{
                    //Log.e(TAG," Response Error "+String.valueOf(response.code()));
              //  }




//
//                Dash_count_Model ggg=new Dash_count_Model();
//                ggg.setTtlAudit("All Audit Records:  "+  user_.getTtlAudit());
//                ggg.setTtlPrjct("Total Project:      "+ user_.getTtlPrjct());
//                ggg.setTtlStateUser("Total State User:    "+ user_.getTtlStateUser());
//                ggg.setTtlUser("Total User:           "+user_.getTtlUser());
//                binding.setUser(ggg);
//
//                if (isAdded()) {
//                    binding.recyclerView.setAdapter(new State_Adapter(user_state));
//                    dialog.dismiss();
//                }
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.recyclerViewRecord.setAdapter(new Added_Adapter(context,user_dashboard));
//                    }
//                }, 100);

            }

            @Override
            public void onFailure(Call<Dash_Main_Model>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error" , Toast.LENGTH_SHORT).show();
            }
        });
                }





}
