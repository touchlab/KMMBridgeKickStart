// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/touchlab/Brownfield-SDK/co/touchlab/brownfield-sdk/allshared-kmmbridge/0.2.5/allshared-kmmbridge-0.2.5.zip"
let remoteKotlinChecksum = "68e3dc28b233b509ffce75c395e5fa5e9f2cf905d219d30ea156ef05f838a097"
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