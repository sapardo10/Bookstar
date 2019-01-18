# Bookstar
Android application that allows the user to search books consuming the openlibrary.org api and save the results he/she is more interested in.
The complete application was developed using kotlin and has tried to follow all the directives from the clean code book by Robert Martin.

## Architecture

This application was built on an MVP (Model-View-Presenter) architecture and relies heavily on the reuse of fragments on different activities.

The packages are divided as it follows:
  - API: 
    It has all the classes related to the consuming of the api such as the data classes and the implementation of retrofit
  - MVP: 
    It contains all the views, presenters and interactors used to draw all the UI to the user and consume the different           services inside the app
  - ROOM: 
    It contains all the classes related to the storage and manipulation of the local database, including the roomDatabase         implementation, dao and entity
  - UTILS: 
    It contains all the additional classes that provide extra functionality to the application (in this case it only contains     the picasso implementation to load images )
 
 ## UI
 
 This application was build following the master-detail flow. This means that the application can be used both in portrait and  landscape mode.
 Another important thing is the use of constraintLayout in all the fragments and activities, this to optimize the drawing of interfaces and having to draw each pixel just once.
 Additionally, the application uses Butterknife to allow a clearer code when binding the UI to the activity
 
 ## Services
 
  ### Retrofit
  
  For fetching the API, this project uses a normal implementation of retrofit and then parses the result (json file) into data objects inside the application. To provide more responsiveness and avoid callback hell reactive java was added to update the UI when the service returns the data.
  
  ### Room

  To store the favorites books of the user, room database was used to allow the application to save the books and survive the restart of the app. Meaning this that if the user doesn't clean the data or reinstall the app, the favorite books will remain on the application.
  LiveData was used here to provide an immediate update of the favorite books when displaying the list, this to avoid inconsistencies between the UI and the logic. Also, this provides more responsiveness to the user.

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
  
### Problems
Due to a missunderstanding of the API the service that could provide more information about a book is not correctly fetched because the ID of the book provided by the search response does not correspond to the ID needed to fetch a book in the other service. The complete implementation of this update is on the code, it will be just missing the correct way to have the id to fetch the book.
  
 # Author
 This application was made by Sergio Pardo. 
 It has the MIT liscense provided inside the repository
 
 
 
Eeny, meeny, miny, moe with wich book should I go?
