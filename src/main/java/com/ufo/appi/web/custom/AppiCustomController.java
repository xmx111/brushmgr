package com.ufo.appi.web.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufo.appi.core.AppiBaseController;
import com.ufo.config.sys.entity.Manager;
import com.ufo.core.service.IBaseSpringDataService;


public class AppiCustomController extends AppiBaseController<Manager, Integer> {

	public AppiCustomController() {

	}

	@Override
	protected IBaseSpringDataService<Manager, Integer> getEntityService() {
		return null;
	}

}
