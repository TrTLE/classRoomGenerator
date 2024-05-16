package classroomgenerator;

import java.util.List;
import java.util.stream.Collectors;

public class TableRow {
    public final String id;
    public final int displayOrder;
    public final List<Table> tableList;

    public TableRow(final String id, final List<Table> tableList, final int displayOrder) {
        this.id = id;
        this.tableList = tableList;
        this.displayOrder = displayOrder;
    }

    public List<Table> getTableList() {
        return this.tableList;
    }

    @Override
    public String toString() {
        return "Groupe de table ["+ id +"] : \n\r {" + tableList.stream()
        .sorted((o1, o2) -> Integer.parseInt(o1.id) < Integer.parseInt(o2.id) ? -1 : 1)
        .map(Table::toString)
        .collect(Collectors.joining("\n\r")) 
        + "}";
    }

    
}
