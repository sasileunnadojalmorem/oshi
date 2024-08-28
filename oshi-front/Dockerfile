FROM node:14-alpine
#컨테이너의 /app 경로에
WORKDIR '/app'  

#package.json 을 복사후
COPY package.json .
#npm install 을 실행후
RUN npm install
#나머지 파일드를 복사후
COPY . .
ENV CHOKIDAR_USEPOLLING=true

#npm run start 실행
CMD ["npm","run","start"]