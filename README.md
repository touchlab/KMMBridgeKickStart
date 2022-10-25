# KMMBridgeKickStart

This is a sample of what a production app might look like when using [KMMBridge](https://github.com/touchlab/KMMBridge/). It's intended to help get you started integrating a Kotlin Mutliplatform Mobile shared library into your production code.

This is still a work in progress so some details might be changing. Feel free to open an issue if you see something that could be improved!

## Structure

The project is split across three different repositories. This one holds the shared code, and there are separate repos for the [Android](https://github.com/touchlab/Brownfield-Android) and [iOS](https://github.com/touchlab/Brownfield-iOS) code that consume it.

This repository is split into the following modules:

    allshared
    ├── analytics
    ├── breeds
        ├── database

`allshared` only has iOS sources. It can include any iOS-specific API surface (eg callback wrappers around suspend funs) and exports `analytics` and `breeds` but not `database`. This is the module where KMMBridge is configured.

`analytics` is a module to make analytics calls. This is often a thing teams try to integrate first when introducing KMM, so it can provide a template for introducing shared analytics into your own project.

`breeds` is a model of a more complicated feature. It's structured around a `BreedRepository` which is able to list dog breeds and mark them as favorites.

`database` includes the storage logic for the `breeds` feature. Because it lives in a separate module which is not exported, SqlDelight definitions are not unnecessarily exported to iOS.

The Android repository consumes `breeds` amd `analytics` as separate modules, while the iOS repository consumes the `allshared` module via the published framework.