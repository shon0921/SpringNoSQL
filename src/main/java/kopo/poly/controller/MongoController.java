package kopo.poly.controller;

import com.mongodb.client.MongoClient;
import jakarta.validation.Valid;
import kopo.poly.controller.response.CommonResponse;
import kopo.poly.dto.MongoDTO;
import kopo.poly.dto.MsgDTO;
import kopo.poly.service.IMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/mongo/v1")
@RequiredArgsConstructor
@RestController
public class MongoController {

    private final IMongoService mongoService;

    @PostMapping("/basic")
    public ResponseEntity<?> basic(@Valid @RequestBody MongoDTO pDTO, BindingResult bindingResult)
            throws Exception {

        log.info("{}.basic Start!", this.getClass().getName());

        // 1. DTO 유효성 검사 실패 시 에러 응답 반환
        if (bindingResult.hasErrors()) {    // Spting Validation 맞춰 잘 바인딩되었는지 체크
            return CommonResponse.getErrors(bindingResult); // 유효성 검증 결과에 따른 에러 메시지 전달

        }

        
        // 2. 전달받은 DTO 로그 출력
        log.info("Received MongoDTO : {}", pDTO); // 입력 받은 값 확인하기

        // 3. MongoDB에 데이터 저장 시도
        int res = mongoService.mongoTest(pDTO);

        // 4. 저장 결과 메시지 설정
        String msg = (res == 1) ? "저장 성공하였습니다." : "저장 실패하였습니다";

        // 5. 결과 메시지를 DTO로 구성
        MsgDTO dto = MsgDTO.builder()
                .result(res)
                .msg(msg)
                .build();

        log.info("{}.basic End!", this.getClass().getName());

        // 6. 공통 응답 객체에 결과 담아 반환
        return ResponseEntity.ok(
                CommonResponse.of(HttpStatus.OK, HttpStatus.OK.series().name(), dto));
    }
}
