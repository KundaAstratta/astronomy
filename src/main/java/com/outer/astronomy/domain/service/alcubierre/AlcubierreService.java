package com.outer.astronomy.domain.service.alcubierre;

import com.outer.astronomy.domain.entity.alcubierre.DataContainer;

import java.io.IOException;

public interface AlcubierreService {

    void save(DataContainer data, String filename) throws IOException;

}
