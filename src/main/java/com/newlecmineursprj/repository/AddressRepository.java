package com.newlecmineursprj.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Address;

@Mapper
public interface AddressRepository {

    void saveByMemberId(Map<String, Object> params);

    List<Address> findAllByMemberId(Long memberId);

    Address findById(long id, Long memberId);

    void update(Address address);

    void delete(long memberId, Long addressId);
}
