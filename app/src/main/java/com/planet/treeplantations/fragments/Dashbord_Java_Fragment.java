package com.planet.treeplantations.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.planet.treeplantations.utils.EqualSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;


public class Dashbord_Java_Fragment extends Fragment {
    List<Dashboard_Data_Model> user_dashboard = new ArrayList<>();
    String search_text = "";
    // ImageView img_srch;
    EditText et_search;
    TextView txt_no_data;
    RecyclerView recycler_view_record;
    private ArrayList<Dashboard_data> list;
    private ArrayList<Dashboard_Data_Model> dm_list;
    private Pref preg_s;
    private FragmentDashboardFragmentBinding binding;
    private Context context;
    private Dialog dialog;
    private ProgressDialog mProgressDialog;
    private Added_Adapter_WithoutBinding adapter;

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getActivity().setTitle("Dashboard");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_fragment, container, false);
        View view = binding.getRoot();
        context = getActivity();
        preg_s = new Pref(context);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);

        binding.recyclerViewRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewRecord.setNestedScrollingEnabled(false);

        binding.recyclerView.addItemDecoration(new EqualSpacingItemDecoration(1, EqualSpacingItemDecoration.VERTICAL));
        binding.recyclerViewRecord.addItemDecoration(new EqualSpacingItemDecoration(1, EqualSpacingItemDecoration.VERTICAL));

        if (isNetworkAvailable(getActivity())) {
            user_info_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }

       /* img_srch = view.findViewById(R.id.img_srch);
           img_srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDialog();
            }
        });*/

        txt_no_data = view.findViewById(R.id.txt_no_data);
        txt_no_data.setVisibility(View.GONE);
        et_search = view.findViewById(R.id.et_search);
        recycler_view_record = view.findViewById(R.id.recycler_view_record);
        search();

        return view;
    }

    private void user_info_API() {

        // final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        // dialog.show();
        user_dashboard.clear();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        //Call<Dash_Main_Model> call = apiService.get_user("Bihar","1","Admin","Admin");

        Call<Dash_Main_Model> call = apiService.get_user(preg_s.loadPreferences("stateName"), preg_s.loadPreferences("pid"), preg_s.loadPreferences("userRole"), preg_s.loadPreferences("loginName"));
        call.enqueue(new Callback<Dash_Main_Model>() {

            @Override
            public void onResponse(Call<Dash_Main_Model> call, Response<Dash_Main_Model> response) {
                // dialog.dismiss();

                // String user_info = response.body().getStatus();
                Dash_count_Model user_ = response.body().getOTotalCount();
                List<Dashboard_data> user_state = response.body().getData();
                user_dashboard = response.body().getDashdata();

                Dash_count_Model ggg = new Dash_count_Model();
                ggg.setTtlAudit("All Audit Records:  " + user_.getTtlAudit());
                ggg.setTtlPrjct("Total Project:      " + user_.getTtlPrjct());
                ggg.setTtlStateUser("Total State User:    " + user_.getTtlStateUser());
                ggg.setTtlUser("Total User:           " + user_.getTtlUser());
                binding.setUser(ggg);

                if (isAdded()) {
                    binding.recyclerView.setAdapter(new State_Adapter(user_state));
                }

//                 final Handler handler = new Handler();
//                  handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                //                        }
                //   binding.recyclerViewRecord.setAdapter(new Added_Adapter(context, user_dashboard));

                adapter = new Added_Adapter_WithoutBinding(context, user_dashboard);


                //  binding.shimmerViewContainer.stopShimmerAnimation();
                binding.shimmerViewContainer.setVisibility(View.GONE);
                binding.linL.setVisibility(View.VISIBLE);
                binding.linLl.setVisibility(View.VISIBLE);
                binding.linR.setVisibility(View.VISIBLE);

                recycler_view_record.setHasFixedSize(true);
                recycler_view_record.setLayoutManager(new LinearLayoutManager(context));
                recycler_view_record.setAdapter(adapter);

//                }, 100);

            }

            @Override
            public void onFailure(Call<Dash_Main_Model> call, Throwable t) {
                // dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerViewContainer.startShimmerAnimation();

    }

    @Override
    public void onPause() {
        binding.shimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void searchDialog() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout._search_dashboard);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

        Button search = (Button) dialog.findViewById(R.id.search);
        final EditText et_search = dialog.findViewById(R.id.et_search);
        et_search.setText(search_text);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
                dialog.show();
                search_text = et_search.getText().toString().trim();
                if (search_text.length() == 0) {
                    binding.recyclerViewRecord.setAdapter(new Added_Adapter(context, user_dashboard));
                } else {

                    List<Dashboard_Data_Model> user_dashboard_srch = new ArrayList<>();

                    for (Dashboard_Data_Model data_model : user_dashboard) {
                        String a = data_model.getProjectCodeName();
                        String b = data_model.getCreatedBy();
                        String c = data_model.getUpdatedBy();
                        String d = data_model.getCreatedDate();
                        String e = data_model.getUpdateddate();
                        if (a.toLowerCase().contains(search_text.toLowerCase()) ||
                                b.toLowerCase().contains(search_text.toLowerCase()) ||
                                c.toLowerCase().contains(search_text.toLowerCase()) ||
                                d.toLowerCase().contains(search_text.toLowerCase()) ||
                                e.toLowerCase().contains(search_text.toLowerCase())
                                ) {
                            user_dashboard_srch.add(data_model);
                        }
                    }

                    binding.recyclerViewRecord.setAdapter(new Added_Adapter(context, user_dashboard_srch));
                    if (user_dashboard_srch.size() == 0) {
                        txt_no_data.setVisibility(View.VISIBLE);
                    } else {
                        txt_no_data.setVisibility(View.GONE);
                    }
                }
                dialog.dismiss();


            }
        });
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                binding.recyclerViewRecord.setAdapter(new Added_Adapter(context, user_dashboard));
                search_text = "";
            }
        });

    }

    private void search() {

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
               /* final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
                dialog.show();*/
                //  showProgressDialog(getActivity());
                String search_text = et_search.getText().toString().trim().toLowerCase();
                if (search_text.length() == 0) {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    binding.recyclerViewRecord.setAdapter(new Added_Adapter(context, user_dashboard));
                    txt_no_data.setVisibility(View.GONE);


                } else {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.clear, 0);


                    List<Dashboard_Data_Model> user_dashboard_srch = new ArrayList<>();

                    for (Dashboard_Data_Model data_model : user_dashboard) {
                        String a = data_model.getProjectCodeName();
                        String b = data_model.getCreatedBy();
                        String c = data_model.getUpdatedBy();
                        String d = data_model.getCreatedDate();
                        String e = data_model.getUpdateddate();
                        if (a.toLowerCase().contains(search_text.toLowerCase()) ||
                                b.toLowerCase().contains(search_text.toLowerCase()) ||
                                c.toLowerCase().contains(search_text.toLowerCase()) ||
                                d.toLowerCase().contains(search_text.toLowerCase()) ||
                                e.toLowerCase().contains(search_text.toLowerCase())
                                ) {
                            user_dashboard_srch.add(data_model);
                        }
                    }
//Toast.makeText(getActivity(),"srch end",Toast.LENGTH_SHORT).show();
                    binding.recyclerViewRecord.setAdapter(new Added_Adapter(context, user_dashboard_srch));
                    //      Toast.makeText(getActivity(),"adapter bindined",Toast.LENGTH_SHORT).show();
                    if (user_dashboard_srch.size() == 0) {
                        txt_no_data.setVisibility(View.VISIBLE);
                    } else {
                        txt_no_data.setVisibility(View.GONE);
                    }


                }
                //  hideProgressDialog();
                // dialog.dismiss();
            }
        });
        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                /*to clear autocomplete*/
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                try {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (motionEvent.getRawX() >= (et_search.getRight() - et_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here
                            et_search.setText("");
                            return true;
                        }
                    }
                } catch (Exception e) {
                }


                return false;
            }
        });

    }

    private void showProgressDialog(Context context) {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Searching...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void hideProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
