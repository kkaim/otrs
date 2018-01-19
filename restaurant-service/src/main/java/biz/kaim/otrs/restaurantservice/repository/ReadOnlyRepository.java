package biz.kaim.otrs.restaurantservice.repository;

import biz.kaim.otrs.restaurantservice.domain.Entity;

import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {
    boolean contains(T id);

    Entity get(T id);

    Collection<TE> getAll();
}
