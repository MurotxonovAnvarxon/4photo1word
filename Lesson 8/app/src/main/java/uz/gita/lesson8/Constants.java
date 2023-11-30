package uz.gita.lesson8;

public enum Constants {
    MAX_IMAGES(4),
    MAX_ANSWER(8),
    MAX_VARIANT(12);
    public final int value;

    Constants(int value) {
        this.value = value;
    }
}
