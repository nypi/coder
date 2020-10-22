package ru.croc.coder.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("ST")
@Entity
public class Student extends User {


}