package com.planet.treeplantations.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.planet.treeplantations.R;
import com.planet.treeplantations.databinding.PoMoListItemBinding;

import com.planet.treeplantations.models.PO_MO_Responce_Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PO_MO_Adapter extends RecyclerView.Adapter<PO_MO_Adapter.MyViewHolder> {

private List<PO_MO_Responce_Model> List;
private LayoutInflater layoutInflater;
private Context context;
private String part1;
private String partc,parts,part;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private PoMoListItemBinding binding;

    public MyViewHolder(final PoMoListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.binding = itemBinding;
    }
}

    public PO_MO_Adapter(Context context, List<PO_MO_Responce_Model> postList) {
        this.List = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PoMoListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.po_mo_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setPomo(List.get(position));
        holder.binding.serialNo.setText(String.valueOf(position + 1));
        setDate_with_Formet(holder);
        int pos = holder.getAdapterPosition();
    }
    @Override
    public int getItemCount() {
        if(List == null)
            return 0;
        return List.size();
    }

    private void setDate_with_Formet(MyViewHolder holder) {
       try{
             parts = holder.binding.getPomo().getCreatedDate(); // escape .

              part = holder.binding.getPomo().getUpdateddate();

            }catch (Exception e){}
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
            try {
               if(part!=null){
               Date date1 = input.parse(part);
               holder.binding.updatedDatee.setText(output.format(date1));
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
