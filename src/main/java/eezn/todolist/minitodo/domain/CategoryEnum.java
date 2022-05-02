package eezn.todolist.minitodo.domain;

public enum CategoryEnum {

    DEFAULT(1),
    WORK(2),
    LIFE(3);

    private final int category;

    CategoryEnum(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
