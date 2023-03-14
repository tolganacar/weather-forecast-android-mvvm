# App Presentation
![weatherapp](https://user-images.githubusercontent.com/83028055/225032056-f608eec5-442e-4989-919c-327dd0a9c3fe.gif)


## Architecture
- Single Activity
- MVVM Pattern
- Clean Code
- Repository Pattern

**View:** Renders UI and delegates user actions to ViewModel

**ViewModel:** Can have simple UI logic but most of the time just gets the data from UseCase

**UseCase:** Contains all business rules and they written in the manner of single responsibility principle

**Repository:** Single source of data. Responsible to get data from one or more data sources

## Tech Stack
#### Dependencies

- **[Fragment](https://developer.android.com/jetpack/androidx/releases/fragment):** Independent screens that are hosted within an Activity 
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata):** Lifecycle aware observable and data holder
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** Holds UI data across configuration changes
- **[Databinding](https://developer.android.com/topic/libraries/data-binding/):** Binds UI components in layouts to data sources
- **[Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android):** Dependency injector
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines):** Asynchronous programming
- **[Kotlin-Flow](https://developer.android.com/kotlin/flow):** Asynchronous programming
- **[Retrofit](https://github.com/square/retrofit):** Type safe HTTP client
- **[Glide](https://github.com/bumptech/glide):** Media management and image loading framework
- **[KTX](https://developer.android.com/kotlin/ktx):** A set of Kotlin extensions

## License

```
Copyright tolganacar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```