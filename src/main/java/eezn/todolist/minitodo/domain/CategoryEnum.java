package eezn.todolist.minitodo.domain;

public enum CategoryEnum {

    DEFAULT(1),
    WORK(2),
    LIFE(3);

    private final int id;

    CategoryEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
