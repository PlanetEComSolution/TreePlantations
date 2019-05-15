package com.planet.treeplantations.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.planet.treeplantations.Activities.FullscreenWebView;
import com.planet.treeplantations.R;
import com.planet.treeplantations.models.Image_Model;

import java.util.List;

//import javax.sql.DataSource;

public class Image_Adapter extends RecyclerView.Adapter<Image_Adapter.MyViewHolder> {

    private List<Image_Model> Image_list;
    private Context context;

    public Image_Adapter(Context context, List<Image_Model> moviesList) {
        this.Image_list = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Image_Model image_model = Image_list.get(position);

       /* Glide.with(context)
                .load(image_model.getImage())
                .into(holder.imageView);

*/

        Glide.with(context).load(image_model.getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.spinner.setVisibility(View.GONE);
                holder.imageView.setImageResource(R.drawable.no_image);
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.spinner.setVisibility(View.GONE);
                return false;
            }
        }).into(  holder.imageView);




        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FullscreenWebView.class);
                i.putExtra("url", image_model.getImage());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (Image_list == null)
            return 0;
        return Image_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private ProgressBar spinner;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            spinner = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
}
