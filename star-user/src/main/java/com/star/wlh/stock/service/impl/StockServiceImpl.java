package com.star.wlh.stock.service.impl;

import com.star.wlh.stock.entity.Stock;
import com.star.wlh.stock.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    private Stock stock = new Stock();
    @Override
    public void sell() {
        stock.setStock(stock.getStock()-1);
        System.out.println("库存余量是:"+stock.getStock());
    }
}
