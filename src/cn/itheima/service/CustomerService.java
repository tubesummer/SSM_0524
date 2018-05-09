package cn.itheima.service;

import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;

import java.util.List;

public interface CustomerService {
    public List<BaseDict> findDictByCode(String code);
    public List<Customer> findCustomerByVo(QueryVo vo);
    public Integer findCustomerByVoCount(QueryVo vo);
    public Customer findCustomerById(Long id);
    public void updateCustomerById(Customer customer);
    public void delCustomerById(Long id);
}
