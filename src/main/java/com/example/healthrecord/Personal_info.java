package com.example.healthrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
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

public class Personal_info extends AppCompatActivity {

    private WebView webView;
    private String htmlMiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        webView = findViewById(R.id.webview_personal);

        personal_info();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void personal_info(){
        final String Email = Profile.Email;

        StringRequest stringRequest = new StringRequest(POST, Constant.url_personalInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    String[] content = response.toString().split("\n");
                    int ln = 0;
                    if(content.length > 15)
                        ln = 15;
                    else
                        ln = content.length;
                    for(int i=0; i<ln; i++){
                        String[] element = content[i].split("-");

                        htmlMiddle +=   "   <tr>\n" +
                                "<td>"+element[0]+"</td>\n" +
                                "<td>"+element[1]+"</td>\n" +
                                "<td>"+element[2]+"</td>\n" +
                                "<td>"+element[3]+"</td>\n" +
                                "<td>"+element[4]+"</td>\n" +
                                "<td>"+element[5]+"</td>\n" +
                                "<td>"+element[6]+"</td>\n" +
                                "<td>"+element[7]+"</td>\n" +
                                "  </tr>\n" ;
                    }

                    String htmlTable = Constant.html+htmlMiddle+Constant.htmlLast;
                    webView.loadDataWithBaseURL(null,htmlTable,"text/html","utf-8",null);

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
                params.put("Email",Email);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
