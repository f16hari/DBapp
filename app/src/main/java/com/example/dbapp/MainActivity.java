package com.example.dbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView eid;
    private TextView ename;
    private TextView esal;
    private Button insert;
    private Button view_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eid = (TextView)findViewById(R.id.eid);
        ename = (TextView)findViewById(R.id.ename);
        esal = (TextView)findViewById(R.id.esal);

        insert = (Button)findViewById(R.id.insert);
        view_all = (Button)findViewById(R.id.view_all);

        db = openOrCreateDatabase("sample", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE table if not exists semployee (eid  varchar, ename varchar,  esal varchar);");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT into semployee values('"+eid.getText()+"','"+ename.getText()+"','"+esal.getText()+"');");
                showMessage("INSERTED");
            }

        });
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c= db.rawQuery("SELECT * from semployee",null);
                StringBuffer buffer = new StringBuffer();
                while(c.moveToNext()){
                    buffer.append("ID: "+c.getString(0)+"\n");
                    buffer.append("name : "+c.getString(1)+"\n");
                    buffer.append("salary : "+c.getString(2)+"\n");
                }
                showMessage(buffer.toString());

            }



        });




    }

    public void showMessage(String data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(data);
        builder.setTitle("sample");
        builder.show();

    }
}
