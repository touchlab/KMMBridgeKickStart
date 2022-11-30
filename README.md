# KMMBridgeKickStart

This is a sample of what a production app might look like when using
[KMMBridge](https://github.com/touchlab/KMMBridge/).
It's intended to help get you started integrating a Kotlin Mutliplatform Mobile shared library into your production
code.

We created sample [Android](https://github.com/touchlab/KMMBridgeKickStart-Android)
and [iOS](https://github.com/touchlab/KMMBridgeKickStart-iOS) projects to illustrate
a simple usage of this SDK.

This is still a work in progress so some details might be changing. Feel free to open an issue if you see something that
could be improved!

## Structure

This repository is split into the following modules:

`allshared` only has iOS sources. It can include any iOS-specific API surface (e.g. callback wrappers around suspend
functions) and exports `analytics` and `breeds` but not `database`. This is the module where KMMBridge is configured.

`analytics` is a module to make analytics calls. This is often a thing teams try to integrate first when introducing
KMM, so it can provide a template for introducing shared analytics into your own project.

`breeds` is a model of a more complicated feature. It's structured around a `BreedRepository` which is able to list dog
breeds and mark them as favorites. It also includes the storage logic. Because it lives in a separate module
from `allshared` which is not exported, SqlDelight definitions are not unnecessarily exported to iOS.

The Android repository consumes `breeds` amd `analytics` as separate modules, while the iOS repository consumes
the `allshared` module via the published framework.

## Usage

### 1) Use This Template Repo to Create Your Kotlin Repo

Click “Use Template”, give your repo a name, and create it.

### 2) Edit GROUP

Add a group string to your repo. You can open gradle.properties and edit GROUP.

### 3) Publish A Build

After the repo has been created and GROUP has been specified, go to “Actions” and run one of the available CI workflows.
KMM Bridge/iOS Publish will build and publish just the iOS SDK. All Publish Will publish iOS and Android binaries.

For more detailed info and for next steps see
this [tutorial blog post](https://touchlab.co/quick-start-with-kmmbridge-1-hour-tutorial/).

## SDK Initialization

### Android

Add dependencies in your build.gradle file

```kotlin
implementation("co.touchlab.kmmbridgekickstart:breeds:${VERSION}")
implementation("co.touchlab.kmmbridgekickstart:analytics:${VERSION}")
```

Initialize the SDK in `onCreate` of your main `application` by calling

```kotlin
val sdkHandle = startSDK(analytics = AnalyticsImpl, context = this)
```

with your implementation of the `Analytics` interface and application context, keep returned `SDKHandle` to have access
to the `CallbackBreedRepository` and analytics.

(Optional) log the app start

```kotlin
sdkHandle.appAnalytics.appStarted()
```

Now you can inject the BreedRepository and Analytics classes into your ViewModel and use all its methods by using

```kotlin
sdkHandle.breedRepository
sdkHandle.appAnalytics
sdkHandle.breedAnalytics
```

### iOS

Import the `allshared` module

```
import allshared
```

Initialize the SDK in `init` function in the `App` class

```
init() {
    self.handle = StartSDKKt.startSDK(analytics: AnalyticsImpl))
}
```

with your implementation of the `Analytics` interface.

The `startSDK` function returns `SDKHandle` data class that holds the `CallbackBreedRepository` and analytics classes,
which your View Model can use to access the data and functions.

(Optional) log the app start

```
handle.appAnalytics().appStarted()
```
