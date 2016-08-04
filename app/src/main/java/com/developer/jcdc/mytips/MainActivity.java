package com.developer.jcdc.mytips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener , DialogBill.dataBill, DialogPerson.dataPerson {

    private DataBaseManager manager;
    EditText bill, persons;
    RadioGroup rgPercents;
    RadioButton rbTen, rbFifteen, rbTwenty, rbVariable;
    SeekBar skbVariable;
    TextView tvTipIndividual, tvTipTotal, tvPerson, tvPersonTotal, tvIva, tvTotal;
    ImageButton ImBtnMin, ImBtnMax;

    int vPersons;
    int percentTips, percentTipsAux, rbValueDefault;

    boolean showIva;
    int valueIva, orderIva;//string

    boolean showResP, showResPT, showResM, showResMT, showResTT;
    String ResP, ResPT, ResM, ResMT,ResIVA, ResTT;
    boolean defaultSave, orientationValueDefault;

    TableRow trPI, trPT, trMI, trMT, trIVA, trTT;
    int Attempts=1;
    String Msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager=new DataBaseManager(this);

        bill = (EditText) findViewById(R.id.et_bill);
        persons = (EditText) findViewById(R.id.et_nPerson);
        rgPercents = (RadioGroup) findViewById(R.id.rg_percents);
        rbTen = (RadioButton) findViewById(R.id.rb_tenPercent);
        rbFifteen = (RadioButton) findViewById(R.id.rb_fifteenPercent);
        rbTwenty = (RadioButton) findViewById(R.id.rb_twentyPercent);
        rbVariable = (RadioButton) findViewById(R.id.rb_variablePercent);
        skbVariable = (SeekBar) findViewById(R.id.skb_variablePercent);

        tvTipIndividual = (TextView) findViewById(R.id.tv_rTipPerson);
        tvTipTotal = (TextView) findViewById(R.id.tv_rTotalTip);
        tvPerson = (TextView) findViewById(R.id.tv_rbillPerson);
        tvPersonTotal = (TextView) findViewById(R.id.tv_rTotalPerson);
        tvIva = (TextView) findViewById(R.id.tv_rIva);
        tvTotal = (TextView) findViewById(R.id.tv_rGPerson);

        ImBtnMin = (ImageButton) findViewById(R.id.ImBtn_min);
        ImBtnMax = (ImageButton) findViewById(R.id.ImBtn_max);
        trPI = (TableRow) findViewById(R.id.tr_PI);
        trPT = (TableRow) findViewById(R.id.tr_PT);
        trMI = (TableRow) findViewById(R.id.tr_MI);
        trMT = (TableRow) findViewById(R.id.tr_MT);
        trIVA = (TableRow) findViewById(R.id.tr_IVA);
        trTT = (TableRow) findViewById(R.id.tr_TT);

        LoadPreference(null);
        PercentInitial(null);
        ShowResults(null);


        if (!orientationValueDefault) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBill dialogFragment;
                new DialogBill();
                if (bill.getText().toString().equals("")) {
                    dialogFragment = DialogBill.newInstance("0");
                } else {
                    String valueDialogBill = bill.getText().toString();
                    dialogFragment = DialogBill.newInstance(valueDialogBill);
                }

                dialogFragment.show(getFragmentManager(), "DialogBill");
            }
        });
        persons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPerson dialogFragment2;
                new DialogPerson();
                String valueDialogPerson;
                if (persons.getText().toString().equals("1")) {
                    dialogFragment2 = DialogPerson.OtherNewInstance("0");
                } else {
                    valueDialogPerson = persons.getText().toString();
                    dialogFragment2 = DialogPerson.OtherNewInstance(valueDialogPerson);
                }

                dialogFragment2.show(getFragmentManager(), "DialogPerson");
            }
        });

        ImBtnMin.setOnClickListener(OnclickButtonPerson);
        ImBtnMax.setOnClickListener(OnclickButtonPerson);
        rgPercents.setOnCheckedChangeListener(this);
        skbVariable.setOnSeekBarChangeListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage();
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });
    }

    public void LoadPreference(View view) {
        SharedPreferences LPreference = PreferenceManager.getDefaultSharedPreferences(this);
        rbValueDefault = Integer.parseInt(LPreference.getString("defaultPercent", "1"));
        //String x= String.valueOf(rbValueDefault);
        //Toast.makeText(MainActivity.this, x, Toast.LENGTH_LONG).show();
        showIva = LPreference.getBoolean("defaultShowIva", false);
        valueIva = Integer.parseInt(LPreference.getString("defaultValueIva", "15"));
        orderIva = Integer.parseInt(LPreference.getString("defaultOrderIva", "1"));

        showResP = LPreference.getBoolean("defaultShowP", true);
        showResPT = LPreference.getBoolean("defaultShowPT", true);
        showResM = LPreference.getBoolean("defaultShowM", true);
        showResMT = LPreference.getBoolean("defaultShowMT", true);
        showResTT = LPreference.getBoolean("defaultShowTT", true);

        defaultSave = LPreference.getBoolean("defaultSave", false);
        orientationValueDefault = LPreference.getBoolean("defaultOrientation", true);
    }

    public void PercentInitial(View view) {
        switch (rbValueDefault) {
            case 1:
                rbTen.setChecked(true);
                percentTips = 10;
                skbVariable.setVisibility(View.GONE);
                break;
            case 2:
                rbFifteen.setChecked(true);
                percentTips = 15;
                skbVariable.setVisibility(View.GONE);
                break;
            case 3:
                rbTwenty.setChecked(true);
                percentTips = 20;
                skbVariable.setVisibility(View.GONE);
                break;
            case 4:
                rbVariable.setChecked(true);
                skbVariable.setVisibility(View.VISIBLE);
                if (percentTips == 0) {
                    skbVariable.setProgress(10);
                    percentTips = 10;
                    SetPercent();
                }
                break;
            default:
                rbTen.setChecked(true);
                percentTips = 10;
                skbVariable.setVisibility(View.GONE);
                break;
        }
    }

    public void ShowResults(View view) {

        if (showResP) {
            if (trPI.getVisibility() != View.VISIBLE) {
                trPI.isShown();
            }
        } else {
            trPI.setVisibility(View.GONE);
        }
        if (showResPT) {
            if (trPT.getVisibility() != View.VISIBLE) {
                trPT.isShown();
            }
        } else {
            trPT.setVisibility(View.GONE);
        }
        if (showResM) {
            if (trMI.getVisibility() != View.VISIBLE) {
                trMI.isShown();
            }
        } else {
            trMI.setVisibility(View.GONE);
        }
        if (showResMT) {
            if (trMT.getVisibility() != View.VISIBLE) {
                trMT.isShown();
            }
        } else {
            trMT.setVisibility(View.GONE);
        }
        if (showIva) {
            if (trIVA.getVisibility() != View.VISIBLE) {
                trIVA.isShown();
            }
        } else {
            trIVA.setVisibility(View.GONE);
        }
        if (showResTT) {
            if (trTT.getVisibility() != View.VISIBLE) {
                trTT.isShown();
            }
        } else {
            trTT.setVisibility(View.GONE);
        }
    }
    public void SetPercent() {
        rbVariable.setText(percentTips + " %");
    }
    public void CalculateResults() {

        try {

            int Cash = Integer.parseInt(bill.getText().toString());
            int personsx = Integer.parseInt(persons.getText().toString());
            double convertPercent = percentTips / 100.0;
            double ResTip = (Cash) * (convertPercent);
            String xc = String.valueOf(convertPercent);
            Toast.makeText(MainActivity.this, xc, Toast.LENGTH_LONG).show();
            double tipPerson = ResTip / personsx;
            double cashPerson = Cash / personsx;
            double cashTotalPerson = tipPerson + cashPerson;

            double cashTotal = 0;
            double convertIva = valueIva / 100.0;
            double resIva = Cash * convertIva;
            if (showIva) {
                if (orderIva == 1) {
                    cashTotal = Cash + ResTip;
                } else if (orderIva == 2) {
                    cashTotal = Cash + ResTip + resIva;
                }
            } else {
                cashTotal = Cash + ResTip;
            }
            ResP = String.valueOf(tipPerson);
            ResPT = String.valueOf(ResTip);
            ResM = String.valueOf(cashPerson);
            ResMT = String.valueOf(cashTotalPerson);
            ResIVA = String.valueOf(resIva);
            ResTT = String.valueOf(cashTotal);

            DecimalFormat newFormat= new DecimalFormat("0.00");

            tvTipIndividual.setText(newFormat.format(tipPerson));
            tvTipTotal.setText(newFormat.format(ResTip));
            tvPerson.setText(newFormat.format(cashPerson));
            tvPersonTotal.setText(newFormat.format(cashTotalPerson));
            tvIva.setText(newFormat.format(resIva));
            tvTotal.setText(newFormat.format(cashTotal));
            Attempts=1;
            AutoSave();
            /*tvTipIndividual.setText(ResP);
            tvTipTotal.setText(ResPT);
            tvPerson.setText(ResM);
            tvPersonTotal.setText(ResMT);
            tvIva.setText(ResIVA);
            tvTotal.setText(ResTT);*/

           // AutoSave();

        } catch (Exception e) {
            if (Attempts<=3){
                Msj = getResources().getString(R.string.catchError1);
                Toast.makeText(MainActivity.this, Msj, Toast.LENGTH_SHORT).show();
                Attempts = Attempts + 1;
            }
        }
    }

    public void SendMessage(){
        String smsAll=getResources().getString(R.string.sms);
        String txt = String.format(smsAll, ResMT, ResP, ResM);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("txt/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT,txt);

            if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }else {
                startActivity(sendIntent);
            }
        }
        else // For early versions, do what worked for you before.
        {
            Intent smsintentd=new Intent(Intent.ACTION_VIEW);
            smsintentd.setType("vnd.android-dir/mms-sms");
            smsintentd.putExtra("sms_body", txt);
            PackageManager manager=getPackageManager();
            List activities=manager.queryIntentActivities(smsintentd,0);

            boolean isIntentSafe=activities.size()>0;
            //Si existen ejecuta la actividad
            if (isIntentSafe){
                try {
                    startActivity(smsintentd);
                    //finish();
                } catch (Exception e) {
                    Msj=getResources().getString(R.string.catchError3);
                    Toast.makeText(MainActivity.this, Msj, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void AutoSave() {
        /*String T_id = "1";
        manager.Search(T_id);
        if (defaultSave) {
            if () {
                //manager.Update(T_id,T_bill, T_persons,T_percent);
            } else {
                try {
                    String T_bill = bill.getText().toString();
                    String T_persons = persons.getText().toString();
                    String T_percent = String.valueOf(percentTips);
                    manager.Insert(T_bill, T_persons, T_percent);
                    Toast.makeText(MainActivity.this, "Se registro" + T_bill + "y " + T_persons + "c" + T_percent + "", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "e", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if () {
                //manager.Delete(T_id);
            }
        }*/
    }

    private View.OnClickListener OnclickButtonPerson = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vPersons = Integer.parseInt(persons.getText().toString());
            switch (v.getId()) {
                case R.id.ImBtn_min:
                    if (vPersons == 1) {
                        Msj = getResources().getString(R.string.catchError2);
                        Toast.makeText(MainActivity.this, Msj, Toast.LENGTH_SHORT).show();
                    } else {
                        vPersons = vPersons - 1;
                        persons.setText(String.valueOf(vPersons));
                    }
                   /* String x=String.valueOf(percentTips);
                    Toast.makeText(v.getContext(), x, Toast.LENGTH_SHORT).show();*/
                    CalculateResults();
                    break;
                case R.id.ImBtn_max:
                    if (vPersons != 0) {
                        vPersons = vPersons + 1;
                        persons.setText(String.valueOf(vPersons));
                    }
                    /*String z=String.valueOf(percentTips);
                    Toast.makeText(MainActivity.this, z, Toast.LENGTH_SHORT).show();*/
                    CalculateResults();
                    break;
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intentPreference = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intentPreference);
            return true;
        }
        if (id == R.id.action_about) {
            startActivity(new Intent(this, About.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (rbTen.isChecked()) {
            percentTips = 10;
            skbVariable.setVisibility(View.GONE);
            rbVariable.setText(getResources().getString(R.string.r4));
            CalculateResults();
        }
        if (rbFifteen.isChecked()) {
            percentTips = 15;
            skbVariable.setVisibility(View.GONE);
            rbVariable.setText(getResources().getString(R.string.r4));
            CalculateResults();
        }
        if (rbTwenty.isChecked()) {
            percentTips = 20;
            skbVariable.setVisibility(View.GONE);
            rbVariable.setText(getResources().getString(R.string.r4));
            CalculateResults();
        }
        if (rbVariable.isChecked()) {
            skbVariable.setVisibility(View.VISIBLE);
            SetPercent();
            if (percentTips != 0) {
                skbVariable.setProgress(percentTips);
                CalculateResults();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        percentTips = progress;
        percentTipsAux = progress;
        SetPercent();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //String x = String.valueOf(percentTips);
        //Toast.makeText(MainActivity.this, x, Toast.LENGTH_SHORT).show();
        CalculateResults();
    }

    @Override
    public void onFinishEditDialogBill(String text) {
        bill.setText(text);
        if (!text.equals("0")) {
            CalculateResults();
        }
    }

    @Override
    public void onFinishEditDialogPerson(String text) {
        if (text.equals("0")){
           persons.setText("1");
            CalculateResults();
        }else {
            persons.setText(text);
            CalculateResults();
        }
    }
}
