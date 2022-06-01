Android app connects to the Movie DB API.

## Descreption

This is a simple android app connects to the Movies DB API, fetch popular movies and present some
basic information related to each one of them.

## Library Stack

- **Material - AndroidX Core - Fragment - Constraint Layout - Swipe To Refresh** support libraries
- **Glide** for image loading and catching
- **Paging** recycler view adapter & pagination
- **Dagger** to provide dependency injection
- **Retrofit - OkHttp3** for request/response API
- **Coroutines** reactive programming paradigm
- **LiveData** use LiveData to see UI update with data changes
- **Data Binding** bind UI components in layouts to data sources
- **View Binding** bind layouts into related UI components

## App Architecture

- Follow the rules from Clean and MVVM Architectures guidelines
- Keep Fragment/Activity responsible only for the code related to UI
- Splitted into six main layers (common - data - di - domain - feature - ui)