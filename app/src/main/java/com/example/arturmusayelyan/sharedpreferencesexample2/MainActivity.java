package com.example.arturmusayelyan.sharedpreferencesexample2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private SharedPreferences myPreference;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> historyList;
    private ListView listView;
    private boolean booleanForVisibility;
    private SharedPreferences preferencesForList;
    private String inputedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        booleanForVisibility = true;
        editText = (EditText) findViewById(R.id.editTextForPutting);
        textView = (TextView) findViewById(R.id.textViewForShowing);

        listView = (ListView) findViewById(R.id.history_list);
        historyList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        listView.setAdapter(adapter);
        listView.setVisibility(View.INVISIBLE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                saveText();
                editText.setText("");
                break;
            case R.id.buttonLoad:
                loadText();
                break;
            case R.id.history_btn:
                if (booleanForVisibility) {
                    listView.setVisibility(View.VISIBLE);
                    booleanForVisibility = false;
                    if (historyList != null) {
                        showHistory();
                    }
                } else {
                    listView.setVisibility(View.INVISIBLE);
                    booleanForVisibility = true;
                }
                break;
            default:
                break;
        }
    }

    public void saveText() {
        myPreference = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreference.edit();
        inputedText = editText.getText().toString();
        if (inputedText.length() > 0) {
            editor.putString("Key", inputedText);
            editor.apply();
            Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
//            adapter.add(inputedText);
//            adapter.notifyDataSetChanged();


            savePreferensecForList();
        }
    }

    public void loadText() {
        myPreference = getPreferences(MODE_PRIVATE);
        String savedText = myPreference.getString("Key", "");
        textView.setText(savedText);
        Toast.makeText(getApplicationContext(), "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void showHistory() {
        preferencesForList = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();

        String json = preferencesForList.getString("MyObject", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        historyList = gson.fromJson(json, type);
        if (historyList != null) {
            adapter.addAll(historyList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        savePreferensecForList();
        super.onDestroy();
    }

    public void savePreferensecForList() {
        historyList.add(inputedText);
        preferencesForList = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editorList = preferencesForList.edit();
        Gson gson = new Gson();
        String json = gson.toJson(historyList);
        editorList.putString("MyObject", json);
        editorList.apply();
    }
}

