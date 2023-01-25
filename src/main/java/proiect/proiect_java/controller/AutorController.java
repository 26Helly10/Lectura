package proiect.proiect_java.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.proiect_java.dto.CreateAutorDto;
import proiect.proiect_java.mapper.AutorMapper;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Carte;
import proiect.proiect_java.service.AutorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/autori")
//@Api(value = "Swagger2DemoRestController")
public class AutorController {
    private final AutorService autorService;
    private final AutorMapper autorMapper ;

    public AutorController(AutorService autorService, AutorMapper autorMapper){
        this.autorService=autorService;
        this.autorMapper=autorMapper;
    }

    @PostMapping("/posts")
    /**@ApiOperation(value = "Autor nou", response = Autor.class, tags = "AutorNoua")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Autor> create(
            @RequestBody
            @Valid
            CreateAutorDto createAutorDto
    ){
        Autor autor =autorMapper.createAutorDtotoAutor(createAutorDto);
        Autor createdAutor = autorService.create(autor);
        return ResponseEntity.created(URI.create("/autori/"+createdAutor.getIdAutor()))
                .body(createdAutor);
    }

    @GetMapping("/get")
/**@ApiOperation(value = "Autor afisat", response = Autor.class, tags = "AutorAfisat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public List<Autor> get(
            @RequestParam(required = false)
            String numeAutor,
            @RequestParam(required = false)
            String taraOrigine
    ){
        return autorService.get(numeAutor, taraOrigine);
    }
}
