package com.example.androidptit_th2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidptit_th2.adapter.SongAdapter;
import com.example.androidptit_th2.dal.SQLiteHelper;
import com.example.androidptit_th2.model.Song;

public class MusicDetail extends AppCompatActivity {
    private EditText editWork, editContent, editDate;
    private Spinner spinnerStatus;
    private CheckBox checkBox;
    private Button btnUpdateWork, btnDeleteWork;
    private SQLiteHelper db;
    private Song currentMusic;
    private SongAdapter songAdapter;
    private ArrayAdapter<String> statusAdapter;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);

        Intent intent = getIntent();
        db = new SQLiteHelper(getApplicationContext());
        editWork = findViewById(R.id.detail_work);
        editContent = findViewById(R.id.detail_content);
        editDate = findViewById(R.id.detail_date);
        spinnerStatus = findViewById(R.id.spin_status);
        checkBox = findViewById(R.id.checkIsLike);
        btnUpdateWork = findViewById(R.id.btnUpdate);
        btnDeleteWork = findViewById(R.id.btnDelete);

        String[] status = {"Chưa thực hiện", "Đang thực hiện", "Hoàn thành"};
        statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, status);
        spinnerStatus.setAdapter(statusAdapter);

        currentMusic = (Song) intent.getSerializableExtra("song");
        setData();

        btnUpdateWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String work = editWork.getText().toString();
                String content = editContent.getText().toString();
                String date = editDate.getText().toString();
                String status = spinnerStatus.getSelectedItem().toString();
                boolean collaborate = checkBox.isChecked();

                if(!work.isEmpty() && !content.isEmpty() && !date.isEmpty()) {
                    Song i = new Song(currentMusic.getId(),work, content, date, status, collaborate);
                    db.updateWork(i);
                    Toast.makeText(getApplicationContext(), "Cập nhật công việc thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thông báo xóa!");
                builder.setTitle("Bạn có chắc muốn xóa công việc "+ currentMusic.getName()+" không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteWork(currentMusic.getId());
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void setData() {
        if (currentMusic == null) {
            return;
        }
        editWork.setText(currentMusic.getName());
        editContent.setText(currentMusic.getContent());
        editDate.setText(currentMusic.getDate());
        spinnerStatus.setSelection(statusAdapter.getPosition(currentMusic.getStatus()));
        checkBox.setChecked(currentMusic.isCollaborate());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}