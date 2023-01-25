package proiect.proiect_java.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.proiect_java.dto.CreateCarteDto;
import proiect.proiect_java.dto.UpdateCarteDto;
import proiect.proiect_java.mapper.CarteMapper;
import proiect.proiect_java.model.Carte;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.service.CarteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/carti")
//@Api(value = "Swagger2DemoRestController")
public class CarteController {
    private final CarteService carteService;
    private final CarteMapper carteMapper;

    public CarteController(CarteService carteService, CarteMapper carteMapper){
        this.carteMapper=carteMapper;
        this.carteService=carteService;
    }

    @PostMapping("/posts")
/** @ApiOperation(value = "Carte noua", response = Carte.class, tags = "CarteNoua")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Carte> create(

            @RequestBody
            @Valid
            CreateCarteDto createCarteDto
    ) {

        Carte carteSalvata=carteService.create(carteMapper.createCarteDtotoCarte(createCarteDto));
        return ResponseEntity.created(URI.create("/carti/"+carteSalvata.getIdCarte()))
                .body(carteSalvata);
    }

    @PutMapping("/put/{idCarte}")
/**  @ApiOperation(value = "Carte updatata", response = Carte.class, tags = "CarteUpdatata")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Carte> update(
            @PathVariable
            long idCarte,
            @RequestBody
            @Valid
            UpdateCarteDto updateCarteDto
    ){
        if (idCarte!= updateCarteDto.getIdCarte()){
            throw new RuntimeException("Id-ul trebuie sa existe");
        }

        Carte carte= carteMapper.updateCarteDtotoCarte(updateCarteDto);
        Carte carteupdatata=carteService.update(carte);
        return ResponseEntity.ok()
                .body(carteupdatata);
    }

    @GetMapping("/get")
/** @ApiOperation(value = "Carte afisata", response = Carte.class, tags = "CarteAfisata")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public List<Carte> get(
            @RequestParam(required = false)
            String numeCarte,
            @RequestParam(required = false)
            String genCarte,
            @RequestParam(required = false)
            String editura,
            @RequestParam(required = false)
            int steluteCarte,
            @RequestParam(required = false)
            String citit,
            @RequestParam(required = false)
            String inBiblioteca
    ){return carteService.get(numeCarte,genCarte,editura,steluteCarte,citit,inBiblioteca);}

}
