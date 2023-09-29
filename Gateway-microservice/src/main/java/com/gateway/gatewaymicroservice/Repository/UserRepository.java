package com.gateway.gatewaymicroservice.Repository;

import com.gateway.gatewaymicroservice.Models.UserModel;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserModel, Long> {
    Mono<UserModel> findByLogin(String login);
}
