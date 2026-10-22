pluginManagement {
    repositories {
        maven ( url ="https://maven.myket.ir" )
        maven ( url ="https://maven.devneeds.ir" )
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ( url ="https://maven.myket.ir" )
        maven ( url ="https://maven.devneeds.ir" )
    }
}

rootProject.name = "w3m"
include(":app")
 