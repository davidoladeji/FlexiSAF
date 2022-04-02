package com.davidoladeji.flexisaf.data;

import com.davidoladeji.flexisaf.model.Department;
import com.davidoladeji.flexisaf.repository.DepartmentRepository;

import java.util.List;

public enum DepartmentEnum {

    DEPT_ONE(1), DEPT_TWO(2), DEPT_THREE(3), DEPT_FOUR(4);
    private final int number;

    DepartmentEnum(final int number) {
        this.number = number;
    }


}
