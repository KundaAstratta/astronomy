package com.outer.astronomy.domain.service.alcubierre;

import com.outer.astronomy.domain.entity.alcubierre.DataContainer;
import com.outer.astronomy.domain.repository.alcubierre.AlcubierreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DomainAlcubierreService implements AlcubierreService{

    @Autowired
    AlcubierreRepository alcubierreRepository;

    @Override
    public void save(DataContainer data, String filename) throws IOException {
        alcubierreRepository.save(data,filename);
    }

}
