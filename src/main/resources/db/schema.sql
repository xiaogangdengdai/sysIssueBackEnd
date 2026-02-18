-- 系统日志表
CREATE TABLE IF NOT EXISTS `sys_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `log_type` VARCHAR(20) DEFAULT 'INFO' COMMENT '日志类型（INFO/WARN/ERROR）',
    `title` VARCHAR(200) DEFAULT NULL COMMENT '日志标题',
    `content` TEXT COMMENT '日志内容',
    `operator` VARCHAR(50) DEFAULT NULL COMMENT '操作人',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT '操作IP',
    `request_method` VARCHAR(10) DEFAULT NULL COMMENT '请求方法',
    `request_url` VARCHAR(500) DEFAULT NULL COMMENT '请求URL',
    `request_params` TEXT COMMENT '请求参数',
    `response_result` TEXT COMMENT '响应结果',
    `execute_time` BIGINT DEFAULT NULL COMMENT '执行时间（毫秒）',
    `status` TINYINT DEFAULT 0 COMMENT '状态（0成功 1失败）',
    `error_msg` TEXT COMMENT '错误信息',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT DEFAULT 0 COMMENT '删除标记（0未删除 1已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_log_type` (`log_type`),
    KEY `idx_operator` (`operator`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统日志表';

-- 系统问题日志表
CREATE TABLE IF NOT EXISTS `system_issue_log` (
    `id` CHAR(36) NOT NULL COMMENT 'ID',
    `type` TINYINT(3) NULL DEFAULT '1' COMMENT '类型：1.bug修复 2.新功能开发 3.原有功能改造 4.页面原型快速实现 5.后台方法深度分析 6.前端功能模块深度分析',
    `description` TEXT NULL DEFAULT NULL COMMENT '问题详细描述',
    `create_table_sql` TEXT NULL DEFAULT NULL COMMENT 'SQL建表语句',
    `new_requirement` TEXT NULL DEFAULT NULL COMMENT '新需求描述',
    `before_transformation` TEXT NULL DEFAULT NULL COMMENT '改造前功能描述',
    `transformation` TEXT NULL DEFAULT NULL COMMENT '改造后的目标',
    `business_context` TEXT NULL DEFAULT NULL COMMENT '业务介绍',
    `remark` TEXT NULL DEFAULT NULL COMMENT '备注',
    `status` TINYINT(3) NULL DEFAULT '1' COMMENT '1:待处理, 2:处理中, 3:已完成, 4:处理失败',
    `creator` VARCHAR(50) NULL DEFAULT NULL COMMENT '创建人（用户名或ID）',
    `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_created_at` (`created_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统问题登记表';

-- 插入测试数据
INSERT INTO `sys_log` (`log_type`, `title`, `content`, `operator`, `ip_address`, `request_method`, `request_url`, `status`) VALUES
('INFO', '系统启动', '系统日志登记系统启动成功', '系统', '127.0.0.1', 'GET', '/api/health', 0),
('INFO', '用户登录', '用户奇诺登录系统', '奇诺', '192.168.2.100', 'POST', '/api/login', 0),
('WARN', '密码错误', '用户尝试登录失败，密码错误', '未知用户', '192.168.2.101', 'POST', '/api/login', 1);
