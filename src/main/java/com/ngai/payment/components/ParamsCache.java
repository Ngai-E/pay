package com.ngai.payment.components;

import com.ngai.payment.model.entity.TParamters;
import com.ngai.payment.model.repository.TParamtersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ParamsCache {
    private static Map<String, TParamters> cachedDbParams;
    private static int calledTimes;
    
    private TParamtersRepository tParametersRepository;
    
    @Autowired
    public ParamsCache(TParamtersRepository tParametersRepository) {
        this.tParametersRepository = tParametersRepository;
        cachedDbParams = new HashMap<>();
        
        loadDbParams();
    }

    private void loadDbParams() {
        
        if (calledTimes > 0) return;
        
        System.out.println("lOADING PARAMETERS....");
        cachedDbParams = tParametersRepository.findAll()
                .stream()
                .filter(TParamters::getBStatus)
                .collect(Collectors.toMap(TParamters::getStrKey, Function.identity()));
        
        calledTimes++;
        System.out.println("DONE LOADING PARAMETERS");
    }

    public static Object getParam(String key) {
        return cachedDbParams.get(key).getStrValue();
    }
    public static String getParam(String key, String defaultValue){
        TParamters paramters =  cachedDbParams.getOrDefault(key, null);

        if (paramters == null) return  defaultValue;

        return paramters.getStrValue();
    }

}
