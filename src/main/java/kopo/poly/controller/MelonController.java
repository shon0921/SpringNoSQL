package kopo.poly.controller;

import kopo.poly.controller.response.CommonResponse;
import kopo.poly.dto.MsgDTO;
import kopo.poly.service.impl.MelonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/melon/v1")
@RequiredArgsConstructor
@RestController
public class MelonController {

    private final MelonService melonService;

    /*
        멜론 노래 리스트 저장하기
     */
    public ResponseEntity<CommonResponse<MsgDTO>> collectMelonSong() throws Exception {
        
        log.info("{}.collectMelonSong Start!", this.getClass().getName());

        // 수집 결과 출력
        String msg;

        if
    }
}
