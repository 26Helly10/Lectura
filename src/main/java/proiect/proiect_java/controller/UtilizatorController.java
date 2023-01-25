package proiect.proiect_java.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.proiect_java.dto.CreateUtilizatorDto;
import proiect.proiect_java.dto.UpdateUtilizatorDto;
import proiect.proiect_java.mapper.UtilizatorMapper;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.service.UtilizatorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/utilizatori")
//@Api(value = "Swagger2DemoRestController")
public class UtilizatorController {
    private final UtilizatorService utilizatorService;
    private final UtilizatorMapper utilizatorMapper;

    public UtilizatorController(UtilizatorService utilizatorService, UtilizatorMapper utilizatorMapper){
        this.utilizatorService=utilizatorService;
        this.utilizatorMapper=utilizatorMapper;
    }

    @PostMapping("/posts")
    /**@ApiOperation(value = "utilizator nou", response = Utilizator.class, tags = "UtilizatorNou")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Utilizator> create(
            @RequestBody
            @Valid
            CreateUtilizatorDto createUtilizatorDto
    ){
        Utilizator utilizatorSalvat=utilizatorService.create(utilizatorMapper.createUtilizatorDtotoUtilizator(createUtilizatorDto));
        return ResponseEntity.created(URI.create("/utilizatori/"+utilizatorSalvat.getIdUtilizator()))
                .body(utilizatorSalvat);
    }

    @PutMapping("/put/{idUtilizator}")
   /** @ApiOperation(value = "utilizator update", response = Utilizator.class, tags = "UtilizatorUpdate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Utilizator> update(
            @PathVariable
            long idUtilizator,
            @RequestBody
            @Valid
            UpdateUtilizatorDto updateUtilizatorDto
    ){
        if(idUtilizator!=updateUtilizatorDto.getIdUtilizator()){
            throw new RuntimeException("Id-ul trebuie sa existe");
        }
        Utilizator utilizator = utilizatorMapper.updateUtilizatorDtotoUtilizator(updateUtilizatorDto);
        Utilizator utilizatorUpdatat= utilizatorService.uptade(utilizator);
        return ResponseEntity.ok()
                .body(utilizatorUpdatat);
    }

    @GetMapping("/get")
  /**  @ApiOperation(value = "utilizator afisare", response = Utilizator.class, tags = "UtilizatorAfisare")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public List<Utilizator> getUtilizator(){
        return utilizatorService.getUtilizator();
    }
}
