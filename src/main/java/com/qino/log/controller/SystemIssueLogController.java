package com.qino.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qino.log.common.IssueLogQuery;
import com.qino.log.common.Result;
import com.qino.log.entity.SystemIssueLog;
import com.qino.log.service.SystemIssueLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统问题日志控制器
 */
@RestController
@RequestMapping("/api/issue-log")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SystemIssueLogController {
    
    private final SystemIssueLogService service;
    
    @GetMapping("/page")
    public Result<IPage<SystemIssueLog>> page(IssueLogQuery query) {
        IPage<SystemIssueLog> page = service.page(query);
        return Result.success(page, page.getTotal());
    }
    
    @GetMapping("/{id}")
    public Result<SystemIssueLog> getById(@PathVariable String id) {
        SystemIssueLog log = service.getById(id);
        return log != null ? Result.success(log) : Result.error("记录不存在");
    }
    
    @PostMapping
    public Result<SystemIssueLog> save(@Valid @RequestBody SystemIssueLog log) {
        return service.save(log) ? Result.success(log) : Result.error("新增失败");
    }
    
    @PutMapping
    public Result<Void> update(@Valid @RequestBody SystemIssueLog log) {
        return service.updateById(log) ? Result.success() : Result.error("更新失败");
    }
    
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return service.removeById(id) ? Result.success() : Result.error("删除失败");
    }
    
    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        return service.removeByIds(ids) ? Result.success() : Result.error("批量删除失败");
    }
}
