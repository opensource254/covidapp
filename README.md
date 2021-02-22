 ![Android CI](https://github.com/opensource254/covidapp/workflows/Android%20CI/badge.svg)
## This project is no longer supported
archived 

thanks to all contributors 
<a href="https://github.com/opensource254/covidapp/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=opensource254/covidapp" />
</a>



## Official Covid 19 APP for Kenya

[![Get it on Google Play][Play Store Badge]][Play Store] coming soon....

### Setup
**Requirements**
- JDK 8
- Latest Android SDK tools
- Latest Android platform tools
- Android SDK 29
- Androidx Support Repository

**Dependencies**



**Build**

    ./gradlew assembleDebug





### Screenshots


### Contributing
Contributions are always welcome. Please make sure you read [Contributing notes](CONTRIBUTING.md) first.

## Available API ENDPOINTS (api.covid19kenya.site)


| Request | Endpoint              | Function                |
| ------- | --------------------- | ----------------------- |
| POST    | `/api/v1/tip`         | Create a new tip        |
| GET     | `/api/v1/tips`        | Get all tips            |
| GET     | `/api/v1/tip/:id`     | Get a tip               |
| PUT     | `/api/v1/tip/:id`     | Update a tip            |
| POST    | `/api/v1/alert`       | Create a new alert      | 
| GET     | `/api/v1/alerts`      | Get all alerts          |
| GET     | `/api/v1/alert/:id`   | Get an alert            |
| PUT     | `/api/v1/alert/:id`   | Update an alert         |
| POST    | `/api/v1/hospital`    | Create a new hospital   | 
| GET     | `/api/v1/hospitals`   | Get all hospitals       |
| GET     | `/api/v1/hospital/:id`| Get a hospital          |
| PUT     | `/api/v1/hospital/:id`| Update a hospital       |
| GET     | `/api/v1/tweets`      | Get all tweets          |
| POST    | `/api/v1/county_case` | Get all counties detail |
| GET     | `/api/v1/counties`    | Get all counties        |
| GET     | `api/v1/county/:id`   | Get all counties by ids | 

opensourced 

[Play Store]: https://play.google.com/store/apps/
[Play Store Badge]: https://play.google.com/intl/en_us/badges/images/badge_new.png
[AOSP support library]: https://developer.android.com/tools/support-library/features.html
[Retrofit]: https://github.com/square/retrofit
[OkHttp]: https://github.com/square/okhttp

