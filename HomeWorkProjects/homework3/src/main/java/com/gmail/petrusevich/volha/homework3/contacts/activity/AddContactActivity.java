package com.gmail.petrusevich.volha.homework3.contacts.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gmail.petrusevich.volha.homework3.R;


public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_NAME = "name";
    public static final String KEY_DATA = "dataContact";

    public static Intent newIntent(Context context) {
        return new Intent(context, AddContactActivity.class);
    }

    private Button buttonBack;
    private EditText editDataContact;
    private EditText editName;
    private Button checkButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        findView();
        setClick();
        clickRadioGroup();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.check_button:
                Intent intent = new Intent();
                intent.putExtra(KEY_NAME, editName.getText().toString());
                intent.putExtra(KEY_DATA, editDataContact.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
        }
    }

    private void findView() {
        buttonBack = findViewById(R.id.back_button);
        editDataContact = findViewById(R.id.data_edit);
        checkButton = findViewById(R.id.check_button);
        editName = findViewById(R.id.name_edit_text);
        radioGroup = findViewById(R.id.group_button);
    }

    private void setClick() {
        buttonBack.setOnClickListener(this);
        checkButton.setOnClickListener(this);
    }

    private void clickRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.phone_button:
                        editDataContact.setInputType(InputType.TYPE_CLASS_PHONE);
                        editDataContact.setHint(R.string.phone);
                        break;
                    case R.id.email_button:
                        editDataContact.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        editDataContact.setHint(R.string.email);
                        break;
                }
            }
        });
    }

}
