package com.example.inverseli;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class Fragment_3x3 extends Fragment {
    EditText a11;
    EditText a12;
    EditText a13;
    EditText a21;
    EditText a22;
    EditText a23;
    EditText a31;
    EditText a32;
    EditText a33;
    Button calculate;
    Button clear;
    TextView result;
    double[] arr = new double[9];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fraglayout_3x3, container, false);
        a11 = v.findViewById(R.id.editTextNumberDecimal9);
        a12 = v.findViewById(R.id.editTextNumberDecimal8);
        a13 = v.findViewById(R.id.editTextNumberDecimal7);
        a21 = v.findViewById(R.id.editTextNumberDecimal5);
        a22 = v.findViewById(R.id.editTextNumberDecimal4);
        a23 = v.findViewById(R.id.editTextNumberDecimal3);
        a31 = v.findViewById(R.id.editTextNumberDecimal6);
        a32 = v.findViewById(R.id.editTextNumberDecimal2);
        a33 = v.findViewById(R.id.editTextNumberDecimal);
        calculate = v.findViewById(R.id.button);
        clear = v.findViewById(R.id.button4);
        result = v.findViewById(R.id.textView);

        // to start python
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(container.getContext()));
        }

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    arr[0] = Double.parseDouble(a11.getText().toString());
                    arr[1] = Double.parseDouble(a12.getText().toString());
                    arr[2] = Double.parseDouble(a13.getText().toString());
                    arr[3] = Double.parseDouble(a21.getText().toString());
                    arr[4] = Double.parseDouble(a22.getText().toString());
                    arr[5] = Double.parseDouble(a23.getText().toString());
                    arr[6] = Double.parseDouble(a31.getText().toString());
                    arr[7] = Double.parseDouble(a32.getText().toString());
                    arr[8] = Double.parseDouble(a33.getText().toString());



                    // to create python instance (to read .py file)
                    Python py = Python.getInstance();

                    // to create python obj (to read myfile.py)
                    PyObject pyobj = py.getModule("myfile");

                    // to read which function
                    PyObject obj = pyobj.callAttr("main",arr);

                    result.setText(obj.toString());
                }
                catch (Exception e){
                    Toast.makeText(container.getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a11.getText().clear();
                a12.getText().clear();
                a13.getText().clear();
                a21.getText().clear();
                a22.getText().clear();
                a23.getText().clear();
                a31.getText().clear();
                a32.getText().clear();
                a33.getText().clear();
                result.setText("");
            }
        });




        return v;
    }
}