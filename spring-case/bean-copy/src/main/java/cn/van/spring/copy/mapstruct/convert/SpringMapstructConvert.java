package cn.van.spring.copy.mapstruct.convert;

import cn.van.spring.copy.mapstruct.domain.UserInfoDTO;
import cn.van.spring.copy.mapstruct.entity.UserExtInfoDO;
import cn.van.spring.copy.mapstruct.entity.UserInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Copyright (C), 2015-2019, 风尘博客
 * 公众号 : 风尘博客
 * FileName: SpringMapstructConvert
 *
 * @author: Van
 * Date:     2019-11-08 20:56
 * Description: 注入 spring 的映射类
 * Version： V1.0
 */
@Mapper(componentModel = "spring")
public interface SpringMapstructConvert {

    /**
     * 一对一映射
     * @param userInfoDO
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            // 自定义转换时间格式，如果为空，给予默认值 "-"
            @Mapping(source = "gmtBroth", target = "brothStr", dateFormat = "yyyy-MM-dd",defaultValue = "-"),
            // 嵌套映射
            @Mapping(source = "role.roleName", target = "roleName"),
            // 忽略不映射的字段
            @Mapping(target = "password", ignore = true),
            // 自定义映射
            @Mapping(target = "phoneNum", expression = "java(cn.van.spring.copy.mapstruct.util.Base64Util.encode(userInfoDO.getPhoneNum()))"),
    })
    UserInfoDTO doToDTO(UserInfoDO userInfoDO);

    /**
     * 集合映射(映射关系继承【一对一映射】)
     * @param userInfoDOS
     * @return
     */
    List<UserInfoDTO> doSToDTOS(List<UserInfoDO> userInfoDOS);

    /**
     * 多参数映射
     * @param userInfoDO
     * @param userExtInfoDO
     * @return
     */
    @Mappings({
            @Mapping(source = "userInfoDO.id", target = "userId"),
            @Mapping(source = "userInfoDO.gmtBroth", target = "brothStr", dateFormat = "yyyy-MM-dd",defaultValue = "-"),
            @Mapping(source = "userInfoDO.role.roleName", target = "roleName"),
            // 忽略不映射的字段
            @Mapping(target = "password", ignore = true),
            // 自定义映射
            @Mapping(target = "phoneNum", expression = "java(cn.van.spring.copy.mapstruct.util.Base64Util.encode(userInfoDO.getPhoneNum()))"),
            @Mapping(source = "userExtInfoDO.favorite", target = "favorite"),
    })
    UserInfoDTO doToDtoMulti(UserInfoDO userInfoDO, UserExtInfoDO userExtInfoDO);
}