package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class MyRedisMapper implements IMyRedisMapper {

    private final RedisTemplate<String, Object> redisDB;
    
    /*
        RedisDB 저장된 키 삭제하는 공통 함수
     */
    private void deleteRedisKey(String rediskey) {
        
        if(Boolean.TRUE.equals(redisDB.hasKey(rediskey))) { // 데이터가 존재하면, 기존 데이터 삭제하기
            redisDB.delete(rediskey); // 삭제하기
            
            log.info("삭제 성공!");
        }
    }
    
    @Override
    public int saveString(String rediskey, RedisDTO pDTO) throws DataAccessException {

        log.info("{}.saveString Start!", this.getClass().getName());

        int res;

        String saveData = CmmUtil.nvl(pDTO.text()); // 저장할 값

        /*
            redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
         */
        redisDB.setKeySerializer(new StringRedisSerializer());  // String 타임
        redisDB.setValueSerializer(new StringRedisSerializer());    // String 타입

        this.deleteRedisKey(rediskey);  //RedisDB 저장된 키 삭제

        // 데이터 저장하기
        redisDB.opsForValue().set(rediskey, saveData);

        // RedisDB에 저장하는 데이터의 유효기간 설정(TTL 설정)
        // 2일이 지나면, 자동으로 데이터가 삭제되도록 설정함
        redisDB.expire(rediskey, 2, TimeUnit.DAYS);

        res = 1;

        log.info("{}.saveString End!", this.getClass().getName());

        return res;
    }

    @Override
    public RedisDTO getString(String rediskey) throws DataAccessException {

        log.info("{}.getString Start!", this.getClass().getName());

        log.info("String rediskey: {}", rediskey);

        /*
            redis 저장 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
         */
        redisDB.setKeySerializer(new StringRedisSerializer());  // String 타입
        redisDB.setValueSerializer(new StringRedisSerializer());    // String 타입

        RedisDTO rDTO = null;

        if (Boolean.TRUE.equals(redisDB.hasKey(rediskey))) {    // 데이터가 존재하면 조회하기
            String res = (String) redisDB.opsForValue().get(rediskey); // redisKey 통해 조회하기

            log.info("res: {}", res);   // 조회 결과

            rDTO = RedisDTO.builder().text(res).build();
        }

        log.info("{}.getString End!", this.getClass().getName());

        return rDTO;
    }
}
