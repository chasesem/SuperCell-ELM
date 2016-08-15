package com.supercell.service.impl;

import com.supercell.dao.MerchantDao;
import com.supercell.entity.Merchant;
import com.supercell.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZHENGNE2
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantDao merchantDao;

    @Override
    public List<Merchant> getAllMerchant() {
        return merchantDao.getAll();
    }

    @Override
    public Merchant getMerchant(Integer merchantId) {
        return merchantDao.get(merchantId);
    }
}
