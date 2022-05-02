package eezn.todolist.minitodo.domain;

public enum PriorityEnum {

    A(1),
    B(2),
    C(3),
    D(4);

    private final int priority;

    PriorityEnum(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
