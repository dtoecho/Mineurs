package com.newlecmineursprj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Address;
import com.newlecmineursprj.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Override
    public void regBymemberId(Address address, Long memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("address", address);
        params.put("memberId", memberId);
        repository.saveByMemberId(params);
    }

    @Override
    public List<Address> findAll(Long memberId) {
        return repository.findAllByMemberId(memberId);
    }

    @Override
    public Address getById(long id, Long memberId) {
        return repository.findById(id, memberId);
    }

    @Override
    public void edit(Address address) {
        repository.update(address);
    }

    @Override
    public void delete(long memberId, Long addressId) {
        repository.delete(memberId,addressId);
    }

}
