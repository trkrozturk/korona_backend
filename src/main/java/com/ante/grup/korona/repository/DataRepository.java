package com.ante.grup.korona.repository;

import com.ante.grup.korona.model.KoronaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends MongoRepository<KoronaData, String> {

    Optional<KoronaData> findByTarihAndAndSehir(String tarih,String sehir);

    Optional<KoronaData> findByVefatGreaterThanEqual(int number);

    Optional<KoronaData> findByTaburcuGreaterThanEqual(int number);
}
