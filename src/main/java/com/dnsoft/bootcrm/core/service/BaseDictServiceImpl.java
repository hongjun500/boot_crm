package com.dnsoft.bootcrm.core.service;

import com.dnsoft.bootcrm.common.utils.Page;
import com.dnsoft.bootcrm.core.dao.BaseDictDao;
import com.dnsoft.bootcrm.core.pojo.BaseDict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(value = "baseDictService")
@Transactional
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictDao baseDictDao;

    @Override
    public List<BaseDict> findBaseDictByTypeCode(String typecode) {
        return baseDictDao.selectBaseDictByTypeCode(typecode);
    }

    @Override
    public List<BaseDict> findBaseDictByTypeName() {
        return baseDictDao.selectBaseDictByTypeName();
    }

    @Override
    public List<BaseDict> findBaseDictByItemName() {
        return baseDictDao.selectBaseDictByItemName();
    }

    @Override
    public Page<BaseDict> findBaseDictALL(Integer page, Integer rows,String dict_type_name,String dict_item_name) {
        BaseDict baseDict=new BaseDict();
        //非空判断
        if (StringUtils.isNotBlank(dict_type_name)){
            baseDict.setDict_type_name(dict_type_name);
        }
        //判断数据项目名称
        if (StringUtils.isNotBlank(dict_item_name)){
            baseDict.setDict_item_name(dict_item_name);
        }

        //当前页
        baseDict.setStart((page-1)*rows);
        //每页数
        baseDict.setRows(rows);

        List<BaseDict> baseDicts=baseDictDao.findBaseDictALL(baseDict);
        Integer count=baseDictDao.findCount(baseDict);

        //创建page返回对象
        Page<BaseDict> result=new Page<>();
        result.setPage(page);
        result.setRows(baseDicts);
        result.setSize(rows);
        result.setTotal(count);
        return result;

    }



    @Override
    public Integer findBaseCount(BaseDict baseDict) {
        return baseDictDao.findCount(baseDict);
    }

    @Override
    public Integer dict_Max() {
        return baseDictDao.dict_Max();
    }

    @Override
    public int addBase(BaseDict baseDict) {
        return baseDictDao.addBaseDict(baseDict);
    }

    @Override
    public BaseDict findBaseDictById(Integer id) {
        return baseDictDao.findBaseDictById(id);
    }

    @Override
    public int updateBaseDict(BaseDict baseDict) {
        return baseDictDao.updateBaseDict(baseDict);
    }

    @Override
    public int deleteBaseDict(BaseDict baseDict) {
        return baseDictDao.deleteBaseDict(baseDict);
    }
}
