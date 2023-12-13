package menu.model;

import java.util.ArrayList;
import java.util.List;
import menu.model.enums.Category;
import menu.model.generator.CategoryNumberGenerator;
import menu.model.generator.MenuShuffleGenerator;

public class Recommend {
    private final CategoryNumberGenerator categoryNumberGenerator;
    private final MenuShuffleGenerator menuShuffleGenerator;
    private List<Category> recommendedCategories;

    public Recommend(CategoryNumberGenerator categoryNumberGenerator, MenuShuffleGenerator menuShuffleGenerator) {
        this.categoryNumberGenerator = categoryNumberGenerator;
        this.menuShuffleGenerator = menuShuffleGenerator;
        this.recommendedCategories = new ArrayList<>();
    }

    public Category recommendCategory() {
        int categoryId = categoryNumberGenerator.generate();
        Category recommendedCategory = Category.findById(categoryId);
        validateRecommendedCategory(recommendedCategory);
        recommendedCategories.add(recommendedCategory);
        return recommendedCategory;
    }

    private void validateRecommendedCategory(Category recommendedCategory) {
        long count = recommendedCategories.stream()
                .filter(category -> category == recommendedCategory)
                .count();

        if (count >= 2) {
            throw new IllegalStateException("이미 2번 이상 추천된 카테고리입니다.");
        }
    }

    public String recommendMenuByCategory(Category category) {
        List<String> menuNames = category.getMenuNames();
        return menuShuffleGenerator.generate(menuNames).get(0);
    }
}
