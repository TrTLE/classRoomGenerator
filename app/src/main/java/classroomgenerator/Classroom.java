package classroomgenerator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Classroom {
    public final List<Student> studentList;
    public final List<TableRow> tableRowList;

    public Classroom(List<TableRow> tableRowList, final List<Student> studentList) {
        this.tableRowList = tableRowList;
        this.studentList = studentList;
    }

    public void generateClassPlan() {
        // put random agited student on agited table
        final List<Student> agitedStudentList = studentList.stream().filter(Student::isAgited).collect(Collectors.toList());
        Collections.shuffle(agitedStudentList);

        // how to get all agited table ?
        final List<Table> tablesThatCanHoldAgitedList = tableRowList.stream()
        .flatMap(tr -> tr.getTableList().stream())
        .filter(Table::canHoldAgited)
        .collect(Collectors.toList());
        Collections.shuffle(tablesThatCanHoldAgitedList);

        Iterator<Table> agitedTableIterators = tablesThatCanHoldAgitedList.iterator();
        agitedStudentList.forEach(agitedStudent -> {
            if (agitedTableIterators.hasNext()) {
                agitedTableIterators.next().addStudent(agitedStudent);
            }
        });

        // tableRowList.stream()
        // .filter(trl -> trl.getTableList().stream().filter(Table::canHoldAgited).findFirst().isPresent())
        // .forEach(tableRow -> {
        //     tableRow.getTableList().stream()
        //     .filter(Table::canHoldAgited)
        //     .findFirst().get().addStudent(agitedStudentList.size() < 1 ? null : agitedStudentList.get(0));
        //     agitedStudentList.remove(0);
        // });

        // put random weak student on table not direct next to an agited
        final List<Student> weakStudentList = studentList.stream().filter(Student::isWeak).collect(Collectors.toList());
        Collections.shuffle(weakStudentList);

        final List<Table> tablesThatCanHoldWeakList = tableRowList.stream()
        .flatMap(tr -> tr.getTableList().stream())
        .filter(Table::canHoldWeak)
        .collect(Collectors.toList());
        Collections.shuffle(tablesThatCanHoldWeakList);

        Iterator<Table> weakTableIterators = tablesThatCanHoldWeakList.iterator();
        weakStudentList.forEach(weakStudent -> {
            if (weakTableIterators.hasNext()) {
                weakTableIterators.next().addStudent(weakStudent);
            }
        });

        // tableRowList.stream()
        // .filter(trl -> trl.getTableList().stream().filter(Table::canHoldWeak).findFirst().isPresent())
        // .forEach(tableRow -> {
        //     tableRow.getTableList().stream()
        //     .filter(Table::canHoldWeak)
        //     .findFirst().get().addStudent(weakStudentList.size() < 1 ? null : weakStudentList.get(0));
        // });


        // put random not agited and not weak student on table
        final List<Student> restOfStudentList = studentList.stream().filter(student -> !student.isAgited() && !student.isWeak()).collect(Collectors.toList());
        Collections.shuffle(restOfStudentList);

        final List<Table> restOfTableList = tableRowList.stream()
        .flatMap(tr -> tr.getTableList().stream())
        .filter(Table::isFree)
        .collect(Collectors.toList());
        Collections.shuffle(restOfTableList);

        Iterator<Table> restOfTableIterators = restOfTableList.iterator();
        restOfStudentList.forEach(restOfStudent -> {
            if (restOfTableIterators.hasNext()) {
                restOfTableIterators.next().addStudent(restOfStudent);
            }
        });

    }

    @Override
    public String toString() {        
        return tableRowList.stream()
        .sorted((o1, o2) -> o1.displayOrder < o2.displayOrder ? -1 : 1)
        .map(TableRow::toString)
        .collect(Collectors.joining("\n\r"));
    }
}
