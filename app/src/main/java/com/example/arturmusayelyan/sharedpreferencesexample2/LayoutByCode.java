package com.example.arturmusayelyan.sharedpreferencesexample2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by artur.musayelyan on 24/10/2017.
 */

public class LayoutByCode extends AppCompatActivity {
    private RelativeLayout main;
    private TextView tvMessage, tvUserName, tvPassword;
    private EditText etUserName, etPassword;
    private Button btnLogin;

    private RelativeLayout.LayoutParams tvMessageDimensions, tvUserNameDimensions, etUserNameDimensions, tvPasswordDimensions, etPasswordDimensions, btnLoginDimensions;

    private int tvMessageId = 1, tvUserNameId = 2, etUserNameId = 3, tvPasswordId = 4, etPasswordId = 5, btnLoginId = 6;
    private int paddingValue = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        createMessageTextView();
        createUserNameTextView();
        createUserNameEditText();
        createPasswordTextView();
        createPasswordEditText();
        createLoginButton();

        main.addView(tvMessage, tvMessageDimensions);
        main.addView(tvUserName, tvUserNameDimensions);
        main.addView(etUserName, etUserNameDimensions);
        main.addView(tvPassword, tvPasswordDimensions);
        main.addView(etPassword, etPasswordDimensions);
        main.addView(btnLogin, btnLoginDimensions);

        setContentView(main);
    }

    private void init() {
        main = new RelativeLayout(this); //ViewGroup object
        RelativeLayout.LayoutParams mainDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //main.setBackgroundColor(Color.BLUE);
        main.setLayoutParams(mainDimensions);

        tvMessage = new TextView(this);
        tvUserName = new TextView(this);
        etUserName = new EditText(this);
        tvPassword = new TextView(this);
        etPassword = new EditText(this);
        btnLogin = new Button(this);
    }

    private void createMessageTextView() {
        tvMessageDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvMessageDimensions.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tvMessage.setText("Please login first");
        tvMessage.setId(tvMessageId);

        tvMessage.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
    }

    private void createUserNameTextView() {
        tvUserNameDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvUserNameDimensions.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tvUserNameDimensions.addRule(RelativeLayout.BELOW, tvMessageId);
        tvUserName.setText("User Name");
        tvUserName.setId(tvUserNameId);

        tvUserName.setPadding(paddingValue, 100, paddingValue, paddingValue);
    }

    private void createUserNameEditText() {
        etUserNameDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        etUserNameDimensions.addRule(RelativeLayout.BELOW, tvMessageId);
        etUserNameDimensions.addRule(RelativeLayout.RIGHT_OF, tvUserNameId);
        etUserNameDimensions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        etUserName.setId(etUserNameId);

        etUserNameDimensions.addRule(RelativeLayout.ALIGN_BASELINE, tvUserNameId);
    }

    private void createPasswordTextView() {
        tvPasswordDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvPasswordDimensions.addRule(RelativeLayout.BELOW, etUserNameId);
        tvPasswordDimensions.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tvPasswordDimensions.addRule(RelativeLayout.RIGHT_OF, tvUserNameId);
        tvPassword.setText("Password");
        tvPassword.setId(tvPasswordId);

        tvPassword.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
        tvPassword.setGravity(Gravity.RIGHT);
        tvPasswordDimensions.addRule(RelativeLayout.ALIGN_RIGHT,tvUserNameId);
    }

    private void createPasswordEditText() {
        etPasswordDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        etPasswordDimensions.addRule(RelativeLayout.BELOW, etUserNameId);
        etPasswordDimensions.addRule(RelativeLayout.RIGHT_OF, tvPasswordId);
        etPasswordDimensions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        etPassword.setId(etPasswordId);

        etPasswordDimensions.addRule(RelativeLayout.ALIGN_BASELINE, tvPasswordId);
    }

    private void createLoginButton() {
        btnLoginDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnLoginDimensions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btnLoginDimensions.addRule(RelativeLayout.BELOW, etPasswordId);
        btnLogin.setText("Login");
        btnLogin.setId(btnLoginId);
    }
}
