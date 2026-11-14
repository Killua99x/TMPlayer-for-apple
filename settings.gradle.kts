pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TMPlayer"
include(":app")
include(":core")
include(":ui")
include(":desktop")
// The Kotlin half of the iOS and iPadOS app; the Xcode project is iosApp/ (docs/IOS_PORT_PLAN.md).
include(":ios")
// The framework the Xcode project links, TMPlayerKit, with only what Swift calls in its API.
include(":ios:kit")
