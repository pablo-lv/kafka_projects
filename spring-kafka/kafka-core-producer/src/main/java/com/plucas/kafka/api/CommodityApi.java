package com.plucas.kafka.api;

import com.plucas.kafka.entity.Commodity;
import com.plucas.kafka.service.CommodityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/commodity")
public class CommodityApi {

    private CommodityService commodityService;

    public CommodityApi(CommodityService commodityService) {
        this.commodityService = commodityService;
    }
    @GetMapping(value = "/all")
    public List<Commodity> generateAllCommodities() {
        return commodityService.createDummyCommodities();
    }
}
