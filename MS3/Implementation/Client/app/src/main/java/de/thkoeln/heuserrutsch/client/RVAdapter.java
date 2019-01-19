package de.thkoeln.heuserrutsch.client;

import android.annotation.SuppressLint;
import android.content.Context;
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

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i) {
        entryViewHolder.text1.setText(entry.get(i).uname + " " + entry.get(i).usurname);
        System.out.print(entry.get(i).uname + " " + entry.get(i).usurname);
        entryViewHolder.text2.setText("Das Ergebnis stimmt zu " + entry.get(i).score + "% überein");
        entryViewHolder.text3.setText("RATING");
        //if (entry.get(i).hHaveTransporter==false) entryViewHolder.iv1.setVisibility(0);
        //if (entry.get(i).hDriveTransporter==false) entryViewHolder.iv1.setVisibility(0);
        //if (entry.get(i).hCanDischarge==false) entryViewHolder.iv1.setVisibility(0);
        //if (entry.get(i).hCanTransport==false) entryViewHolder.iv1.setVisibility(0);
        //if (entry.get(i).hCanMontate==false) entryViewHolder.iv1.setVisibility(0);
        //if (entry.get(i).hCanInstall==false) entryViewHolder.iv1.setVisibility(0);

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
        TextView text3;
        ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7;

        //ImageView photo;

        EntryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.lcv);
            text1 = (TextView) itemView.findViewById(R.id.list_text_1);
            text2 = (TextView) itemView.findViewById(R.id.list_text_2);
            text3 = (TextView) itemView.findViewById(R.id.list_text_3);
            ImageView iv1 = (ImageView) itemView.findViewById(R.id.lcimageView1);
            ImageView iv2 = (ImageView) itemView.findViewById(R.id.lcimageView2);
            ImageView iv3 = (ImageView) itemView.findViewById(R.id.lcimageView3);
            ImageView iv4 = (ImageView) itemView.findViewById(R.id.lcimageView4);
            ImageView iv5 = (ImageView) itemView.findViewById(R.id.lcimageView5);
            ImageView iv6 = (ImageView) itemView.findViewById(R.id.lcimageView6);
            ImageView iv7 = (ImageView) itemView.findViewById(R.id.lcimageView7);
            System.out.println("CREATED ENTRYVIEWHOLDER");


            //photo = (ImageView) itemView.findViewById(R.id.list_person_photo);
        }


    }

}