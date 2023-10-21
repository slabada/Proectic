package com.employeemanagement.employeemanagementmicroservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Модель сотрудника
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3)
    private String firstName;

    @NotNull
    @Size(min = 3)
    private String lastName;

    @NotNull
    @Size(min = 3)
    @Email
    private String email;

    @NotNull
    @Size(min = 3)
    private String address;

    /*
    * В данном случае, я считаю, что Lazy просто не уместен,
    * так как в моем проекте каждый возвращаемый объект EmployeeModel должен хранить в себе
    * поле position.
    * Если в данный момент установить Lazy, то получится, что каждый раз придется
    * "ручками" дергать position, чтобы он подгрузился, а это, грубо говоря разворот на 360 градусов,
    * телодвижение какое-то было, а результат остался таким же.
    * Если у меня был бы второй контроллер GET, который возвращал массив сотрудников в формате
    * ИМЯ ФАМАЛИЯ и никакая должность бы не понадобилась, то ставить Lazy было бы кстати,
    * так как есть контроллер, которому должность не нужна для вывода, а "левая" таблица бы все равно
    * "дергалась" при вызове и создавала бы нагрузку на БД.
    * Если в плане, что ставить Lazy или EAGER, в моем проекте есть какая-то ошибка,
    * то только в моей философии когда должна выводится должность,
    * если брать нынешнюю картину происходящего, то EAGER уместнее некуда.
    * */

    @ManyToOne()
    @JoinColumn(name = "position_id")
    private PositionModel position;
}
