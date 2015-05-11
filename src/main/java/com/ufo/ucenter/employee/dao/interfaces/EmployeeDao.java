package com.ufo.ucenter.employee.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.ufo.ucenter.employee.entity.Employee;
import com.ufo.core.dao.BaseDao;

@Repository
public interface EmployeeDao extends BaseDao<Employee, Integer> {

}
