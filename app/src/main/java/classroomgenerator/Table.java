package classroomgenerator;

import java.util.List;

public class Table {
    
    public final String id;
    public final boolean canHoldAgited;
    public final List<Table> neighbourTableList;
    
    public boolean canHoldWeak;
    public Student student;
    
    public Table(final String id) {
        this.id = id;
        this.neighbourTableList = null;
        this.canHoldAgited = false;
        this.canHoldWeak = true;
    }

    public Table(final String id, final boolean canHoldAgited) {
        this.id = id;
        this.neighbourTableList = null;
        this.canHoldAgited = canHoldAgited;
        this.canHoldWeak = !canHoldAgited;
    }

    public Table(final String id, final boolean canHoldAgited, final boolean canHoldWeak) {
        this.id = id;
        this.neighbourTableList = null;
        this.canHoldAgited = canHoldAgited;
        this.canHoldWeak = canHoldWeak;
    }

    public Table(final String id, final List<Table> neighbourTableList, final boolean canHoldAgited, final boolean canHoldWeak) {
        this.id = id;
        this.neighbourTableList = neighbourTableList;
        this.canHoldAgited = canHoldAgited;
        this.canHoldWeak = canHoldWeak;
    }

    public Table(final String id, final List<Table> neighbourTableList, final boolean canHoldAgited) {
        this.id = id;
        this.neighbourTableList = neighbourTableList;
        this.canHoldAgited = canHoldAgited;
        this.canHoldWeak = false;
    }

    public void addStudent(Student student) {
        this.student = student;
    }

    public boolean isOccuped() {
        return this.student != null;
    }

    public boolean isFree() {
        return this.student == null;
    }

    public boolean canHoldAgited() {
        return canHoldAgited;
    }

    public boolean canHoldWeak() {
        // TODO : comment définir si faible peut s'assoir sur une table ? 
        // à quelle distance d'un agité doit-il être ?
        return canHoldWeak;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return (student == null) ? "Table libre" : "" + student;
    }
}
