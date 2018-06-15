package com.example.demo.REST.Controllers;

import com.example.demo.REST.Requests.AttackRequest;
import com.example.demo.REST.Responses.BattleResponse;
import com.example.demo.REST.Services.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BattleController {

    private BattleService battleService;

    @Autowired
    public BattleController(
            BattleService battleService
    ) {
        this.battleService = battleService;
    }

    @PostMapping("/attack")
    public ResponseEntity<BattleResponse> attack(@RequestBody AttackRequest request) {
        BattleResponse response =battleService.resolveBattle(
                                                request.getAttackerCity(),
                                                request.getTargetCity(),
                                                request.getTroops()
                                                );

        if(response == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
