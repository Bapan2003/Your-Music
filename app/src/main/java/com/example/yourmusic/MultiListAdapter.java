package com.example.yourmusic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class MultiListAdapter extends RecyclerView.Adapter<MultiListAdapter.ViewHolder> {
    public MultiListAdapter(ArrayList<AudioModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    ArrayList<AudioModel>songList;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_view,parent,false);
        return new MultiListAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MultiListAdapter.ViewHolder holder,int position) {
        AudioModel songdata=songList.get(holder.getAdapterPosition());
        holder.titleTextView.setText(songdata.getTitle());
        if(MyMediaPlayer.curIndex==holder.getAdapterPosition()){
            holder.titleTextView.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.titleTextView.setTextColor(Color.parseColor("#000000"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyMediaPlayer.curIndex!=holder.getAdapterPosition()) {
                    MyMediaPlayer.getInstance().reset();
                    MyMediaPlayer.curIndex = holder.getAdapterPosition();
                    Intent intent = new Intent(context, MusicPlayer.class);
                    intent.putExtra("List", songList);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, MusicPlayer.class);
                    intent.putExtra("List", songList);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return songList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
       public TextView titleTextView;
        ImageView iconImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.music_title_text);
            iconImageView=itemView.findViewById(R.id.play_icon);
        }
    }

}
