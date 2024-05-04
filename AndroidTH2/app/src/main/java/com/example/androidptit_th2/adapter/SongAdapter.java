package com.example.androidptit_th2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidptit_th2.R;
import com.example.androidptit_th2.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private Context context;
    private List<Song> listSong;
    private ItemListener itemListener;
    public SongAdapter(Context context) {
        this.context = context;
        listSong = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setData(List<Song> list) {
        this.listSong = list;
        notifyDataSetChanged();
    }

    public Song getItem(int position) {
        return listSong.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = listSong.get(position);
        if(song == null) {
            return;
        }
        holder.tvWork.setText(song.getName());
        holder.tvContent.setText(song.getContent());
        holder.tvDate.setText(song.getDate());
    }

    @Override
    public int getItemCount() {
        if(listSong!=null) {
            return listSong.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvWork, tvContent, tvDate;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvWork = view.findViewById(R.id.tv_work);
            tvContent = view.findViewById(R.id.tv_content);
            tvDate = view.findViewById(R.id.tv_date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener {
        void onItemClick(View view, int position);
    }
}
