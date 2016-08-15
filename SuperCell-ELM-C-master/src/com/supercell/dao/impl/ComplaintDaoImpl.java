package com.supercell.dao.impl;

import com.supercell.dao.ComplaintDao;
import com.supercell.entity.Complaint;
import org.springframework.stereotype.Repository;

/**
 * Created by WUJO2 on 8/12/2016.
 */
@Repository
public class ComplaintDaoImpl extends GenericDaoImpl<Complaint> implements ComplaintDao {
    @Override
    Class<Complaint> classOfT() {
        return Complaint.class;
    }
}
