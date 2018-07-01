package com.example.android.journal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.Task;

import java.util.List;

/**
 * Created by katherine on 7/1/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<Entry> entry;
    protected Context context;

    public RecyclerViewAdapter(Context context, List<Entry> entry) {
        this.entry = entry;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_entry, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView, entry);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.entryTitle.setText(entry.get(position).getEntry());
    }

    @Override
    public int getItemCount() {
        return this.entry.size();
    }
}
