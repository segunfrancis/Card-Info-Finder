# Card-Info-Finder
Card Info Finder app that displays card information after entering part or all of the card numbers. Built using android MVVM pattern

It is written 100% in Kotlin with unit, integrated and UI tests.🙂

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [Coroutine](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Card-io](https://github.com/card-io/card.io-Android-SDK)
* [Retrofit](https://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
* [Lottie](https://github.com/airbnb/lottie-android)
* [JUnit](https://junit.org/junit4/)
* [Espresso](https://developer.android.com/training/testing/espresso)
* [Robolectric](http://robolectric.org/)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android 11 ([API 30](https://developer.android.com/preview/api-overview.html))
* Latest Android SDK Tools and build tools.

## Installation

* To run this code, clone this repository using this command `git clone https://github.com/segunfrancis/Card-Info-Finder.git`
* Import into android studio
* Build the project and run on an android device or emulator

## Architecture

The architecture of the project follows the principles of Clean Architecture and MVVM. Here's how the project implements it:


The app when run will displaya an interface that gives the user 2 options of how they want to input their card number.
<p align="center">
  <img src="https://github.com/segunfrancis/Card-Info-Finder/blob/master/art/Screenshot_20210206-055605_Card%20Info%20Finder.jpg" alt="Drawing" width="40%" hspace="15"/>
</p>

This architecture is used in order to maintain clean coding and adhere to the SOLID principles of coding. Let's look at each of the architecture layers and the role each one plays :)

### Presentation layer

This layer contains the views(activity and fragments) and their ViewModel classes. The ViewModel receives its data from the use case layer and then supplies the views.

### Use case layer

This layer presents each individual action of the app.

### Domain layer

The domain layer contains the data transfer objects for the entire app.

### Data layer

The Data layer is our access point to external data source and is used to fetch data from multiple sources (examples are cache and network). In this case, it gets data from the remote source only.


## Screens

<ul>
  <img src="https://github.com/segunfrancis/Card-Info-Finder/blob/master/art/Screenshot_20210206-055709_Card%20Info%20Finder.jpg" width="40%" alt="Screen2" hspace="15">
  <img src="https://github.com/segunfrancis/Card-Info-Finder/blob/master/art/Screenshot_20210206-055725_Card%20Info%20Finder.jpg" alt="Screenshot" width="40%" hspace="15"/>
</ul>

## Reference
* [Card scan animation](https://lottiefiles.com/34501-card-scan) - Lottie Animation

* [Card payment animation](https://lottiefiles.com/33269-card-payment) - Lottie Animation

## Author

* [Segun Francis](https://www.linkedin.com/in/segun-francis-302361a1)

## LICENSE
```
MIT License

Copyright (c) 2021 Segun Francis

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
