package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.MelonDTO;
import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMongoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Component
public class MongoMapper extends AbstractMongoDBComon implements IMongoMapper {

    private final MongoTemplate mongodb;

    @Override
    public int insertData(MongoDTO pDTO, String colNm) throws MongoException {

        log.info("{}.insertData Start!", this.getClass().getName());

        int res;

        // 데이터를 저장할 컬렉션 생성
        if (super.createCollection(mongodb, colNm)) {
            log.info("{} 생성되었습니다.",colNm);
        }

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info("{}.insertData End!", this.getClass().getName());

        return res;
    }

}
