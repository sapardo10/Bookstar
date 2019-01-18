# Bookstar
Android application that allows the user to search books consuming the openlibrary.org api and save the results he/she is more interested in.
The complete application was developed using kotlin and has tried to follow all the directives from the clean code book by Robert Martin.

## Architecture

This application was build on an MVP (Model-View-Presenter) architecture and relies heavily on the reuse of fragments on different acitivities.

The packages are divided as it follows:
  - api
    It has all the classes related to the consuming of the api such as the data classes and the implementation of retrofit
  - mvp
    It contains all the views, presenters and interactors used to draw all the UI to the user and consume the different           services inside the app
  - room
    It contains all the classes related to the storage and manipulation of the local database, including the roomDatabase         implementation, dao and entity
  - utils
    It contains all the additional classes that provide extra functionality to the application (in this case it only contains     the picasso implementation to load images )
 
 ## UI
 
 This application was build following the master-detail flow. This means that the application can be used both in portrait and  lanscape mode.
 Another important thing is the use of constraintLayout in all the fragments and activities, this to optimize the drwaing of interfaces and having to draw each pixel just once.
 Additionally, the application uses Butterknife to allow a clearer code when binding the UI to the activity
 
 ## Services
 
  ### Retrofit
  
  For fetching the API, this project uses a normal implementation of retrofit and then parses the result (json file) into data objects inside the application. To provide more responsiveness and avoid callback hell reactive java was added to update the UI when the service returns the data.
  
  ### Room

  To store the favorites books of the user, room database was used to allow the application to save the books and survive the restart of the app. Meaning this that if the user doesn't clean the data or reinstall the app, the favorite books will remain on the application.
  LiveData was used here to provide an inmidiate update of the favorite books when displaying the list, this to avoid incosistencies between the UI and the logic. Also, this provides more responsiveness to the user.

## Dependencies used
  - Kotlin
  - AppCompat
  - ConstraintLayout
  - CardView
  - RecyclerView
  - ProgressBar
  - Picasso
  - Butterknife
  - Retrofit
  - Rxjava
  - Room
