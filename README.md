# 系统日志登记系统 - 后端

基于 Spring Boot 3 + MyBatis Plus 的系统日志管理后端服务。

## 技术栈

- Java 17+
- Spring Boot 3.2.0
- MyBatis Plus 3.5.5
- MySQL 8.0
- Druid 连接池

## 启动前准备

1. 确保数据库 `oxi_ethetics` 存在
2. 执行 `src/main/resources/db/schema.sql` 创建表结构

## 启动方式

```bash
mvn spring-boot:run
```

## API 接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | /api/log/page | 分页查询日志 |
| GET | /api/log/{id} | 查询日志详情 |
| POST | /api/log | 新增日志 |
| PUT | /api/log | 更新日志 |
| DELETE | /api/log/{id} | 删除日志 |
| DELETE | /api/log/batch | 批量删除 |
