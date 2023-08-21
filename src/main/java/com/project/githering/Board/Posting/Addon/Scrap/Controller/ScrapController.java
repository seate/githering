package com.project.githering.Board.Posting.Addon.Scrap.Controller;

import com.project.githering.Board.Posting.Addon.Scrap.Service.ScrapService;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {

    private final ScrapService scrapService;

    private final GeneralUserService generalUserService;

    @PostMapping("/{postingId}")
    public ResponseEntity<Void> saveScrap(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        scrapService.saveScrap(postingId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<Void> deleteScrap(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        scrapService.deleteScrap(postingId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{postingId}")
    public ResponseEntity<Boolean> isScraped(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        Boolean isScrapExist = scrapService.isScrapExist(postingId, userId);

        return new ResponseEntity<>(isScrapExist, HttpStatus.OK);
    }
}