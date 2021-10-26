package com.example.android.catadog;

public class ItemData {
    private String nombre;
    private String URLImagen;

    public ItemData(String nombre, String URLImagen) {
        this.nombre = nombre;
        this.URLImagen = URLImagen;
    }

    public String getURLImagen() {
        return URLImagen;
    }

    public void setURLImagen(String URLImagen) {
        this.URLImagen = URLImagen;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
