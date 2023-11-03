# 设置基础镜像为官方的 OpenJDK 8-Alpine
FROM openjdk:8-jre

# 设置工作目录
WORKDIR /app

# 复制应用的jar文件到容器中
COPY target/dream-fulfill-0.0.1-SNAPSHOT.jar /app/dream-fulfill-0.0.1-SNAPSHOT.jar

# 暴露8080端口
EXPOSE 8080

# 定义容器启动时运行的命令
CMD ["java", "-jar", "/app/dream-fulfill-0.0.1-SNAPSHOT.jar"]