// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/touchlab/KMMBridgeSampleKotlin/co/touchlab/kmmbridgekickstart/allshared-kmmbridge/0.3.1/allshared-kmmbridge-0.3.1.zip"
let remoteKotlinChecksum = "673e4accf5df9a1b65f13aba2173605c3b84808334ab18c8da5955ea64a078bc"
let packageName = "KMMBridgeKickStart"

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