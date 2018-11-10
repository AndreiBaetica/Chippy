package com.example.janieamyot.chippy;

import static org.junit.Assert.*;
import org.junit.Test;

public class CategoryCreationTest {

    @Test
    public void checkCategoryLabel(){
        Category aCategory = new Category("Electrical Work");
        assertEquals("Check the label of the category", "Electrical Work", aCategory.getLabel());
    }

    @Test
    public void checkCategoryAddService(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement", aCategory);
        aCategory.addService(aService);
        assertEquals("Check if a service is in the category", "Light bulb replacement", aCategory.getService(aService.getName()).getName());
    }

    @Test
    public void checkCategoryContainService(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement", aCategory);
        aCategory.addService(aService);
        assertEquals("Check if a service is in the category", "true", Boolean.toString(aCategory.containsService(aService)));
    }
}
