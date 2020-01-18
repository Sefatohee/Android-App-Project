package com.example.healthrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private EditText  date, consumed_calories,sugar_level,systolic_rate,diastolic_rate,weight;
    private TextView username;
    private Button submit;
    private ProgressBar updateprogress;
    private DatePicker datePicker;
    private TimePicker timePicker;
    public static String Email, Date;
    private final String channel_id = "public notifications";
    private final int notification_id = 001;
    private ProgressBar progressBar;
    public static String name;
    View view;

    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = getIntent().getExtras();
        Email = bundle.getString("Email");

        progressBar = findViewById(R.id.update_pro);
        username = findViewById(R.id.username);
        username.setText(name);
        datePicker = findViewById(R.id.picdate);
        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        consumed_calories = findViewById(R.id.consumed_calories);
        date = findViewById(R.id.date);
        systolic_rate = findViewById(R.id.systolicRate);
        diastolic_rate = findViewById(R.id.diastolicRate);
        weight = findViewById(R.id.weight);
        sugar_level = findViewById(R.id.sugarLevel);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        date.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){

            SharedPreferences settings = getSharedPreferences("login", Context.MODE_PRIVATE);
            settings.edit().clear().commit();

            this.finish();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }


        else if(item.getItemId() == R.id.myinfo){
            Intent intent = new Intent(getApplicationContext(),Personal_info.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.date){
            date.setText(currentDate());
        }

        if(view.getId() == R.id.submit){
            update(Date,view);
        }


    }

    String currentDate(){
        StringBuilder date = new StringBuilder();
        StringBuilder dateForAutoUpdate = new StringBuilder();
        if(datePicker.getDayOfMonth()<10){
            date.append("0"+datePicker.getDayOfMonth()+"/");
            dateForAutoUpdate.append("0"+(datePicker.getDayOfMonth()-1)+"/");
        }else {
            date.append(datePicker.getDayOfMonth() + "/");
            dateForAutoUpdate.append(datePicker.getDayOfMonth()-1 + "/");
        }
        if(datePicker.getMonth() <10){
            date.append("0"+(datePicker.getMonth()+1)+"/");
            dateForAutoUpdate.append("0"+(datePicker.getMonth()+1)+"/");
        }else{
            date.append((datePicker.getMonth()+1)+"/");
            dateForAutoUpdate.append((datePicker.getMonth()+1)+"/");
        }

        date.append(datePicker.getYear());
        dateForAutoUpdate.append(datePicker.getYear());
        Date = dateForAutoUpdate.toString();
        return date.toString();
    }


    private void update(String datepic, View view) {

        progressBar.setVisibility(View.VISIBLE);

        final String Date = date.getText().toString().trim();
        final String SugarLevel = sugar_level.getText().toString().trim();
        final String ConsumedCalories = consumed_calories.getText().toString().trim();
        final String DiastolicRate = diastolic_rate.getText().toString().trim();
        final String SystolicRate = systolic_rate.getText().toString().trim();
        final String Weight = weight.getText().toString().trim();


        if(Date.isEmpty()){
            date.setError("please enter your date");
            date.requestFocus();
            return;
        }

        if(SugarLevel.isEmpty()){
            sugar_level.setError("please enter your sugar level");
            sugar_level.requestFocus();
            return;
        }

        if(ConsumedCalories.isEmpty()){
            consumed_calories.setError("please enter today's consumed calories");
            consumed_calories.requestFocus();
            return;
        }

        if(SystolicRate.isEmpty()){
            systolic_rate.setError("please enter your low blood pressure");
            diastolic_rate.requestFocus();
            return;
        }

        if(DiastolicRate.isEmpty()){
            diastolic_rate.setError("please enter high blood pressure");
            diastolic_rate.requestFocus();
            return;
        }

        if(Weight.isEmpty()){
            weight.setError("please enter your weight");
            weight.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(POST, Constant.url_updatebazar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("Updated successfully")){

                        progressBar.setVisibility(View.GONE);
                       // setNotification();
                        date.setText(null);
                        sugar_level.setText(null);
                        consumed_calories.setText(null);
                        systolic_rate.setText(null);
                        diastolic_rate.setText(null);
                        weight.setText(null);


                    }else{
                        progressBar.setVisibility(View.GONE);
                    }
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Email", Email);
                params.put("Date",Date);
                params.put("Sugar_level",SugarLevel);
                params.put("CC",ConsumedCalories);
                params.put("SR",SystolicRate);
                params.put("DR",DiastolicRate);
                params.put("Weight",Weight);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }


    public void setNotification(){

        String tk =sugar_level.getText().toString().trim();
        String message;
        String Message;
        String title;

        if(tk.equalsIgnoreCase("0")){
            Message = "Hei "+Email+"\n You have update your meal\n "+"You have taken "+consumed_calories.getText().toString().trim()+" meal today";
            title = "Meal updated";
        }else{
            Message = "Bazar is done by "+Email+"\n and the cost is "+sugar_level.getText().toString().trim()+" Tk";
            title = "Bazar done !!!";
        }

        message = Message;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)

        .setSmallIcon(R.drawable.ic_message_black_24dp)
        .setContentTitle(title)
        .setContentText(message)
        .setAutoCancel(true);
        builder.setVibrate(new long[]{0, 500, 1000});
        builder.setLights(0xff0000ff, 300, 1000);

        Intent intent = new Intent(this,Notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Message",message);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);


        NotificationManager notificationManager =(NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE
        );

        notificationManager.notify(0,builder.build());
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);
                }
            },  10);

        }

    }
}
