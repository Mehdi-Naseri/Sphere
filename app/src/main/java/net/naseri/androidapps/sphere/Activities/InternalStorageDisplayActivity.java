package net.naseri.androidapps.sphere.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.naseri.androidapps.sphere.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InternalStorageDisplayActivity extends AppCompatActivity {

    private EditText etFileName;
    private TextView txvFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_display);

        etFileName = (EditText) findViewById(R.id.etFileName);
        txvFileContent = (TextView) findViewById(R.id.txvFileContent);
    }

    public void readAndDisplayFromFile(View view) {

		/*
		* File Content: "Hello from my file"
		* How Data is Actually Saved: 0110 0011 1001 1100 1111 -1
		* */

        String fileName = etFileName.getText().toString();
        StringBuffer strBuffer = new StringBuffer();

        FileInputStream fis = null;

        try {
            fis = openFileInput(fileName);

            int read;
            while ((read = fis.read()) != -1) {
                strBuffer.append((char)read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        txvFileContent.setText(strBuffer);
    }
}
