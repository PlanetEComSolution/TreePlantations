package com.planet.treeplantations.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.planet.treeplantations.R;
import com.planet.treeplantations.databinding.MaintenceListItemBinding;
import com.planet.treeplantations.models.Year_Maintence_Responce_Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Year_Maintence_Adapter extends RecyclerView.Adapter<Year_Maintence_Adapter.MyViewHolder> {

    private List<Year_Maintence_Responce_Model> List;
    private LayoutInflater layoutInflater;
    private Context context;
    private String parts,part;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private MaintenceListItemBinding binding;

        public MyViewHolder(final MaintenceListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public Year_Maintence_Adapter(Context context, List<Year_Maintence_Responce_Model> postList) {
        this.List = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MaintenceListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.maintence_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setMaintence(List.get(position));
        holder.binding.serialNo.setText(String.valueOf(position + 1));
        setDate_with_Formet(holder);
        }

    @Override
    public int getItemCount() {
        if(List == null)
            return 0;
        return List.size();
    }

    private void setDate_with_Formet(MyViewHolder holder) {
            try{
                parts = holder.binding.getMaintence().getCreatedDate(); // escape .

                part = holder.binding.getMaintence().getUpdateddate(); // escape .

            }catch (Exception e){}
           SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
           SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
            try {
                if(part!=null){
                Date date1 = input.parse(part);
                holder.binding.updatedDate.setText(output.format(date1));
              }
              if(parts!=null) {
                  Date date = input.parse(parts);
                  holder.binding.createdDate.setText(output.format(date));
              }
            } catch (ParseException e) {
                e.printStackTrace();
            }


            }

}
