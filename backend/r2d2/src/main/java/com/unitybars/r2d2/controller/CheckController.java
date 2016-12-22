package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.CheckLog;
import com.unitybars.r2d2.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg.nestyuk
 * Date: 21-Dec-16.
 */
@RestController
@RequestMapping("/api/v1/check")
public class CheckController {

    @Autowired
    private CheckService checkService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public CheckLog getAllServices() {
        return checkService.startCheck();
    }
}