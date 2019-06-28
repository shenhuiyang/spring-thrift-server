# spring-thriftpath.thrift-gs.server
spring集成thrift-gs.server

## 生成thrift文件对应的java接口文件
sh ./thriftpath.thrift-gen.sh

## 编译运行
mvn package && java -jar target/spring-thriftpath.thrift-app-0.1.0.jar