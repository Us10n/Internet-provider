package com.epamjwd.provider.model.entity.comparator;

import com.epamjwd.provider.model.entity.Tariff;

import java.math.BigDecimal;
import java.util.Comparator;

public class TariffNewPriceComparator implements Comparator<Tariff> {
    @Override
    public int compare(Tariff tariff1, Tariff tariff2) {
        BigDecimal price1 = tariff1.getNewPrice() == null ? tariff1.getPrice() : tariff1.getNewPrice();
        BigDecimal price2 = tariff2.getNewPrice() == null ? tariff2.getPrice() : tariff2.getNewPrice();
        return price1.compareTo(price2);
    }
}
