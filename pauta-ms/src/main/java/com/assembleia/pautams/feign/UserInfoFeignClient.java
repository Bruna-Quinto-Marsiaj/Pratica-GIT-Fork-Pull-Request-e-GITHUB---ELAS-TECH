package com.assembleia.pautams.feign;

import com.assembleia.pautams.domain.UserInfoDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user-info", url = "https://user-info.herokuapp.com")
public interface UserInfoFeignClient {

    @GetMapping("/users/{cpf}")
    UserInfoDomain isAbleToVote(@PathVariable String cpf);
}
