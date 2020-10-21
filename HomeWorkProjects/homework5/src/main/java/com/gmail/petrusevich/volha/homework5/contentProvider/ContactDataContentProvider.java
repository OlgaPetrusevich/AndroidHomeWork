package com.gmail.petrusevich.volha.homework5.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gmail.petrusevich.volha.homework5.database.ContactDao;
import com.gmail.petrusevich.volha.homework5.database.ContactDatabase;

public class ContactDataContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.gmail.petrusevich.volha.homework5";
    private static final UriMatcher uriMatcher;
    private static final int URI_CODE = 1;
    private ContactDao contactDao;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "data/data", URI_CODE);
    }


    @Override
    public boolean onCreate() {
        contactDao = ContactDatabase.Companion.getInstance(getContext()).getContactDao();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == URI_CODE) {
            cursor = contactDao.getAll();
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
