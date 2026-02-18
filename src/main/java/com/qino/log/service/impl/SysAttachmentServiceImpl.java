package com.qino.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qino.log.entity.SysAttachment;
import com.qino.log.mapper.SysAttachmentMapper;
import com.qino.log.service.SysAttachmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 附件服务实现类
 */
@Service
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentMapper, SysAttachment> implements SysAttachmentService {

    @Value("${attachment.upload.path:/home/wzh/codes/uploadFiles}")
    private String uploadBasePath;

    // 允许的文件扩展名
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
            "txt", "md", "json", "xml", "yaml", "yml", "log", "csv",
            "jpg", "jpeg", "png", "gif", "webp"
    );

    // 文本文件扩展名
    private static final List<String> TEXT_EXTENSIONS = Arrays.asList(
            "txt", "md", "json", "xml", "yaml", "yml", "log", "csv"
    );

    @Override
    public SysAttachment upload(String targetType, String targetId, MultipartFile file, Integer type) {
        // 获取原始文件名和扩展名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new RuntimeException("文件名不能为空");
        }

        String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

        // 校验文件类型
        if (!ALLOWED_EXTENSIONS.contains(fileExt)) {
            throw new RuntimeException("不支持的文件类型: " + fileExt);
        }

        // 创建目标目录
        String targetDir = uploadBasePath + File.separator + targetId;
        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 生成唯一文件名，保留原始文件名
        String newFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 8) + "_" + originalFilename;
        String filePath = targetDir + File.separator + newFileName;

        // 保存文件
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("文件保存失败: " + e.getMessage());
        }

        // 计算文件MD5
        String fileMd5 = null;
        try {
            fileMd5 = calculateMD5(file.getBytes());
        } catch (IOException e) {
            // MD5计算失败不影响上传
        }

        // 保存附件记录
        SysAttachment attachment = new SysAttachment();
        attachment.setTargetType(targetType);
        attachment.setTargetId(targetId);
        attachment.setFileName(originalFilename);
        attachment.setFilePath(filePath);
        attachment.setFileSize(file.getSize());
        attachment.setFileType(file.getContentType());
        attachment.setFileExt(fileExt);
        attachment.setFileMd5(fileMd5);
        attachment.setType(type != null ? type : 1);
        attachment.setSortOrder(0);

        save(attachment);

        return attachment;
    }

    /**
     * 计算字节数组的MD5值
     */
    private String calculateMD5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Override
    public List<SysAttachment> listByTarget(String targetType, String targetId, Integer type) {
        LambdaQueryWrapper<SysAttachment> wrapper = new LambdaQueryWrapper<SysAttachment>()
                .eq(SysAttachment::getTargetType, targetType)
                .eq(SysAttachment::getTargetId, targetId);
        if (type != null) {
            wrapper.eq(SysAttachment::getType, type);
        }
        wrapper.orderByAsc(SysAttachment::getSortOrder)
               .orderByAsc(SysAttachment::getId);
        return list(wrapper);
    }

    @Override
    public void deleteWithFile(Long id) {
        SysAttachment attachment = getById(id);
        if (attachment != null) {
            // 删除物理文件
            File file = new File(attachment.getFilePath());
            if (file.exists()) {
                file.delete();
            }
            // 删除数据库记录
            removeById(id);
        }
    }

    @Override
    public void deleteByTarget(String targetType, String targetId) {
        List<SysAttachment> attachments = listByTarget(targetType, targetId, null);
        for (SysAttachment attachment : attachments) {
            deleteWithFile(attachment.getId());
        }
    }

    @Override
    public String getFileContent(Long id) {
        SysAttachment attachment = getById(id);
        if (attachment == null) {
            throw new RuntimeException("附件不存在");
        }

        String fileExt = attachment.getFileExt().toLowerCase();
        if (!TEXT_EXTENSIONS.contains(fileExt)) {
            throw new RuntimeException("该文件类型不支持文本预览");
        }

        try {
            Path path = Paths.get(attachment.getFilePath());
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败: " + e.getMessage());
        }
    }
}
