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
import com.planet.treeplantations.Activities.Project_Details_Activity;
import com.planet.treeplantations.R;
import com.planet.treeplantations.databinding.ProjectListItemBinding;
import com.planet.treeplantations.models.Project_Responce_Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Project_Adapter extends RecyclerView.Adapter<Project_Adapter.MyViewHolder> {

    private List<Project_Responce_Model> List;
    private LayoutInflater layoutInflater;
    private Context context;
    private String parts,part;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ProjectListItemBinding binding;
        public MyViewHolder(final ProjectListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public Project_Adapter(Context context,List<Project_Responce_Model> postList) {
        this.List = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ProjectListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.project_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setProject(List.get(position));
        holder.binding.serialNo.setText(String.valueOf(position+1));
        setDate_with_Formet(holder);
        holder.binding.btnLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                Intent intent =new Intent(context, Project_Details_Activity.class);
                intent.putExtra("id", holder.binding.getProject().getId());
                context.startActivity(intent);


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
            parts = holder.binding.getProject().getCreatedDate(); // escape .
            part = holder.binding.getProject().getUpdatedDate(); // escape .

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
