package com.ante.grup.korona.service;

import com.ante.grup.korona.model.KoronaData;
import com.ante.grup.korona.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    private DataRepository dataRepository;

    @Autowired
    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    public KoronaData save(KoronaData koronaData) {
        return dataRepository.save(koronaData);
    }

    @Override
    public void deleteAll() {
        dataRepository.deleteAll();
    }

    @Override
    public List<KoronaData> findAll() {
        return dataRepository.findAll();
    }

    @Override
    public Optional<KoronaData> findByTarihAndAndSehir(String tarih, String sehir) {
        return dataRepository.findByTarihAndAndSehir(tarih,sehir);
    }
}
