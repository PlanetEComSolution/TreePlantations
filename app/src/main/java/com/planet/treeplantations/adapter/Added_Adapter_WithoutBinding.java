package com.planet.treeplantations.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.treeplantations.R;
import com.planet.treeplantations.models.Dashboard_Data_Model;

import java.util.List;

public class Added_Adapter_WithoutBinding  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;

    static Context context;
    List<Dashboard_Data_Model> movies;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    /*
     * isLoading - to set the remote loading and complete status to fix back to back load more call
     * isMoreDataAvailable - to set whether more data from server available or not.
     * It will prevent useless load more request even after all the server data loaded
     * */


    public Added_Adapter_WithoutBinding(Context context, List<Dashboard_Data_Model> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType==TYPE_MOVIE){
            return new MovieHolder(inflater.inflate(R.layout.list_item_without_binding,parent,false));
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
        TextView land,create_by,cre_date,update_by,update_date;

        public MovieHolder(View itemView) {
            super(itemView);
            land=(TextView)itemView.findViewById(R.id.land);
            create_by=(TextView)itemView.findViewById(R.id.create_by);
            cre_date=(TextView)itemView.findViewById(R.id.cre_date);
            update_by=(TextView)itemView.findViewById(R.id.update_by);
            update_date=(TextView)itemView.findViewById(R.id.update_date);

        }

        void bindData(Dashboard_Data_Model movieModel){
            land.setText(movieModel.getProjectCodeName());
            create_by.setText(movieModel.getCreatedBy());
            cre_date.setText(movieModel.getCreatedDate());
            update_by.setText(movieModel.getUpdatedBy());
            update_date.setText(movieModel.getUpdateddate());
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
