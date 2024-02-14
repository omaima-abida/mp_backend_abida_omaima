package soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.entities.Categorie;
import soa.repository.CategorieRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategorieRESTController {

    @Autowired // pour l'injection de dépendances
    private CategorieRepository categRepos;

    @GetMapping(
            // spécifier le path de la méthode
            value= "/",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public  List<Categorie> getAllCategories() {
        return categRepos.findAll();

    }
    @GetMapping(
            // spécifier le path de la méthode
            value= "/{libelle}",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<Categorie> getCategorieByLibelle(@PathVariable String libelle) {
        Categorie categorie = categRepos.findByLibelle(libelle);
        if (categorie != null) {
            return ResponseEntity.ok().body(categorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}