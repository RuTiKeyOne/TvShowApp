# Description files

Here is a detailed description of the project files, this will help you better understand the architecture.

Name             |Description
:-------------------------:|:-------------------------:
build.grable  | Contains all the libraries connected to the project and necessary for correct operation.
activities | Contains all the activity of the application. 
adapters | This package contains the adapters needed to work with EpisodesRecycleView, TvShowRecycleView,imagesliderrecycleview, WatchListRecycleView.
dao | Contains the database interface.
database | Contains a class that implements the logic of getting a database.
listener | This folder contains interfaces that implement callback logic in the application.
models | Contains the class required to get and add record data to the database and use this data in the application.
network | network contains work with Retrofit and logic for getting data from the Api. To get data in this application, the Episodate was used https://www.episodate.com/api.
repositories |Contains the implementation of the logic of obtaining and deserializing data for each specific list (Most popular Tv shows, Search Tv shows, Tv show details).
response | Contains high-level classes necessary to obtain data and deserialize them.
utilities |A class with a picasso implementation and set image logic.
viewmodels | Contains the viewmodel classes needed to implement the mvvm pattern.
drawable | Contains resources used in the markup and application.
font | Contains fonts used in application.
layout | Contains view layout used in application.
strings | Contains text variables, which are selected depending on the language installed on the device.
