package com.developer.jcdc.mytips;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/*
 * Created by jcac_ on 25/05/2016.
 */
public class Preference extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
