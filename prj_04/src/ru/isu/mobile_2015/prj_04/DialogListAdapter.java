package com.antoshkasaz.prj_04;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DialogListAdapter extends BaseAdapter {
    JSONArray mDialog;
    Context mContext;
    DBHelper dbHelper;
    long user_id;

    public DialogListAdapter(Context context, JSONArray dialog, long userId) {
        mDialog = dialog;
        mContext = context;
        user_id = userId;
    }

    @Override
    public int getCount() {
        return mDialog.length();
    }

    @Override
    public JSONObject getItem(int position) {
        try {
            return mDialog.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        try {
            return mDialog.getJSONObject(position).getLong("uid");
        } catch (JSONException e) {
            Log.d("LOG", "POSITION " + position);
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
