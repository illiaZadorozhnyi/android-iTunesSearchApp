package com.example.itunessearchapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private Context context;
    private ArrayList<Movie> list;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    // onCreateViewHolder stays without much change for any recycleViewAdaptor, all it needs is a layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // this decides what UI is going to look like, IMPORTANT METHOD !
    @Override
    public void onBindViewHolder(ViewHolder holderPassed, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        final Movie movieItem = list.get(position);
        final ViewHolder holder = holderPassed;


        Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.ic_autorenew)
                .load(movieItem.getImageURL())
                .into(holder.image);

        holder.imageDescr.setText(movieItem.getImageDesc());
        holder.namesView.setText(movieItem.getTitle());

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(ArrayList<Movie> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageDescr;
        LinearLayout parentLayout;
        TextView namesView;
        ImageView heartImage;
        boolean isFavorited;
        boolean isExpanded;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageDescr = itemView.findViewById(R.id.image_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            namesView = itemView.findViewById(R.id.movie_name);
            heartImage = itemView.findViewById(R.id.like_image_border);
            imageDescr.setVisibility(View.GONE);
            toggleFavorite();
            toggleExpanded();
        }

        private void toggleFavorite() {
            heartImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFavorited) {
                        heartImage.setBackgroundResource(R.drawable.ic_favorite_border);
                        isFavorited = false;
                    } else {
                        heartImage.setBackgroundResource(R.drawable.ic_favorite_full);
                        isFavorited = true;
                        displayToast("Added to favorites!", v);
                    }
                }
            });
        }

        private void toggleExpanded() {
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (isExpanded) {
                        imageDescr.setVisibility(View.GONE);
                        isExpanded = false;
                    } else {
                        imageDescr.setVisibility(View.VISIBLE);
                        isExpanded = true;
                    }
                }
            });
        }

        private void displayToast(String text, View v) {
            Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT).show();
        }

    }
}
