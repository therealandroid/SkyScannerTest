# Skyscanner App Documentation

### What you shoud know about this project?
#### 1 - Architecture

      I've followed the MVP and Repository patterns. The structure is basically the following.
   |--main
   |  |-- config         -> Configuration files, Extensions etc
   |  |-- View             -> Activities, Fragments, Adapters
   |  |  |-- Presenter     -> Answer to activity calls (e.a, user clicks, triggers) and respond with data to the activty to bind then later
   |  |  |-- Contract      -> Contract is an interface that obligate the Presenter and the View (activity) to follow the functionality structure and callbacks
   |  |-- Model            -> Contains the logic and necessary processment and abstractions
   |     |-- Api Wrapper   -> A wrapper that abstracts the Back-end implementation, for example, Presenter will just receive the data to give to the activity to display.
   |     |   |-- Entities -> Those are the Api models from the Living Price Api.
   |     |-- Repository    -> The repository is the data source provider, that abstracts to the presenter where the data come from.
   |     |-- View Entities -> Those are POJOS, that are used just to be manipuled by the view or the user
   |
   |--test                 -> It contains the behavioral tests and unity tests for the given projects.
   |--Android test         -> Contain some UI testing and instrumentation tests

#### 2 - Main Libraries

  `rxjava2`
  `Retrofit`
  `OkHttpClient`
  `Picasso`
  `Skeleton View` plus `Shime layout`


#### 3 - Project Version

   The minimum Android Api Level to run this project is `21` - Android 5.0 (LOLLIPOP)