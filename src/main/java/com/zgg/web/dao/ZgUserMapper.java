package com.zgg.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import com.zgg.web.pojo.ZgUser;
import com.zgg.web.pojo.ZgUserExample;

@Mapper
public interface ZgUserMapper {
    int countByExample(ZgUserExample example);

    int deleteByExample(ZgUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZgUser record);

    int insertSelective(ZgUser record);

    List<ZgUser> selectByExample(ZgUserExample example);

    ZgUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZgUser record, @Param("example") ZgUserExample example);

    int updateByExample(@Param("record") ZgUser record, @Param("example") ZgUserExample example);

    int updateByPrimaryKeySelective(ZgUser record);

    int updateByPrimaryKey(ZgUser record);
}