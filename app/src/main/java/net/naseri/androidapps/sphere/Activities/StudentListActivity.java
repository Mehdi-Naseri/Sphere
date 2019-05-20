package net.naseri.androidapps.sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.naseri.androidapps.sphere.DAO.StudentDAO;
import net.naseri.androidapps.sphere.Models.Student;
import net.naseri.androidapps.sphere.R;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        getStudentListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> studentList,View item,int position, long id){
                Student student = (Student) studentList.getItemAtPosition(position);
                Intent intent = new Intent(StudentListActivity.this,StudentCreateActivity.class);
                intent.putExtra("student",student);
                startActivity(intent);
            }
        });

        registerForContextMenu(getStudentListView());
    }

    private ListView getStudentListView() {
        return (ListView) findViewById(R.id.student_list_studentList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadStudents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.equals(getStudentListView())) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            final Student student = (Student) getStudentListView().getItemAtPosition(info.position);

            MenuItem menuItemRemove = menu.add("Remove");
            menuItemRemove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    StudentDAO studentDAO = new StudentDAO(StudentListActivity.this);
                    studentDAO.Delete(student);
                    studentDAO.close();
                    Toast.makeText(StudentListActivity.this, "Removing " + student.name, Toast.LENGTH_SHORT).show();
                    LoadStudents();
                    return true;
                }
            });
        }
    }

    private void LoadStudents() {
        StudentDAO studentDAO = new StudentDAO(this);
        List<Student> students = studentDAO.ReadAll();
        studentDAO.close();

        ListView listViewStudent = (ListView) findViewById(R.id.student_list_studentList);
        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1,students);
        listViewStudent.setAdapter(arrayAdapter);
    }

    public void button_create_click(View view) {
        Intent intent = new Intent(StudentListActivity.this,StudentCreateActivity.class);
        startActivity(intent);
    }

    public void studentList_click(View view){
        Toast.makeText(StudentListActivity.this, "studentList Clicked", Toast.LENGTH_SHORT).show();
    }
}
