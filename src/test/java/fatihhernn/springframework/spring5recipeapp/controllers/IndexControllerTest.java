package fatihhernn.springframework.spring5recipeapp.controllers;

import fatihhernn.springframework.spring5recipeapp.domain.Recipe;
import fatihhernn.springframework.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    /**
     *
     * Create JUnit test for Index Controller
     * Use mockito Mock for RecipeService and Model
     * Verify proper String is returned
     * Verify interactions with mocks
     *
     */

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

        indexController=new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {

        //given
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe=new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        //when
        String viewName=indexController.getIndexPage(model);

        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);

        //then
        assertEquals("index",viewName);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController=argumentCaptor.getValue();
        assertEquals(2,setInController.size());
    }
}