package net.naseri.androidapps.sphere.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import net.naseri.androidapps.sphere.Models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehdi on 9/24/2017.
 */

public class StudentDAO extends SQLiteOpenHelper {
    public StudentDAO(Context context) {
        super(context, "SphereDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student(id INTEGER PRIMARY KEY,name TEXT NOT NULL,family TEXT,phonenumber INTEGER)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void Insert(Student student) {
        ContentValues contentvalues = student.toContentValues();
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.insert("student",null,contentvalues);

    /*    sqlitedatabase.execSQL("INSERT INTO student values ('"+student.name+""+student.family+""+student.phonenumber+"')");*/
    }

    public List<Student> ReadAll() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery("SELECT * FROM student;",null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String family = cursor.getString(cursor.getColumnIndex("family"));
            int phonenumber = cursor.getInt(cursor.getColumnIndex("phonenumber"));
            Student student = new Student(id,name,family,phonenumber);
            students.add(student);
        }
        return students;
    }

    public void Delete(Student student) {
        SQLiteDatabase database = getWritableDatabase();
        String studentId = Integer.toString(student.id);
        String[] params = { studentId};
        database.delete("student" , "id = ?",params);
    }

    public void Update(Student student,int id) {
        ContentValues contentValues = student.toContentValues();
        SQLiteDatabase database = getWritableDatabase();
        String studentId = Integer.toString(id);
        String[] params = { studentId};
        database.update("student",contentValues,"id = ?",params);
    }

}
