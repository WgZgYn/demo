## Spring Boot介绍
### 自动装配
### 

## 模块介绍
### controller
> 与网页API数据请求的直接交互
内部类中的特点
* 类含有 @RestController @Controller 注解
* 类方法有 @GetMapping @PostMapping 注解
* 类方法使用 `Result` 或 `Response`作为返回值

### service
> 业务逻辑在这里编写
* 类含有 @Service 注解
* 大致的服务
  * 处理自身的对外的http请求
    * 认证
    * 接收服务器SSE推流 -- 需要稳定 
    * 第一代通讯版本
      * 拉取服务器设备任务 -- 需要重构
    * 转发SSE -- 逐渐遗弃
  * mdns
    * 局域网内服务广播
      * 为局域网内APP与网页提供支持
  * mqtt
    * 第二代通讯版本 
      * 设备联网后可以直接接受publisher的任务命令 -- 安全性有待考究
    * 客户端
  * tcp
    * 处理与设备的tcp交互
      * 绑定
  * 
### config
> 这里进行服务的配置
### repository
> 数据库持久层

### dao
> 数据库类型映射

### dto
> 网络传输对象

### entity

### object

### security


### util
在这里编写通用的工具类
如 `Result` `Response` 作为Controller的通用json返回对象
* `Result` 不含数据的返回 
* `Response` 含有数据的返回