import { Component, ViewChild, ElementRef  } from '@angular/core';
import {environment} from "../../environments/environment";
import { HttpClient } from '@angular/common/http';
import {AlertController} from '@ionic/angular'
@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  convertedCurrencySelect:string;
  baseCurrencySelect:string;
  isCurrencyChanged:boolean = false;
  environmentVar = environment;
  BASE_URL: string = environment.BASE_URL;
  baseCurrency: string = "ZAR";
  convertedCurrency: string = "EUR";
  ENDPOINT: string = environment.ENDPOINT;
  inputMoney:number;
  zarValue:number = 0;
  eurValue:number = 0;
  usdValue:number = 0;
  gbpValue:number = 0;
  cadValue:number = 0;
  sekValue:number = 0;
  errorMessage;
  isOnline:boolean = true;

  constructor(
    private http: HttpClient,
    public atrCtrl: AlertController
    ) {
    environment :environment;
}

  convertCurrency(){
    let request = this.GetBaseUrl() + this.GetEndpoint() + "?base=" + this.GetBaseCurrency()
    this.http.get<any>(request).subscribe({
      next: data => {
        let currency = this.GetConvertedCurrency()
        let convertedValue = data.rates[currency];
        this.SetCurrencyValue(this.GetConvertedCurrency(), convertedValue * this.inputMoney)
      },
      error: async error => {
        this.errorMessage = error;
        if(this.errorMessage.name == "HttpErrorResponse"){
          let alert = await this.atrCtrl.create({
            header: 'No Internet Connection',
            message: 'Please check your internet connection before tying to convert again',
            buttons: ['OK']
          });
          await alert.present();
        }
        console.error('There was an error!', error);
      }
    })

  }


  /*========================
            GETTERS
    ============================*/

  GetBaseUrl(){
    return this.BASE_URL;
  }

  GetBaseCurrency(){
    return this.baseCurrency;
  }

  GetConvertedCurrency(){
    return this.convertedCurrency;
  }

  GetEndpoint(){
    return this.ENDPOINT;
  }

  GetCurrencyCode( Currency){
    switch(Currency) {
      case "ZAR":
        return environment.ZAR_CODE;
      case "EUR":
        return environment.EUR_CODE;
      case "USD":
        return environment.USD_CODE;
      case "CAD":
        return environment.CAD_CODE;
      case "GBP":
        return environment.GBP_CODE;
      case "SEK":
        return environment.SEK_CODE;
      default:
        return "unknown currency input";
    }
  }

  GetCurrencySymbol( Currency){
    switch(Currency) {
      case "ZAR":
        return environment.ZAR_SYMBOL;
      case "EUR":
        return environment.EUR_SYMBOL;
      case "USD":
        return environment.USD_SYMBOL;
      case "CAD":
        return environment.CAD_SYMBOL;
      case "GBP":
        return environment.GBP_SYMBOL;
      case "SEK":
        return environment.SEK_SYMBOL;
      default:
        return "unknown currency input";
    }
  }

  GetCurrencyValue( Currency){
    switch(Currency) {
      case "ZAR":
        return this.zarValue;
      case "EUR":
        return this.eurValue;
      case "USD":
        return this.usdValue;
      case "CAD":
        return this.cadValue;
      case "GBP":
        return this.gbpValue;
      case "SEK":
        return this.sekValue;
      default:
        return 0.0;
    }
  }

  /*========================
          SETTERS
  ============================*/

  SetBaseCurrency( Currency){
    this.convertCurrency();
    this.baseCurrency = Currency;
  }

  SetConvertedCurrency( Currency){
    this.convertCurrency();
    this.convertedCurrency = Currency;
  }

  SetCurrencyValue( Currency,  Value){
    switch(Currency) {
      case "ZAR":
        this.zarValue = Value;
        break;
      case "EUR":
        this.eurValue = Value;
        break;
      case "USD":
        this.usdValue = Value;
        break;
      case "CAD":
        this.cadValue = Value;
        break;
      case "GBP":
        this.gbpValue = Value;
      case "SEK":
        this.sekValue = Value;
        break;
      default:
        break;
    }
  }

}
