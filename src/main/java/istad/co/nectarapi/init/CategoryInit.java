package istad.co.nectarapi.init;

import istad.co.nectarapi.domain.Category;
import istad.co.nectarapi.features.category.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryInit {

    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void initCategories() {
        if (categoryRepository.count() > 0) {
            return;
        }

        List<Category> categories = List.of(
                createCategory("Fruits", "Fresh and organic fruits", "https://www.ninosalvaggio.com/wp-content/uploads/2023/07/Ninos_What-Makes-A-Fruit-A-Fruit.jpg"),
                createCategory("Vegetables", "Healthy green vegetables", "https://cdn.britannica.com/17/196817-159-9E487F15/vegetables.jpg"),
                createCategory("Beverages", "Soft drinks, juices, and bottled water", "https://static.wixstatic.com/media/8f1abd_80bf432a005041b59c2a7fa1960ce71e~mv2.jpeg/v1/fill/w_744,h_400,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/8f1abd_80bf432a005041b59c2a7fa1960ce71e~mv2.jpeg"),
                createCategory("Snacks", "Chips, biscuits, and packaged snacks", "https://www.2foodtrippers.com/wp-content/uploads/2023/02/American-Snacks.jpg"),
                createCategory("Dairy", "Milk, cheese, and dairy products", "https://domf5oio6qrcr.cloudfront.net/medialibrary/9685/iStock-544807136.jpg")
        );

        categoryRepository.saveAll(categories);
    }

    private Category createCategory(String name, String description, String imageUrl) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setImageUrl(imageUrl);
        category.setUuid(UUID.randomUUID().toString());
        category.setIsDeleted(false);
        return category;
    }
}
