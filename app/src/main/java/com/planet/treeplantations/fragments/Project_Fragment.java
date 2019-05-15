package com.planet.treeplantations.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.adapter.Added_Adapter;
import com.planet.treeplantations.adapter.Project_Adapter;
import com.planet.treeplantations.models.Dashboard_Data_Model;
import com.planet.treeplantations.models.Project_Model;
import com.planet.treeplantations.models.Project_Responce_Model;
import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;
import com.planet.treeplantations.utils.Util;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;

public class Project_Fragment extends Fragment {
    String search_text = "";
    ImageView img_srch;
    EditText et_search;
    TextView txt_no_data;
    List<Project_Responce_Model> project_responce_models = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private Pref preg_s;
    private Dialog dialog;
    private ProgressDialog mProgressDialog;

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getActivity().setTitle("Project");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project_, container, false);
        context = getActivity();
      //  setHasOptionsMenu(true);
        preg_s = new Pref(context);
        if (isNetworkAvailable(getActivity())) {
            user_info_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        txt_no_data = view.findViewById(R.id.txt_no_data);
        txt_no_data.setVisibility(View.GONE);
        et_search = view.findViewById(R.id.et_search);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setCursorVisible(true);
            }
        });
        search();

        return view;

    }

    private void user_info_API() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        // Call<Project_Model> call = apiService.project("Admin");
        Call<Project_Model> call = apiService.project(preg_s.loadPreferences("userRole"));

        call.enqueue(new Callback<Project_Model>() {
            @Override
            public void onResponse(Call<Project_Model> call, Response<Project_Model> response) {
                String user_info = response.body().getStatus();
                dialog.dismiss();
                project_responce_models = response.body().getPrjctList();
                recyclerView.setAdapter(new Project_Adapter(context, project_responce_models));
            }

            @Override
            public void onFailure(Call<Project_Model> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            searchDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                    recyclerView.setAdapter(new Project_Adapter(context, project_responce_models));
                } else {

                    List<Project_Responce_Model> user_dashboard_srch = new ArrayList<>();

                    for (Project_Responce_Model data_model : project_responce_models) {
                        String a = data_model.getPOMOUNumber().toString();
                        String b = data_model.getProjectName();
                        String c = data_model.getRegionName();
                        String d = data_model.getStateName();
                        String e = data_model.getIsActive();
                        String f = data_model.getCreatedBy();
                        String g = Util.getDateWithMonth(data_model.getCreatedDate());
                        String h = Util.getDateWithMonth(data_model.getUpdatedDate());


                        if (a.toLowerCase().contains(search_text.toLowerCase()) ||
                                b.toLowerCase().contains(search_text.toLowerCase()) ||
                                c.toLowerCase().contains(search_text.toLowerCase()) ||
                                d.toLowerCase().contains(search_text.toLowerCase()) ||
                                e.toLowerCase().contains(search_text.toLowerCase()) ||
                                f.toLowerCase().contains(search_text.toLowerCase()) ||
                                g.toLowerCase().contains(search_text.toLowerCase()) ||
                                h.toLowerCase().contains(search_text.toLowerCase())

                                ) {
                            user_dashboard_srch.add(data_model);
                        }
                    }

                    recyclerView.setAdapter(new Project_Adapter(context, user_dashboard_srch));
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
                recyclerView.setAdapter(new Project_Adapter(context, project_responce_models));
                search_text = "";
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
                    txt_no_data.setVisibility(View.GONE);
                    recyclerView.setAdapter(new Project_Adapter(context, project_responce_models));
                    et_search.setCursorVisible(false);

                } else {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.clear, 0);


                    List<Project_Responce_Model> user_dashboard_srch = new ArrayList<>();

                    for (Project_Responce_Model data_model : project_responce_models) {
                        String a = data_model.getPOMOUNumber().toString();
                        String b = data_model.getProjectName();
                        String c = data_model.getRegionName();
                        String d = data_model.getStateName();
                        String e = data_model.getIsActive();
                        String f = data_model.getCreatedBy();
                        String g = Util.getDateWithMonth(data_model.getCreatedDate());
                        String h = Util.getDateWithMonth(data_model.getUpdatedDate());


                        if (a.toLowerCase().contains(search_text.toLowerCase()) ||
                                b.toLowerCase().contains(search_text.toLowerCase()) ||
                                c.toLowerCase().contains(search_text.toLowerCase()) ||
                                d.toLowerCase().contains(search_text.toLowerCase()) ||
                                e.toLowerCase().contains(search_text.toLowerCase()) ||
                                f.toLowerCase().contains(search_text.toLowerCase()) ||
                                g.toLowerCase().contains(search_text.toLowerCase()) ||
                                h.toLowerCase().contains(search_text.toLowerCase())

                                ) {
                            user_dashboard_srch.add(data_model);
                        }
                    }

                    recyclerView.setAdapter(new Project_Adapter(context, user_dashboard_srch));
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
}
