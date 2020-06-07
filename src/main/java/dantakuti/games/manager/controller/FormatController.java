package dantakuti.games.manager.controller;

import dantakuti.games.manager.entity.GameFormat;
import dantakuti.games.manager.services.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
@RestController
@RequestMapping("/formats")
@CrossOrigin(origins="http://localhost:3000")
public class FormatController {

    @Autowired
    FormatService formatService;

    @RequestMapping("/")
    public ResponseEntity<List<GameFormat>> findAll(){
        System.out.println(formatService.listAll());
        return new ResponseEntity<List<GameFormat>>(formatService.listAll(), HttpStatus.OK);
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

}
