package menu.model;

public class Coach {
    private final String name;

    public Coach(final String name) {
        this.name = name;
        validateName();
    }

    public void validateName() {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("이름은 2~4자 이내로 입력해야 합니다.");
        }
    }
}
