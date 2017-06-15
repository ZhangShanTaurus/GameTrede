/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 装备实体类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/14
 */
@Entity(nameInDb = "equipment")
public class Equipment {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "equipment_type")
    private int equipmentType;

    public int getEquipmentType() {
        return this.equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 627015716)
    public Equipment(Long id, int equipmentType) {
        this.id = id;
        this.equipmentType = equipmentType;
    }

    @Generated(hash = 748305627)
    public Equipment() {
    }

    public enum EquipmentType {
        WEAPON(1, "武器"),
        JEWELRY(2, "首饰");

        int type;//类型
        String desc;//描述

        private EquipmentType(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }
    }
}
