package com.example.ren95.lab3_shakurov_renat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tx2;
    TextView tx1;
    String str,str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    static String NeprimimtMP(String str) {
        String result = "";
        ArrayList<Character> stack = new ArrayList<Character>();
        stack.add('X');
        str=str+"$";
        for (int i = 0; i <= str.length(); i++) {

            if (str.charAt(i) == '1' && stack.get(stack.size() - 1) == 'X') {
                stack.add('Z');
                result += "1";
                continue;
            }
            if (str.charAt(i) == '1' && stack.get(stack.size() - 1) == 'Z') {
                result += "1";
                continue;
            }
            if (str.charAt(i) == '0' && stack.get(stack.size() - 1) == 'X') {
                break;
            }
            if (str.charAt(i) == '1' && stack.get(stack.size() - 1) == 'A') {
                break;
            }

            if (str.charAt(i) == '0' && stack.get(stack.size() - 1) == 'Z') {
                stack.remove(stack.size()-1);
                stack.add('A');
                continue;
            }
            if (str.charAt(i) == '0' && stack.get(stack.size() - 1) == 'A') {
                stack.add('A');
                continue;
            }
            if(str.charAt(i)=='$'&&stack.get(stack.size()-1)=='A'){
                if (i == str.length()) {
                    while (stack.get(stack.size() - 1) != 'X') {
                        //  if (stack[stack.Count - 1] == "Z") { stack.Remove("Z"); continue; }
                        if (stack.get(stack.size() - 1) == 'A') {
                            stack.remove(stack.size() - 1);
                            result += "22";
                            continue;
                        }
                    }
                    return result;
                }
            }
            if(str.charAt(i)=='$'&&stack.get(stack.size()-1)=='Z'){
                break;
            }
            if(str.charAt(i)=='$'&&stack.get(stack.size()-1)=='X'){
break;
            }
        }
        return "false";
    }


    public void pressButton2(View view) {

        TextView tx2 = (TextView) findViewById(R.id.textView2);
        EditText ed2 = (EditText) findViewById(R.id.editText2);
        str = ed2.getText().toString();
        tx2.setText(NeprimimtMP(str));
    }

    public void pressButton1(View view) {
        EditText ed1 = (EditText) findViewById(R.id.editText1);
        TextView tx1 = (TextView) findViewById(R.id.textView1);
      str1 = ed1.getText().toString();
        int station = 1;
        ArrayList<Character> stack = new ArrayList<Character>();
        stack.add('X');
        while(!str1.equals("")) {
            if (station == 1 && str1.charAt(0) == '1' && stack.get(stack.size() - 1) == 'X') {
                stack.add('A');
                str1 = str1.substring(1);
                continue;
            }
            if (station == 1 && str1.charAt(0) == '1' && stack.get(stack.size() - 1) == 'A') {
                stack.add('A');
                str1 = str1.substring(1);
                continue;
            }
            if (station == 1 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'A') {
                station = 2;
                continue;
            }
            if (station == 1 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'X') {
                break;
            }

            if (station == 2 && str1.charAt(0) == '1' && stack.get(stack.size() - 1) == 'A') {
                break;
            }
            if (station == 2 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'X') {
                break;
            }
            if (station == 2 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'A') {
                stack.remove(stack.size() - 1);
                str1 = str1.substring(1);
                continue;
            }
            if (station == 2 && str1.charAt(0) == '1' && stack.get(stack.size() - 1) == 'X') {
                station = 3;
                continue;
            }

            if (station == 3 && str1.charAt(0) == '1' && stack.get(stack.size() - 1) == 'X') {
                stack.add('A');
                str1 = str1.substring(1);
                continue;
            }
            if (station == 3 && str1.charAt(0) == '1' && stack.get(stack.size() - 1) == 'A') {
                stack.add('A');
                str1 = str1.substring(1);
                continue;
            }
            if (station == 3 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'A') {
                station = 4;
                continue;
            }
            if (station == 3 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'X') {
                break;
            }
            if (str1.length() > 0) {
                if (station == 4 && str1.charAt(0) == '1') {
                    tx1.setText("Error");
                    break;
                }
                if (station == 4 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'A') {
                    stack.remove(stack.size() - 1);
                    str1 = str1.substring(1);
                    continue;
                }
                if (station == 4 && str1.charAt(0) == '0' && stack.get(stack.size() - 1) == 'X') {
                    tx1.setText("Error");
                    break;
                }
                if (!"01".contains(str1)) {
                    break;
                }
            }
        }
        if (stack.get(stack.size() - 1) == 'X' && str1.equals("")&&station==4) { tx1.setText("True"); }

            if (stack.get(stack.size() - 1) == 'A' || !str1.equals("")  || station != 4) {
                tx1.setText("Error");
            }


    }
}



