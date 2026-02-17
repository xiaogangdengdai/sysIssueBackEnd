package com.qino.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qino.log.common.LogQuery;
import com.qino.log.common.Result;
import com.qino.log.entity.SysLog;
import com.qino.log.service.SysLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统日志控制器
 */
@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SysLogController {
    
    private final SysLogService sysLogService;
    
    /**
     * 分页查询日志列表
     */
    @GetMapping("/page")
    public Result<IPage<SysLog>> page(LogQuery query) {
        IPage<SysLog> page = sysLogService.page(query);
        return Result.success(page, page.getTotal());
    }
    
    /**
     * 根据ID查询日志详情
     */
    @GetMapping("/{id}")
    public Result<SysLog> getById(@PathVariable Long id) {
        SysLog sysLog = sysLogService.getById(id);
        return sysLog != null ? Result.success(sysLog) : Result.error("日志不存在");
    }
    
    /**
     * 新增日志
     */
    @PostMapping
    public Result<SysLog> save(@Valid @RequestBody SysLog sysLog) {
        return sysLogService.save(sysLog) ? Result.success(sysLog) : Result.error("新增失败");
    }
    
    /**
     * 更新日志
     */
    @PutMapping
    public Result<Void> update(@Valid @RequestBody SysLog sysLog) {
        return sysLogService.updateById(sysLog) ? Result.success() : Result.error("更新失败");
    }
    
    /**
     * 删除日志
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        return sysLogService.removeById(id) ? Result.success() : Result.error("删除失败");
    }
    
    /**
     * 批量删除日志
     */
    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<Long> ids) {
        return sysLogService.removeByIds(ids) ? Result.success() : Result.error("批量删除失败");
    }
}
