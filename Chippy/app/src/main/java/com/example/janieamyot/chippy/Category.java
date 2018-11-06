package com.example.janieamyot.chippy;

import java.util.Map;

public class Category {
    private String label;
    private Map<String, Service> services;

    public Category(String label){
        this.label = label;

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


    public Map<String, Service> getAllServices(){
        return services;
    }

    public Service getService(String name){
        return services.get(name);
    }

    public boolean containsService(Service service){
        return services.containsValue(service);
    }

    public String getLabel() {
        return this.label;
    }

}
