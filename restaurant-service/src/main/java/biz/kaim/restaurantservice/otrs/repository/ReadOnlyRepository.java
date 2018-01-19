package biz.kaim.restaurantservice.otrs.repository;

import biz.kaim.restaurantservice.otrs.domain.Entity;

import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {
    boolean contains(T id);

    Entity get(T id);

    Collection<TE> getAll();
}
