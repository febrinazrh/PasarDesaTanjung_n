package com.example.pasardesatanjung;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PembayaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        TextView pemb = (TextView) findViewById(R.id.totalpembayaran);

        getIntent().getExtras();
        pemb.setText(getIntent().getStringExtra("data1"));

    }
    private static final String TAG_ACTIVITY = PembayaranActivity.class.getSimpleName();
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    displayToast(getString(R.string.chosen) + getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    displayToast(getString(R.string.chosen) + getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    displayToast(getString(R.string.chosen) + getString(R.string.pick_up));
                break;
            default:
                Log.d(TAG_ACTIVITY, getString(R.string.nothing_clicked));
                break;
        }
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void Selesai(View view){
        EditText sinput = findViewById(R.id.inputBayar);
        TextView back = findViewById(R.id.kembalian);
        String data = getIntent().getExtras().getString("data2");
        Integer data1 = Integer.parseInt(data.replaceAll("[\\D]",""));
        Integer minput = Integer.parseInt(sinput.getText().toString());
        if (minput > data1){
            Integer hasil = minput - data1;
            back.setText("Kembalian = Rp."+(hasil));
        }else if (data1 > minput){
            Integer hasil = data1 - minput;
            back.setText("Pembayaran kurang = Rp."+(hasil));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pembayaran Selesai");
        builder.setMessage("Terima Kasih ~");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}