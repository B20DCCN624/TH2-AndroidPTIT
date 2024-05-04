package com.example.androidptit_th2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidptit_th2.R;
import com.example.androidptit_th2.adapter.SongAdapter;
import com.example.androidptit_th2.dal.SQLiteHelper;
import com.example.androidptit_th2.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{
    private SearchView search;
    private RecyclerView rcvSearch;
    private SQLiteHelper db;
    private SongAdapter songAdapter;
    private Spinner spinnerStatus;
    private ArrayAdapter<String> statusAdapter;
    List<Song>listData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = view.findViewById(R.id.search);
        rcvSearch = view.findViewById(R.id.rcv_serach);
        spinnerStatus = view.findViewById(R.id.spinner_statistical);
        db = new SQLiteHelper(getContext());

        String[] status = {"Chưa thực hiện", "Đang thực hiện", "Hoàn thành"};
        statusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, status);
        spinnerStatus.setAdapter(statusAdapter);

        songAdapter = new SongAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvSearch.setLayoutManager(linearLayoutManager);
        List<Song> list = db.getAll();
        songAdapter.setData(list);
        rcvSearch.setAdapter(songAdapter);
        search.setOnQueryTextListener(this);

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                List<Song> result = new ArrayList<>();
                if (selectedItem.equals("Tất cả")) {
                    result = db.getAll();
                } else {
                    listData = db.getAll();
                    for (Song item: listData) {
                        if (item.getStatus().equals(selectedItem)) {
                            result.add(item);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Song> music = db.searchByName(s, "date DESC");
        songAdapter.setData(music);
        return false;
    }
}
