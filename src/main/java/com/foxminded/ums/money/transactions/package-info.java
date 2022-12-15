@AnyMetaDef(name = "person",
        idType = "java.util.UUID",
        metaType = "string",
        metaValues = {
                @MetaValue(value = "teacher", targetEntity = Teacher.class),
                @MetaValue(value = "student", targetEntity = Student.class)})
package com.foxminded.ums.money.transactions;

import com.foxminded.ums.entities.Student;
import com.foxminded.ums.entities.Teacher;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;