package com.example.demo.designpattern.compositepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.myapplication.R;

public class MainTestActivity extends AppCompatActivity{
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

        test();

    }

    private void test(){
        Folder folder_all = new Folder("folder_all");
        File textfile_1 = new TextFile("textfile_1");
        File imagefile_1 = new ImageFile("imagefile_1");
        File textfile_2 = new TextFile("textfile_2");
        File imagefile_2 = new ImageFile("imagefile_2");

        folder_all.addFile(textfile_1);
        folder_all.addFile(imagefile_1);
        folder_all.addFile(textfile_2);
        folder_all.addFile(imagefile_2);

        folder_all.display();

        folder_all.removeFile(textfile_2);
        folder_all.display();
    }
}
