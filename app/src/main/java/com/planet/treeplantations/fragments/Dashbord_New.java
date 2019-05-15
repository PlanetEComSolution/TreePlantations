package com.planet.treeplantations.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.adapter.Added_Adapter;
import com.planet.treeplantations.adapter.FilteredArrayAdapter;
import com.planet.treeplantations.adapter.MultiViewType_Adapter;
import com.planet.treeplantations.adapter.State_Adapter;
import com.planet.treeplantations.adapter.filteredAdapter;
import com.planet.treeplantations.models.Dash_Main_Model;
import com.planet.treeplantations.models.Dash_count_Model;
import com.planet.treeplantations.models.Dashboard_Data_Model;
import com.planet.treeplantations.models.Dashboard_data;
import com.planet.treeplantations.models.Devision_Name_Model;
import com.planet.treeplantations.models.Division_Responce_Model;
import com.planet.treeplantations.models.Project_Code_Model;
import com.planet.treeplantations.models.Project_Code_Responce_Model;
import com.planet.treeplantations.models.Project_Model;
import com.planet.treeplantations.models.Project_Responce_Model;
import com.planet.treeplantations.models.Range_Model;
import com.planet.treeplantations.models.Range_Responce_Model;
import com.planet.treeplantations.models.Region_Model;
import com.planet.treeplantations.models.Region_Responce_Model;
import com.planet.treeplantations.models.State_Model;
import com.planet.treeplantations.models.State_Responce_Model;
import com.planet.treeplantations.rest.API_Client;
import com.planet.treeplantations.rest.API_Interface;
import com.planet.treeplantations.utils.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;


public class Dashbord_New extends Fragment {
    List<Dashboard_Data_Model> user_dashboard = new ArrayList<>();
    List<Dashboard_data> user_state = new ArrayList<>();
    String search_text = "";
    // ImageView img_srch;
    EditText et_search;
    LinearLayout txt_no_data;
    ImageView imgvw_cross;
    ///  RecyclerView recycler_view_record;

    RecyclerView recycler_view;
    TextView all_audit, total_project, total_state, total_user;
    ArrayList<Project_Responce_Model> project_Temp = new ArrayList<>();
    ArrayList<Project_Code_Responce_Model> pcodeTemp = new ArrayList<>();
    MenuItem search, close;
    private ArrayList<Dashboard_data> list;
    private ArrayList<Dashboard_Data_Model> dm_list;
    private Pref preg_s;
    //  private FragmentDashboardFragmentBinding binding;
    private Context context;
    private Dialog dialog;
    private ProgressDialog mProgressDialog;
    //private Added_Adapter_WithoutBinding adapter;
    //  private State_Adapter_WithoutBinding state_adapter;
    private Added_Adapter adapter;
    private State_Adapter state_adapter;
    private MultiViewType_Adapter multiViewType_adapter;
    private AutoCompleteTextView state_name, finyear, range_name, project_name,
            project_code, region_name, division_name;
    private String str_state_name = "", str_finyear = "",
            str_range_name = "", range_id = "",
            state_id = "", project_code_str = "",
            project_name_id = "",
            project_name_str = "",
            region_name_str = "",
            division_name_str = "",
            region_id = "",
            division_id = "", p_id = "";
    private ArrayList<State_Responce_Model> state = new ArrayList<>();
    private ArrayList<Range_Responce_Model> range = new ArrayList<>();
    private ArrayList<Project_Code_Responce_Model> pcode = new ArrayList<>();
    private ArrayList<Project_Responce_Model> project = new ArrayList<>();
    private ArrayList<Region_Responce_Model> rname = new ArrayList<>();
    private ArrayList<Division_Responce_Model> devision_arrary = new ArrayList<>();
    private String stateName;
    private String[] strArray;

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getActivity().setTitle("Dashboard");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        search = menu.findItem(R.id.action_search);
        close = menu.findItem(R.id.action_close);
        close.setVisible(false);
        super.onPrepareOptionsMenu(menu);
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
            searchDialog_new();
            return true;
        } else if (id == R.id.action_close) {
            refreshData();

           /* if (isNetworkAvailable(getActivity())) {
                user_info_API();
            } else {
                Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
            }*/
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_dashboard_new, container, false);

        context = getActivity();
        preg_s = new Pref(context);
        setHasOptionsMenu(true);
        all_audit = view.findViewById(R.id.all_audit);
        total_project = view.findViewById(R.id.total_project);
        total_state = view.findViewById(R.id.total_state);
        total_user = view.findViewById(R.id.total_user);


        //     binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //   binding.recyclerView.setNestedScrollingEnabled(false);
        //    binding.recyclerView.setHasFixedSize(true);
        //    binding.recyclerViewRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
        //     binding.recyclerViewRecord.setNestedScrollingEnabled(false);
        //     binding.recyclerView.addItemDecoration(new EqualSpacingItemDecoration(1, EqualSpacingItemDecoration.VERTICAL));
        //     binding.recyclerViewRecord.addItemDecoration(new EqualSpacingItemDecoration(1, EqualSpacingItemDecoration.VERTICAL));
