dependencies {
    implementation(project(":boilerplate-core:core-usecase"))
    implementation(project(":boilerplate-core:core-port"))
    implementation(project(":boilerplate-core:core-domain"))
    implementation(project(":boilerplate-commons"))

    runtimeOnly(project(":boilerplate-adapters:adapter-http"))
    runtimeOnly(project(":boilerplate-adapters:adapter-persistence"))
    runtimeOnly(project(":boilerplate-adapters:adapter-redis"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")

    implementation("io.jsonwebtoken:jjwt-api")
    implementation("io.jsonwebtoken:jjwt-impl")
    implementation("io.jsonwebtoken:jjwt-jackson")
}