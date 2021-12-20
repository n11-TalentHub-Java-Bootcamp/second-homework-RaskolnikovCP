package com.ozansaribal.n11_bootcamp_week2_trial.Service;

import com.ozansaribal.n11_bootcamp_week2_trial.Converter.ResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebService {

    @Autowired
    private ResponseConverter xmlResponseConverter;

    public void convertResponse(){
        xmlResponseConverter.convert();
    }

}
