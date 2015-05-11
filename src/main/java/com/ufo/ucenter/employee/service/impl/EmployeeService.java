package com.ufo.ucenter.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.ucenter.employee.dao.interfaces.EmployeeDao;
import com.ufo.ucenter.employee.entity.Employee;
import com.ufo.ucenter.employee.service.interfaces.IEmployeeService;
import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.service.IBaseSpringDataService;

@Service
@Transactional
public class EmployeeService extends BaseSpringDataService<Employee, Integer> implements IEmployeeService {
    
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public BaseDao<Employee, Integer> getEntityDao() {
        return employeeDao;
    }
}
