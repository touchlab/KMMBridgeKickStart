// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/touchlab/Brownfield-SDK/co/touchlab/brownfield-sdk/allshared-kmmbridge/0.2.0/allshared-kmmbridge-0.2.0.zip"
let remoteKotlinChecksum = "460affead05546707522af90bdc52c4c53ba2d07d199d94d263240f05d118478"
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