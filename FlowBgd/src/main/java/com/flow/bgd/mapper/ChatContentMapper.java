package com.flow.bgd.mapper;

import com.flow.bgd.model.ChatContent;
import com.flow.bgd.model.ChatContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatContentMapper {
    long countByExample(ChatContentExample example);

    int deleteByExample(ChatContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChatContent record);

    int insertSelective(ChatContent record);

    List<ChatContent> selectByExample(ChatContentExample example);

    ChatContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChatContent record, @Param("example") ChatContentExample example);

    int updateByExample(@Param("record") ChatContent record, @Param("example") ChatContentExample example);

    int updateByPrimaryKeySelective(ChatContent record);

    int updateByPrimaryKey(ChatContent record);
}