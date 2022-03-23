package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;

public interface cvService {
    userCv saveCv(userCv cv) throws Exception;
    boolean deleteCv(userCv cv);
}
