package com.dnsoft.bootcrm.core.web.controller;


import com.dnsoft.bootcrm.common.utils.Page;
import com.dnsoft.bootcrm.core.pojo.BaseDict;
import com.dnsoft.bootcrm.core.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BaseDictController {

    @Autowired
    private BaseDictService baseDictService;

    @RequestMapping(value = "/search.action")
    public String findAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "6") Integer rows,String dict_type_name,String dict_item_name,Model model){


        BaseDict baseDict=new BaseDict();

        //查询到的数据集合
        Page<BaseDict> page1=baseDictService.findBaseDictALL(page,rows,dict_type_name,dict_item_name);
        model.addAttribute("page",page1);


        //在下拉框中显示类别名称不同的值(一级目录)
        List<BaseDict> baseDictTypeName=baseDictService.findBaseDictByTypeName();
        model.addAttribute("baseDictTypeName",baseDictTypeName);


        //项目名称的下拉框(二级目录)
        List<BaseDict> baseDictItemName=baseDictService.findBaseDictByItemName();
        model.addAttribute("baseDictItemName",baseDictItemName);


        //对查询的第一级目录进行遍历是否有项目名称与其对应
        for (int i=0;i<baseDictTypeName.size();i++){              //(一级目录)从0开始依次遍历去重之后的9个类别名称和项目名称是否对应
            for(int x=0;x<baseDictItemName.size();x++){          //依次比对项目名称的数量的次数(9轮循环，1轮次数为二级目录的数量)
                if(baseDictTypeName.get(i).getDict_type_name().equals(baseDictItemName.get(x).getDict_type_name())){   //从第1个项目名称开始使其与第1个类别名称进行比对，
                   baseDictTypeName.get(i).getBaseDictList().add(baseDictItemName.get(x));  //如果项目名称与前面的类别名称对应就把该项目名称添加到对应的类别名称里
                }
            }
        }
        //遍历其id是否有与其项目名称对应
        for(int i=0;i<baseDictTypeName.size();i++){
            for(int x=0;x<baseDictItemName.size();x++){
                if(baseDictTypeName.get(i).getDict_type_name().equals(baseDictItemName.get(x).getDict_id())){
                    baseDictTypeName.get(i).getBaseDictList().add(baseDictItemName.get(x));
                }
            }
        }

        //将以上对照存储的值转换成一个json数组并进行拼接；例如:[   {  "dict_type_name": "客户级别","dict_item_list":[{"dict_item":"普通客户"},{"dict_item":"vip客户"}]  },{  {"客户信息来源","dict_item_list:"[{"dict_item":"电话营销"},{"dict_item":"网络营销"}]  }]
        String data="[";
        //对集合进行遍历
        List<BaseDict> list=baseDictTypeName;  //将一级目录查询到的数据转换成集合的形式
       for (int i=0;i<list.size();i++){
           if(i!=0){
               data+=",";
           }
           data+="{";
           data+="dict_type_name:'";
           data+=list.get(i).getDict_type_name();
           for (int x=0;x<list.get(i).getBaseDictList().size();x++) {   //拼接的是某个一级目录里的二级目录的值
               if (x == 0) {
                   data += "',dict_item_list:[";
               }
               if (x!=0) {
                   data += ",";
               }
               data+="{";
               data+="dict_item_name:'";
               data+=list.get(i).getBaseDictList().get(x).getDict_item_name();
               data+="',dict_id:'";
               data+=list.get(i).getBaseDictList().get(x).getDict_id();
               data+="'}";
           }
           data+="]}";
       }
        data+="]";
        System.out.println(data);
        model.addAttribute("data",data);  //方便在前端拿到值


        //获取查询总条数
        Integer count=baseDictService.findBaseCount(baseDict);
        model.addAttribute("count",count);
        return "baseDict";
    }


    @RequestMapping(value = "/add.action")
    @ResponseBody
    public String addBase_dict(BaseDict baseDict){

        //dict_ID获取的最大值再加一，即为插入数据时生成的id值
        int dict_ID=baseDictService.dict_Max()+1;

        baseDict.setDict_id(Integer.toString(dict_ID));//用户插入数据时生成的id值并将整型转字符串类型

        int rows=baseDictService.addBase(baseDict);
        if(rows > 0){
            return "OK";
        }else{
            return "FAIL";
        }
    }

    @RequestMapping(value = "/getBaseDictById.action")
    @ResponseBody
    public BaseDict findBase_DictById(Integer id){
        BaseDict baseDict=baseDictService.findBaseDictById(id);
        return baseDict;
    }
    //执行修改操作
    @RequestMapping(value = "/editBaseDict.action")
    @ResponseBody
    public String editBase_Dict(BaseDict baseDict){
        int rows= baseDictService.updateBaseDict(baseDict);
        if(rows>0){
            return "OK";
        }else{
            return "FAIL";
        }
    }

    //假删除，更改状态值使其不显示
    @RequestMapping(value = "/deleteBase_Dict.action")
    @ResponseBody
    public String deleteBase_Dict(BaseDict baseDict){
        baseDict.setDict_enable("0");                        //改为0之后不在页面显示
        int rows=baseDictService.deleteBaseDict(baseDict);
        if(rows>0){
            return "OK";
        }else{
            return "FAIL";
        }
    }

}
