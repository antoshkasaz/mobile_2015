package com.antonsazonov.prj_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button buttonAdd, buttonClear;
    ListView listView;
    DBHelper dbHelper;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        editText = (EditText) findViewById(R.id.editText);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.buttonAdd:
                ContentValues cv = new ContentValues();
                String text = editText.getText().toString();
                editText.setText("");
                cv.put("text", text);
                cv.put("done", 0);
                db.insert("tasks", null, cv);
                break;
            case R.id.buttonClear:
                db.delete("tasks", null, null);
                break;
        }
        Cursor c = db.query("tasks", null, null, null, null, null, null);
        adapter = new MyAdapter(
                this,
                R.layout.item,
                c,
                new String[]{"text", "done"},
                new int[]{R.id.textViewItem, R.id.checkBoxItem},
                0
        );
        listView.setAdapter(adapter);
        dbHelper.close();
    }

    class MyAdapter extends SimpleCursorAdapter {
        Cursor cursor;
        Context context;
        String[] from;
        int[] to;

        public MyAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.context = context;
            this.cursor = c;
            this.from = from;
            this.to = to;
        }

        class ViewTemp implements View.OnClickListener {
            int pos;
            TextView textView;
            CheckBox checkBox;

            @Override
            public void onClick(View v) {
                int flag = 0;
                if (checkBox.isChecked()) {
                    flag = 1;
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("text", textView.getText().toString());
                cv.put("done", flag);
                db.update("tasks", cv, "_id = ?", new String[]{pos + ""});
                db.close();
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewTemp viewTemp;
            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.item, null, true);
                viewTemp = new ViewTemp();
                viewTemp.textView = (TextView) itemView.findViewById(R.id.textViewItem);
                viewTemp.checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxItem);
                itemView.setTag(viewTemp);
            } else {
                viewTemp = (ViewTemp) itemView.getTag();
            }
            if (cursor.moveToPosition(position)) {
                viewTemp.textView.setText(cursor.getString(cursor.getColumnIndex("text")) + "");
                if (cursor.getInt(cursor.getColumnIndex("done")) > 0) {
                    viewTemp.checkBox.setChecked(true);
                }
            }
            viewTemp.pos = cursor.getInt(cursor.getColumnIndex("_id"));
            viewTemp.checkBox.setOnClickListener(viewTemp);
            return itemView;
        }
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "tasks.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table tasks ("
                    + "_id integer primary key autoincrement,"
                    + "text text not null,"
                    + "done int" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
