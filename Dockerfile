# 基础镜像
FROM openjdk:8-jdk-alpine

# 安装字体
RUN apk add --no-cache fontconfig ttf-dejavu

# 指定工作目录
WORKDIR /app

# 将 jar 包添加到工作目录，比如 target/yuoj-backend-user-service-0.0.1-SNAPSHOT.jar
ADD target/CheckinStatistics-0.0.1-SNAPSHOT.jar .

# 暴露端口 注意只是一个声明，告诉 Docker 这个容器会使用这个端口，但实际运行时需要手动映射端口。
EXPOSE 8101

# 启动命令
ENTRYPOINT ["java","-jar","/app/CheckinStatistics-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]

# 第二次执行就不是创建容器了，而是开启之前创建的容器
#Run DockerFile实际上是运行了什么？
#
#
#
#
#
#
#
#运行 Dockerfile 实际上是一个两步过程：
#
#1. **首先是 `docker build`** - 构建镜像：
#```bash
## 基于 Dockerfile 构建镜像
#docker build -t myapp:1.0 .
#```
#
#这个过程会执行 Dockerfile 中的每个指令：
#```dockerfile
## 1. FROM: 拉取基础镜像
#FROM openjdk:8-jdk-alpine
#
## 2. WORKDIR: 创建并切换工作目录
#WORKDIR /app
#
## 3. ADD: 复制 jar 包到容器
#ADD target/CheckinStatistics-0.0.1-SNAPSHOT.jar .
#
## 4. EXPOSE: 声明端口（只是声明）
#EXPOSE 8101
#
## 5. ENTRYPOINT: 设置启动命令（此时不会执行）
#ENTRYPOINT ["java","-jar","/app/CheckinStatistics-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
#```
#
#2. **然后是 `docker run`** - 运行容器：
#```bash
## 基于构建好的镜像运行容器
#docker run -d myapp:1.0
#```
#
#这时才会：
#- 创建容器
#- 执行 ENTRYPOINT 命令
#- 启动应用程序
#
#
#完整的执行流程：
#```bash
## 1. 构建镜像
#docker build -t myapp:1.0 .
#
## 2. 查看构建的镜像
#docker images
#
## 3. 运行容器
#docker run -d myapp:1.0
#
## 4. 查看运行的容器
#docker ps
#
## 5. 查看容器日志
#docker logs 容器ID
#```
#
#所以，"Run Dockerfile" 实际上是这两个步骤的组合。在 IDEA 中点击 "Run Dockerfile" 时，IDE 会自动执行这两个步骤。
#