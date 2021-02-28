// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
	BASE_URL : "https://api.exchangeratesapi.io/",
	ENDPOINT : "latest",
	ZAR_CODE : "ZAR",
	EUR_CODE : "EUR",
	USD_CODE : "USD",
	CAD_CODE : "CAD",
	GBP_CODE : "GBP",
  SEK_CODE : "SEK",
	ZAR_SYMBOL : "R",
	EUR_SYMBOL : "€",
	USD_SYMBOL : "$",
	CAD_SYMBOL : "$",
	GBP_SYMBOL : "£",
  SEK_SYMBOL : "KR",
	ZAR : "Rand",
	EUR : "Euro",
	USD : "US Dollar",
	CAD : "CA Dollar",
	GBP : "Pound",
  SEK : "Krona",
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
