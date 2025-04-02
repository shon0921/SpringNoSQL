package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public record MelonDTO(

        String collectTime, // 수집 시간

        String song, // 노래 제목

        String singer,  // 가수

        int singerCnt , // 차트에 등록된 가수별 노래수

        String updateSinger,    // 수정할 가수명(MongoDB 필드 추가 교육용)

        String nickname,    // 추가될 닉네임(MongoDB 필드 추가 교육용)

        List<String> member, // 추가될 그룹 멤버 이름들(MongoDB 필드 추가 교육용)

        String addFieldValue    // 추가될 필드 값(MongoDB 월드 추가 교육용)
) {
}
