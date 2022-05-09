package eezn.todolist.minitodo.domain.utils;

public enum StatusEnum {

    TODO(1),
    DONE(2);

    private final int id;

    StatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
