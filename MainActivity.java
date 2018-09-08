package com.example.cipher.adamdiary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText enterText;
    private Button Savebutton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//constructors for the Diary

        enterText = (EditText) findViewById(R.id.text2);

        Savebutton = (Button) findViewById(R.id.Sbutton);
        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().equals("")){
                    String data = enterText.getText().toString();

                    writeToFile(data);
                }else{
                    Toast.makeText(getApplicationContext(), "Please Enter Text", Toast.LENGTH_LONG).show();
                }

            }
        });
        if (readFromFile() != null){
            enterText.setText(readFromFile());
        }else{

        }

    }
    private void writeToFile(String MyData){
        try {
            OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(openFileOutput("Diary.txt", Context.MODE_PRIVATE));
            OutputStreamWriter.write(MyData);
            OutputStreamWriter.close();//always close the stream

        }catch(IOException e){
            Log.v("MyActivity",e.toString());
        }
    }
    private String readFromFile(){

        String result = "";

        try{
            InputStream inputStream = openFileInput("Diary.txt");
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String TempString ="";
                StringBuilder stringBuilder = new StringBuilder();
                while ((TempString = bufferedReader.readLine()) != null){
                    stringBuilder.append(TempString);
                }
                inputStream.close();

                result = stringBuilder.toString();


            }

        }catch (FileNotFoundException e){
            Log.v("MyActivity","File Not Found"+ e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;

    }
}
