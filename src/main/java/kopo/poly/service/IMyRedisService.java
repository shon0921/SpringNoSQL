package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;

public interface IMyRedisService {

    /*
        String 타입 저장 및 가져오기
     */
    RedisDTO saveString(RedisDTO pDTO) throws Exception;

    /*
        String 타입 JSON 형태로 저장하기
     */
    RedisDTO saveStringJSON(RedisDTO pDTO) throws Exception;

    /*
        List타입에 여러 문자열로 저장하기
     */
    List<String> saveList(List<RedisDTO> pList) throws Exception;
}
