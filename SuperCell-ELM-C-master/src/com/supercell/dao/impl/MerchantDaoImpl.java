package com.supercell.dao.impl;

import com.supercell.dao.MerchantDao;
import com.supercell.entity.Merchant;
import org.springframework.stereotype.Repository;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository
public class MerchantDaoImpl extends GenericDaoImpl<Merchant> implements MerchantDao {
    @Override
    Class<Merchant> classOfT() {
        return Merchant.class;
    }
}
