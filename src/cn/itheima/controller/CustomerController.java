package cn.itheima.controller;

import cn.itcast.utils.Page;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;
import cn.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Value("${customer.dict.source}")
    private String source;
    @Value("${customer.dict.industry}")
    private String industry;
    @Value("${customer.dict.level}")
    private String level;

    @RequestMapping("/list")
    public String list(QueryVo vo,Model model) throws Exception{
        //????
        List<BaseDict> sourceList  = customerService.findDictByCode(source);
        //????
        List<BaseDict> industryList  = customerService.findDictByCode(industry);
        //????
        List<BaseDict> levelList  = customerService.findDictByCode(level);

        if(vo.getCustName() != null){
            vo.setCustName(new String(vo.getCustName().getBytes("iso8859-1"),"utf-8"));
        }
       if(vo.getPage() == null){
           vo.setPage(1);
       }
        System.out.println(vo.getCustName());
       //????????
       vo.setStart((vo.getPage()-1)*vo.getSize());


       //?????????
        List<Customer> resultList = customerService.findCustomerByVo(vo);
        Integer count = customerService.findCustomerByVoCount(vo);

        Page<Customer> page = new Page<Customer>();
        page.setTotal(count);           //????
        page.setSize(vo.getSize());     //??????
        page.setPage(vo.getPage());     //????
        page.setRows(resultList);       //????

        model.addAttribute("page",page);


        //??????????
        model.addAttribute("fromType",sourceList);
        model.addAttribute("industryType",industryList);
        model.addAttribute("levelType",levelList);

        //??????????
        model.addAttribute("custName",vo.getCustName());
        model.addAttribute("custSource",vo.getCustSource());
        model.addAttribute("custIndustry",vo.getCustIndustry());
        model.addAttribute("custLevel",vo.getCustLevel());

        return "customer";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Customer detail(Long id) throws Exception{
        Customer customer = customerService.findCustomerById(id);
        return customer;
    }

    @RequestMapping("/update")
    public String update(Customer customer) throws Exception{
        customerService.updateCustomerById(customer);
        return "customer";
    }

    @RequestMapping("/delete")
    public String delete(Long id) throws Exception{
        customerService.delCustomerById(id);
        return "customer";
    }
}
