<h1 align="center">Rryma</h1>

<p align="center">  
An electric bill expenses application built with Hilt,Room,Coroutines,Jetpack(ViewModel), and Material Design based on MVVM architecture.
</p>
</br>

<img src="/mockups/rryma_mockup_demo.gif" align="right" width="320"/>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
  - [Room](https://developer.android.com/jetpack/androidx/releases/room) Save calculations done by users
  - [Faker](https://serpro69.github.io/kotlin-faker/) Generate dummy data for database
- Architecture
- MVVM Architecture (View - DataBinding - ViewModel - Model)
## Architecture
**Rryma** is based on the MVVM architecture which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).
