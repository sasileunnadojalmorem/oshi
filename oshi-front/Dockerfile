# Node.js 이미지를 기반으로 하는 도커 이미지 생성
FROM node:14

# 작업 디렉토리 설정
WORKDIR /app

# 의존성 설치를 위해 package.json과 package-lock.json을 복사
COPY package*.json ./



# 의존성 설치
RUN npm install

# 프로젝트 소스를 컨테이너에 복사
COPY . .


# 앱 빌드
RUN npm run build

# 앱을 3000 포트에서 실행
EXPOSE 3000

# 앱 실행
CMD ["npm", "start"]
