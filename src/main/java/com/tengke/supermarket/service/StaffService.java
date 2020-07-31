package com.tengke.supermarket.service;

import com.tengke.supermarket.dto.PageDTO;
import com.tengke.supermarket.dto.ResultDTO;
import com.tengke.supermarket.dto.StaffDTO;
import com.tengke.supermarket.mapper.StaffMapper;
import com.tengke.supermarket.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mr.Chen
 * @Description:
 * @Date:Created in 6:13 2020/7/28
 */
@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;

    public ResultDTO getAllStaffByPage(Integer page, Integer size, String search) {
        //获取总员工数
        int total = staffMapper.getCount(search);
        if(total == 0) {
            return ResultDTO.error("员工不存在");
        }
        //根据page,size,total获取偏移量
        PageDTO<StaffDTO> pageDTO = new PageDTO<>(size, total, page);
        //获取员工列表，封装
        List<StaffDTO> staffList = staffMapper.getStaffsByPage(pageDTO.getStart(), pageDTO.getPageSize(), search);
        pageDTO.setData(staffList);
        return ResultDTO.success("员工信息获取成功", pageDTO);
    }
}
