package biz.kaim.restaurantservice.otrs.domain;

public class BaseEntity<T> extends Entity<T> {

    private boolean isModified;

    public BaseEntity(T id, String name) {
        super.id = id;
        super.name = name;
        isModified = false;
    }
    public boolean isIsModified() {
        return isModified;
    }

}
