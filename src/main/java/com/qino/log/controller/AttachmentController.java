package com.qino.log.controller;

import com.qino.log.common.Result;
import com.qino.log.entity.SysAttachment;
import com.qino.log.service.SysAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 附件控制器
 */
@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    private SysAttachmentService attachmentService;

    /**
     * 上传附件
     */
    @PostMapping("/upload")
    public Result<SysAttachment> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("targetType") String targetType,
            @RequestParam("targetId") String targetId,
            @RequestParam(value = "type", defaultValue = "1") Integer type) {
        SysAttachment attachment = attachmentService.upload(targetType, targetId, file, type);
        return Result.success(attachment);
    }

    /**
     * 查询附件列表
     */
    @GetMapping("/list")
    public Result<List<SysAttachment>> list(
            @RequestParam("targetType") String targetType,
            @RequestParam("targetId") String targetId,
            @RequestParam(value = "type", required = false) Integer type) {
        List<SysAttachment> attachments = attachmentService.listByTarget(targetType, targetId, type);
        return Result.success(attachments);
    }

    /**
     * 下载附件
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<FileSystemResource> download(@PathVariable Long id) {
        SysAttachment attachment = attachmentService.getById(id);
        if (attachment == null) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(attachment.getFilePath());
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String encodedFileName = URLEncoder.encode(attachment.getFileName(), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .body(resource);
    }

    /**
     * 预览附件
     */
    @GetMapping("/preview/{id}")
    public Result<Map<String, Object>> preview(@PathVariable Long id) {
        SysAttachment attachment = attachmentService.getById(id);
        if (attachment == null) {
            return Result.error("附件不存在");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("fileName", attachment.getFileName());
        result.put("fileExt", attachment.getFileExt());
        result.put("fileType", attachment.getFileType());

        // 判断是否为图片
        String fileExt = attachment.getFileExt().toLowerCase();
        if (fileExt.equals("jpg") || fileExt.equals("jpeg") || fileExt.equals("png")
                || fileExt.equals("gif") || fileExt.equals("webp")) {
            result.put("type", "image");
            result.put("url", "/api/attachment/download/" + id);
        } else {
            // 文本文件返回内容
            result.put("type", "text");
            result.put("content", attachmentService.getFileContent(id));
        }

        return Result.success(result);
    }

    /**
     * 删除附件
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        attachmentService.deleteWithFile(id);
        return Result.success();
    }
}
