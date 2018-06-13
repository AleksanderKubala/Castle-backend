package com.example.demo.Model.Abstract;

import com.example.demo.Model.Resource.Resource;

public interface Requisition extends Quantitative {

    Resource getResource();
    Double getRecoveryCoef();
}
