package com.planet.treeplantations.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.planet.treeplantations.databinding.AdminDashboardListItemBinding;
import android.view.ViewGroup;
import com.planet.treeplantations.R;
import com.planet.treeplantations.models.Dashboard_data;
import java.util.List;

public class State_Adapter extends RecyclerView.Adapter<State_Adapter.MyViewHolder> {
    private List<Dashboard_data> List;
    private LayoutInflater layoutInflater;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private AdminDashboardListItemBinding binding;
        public MyViewHolder(final AdminDashboardListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public State_Adapter(List<Dashboard_data> postList) {
        this.List = postList;
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        AdminDashboardListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.admin_dashboard_list_item, parent, false);
        return new MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setItem(List.get(position));
//        if(List.size()==position-1){
//
//        }

    }
    @Override
    public int getItemCount() {
        if(List == null)
            return 0;
        return List.size();
        }
}
