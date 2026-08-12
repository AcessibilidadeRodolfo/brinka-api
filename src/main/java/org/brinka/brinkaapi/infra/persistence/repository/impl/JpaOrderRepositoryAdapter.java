package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Order;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.OrderRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.OrderMapper;
import org.brinka.brinkaapi.infra.persistence.mapper.UserMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {
    private final JpaOrderRepository jpaRepository;
    private final UserMapper userMapper;
    private final OrderMapper mapper;

    @Override
    public Order save(Order order) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(order)));
    }

    @Override
    public Optional<Order> findByIdAndUser(Integer id, User user) {
        return jpaRepository.findByIdAndUsuario(id, userMapper.toEntity(user))
                .map(mapper::toDomain);
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return jpaRepository.findAllByUsuarioOrderByDataPedidoDesc(userMapper.toEntity(user))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
