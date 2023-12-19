package com.labwork01.app.user.repository;

import java.util.List;

public interface UserRepositoryExtension {
    void addOrders(long id, List<Long> bouquetsId);
    void removeOrders(long id, List<Long> bouquetsId);
    void clearOrders(long id);
}
