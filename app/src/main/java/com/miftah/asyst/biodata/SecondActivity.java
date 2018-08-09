package com.miftah.asyst.biodata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miftah.asyst.biodata.utility.Constant;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName, tvAddress, tvTempatLahir, tvEmail, tvTglLahir, tvJenisKelamin, tvHobi, tvPendidikan;
    String name, address, tempatLahir, email, tglLahir, jenisKelamin, hobi, pendidikan;
    Button btnBack;

    EditText etName, etAddress, etTepatLahir, etEmail;
    TextView tvDate;

    String stringName, stringAddress, stringTempatLahir, stringEmail;
    String stringGender;

    ArrayList<String> listHobi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_alamat);
        tvTempatLahir = findViewById(R.id.tv_tempat_lahir);
        tvTglLahir = findViewById(R.id.tv_tgl_lahir);
        tvEmail = findViewById(R.id.tv_email);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin);
        tvHobi = findViewById(R.id.tv_hobi);
        tvPendidikan = findViewById(R.id.tv_pendidikan);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            name = getIntent().getExtras().getString(Constant.KEY_NAME);
            address = getIntent().getExtras().getString(Constant.KEY_ADDRESS);
            tempatLahir = getIntent().getExtras().getString(Constant.KEY_TEMPAT_lAHIR);
            email = getIntent().getExtras().getString(Constant.KEY_EMAIL);
            tglLahir = getIntent().getExtras().getString(Constant.KEY_TANGGAL_lAHIR);
            jenisKelamin = getIntent().getExtras().getString(Constant.KEY_GENDER);
            hobi = getIntent().getExtras().getString(Constant.KEY_HOBI);
            pendidikan = getIntent().getExtras().getString(Constant.KEY_PENDIDIKAN_AKHIR);
        }
        tvName.setText(name);
        tvAddress.setText(address);
        tvTempatLahir.setText(tempatLahir);
        tvTglLahir.setText(tglLahir);
        tvEmail.setText(email);
        tvJenisKelamin.setText(jenisKelamin);
        tvHobi.setText(hobi);
        tvPendidikan.setText(pendidikan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.input_main_menu:
                hobi = "";
                for (int i = 0; i < listHobi.size(); i++) {
                    hobi = hobi + " " + listHobi.get(i);
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SecondActivity.this, EditActivity.class);
                                intent.putExtra(Constant.KEY_NAME, tvName.getText().toString());
                                intent.putExtra(Constant.KEY_ADDRESS, tvAddress.getText().toString());
                                intent.putExtra(Constant.KEY_EMAIL, tvEmail.getText().toString());
                                intent.putExtra(Constant.KEY_TEMPAT_lAHIR, tvTempatLahir.getText().toString());
                                intent.putExtra(Constant.KEY_TANGGAL_lAHIR, tvTglLahir.getText().toString());
                                intent.putExtra(Constant.KEY_GENDER, stringGender);
                                intent.putExtra(Constant.KEY_HOBI, hobi);
                                intent.putExtra(Constant.KEY_PENDIDIKAN_AKHIR, pendidikan);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", null).show();


                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
