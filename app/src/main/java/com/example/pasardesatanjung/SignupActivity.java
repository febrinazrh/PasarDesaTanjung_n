package com.example.pasardesatanjung;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etUsername, etPassword;
    String Name, Username, Password;
    TextView tvSigninLink;
    String url = "https://febrinazrh.000webhostapp.com/register_konsumen.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvSigninLink = (TextView) findViewById(R.id.tvSigninLink);

        tvSigninLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Register(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(etName.getText().toString().equals("")){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }
        else if(etUsername.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(etPassword.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
            Name = etName.getText().toString().trim();
            Username = etUsername.getText().toString().trim();
            Password = etPassword.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    etName.setText("");
                    etUsername.setText("");
                    etPassword.setText("");
                    Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(SignupActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("fullname",Name);
                    params.put("username",Username);
                    params.put("password",Password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
            requestQueue.add(request);


        }

    }
}