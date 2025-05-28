package com.renta.herramienta.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.CategoriaHerramientaService;
import com.renta.herramienta.domain.entities.CategoriaHerramienta;

@CrossOrigin(origins = "*") // Puerto de Vite
@RestController
@RequestMapping("/api/categorias")
public class CategoriaHerramientaController {

    @Autowired
    private CategoriaHerramientaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaHerramienta> createCategoria(@RequestBody CategoriaHerramienta categoria) {
        CategoriaHerramienta nuevaCategoria = categoriaService.createCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaHerramienta>> getAllCategorias() {
        List<CategoriaHerramienta> categorias = categoriaService.getAllCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaHerramienta> getCategoriaById(@PathVariable Long id) {
        CategoriaHerramienta categoria = categoriaService.getCategoriaById(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CategoriaHerramienta>> searchCategorias(@RequestParam String nombre) {
        List<CategoriaHerramienta> categorias = categoriaService.searchCategoriasByNombre(nombre);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaHerramienta> updateCategoria(@PathVariable Long id, @RequestBody CategoriaHerramienta categoriaDetails) {
        CategoriaHerramienta updatedCategoria = categoriaService.updateCategoria(id, categoriaDetails);
        return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
