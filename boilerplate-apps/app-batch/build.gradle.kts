dependencies {
    implementation(project(":boilerplate-core:core-usecase"))
    implementation(project(":boilerplate-core:core-domain"))
    implementation(project(":boilerplate-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // jpa
    implementation("org.springframework:spring-tx")

    runtimeOnly(project(":boilerplate-core:core-service"))
}