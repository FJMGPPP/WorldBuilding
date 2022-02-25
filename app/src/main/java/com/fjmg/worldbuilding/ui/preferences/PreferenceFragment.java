package com.fjmg.worldbuilding.ui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.fjmg.worldbuilding.R;

public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener
{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (key.equals(getString(R.string.key_rigtone)))
        {
            ListPreference listPreference = (ListPreference)preference;
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key,""));
            if(index >= 0)
            {
                preference.setSummary(listPreference.getValue());
            }
            else
            {
                preference.setSummary(sharedPreferences.getString(key,""));
            }
        }
        if (key.equals(getString(R.string.iconos)))
        {
            ListPreference listPreference = (ListPreference)preference;
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key,""));
            if(index >= 0)
            {
                preference.setSummary(listPreference.getValue());
            }
            else
            {
                preference.setSummary(sharedPreferences.getString(key,""));
            }
        }
        if (key.equals(getString(R.string.coloresNotification)))
        {
            ListPreference listPreference = (ListPreference)preference;
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key,""));
            if(index >= 0)
            {
                preference.setSummary(listPreference.getValue());
            }
            else
            {
                preference.setSummary(sharedPreferences.getString(key,""));
            }
        }
    }
}