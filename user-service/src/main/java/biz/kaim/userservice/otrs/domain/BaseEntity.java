package biz.kaim.userservice.otrs.domain;

public class BaseEntity<T> extends Entity<T> {
    private boolean isModified;

    public BaseEntity(T id, String name) {
        super.id = id;
        super.name = name;
        this.isModified = isModified;
    }

    public boolean isModified() {
        return isModified;
    }
}
