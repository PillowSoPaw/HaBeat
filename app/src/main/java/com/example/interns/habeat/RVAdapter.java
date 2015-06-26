package com.example.interns.habeat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Interns on 6/26/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.customViewHolder> {
    private LayoutInflater inflater;
    List<String> data = Collections.emptyList();


    public RVAdapter (Context context,List<String> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_row,parent,false);
        customViewHolder holder = new customViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(customViewHolder holder, int position) {
        String current = data.get(position);
        holder.drawer_item_name.setText(current);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class customViewHolder extends RecyclerView.ViewHolder{
        TextView drawer_item_name;

        public customViewHolder(View itemView) {
            super(itemView);

            drawer_item_name = (TextView) itemView.findViewById(R.id.drawer_item);
        }
    }
}
