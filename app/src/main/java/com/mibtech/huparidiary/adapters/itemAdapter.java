package com.mibtech.huparidiary.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.mibtech.huparidiary.ItemDetailActivity;
import com.mibtech.huparidiary.R;
import com.mibtech.huparidiary.model.items;

public class itemAdapter extends  RecyclerView.Adapter<itemAdapter.MyViewHolder>

{

    private List<items> mDataset;
    String catname_text;
    Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public TextView itemname;
        public RatingBar ratingBar;
        public TextView  totalRatings;
        public TextView status;
        public TextView catname;
        public  CardView cardView;
        public MyViewHolder(View v) {
            super(v);
            itemname = v.findViewById(R.id.item_name);
            ratingBar=v.findViewById(R.id.ratingbar);
            totalRatings=v.findViewById(R.id.totalrating);
            catname = v.findViewById(R.id.catname);
            status = v.findViewById(R.id.status);
            cardView=v.findViewById(R.id.card_item);
        }



    }

    // Provide a suitable constructor (depends on the kind of dataset)

    public itemAdapter(Activity activity, List<items> myDataset,String catname_text) {
        mDataset=new ArrayList<items>();
        this.catname_text=catname_text;
        mDataset = myDataset;
        this.activity=activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public itemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcat_items, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final items item= mDataset.get(position);
        holder.itemname.setText(item.getName());
        holder.totalRatings.setText(item.getRatings());
        holder.catname.setText(catname_text);
        holder.status.setText(item.getStatus());
        holder.ratingBar.setIsIndicator(true);
        holder.ratingBar.setRating(Float.valueOf(item.getStars()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= null;
                try {
                    intent = new Intent(activity, ItemDetailActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("MyClass", item);
                activity.startActivity(intent);


            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}