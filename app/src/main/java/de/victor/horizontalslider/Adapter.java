package de.victor.horizontalslider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    private static final String LOGTAG = "Adapter";
    private List<String> data = new ArrayList<>();


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION)
            holder.itemText.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<String> data) {
        ArrayList<String> a = new ArrayList<>();
        a.add("");
        a.addAll(data);
        a.add("");
        this.data = a;
    }


    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;
        ConstraintLayout itemContainer;

        public AdapterViewHolder(@NonNull View view) {
            super(view);

            itemContainer = view.findViewById(R.id.item_container);
            itemText = view.findViewById(R.id.item_text);

        }
    }

}
