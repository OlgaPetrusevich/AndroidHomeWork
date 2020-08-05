package com.gmail.petrusevich.volha.homework3.contacts.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gmail.petrusevich.volha.homework3.R;
import com.gmail.petrusevich.volha.homework3.contacts.listcontacts.Contacts;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonBack;
    private Button buttonRemove;
    private Button buttonCheck;
    private Contacts contacts;
    private EditText nameEditText;
    private EditText phoneEdit;
    private Boolean isContact;
    public static final String KEY_EDIT = "contact";
    public static final String KEY_REMOVE = "removeContact";

    public static Intent newIntent(Context context, Contacts contact) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(KEY_EDIT, contact);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        findView();
        setClick();
        editText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.remove_button:
                removeContact();
                break;
            case R.id.check_button:
                saveChanges();
                break;
            default:
                break;
        }
    }

    private void findView() {
        buttonBack = findViewById(R.id.back_button);
        buttonRemove = findViewById(R.id.remove_button);
        buttonCheck = findViewById(R.id.check_button);
        nameEditText = findViewById(R.id.name_edit_text);
        phoneEdit = findViewById(R.id.phone_edit);
    }

    private void editText() {
        contacts = getIntent().getParcelableExtra(KEY_EDIT);
        if (contacts != null) {
            nameEditText.setText(contacts.getName());
            phoneEdit.setText(contacts.getContactData());
        }
    }

    private void setClick() {
        buttonBack.setOnClickListener(this);
        buttonRemove.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);
    }

    private void removeContact(){
        isContact = false;
        Intent intent1 = new Intent();
        intent1.putExtra(KEY_REMOVE, isContact);
        setResult(RESULT_OK, intent1);
        finish();
    }

    private void saveChanges(){
        contacts.setName(nameEditText.getText().toString());
        contacts.setContactData(phoneEdit.getText().toString());
        Intent intent = new Intent();
        intent.putExtra(KEY_EDIT, contacts);
        setResult(RESULT_OK, intent);
        finish();
    }
}
