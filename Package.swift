// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/touchlab/Brownfield-SDK/co/touchlab/kmmbridgekickstart/allshared-kmmbridge/0.4.0/allshared-kmmbridge-0.4.0.zip"
let remoteKotlinChecksum = "b0c121f38b17bca6658d2765509e52b487fcdc23fc2ef1e59e7f84a9bf1d006f"
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