//
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
        imgvw_cross = view.findViewById(R.id.imgvw_cross);
        txt_no_data.setVisibility(View.GONE);
        et_search = view.findViewById(R.id.et_search);
        //  recycler_view_record = view.findViewById(R.id.recycler_view_record);
        recycler_view = view.findViewById(R.id.recycler_view);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setCursorVisible(true);
            }
        });
        imgvw_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData();

            }
        });
        search();

        return view;
    }

    private void user_info_API() {

        try {
            search.setVisible(true);
            close.setVisible(false);
        } catch (Exception e) {
            e.getCause();
        }
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();
        user_dashboard.clear();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);
        //Call<Dash_Main_Model> call = apiService.get_user("Bihar","1","Admin","Admin");

        Call<Dash_Main_Model> call = apiService.get_user(preg_s.loadPreferences("stateName"), preg_s.loadPreferences("pid"), preg_s.loadPreferences("userRole"), preg_s.loadPreferences("loginName"));
        call.enqueue(new Callback<Dash_Main_Model>() {

            @Override
            public void onResponse(Call<Dash_Main_Model> call, Response<Dash_Main_Model> response) {
                dialog.dismiss();

                // String user_info = response.body().getStatus();
                Dash_count_Model user_ = response.body().getOTotalCount();
                user_state = response.body().getData();
                user_dashboard = response.body().getDashdata();

                Dash_count_Model ggg = new Dash_count_Model();
                all_audit.setText("All Audit Records:  " + user_.getTtlAudit());
                total_project.setText("Total Project:      " + user_.getTtlPrjct());
                total_state.setText("Total State User:    " + user_.getTtlStateUser());
                total_user.setText("Total User:           " + user_.getTtlUser());


                if (user_dashboard.size() > 0) {
                    txt_no_data.setVisibility(View.GONE);
                } else {
                    txt_no_data.setVisibility(View.VISIBLE);
                }
                multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard, user_state);
                recycler_view.setHasFixedSize(true);
                recycler_view.setLayoutManager(new LinearLayoutManager(context));
                recycler_view.setAdapter(multiViewType_adapter);


            }

            @Override
            public void onFailure(Call<Dash_Main_Model> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                txt_no_data.setVisibility(View.VISIBLE);

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        //  binding.shimmerViewContainer.startShimmerAnimation();

    }

    @Override
    public void onPause() {
        //  binding.shimmerViewContainer.stopShimmerAnimation();
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
                    multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard, user_state);
                    recycler_view.setAdapter(multiViewType_adapter);

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


                    multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard_srch, user_state);
                    recycler_view.setAdapter(multiViewType_adapter);

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
                multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard, user_state);
                recycler_view.setAdapter(multiViewType_adapter);
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

                    multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard, user_state);
                    recycler_view.setAdapter(multiViewType_adapter);
                    txt_no_data.setVisibility(View.GONE);
                    et_search.setCursorVisible(false);

                } else {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.clear, 0);


                    List<Dashboard_Data_Model> user_dashboard_srch = new ArrayList<>();

                    for (Dashboard_Data_Model data_model : user_dashboard) {
                        // String a = data_model.getProjectCodeName();
                        //  String b = data_model.getCreatedBy();
                        //  String c = data_model.getUpdatedBy();
                        //  String d = data_model.getCreatedDate();
                        //  String e = data_model.getUpdateddate();

                        /**/

                        String a = data_model.getPO_MOUNumber();
                        String b = data_model.getProjectName();
                        String c = data_model.getPolygonDetai();
                        String d = getDateWithMonth(data_model.getCreatedDate());
                        String e = data_model.getUpdatedBy();
                        String f = data_model.getIsRequested();
                        if (f.equalsIgnoreCase("true")) {
                            f = "Yes";
                        } else {
                            f = "No";
                        }
                        String g = getDateWithMonth(data_model.getUpdateddate());


                        if (a.toLowerCase().contains(search_text.toLowerCase()) ||
                                b.toLowerCase().contains(search_text.toLowerCase()) ||
                                c.toLowerCase().contains(search_text.toLowerCase()) ||
                                d.toLowerCase().contains(search_text.toLowerCase()) ||
                                e.toLowerCase().contains(search_text.toLowerCase()) ||
                                f.toLowerCase().contains(search_text.toLowerCase()) ||
                                g.toLowerCase().contains(search_text.toLowerCase())
                        ) {
                            user_dashboard_srch.add(data_model);
                        }
                    }
                    multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard_srch, user_state);
                    recycler_view.setAdapter(multiViewType_adapter);

                    if (user_dashboard_srch.size() == 0) {
                        txt_no_data.setVisibility(View.VISIBLE);
                    } else {
                        txt_no_data.setVisibility(View.GONE);
                    }

                    recycler_view.getLayoutManager().scrollToPosition(user_state.size() + 1);
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

    private String getDateWithMonth(String date) {
        String outputDate = "";
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        try {
            if (date != null) {
                Date date1 = input.parse(date);
                outputDate = output.format(date1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    private void searchDialog_new() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout._search_);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        state_name = (AutoCompleteTextView) dialog.findViewById(R.id.state_name);
        division_name = (AutoCompleteTextView) dialog.findViewById(R.id.division_name);
        region_name = (AutoCompleteTextView) dialog.findViewById(R.id.region_name);
        project_code = (AutoCompleteTextView) dialog.findViewById(R.id.project_code);
        range_name = (AutoCompleteTextView) dialog.findViewById(R.id.range_name);
        project_name = (AutoCompleteTextView) dialog.findViewById(R.id.project_name);
        state_name = (AutoCompleteTextView) dialog.findViewById(R.id.state_name);
        finyear = (AutoCompleteTextView) dialog.findViewById(R.id.fin_yer);


        fin_Year();

        if (isNetworkAvailable(getActivity())) {
            projectCode_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        if (isNetworkAvailable(getActivity())) {
            project_Name_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        if (isNetworkAvailable(getActivity())) {
            regionName_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }



    /*    if (isNetworkAvailable(getActivity())) {
            range_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }

        if (isNetworkAvailable(getActivity())) {
            state_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }

        if (isNetworkAvailable(getActivity())) {
            regionName_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        if (isNetworkAvailable(getActivity())) {
            devision_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }*/

        if (!dialog.isShowing()) {
            dialog.show();
        }
        final Button btn_search = (Button) dialog.findViewById(R.id.search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
              //  close.setVisible(true);
              //  search.setVisible(false);
                final AlertDialog progressDialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
                progressDialog.show();

                str_state_name = state_name.getText().toString();
                str_finyear = finyear.getText().toString();
                str_range_name = range_name.getText().toString();
                project_code_str = project_code.getText().toString();
                region_name_str = region_name.getText().toString();
                division_name_str = division_name.getText().toString();
                project_name_str = project_name.getText().toString();

                if (str_state_name.equalsIgnoreCase("") && str_finyear.equalsIgnoreCase("") && str_range_name.equalsIgnoreCase("")
                        && project_code_str.equalsIgnoreCase("") && region_name_str.equalsIgnoreCase("") && division_name_str.equalsIgnoreCase("") && project_name_str.equalsIgnoreCase("")) {
                    //   Toast.makeText(getActivity(), "Please Select Any One.", Toast.LENGTH_SHORT).show();

                    multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard, user_state);
                    recycler_view.setAdapter(multiViewType_adapter);

                    if (user_dashboard.size() == 0) {
                        txt_no_data.setVisibility(View.VISIBLE);
                    } else {
                        txt_no_data.setVisibility(View.GONE);
                    }

                } else {
                    if (user_state.size() > 0) {
                        List<Dashboard_Data_Model> user_dashboard_srch = new ArrayList<>();

                        for (Dashboard_Data_Model data_model : user_dashboard) {
                            /**/
                            String FinancialYear = data_model.getFinancialYear();
                            String ProjectCodeNam = data_model.getProjectCodeName();
                            String ProjectName = data_model.getProjectName();
                            String RegionName = data_model.getRegionName();
                            String StateName = data_model.getStateName();
                            String DivisionName = data_model.getDivisionName();
                            String RangeName = data_model.getRangeName();

                            if (str_finyear.equals("")) FinancialYear = "";
                            if (project_code_str.equals("")) ProjectCodeNam = "";
                            if (project_name_str.equals("")) ProjectName = "";
                            if (region_name_str.equals("")) RegionName = "";
                            if (str_state_name.equals("")) StateName = "";
                            if (division_name_str.equals("")) DivisionName = "";
                            if (str_range_name.equals("")) RangeName = "";


                            if (FinancialYear.toLowerCase().equalsIgnoreCase(str_finyear.toLowerCase()) &&
                                    ProjectCodeNam.toLowerCase().equalsIgnoreCase(project_code_str.toLowerCase()) &&
                                    ProjectName.toLowerCase().equalsIgnoreCase(project_name_str.toLowerCase()) &&
                                    RegionName.toLowerCase().equalsIgnoreCase(region_name_str.toLowerCase()) &&
                                    StateName.toLowerCase().equalsIgnoreCase(str_state_name.toLowerCase()) &&
                                    DivisionName.toLowerCase().equalsIgnoreCase(division_name_str.toLowerCase()) &&
                                    RangeName.toLowerCase().equalsIgnoreCase(str_range_name.toLowerCase())

                            ) {
                                user_dashboard_srch.add(data_model);
                            }
                        }


                        multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard_srch, user_state);
                        recycler_view.setAdapter(multiViewType_adapter);

                        if (user_dashboard_srch.size() == 0) {
                            txt_no_data.setVisibility(View.VISIBLE);
                        } else {
                            txt_no_data.setVisibility(View.GONE);
                        }

                        recycler_view.getLayoutManager().scrollToPosition(user_state.size() + 1);

                    } else {
                        Toast.makeText(getActivity(), "No data found.", Toast.LENGTH_SHORT).show();
                    }


                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


            }
        });
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                close.setVisible(false);
                search.setVisible(true);


               /* if (isNetworkAvailable(getActivity())) {
                    user_info_API();
                } else {
                    Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
                }*/
               refreshData();

            }
        });

    }

    private void fin_Year() {

        String[] voucher = {"2014-2015", "2015-2016", "2016-2017"};
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.simple_dropdown_item, voucher);
        finyear.setAdapter(adapter);
        finyear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finyear.setFocusable(false);
                finyear.setFocusableInTouchMode(false);

                if (!finyear.getText().toString().equals(""))
                    adapter.getFilter().filter(null);
                finyear.showDropDown();
                return false;
            }

        });

    }

    private void projectCode_API() {
        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);


        //Call<Project_Code_Model> call = apiService.projectCode(preg_s.loadPreferences("loginName"), preg_s.loadPreferences("userRole"));
        Call<Project_Code_Model> call = apiService.projectCode(preg_s.loadPreferences("userRole"), preg_s.loadPreferences("loginName"));
        pcode.clear();
        call.enqueue(new Callback<Project_Code_Model>() {

            @Override
            public void onResponse(Call<Project_Code_Model> call, Response<Project_Code_Model> response) {
                dialog1.dismiss();
                List<Project_Code_Responce_Model> project_code_responce_models = response.body().getData();


                for (int i = 0; i < project_code_responce_models.size(); i++) {
                    String pcodename = project_code_responce_models.get(i).getProjectCodeName();
                    String id = project_code_responce_models.get(i).getID();
                    Project_Code_Responce_Model range_responce_model = new Project_Code_Responce_Model(pcodename, id);
                    pcode.add(range_responce_model);
                    // strArray = new String[] {stateName};

                }

                pcodeTemp = new ArrayList<>(pcode);
                //  FilteredArrayAdapter_1
                final filteredAdapter adapter1 = new filteredAdapter(getActivity(), R.layout.simple_dropdown_item, pcode);
                project_code.setAdapter(adapter1);
                project_code.setThreshold(1);

       /*         project_code.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        project_code.setFocusable(false);
                        project_code.setFocusableInTouchMode(false);

                        if (!project_code.getText().toString().equals(""))
                            adapter1.getFilter().filter(null);
                        project_code.showDropDown();
                        return false;
                    }

                });*/


                project_code.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        project_code.showDropDown();
                        /*to clear autocomplete*/
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;
                        try {
                            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                if (motionEvent.getRawX() >= (project_code.getRight() - project_code.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                    // your action here
                                    project_code.setText("");
                                    return true;
                                }
                            }
                        } catch (Exception e) {
                        }
                        /**/

                        return false;
                    }
                });


                project_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Project_Code_Responce_Model idget = (Project_Code_Responce_Model) pcode.get(i);
                        p_id = idget.getID();
                        Util.hideKeyboard(getActivity());
                    }
                });

                project_code.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().trim().length() == 0) {
                            project_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0);
                            pcode = new ArrayList<>(pcodeTemp);
                            final filteredAdapter adapter1 = new filteredAdapter(getActivity(), R.layout.simple_dropdown_item, pcode);
                            project_code.setAdapter(adapter1);
                            project_code.showDropDown();

                        } else {
                            project_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.clear, 0);
                            if (pcode.size() == 0) {
                                project_code.setAdapter(null);
                            }


                        }
                    }
                });


            }

            @Override
            public void onFailure(Call<Project_Code_Model> call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void project_Name_API() {

        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);

        project_name.setThreshold(1);
        // Call<Range_Model> call = apiService.range(preg_s.loadPreferences("userRole"),preg_s.loadPreferences("userRole"));


        Call<Project_Model> call = apiService.project(preg_s.loadPreferences("userRole"));
        //  range.clear();
        call.enqueue(new Callback<Project_Model>() {

            @Override
            public void onResponse(Call<Project_Model> call, Response<Project_Model> response) {
                dialog1.dismiss();
                List<Project_Responce_Model> range_models = response.body().getPrjctList();


                for (int i = 0; i < range_models.size(); i++) {

                    String range_ = range_models.get(i).getProjectName();
                    String id = range_models.get(i).getId();
                    Project_Responce_Model range_responce_model = new Project_Responce_Model(range_, id);
                    project.add(range_responce_model);
                    // strArray = new String[] {stateName};

                }
                project_Temp = new ArrayList<>(project);

                final FilteredArrayAdapter adapter1 = new FilteredArrayAdapter(getActivity(), R.layout.simple_dropdown_item, project);
                project_name.setAdapter(adapter1);

                project_name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        project_name.showDropDown();
                        /*to clear autocomplete*/
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;
                        try {
                            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                if (motionEvent.getRawX() >= (project_name.getRight() - project_name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                    // your action here
                                    project_name.setText("");
                                    return true;
                                }
                            }
                        } catch (Exception e) {
                        }
                        /**/

                        return false;
                    }
                });

                project_name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {


                        if (s.toString().trim().length() == 0) {

                            project_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0);
                            project = new ArrayList<>(project_Temp);
                            final FilteredArrayAdapter adapter1 = new FilteredArrayAdapter(getActivity(), R.layout.simple_dropdown_item, project);
                            project_name.setAdapter(adapter1);
                            project_name.showDropDown();


                        } else {
                            project_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.clear, 0);
                            if (project.size() == 0) {
                                project_name.setAdapter(null);
                            }


                        }
                    }
                });


                project_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Project_Responce_Model idget = (Project_Responce_Model) project.get(i);
                        project_name_id = idget.getId();
                        Util.hideKeyboard(getActivity());
                    }
                });

            }

            @Override
            public void onFailure(Call<Project_Model> call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void state_API() {
        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);

        Call<State_Model> call = apiService.state(preg_s.loadPreferences("userRole"), region_id);
        state.clear();
        call.enqueue(new Callback<State_Model>() {

            @Override
            public void onResponse(Call<State_Model> call, Response<State_Model> response) {
                dialog1.dismiss();
                List<State_Responce_Model> state_responce_models = response.body().getData();


                strArray = new String[state_responce_models.size()];
                for (int i = 0; i < state_responce_models.size(); i++) {

                    stateName = state_responce_models.get(i).getStateName();
                    String id = state_responce_models.get(i).getID();
                    State_Responce_Model state_responce_model = new State_Responce_Model(stateName, id);
                    state.add(state_responce_model);
                    // strArray = new String[] {stateName};
                    strArray[i] = stateName;
                }


                final ArrayAdapter<State_Responce_Model> adapter = new ArrayAdapter<State_Responce_Model>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, state);
                state_name.setAdapter(adapter);
//
//                final ArrayAdapter<State_Responce_Model> adapter1 = new ArrayAdapter(getActivity(),R.layout.simple_dropdown_item, state);
//                state_name.setAdapter(adapter1);
                state_name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        state_name.setFocusable(false);
                        state_name.setFocusableInTouchMode(false);

                        if (!state_name.getText().toString().equals(""))
                            adapter.getFilter().filter(null);
                        state_name.showDropDown();
                        return false;
                    }

                });
                state_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        division_name.setText("");
                        range_name.setText("");
                        range_name.setAdapter(null);


                        State_Responce_Model idget = (State_Responce_Model) state.get(i);
                        state_id = idget.getID();
                        devision_API();
                    }
                });

            }

            @Override
            public void onFailure(Call<State_Model> call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void devision_API() {

        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);


        // Call<Devision_Name_Model> call = apiService.devision(preg_s.loadPreferences("userRole"),preg_s.loadPreferences("sid"));
        Call<Devision_Name_Model> call = apiService.devision(preg_s.loadPreferences("userRole"), state_id);
        devision_arrary.clear();
        call.enqueue(new Callback<Devision_Name_Model>() {

            @Override
            public void onResponse(Call<Devision_Name_Model> call, Response<Devision_Name_Model> response) {
                dialog1.dismiss();
                List<Division_Responce_Model> division_responce_models = response.body().getData();


                for (int i = 0; i < division_responce_models.size(); i++) {
                    String regionName = division_responce_models.get(i).getDivisionName();
                    String id = division_responce_models.get(i).getID();
                    Division_Responce_Model region = new Division_Responce_Model(regionName, id);
                    devision_arrary.add(region);
                    // strArray = new String[] {stateName};

                }

                final ArrayAdapter<State_Responce_Model> adapter1 = new ArrayAdapter(getActivity(), R.layout.simple_dropdown_item, devision_arrary);
                division_name.setAdapter(adapter1);
                division_name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        division_name.setFocusable(false);
                        division_name.setFocusableInTouchMode(false);

                        if (!division_name.getText().toString().equals(""))
                            adapter1.getFilter().filter(null);
                        division_name.showDropDown();
                        return false;
                    }

                });
                division_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        range_name.setText("");
                        Division_Responce_Model idget = (Division_Responce_Model) devision_arrary.get(i);
                        division_id = idget.getID();
                        range_API();
                    }
                });
            }

            @Override
            public void onFailure(Call<Devision_Name_Model> call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void range_API() {

        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);


        // Call<Range_Model> call = apiService.range(preg_s.loadPreferences("userRole"),preg_s.loadPreferences("userRole"));


        Call<Range_Model> call = apiService.range(preg_s.loadPreferences("userRole"), division_id);
        range.clear();
        call.enqueue(new Callback<Range_Model>() {

            @Override
            public void onResponse(Call<Range_Model> call, Response<Range_Model> response) {
                dialog1.dismiss();
                List<Range_Responce_Model> range_models = response.body().getData();


                for (int i = 0; i < range_models.size(); i++) {
                    String range_ = range_models.get(i).getRangeName();
                    String id = range_models.get(i).getID();
                    Range_Responce_Model range_responce_model = new Range_Responce_Model(range_, id);
                    range.add(range_responce_model);
                    // strArray = new String[] {stateName};

                }

                final ArrayAdapter<State_Responce_Model> adapter1 = new ArrayAdapter(getActivity(), R.layout.simple_dropdown_item, range);
                range_name.setAdapter(adapter1);
                range_name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        range_name.setFocusable(false);
                        range_name.setFocusableInTouchMode(false);

                        if (!range_name.getText().toString().equals(""))
                            adapter1.getFilter().filter(null);
                        range_name.showDropDown();
                        return false;
                    }

                });
                range_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Range_Responce_Model idget = (Range_Responce_Model) range.get(i);
                        range_id = idget.getID();

                    }
                });

            }

            @Override
            public void onFailure(Call<Range_Model> call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void regionName_API() {

        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);


        //Call<Region_Model> call = apiService.region(preg_s.loadPreferences("userRole"));
        Call<Region_Model> call = apiService.region(preg_s.loadPreferences("userRole"));
        rname.clear();
        call.enqueue(new Callback<Region_Model>() {

            @Override
            public void onResponse(Call<Region_Model> call, Response<Region_Model> response) {
                dialog1.dismiss();
                List<Region_Responce_Model> region_responce_models = response.body().getData();


                for (int i = 0; i < region_responce_models.size(); i++) {
                    String regionName = region_responce_models.get(i).getRegionName();
                    String id = region_responce_models.get(i).getID();
                    Region_Responce_Model region = new Region_Responce_Model(regionName, id);
                    rname.add(region);
                    // strArray = new String[] {stateName};

                }

                final ArrayAdapter<State_Responce_Model> adapter1 = new ArrayAdapter(getActivity(), R.layout.simple_dropdown_item, rname);
                region_name.setAdapter(adapter1);
                region_name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        region_name.setFocusable(false);
                        region_name.setFocusableInTouchMode(false);

                        if (!region_name.getText().toString().equals(""))
                            adapter1.getFilter().filter(null);
                        region_name.showDropDown();
                        return false;
                    }

                });
                region_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        state_name.setText("");
                        division_name.setText("");
                        range_name.setText("");

                        division_name.setAdapter(null);
                        range_name.setAdapter(null);

                        Region_Responce_Model idget = (Region_Responce_Model) rname.get(i);
                        region_id = idget.getID();
                        state_API();
                    }
                });
            }

            @Override
            public void onFailure(Call<Region_Model> call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void refreshData(){
        multiViewType_adapter = new MultiViewType_Adapter(context, user_dashboard, user_state);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(context));
        recycler_view.setAdapter(multiViewType_adapter);

        if (user_dashboard.size() == 0) {
            txt_no_data.setVisibility(View.VISIBLE);
        } else {
            txt_no_data.setVisibility(View.GONE);
        }
    }
}
