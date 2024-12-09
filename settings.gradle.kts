// Define kotlin_version in settings.gradle.kts
extra["kotlin_version"] = "1.9.0"

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

rootProject.name = "WelcomeProjectApp"
include(":app")
include(":inmanage")
project(":inmanage").projectDir = File(rootDir, "Base-Project-Android/inmanage/")
