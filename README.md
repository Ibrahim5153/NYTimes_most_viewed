# Summary
This assignment is written in Java and followed __MVVM__ Architecture with retrofit and following Architecture Components:

* ViewModel
* LiveData
* Data Binding
* Navigation

It uses Dagger dependency injection.

# How it works

### Overview
The repository is constructor injected by __Retrofit__ instance and __ViewModel__ is injected by __Repository__. Inside activity i field injected the Custom __ViewModelFactory__ from which we can get the instance of __ViewModel__. 

### Binding

I have used binding to directly bind UI from xml to ViewModel 

### UI
Projects contains one Activity and two Fragments The __MainActivity__ is the host activity for the fragments and __MasterFragment__ will show the list of news while __DetailFragment__ will be opened on tapping on list item in __MasterFragment__ and shows the detail of particular news.

### ViewModel
Each ViewModel will contain one Observable model for binding the each element with ui the Observable Model is extended by __BaseObservable__ so the any change to the field wiil automatically reflect in ui

### Repository
For now the repository only calls the api and post the data to the LiveData provided by ViewModel. We can add persistent storage like __Room__ to cache the data and set some period for example  
>if we know that Data will be updated daily at 12AM then there is no need to call api for each time user opens the app we can cache the data and only show the data when we know the data has outdated.

# KeyFiles
* **DI** this package contains the dependancy injection related classes
* **ViewModels** holds the ViewModel classes
* **Repositories** holds the repo classes keeps the logic for calling api calls
* **View** holds the activities and fragments and it also has a package named adapter which holds adpaters like for recyclerview, viewpager etc aand also contains the abstract class __BindingAdapter__ which holds the custom Binding Adapters for view binding
* **Data** it holds three types of modelone for local uses as there is no need for so much un-necessary data we should use only which we use the second ones are observable for viewmodels and the third ones are for parsing json from api



>## NOTE
>Viewmodel and the Repository classes has the Custom Base class for each, any new Repository or Viewmodel should extend the respective Base class so if we have some shared code for any of them in the future we can put it inside the respective Base classes and also it helps in __Generics__ and __Casting__.
