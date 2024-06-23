package com.outer.astronomy.domain.repository.alcubierre;

import java.io.IOException;

import com.outer.astronomy.domain.model.alcubierre.DataContainer;

public interface AlcubierreRepository {

    void save(DataContainer data, String filename) throws IOException;

}
