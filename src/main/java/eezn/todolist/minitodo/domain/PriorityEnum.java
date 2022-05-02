package eezn.todolist.minitodo.domain;

public enum PriorityEnum {

    A(1),
    B(2),
    C(3),
    D(4);

    private final int id;

    PriorityEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
