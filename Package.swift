// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/touchlab/Brownfield-SDK/co/touchlab/brownfield-sdk/allshared-kmmbridge/0.2.4/allshared-kmmbridge-0.2.4.zip"
let remoteKotlinChecksum = "2ac003e018d10d4d3e949f6793f71418b181787d97875944f4e5a35db10bd5c5"
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