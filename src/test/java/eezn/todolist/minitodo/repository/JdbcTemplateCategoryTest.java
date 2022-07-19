package eezn.todolist.minitodo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTemplateCategoryTest {
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Test
//    public void findByIdTest() {
//
//        eezn.todolist.minitodo.domain.Category category1 = categoryRepository.findById(CategoryEnum.DEFAULT.getId()).get();
//        eezn.todolist.minitodo.domain.Category category2 = categoryRepository.findById(CategoryEnum.WORK.getId()).get();
//        eezn.todolist.minitodo.domain.Category category3 = categoryRepository.findById(CategoryEnum.LIFE.getId()).get();
//
////        System.out.println(category1);
////        System.out.println(category2);
////        System.out.println(category3);
//
//        assertThat("default").isEqualTo(category1.getCategory());
//        assertThat("work").isEqualTo(category2.getCategory());
//        assertThat("life").isEqualTo(category3.getCategory());
//    }
//
//    @Test
//    public void findAllTest() {
//
//        List<eezn.todolist.minitodo.domain.Category> findAll = categoryRepository.findAll();
//
////        findAll.forEach(System.out::println);
//
//        assertThat("default").isEqualTo(findAll.get(0).getCategory());
//        assertThat("work").isEqualTo(findAll.get(1).getCategory());
//        assertThat("life").isEqualTo(findAll.get(2).getCategory());
//    }
}
