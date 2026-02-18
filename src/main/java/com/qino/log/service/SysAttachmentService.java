package com.qino.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qino.log.entity.SysAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 附件服务接口
 */
public interface SysAttachmentService extends IService<SysAttachment> {

    /**
     * 上传附件
     * @param targetType 目标类型
     * @param targetId 目标ID
     * @param file 文件
     * @param type 附件类型 (1-问题日志, 可扩展), 为null时默认为1
     * @return 附件实体
     */
    SysAttachment upload(String targetType, String targetId, MultipartFile file, Integer type);

    /**
     * 根据目标查询附件列表
     * @param targetType 目标类型
     * @param targetId 目标ID
     * @param type 附件类型, 为null时查询所有类型
     * @return 附件列表
     */
    List<SysAttachment> listByTarget(String targetType, String targetId, Integer type);

    /**
     * 删除附件（同时删除物理文件）
     */
    void deleteWithFile(Long id);

    /**
     * 根据目标删除所有附件
     */
    void deleteByTarget(String targetType, String targetId);

    /**
     * 获取文件内容（用于预览）
     */
    String getFileContent(Long id);
}
