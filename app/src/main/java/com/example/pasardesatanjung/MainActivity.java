package com.example.pasardesatanjung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String url = "https://febrinazrh.000webhostapp.com/displaybrg.php";

    List<Model> modelList;

    String id = "id";
    String nama = "nama";
    String harga = "harga";
    String gambar = "gambar";
    String deskripsi = "deskripsi";

    JsonArrayRequest RequestOfJson;
    RequestQueue requestQueue;

    int total =0;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<String> ImageTitleIdforClick;
    TextView txtTotal;
    CardView feez;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        email= findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout);

        recyclerView = findViewById(R.id.rcy);
        txtTotal = findViewById(R.id.total);

        ImageTitleIdforClick = new ArrayList<>();
        modelList = new ArrayList<>();

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName=acct.getDisplayName();
            String personEmail=acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        feez = findViewById(R.id.feez);
        feez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clara = new Intent(MainActivity.this, PembayaranActivity.class);
                startActivity(clara);
            }
        });

        JSON_HTTP();
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity.this,SigninActivity.class));
            }
        });
    }

    public void JSON_HTTP() {
        RequestOfJson = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ParseJsonResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(RequestOfJson);

    }
    public void ParseJsonResponse(JSONArray array){
        for(int i = 0;i<array.length();i++){
            Model model = new Model();
            JSONObject json= null;
            try {
                json = array.getJSONObject(i);
                ImageTitleIdforClick.add(json.getString(id));
                model.setId(json.getString(id));
                model.setnm_brg(json.getString(nama));
                model.setHarga(json.getInt(harga));
                model.setDeskripsi(json.getString(deskripsi));
                model.setGambar(json.getString(gambar));
            }catch (JSONException e){
                e.printStackTrace();
            }
            modelList.add(model);
        }
        adapter = new RecyclerViewAdapter(modelList,this);
        recyclerView.setAdapter(adapter);
    }

    public void hitung(View view){
        total++;
        Integer listharga = 40000;
        if (txtTotal!=null){
            txtTotal.setText("Rp."+(total*listharga));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call_center:
                Toast.makeText(getApplicationContext(), "Call Center",
                        Toast.LENGTH_SHORT).show();
                Intent iCall = new Intent(Intent.ACTION_DIAL);
                iCall.setData(Uri.parse("tel:081236719270"));
                if (iCall.resolveActivity(getPackageManager()) != null) {
                    startActivity(iCall);
                }
                return true;
            case R.id.action_sms_center:
                Toast.makeText(getApplicationContext(), "SMS Center",
                        Toast.LENGTH_SHORT).show();
                Intent iSMS = new Intent(Intent.ACTION_SENDTO);
                iSMS.setData(Uri.parse("smsto:081236719270"));
                if (iSMS.resolveActivity(getPackageManager()) != null) {
                    startActivity(iSMS);
                }
                return true;
            case R.id.action_lokasi:
                Toast.makeText(getApplicationContext(), "Lokasi",
                        Toast.LENGTH_SHORT).show();
                Intent iLoc = new Intent(Intent.ACTION_VIEW);
                iLoc.setData(Uri.parse("geo:-6.878513, 109.042002, z=21"));
                if (iLoc.resolveActivity(getPackageManager()) != null) {
                    startActivity(iLoc);
                }
                return true;
            case R.id.action_update_user:
                Toast.makeText(getApplicationContext(), "Update User",
                        Toast.LENGTH_SHORT).show();
                Intent iUpUser = new Intent(this, UpdateUserActivity.class);
                startActivity(iUpUser);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}