package com.labwork01.app.order.repository;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.model.BouquetDTO;

import java.util.List;

public interface OrderRepositoryExtension {
    void addBouquets(long id, List<Long> bouquetsId);
    void removeBouquets(long id, List<Long> bouquetsId);
    void clearBouquets(long id);
}
