package biz.kaim.restaurantservice.otrs.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends BaseEntity<String> {
    private List<Table> tables = new ArrayList<>();

    public Restaurant(String name, String id, List<Table> tables) {
        super(id, name);
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

//    @Override
//    public String toString() {
//        return String.format("{id: %s, name: %s, address: %s, tables: %s}", this.getId(),
//                this.getName(), this.getAddress(), this.getTables());
//    }


}
