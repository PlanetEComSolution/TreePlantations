package com.planet.treeplantations.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.treeplantations.Activities.DashBoard_Details_Activity;
import com.planet.treeplantations.R;
import com.planet.treeplantations.databinding.ListItemDmBinding;
import com.planet.treeplantations.models.Dashboard_Data_Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Added_Adapter extends RecyclerView.Adapter<Added_Adapter.MyViewHolder> {

    private List<Dashboard_Data_Model> List;
    private LayoutInflater layoutInflater;
    private Context context;
    private String parts, part;

    public Added_Adapter(Context context, List<Dashboard_Data_Model> postList) {
        this.List = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemDmBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item_dm, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setModelss(List.get(position));
        holder.binding.serialNo.setText(String.valueOf(position + 1));
//change date formet
        setDate_with_Formet(holder);
//
        holder.binding.btnLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Intent intent = new Intent(context, DashBoard_Details_Activity.class);
                intent.putExtra("id", holder.binding.getModelss().getID());
                context.startActivity(intent);

                Toast.makeText(context, holder.binding.getModelss().getID(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setDate_with_Formet(MyViewHolder holder) {
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
        if (List == null)
            return 0;
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ListItemDmBinding binding;

        public MyViewHolder(final ListItemDmBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
