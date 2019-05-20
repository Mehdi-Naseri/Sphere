package net.naseri.androidapps.sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import net.naseri.androidapps.sphere.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
          case R.id.menu_Gallery:
                return true;
            case R.id.menu_CacheStorage:
                displayCashStorageLayout();
                return true;
            case R.id.menu_Students_CRUD:
                displayCrudLayout();
                return true;
            case R.id.menu_About:
                displayAboutLayout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
      }
    }

    public void main_button_CacheStorage_Click(View view) {
        displayCashStorageLayout();
    }
    public void main_button_InternalStorage_Click(View view) {
        displayInternalStorageLayout();
    }
    public void main_button_ExternalStorage_Click(View view) {
        displayExternalStorageLayout();
    }
    public void main_button_Crud_Click(View view) {
        displayCrudLayout();
    }
    public void main_button_About_Click(View view) {
        displayAboutLayout();
    }


    private void displayCashStorageLayout() {
        Intent intent = new Intent(MainActivity.this,CacheStorageActivity.class);
        startActivity(intent);
    }
    private void displayInternalStorageLayout() {
        Intent intent = new Intent(MainActivity.this,InternalStorageActivity.class);
        startActivity(intent);
    }
    private void displayExternalStorageLayout() {
        Intent intent = new Intent(MainActivity.this,ExternalStorageActivity.class);
        startActivity(intent);
    }
    private void displayCrudLayout() {
        Intent intent = new Intent(MainActivity.this,StudentListActivity.class);
        startActivity(intent);
    }
    public void displayAboutLayout(){
        Intent intent = new Intent(MainActivity.this,AboutActivity.class);
        startActivity(intent);
/*        LayoutInflater layoutInflater = getLayoutInflater();
        View view1 = layoutInflater.inflate(R.layout.activity_about,null);
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view1);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();*/
    }



}
