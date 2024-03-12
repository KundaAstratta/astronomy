package com.outer.astronomy.domain.repository.alcubierre;

import com.outer.astronomy.domain.entity.alcubierre.DataContainer;

import java.io.IOException;

public interface AlcubierreRepository {

    void save(DataContainer data, String filename) throws IOException;

}
