# Schedule项目技术栈

|核心内容|版本|说明|
|-|-|-|
|JDK|17|使用Java最新LTS版本|
|Spring Boot|3.2.1|核心框架|
|Spring WebFlux|6.1.2|响应式Web框架|
|Spring Data JPA|3.2.1|数据访问层框架|
|MySQL|8.0|数据库|
|Liquibase|4.24.0|数据库版本控制|
|HikariCP|5.1.0|数据库连接池|
|P6Spy|3.9.1|SQL性能监控|
|Apache Kafka|3.6.1|消息中间件|
|Jackson|2.15.3|JSON处理库|
|Hutool|5.8.23|Java工具集|
|Guava|32.1.3|Google核心库|
|SLF4J|2.0.9|日志门面|
|Logback|1.4.14|日志实现|
|JUnit Jupiter|5.10.1|单元测试框架|
|Spring Boot Test|3.2.1|集成测试框架|
|Spring Boot Actuator|3.2.1|应用监控|

## 架构设计

- DDD (Domain-Driven Design) 领域驱动设计
- 六边形架构 (Hexagonal Architecture)
- 响应式编程 (Reactive Programming)
- 事件驱动架构 (Event-Driven Architecture)

## 主要功能

- 分布式ID生成（雪花算法）
- 国际化支持 (i18n)
- 全局异常处理
- 统一响应格式
- 领域事件总线
- 死信队列处理
- SQL性能监控
- 分布式追踪

## 设计模式

- 单例模式 (Singleton Pattern)
- 工厂模式 (Factory Pattern)
- 观察者模式 (Observer Pattern)
- 策略模式 (Strategy Pattern)

## 性能优化

- 自定义类型转换器
- Guava Cache缓存机制
- HikariCP连接池优化
- P6Spy SQL监控
- 响应式非阻塞处理

## 可用性设计

- 优雅停机机制
- 重试机制
- 幂等性控制
- 事件追踪
- 分布式锁

## 开发规范

- 统一的包命名规范 (cn.org.byc.schedule)
- 完整的JavaDoc文档
- 统一的编码规范
- REST API设计规范
- 异常处理规范

## 依赖倒置
1. 解耦：领域层不依赖具体实现，便于更换技术实现
2. 测试性：可以轻松Mock依赖，进行单元测试
3. 维护性：内层代码的修改不会影响外层
4. 灵活性：可以根据需要替换外层实现，而不影响核心业务逻辑
> 记住关键原则: 依赖永远指向内层；接口定义在内层；实现放在外层；通过依赖注入连接各层