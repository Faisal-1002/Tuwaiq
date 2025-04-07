package com.example.capstone01repo.Service;

import com.example.capstone01repo.Model.Merchant;
import com.example.capstone01repo.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public Boolean addMerchant(Merchant merchant) {
        for (Merchant existing : merchantRepository.findAll()) {
            if (existing.getName().equalsIgnoreCase(merchant.getName())) {
                return false;
            }
        }
        merchantRepository.save(merchant);
        return true;
    }

    public Boolean updateMerchant(Integer id, Merchant updatedMerchant) {
        for (Merchant existing : merchantRepository.findAll()) {
            if (existing.getId().equals(id)) {
                updatedMerchant.setId(id); // preserve ID
                merchantRepository.save(updatedMerchant);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMerchant(Integer id) {
        for (Merchant merchant : merchantRepository.findAll()) {
            if (merchant.getId().equals(id)) {
                merchantRepository.delete(merchant);
                return true;
            }
        }
        return false;
    }

    public Merchant getMerchantById(Integer id) {
        for (Merchant merchant : merchantRepository.findAll()) {
            if (merchant.getId().equals(id)) {
                return merchant;
            }
        }
        return null;
    }
}
