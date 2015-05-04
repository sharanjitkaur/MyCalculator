package com.sharanjit.mycalculator;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private Button complex, del;
    private Button b7, b8, b9, bd;
    private Button b4, b5, b6, bm;
    private Button b1, b2, b3, bminus;
    private Button bdot, b0, bequal, bplus;
    private TextView editText;

    int flag = 0;
    String no1 = "";
    String no2 = "";
    String op = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (TextView) findViewById(R.id.editText);

        EditText edt = (EditText)findViewById(R.id.editText);
        disableSoftInputFromAppearing(edt);

        complex = (Button) findViewById(R.id.bcomplex);
        complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if ( flag == 3 ) {
                        Calc1();
                    }
                    if ( flag == 2 ) {
                        editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
                        flag = 1;
                        op = "";
                        no2 = "";
                    }
                } catch(Exception eer1) {
                    reSetValue("");
                }
                MyData.appdata = editText.getText().toString();

                Intent intent = new Intent(MainActivity.this, ComplexActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        del = (Button) findViewById(R.id.bdel);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delValue();
            }
        });

        b0 = (Button) findViewById(R.id.b0);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("0");
            }
        });

        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("1");
            }
        });

        b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("2");
            }
        });

        b3 = (Button) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("3");
            }
        });

        b4 = (Button) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("4");
            }
        });

        b5 = (Button) findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("5");
            }
        });

        b6 = (Button) findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("6");
            }
        });

        b7 = (Button) findViewById(R.id.b7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("7");
            }
        });

        b8 = (Button) findViewById(R.id.b8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("8");
            }
        });

        b9 = (Button) findViewById(R.id.b9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("9");
            }
        });

        bdot = (Button) findViewById(R.id.bdot);
        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(".");
            }
        });


        bd = (Button) findViewById(R.id.bd);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("/");
            }
        });

        bm = (Button) findViewById(R.id.bm);
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("*");
            }
        });

        bminus = (Button) findViewById(R.id.bminus);
        bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("-");
            }
        });

        bplus = (Button) findViewById(R.id.bplus);
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue("+");
            }
        });

        bequal = (Button) findViewById(R.id.bequal);
        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    flag = 0;
                    Calc1();
                }catch(Exception eer1) {
                    reSetValue("Error");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        editText.setText(MyData.appdata.toString());
        flag = 1;
        no1 = editText.getText().toString();
        op = "";
        no2 = "";
    }

    private void setValue(String v){
        //editText.setText( editText.getText() + v );
        int opflag = 0;
        int showv = 0;

        if ( v.equals("+") || v.equals("-") || v.equals("/") || v.equals("*") ) {
            opflag = 1;
        }

        if ( (flag == 0 || flag == 1) && opflag == 0 ) {
            flag = 1;
            no1 = no1 + "" + v;
            showv = 1;
        }
        if ( flag == 1 && opflag == 1 ) {
            flag = 2;
            if (v.equals("+")) {
                op = "+";
            }
            if (v.equals("-")) {
                op = "-";
            }
            if (v.equals("*")) {
                op = "*";
            }
            if (v.equals("/")) {
                op = "/";
            }
            showv = 1;
        }
        if ( (flag == 2 || flag == 3) && opflag == 0 ) {
            flag = 3;
            no2 = no2 + "" + v;
            showv = 1;
        }

        if ( showv == 1 ) {
            editText.setText( editText.getText() + v );
        }
        Log.d("soni", "setvalue[no1: " + no1 + ", op: " + op + ", no2: " + no2 + ", flag: " + flag + "]");
    }

    private void reSetValue(String v){
        editText.setText(v);
    }

    private void delValue(){
        if ( editText.getText() != null && editText.getText().toString().length() > 0 ) {
            if ( editText.getText().toString().length() > 1 ) {
                if ( editText.getText().toString().equalsIgnoreCase("error") ) {
                    editText.setText("");
                    flag = 0;
                    no1 = "";
                    no2 = "";
                    op = "";
                } else {

                    editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
                    if ( flag == 1 ) {
                        no1 = no1.substring(0, no1.length() - 1);
                        if ( no1.trim().length() == 0 ) {
                            flag = 0;
                        }
                    }
                    if ( flag == 2 ) {
                        flag = 1;
                        op = "";
                    }
                    if ( flag == 3 ) {
                        no2 = no2.substring(0, no2.length() - 1);

                        if ( no2.trim().length() == 0 ) {
                            flag = 2;
                        }
                    }
                }
            } else {
                editText.setText("");
                flag = 0;
                no1 = "";
                no2 = "";
                op = "";
            }
        }
        Log.d("soni","delvalue[no1: "+no1+", op: " + op + ", no2: " + no2 + ", flag: " + flag +"]");
    }

    private void Calc1() throws Exception {
        double d1 = 0;
        double d2 = 0;
        double ans = 0;
        String sans = "";
        Log.d("soni","calc1[no1: "+no1+", op: " + op + ", no2: " + no2 + ", flag: " + flag +"]");

        d1 = Double.valueOf(no1);
        d2 = Double.valueOf(no2);
        if ( op.equals("+") ) {
            ans = d1 + d2;
        }
        if ( op.equals("-") ) {
            ans = d1 - d2;
        }
        if ( op.equals("*") ) {
            ans = d1 * d2;
        }
        if ( op.equals("/") ) {
            ans = d1 / d2;
        }

        sans = String.valueOf(ans);

        sans = rm0(sans);

        reSetValue(sans);

        flag = 1;
        no1 = sans;
        no2 = "";
        op = "";
    }

    //If ends with ".0", remove ".0"
    private String rm0(String pVal) {
        String rv = pVal;

        if ( pVal != null && pVal.length() > 1 && pVal.endsWith(".0") ) {
            rv = pVal.substring(0, pVal.length()-2);
        }

        return rv;
    }

}
