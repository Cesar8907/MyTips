package com.developer.jcdc.mytips;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;



/*
 * Created by jcac_ on 25/05/2016.
 */
public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Preference()).commit();
    }
}
