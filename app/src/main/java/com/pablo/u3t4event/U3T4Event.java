package com.pablo.u3t4event;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class U3T4Event extends AppCompatActivity implements Serializable {

    private final int REQUEST = 0;

    private EditText etEventName;

    private TextView tvCurrentData;

    private Event event;

    private Bundle bundle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        event = (Event) getIntent().getSerializableExtra("Event");

        setUI();
    }

    private void setUI(){
        etEventName = findViewById(R.id.etEventName);
        tvCurrentData = findViewById(R.id.tvCurrentData);

        tvCurrentData.setText("");
    }

    public void editEventData(View view) {
        Intent intent = new Intent(this, EventDataActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("EventName",etEventName.getText().toString());
        intent.putExtras(bundle);

        startActivityForResult(intent,REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST && resultCode == RESULT_OK){
            tvCurrentData.setText(data.getStringExtra("EventData"));
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvCurrentData.setText(savedInstanceState.getString("tvCurrentData"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("tvCurrentData", tvCurrentData.getText().toString());
        super.onSaveInstanceState(outState);
    }


}