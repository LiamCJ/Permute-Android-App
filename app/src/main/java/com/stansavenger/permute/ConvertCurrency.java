package com.stansavenger.permute;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.ParseException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.stansavenger.permute.Data.*;

public class ConvertCurrency {
    private  static HttpClient httpclient = new DefaultHttpClient();

    private static HttpResponse response;

    static {
        try {
            response = httpclient.execute(new HttpGet(GetBaseUrl() + GetEndpoint() + "?base="+ GetBaseCurrency()));
        }  catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static StatusLine statusLine = response.getStatusLine();

    static CloseableHttpClient httpClient = HttpClients.createDefault();


    public static void ConvertCurrencyMethod(double amountOfMoney, String inputCurrency, String outputCurrency) throws IOException, JSONException {
        double convertedAmount;
        SetBaseCurrency(inputCurrency);
//        HttpGet get = new HttpGet(Data.GetBaseUrl() + Data.GetEndpoint() + "?base="+ Data.GetBaseCurrency());

        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            HttpEntity entity = response.getEntity();

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

            convertedAmount = amountOfMoney * exchangeRates.getJSONObject("rates").getDouble(outputCurrency);

            SetCurrencyValue(GetCurrencyCode(outputCurrency), convertedAmount);
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
        /*try {
            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();

                // the following line converts the JSON Response to an equivalent Java Object
                JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

                convertedAmount = amountOfMoney * exchangeRates.getJSONObject("rates").getDouble(outputCurrency);

                Data.SetCurrencyValue(Data.GetCurrencyCode(outputCurrency), convertedAmount);
            }
        } catch (ClientProtocolException e) {
            System.out.println(e);
        } catch (IOException | ParseException | JSONException e) {
            System.out.println(e);
        }*/

        httpClient.close();
    }
}
