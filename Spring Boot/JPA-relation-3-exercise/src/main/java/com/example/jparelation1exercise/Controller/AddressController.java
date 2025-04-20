package com.example.jparelation1exercise.Controller;

import com.example.jparelation1exercise.Api.ApiResponse;
import com.example.jparelation1exercise.DTO.AddressDTO;
import com.example.jparelation1exercise.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    @GetMapping("/get")
    public ResponseEntity getAllAddresses() {
        return ResponseEntity.status(200).body(addressService.getAllAddresses());
    }
    @PostMapping("/add")
    public ResponseEntity addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added address"));
    }
    @PutMapping("/update")
    public ResponseEntity updateAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated address"));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted address"));
    }
}
