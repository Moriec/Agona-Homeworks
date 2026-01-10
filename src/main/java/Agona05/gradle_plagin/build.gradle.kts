plugins {
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.vinogradov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jgit:org.eclipse.jgit:7.2.1.202505142326-r")
    gradleApi()
}

gradlePlugin {
    plugins {
        create("checkTodoPlugin") {
            id = "com.vinogradov.checktodo"
            implementationClass = "com.vinogradov.CheckTodoPlugin"
            displayName = "Check TODO Plugin"
        }
    }
}
