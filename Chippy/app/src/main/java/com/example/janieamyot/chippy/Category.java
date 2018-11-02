package com.example.janieamyot.chippy;

import java.util.Map;

public class Category {
    private String label;
    private Map<String, Category> subcategories;
    private Category superCategory;
    private Map<String, Service> services;

    public Category(String label, Category superCategory){
        this.label = label;
        try{
            this.superCategory = new Category(superCategory);
        }catch(NullPointerException e){
            //in the case the category has no superCategory
            this.superCategory = null;
        }
    }
    //copy constructor
    public Category (Category source) {
        this.label= source.getLabel();
        //make new objects to prevent privacy leaks
        Map<String, Category> categoryMap = this.getAllCategories();
        this.subcategories = categoryMap;
        Map<String, Service> serviceMap = this.getAllServices();
        this.services= serviceMap;
        Category superCategory = source.getSuperCategory();
        this.superCategory = superCategory;
    }

    public boolean addService(Service service){
        boolean result;
        try {
            this.services.put(service.getName(), service);
            result= true;
        }catch(NullPointerException e){
            result =false;
        }
        return result;
    }

    public boolean addSubCategory(Category subCategory){
        boolean result;
        try{
            this.subcategories.put(subCategory.getLabel(), subCategory);
            result=true;
        }catch(NullPointerException e) {
            result = false;
        }
        return result;
    }

    public Map<String, Service> getAllServices(){
        return services;
    }

    public Map<String, Category> getAllCategories(){
        return subcategories;
    }

    public Service getService(String name){
        return services.get(name);
    }

    public Category getSubCategory(String label){
        return subcategories.get(label);
    }

    public boolean containsService(Service service){
        return services.containsValue(service);
    }

    public boolean containsSubCategory(Category category){
        return subcategories.containsValue(category);
    }

    public boolean hasSuperCategory(){
        if (this.superCategory !=null){
            return true;
        }
        else{
            return false;
        }
    }
    public String getLabel() {
        return this.label;
    }

    public Category getSuperCategory(){
        if (this.hasSuperCategory()) {
            return this.superCategory;
        }
        else{
            //this may need to be updated depending on how/if it is implemented
            return this;
        }
    }
}
