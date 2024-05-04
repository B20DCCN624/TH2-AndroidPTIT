package com.example.androidptit_th2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidptit_th2.adapter.SongAdapter;
import com.example.androidptit_th2.dal.SQLiteHelper;
import com.example.androidptit_th2.model.Song;

import java.util.ArrayList;
import java.util.Calendar;

public class AddItem extends AppCompatActivity {

    private EditText addWork, addContent, addDate;
    private Spinner spinnerStatus;
    private Button btnAddWork, btnCancleWork;
    private CheckBox checkBox;
    private ArrayAdapter<String> statusAdapter;
    private SongAdapter songAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addWork = findViewById(R.id.tv_add_work);
        addContent = findViewById(R.id.tv_add_content);
        addDate = findViewById(R.id.tv_add_date);
        spinnerStatus = findViewById(R.id.add_spin_status);
        btnAddWork = findViewById(R.id.btn_add_work);
        btnCancleWork = findViewById(R.id.btn_cancle_work);
        checkBox = findViewById(R.id.checkIsLike);

        String[] status = {"Chưa thực hiện", "Đang thực hiện", "Hoàn thành"};
        statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, status);
        spinnerStatus.setAdapter(statusAdapter);

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int yyyy = c.get(Calendar.YEAR);
                int mm = c.get(Calendar.MONTH);
                int dd = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddItem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int d, int m, int y) {
                        addDate.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, yyyy, mm, dd);
                datePickerDialog.show();
            }
        });

        btnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String work = addWork.getText().toString();
                String content = addContent.getText().toString();
                String date = addDate.getText().toString();
                String status = spinnerStatus.getSelectedItem().toString();
                boolean collaborate = checkBox.isChecked();

                if(!work.isEmpty() && !content.isEmpty() && !date.isEmpty()) {
                    Song i = new Song(work, content, date, status, collaborate);
                    SQLiteHelper db= new SQLiteHelper(getApplicationContext());
                    db.addWork(i);
                    Toast.makeText(getApplicationContext(), "Thêm bài hát thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancleWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}