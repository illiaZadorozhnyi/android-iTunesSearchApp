package com.example.itunessearchapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<String> mImages = new ArrayList<>();
    private List<String> mImageDescr = new ArrayList<>();
    private List<String> movieNames = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(List<String> mImageDescr, List<String> mImages, List<String> movieNames, Context context) {
        this.mImages = mImages;
        this.context = context;
        this.mImageDescr = mImageDescr;
        this.movieNames = movieNames;
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

        final ViewHolder holder = holderPassed;

        Glide.with(context)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on " + mImageDescr.get(position));

// TODO need to look for better implementations for Toast
//                Toast.makeText(context, mImageDescr.get(position), Toast.LENGTH_SHORT).show();

//  TODO need to understand how to apply this below toggle-like behavior
//                if (view.getVisibility() == View.VISIBLE) {
//                    view.setVisibility(View.GONE);
//                } else {
//                    view.setVisibility(View.VISIBLE);
//                }

                holder.imageDescr.setText(mImageDescr.get(position));
                holder.namesView.setText("");

            }
        });
        holder.namesView.setText(movieNames.get(position));
//        holder.movieNames.setText(mImageDescr.get(position));

//       holder.movieNames.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick for minDescr : clicked on " + movieNames.get(position));
////                movieNames.setText(movieNames.get(position));
//            }
//        });
    }

    // this tells the adaptor how many items are in the list, if it is left at 0, blank screen will be displayed
    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageDescr;
        RelativeLayout parentLayout;
        TextView namesView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageDescr = itemView.findViewById(R.id.image_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            namesView = itemView.findViewById(R.id.movie_name);
        }
    }
}
