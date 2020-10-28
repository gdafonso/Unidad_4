package com.example.unidad4;

public interface RepositorioHospitales {
    Hospital elemento(int id); // Devuelve el elemento dado su id
    void addHospital(Hospital hospital); // Añade el elemento indicado
    int nuevo(); // Añade un elemento en blanco y devuelve su id
    void borrar(int id); // Elimina el elemento con el id indicado
    int tam(); //Devuelve el número de elementos
    void actualiza(int id, Hospital hospital); // Reemplaza un elemento
}
