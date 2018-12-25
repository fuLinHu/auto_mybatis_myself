spring:
  datasource:
     driver-class-name: com.mysql.cj.jdbc.Driver
     url: jdbc:mysql://localhost:3306/test?serverTimezone=UTC
     username: root
     password: root
  thymeleaf:
     prefix: classpath:/templates/thymeleaf
     suffix: .html
     mode: HTML5
     encoding: UTF-8
     cache: false #热部署文件，页面不产生缓存，及时更新
server:
    port: 8087



