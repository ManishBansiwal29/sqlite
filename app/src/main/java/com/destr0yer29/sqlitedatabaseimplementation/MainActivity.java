package com.destr0yer29.sqlitedatabaseimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        ArrayList<Student> students = new ArrayList<>();

        databaseHelper = new DatabaseHelper(this);

        try {
            SQLiteDatabase sqLiteDatabase =databaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query("students",null,null,null,null,null,null);

            if (null != cursor){
                if (cursor.moveToFirst()){
                    for (int i=0;i<cursor.getCount();i++){
                        Student s = new Student();
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String email = cursor.getString(cursor.getColumnIndex("email"));

                        s.setId(id);
                        s.setName(name);
                        s.setEmail(email);

                        students.add(s);

                        cursor.moveToNext();
                    }
                    cursor.close();
                    sqLiteDatabase.close();
                }else{
                    cursor.close();
                    sqLiteDatabase.close();
                }
            }else{
                sqLiteDatabase.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        String text = "";

        for (Student s:students){
            text += s.getId()+" \n"+s.getName()+" \n"+s.getEmail() + " \n******\n";
        }
        textView.setText(text);

    }
}
