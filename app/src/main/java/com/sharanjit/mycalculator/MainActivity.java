package com.sharanjit.mycalculator;

import android.content.Intent;
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
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.math.Sin;
import net.sourceforge.jeval.function.string.Eval;

public class MainActivity extends ActionBarActivity {
    private Button complex, del;
    private Button b7, b8, b9, bd;
    private Button b4, b5, b6, bm;
    private Button b1, b2, b3, bminus;
    private Button bdot, b0, bequal, bplus;
    private TextView editText;

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
                    Calc();
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
                //reSetValue(MathEval.calculate(editText.getText().toString()));
                try {
                    Calc();
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
    }

    private void setValue(String v){
        editText.setText( editText.getText() + v );
    }

    private void reSetValue(String v){
        editText.setText( v );
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
