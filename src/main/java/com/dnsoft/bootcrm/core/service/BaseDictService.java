package com.dnsoft.bootcrm.core.service;

import com.dnsoft.bootcrm.common.utils.Page;
import com.dnsoft.bootcrm.core.pojo.BaseDict;

import java.util.List;

public interface BaseDictService {
    //根据类别代码查询
    public List<BaseDict> findBaseDictByTypeCode(String typecode);

    //根据类别名称筛选
    public List<BaseDict> findBaseDictByTypeName();

    //项目名称
    public List<BaseDict> findBaseDictByItemName();

    //根据条件查询数据表所有
    public Page<BaseDict> findBaseDictALL(Integer page, Integer rows,String dict_type_name,String dict_item_name);

    //数据字典表总数
    public Integer findBaseCount(BaseDict baseDict);

   //表中ID最大值
    public Integer dict_Max();

    //添加数据
    public int addBase(BaseDict baseDict);

    //修改根据id查询内容
    public BaseDict findBaseDictById(Integer id);
    public int updateBaseDict(BaseDict baseDict);

    //逻辑删除(假删除)，更改状态值使其不显示
    public int deleteBaseDict(BaseDict baseDict);
}
