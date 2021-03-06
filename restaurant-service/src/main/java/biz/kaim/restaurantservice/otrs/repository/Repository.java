package biz.kaim.restaurantservice.otrs.repository;

public interface Repository<TE, T> extends ReadOnlyRepository<TE, T> {
    void add(TE entity);

    void remove(T id);

    void update(TE entity);

}
