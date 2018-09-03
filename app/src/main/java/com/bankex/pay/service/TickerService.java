package com.bankex.pay.service;

import com.bankex.pay.entity.Ticker;

import io.reactivex.Observable;

public interface TickerService {

    Observable<Ticker> fetchTickerPrice(String ticker);
}
