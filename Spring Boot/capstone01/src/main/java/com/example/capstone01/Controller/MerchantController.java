package com.example.capstone01.Controller;

import com.example.capstone01.Api.ApiResponse;
import com.example.capstone01.Model.Merchant;
import com.example.capstone01.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant/")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchants(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantService.addMerchant(merchant))
            return ResponseEntity.status(200).body(new ApiResponse("Merchant added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not added"));
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantService.updateMerchant(id,merchant))
            return ResponseEntity.status(200).body(new ApiResponse("Merchant updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        if (merchantService.deleteMerchant(id))
            return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not deleted"));
    }
    @GetMapping("/searchbyid/{id}")
    public ResponseEntity getMerchantById(@PathVariable String id){
        if (merchantService.getMerchantById(id) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));
        return ResponseEntity.status(200).body(merchantService.getMerchantById(id));
    }
}
