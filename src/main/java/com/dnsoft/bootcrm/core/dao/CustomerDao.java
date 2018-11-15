package com.dnsoft.bootcrm.core.dao;

import com.dnsoft.bootcrm.core.pojo.Customer;

import java.util.List;

public interface CustomerDao {

    //客户列表
    public List<Customer> selectCustomerList(Customer customer);
    //客户总数
    public Integer selectCustomerListCount(Customer customer);
   //新建客户
    public  int createCustomer(Customer customer);

    //修改客户,先获取id
    public  Customer getCustomerById(Integer id);

    public int updateCustomer(Customer customer);

    //删除客户
    public int deleteCustomer(Integer id);

}
