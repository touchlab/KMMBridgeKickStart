// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "file:/Users/kgalligan/devel/KMMBridgeKickStart/allshared/build/my-local-repo//co/touchlab/kmmbridgekickstart/allshared-kmmbridge/0.7.2/allshared-kmmbridge-0.7.2.zip"
let remoteKotlinChecksum = "fe57822fa8ae5806e791558b1a632c3cd3af6f185d8e866ff4e132f40f68a6d4"
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