pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "research"

includeBuild("../node_modules/react-native-gradle-plugin")
include(":app")

apply(from = file("../node_modules/@react-native-community/cli-platform-android/native_modules.gradle"))
val applyNativeModules: groovy.lang.Closure<Any> = extra.get("applyNativeModulesSettingsGradle") as groovy.lang.Closure<Any>
applyNativeModules(settings)


