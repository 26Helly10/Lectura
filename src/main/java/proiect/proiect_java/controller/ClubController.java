package proiect.proiect_java.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.proiect_java.dto.CreateClubDto;
import proiect.proiect_java.mapper.ClubMapper;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.service.ClubService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cluburi")
//@Api(value = "Swagger2DemoRestController")
public class ClubController {
    private final ClubService clubService;
    private final ClubMapper clubMapper;

    public ClubController(ClubService clubService, ClubMapper clubMapper){
        this.clubService=clubService;
        this.clubMapper=clubMapper;
    }

    @PostMapping("/posts")
/** @ApiOperation(value = "Club nou", response = Club.class, tags = "ClubNou")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })**/
    public ResponseEntity<Club> create(
            @RequestBody
            @Valid
            CreateClubDto createClubDto
    ){
        Club club= clubMapper.createClubDtotoClub(createClubDto);
        Club savedClub= clubService.create(club);
        return ResponseEntity.created(URI.create("/cluburi/"+savedClub.getIdClub()))
                .body(savedClub);
    }

    @GetMapping("/get")
  /**  @ApiOperation(value = "Club afisat", response = Club.class, tags = "afisareClub")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") }) **/
    public List<Club> get(@RequestParam(required = false)
                          String numeClub){
        return clubService.get(numeClub);
    }
}
