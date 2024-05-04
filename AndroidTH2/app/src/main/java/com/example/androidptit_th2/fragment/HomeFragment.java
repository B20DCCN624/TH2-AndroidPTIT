package com.example.androidptit_th2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidptit_th2.AddItem;
import com.example.androidptit_th2.MusicDetail;
import com.example.androidptit_th2.R;
import com.example.androidptit_th2.adapter.SongAdapter;
import com.example.androidptit_th2.dal.SQLiteHelper;
import com.example.androidptit_th2.model.Song;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment implements SongAdapter.ItemListener{
    private SongAdapter songAdapter;
    private RecyclerView rcvSong;
    private FloatingActionButton actionButton;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvSong = view.findViewById(R.id.rcv_list_song);
        actionButton = view.findViewById(R.id.fab);
        db = new SQLiteHelper(getContext());

        songAdapter = new SongAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvSong.setLayoutManager(linearLayoutManager);
        List<Song> list = db.getAll();

        songAdapter.setData(list);
        rcvSong.setAdapter(songAdapter);
        songAdapter.setItemListener(this);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddItem.class);
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void onItemClick(View view, int position) {
        Song song = songAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), MusicDetail.class);
        intent.putExtra("song", song);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Song> music = db.getAll();
        songAdapter.setData(music);
        songAdapter.notifyDataSetChanged();
    }
}
