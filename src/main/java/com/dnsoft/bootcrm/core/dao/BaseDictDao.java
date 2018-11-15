package com.dnsoft.bootcrm.core.dao;

import com.dnsoft.bootcrm.core.pojo.BaseDict;

import java.util.List;

public interface BaseDictDao {
    //根据类别代码查询数据字典
    public List<BaseDict> selectBaseDictByTypeCode(String typecode);

    //根据类别名称筛选在下拉框中
    public List<BaseDict> selectBaseDictByTypeName();
    //项目名称显示在下拉框中
    public List<BaseDict> selectBaseDictByItemName();
   /*查询数据字典表中全部内容*/
    public List<BaseDict> findBaseDictALL(BaseDict baseDict);

    /*根据条件查询数据字典的总数*/
    public Integer findCount(BaseDict baseDict);

    //表中最大值
    public Integer dict_Max();

    //添加数据
    public int addBaseDict(BaseDict baseDict);


    //修改操作,先根据id获取到其值再进行修改操作
    public BaseDict findBaseDictById(Integer id);
    public int updateBaseDict(BaseDict baseDict);


    //逻辑删除(假删除)，更改状态值使其不显示
    public int deleteBaseDict(BaseDict baseDict);

}
