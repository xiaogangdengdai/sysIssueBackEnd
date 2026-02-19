package com.qino.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qino.log.common.IssueLogQuery;
import com.qino.log.common.Result;
import com.qino.log.entity.SystemIssueLog;
import com.qino.log.req.SystemIssueLogCreateReq;
import com.qino.log.req.SystemIssueLogUpdateReq;
import com.qino.log.service.SystemIssueLogService;
import com.qino.log.vo.SystemIssueLogVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统问题日志控制器
 *
 * @author wangzhihao
 */
@RestController
@RequestMapping("/api/issue-log")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SystemIssueLogController {

    private final SystemIssueLogService service;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<IPage<SystemIssueLogVO>> page(IssueLogQuery query) {
        IPage<SystemIssueLog> entityPage = service.page(query);

        // 转换为VO
        IPage<SystemIssueLogVO> voPage = entityPage.convert(this::toVO);
        return Result.success(voPage, voPage.getTotal());
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result<SystemIssueLogVO> getById(@PathVariable String id) {
        SystemIssueLog log = service.getById(id);
        return log != null ? Result.success(toVO(log)) : Result.error("记录不存在");
    }

    /**
     * 新增
     */
    @PostMapping
    public Result<SystemIssueLogVO> save(@Valid @RequestBody SystemIssueLogCreateReq req, HttpServletRequest request) {
        SystemIssueLog log = new SystemIssueLog();
        BeanUtils.copyProperties(req, log);

        log.setCreatorIp(getClientIp(request));
        // 只有当status为空时才设置默认值
        if (log.getStatus() == null) {
            log.setStatus(1);
        }

        boolean result = service.save(log);
        return result ? Result.success(toVO(log)) : Result.error("新增失败");
    }

    /**
     * 更新
     */
    @PutMapping
    public Result<Void> update(@Valid @RequestBody SystemIssueLogUpdateReq req, HttpServletRequest request) {
        SystemIssueLog log = new SystemIssueLog();
        BeanUtils.copyProperties(req, log);

        log.setModifyIp(getClientIp(request));
        log.setModifyAt(LocalDateTime.now());

        return service.updateById(log) ? Result.success() : Result.error("更新失败");
    }

    /**
     * 删除单个
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return service.removeById(id) ? Result.success() : Result.error("删除失败");
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        return service.removeByIds(ids) ? Result.success() : Result.error("批量删除失败");
    }

    /**
     * 实体转VO
     */
    private SystemIssueLogVO toVO(SystemIssueLog entity) {
        SystemIssueLogVO vo = new SystemIssueLogVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    /**
     * 获取客户端真实IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
