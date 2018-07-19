package com.udacity.spyrakis.capstoneapp.engine;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.capstoneapp.R;
import com.udacity.spyrakis.capstoneapp.models.placeDetails.PlaceDetails;

import java.util.ArrayList;

/**
 * Created by pspyrakis on 17/7/18.
 */
public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ViewHolder> {

    private ArrayList<PlaceDetails> places;
    private OnListItemClickListener listener;

    public ResultsListAdapter(ArrayList<PlaceDetails> places, OnListItemClickListener listener) {
        this.places = places;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PlaceDetails item = places.get(position);
        viewHolder.itemView.setTag(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView image;
        private TextView title;
        private TextView description;
        private OnListItemClickListener listener;

        ViewHolder(View v, OnListItemClickListener listener) {
            super(v);
            this.image = v.findViewById(R.id.image);
            this.title = v.findViewById(R.id.title);
            this.description = v.findViewById(R.id.description);
            this.listener = listener;
        }

        void bind(final PlaceDetails place) {
            this.title.setText(place.getName());
            this.description.setText(place.getAddress());
            this.title.setText(place.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    listener.onItemClick(position);
                }
            });
            if (place.getIcon().trim().length() != 0) {
                Picasso.get().load(place.getIcon()).placeholder(R.drawable.placeholder).into(this.image);
            }
        }
    }
}
