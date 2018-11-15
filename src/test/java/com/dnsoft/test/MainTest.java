package com.dnsoft.test;


import com.dnsoft.bootcrm.core.dao.UserDao;
import com.dnsoft.bootcrm.core.pojo.BaseDict;
import com.dnsoft.bootcrm.core.pojo.User;
import com.dnsoft.bootcrm.core.service.BaseDictService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MainTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BaseDictService baseDictService;

    @Test
    public void TestUserDao(){
        User user=userDao.findUser("admin","123456");
        Assert.assertEquals("畢先生",user.getUser_name());

    }
    @Test
    public void TestBaseDict(){
        BaseDict baseDict=new BaseDict();
        Assert.assertNotNull(baseDict);
    }

    @Test
    public void TestBaseDictService(){
        List<BaseDict> baseDictList=baseDictService.findBaseDictByTypeCode("001");
        Assert.assertEquals("客户行业",baseDictList.get(2).getDict_type_name());

    }

    @Test
    public void TestBaseDict(BaseDict baseDict) {
        BaseDict baseDict1 = new BaseDict();
        int dict_ID = baseDictService.dict_Max() + 1;
        baseDict1.setDict_id(Integer.toString(dict_ID));
        baseDict1.setDict_type_code("003");
        baseDict1.setDict_type_name("公司性质");
        baseDict1.setDict_item_name("黑作坊");
        baseDict1.setDict_sort(6);
        baseDict1.setDict_enable("1");
        baseDictService.addBase(baseDict1);
    }
}
