package com.outer.astronomy.domain.service.alcubierre;

import java.io.IOException;

import com.outer.astronomy.domain.model.alcubierre.DataContainer;

public interface AlcubierreService {

    void save(DataContainer data, String filename) throws IOException;

}
