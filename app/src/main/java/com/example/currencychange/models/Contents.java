package com.example.currencychange.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Contents {
   public static   List<String>currencyNamee=new ArrayList<>();


  /*  public static String [] CurrencyName ={"AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
            "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYN",
            "BYR", "BZD",
            "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK",
            "DJF", "DKK", "DOP", "DZD",
            "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD",
            "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY",
            "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT",
            "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD",
            "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN",
            "NAD", "NGN", "NIO", "NOK", "NPR", "NZD",
            "OMR",
            "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG",
            "QAR",
            "RON", "RSD", "RUB", "RWF",
            "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL",
            "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS",
            "UAH", "UGX", "USD", "UYU", "UZS",
            "VEF", "VND", "VUV",
            "WST",
            "XAF", "XAG","XAU" , "XCD", "XDR", "XOF", "XPF",
            "YER",
            "ZAR","ZMK", "ZMW","ZWL"};*/
         public static List<String> favourName=new ArrayList<>();
         public static List<Integer> favourPostion = new ArrayList<>();
    public static List<Object> currencyValue=new ArrayList<>() ;

   public  static List<Double> currencyValues = new ArrayList<>();

   public static List<Object> CurrencyNames = new ArrayList<>();
   // 1 mean input valur ---- 2 mean output value
    public static int select =1;
    //1 mean add anther currency  2 mean clear crrency
    public static int reverceCase;
    public static String nameIn;
    public static String nameOut;
    public static String nameOut3;
    public static int posIn;
    public static int posOut;
    public static int posOut3;
    public static ArrayList<String> filtredNameList = new ArrayList<>();
    public static ArrayList<Integer>filtrePostionList = new ArrayList<>();




}



