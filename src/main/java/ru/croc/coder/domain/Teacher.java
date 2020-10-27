package ru.croc.coder.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("TR")
@Entity
public class Teacher extends User {
}
