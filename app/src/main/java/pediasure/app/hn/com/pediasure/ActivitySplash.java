package pediasure.app.hn.com.pediasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import asynctasks.DataWebService;
import entities.CalculatorList;

/**
 * Created by mac on 27/9/15.
 */
public class ActivitySplash extends Activity {


    private Button btnIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new DataWebService(this).execute();


        btnIniciar = (Button) findViewById(R.id.buttonIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try {
                        CalculatorList.calculatorMap = new HashMap<String, String>();
                        Class ourclass = Class.forName("pediasure.app.hn.com.pediasure.ActivityEdad");
                        Intent ourintent = new Intent(ActivitySplash.this, ourclass);
                        startActivity(ourintent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });

    }

}
