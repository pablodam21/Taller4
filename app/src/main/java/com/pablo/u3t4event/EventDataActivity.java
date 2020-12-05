package com.pablo.u3t4event;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView tvEventName;
    private EditText etPlace;
    private RadioGroup rgPriority;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private Button btAccept;
    private Button btCancel;

    private String priority = "Normal";
    private String[] mouth;
    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        Resources res = getResources();
        mouth = res.getStringArray(R.array.mouths);

        Bundle inputData = getIntent().getExtras();

        tvEventName.setText(inputData.getString("EventName"));
    }

    @SuppressLint("SwitchIntDef")
    private void setUI(){
        tvEventName = findViewById(R.id.tvEventName);
        rgPriority = findViewById(R.id.rgPriority);
        rgPriority.check(R.id.rbMedium);
        dpDate = findViewById(R.id.dpDate);

        if(getRotation(getApplicationContext()).equals("vertical")){ //es vertical o portrait.
            dpDate.setCalendarViewShown(false);
        }else{ // es horizontal o landscape.
            dpDate.setCalendarViewShown(true);
        }

        tpTime = findViewById(R.id.tpTime);
        tpTime.setIs24HourView(true);
        btAccept= findViewById(R.id.btAceptar);
        btCancel = findViewById(R.id.btCancel);
        etPlace = findViewById(R.id.etPlace);


        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgPriority.setOnCheckedChangeListener(this);

    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        Intent activityResuklt = new Intent(this,U3T4Event.class);
        Bundle eventData = new Bundle();
        Resources res = getResources();
        mouth = res.getStringArray(R.array.mouths);
        switch (v.getId()){
            case R.id.btAceptar:
                event = new Event(etPlace.getText().toString(),priority,dpDate,tpTime);
                eventData.putString("EventData","Priority: " + priority + "\n"+
                                                "Date: " + dpDate.getDayOfMonth() + mouth[dpDate.getMonth()] + dpDate.getYear() + "\n"+
                                                "Hour: "  + tpTime.getHour()+":"+tpTime.getMinute()+ "\n" +
                                                "Place: " + etPlace.getText());
                eventData.putString("Event",event.toString());
                break;
            case R.id.btCancel:
                eventData.putString("EventData",activityResuklt.getSerializableExtra("EventData").toString());
                break;
        }
        activityResuklt.putExtras(eventData);
        setResult(RESULT_OK,activityResuklt);

        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbLow:
                priority = "Low";
                break;
            case R.id.rbMedium:
                priority = "Medium";
                break;
            case R.id.rbHigh:
                priority = "High";
                break;

        }
    }
    public String getRotation(Context context){
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return "vertical";
            case Surface.ROTATION_90:
            default:
                return "horizontal";
        }
    }


}