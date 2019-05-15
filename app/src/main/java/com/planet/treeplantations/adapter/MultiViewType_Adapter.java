package com.planet.treeplantations.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.planet.treeplantations.Activities.DashBoard_Details_Activity;
import com.planet.treeplantations.R;
import com.planet.treeplantations.databinding.AdminDashboardListItemBinding;
import com.planet.treeplantations.databinding.ListItemDmBinding;
import com.planet.treeplantations.models.Dashboard_Data_Model;
import com.planet.treeplantations.models.Dashboard_data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultiViewType_Adapter extends RecyclerView.Adapter {

    private static final int ITEM_TYPE_AllRecord = 1;
    private static final int ITEM_TYPE_State = 2;
    private static final int ITEM_TYPE_Search = 3;
    private List<Dashboard_Data_Model> RecordList;
    private List<Dashboard_data> stateList;

    private List<String> searchList = new ArrayList<>();

    private LayoutInflater layoutInflater;
    private Context context;
    private String parts, part;
    private List<Object> items = new ArrayList<>();



    public MultiViewType_Adapter(Context context,
                                 List<Dashboard_Data_Model> AllRecordList,
                                 List<Dashboard_data> statetList) {
        this.RecordList = AllRecordList;
        this.stateList = statetList;
        this.context = context;

        searchList.clear();
        searchList.add("");

        this.items.addAll(stateList);
        this.items.addAll(searchList);
        this.items.addAll(RecordList);

    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Dashboard_Data_Model) {
            return ITEM_TYPE_AllRecord;
        } else if (items.get(position) instanceof Dashboard_data) {
            return ITEM_TYPE_State;
        } else {
            return ITEM_TYPE_Search;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == ITEM_TYPE_AllRecord) {
            ListItemDmBinding binding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.list_item_dm, parent, false);
            return new MyViewHolder_AllRecord(binding);
        } else if (viewType == ITEM_TYPE_State) {
            AdminDashboardListItemBinding binding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.admin_dashboard_list_item, parent, false);
            return new MyViewHolderState(binding);

        } else {
            View itemView = layoutInflater
                    .inflate(R.layout.search, parent, false);
            return new MyViewHolderSearch(itemView);
        }
        // return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int listPosition) {

        Object item = items.get(listPosition);
        if (viewHolder instanceof MyViewHolder_AllRecord) {

            ((MyViewHolder_AllRecord) viewHolder).binding.setModelss((Dashboard_Data_Model) item);
            ((MyViewHolder_AllRecord) viewHolder).binding.serialNo.setText(String.valueOf((listPosition + 1) -( stateList.size()+searchList.size())));

            setDate_with_Formet(((MyViewHolder_AllRecord) viewHolder));

            ((MyViewHolder_AllRecord) viewHolder).binding.btnLis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = ((MyViewHolder_AllRecord) viewHolder).getAdapterPosition();
                    Intent intent = new Intent(context, DashBoard_Details_Activity.class);
                    intent.putExtra("id", ((MyViewHolder_AllRecord) viewHolder).binding.getModelss().getID());
                    context.startActivity(intent);
                    //   Toast.makeText(context, ((MyViewHolder_AllRecord) viewHolder).binding.getModelss().getID(), Toast.LENGTH_SHORT).show();
                }
            });


        } else if (viewHolder instanceof MyViewHolderState) {
            ((MyViewHolderState) viewHolder).binding.setItem((Dashboard_data) item);
        } else {


        }

    }

    private void setDate_with_Formet(MyViewHolder_AllRecord holder) {
        try {
            parts = holder.binding.getModelss().getCreatedDate(); // escape .
            part = holder.binding.getModelss().getUpdateddate(); // escape .

        } catch (Exception e) {
        }
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        try {
            if (part != null) {
                Date date1 = input.parse(part);
                holder.binding.updatedDate.setText(output.format(date1));
            }
            if (parts != null) {
                Date date = input.parse(parts);                 // parse input
                holder.binding.createdDate.setText(output.format(date));
            }// format output
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public class MyViewHolder_AllRecord extends RecyclerView.ViewHolder {

        private ListItemDmBinding binding;

        public MyViewHolder_AllRecord(final ListItemDmBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public class MyViewHolderState extends RecyclerView.ViewHolder {

        private AdminDashboardListItemBinding binding;

        public MyViewHolderState(final AdminDashboardListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public class MyViewHolderSearch extends RecyclerView.ViewHolder {

        //  TextView tv_file_name, tv_job_name, tv_dated, tv_status, tv_status_heading;
    //    EditText et_Search;
     //   TextView txt_no_data;

        public MyViewHolderSearch(View view) {
            super(view);
         //   et_Search = (EditText) view.findViewById(R.id.et_search);
           // txt_no_data = (TextView) view.findViewById(R.id.txt_no_data);
        }
    }
}
