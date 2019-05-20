package net.naseri.androidapps.sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.naseri.androidapps.sphere.DAO.StudentDAO;
import net.naseri.androidapps.sphere.Models.Student;
import net.naseri.androidapps.sphere.R;

public class StudentCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if(student != null) {
            FillForm(student);
        }
    }

    private void FillForm(Student student) {
        EditText editTextName =(EditText) this.findViewById(R.id.student_create_name);
        editTextName.setText(student.name);
        EditText editTextFamily =(EditText) this.findViewById(R.id.student_create_family);
        editTextFamily.setText(student.family);
        EditText editTextPhoneNumber =(EditText) this.findViewById(R.id.student_create_phonenumber);
        editTextPhoneNumber.setText(Integer.toString(student.phonenumber));
    }

    public void button_save_click(View view) {
        String name = ((EditText) findViewById(R.id.student_create_name)).getText().toString();
        String family = ((EditText) findViewById(R.id.student_create_family)).getText().toString();
        int phonenumber = Integer.parseInt(((EditText) findViewById(R.id.student_create_phonenumber)).getText().toString());
        int StudentId = ((Student) getIntent().getSerializableExtra("student")).id;

        Student student = new Student(name,family,phonenumber);
        StudentDAO studentDAO = new StudentDAO(this);

        if(getIntent().hasExtra("student")){
            studentDAO.Update(student,StudentId);
        }
        else{
            studentDAO.Insert(student);
        }
        studentDAO.close();

        Toast.makeText(this, "'"+name+"' was saved.", Toast.LENGTH_SHORT).show();
        finish();
    }

}
