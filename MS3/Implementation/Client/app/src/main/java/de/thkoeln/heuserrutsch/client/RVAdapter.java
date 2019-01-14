package de.thkoeln.heuserrutsch.client;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EntryViewHolder>{

    List<Entry> entry;

    RVAdapter(List<Entry> entry){
        this.entry = entry;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_card , viewGroup, false);
        EntryViewHolder evh = new EntryViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i) {
        entryViewHolder.text1.setText(entry.get(i).userID);
        entryViewHolder.text2.setText(entry.get(i).title);
        //entryViewHolder.photo.setImageResource(entry.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return entry.size();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView text1;
        TextView text2;
        ImageView photo;

        EntryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.lcv);
            text1 = (TextView) itemView.findViewById(R.id.list_text_1);
            text2 = (TextView) itemView.findViewById(R.id.list_text_2);
            photo = (ImageView) itemView.findViewById(R.id.list_person_photo);
        }


    }

}