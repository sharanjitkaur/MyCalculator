package com.sharanjit.mycalculator;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.sourceforge.jeval.Evaluator;


public class ComplexActivity extends ActionBarActivity {
    private Button simple, del;
    private TextView editText;

    private Button sin, cos, tan, log, pi, mod, sq, ob, cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);

        editText = (TextView) findViewById(R.id.editTextC);

        EditText edt = (EditText)findViewById(R.id.editTextC);
        disableSoftInputFromAppearing(edt);

        editText.setText(MyData.appdata.toString());

        simple = (Button) findViewById(R.id.cbsimple);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyData.appdata = editText.getText().toString();

                finish();
            }
        });

        del = (Button) findViewById(R.id.cbdel);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delValue();
            }
        });

        sin = (Button)findViewById(R.id.bsin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( editText.getText() != null && editText.getText().toString().length() > 0 ) {
                    try {
                        String rr = String.valueOf( Math.sin(Math.toRadians(Double.valueOf(editText.getText().toString()))) );
                        rr = setVals(rr);
                        reSetValue(rr);
                    }catch(Exception eer1) {
                        reSetValue("0");
                    }
                }
            }
        });

        cos = (Button)findViewById(R.id.bcos);
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( editText.getText() != null && editText.getText().toString().length() > 0 ) {
                    try {
                        String rr = String.valueOf( Math.cos(Math.toRadians(Double.valueOf(editText.getText().toString()))) );
                        rr = setVals(rr);
                        reSetValue(rr);
                    }catch(Exception eer1) {
                        reSetValue("0");
                    }
                }
            }
        });

        tan = (Button)findViewById(R.id.btan);
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( editText.getText() != null && editText.getText().toString().length() > 0 ) {
                    try {
                        String rr = String.valueOf( Math.tan(Math.toRadians(Double.valueOf(editText.getText().toString()))) );
                        rr = setVals(rr);
                        reSetValue(rr);
                    }catch(Exception eer1) {
                        reSetValue("0");
                    }
                }
            }
        });
/*
        log = (Button)findViewById(R.id.bsin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        pi = (Button)findViewById(R.id.bsin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mod = (Button)findViewById(R.id.bsin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
*/
        sq = (Button)findViewById(R.id.bsq);
        sq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( editText.getText() != null && editText.getText().toString().length() > 0 ) {
                    try {
                        String rr = String.valueOf( Math.sqrt(Double.valueOf(editText.getText().toString())) );
                        rr = setVals(rr);
                        reSetValue(rr);
                    }catch(Exception eer1) {
                        reSetValue("0");
                    }
                }
            }
        });
/*
        ob = (Button)findViewById(R.id.bsin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        cb = (Button)findViewById(R.id.bsin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_complex, menu);
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

    private String setVals(String v){
        String rr = v;
        //rr = String.valueOf( Math.ceil( new Double(v) ) );

        rr = rm0(rr);

        return rr;
    }

    private void setValue(String v){
        editText.setText( editText.getText() + v );
    }

    private void reSetValue(String v){
        editText.setText(v);
    }

    private void delValue(){
        if ( editText.getText() != null && editText.getText().toString().length() > 0 ) {
            if ( editText.getText().toString().length() > 1 ) {
                editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length()-1));
            } else {
                editText.setText("");
            }
        }
    }

    private void Calc() throws Exception {
        Evaluator ev = new Evaluator();
        String rr = ev.evaluate(editText.getText().toString());
        rr = rm0(rr);
        reSetValue(rr);
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
