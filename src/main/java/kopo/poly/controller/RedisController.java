package kopo.poly.controller;

import kopo.poly.controller.response.CommonResponse;
import kopo.poly.dto.RedisDTO;
import kopo.poly.service.IMyRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequestMapping(value = "/redis/v1")
@RequiredArgsConstructor
@RestController
public class RedisController {

    private final IMyRedisService myRedisService;

    @PostMapping(value = "saveString")
    public ResponseEntity<CommonResponse<RedisDTO>> saveString(@RequestBody RedisDTO pDTO) throws Exception {

        log.info("{}.saveString Start", this.getClass().getName());

        log.info("{}", pDTO);   // 전달받은 값 로그로 확인하기!(반드시 작성하기)

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        RedisDTO rDTO = Optional.ofNullable(myRedisService.saveString(pDTO))
                .orElseGet(() -> RedisDTO.builder().build());

        log.info("{}.saveString End", this.getClass().getName());

        return ResponseEntity.ok(
                CommonResponse.of(HttpStatus.OK, HttpStatus.OK.series().name(), rDTO));

    }

    /*
        Redis 문자열을 JSON으로 저장 실습
     */
    @PostMapping(value = "saveStringJSON")
    public ResponseEntity<CommonResponse<RedisDTO>> saveStringJSON(@RequestBody RedisDTO pDTO) throws Exception {

        log.info("{}.saveStringJSON Start", this.getClass().getName());

        log.info("pDTO : {}", pDTO);    // 전달받은 값 로그로 확인하기!(반드시 작성하기)

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        RedisDTO rDTO = Optional.ofNullable(myRedisService.saveStringJSON(pDTO))
                .orElseGet(() -> RedisDTO.builder().build());

        log.info("{}.saveStringJSON End", this.getClass().getName());

        return ResponseEntity.ok(
                CommonResponse.of(HttpStatus.OK, HttpStatus.OK.series().name(), rDTO));
    }
}
