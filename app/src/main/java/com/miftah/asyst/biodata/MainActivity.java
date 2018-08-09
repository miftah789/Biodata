package com.miftah.asyst.biodata;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.miftah.asyst.biodata.utility.Constant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    EditText etName, etAddress, etTepatLahir, etEmail;
    TextView tvDate;
    ImageView ivDate;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    CheckBox cbBerenang, cbMembaca, cbBerkuda;
    Spinner spinnerStudy;
    Button btnKirim;
    DatePickerDialog datePickerDialog;

    String stringName, stringAddress, stringTempatLahir, stringEmail;
    String stringGender;
    String stringBerenang, stringBerkuda, stringMembaca;
    String hobi;
    String pendidikan;

    ArrayList<String> listPendidikan = new ArrayList<>();
    ArrayList<String> listHobi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAddress = findViewById(R.id.et_address);
        etTepatLahir = findViewById(R.id.et_tempat_lahir);
        etEmail = findViewById(R.id.et_email);
        tvDate = findViewById(R.id.tv_date);
        ivDate = findViewById(R.id.img_view_date);
        rgGender = findViewById(R.id.rg_gender);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        ((RadioButton) findViewById(R.id.rb_male)).setChecked(true);
        cbBerenang = findViewById(R.id.cb_berenang);
        cbBerkuda = findViewById(R.id.cb_berkuda);
        cbMembaca = findViewById(R.id.cb_membaca);
        spinnerStudy = findViewById(R.id.spinner_study);
        btnKirim = findViewById(R.id.btn_kirim);

        spinnerStudy.setOnItemSelectedListener(this);
        rbFemale.setOnCheckedChangeListener(this);
        rbMale.setOnCheckedChangeListener(this);
        cbBerkuda.setOnCheckedChangeListener(this);
        cbBerenang.setOnCheckedChangeListener(this);
        cbMembaca.setOnCheckedChangeListener(this);
        rgGender.setOnCheckedChangeListener(this);


        listPendidikan.add("SD");
        listPendidikan.add("SMP");
        listPendidikan.add("SMA/SMK");
        listPendidikan.add("S1");
        listPendidikan.add("S2");
        listPendidikan.add("S3");
        listPendidikan.add("Tidak Bersekolah");

        ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPendidikan);
        spinnerStudy.setAdapter(pendidikanAdapter);
        btnKirim.setOnClickListener(this);

        Calendar now = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        ivDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kirim:
                stringName = etName.getText().toString();
                stringAddress = etAddress.getText().toString();
                stringTempatLahir = etTepatLahir.getText().toString();
                stringEmail = etEmail.getText().toString();

                Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

                Matcher matcher1 = pattern1.matcher(stringEmail);
                if (TextUtils.isEmpty(stringName)) {
                    etName.setError("Nama Belum Diisi");
                } else if (TextUtils.isEmpty(stringAddress)) {
                    etAddress.setError("Alamat Belum Diisi");
                } else if (TextUtils.isEmpty(stringTempatLahir)) {
                    etTepatLahir.setError("Tempat Lahir Belum Diisi");
                } else if (TextUtils.isEmpty(stringEmail)) {
                    etEmail.setError("Email Belum Diisi");
                } else if (!matcher1.matches()) {
                    etEmail.setError("Format Email Salah");
                } else {

                    hobi = "";
                    for (int i = 0; i < listHobi.size(); i++) {
                        hobi = hobi + " " + listHobi.get(i);
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                    intent.putExtra(Constant.KEY_NAME, etName.getText().toString());
                                    intent.putExtra(Constant.KEY_ADDRESS, etAddress.getText().toString());
                                    intent.putExtra(Constant.KEY_EMAIL, etEmail.getText().toString());
                                    intent.putExtra(Constant.KEY_TEMPAT_lAHIR, etTepatLahir.getText().toString());
                                    intent.putExtra(Constant.KEY_TANGGAL_lAHIR, tvDate.getText().toString());
                                    intent.putExtra(Constant.KEY_GENDER, stringGender);
                                    intent.putExtra(Constant.KEY_HOBI, hobi);
                                    intent.putExtra(Constant.KEY_PENDIDIKAN_AKHIR, pendidikan);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("No", null).show();

                }
                break;
            case R.id.img_view_date:
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_male:
                stringGender = "Laki-laki";
                break;
            case R.id.rb_female:
                stringGender = "Perempuan";
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_berenang:
                stringBerenang = cbBerenang.getText().toString();
                if (isChecked) {
                    listHobi.add("Berenang");
                } else {
                    listHobi.remove("Berenang");
                }
                break;
            case R.id.cb_berkuda:
                stringBerkuda = cbBerkuda.getText().toString();
                if (isChecked) {
                    listHobi.add("Berkuda");
                } else {
                    listHobi.remove("Berkuda");
                }
                break;
            case R.id.cb_membaca:
                stringMembaca = cbMembaca.getText().toString();
                if (isChecked) {
                    listHobi.add("Membaca");
                } else {
                    listHobi.remove("Membaca");
                }
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pendidikan = spinnerStudy.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        tvDate.setText(date);
    }
}
