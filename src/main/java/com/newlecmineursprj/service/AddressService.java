package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Address;

public interface AddressService {
    void regBymemberId(Address address, Long memberId);

    List<Address> findAll(Long memberId);

    Address getById(long id, Long memberId);

    void edit(Address address);

    void delete(long memberId, Long addressId);
}
