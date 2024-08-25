# Node.js 이미지를 기반으로 설정
FROM node:14 as build

# 작업 디렉토리 설정
WORKDIR /app

# 의존성 설치를 위해 package.json과 package-lock.json 복사
COPY package*.json ./

# 의존성 설치
RUN npm install

# 소스 코드 복사
COPY . .

# 애플리케이션 빌드
RUN npm run build

# Nginx를 사용하여 정적 파일 제공
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

# Nginx 포트 설정
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
