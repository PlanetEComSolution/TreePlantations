package com.planet.treeplantations.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planet.treeplantations.Activities.AllTree_Details_Activity;
import com.planet.treeplantations.Activities.DashBoard_Details_Activity;
import com.planet.treeplantations.Activities.Project_Details_Activity;
import com.planet.treeplantations.R;
import com.planet.treeplantations.databinding.AllTreeListItemBinding;
import com.planet.treeplantations.models.All_Tree_Responce_Model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class All_Project_Adapter extends RecyclerView.Adapter<All_Project_Adapter.MyViewHolder> {

    private java.util.List<All_Tree_Responce_Model> List;
    private LayoutInflater layoutInflater;
    private Context context;
    private String parts,part;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private AllTreeListItemBinding binding;

        public MyViewHolder(final AllTreeListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public All_Project_Adapter(Context context, List<All_Tree_Responce_Model> postList) {
        this.List = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        AllTreeListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.all_tree_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setAllproject(List.get(position));
        holder.binding.serialNo.setText(String.valueOf(position + 1));
        setDate_with_Formet(holder);
        holder.binding.btnLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
               // Intent intent =new Intent(context, DashBoard_Details_Activity.class);
                Intent intent =new Intent(context,  AllTree_Details_Activity.class);
                intent.putExtra("id", holder.binding.getAllproject().getID());
                intent.putExtra("kml", holder.binding.getAllproject().getPolygonDetai());

                context.startActivity(intent);

         //   Toast.makeText(context,  holder.binding.getAllproject().getID() , Toast.LENGTH_SHORT).show();

            }
        });



    }
    @Override
    public int getItemCount() {
        if(List == null)
            return 0;
        return List.size();
    }

    private void setDate_with_Formet(MyViewHolder holder) {
        try{
         parts = holder.binding.getAllproject().getCreatedDate(); // escape .

         part = holder.binding.getAllproject().getUpdateddate(); // escape .

        }catch (Exception e){}
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        try {
            if(part!=null){
            Date date1 = input.parse(part);
            holder.binding.updatedDate.setText(output.format(date1));
            }
            if(parts!=null){
            Date date = input.parse(parts);
            holder.binding.createdDate.setText(output.format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        }


}