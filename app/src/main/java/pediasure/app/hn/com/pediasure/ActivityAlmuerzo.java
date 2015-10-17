package pediasure.app.hn.com.pediasure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.ExpandableListAdapter;
import entities.CalculatorList;

/**
 * Created by mac on 26/9/15.
 */
public class ActivityAlmuerzo  extends Activity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    private TextView txtViewPlatoFuerte;
    private TextView txtViewAcompañante;
    private TextView txtViewBebida;

    private int lastExpandedPosition = -1;

    private Button btnContinuar;

    public static Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = this;
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getActionBar().setTitle("Ir a Desayuno");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final Context context = this;


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        txtViewPlatoFuerte = (TextView) findViewById(R.id.textViewPlatoFuerte);
        txtViewAcompañante = (TextView) findViewById(R.id.textViewAcompañante);
        txtViewBebida = (TextView) findViewById(R.id.textViewBebida);

        fillFields();

        TextView textViewFaseDesayunoTitle = (TextView) findViewById(R.id.textViewFaseDesayunoTitle);
        TextView textViewFaseAlmuerzoTitle = (TextView) findViewById(R.id.textViewFaseAlmuerzoTitle);
        TextView textViewFaseCenaTitle = (TextView) findViewById(R.id.textViewFaseCenaTitle);
        TextView textViewFaseMeriendaTitle = (TextView) findViewById(R.id.textViewFaseMeriendaTitle);

        textViewFaseDesayunoTitle.setTextColor(getResources().getColor(R.color.gray));
        textViewFaseAlmuerzoTitle.setTextColor(getResources().getColor(R.color.white));
        textViewFaseCenaTitle.setTextColor(getResources().getColor(R.color.gray));
        textViewFaseMeriendaTitle.setTextColor(getResources().getColor(R.color.gray));


        btnContinuar = (Button) findViewById(R.id.buttonContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (("".equals(txtViewPlatoFuerte.getText()) && !CalculatorList.nodeListAlmuerzoPlato.isEmpty())
                        || ("".equals(txtViewAcompañante.getText()) && !CalculatorList.nodeListAlmuerzoAcompañante.isEmpty())
                        || ("".equals(txtViewBebida.getText()) && !CalculatorList.nodeListAlmuerzoBebida.isEmpty()))
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    alertDialogBuilder.setTitle("Advertencia");
                    alertDialogBuilder
                            .setMessage("No has seleccionado plato, acompañante o bebida. Deseas continuar ?")
                            .setCancelable(false)
                            .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    callNext();
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }else{
                    callNext();
                }

            }
        });


        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                TextView moreTextView = (TextView) v.findViewById(R.id.lblListHeader);  // this works fine
                //moreTextView.setTextColor(getResources().getColor(R.color.blue));

                return false;
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                final String alimentoSeleccionado = listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition);


                switch (groupPosition) {
                    case 0:
                        txtViewPlatoFuerte.setText(alimentoSeleccionado);
                        CalculatorList.calculatorMap.put(CalculatorConstants.ALMUERZO_PLATO, alimentoSeleccionado);
                        break;
                    case 1:
                        txtViewAcompañante.setText(alimentoSeleccionado);
                        CalculatorList.calculatorMap.put(CalculatorConstants.ALMUERZO_ACOMPAÑANTE, alimentoSeleccionado);
                        break;
                    case 2:
                        txtViewBebida.setText(alimentoSeleccionado);
                        CalculatorList.calculatorMap.put(CalculatorConstants.ALMUERZO_BEBIDA, alimentoSeleccionado);
                        break;
                }

                lastExpandedPosition = groupPosition;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                expListView.collapseGroup(groupPosition);


                /*Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/

                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;


            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private void callNext(){
        try {
            Class ourclass = Class.forName("pediasure.app.hn.com.pediasure.ActivityCena");
            Intent ourintent = new Intent(ActivityAlmuerzo.this, ourclass);
            startActivity(ourintent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    };

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        fillFields();


    }

    public void fillFields(){

        if(CalculatorList.calculatorMap.containsKey(CalculatorConstants.ALMUERZO_PLATO)){
            txtViewPlatoFuerte.setText(CalculatorList.calculatorMap.get(CalculatorConstants.ALMUERZO_PLATO));
        }

        if(CalculatorList.calculatorMap.containsKey(CalculatorConstants.ALMUERZO_ACOMPAÑANTE)){
            txtViewAcompañante.setText(CalculatorList.calculatorMap.get(CalculatorConstants.ALMUERZO_ACOMPAÑANTE));
        }

        if(CalculatorList.calculatorMap.containsKey(CalculatorConstants.ALMUERZO_BEBIDA)){
            txtViewBebida.setText(CalculatorList.calculatorMap.get(CalculatorConstants.ALMUERZO_BEBIDA));
        }

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        if(!CalculatorList.nodeListAlmuerzoPlato.isEmpty()){
            listDataHeader.add("Plato Fuerte");
            listDataChild.put(listDataHeader.get(0), CalculatorList.nodeListAlmuerzoPlato); // Header, Child data
        }
        if(!CalculatorList.nodeListAlmuerzoAcompañante.isEmpty()){
            listDataHeader.add("Acompañante");
            listDataChild.put(listDataHeader.get(1), CalculatorList.nodeListAlmuerzoAcompañante);
        }
        if(!CalculatorList.nodeListAlmuerzoBebida.isEmpty()){
            listDataHeader.add("Bebida");
            listDataChild.put(listDataHeader.get(2), CalculatorList.nodeListAlmuerzoBebida);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
