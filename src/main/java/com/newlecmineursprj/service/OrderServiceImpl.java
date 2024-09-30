package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Order;
import com.newlecmineursprj.entity.OrderView;
import com.newlecmineursprj.repository.MemberRepository;
import com.newlecmineursprj.repository.OrderRepository;
import com.newlecmineursprj.repository.PointRepository;
import com.newlecmineursprj.util.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final PointRepository pointRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<OrderView> getList(Integer pageNumber) {
        int pageSize = 10;

        return getList(pageNumber, pageSize, "ordered_datetime",
                "DESC", 5, null,
                null, null, null,
                null, null).getContent();
    }

    @Override
    public CustomPageImpl<OrderView> getList(Integer pageNumber, Integer pageSize, String sortMethod,
            String sortDirection, Integer pageGroupSize, String searchMethod,
            String searchKeyword, Long memberId, String calendarStart, String calendarEnd,
            String startDate) {

        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize,
                Sort.by(Sort.Direction.fromString(sortDirection), sortMethod));

        List<OrderView> content = repository.findAll(
                pageRequest, searchMethod, searchKeyword,
                memberId, calendarStart, calendarEnd,
                startDate);

        long count = repository.getCount(searchMethod, searchKeyword, memberId);

        return new CustomPageImpl<OrderView>(content, pageRequest, count, pageGroupSize);
    }

    @Override
    public CustomPageImpl<OrderView> getList(Integer pageNumber, Integer pageSize, String sortMethod,
            String sortDirection, Integer pageGroupSize, String searchMethod,
            String searchKeyword, Long memberId) {

        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize,
                Sort.by(Sort.Direction.fromString(sortDirection), sortMethod));

        List<OrderView> content = repository.findAll(
                pageRequest, searchMethod, searchKeyword,
                memberId, null, null, null);

        long count = repository.getCount(searchMethod, searchKeyword, memberId);

        return new CustomPageImpl<OrderView>(content, pageRequest, count, pageGroupSize);
    }

    @Override
    public CustomPageImpl<OrderView> getList(Integer pageNumber, String searchMethod, String searchKeyword,
            Long memberId) {
        int pageSize = 10;
        return getList(pageNumber, pageSize, "ordered_datetime",
                "DESC", 5, searchMethod,
                searchKeyword, memberId, null,
                null, null);
    }

    @Override
    public int getCount(String searchMethod, String searchKeyword, Long memberId) {
        return repository.getCount(searchMethod, searchKeyword, memberId);
    }

    @Override
    public int getCount(String searchMethod, String searchKeyword) {
        return getCount(searchMethod, searchKeyword, null);
    }

    @Override
    public OrderView getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void add(Order order) {
        repository.add(order);
        Long memberId = order.getMemberId();
        Long id = order.getId();
        Integer totalProductPrice = order.getTotalProductPrice();
        System.out.println("totalProductPrice = " + totalProductPrice);
        System.out.println("memberId = " + memberId);
        System.out.println("id = " + id);
        Integer point = totalProductPrice / 100;
        pointRepository.save(point,memberId,id);

        memberRepository.updatePoint(point,memberId);

    }

    @Override
    public List<OrderView> getByMemberId(Long memberId) {
        return repository.findByMemberId(memberId);
    }

    @Override
    public Map<String, Integer> getOrderStateCounts(List<OrderView> orderList) {
        Map<String, Integer> orderStateCounts = new HashMap<>();
        orderStateCounts.put("결제완료", 0);
        orderStateCounts.put("배송중", 0);
        orderStateCounts.put("배송준비중", 0);
        orderStateCounts.put("배송완료", 0);

        for (OrderView order : orderList) {
            String orderState = order.getOrderState(); // 실제 OrderView에서 상태를 가져오는 코드로 대체
            orderStateCounts.put(orderState, orderStateCounts.getOrDefault(orderState, 0) + 1);
        }

        return orderStateCounts;
    }

    @Override
    public int getTotalOrderCount(Map<String, Integer> orderStateCounts) {
        int totalOrderCount = 0;
        for (int count : orderStateCounts.values()) {
            totalOrderCount += count;
        }
        return totalOrderCount;
    }

}
