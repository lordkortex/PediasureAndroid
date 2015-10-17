package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asynctasks.XpathUtil;
import pediasure.app.hn.com.pediasure.CalculatorConstants;

/**
 * Created by mac on 26/9/15.
 */
public class CalculatorList {
    public static String edad;

    public static Map<String,String> calculatorMap = new HashMap<String,String>();;

    public static   List<String> nodeListRequerimiento  = new ArrayList<String>();

    public static  List<String> nodeListDesayunoPlato = new ArrayList<String>();
    public static  List<String> nodeListDesayunoAcompañante  = new ArrayList<String>();
    public static  List<String> nodeListDesayunoBebida  = new ArrayList<String>();

    public static  List<String> nodeListAlmuerzoPlato  = new ArrayList<String>();
    public static  List<String> nodeListAlmuerzoAcompañante  = new ArrayList<String>();
    public static  List<String> nodeListAlmuerzoBebida  = new ArrayList<String>();

    public static  List<String> nodeListCenaPlato  = new ArrayList<String>();
    public static  List<String> nodeListCenaAcompañante  = new ArrayList<String>();
    public static  List<String> nodeListCenaBebida  = new ArrayList<String>();

    public static  List<String> nodeListMeriendaPlato  = new ArrayList<String>();
    public static  List<String> nodeListMeriendaAcompañante  = new ArrayList<String>();
    public static  List<String> nodeListMeriendaBebida  = new ArrayList<String>();

    public static Map<String,String> mapRequerimientos = new HashMap<String,String>();
    public static Map<String,String> mapCatalogo = new HashMap<String,String>();



    private CalculatorList(){

    }
}
