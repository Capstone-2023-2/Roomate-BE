plugins {
    id("java")
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // 스프링 데이터 JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    runtimeOnly("com.h2database:h2") //인메모리 데이터베이스
    compileOnly("org.projectlombok:lombok") //롬복
    annotationProcessor("org.projectlombok:lombok")
    // 스프링 시큐리티를 사용하기 위한 스타터 추가
    implementation("org.springframework.boot:spring-boot-starter-security")
    // 타임리프에서 스프링 시큐리티를 사용하기 위한 의존성 추가
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    // 스프링 시큐리티를 테스트하기 위한 의존성 추가
    testImplementation("org.springframework.security:spring-security-test")
    // 이메일 인증을 위한 의존성 추가
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    //thymeleaf사용을 위한 의존성 추가
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.webjars:sockjs-client:1.5.1")
    implementation("org.webjars:stomp-websocket:2.3.4")
    implementation("org.springframework:spring-messaging:6.0.3")
    implementation("org.springframework.security:spring-security-messaging:6.0.2")

}

tasks.test {
    useJUnitPlatform()
}