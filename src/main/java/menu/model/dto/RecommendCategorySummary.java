package menu.model.dto;

import menu.model.enums.Category;

public class RecommendCategorySummary {
    private final String category;

    public RecommendCategorySummary(Category category) {
        this.category = category.getCategory();
    }

    public String getCategory() {
        return category;
    }
}
