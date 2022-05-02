package eezn.todolist.minitodo.domain;

public enum StatusEnum {

    TODO(1),
    DONE(2);

    private final int status;

    StatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
