package com.shell.test;

import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ShellTest extends Activity {
    TextView text;
    String input_cmd = new String();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        text = (TextView) findViewById(R.id.text);
        final EditText input = (EditText) findViewById(R.id.editText);


        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                do_exec("ls /data/data/");
            }
        });
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                do_exec("su - ls /data/data");
            }
        });
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                do_exec("ls /data/app");
            }
        });
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                do_exec("ls /sdcard");
            }
        });
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                do_exec("ls /system");
            }
        });
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                do_exec("ls /sd-ext");
            }
        });
        Button test = (Button) findViewById(R.id.test);
        test.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                input_cmd = input.getText().toString();
                if (input_cmd.isEmpty())
                    Toast.makeText(getApplicationContext(), "请输入Shell命令！",
                            Toast.LENGTH_SHORT).show();
                else
                    do_exec(input_cmd);
            }
        });
    }

    String do_exec(String cmd) {
        String s = "/n";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                s += line + "\n";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        text.setText(s);
        return cmd;
    }
}