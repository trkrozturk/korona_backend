package com.ante.grup.korona.service;

import com.ante.grup.korona.model.KoronaData;

import java.util.List;
import java.util.Optional;

public interface DataService {

    KoronaData save(KoronaData koronaData);
    void deleteAll();
    List<KoronaData> findAll();
    Optional<KoronaData> findByTarihAndAndSehir(String tarih, String sehir);
}
