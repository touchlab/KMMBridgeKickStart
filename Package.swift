// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/touchlab/Brownfield-SDK/co/touchlab/brownfield-sdk/allshared-kmmbridge/0.2.3/allshared-kmmbridge-0.2.3.zip"
let remoteKotlinChecksum = "934300f624f4ccb619a041b4fb3890f6d2621e68f09398f3e01fca6a5b9e6b40"
let packageName = "allshared"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)