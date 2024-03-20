/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.leisure.persist;

import cat.proven.dbpersist.DaoList;
import cat.proven.leisure.model.Activity;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author José Moreno
 */
public class ActivityListDao extends DaoList<Activity> {

    public ActivityListDao() {
        super(Activity.class);
        loadData();
    }

    private void loadData() {
        data = IntStream
                .range(1, 8)
                .mapToObj((value) -> {
                    return new Activity(
                            value,
                            String.format("Activ%02d", value),
                            20 + value
                    );
                })
                .collect(Collectors.toList());
    }

}
