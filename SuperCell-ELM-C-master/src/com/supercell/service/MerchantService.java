package com.supercell.service;

import com.supercell.entity.Merchant;

import java.util.List;

public interface MerchantService {
    List<Merchant> getAllMerchant();

    Merchant getMerchant(Integer merchantId);
}
