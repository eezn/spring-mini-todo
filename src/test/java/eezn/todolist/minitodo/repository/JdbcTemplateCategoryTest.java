package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Category;
import eezn.todolist.minitodo.repository.jdbc_template.JdbcTemplateCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateCategoryTest {

    @Autowired
    JdbcTemplateCategoryRepository repository;

    @Test
    public void findByIdTest() {

        Category category1 = repository.findById(1).get();
        Category category2 = repository.findById(2).get();
        Category category3 = repository.findById(3).get();

        System.out.println(category1);
        System.out.println(category2);
        System.out.println(category3);

        assertThat("default").isEqualTo(category1.getCategory());
        assertThat("work").isEqualTo(category2.getCategory());
        assertThat("life").isEqualTo(category3.getCategory());
    }

    @Test
    public void findAllTest() {

        List<Category> findAll = repository.findAll();

        for (Category category : findAll) {
            System.out.println(category.toString());
        }

        assertThat("default").isEqualTo(findAll.get(0).getCategory());
        assertThat("work").isEqualTo(findAll.get(1).getCategory());
        assertThat("life").isEqualTo(findAll.get(2).getCategory());
    }
}
