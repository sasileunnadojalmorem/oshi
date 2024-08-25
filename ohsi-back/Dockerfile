# 빌드 단계
FROM gradle:7.2-jdk17 AS build

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 파일 복사
COPY . .

# 프로젝트 빌드 (bootJar는 Gradle의 기본 빌드 태스크입니다)
RUN gradle bootJar --no-daemon

# 실행 단계
FROM openjdk:17-jdk-slim

# 빌드 결과물 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]

# 컨테이너 포트 설정
EXPOSE 8080
