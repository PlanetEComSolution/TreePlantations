package com.planet.treeplantations.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.treeplantations.R;
import com.planet.treeplantations.models.Dashboard_Data_Model;
import com.planet.treeplantations.models.Dashboard_data;

import java.util.List;

public class State_Adapter_WithoutBinding extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;

    static Context context;
    List<Dashboard_data> movies;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    /*
     * isLoading - to set the remote loading and complete status to fix back to back load more call
     * isMoreDataAvailable - to set whether more data from server available or not.
     * It will prevent useless load more request even after all the server data loaded
     * */


    public State_Adapter_WithoutBinding(Context context, List<Dashboard_data> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType==TYPE_MOVIE){
            return new MovieHolder(inflater.inflate(R.layout.admin_dashboard_list_item_new,parent,false));
        }else{
            return new LoadHolder(inflater.inflate(R.layout.row_load,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position>=getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if(getItemViewType(position)==TYPE_MOVIE){
            ((MovieHolder)holder).bindData(movies.get(position));
        }
        //No else part needed as load holder doesn't bind any data
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(movies.get(position).getType().equals("data")){
//            return TYPE_MOVIE;
//        }else{
//            return TYPE_LOAD;
//        }
//    }

    @Override
    public int getItemCount() {

        if(movies == null)
            return 0;
        return movies.size();
    }

    /* VIEW HOLDERS */

    static class MovieHolder extends RecyclerView.ViewHolder{
        TextView state_name,total_project_cost,total_plant;

        public MovieHolder(View itemView) {
            super(itemView);
            state_name=(TextView)itemView.findViewById(R.id.state_name);
            total_project_cost=(TextView)itemView.findViewById(R.id.total_project_cost);
            total_plant=(TextView)itemView.findViewById(R.id.total_plant);


        }

        void bindData(Dashboard_data movieModel){
            total_plant.setText(movieModel.getTotalNoOfPlant());
            state_name.setText(movieModel.getStateName());
            total_project_cost.setText(movieModel.getTotalProjectCost());

        }
    }

    static class LoadHolder extends RecyclerView.ViewHolder{
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }


    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

}
