package com.zss.trade.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zss.trade.model.GoodsType;
import com.zss.trade.model.Equipment;
import com.zss.trade.model.User;

import com.zss.trade.gen.GoodsTypeDao;
import com.zss.trade.gen.EquipmentDao;
import com.zss.trade.gen.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig goodsTypeDaoConfig;
    private final DaoConfig equipmentDaoConfig;
    private final DaoConfig userDaoConfig;

    private final GoodsTypeDao goodsTypeDao;
    private final EquipmentDao equipmentDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        goodsTypeDaoConfig = daoConfigMap.get(GoodsTypeDao.class).clone();
        goodsTypeDaoConfig.initIdentityScope(type);

        equipmentDaoConfig = daoConfigMap.get(EquipmentDao.class).clone();
        equipmentDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        goodsTypeDao = new GoodsTypeDao(goodsTypeDaoConfig, this);
        equipmentDao = new EquipmentDao(equipmentDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(GoodsType.class, goodsTypeDao);
        registerDao(Equipment.class, equipmentDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        goodsTypeDaoConfig.getIdentityScope().clear();
        equipmentDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public GoodsTypeDao getGoodsTypeDao() {
        return goodsTypeDao;
    }

    public EquipmentDao getEquipmentDao() {
        return equipmentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
