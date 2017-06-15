/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 物品类型
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/15
 */
@Entity(nameInDb = "goods_type")
public class GoodsType {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "type_desc")
    private String typeDesc;

    public String getTypeDesc() {
        return this.typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 939862795)
    public GoodsType(Long id, String typeDesc) {
        this.id = id;
        this.typeDesc = typeDesc;
    }

    @Generated(hash = 1568965165)
    public GoodsType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GoodsType goodsType = (GoodsType) o;

        return typeDesc != null ? typeDesc.equals(goodsType.typeDesc) : goodsType.typeDesc == null;

    }

    @Override
    public int hashCode() {
        return typeDesc != null ? typeDesc.hashCode() : 0;
    }
}
