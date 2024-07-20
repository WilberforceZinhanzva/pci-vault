package zw.co.jugaad.pcivault.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zw.co.jugaad.pcivault.models.dtos.TokenizeRequest;
import zw.co.jugaad.pcivault.models.dtos.TokenizeResponse;
import zw.co.jugaad.pcivault.services.VaultService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vault")
public class VaultController {

    private final VaultService vaultService;

    @PostMapping("/tokenize")
    public TokenizeResponse tokenize(@RequestBody TokenizeRequest request,@RequestParam String passphrase) throws Exception {
       return vaultService.tokenize(request,passphrase);
    }
}
