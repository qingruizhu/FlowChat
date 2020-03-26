package com.flow.bgd.mapper;

import com.flow.bgd.model.OnLine;
import com.flow.bgd.model.OnLineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnLineMapper {
    long countByExample(OnLineExample example);

    int deleteByExample(OnLineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OnLine record);

    int insertSelective(OnLine record);

    List<OnLine> selectByExample(OnLineExample example);

    OnLine selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OnLine record, @Param("example") OnLineExample example);

    int updateByExample(@Param("record") OnLine record, @Param("example") OnLineExample example);

    int updateByPrimaryKeySelective(OnLine record);

    int updateByPrimaryKey(OnLine record);
}