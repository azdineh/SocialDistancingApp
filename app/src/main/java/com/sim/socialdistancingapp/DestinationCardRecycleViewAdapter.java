package com.sim.socialdistancingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinationCardRecycleViewAdapter extends RecyclerView.Adapter<DestinationCardRecycleViewAdapter.DesctinationCardViewHolder> {

    private List<Destination> destinationList;
    private OnItemClickListener mListener;

    public DestinationCardRecycleViewAdapter(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }

    public interface OnItemClickListener {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    @NonNull
    @Override
    public DesctinationCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_item, parent, false);
        return new DesctinationCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull DesctinationCardViewHolder holder, int position) {
        if (destinationList != null && position < destinationList.size()) {
            Destination destination = destinationList.get(position);
            holder.textViewDestinationName.setText(destination.getName());
            holder.textViewDestinationDateTime.setText(destination.getWhenAvailable());
        }
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public class DesctinationCardViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewDestinationName;
        public TextView textViewDestinationDateTime;


        public DesctinationCardViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDestinationName=itemView.findViewById(R.id.destination_name);
            textViewDestinationDateTime=itemView.findViewById(R.id.destination_rec);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(pos);
                    }
                }
            }
        });

        }
    }
}
