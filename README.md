# spring-thrift
spring集成thrift

## 生成thrift文件对应的java接口文件
sh ./thrift-gen.sh

## 编译运行
mvn package && java -jar target/spring-thrift-app-0.1.0.jar