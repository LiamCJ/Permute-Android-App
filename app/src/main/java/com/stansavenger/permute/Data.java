package com.stansavenger.permute;

public class Data {
    private static String baseCurrency = "", convertedCurrency = "";

    private static final String
        BASE_URL = "https://api.exchangeratesapi.io/",
        ENDPOINT = "latest",

        ZAR_CODE = "ZAR",
        EUR_CODE = "EUR",
        USD_CODE = "USD",
        CAD_CODE = "CAD",
        GBP_CODE = "GBP",

        ZAR_SYMBOL = "R",
        EUR_SYMBOL = "€",
        USD_SYMBOL = "$",
        CAD_SYMBOL = "$",
        GBP_SYMBOL = "£";

    private static Double
        zarValue = 0.0,
        eurValue = 0.0,
        usdValue = 0.0,
        cadValue = 0.0,
        gbpValue = 0.0;

    /*========================
            GETTERS
    ============================*/

    public static String GetBaseUrl(){
        return BASE_URL;
    }

    public static String GetBaseCurrency(){
        return baseCurrency;
    }

    public static String GetConvertedCurrency(){
        return convertedCurrency;
    }

    public static String GetEndpoint(){
        return ENDPOINT;
    }


    public static String GetCurrencyCode(String Currency){
        switch(Currency) {
            case "ZAR":
                return ZAR_CODE;
            case "EUR":
                return EUR_CODE;
            case "USD":
                return USD_CODE;
            case "CAD":
                return CAD_CODE;
            case "GBP":
                return GBP_CODE;
            default:
                return "unknown currency input";
        }
    }

    public static Double GetCurrencyValue(String Currency){
        switch(Currency) {
            case "ZAR":
                return zarValue;
            case "EUR":
                return eurValue;
            case "USD":
                return usdValue;
            case "CAD":
                return cadValue;
            case "GBP":
                return gbpValue;
            default:
                return 0.0;
        }
    }

    /*========================
            SETTERS
    ============================*/

    public static void SetBaseCurrency(String Currency){
        baseCurrency = Currency;
    }

    public static void SetConvertedCurrency(String Currency){
        convertedCurrency = Currency;
    }

    public static void SetCurrencyValue(String Currency, Double Value){
        switch(Currency) {
            case "ZAR":
                zarValue = Value;
                break;
            case "EUR":
                eurValue = Value;
                break;
            case "USD":
                usdValue = Value;
                break;
            case "CAD":
                cadValue = Value;
                break;
            case "GBP":
                gbpValue = Value;
                break;
            default:
                break;
        }
    }

}
