package com.example.arturmusayelyan.sharedpreferencesexample2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private SharedPreferences myPreference;

    private ArrayAdapter<String> adapter;
    private List<String> historyList;
    private ListView listView;
    private boolean booleanForVisibility;

    private ArrayList<String> listItems;
    private List<String> items;
    SharedPreferences preferences;

    @Override
    //https://www.youtube.com/watch?v=H92nFax2cws
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booleanForVisibility = true;
        editText = (EditText) findViewById(R.id.editTextForPutting);
        textView = (TextView) findViewById(R.id.textViewForShowing);



        preferences = getSharedPreferences("PREFS", 0);
        String wordsString = preferences.getString("words", "");
        if (wordsString.length() != 0) {
            historyList = new ArrayList<>();
            String[] itemsWords = wordsString.split(",");

            items = new ArrayList<>();
            for (int i = 0; i < itemsWords.length; i++) {
                historyList.add(itemsWords[i]);
            }
        } else {
            historyList = new ArrayList<>();
        }



        listView = (ListView) findViewById(R.id.history_list);
        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listView.setVisibility(View.INVISIBLE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                String parametr = editText.getText().toString();
                saveText(parametr);
                break;
            case R.id.buttonLoad:
                loadText();
                break;
            case R.id.history_btn:
                if (booleanForVisibility) {
                    listView.setVisibility(View.VISIBLE);
                    booleanForVisibility = false;
                    // showHistory();
                    getSavedList();
                } else {
                    listView.setVisibility(View.INVISIBLE);
                    booleanForVisibility = true;
                }
                break;
            case R.id.clean_btn:
//                preferences = getSharedPreferences("PREFS", 0);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.apply();
                getApplicationContext().getSharedPreferences("PREFS", 0).edit().clear().commit();

                historyList.clear();
                Toast.makeText(this, "You clean SharedPreferences", Toast.LENGTH_SHORT).show();
                textView.setText("");
                adapter.clear();
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    public void saveText(String inputedText) {
        booleanForVisibility = true;
        Log.d("Art_Log", "saveText worked");
        if (inputedText.length() > 0) {
            myPreference = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = myPreference.edit();
            editor.putString("Key", inputedText);
            editor.apply();
            Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
            historyList.add(inputedText);
            editText.setText("");
            saveList();
        } else {
            Toast.makeText(this, "You dont save anything", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadText() {
        Log.d("Art_Log", "loadText worked");
        myPreference = getPreferences(MODE_PRIVATE);
        String savedText = myPreference.getString("Key", "");
        if (savedText.length() > 0) {
            textView.setText(savedText);
            Toast.makeText(getApplicationContext(), "Text loaded", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SharedPreferences is empty", Toast.LENGTH_SHORT).show();
            textView.setText("");
        }
    }

    public void showHistory() {
        // Log.d("Art_Log", "showHistory worked");
        //if (booleanForCheckSave) {
        Log.d("Art_Log", "showHistory worked");
        myPreference = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = myPreference.getString("MyObjects", "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        //String odjects[] = new String[]{gson.fromJson(json, type)};
        ArrayList<String> localList = gson.fromJson(json, type);
        System.out.println("localList: " + localList + " json: " + json + " gson:  " + gson.fromJson(json, type));
//            for (String odject : odjects) {
//                adapter.add(odject);
//                adapter.notifyDataSetChanged();
//          //  }
        for (int i = 0; i < localList.size(); i++) {
            adapter.add(localList.get(i));
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        Log.d("Art_Log", "onDestroy worked");
        //savePreferensecForList();
        saveList();
        super.onDestroy();
    }

    public void savePreferensecForList() {
        Log.d("Art_Log", "savePreferensecForList worked");
        // booleanForCheckSave = true;
        myPreference = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editorList = myPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(historyList);
        editorList.putString("MyObject", json);
        editorList.apply();
    }

    public void saveList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : historyList) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        preferences = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("words", stringBuilder.toString());
        editor.commit();
    }

    public List<String> getSavedList() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        preferences = getSharedPreferences("PREFS", 0);
        String wordsString = preferences.getString("words", "");
        String[] itemsWords = wordsString.split(",");

        items = new ArrayList<>();
        for (int i = 0; i < itemsWords.length; i++) {
            items.add(itemsWords[i]);
        }
        adapter.addAll(items);
        adapter.notifyDataSetChanged();
        return items;
    }
}

