package br.com.alura.projeto.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void existsByCode__should_return_true_when_code_exists() {
        Category category = new Category("Programação", "programacao", "#0099ff", 1, "desc", "/icon.svg");
        entityManager.persist(category);

        boolean exists = categoryRepository.existsByCode("programacao");

        assertThat(exists).isTrue();
    }

    @Test
    void existsByCode__should_return_false_when_code_does_not_exist() {
        boolean exists = categoryRepository.existsByCode("java-avancado");

        assertThat(exists).isFalse();
    }

    @Test
    void findAllByOrderByOrderAsc__should_return_categories_in_order() {
        Category category1 = new Category("Programação", "programacao", "#0099ff", 2, "desc", "/icon.svg");
        Category category2 = new Category("Front-end", "front-end", "#ff6600", 1, "desc", "/icon.svg");
        entityManager.persist(category1);
        entityManager.persist(category2);

        List<Category> categories = categoryRepository.findAllByOrderByOrderAsc();

        assertThat(categories).hasSize(2);
        assertThat(categories.get(0).getCode()).isEqualTo("front-end");
        assertThat(categories.get(1).getCode()).isEqualTo("programacao");
    }
}