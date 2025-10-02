package br.com.alura.projeto.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void list__should_return_view_with_categories() throws Exception {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(new Category("Programação", "programacao", "#ffffff", 1, "", "")));

        mockMvc.perform(get("/admin/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/list"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    void save__should_redirect_when_valid() throws Exception {
        mockMvc.perform(post("/admin/category/new")
                        .param("name", "Nova Categoria")
                        .param("code", "nova-cat")
                        .param("order", "1")
                        .param("color", "#123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/categories"));
    }
}