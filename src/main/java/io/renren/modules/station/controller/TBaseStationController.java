package io.renren.modules.station.controller;


import io.renren.common.utils.R;
import io.renren.modules.station.service.impl.TBaseStationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
@RestController
@RequestMapping("/station/tBaseStation")
public class TBaseStationController {

    @Autowired
    private TBaseStationServiceImpl baseStationService;

    @GetMapping("/search")
    public R search(Double longitude, Double latitude, Double radius) {

        return R.ok().put("stations", baseStationService.search(longitude, latitude, radius));
    }



}

