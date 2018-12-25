package com.nollpointer.graphapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarA;
    SeekBar seekBarC;

    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarA = findViewById(R.id.seekbarA);
        seekBarC = findViewById(R.id.seekbarC);
        graphView = findViewById(R.id.graphView);

        seekBarA.setMax(200);
        seekBarC.setMax(200);

        seekBarA.setProgress(100);
        seekBarC.setProgress(100);

        final TextView seekbarAvalue = findViewById(R.id.seekbarAvalue);
        final TextView seekbarCvalue = findViewById(R.id.seekbarCvalue);

        seekBarA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = ((float) i)/100;
                seekbarAvalue.setText("A = " + value);
                graphView.setA(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = ((float) i)/100;
                seekbarCvalue.setText("C = " + value);
                graphView.setC(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
