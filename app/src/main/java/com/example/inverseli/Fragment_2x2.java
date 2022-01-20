package com.example.inverseli;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class Fragment_2x2 extends Fragment {
    EditText a11;
    EditText a12;
    EditText a21;
    EditText a22;
    Button calculate;
    Button clear;
    TextView result;
    double[] arr = new double[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fraglayout_2x2, container, false);
        a11 = v.findViewById(R.id.editTextNumberDecimal4);
        a12 = v.findViewById(R.id.editTextNumberDecimal3);
        a21 = v.findViewById(R.id.editTextNumberDecimal2);
        a22 = v.findViewById(R.id.editTextNumberDecimal);
        calculate = v.findViewById(R.id.button);
        clear = v.findViewById(R.id.button2);
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
                    arr[2] = Double.parseDouble(a21.getText().toString());
                    arr[3] = Double.parseDouble(a22.getText().toString());

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
                a21.getText().clear();
                a22.getText().clear();
                result.setText("");
            }
        });


        return v;
    }

}