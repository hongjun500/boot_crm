package com.dnsoft.bootcrm.core.service;

import com.dnsoft.bootcrm.common.utils.Page;
import com.dnsoft.bootcrm.core.pojo.Customer;

public interface CustomerService {
        // 查询客户列表
        public Page<Customer> findCustomerList(Integer page, Integer rows,
                                               String custName, String custSource,
                                               String custIndustry, String custLevel);

        public int createCustomer(Customer customer);

        //修改客户
        public Customer getCustomerById(Integer id);
        public int updateCustomer(Customer customer);

        //删除客户
        public int deleteCustomer(Integer id);
}


