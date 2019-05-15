package com.planet.treeplantations.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.adapter.All_Project_Adapter;
import com.planet.treeplantations.adapter.Project_Adapter;
import com.planet.treeplantations.models.All_Project_Model;
import com.planet.treeplantations.models.All_Tree_Responce_Model;
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

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.planet.treeplantations.utils.Util.isNetworkAvailable;


public class All_Tree_Fragment extends Fragment {

    String search_text = "";
    ImageView img_srch;
    EditText et_search;
    TextView txt_no_data;
    List<All_Tree_Responce_Model> all_tree_responce_models = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private Dialog dialog;
    private String stateName;
    private String[] strArray;
    private TextView no_data;
    private ImageView close;
    private ArrayList<State_Responce_Model> state;
    private ArrayList<Range_Responce_Model> range;
    private ArrayList<Project_Code_Responce_Model> pcode;
    private ArrayList<Project_Responce_Model> project;
    private ArrayList<Region_Responce_Model> rname;
    private ArrayList<Division_Responce_Model> devision_arrary;
    private AutoCompleteTextView state_name, finyear, range_name, project_name,
            project_code, region_name, division_name;
    private String str_state_name = "", str_finyear = "",
            str_range_name = "", range_id = "",
            state_id = "", project_code_str = "",
            project_name_id = "",
            region_name_str = "",
            division_name_str = "",
            region_id = "",
            division_id = "", p_id = "";
    private Pref preg_s;

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getActivity().setTitle("All Tree Planted");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__tree_, container, false);
        context = getActivity();
       // setHasOptionsMenu(true);
        state = new ArrayList<>();
        range = new ArrayList<>();
        pcode = new ArrayList<>();
        rname = new ArrayList<>();
        project = new ArrayList<>();
        devision_arrary = new ArrayList<>();
        preg_s = new Pref(context);
        if (isNetworkAvailable(getActivity())) {
            user_info_API();
        } else {
            Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
        }
        close = (ImageView) view.findViewById(R.id.close);
        no_data = (TextView) view.findViewById(R.id.no_data);
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

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable(getActivity())) {
                    no_data.setVisibility(View.GONE);
                    close.setVisibility(View.GONE);
                    user_info_API();

                } else {
                    Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

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


        dialog.show();
        Button search = (Button) dialog.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                str_state_name = state_name.getText().toString();
                str_finyear = finyear.getText().toString();
                str_range_name = range_name.getText().toString();
                project_code_str = project_code.getText().toString();
                region_name_str = region_name.getText().toString();
                division_name_str = division_name.getText().toString();
//                String  str_range_name=range_name.getText().toString();
//                String  str_range_name=range_name.getText().toString();
                if (str_state_name.equalsIgnoreCase("") && str_finyear.equalsIgnoreCase("") && str_range_name.equalsIgnoreCase("")
                        && project_code_str.equalsIgnoreCase("") && region_name_str.equalsIgnoreCase("") && division_name_str.equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Please Select Any One.", Toast.LENGTH_SHORT).show();
                } else {
                    if (isNetworkAvailable(getActivity())) {
                        user_info_API();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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

                final ArrayAdapter<State_Responce_Model> adapter1 = new ArrayAdapter(getActivity(), R.layout.simple_dropdown_item, pcode);
                project_code.setAdapter(adapter1);
                project_code.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        project_code.setFocusable(false);
                        project_code.setFocusableInTouchMode(false);

                        if (!project_code.getText().toString().equals(""))
                            adapter1.getFilter().filter(null);
                        project_code.showDropDown();
                        return false;
                    }

                });
                project_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Project_Code_Responce_Model idget = (Project_Code_Responce_Model) pcode.get(i);
                        p_id = idget.getID();
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

    private void project_Name_API() {

        final AlertDialog dialog1 = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog1.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);


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

                final ArrayAdapter<Project_Responce_Model> adapter1 = new ArrayAdapter(getActivity(), R.layout.simple_dropdown_item, project);
                project_name.setAdapter(adapter1);
                project_name.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        project_name.setFocusable(false);
                        project_name.setFocusableInTouchMode(false);

                        if (!project_name.getText().toString().equals(""))
                            adapter1.getFilter().filter(null);
                        project_name.showDropDown();
                        return false;
                    }

                });
                project_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Project_Responce_Model idget = (Project_Responce_Model) project.get(i);
                        project_name_id = idget.getId();
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


    private void user_info_API() {
        all_tree_responce_models.clear();

        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).build();
        dialog.show();
        API_Interface apiService = API_Client.getClient().create(API_Interface.class);

        String url =
                API_Client.BASE_URL + "TreePlantation_AllTreePlanted?" +

                        "utype=" + preg_s.loadPreferences("userRole") +
                        "&lname=" + preg_s.loadPreferences("loginName") +
                        "&year=" + str_finyear +
                        "&stateid=" + state_id +
                        "&prjctcodeid=" + p_id +
                        "&prjctid=" + project_name_id +
                        "&divid=" + division_id +
                        "&rangeid=" + range_id +
                        "&regionid=" + region_id;

        //  Toast.makeText(getActivity(), url, Toast.LENGTH_LONG).show();

        Log.e("URL---->", String.valueOf(url));


        Call<All_Project_Model> call =
                apiService.allTree(preg_s.loadPreferences("userRole"),
                        preg_s.loadPreferences("loginName"),
                        str_finyear,
                        state_id,
                        p_id,
                        project_name_id,
                        division_id,
                        range_id,
                        region_id);

        str_finyear = "";
        state_id = "";
        p_id = "";
        project_name_id = "";
        division_id = "";
        range_id = "";
        region_id = "";


        call.enqueue(new Callback<All_Project_Model>() {

            @Override
            public void onResponse(Call<All_Project_Model> call, Response<All_Project_Model> response) {
                // String user_info = response.body().getStatus();
                dialog.dismiss();
                all_tree_responce_models = response.body().getData();
                if (all_tree_responce_models.size() > 0) {
                    recyclerView.setAdapter(new All_Project_Adapter(context, all_tree_responce_models));
                } else {
                    no_data.setVisibility(View.VISIBLE);
                    close.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new All_Project_Adapter(context, all_tree_responce_models));
                }
            }


            @Override
            public void onFailure(Call<All_Project_Model> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
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
                    txt_no_data.setVisibility(View.GONE);
                    et_search.setCursorVisible(false);
                    recyclerView.setAdapter(new All_Project_Adapter(context, all_tree_responce_models));

                } else {
                    et_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.clear, 0);


                    List<All_Tree_Responce_Model> user_dashboard_srch = new ArrayList<>();

                    for (All_Tree_Responce_Model data_model : all_tree_responce_models) {

                        String a = data_model.getPOMOUNumber();
                        String b = data_model.getProjectName();
                        String c = data_model.getPolygonDetai();
                      //  String d = data_model.getCreatedDate();
                        String e = data_model.getUpdatedBy();
                        String f = data_model.getIsRequested();
                        if (f.equalsIgnoreCase("true")) {
                            f = "Yes";
                        } else {
                            f = "No";
                        }
                        String g = Util.getDateWithMonth(data_model.getCreatedDate());
                        String h = Util.getDateWithMonth(data_model.getUpdateddate());


                        if (a.toLowerCase().contains(search_text.toLowerCase()) ||
                                b.toLowerCase().contains(search_text.toLowerCase()) ||
                                c.toLowerCase().contains(search_text.toLowerCase()) ||
                              //  d.toLowerCase().contains(search_text.toLowerCase()) ||
                                e.toLowerCase().contains(search_text.toLowerCase()) ||
                                f.toLowerCase().contains(search_text.toLowerCase()) ||
                                g.toLowerCase().contains(search_text.toLowerCase()) ||
                                h.toLowerCase().contains(search_text.toLowerCase())

                                ) {
                            user_dashboard_srch.add(data_model);
                        }
                    }


                    recyclerView.setAdapter(new All_Project_Adapter(context, user_dashboard_srch));


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


