package com.example.jparelation1exercise.Service;

import com.example.jparelation1exercise.Api.ApiException;
import com.example.jparelation1exercise.DTO.AddressDTO;
import com.example.jparelation1exercise.Model.Address;
import com.example.jparelation1exercise.Model.Teacher;
import com.example.jparelation1exercise.Repository.AddressRepository;
import com.example.jparelation1exercise.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class AddressService {

    private AddressRepository addressRepository;
    private TeacherRepository teacherRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuilding_number(), teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (address == null) {
            throw new ApiException("Address not found");
        }
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuilding_number(addressDTO.getBuilding_number());
        addressRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            throw new ApiException("Address not found");
        }
        Teacher teacher = teacherRepository.findTeacherById(id);
        teacher.setAddress(null);
        teacherRepository.save(teacher);
        addressRepository.delete(address);
    }

}
