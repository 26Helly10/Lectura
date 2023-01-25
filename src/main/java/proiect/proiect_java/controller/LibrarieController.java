package proiect.proiect_java.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.proiect_java.dto.CreateLibrarieDto;
import proiect.proiect_java.mapper.LibrarieMapper;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.service.LibrarieService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/librarii")
//@Api(value = "Swagger2DemoRestController")
public class LibrarieController {
    private final LibrarieService librarieService;
    private final LibrarieMapper librarieMapper;

    public LibrarieController(LibrarieService librarieService, LibrarieMapper librarieMapper){
        this.librarieService= librarieService;
        this.librarieMapper=librarieMapper;
    }

    @PostMapping("/posts")
/** @ApiOperation(value = "librarie noua", response = Librarie.class, tags = "LibrarieNoua")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Librarie> create(
            @RequestBody
            @Valid
            CreateLibrarieDto createLibrarieDto
    ){
        Librarie librarie= librarieMapper.createLibrarieDtotoLibrarie(createLibrarieDto);
        Librarie savedLibrarie= librarieService.create(librarie);
        return ResponseEntity.created(URI.create("/librarii/"+savedLibrarie.getIdLibrarie()))
                .body(savedLibrarie);
    }
    @GetMapping("/get")
/**  @ApiOperation(value = "librarie afisata", response = Librarie.class, tags = "librarie Afisata")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public List<Librarie> getLibrarie(){return librarieService.getLibrarie();}
}
